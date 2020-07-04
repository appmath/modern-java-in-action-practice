package aziz.util;

import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.*;


public class OptionalUtilityTest {

    @Test
    public void validNumber(){
        Optional<Integer> five = OptionalUtility.stringToInt("5");
        assertTrue(five.isPresent());
        assertEquals(5,five.get().intValue());
    }

    @Test
    public void invalidNumber(){
        Optional<Integer> five = OptionalUtility.stringToInt("S");
        assertFalse(five.isPresent());
        assertEquals(Optional.empty(),five);
    }

}