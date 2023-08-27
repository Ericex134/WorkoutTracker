package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DateTest {
    Date date;

    //MODIFIES: this
    //EFFECTS: Creates a date for testing
    @BeforeEach
    public void setup(){
        date = new Date(2023, 1, 23);

    }

    //EFFECTS: tests the getters, and the toString function
    @Test
    public void toStringTest(){
        assertEquals(2023, date.getYear());
        assertEquals(1, date.getMonth());
        assertEquals(23, date.getDay());
        assertEquals("1/23/2023", date.toString());
    }

}
