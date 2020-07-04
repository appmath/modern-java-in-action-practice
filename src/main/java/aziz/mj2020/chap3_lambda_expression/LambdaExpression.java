package aziz.mj2020.chap3_lambda_expression;

import aziz.data.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;

import static aziz.data.Data.A;
import static aziz.data.Data.newApples;
import static aziz.data.Data.newDataFileReader;


public class LambdaExpression {
    /**
     * LambdasInaNutshell
     */
    private static class LambdasInaNutshell {


        public static void main(String[] args) {
            try {
                final Comparator<Apple> byWeight = (Apple a1, Apple a2) -> a1.getWeight().compareTo(a2.getWeight());
                process(() -> System.out.println("Hello"));
                System.out.println("processFile(): " + processFile1());
                final String info = processFile((BufferedReader b) -> b.readLine() + b.readLine());
                System.out.println("info: " + info);

                // 3.4.1 Predicate
                final List<Apple> heavyApples = filter(A, a -> a.getWeight() > 100);
                System.out.println("\nheavyApples: " + heavyApples);

                // 3.4.2 Consumer
                System.out.println("\nConsumer, print apples");
                print(A, a -> System.out.println(a));

                // 3.4.3
                System.out.println("\nFunction, enlarge apples");
                List<Apple> largerApples = enlarge(A, a -> new Apple(a.getWeight() + 50));
                System.out.println("largerApples: " + largerApples);

                // 3.5.2 Same lambda, different functional interfaces
                final Comparator<Apple> appleComparator = (Apple a1, Apple a2) -> a1
                        .getWeight()
                        .compareTo(a2.getWeight());

                final BiFunction<Apple, Apple, Integer> biFunction = (Apple a1, Apple a2) -> a1
                        .getWeight()
                        .compareTo(a2.getWeight());

                final ToIntBiFunction<Apple, Apple> toIntBiFunction = (Apple a1, Apple a2) -> a1
                        .getWeight()
                        .compareTo(a2.getWeight());

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        private static void print(List<Apple> apples, MyConsumer<Apple> c) {
            apples.stream()
                  .forEach(a -> c.accept(a));

        }

        private static List<Apple> filter(List<Apple> apples, MyPredicate<Apple> p) {
            List<Apple> filteredApples = new ArrayList<>();
            for (Apple apple : apples) {
                if (p.test(apple)) {
                    filteredApples.add(apple);
                }

            }
            return filteredApples;
        }

        private static List<Apple> enlarge(List<Apple> apples, MyFunction<Apple, Apple> f) {
            return apples.stream()
                         .map(a -> f.apply(a))
                         .collect(Collectors.toList());
        }

        private static String processFile(BufferedReaderProcess p) throws IOException {
            try (final BufferedReader b = new BufferedReader(newDataFileReader())) {
                return p.process(b);
            }
        }


        private static String processFile1() throws IOException {
            try (BufferedReader br = new BufferedReader(newDataFileReader())) {
                return br.readLine();
            }
        }
        private static String processFileExp() throws IOException {
            try (BufferedReader br = new BufferedReader(newDataFileReader())) {
                return br.readLine();
            }
        }


        private static void process(Runnable hello) {
            hello.run();
        }
    }//////////////////////////////////////////////////    //////////////////////////////////////////////////

    // BufferedReaderProcessor
    @FunctionalInterface
    interface BufferedReaderProcess {
        String process(BufferedReader b) throws IOException;
    }

    @FunctionalInterface
    interface MyPredicate<T> {
        boolean test(T t);
    }

    @FunctionalInterface
    interface MyConsumer<T> {
        void accept(T t);
    }

    @FunctionalInterface
    interface MyFunction<T, R> {
        R apply(T t);
    }

    /**
     * 3.6.2 Constructor references
     */
    private static class ConstructorReferences {

        static Map<String, Function<Integer, Fruit>> map = new HashMap<>();
        static {
            map.put("apple", Apple::new);
            map.put("orange", Orange::new);
        }

        private static Fruit giveMeFruit(String fruit, Integer weight){
             return map.get(fruit).apply(weight);
        }

        public static void main(String[] args) {
            try {
                Supplier<Apple> c1 = Apple::new;
                Apple apple = c1.get();

                c1  = () -> new Apple();
                apple = c1.get();

                Function<Integer, Apple> c2 = Apple::new;
                apple = c2.apply(100);
                System.out.println("c2.apply(100): " + apple);

                c2 = (weight) -> new Apple(weight);
                apple = c2.apply(200);
                System.out.println("c2.apply(200): " + apple);
                
                BiFunction<Color, Integer, Apple> c3 = Apple::new;
                apple = c3.apply(Color.GREEN,300);
                System.out.println("c3.apply(Color.GREEN,300): " + apple);

                System.out.println("giveMeFruit(orange): " + giveMeFruit("orange",100));

                System.out.println("Unsorted apples: " + A);

                List<Apple> sortedApples = newApples();
                sortedApples.sort((a1, a2) -> a1.getWeight().compareTo(a2.getWeight()));
                System.out.println("Sorted apples: " + sortedApples);

                System.out.println("\nUnsorted apples: " + A);
                sortedApples = newApples();
                sortedApples.sort(Comparator.comparing(Apple::getWeight));
                System.out.println("Sorted apples: " + sortedApples);




            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }//////////////////////////////////////////////////    //////////////////////////////////////////////////

    /**
     * 3.8.2 Composing Predicates
     */
    private static class ComposingPredicates {

        public static void main(String[] args) {
            try {
                Predicate<Apple> redApple = (a) -> Color.RED.equals(a.getColor());
                final Predicate<Apple> notRedApple = redApple.negate();

                Predicate<Apple> redAndHeavy = redApple.and(apple -> apple.getWeight() > 100);
                Predicate<Apple> redAndHeavyOrGreen = redAndHeavy.or(a -> Color.GREEN.equals(a.getColor()));

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }//////////////////////////////////////////////////    //////////////////////////////////////////////////

    private static class Letter {

        public static String addHeader(String text) {
            System.out.println("text Header: " + text);

            return "From Raoul, Mario and Alan: " + text;
        }

        public static String addFooter(String text) {
            System.out.println("text Footer: " + text);

            return text + " Kind regards";
        }

        public static String checkSpelling(String text) {
            System.out.println("text to be re[aced: " + text);

            return text.replaceAll("labda", "lambda");
        }
    }
    /**
     * 3.8.3 Composing Functions
     */
    private static class ComposingFunctions {

        public static void main(String[] args) {
            try {
                final Function<Integer, Integer> incrementBy1 = x -> x + 1;
                final Function<Integer, Integer> multiplyBy2 = x -> x * 2;

                final Function<Integer, Integer> add1ThenMultiplyBy2 = incrementBy1.andThen(multiplyBy2);
                System.out.println("add1ThenMultiplyBy2.apply(5): " + add1ThenMultiplyBy2.apply(5));

                System.out.println("multiplyBy2.compose(incrementBy1).apply(5): " + multiplyBy2
                        .compose(incrementBy1)
                        .apply(5));

                final Function<String, String> addHeader = Letter::addHeader;
                final Function<String, String> transformationPipeline = addHeader
                        .andThen(Letter::checkSpelling)
                        .andThen(Letter::addFooter);



            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }//////////////////////////////////////////////////    //////////////////////////////////////////////////
}
