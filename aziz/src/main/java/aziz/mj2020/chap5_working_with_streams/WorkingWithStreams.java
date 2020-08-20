package aziz.mj2020.chap5_working_with_streams;


import aziz.data.*;

import java.io.*;
import java.nio.charset.*;
import java.nio.file.*;
import java.util.*;
import java.util.stream.*;

import static aziz.data.Data.CAMBRIDGE;
import static aziz.data.Data.HELLO_WORLD;
import static aziz.data.Data.M;
import static aziz.data.Data.MILAN;
import static aziz.data.Data.N;
import static aziz.data.Data.NUMBER_ARRAY;
import static aziz.data.Data.N_WITH_DUPS;
import static aziz.data.Data.STREAM_OF_HELLO_W;
import static aziz.data.Data.T;
import static aziz.data.Data.WORDS;
import static aziz.data.Data.newMenu;
import static aziz.data.Data.printMenu;
import static aziz.data.Data.printNumbers;
import static aziz.data.Data.printSortedMenu;
import static aziz.data.Data.printTransactions;


public class WorkingWithStreams {
    /**
     * 5.1 Filtering
     */
    private static class Filtering {

        public static void main(String[] args) {
            try {
                final List<Dish> veggies = M.stream()
                                            .filter(Dish::isVegetarian)
                                            .collect(Collectors.toList());

                final List<Integer> nonDups = N_WITH_DUPS.stream()
                                                         .filter(n -> n % 2 == 0)
                                                         .distinct()
                                                         .collect(Collectors.toList());
                System.out.println("nonDups: " + nonDups);


            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }//////////////////////////////////////////////////    //////////////////////////////////////////////////

    /**
     * 5.2 Slicing a stream
     * WARNING: Java 9 features
     */
    private static class SlicingStream {

        public static void main(String[] args) {
            try {
                final List<Dish> filteredMenu = M.stream()
                                                 .filter(d -> d.getCalories() < 320)
                                                 .collect(Collectors.toList());
                System.out.println("filteredMenu: " + filteredMenu);


                List<Dish> sortedMenu = Data.newMenu().stream().sorted(Dish::compareTo).collect(Collectors.toList());
                final List<Dish> takeWhileLessThan320 = sortedMenu.stream()
                                                                  .takeWhile(d -> d.getCalories() < 320)
                                                                  .collect(Collectors.toList());
                System.out.println("takeWhileLessThan320: " + takeWhileLessThan320);

                sortedMenu = Data.newMenu().stream().sorted(Dish::compareTo).collect(Collectors.toList());
                final List<Dish> dropWhile = sortedMenu.stream()
                                                       .dropWhile(d -> d.getCalories() < 320)
                                                       .collect(Collectors.toList());
                System.out.println("dropWhile: " + dropWhile);

                final List<Dish> first3over320Cal = M.stream()
                                                     .filter(d -> d.getCalories() > 320)
                                                     .limit(3)
                                                     .collect(Collectors.toList());

                printSortedMenu();
                final List<Dish> skip2 = newMenu().stream()
                                                  .filter(d -> d.getCalories() > 300)
                                                  .skip(2)
                                                  .collect(Collectors.toList());
                System.out.println("skip2: " + skip2);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }//////////////////////////////////////////////////    //////////////////////////////////////////////////

    /**
     * 5.3 Mapping
     */
    private static class Mapping {

        public static void main(String[] args) {
            try {
                final List<String> words = STREAM_OF_HELLO_W
                        .map(s -> s.split(""))
                        .flatMap(array -> {
                            System.out.println("array: " + Arrays.toString(array));
                            return Arrays.stream(array);
                        })
                        .distinct()
                        .collect(Collectors.toList());
                System.out.println("words: " + words);


            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }//////////////////////////////////////////////////    //////////////////////////////////////////////////

    /**
     * 5.4 Finding and matching
     */
    private static class FindingAndMatching {

        public static void main(String[] args) {
            try {
                final boolean allMatchLessThan1000cal = M.stream()
                                                         .allMatch(d -> d.getCalories() < 1000);
                System.out.println("allMatchLessThan1000cal: " + allMatchLessThan1000cal);


                final boolean atLeastOneDishWith120cal = M.stream()
                                                          .noneMatch(dish -> dish.getCalories() >= 1000);
                System.out.println("atLeastOneDishWith120cal: " + atLeastOneDishWith120cal);

                M.stream()
                 .filter(Dish::isVegetarian)
                 .findAny()
                 .ifPresent(System.out::println);

                M.stream()
                 .filter(Dish::isVegetarian)
                 .findFirst()
                 .ifPresent(System.out::println);


            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }//////////////////////////////////////////////////    //////////////////////////////////////////////////


    /**
     * 5.5 Reducing
     */
    private static class Reducing {

        public static void main(String[] args) {
            try {
                printNumbers();
                N.stream()
                 .map(n -> n * n)
                 .filter(n -> n % 3 == 0)
                 .findFirst()
                 .ifPresent(System.out::println);


                final Integer sum = N.stream()
                                     .reduce(0, Integer::sum);
                System.out.println("sum: " + sum);

                N.stream()
                 .reduce((a, b) -> a + b)
                 .ifPresent(System.out::println);

                N.stream()
                 .reduce(Integer::max)
                 .ifPresent(System.out::println);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }//////////////////////////////////////////////////    //////////////////////////////////////////////////

    /**
     * 5.6 Putting it all into practice
     */
    private static class Practice {

        public static void main(String[] args) {
            try {
                printTransactions();
                final List<Transaction> txFor2011SortedByValue = T.stream()
                                                                  .filter(t -> t.getYear() == 2011)
                                                                  .sorted(Comparator.comparing(Transaction::getValue))
                                                                  .collect(Collectors.toList());
                System.out.println("txFor2011SortedByValue: " + txFor2011SortedByValue);

                final List<String> uniqueCities = T.stream()
                                                   .map(t -> t.getTrader().getCity())
                                                   .distinct()
                                                   .collect(Collectors.toList());
                System.out.println("uniqueCities: " + uniqueCities);


                final Set<String> cities = T.stream()
                                            .map(t -> t.getTrader().getCity())
                                            .collect(Collectors.toSet());
                System.out.println("cities: " + cities);

                final List<String> sortedCambridgeTraders = T.stream()
                                                             .map(Transaction::getTrader)
                                                             .filter(t -> CAMBRIDGE.equals(t.getCity()))
                                                             .map(Trader::getName)
                                                             .distinct()
                                                             .sorted()
                                                             .collect(Collectors.toList());
                System.out.println("sortedCambridgeTraders: " + sortedCambridgeTraders);

                // INEFFICIENT
//                final String sortedTraderNames = T.stream()
//                                                        .map(t -> t.getTrader().getName())
//                                                        .distinct()
//                                                        .sorted()
//                                                        .reduce("", (n1, n2) -> n1 + " " + n2);
//                System.out.println("sortedTraderNames: " + sortedTraderNames);

                final String sortedTraderNames = T.stream()
                                                  .map(t -> t.getTrader().getName())
                                                  .distinct()
                                                  .sorted()
                                                  .collect(Collectors.joining());
                System.out.println("sortedTraderNames: " + sortedTraderNames);


                final Optional<Trader> anyMilanTraders = T.stream()
                                                          .map(Transaction::getTrader)
                                                          .filter(t -> MILAN.equals(t.getCity()))
                                                          .findAny();
                System.out.println("anyMilanTraders: " + anyMilanTraders);

                final boolean anyMatchMilanTrader = T.stream()
                                                     .anyMatch(t -> MILAN.equals(t.getTrader().getCity()));
                System.out.println("anyMatchMilanTrader: " + anyMatchMilanTrader);


                final List<Transaction> allCambridgeTxs = T.stream()
                                                           .filter(t -> CAMBRIDGE.equals(t.getTrader().getCity()))
                                                           .collect(Collectors.toList());
                System.out.println("allCambridgeTxs: " + allCambridgeTxs);


                final Optional<Integer> maxTxValue = T.stream()
                                                      .map(Transaction::getValue)
                                                      .max(Integer::compareTo);
                System.out.println("maxTxValue: " + maxTxValue);

                // Using reduce
                final Optional<Integer> maxTxValueReduce = T.stream()
                                                            .map(Transaction::getValue)
                                                            .reduce(Integer::max);
                System.out.println("maxTxValueReduce: " + maxTxValueReduce);


                final Optional<Transaction> txWithMinValue = T.stream()
                                                              .min(Comparator.comparing(Transaction::getValue));
                System.out.println("txWithMinValue: " + txWithMinValue);

                final Optional<Transaction> txWithMinValueReduce = T.stream()
                                                                    .reduce((t1, t2) -> t1.getValue() < t2.getValue() ? t1 : t2);
                System.out.println("txWithMinValueReduce: " + txWithMinValueReduce);


                final Optional<Transaction> txWithMinValueReduceIf = T.stream()
                                                                      .reduce((t1, t2) -> {
                                                                          if (t1.getValue() < t2.getValue()) {
                                                                              return t1;
                                                                          }
                                                                          return t2;

                                                                      });
                System.out.println("txWithMinValueReduceIf: " + txWithMinValueReduceIf);


            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }//////////////////////////////////////////////////    //////////////////////////////////////////////////

    /**
     * 5.7 Numeric streams
     */
    private static class NumericStreams {

        public static void main(String[] args) {
            try {


                final Integer sumReduce = N.stream()
                                           .reduce(0, (a, b) -> a + b);
                System.out.println("sumReduce: " + sumReduce);

                final Integer sumReduceSum = N.stream()
                                              .reduce(0, Integer::sum);
                System.out.println("sumReduceSum: " + sumReduceSum);


                final Integer totalDishCaloriesReduce = M.stream()
                                                         .map(Dish::getCalories)
                                                         .reduce(0, Integer::sum);
                System.out.println("totalDishCaloriesReduce: " + totalDishCaloriesReduce);

                final int totalDishesCalMapToInt = M.stream()
                                                    .mapToInt(Dish::getCalories)
                                                    .sum();
                System.out.println("totalDishesCalMapToInt: " + totalDishesCalMapToInt);

                final IntStream intStream = M.stream().mapToInt(Dish::getCalories);
                final Stream<Integer> boxed = intStream.boxed();

                final int maxDishCalories = M.stream()
                                             .mapToInt(Dish::getCalories)
                                             .max()
                                             .orElse(0);
                System.out.println("maxDishCalories: " + maxDishCalories);


            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }//////////////////////////////////////////////////    //////////////////////////////////////////////////

    /**
     * 5.7.2 Numeric ranges
     */
    private static class NumericRanges {

        public static void main(String[] args) {
            try {
                final IntStream zeroToTen = IntStream.rangeClosed(0, 10);
                zeroToTen.forEach(System.out::print);

                final String pythagorean = IntStream.rangeClosed(1, 100).boxed()
                                                    .flatMap(a -> IntStream.rangeClosed(a, 100)
                                                                           .filter(b -> Math.sqrt(a * a + b * b) % 1 == 0)
                                                                           .mapToObj(b ->
                                                                                   new int[]{a, b, (int) Math.sqrt(a * a + b * b)})
                                                    )
                                                    .map(Arrays::toString)
                                                    .collect(Collectors.joining(", "));
                System.out.println("pythagorean: " + pythagorean);

                final String pythagorean2 = IntStream.rangeClosed(1, 100).boxed()
                                                     .flatMap(a -> IntStream.rangeClosed(a, 100)
                                                                            .mapToObj(b -> new double[]{a, b, Math.sqrt(
                                                                                    a * a + b * b)})
                                                                            .filter(array -> array[2] % 1 == 0))
                                                     .map(doubleArray ->
                                                             new int[]{(int) doubleArray[0], (int) doubleArray[1], (int) doubleArray[2]})
                                                     .map(Arrays::toString)
                                                     .collect(Collectors.joining(", "));
                System.out.println("pythagorean2: " + pythagorean2);


            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }//////////////////////////////////////////////////    //////////////////////////////////////////////////

    /**
     * 5.8 Building streams
     */
    private static class BuildingStreams {

        public static void main(String[] args) {
            try {
                final String upperC = WORDS.stream()
                                            .map(String::toUpperCase)
                                            .collect(Collectors.joining(" "));
                System.out.println("upperC: " + upperC);


                // WARNING: JAVA 9
                final List<String> config = Stream.of("config", "home", "user")
                                                   .flatMap(key -> Stream.ofNullable(getKey(key)))
                                                   .collect(Collectors.toList());
                System.out.println("config: " + config);

                final int sum = Arrays.stream(NUMBER_ARRAY)
                                      .sum();
                System.out.println("sum: " + sum);
                

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        private static String getKey(String name) {
            String key = null;
            switch (name) {
                case "config" :
                case "user" :
                    key = name;
                break;
                case "home" :
                    break;
                default: key = "unknown";
            }
                return key;
        }
    }//////////////////////////////////////////////////    //////////////////////////////////////////////////


    /**
     * 5.8.4 Streams from files
     */
    private static class StreamsFromFiles {

        public static void main(String[] args) {
            try {
               long uniqueWords = 0;

               try(Stream<String> lines = Files.lines(Paths.get("data.txt" ),Charset.defaultCharset())){
                   uniqueWords = lines.flatMap(l -> Arrays.stream(l.split(" ")))
                           .distinct()
                           .count();
               } catch (IOException e) {

               }
                System.out.println("uniqueWords: " + uniqueWords);

                
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }//////////////////////////////////////////////////    //////////////////////////////////////////////////

    /**
     * 5.8.5 Streams from functions: creating infinite streams!
     */
    private static class StreamsFromFunctions {

        public static void main(String[] args) {
            try {
                Stream.iterate(0, n -> n + 2)
                      .limit(10)
                      .map(n -> n + ", ")
                      .forEach(System.out::print);
                System.out.println();
                
                // (0, 1), (1, 1), (1, 2), (2, 3), (3, 5), (5, 8), (8, 13), (13, 21).
                Stream.iterate(new int[] {0, 1}, n -> new int[] {n[1], n[0] + n[1]})
                      .map(n -> "(" + n[0] + "," + n[1] + "), ")
                      .limit(20)
                      .forEach(System.out::print);
                System.out.println();

                IntStream.iterate(0, n -> n < 100, n -> n + 4)
                         .mapToObj(n -> n + ", ")
                         .forEach(System.out::print);

//                IntStream.iterate(0, n -> n + 4)
//                         .filter(n -> n < 100)
//                         .mapToObj(n -> n + ", ")
//                         .forEach(System.out::print);
                // INFINITE LOOPING!

                System.out.println();
                
                IntStream.iterate(0, n -> n + 4)
                         .takeWhile(n -> n < 100)
                         .mapToObj(n -> n + ", ")
                         .forEach(System.out::print);

                System.out.println();
                
                Stream.generate(Math::random)
                      .limit(5)
                      .map(d -> ((int) (d * 100)))
                      .forEach(System.out::println);


                

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }//////////////////////////////////////////////////    //////////////////////////////////////////////////

    
    
    
    
    
    
    
    

}
