package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WorkoutLogTest {
    Workout workout;
    Date date;
    Exercise e1;
    Exercise e2;
    Exercise e3;
    Workout workout2;
    Date date2;
    Exercise e5;
    Exercise e4;
    WorkoutLog wl;

    //MODIFIES: this
    //EFFECTS: Creates a new Date, multiple Exericises, Workouts, and a WorkoutLog for testing
    @BeforeEach
    public void setup(){
        date = new Date(2023, 1, 23);
        workout = new Workout(date);
        e1 = new Exercise("Squat", 4, 5, 225);
        e2 = new Exercise("Bench", 5, 6, 165);
        e3 = new Exercise("Pullup", 4, 8, 180);
        workout.add(e1);
        workout.add(e2);
        workout.add(e3);
        date2 = new Date(2023, 2, 26);
        workout2 = new Workout(date2);
        e4 = new Exercise("Row", 4, 12, 90);
        e5 = new Exercise("Deadlift", 4, 6, 255);
        workout2.add(e4);
        workout2.add(e5);

        wl = new WorkoutLog();
    }


    //EFFECTS: tests the addWorkout function, and the getter
    @Test
    public void addWorkoutTest(){
        assertEquals(0, wl.getWorkouts().size());
        wl.addWorkout(workout);
        assertEquals(1, wl.getWorkouts().size());
        assertEquals(workout, wl.getWorkouts().get(0));
        wl.addWorkout(workout2);
        assertEquals(2, wl.getWorkouts().size());
        assertEquals(workout2, wl.getWorkouts().get(1));
    }


    //Tests that the string is formed correctly in toStringWorkoutLog
    @Test
    public void toStringWorkoutLogTest(){
        addWorkoutTest();
        assertEquals("2/26/2023\n" +
                "Row, 4 sets x 12 reps, 90lbs\n" +
                "Deadlift, 4 sets x 6 reps, 255lbs\n" , wl.toStringWorkoutLog(2));
        assertEquals("1/23/2023\n" +
                "Squat, 4 sets x 5 reps, 225lbs\n" +
                "Bench, 5 sets x 6 reps, 165lbs\n" +
                "Pullup, 4 sets x 8 reps, 180lbs\n", wl.toStringWorkoutLog(1));


    }


    //TESTS the toStringDates function to make sure the string returned is correct
    @Test
    public void toStringDatesTest(){
        addWorkoutTest();
        assertEquals("1 - 1/23/2023\n" + "2 - 2/26/2023\n" , wl.toStringDates());


    }
}
