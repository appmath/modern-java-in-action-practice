package aziz.data;

/**
 * Created by Aziz Kadhi on 3/29/17.
 */
public class CrispApple extends Apple {
    private final int crispness;

    public CrispApple(int weight, Color color, int crispness) {
        super(weight, color);
        this.crispness = crispness;
    }
    public CrispApple(int crispness,int weight, Color color) {
        super(weight, color);
        this.crispness = crispness;
    }



    public int getCrispness() {
        return crispness;
    }

    @Override
    public String toString() {
        return "CrispApple(" + getColor() + ",  " + getWeight() + ",  " + crispness + ")";
    }
}
