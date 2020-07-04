package aziz.data;

public enum Color {

    GREEN("green"),
    RED("red"),
    BROWN("brown"),
    ORANGE("orange"),
    NONE("none");

    private final String name;

    Color(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }


    @Override
    public String toString() {
        return name;
    }
}
