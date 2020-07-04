package aziz.mj2020.chap4_stream_intro;

import aziz.data.*;

import java.io.*;
import java.util.*;
import java.util.function.*;
import java.util.stream.*;

import static aziz.data.Data.*;


public class StreamIntro {

    /**
     * 4.1 What are streams?
     */
    private static class WhatAreStreams {

        public static void main(String[] args) {
            try {
                final List<Dish> lessThan400CalSortedByCalories = M.stream()
                                                                   .filter(d -> d.getCalories() < 400)
                                                                   .sorted(Comparator
                                                                           .comparing(Dish::getCalories)
                                                                           .reversed())
                                                                   .collect(Collectors.toList());
                System.out.println("lessThan400CalSortedByCalories: " + lessThan400CalSortedByCalories);


                final Map<Dish.Type, List<Dish>> dishTypeMap = M.stream()
                                                                .collect(Collectors.groupingBy(Dish::getType));
                for (Dish.Type type : dishTypeMap.keySet()) {
                    System.out.println(type + ": " + dishTypeMap.get(type));
                }

                final List<Dish> sortedMenu = newMenu().stream()
                        .sorted(Comparator.comparing(Dish::getCalories))
                        .collect(Collectors.toList());
                System.out.println("\n" + sortedMenu + "\n");


                final List<String> first3highCalDishes = newMenu().stream()
                                                          .filter(d -> d.getCalories() > 300)
                                                          .sorted(Comparator.comparing(Dish::getCalories).reversed())
                                                          .map(Dish::getName)
                                                          .limit(3)
                                                          .collect(Collectors.toList());
                System.out.println("first3highCalDishes: " + first3highCalDishes);


            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }//////////////////////////////////////////////////    //////////////////////////////////////////////////
}
