package aziz.util;

import java.util.Optional;

public final class OptionalUtility {

    private OptionalUtility() { }


    public static Optional<Integer> stringToInt(String s) {
        try {
            return Optional.of(Integer.valueOf(s));
        } catch ( NumberFormatException e) {
        return Optional.empty();
        }
    }

}
