package aziz.data;

/**
 * Created by Aziz Kadhi on 3/29/17.
 */
public class Orange implements Fruit {

    private final int weight;

    public Orange(final int weight) {
        this.weight = weight;
    }

    @Override
    public Integer getWeight() {
        return weight;
    }

    @Override
    public Color getColor() {
        return Color.ORANGE;
    }

    public String toString() {
        return "Orange(" + weight + ")";
    }

}
