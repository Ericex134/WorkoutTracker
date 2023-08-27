package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WeightTest {
    Weight weight;
    Date date;

    //MODIFIES: this
    //EFFECTS: creates a Date and a Weight for testing
    @BeforeEach
    public void setup(){
        date = new Date(2023, 1, 23);
        weight = new Weight(date, 190);
    }


    //EFFECTS: Tests the getters and the stringWeight function
    @Test
    public void testPrintWeight(){
        assertEquals(date, weight.getDate());
        assertEquals(190, weight.getWeight());
        assertEquals("190.0lbs - 1/23/2023", weight.stringWeight());


    }

}
