package persistence;

import model.*;
import org.junit.jupiter.api.Test;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;


public class JsonWriterTest {

    @Test
    public void writeInvalidFileTest() {
        try {
            JsonWriter json = new JsonWriter("./data/invalid\0File:name.json");
            json.open();
            fail("IO Exception expected");
        } catch (IOException e) {
            // pass, successfully caught exception
        }
    }

    @Test
    public void writeEmptyWorkoutLogTest() {
        WorkoutLog wl = new WorkoutLog();
        JsonWriter json = new JsonWriter("./data/emptyJsonWriterTest.json");
        try {
            json.open();
        } catch (FileNotFoundException e) {
            fail("FileNotFound Exception Caught");

        }
        json.write(wl);
        json.close();

        JsonReader jsonReader = new JsonReader("./data/emptyJsonWriterTest.json");
        try {
            wl = jsonReader.readWorkoutLog();
        } catch (IOException e) {
            fail("IO Exception Caught");
        }

        assertEquals(0, wl.getWorkouts().size());
        assertEquals(0, wl.getWeightLog().getWeights().size());

    }

    @Test
    public void writeGeneralWorkoutLogTest() {
        try {
            WorkoutLog wl = new WorkoutLog();
            Workout w1 = new Workout(new Date(2023, 4, 15));
            Workout w2 = new Workout(new Date(2023, 5, 14));
            Exercise e = new Exercise("Bench", 4, 6, 190);
            Exercise e1 = new Exercise("pull", 2, 5, 170);
            Weight weight = new Weight(new Date(2023, 3,4), 190.0);
            w1.add(e);
            w2.add(e1);
            wl.addWorkout(w1);
            wl.addWorkout(w2);
            wl.addWeight(weight);

            JsonWriter json = new JsonWriter("./data/generalJsonWriterTest.json");
            json.open();
            json.write(wl);
            json.close();

            JsonReader reader = new JsonReader("./data/generalJsonWriterTest.json");
            wl = reader.readWorkoutLog();
            ArrayList<Workout> workouts = wl.getWorkouts();
            WeightLog weightLog = wl.getWeightLog();
            assertEquals(2, workouts.size());
            assertEquals(1, weightLog.size());

            w1 = workouts.get(0);
            w2 = workouts.get(1);
            weight = weightLog.getWeights().get(0);
            Date date = weight.getDate();
            Date wdate = w1.getDate();

            e = w1.getExercises().get(0);
            e1 = w2.getExercises().get(0);

            assertEquals("Bench", e.getName());
            assertEquals(4, e.getSets());
            assertEquals(5, e1.getReps());
            assertEquals(170, e1.getWeight());
            assertEquals(2023, date.getYear());
            assertEquals(3, date.getMonth());
            assertEquals(15, wdate.getDay());

        } catch (IOException e) {
            fail("Unexpected Exception Caught");
        }
    }
}