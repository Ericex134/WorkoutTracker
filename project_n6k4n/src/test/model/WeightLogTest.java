package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WeightLogTest {
    WeightLog wl;
    Weight w1;
    Weight w2;
    Date d1;
    Date d2;

    @BeforeEach
    public void setup() {
        wl = new WeightLog();
        d1 = new Date(2023, 3, 32);
        d2 = new Date(2023, 4, 1);
        w1 = new Weight(d1, 190);
        w2 = new Weight(d2, 180);
    }


    @Test
    public void testConstructer() {
        assertEquals(0, wl.size());


    }

    @Test
    public void testAdd() {
        wl.add(w1);
        assertEquals(1, wl.size());
        assertEquals(w1, wl.getWeights().get(0));
        wl.add(w2);
        assertEquals(2, wl.size());
        assertEquals(w2, wl.getWeights().get(1));
        wl.add(null);
        assertEquals(2, wl.size());
    }

    @Test
    public void testToStringWeight(){
        testAdd();
        assertEquals("180.0lbs - 4/1/2023\n190.0lbs - 3/32/2023\n", wl.toStringWeight());
    }
}
