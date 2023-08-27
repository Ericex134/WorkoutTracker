package persistence;

import model.*;
import org.junit.jupiter.api.Test;
import persistence.JsonReader;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;


public class JsonReaderTest {

    @Test
    public void invalidFileTest() {
        JsonReader json = new JsonReader("./data/invalidFile:name.json");
        try {
            WorkoutLog wl = json.readWorkoutLog();
            fail("IO Exception expected");
        } catch (IOException e) {
            // pass, successfully caught exception
        }
    }

    @Test
    public void emptyWorkoutLogTest() {
        JsonReader reader = new JsonReader("./data/emptyJsonReaderTest.json");
        try {
            WorkoutLog wl = reader.readWorkoutLog();
            assertEquals(0, wl.getWorkouts().size());
            assertEquals(0, wl.getWeightLog().getWeights().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }

    }

    @Test
    public void generalWorkoutLogTest() {
        try {
            JsonReader reader = new JsonReader("./data/generalJsonReaderTest.json");
            WorkoutLog wl = reader.readWorkoutLog();
            ArrayList<Workout> workouts = wl.getWorkouts();
            WeightLog weightLog = wl.getWeightLog();
            assertEquals(2, workouts.size());
            assertEquals(1, weightLog.size());

            Workout w1 = workouts.get(0);
            Workout w2 = workouts.get(1);
            Weight weight = weightLog.getWeights().get(0);
            Date date = weight.getDate();
            Date wdate = w1.getDate();

            Exercise e = w1.getExercises().get(0);
            Exercise e1 = w2.getExercises().get(0);

            assertEquals("Bench", e.getName());
            assertEquals(4, e.getSets());
            assertEquals(5, e1.getReps());
            assertEquals(170, e1.getWeight());
            assertEquals(2023, date.getYear());
            assertEquals(3, date.getMonth());
            assertEquals(15, wdate.getDay());
        } catch (IOException e) {
            fail("IO Exception Caught");
        }
    }
}