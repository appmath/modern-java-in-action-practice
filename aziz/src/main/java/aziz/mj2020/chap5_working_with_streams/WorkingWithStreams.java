package aziz.mj2020.chap5_working_with_streams;



import aziz.data.Dish;

import java.util.List;
import java.util.stream.Collectors;

import static aziz.data.Data.M;
import static aziz.data.Data.N_WITH_DUPS;


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
                

                final List<Dish> takeWhileLessThan320 = M.stream()
                                            .takeWhile(d -> d.getCalories() < 320)
                                            .collect(Collectors.toList());
                System.out.println("takeWhileLessThan320: " + takeWhileLessThan320);


            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }//////////////////////////////////////////////////    //////////////////////////////////////////////////

}
