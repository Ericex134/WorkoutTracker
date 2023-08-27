package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExerciseTest {
    Exercise e;

    //MODIFIES: this
    //EFFECTS: Creates an exercise for testing
    @BeforeEach
    public void setup(){
        e = new Exercise("Bench", 4, 6, 175);

    }

    //EFFECTS: Tests the getters and the toStringExercise function
    @Test
    public void testStringExercise(){
        assertEquals("Bench", e.getName());
        assertEquals(4, e.getSets());
        assertEquals(6, e.getReps());
        assertEquals(175, e.getWeight());
        assertEquals("Bench, 4 sets x 6 reps, 175lbs", e.toStringExercise());
    }
}
