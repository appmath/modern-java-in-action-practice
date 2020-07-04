package aziz.data;



import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 */
@SuppressWarnings("UseOfSystemOutOrSystemErr")
public class Data {
    public static final File DATA_FILE = new File(Data.class.getClassLoader().getResource("data.txt").getFile());


    @NotNull
    public static FileReader newDataFileReader() throws FileNotFoundException {
        return new FileReader(Data.DATA_FILE);
    }

    public static final Apple SMALL_GREEN_APPLE = new Apple(80, Color.GREEN);
    public static final Apple LARGE_GREEN_APPLE = new Apple(155, Color.GREEN);
    public static final Apple LARGE_RED_APPLE = new Apple(120, Color.RED);
    public static final List<Apple> APPLES = Arrays.asList(SMALL_GREEN_APPLE,
                                                           LARGE_GREEN_APPLE,
                                                           LARGE_RED_APPLE);

    public static final List<CrispApple> CRISP_APPLES = Arrays.asList(
            new CrispApple(80, Color.GREEN, 1),
            new CrispApple(155, Color.GREEN, 2),
            new CrispApple(120, Color.RED, 10),
            new CrispApple(120, Color.RED, 20),
            new CrispApple(125, Color.BROWN, 15),
            new CrispApple(120, Color.RED, 5),
            new CrispApple(176, Color.RED, 5));

    //////////////////////// DISHES ARRAY AND MENU LIST /////////////////////////
    private final static Dish[] DISHES = {
            new Dish("pork", false, 800, Dish.Type.MEAT),
            new Dish("beef", false, 700, Dish.Type.MEAT),
            new Dish("chicken", false, 400, Dish.Type.MEAT),
            new Dish("french fries", true, 530, Dish.Type.OTHER),
            new Dish("rice", true, 350, Dish.Type.OTHER),
            new Dish("season fruit", true, 120, Dish.Type.OTHER),
            new Dish("pizza", true, 550, Dish.Type.OTHER),
            new Dish("prawns", false, 400, Dish.Type.FISH),
            new Dish("salmon", false, 450, Dish.Type.FISH)};
    public static final List<Dish> MENU = Arrays.asList(DISHES);
    /////////////////////////////////////////////////////////////////////////////////

    public static final String CAMBRIDGE = "Cambridge";
    public static final String MILAN = "Milan";

    private static final Trader RAOUL = new Trader("Raoul", CAMBRIDGE);
    private static final Trader MARIO = new Trader("Mario", MILAN);
    private static final Trader ALAN = new Trader("Alan", CAMBRIDGE);
    private static final Trader BRIAN = new Trader("Brian", CAMBRIDGE);

    public static final List<Transaction> TRANSACTIONS = Arrays.asList(
            new Transaction(BRIAN, 2011, 300),
            new Transaction(RAOUL, 2012, 1000),
            new Transaction(RAOUL, 2011, 400),
            new Transaction(MARIO, 2012, 710),
            new Transaction(MARIO, 2012, 700),
            new Transaction(ALAN, 2012, 950)
    );

    private static List<TransactionWithCurrency> TRANSACTIONS_WITH_CURRENCY = Arrays.asList(
            new TransactionWithCurrency(Currency.EUR, 1500.0),
            new TransactionWithCurrency(Currency.USD, 2300.0),
            new TransactionWithCurrency(Currency.GBP, 9900.0),
            new TransactionWithCurrency(Currency.EUR, 1100.0),
            new TransactionWithCurrency(Currency.JPY, 7800.0),
            new TransactionWithCurrency(Currency.CHF, 6700.0),
            new TransactionWithCurrency(Currency.EUR, 5600.0),
            new TransactionWithCurrency(Currency.USD, 4500.0),
            new TransactionWithCurrency(Currency.CHF, 3400.0),
            new TransactionWithCurrency(Currency.GBP, 3200.0),
            new TransactionWithCurrency(Currency.USD, 4600.0),
            new TransactionWithCurrency(Currency.JPY, 5700.0),
            new TransactionWithCurrency(Currency.EUR, 6800.0));

    public static final int[] NUMBER_ARRAY = {1, 2, 3, 4, 5};

    public static final Integer[] NUMBER_ARRAY_INT_OBJECT = {1, 2, 3, 4, 5};
    public static final List<Integer> NUMBERS = Arrays.asList(NUMBER_ARRAY_INT_OBJECT);
    public static final List<Integer> NUMBERS_2 = Arrays.asList(6, 7, 8, 9);

    public static final Integer[] NUMBER_ARRAY_WITH_DUPLICATES = {1, 2, 1, 2, 3, 4, 5, 3, 4, 5};
    public static final List<Integer> NUMBERS_WITH_DUPLICATES = Arrays.asList(NUMBER_ARRAY_WITH_DUPLICATES);


    public static final List<String> STRINGS = Arrays.asList("a", "bb", "ccc", "dd");

    public static final List<String> FRIENDS =
            Arrays.asList("Brian", "Nate", "Neal", "Raju", "Sara", "Scott", "Abdelaziz");

    public static final List<String> EDITORS =
            Arrays.asList("Brian", "Jackie", "John", "Mike");

    public static final List<String> COMRADES =
            Arrays.asList("Kate", "Ken", "Nick", "Paula", "Zach");


    // *****************************************************************
    public static final List<Dish> M = MENU;
    public static final List<String> F = FRIENDS;
    public static final List<Apple> A = APPLES;
    public static final List<CrispApple> A_C = CRISP_APPLES;

    public static final int[] N_A = NUMBER_ARRAY;
    public static final Integer[] N_A_O = NUMBER_ARRAY_INT_OBJECT;
    public static final List<Integer> N = NUMBERS;
    public static final List<Integer> N_2 = NUMBERS_2;
    public static final List<Integer> N_WITH_DUPS = NUMBERS_WITH_DUPLICATES;

    public static final List<Transaction> T = TRANSACTIONS;
    public static final List<TransactionWithCurrency> TWC = TRANSACTIONS_WITH_CURRENCY;

    // *****************************************************************


    public static List<Integer> newN() {
        return Arrays.asList(1, 2, 3, 4, 5);
    }
    public static List<Integer> newUnsortedN() {
        return Arrays.asList(5, 3, 2, 4, 1);
    }

    public static List<Apple> newApples() {
        return new ArrayList<>(APPLES);
    }

    public static List<Dish> newMenu() {
        return new ArrayList<>(M);
    }

    public static List<CrispApple> newCrispApples() {
        return new ArrayList<>(CRISP_APPLES);
    }

    public static void printAllNames() {
        System.out.print("\n FRIENDS: ");
        FRIENDS.stream().forEach(name -> System.out.print(name + " "));

        System.out.print("\n COMRADES: ");
        COMRADES.stream().forEach(name -> System.out.print(name + " "));

        System.out.print("\n EDITORS: ");
        EDITORS.stream().forEach(name -> System.out.print(name + " "));
    }
}
