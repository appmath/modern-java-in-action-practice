package aziz.data;

/**
 * Created by Aziz Kadhi on 5/10/16.
 */
public class Apple implements Fruit, Comparable<Apple> {
    private Color color = Color.NONE;
    private int weight = 0;

    public Apple(int weight, Color color) {
        this.weight = weight;
        this.color = color;
    }
    public Apple(Color color, int weight) {
        this.weight = weight;
        this.color = color;
    }

    public Apple() {}

    public Apple(Color color) {
        this.color = color;
        this.weight = -1;
    }

    public Apple(int weight) {
        this.weight = weight;
    }

    public Apple(String s) {
    }

    @Override
    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    @Override
    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public String toString() {
        return "Apple(" + color + ": " + weight + ")";
    }

    @Override
    public int compareTo(Apple o) {
        return Integer.valueOf(weight).compareTo(Integer.valueOf(o.weight));
    }
}