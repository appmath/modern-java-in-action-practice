package aziz.data;

import org.jetbrains.annotations.NotNull;

import java.util.function.Function;

public class Dish implements Comparable<Dish>{

    @NotNull
    public Function<Dish, CaloricLevel> getCaloricLevel() {
        return dish -> {
            if (dish.getCalories() <= 400) {
                return CaloricLevel.DIET;
            } else if (dish.getCalories() <= 700) {
                return CaloricLevel.NORMAL;
            } else {
                return CaloricLevel.FAT;
            }
        };
    }

    public enum CaloricLevel {DIET, NORMAL, FAT}
    public enum Type {MEAT, FISH, OTHER}

    private final String name;
    private final boolean vegetarian;
    private final int calories;
    private final Type type;

    public Dish(String name, boolean vegetarian, int calories, Type type) {
        this.name = name;
        this.vegetarian = vegetarian;
        this.calories = calories;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public boolean isVegetarian() {
        return vegetarian;
    }

    public int getCalories() {
        return calories;
    }

    public Type getType() {
        return type;
    }

    @Override
    public String toString() {
        return name + " -> " + calories;
    }

    @Override
    public int compareTo(Dish d) {
        return Integer.compare(calories, d.calories);
    }

}