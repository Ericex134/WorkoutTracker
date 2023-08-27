package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WorkoutTest {
    Workout workout;
    Date date;
    Exercise e1;
    Exercise e2;
    Exercise e3;

    //MODIFIES: this
    //EFFECTS: Creates a new Date, multiple Exericises, and Workout for testing
    @BeforeEach
    public void setup(){
        date = new Date(2023, 1, 23);
        workout = new Workout(date);
        e1 = new Exercise("Squat", 4, 5, 225);
        e2 = new Exercise("Bench", 5, 6, 165);
        e3 = new Exercise("Pullup", 4, 8, 180);
    }

    //EFFECTS: tests the getters and the add function
    @Test
    public void addExerciseTest(){
        assertEquals(0, workout.getExercises().size());
        assertEquals(date, workout.getDate());
        workout.add(e1);
        assertEquals(1, workout.getExercises().size());
        assertEquals(e1, workout.getExercises().get(0));
        workout.add(e2);
        assertEquals(2, workout.getExercises().size());
        assertEquals(e2, workout.getExercises().get(1));
    }

    //TESTS the toStringWorkout function
    @Test
    public void toStringTest(){
        addExerciseTest();
        assertEquals("1/23/2023\n" +
                "Squat, 4 sets x 5 reps, 225lbs\n" +
                "Bench, 5 sets x 6 reps, 165lbs\n" , workout.toStringWorkout());
        workout.add(e3);
        assertEquals("1/23/2023\n" +
                "Squat, 4 sets x 5 reps, 225lbs\n" +
                "Bench, 5 sets x 6 reps, 165lbs\n" +
                "Pullup, 4 sets x 8 reps, 180lbs\n", workout.toStringWorkout());


    }
    @Test
    public void toStringTestNoDate(){
        addExerciseTest();
        assertEquals(
                "Squat, 4 sets x 5 reps, 225lbs\n" +
                "Bench, 5 sets x 6 reps, 165lbs\n" , workout.toStringWorkoutNoDate());
        workout.add(e3);
        assertEquals(
                "Squat, 4 sets x 5 reps, 225lbs\n" +
                "Bench, 5 sets x 6 reps, 165lbs\n" +
                "Pullup, 4 sets x 8 reps, 180lbs\n", workout.toStringWorkoutNoDate());


    }

}
