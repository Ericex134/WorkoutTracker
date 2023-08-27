package model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;


public class Workout {
    private ArrayList<Exercise> exercises;
    private Date date;
    private EventLog eventLog;

    public Workout(Date date) {
        this.date = date;
        exercises = new ArrayList<Exercise>();
    }

    public Date getDate() {
        return this.date;
    }

    public ArrayList<Exercise> getExercises() {
        return exercises;
    }

    // MODIFIES: This
    // EFFECTS: adds an exercise to the list of exercises
    public void add(Exercise e) {
        eventLog.getInstance().logEvent(new Event("An exercise was added to the workout"));
        exercises.add(e);
    }


    //EFFECTS: returns the workout in string form with date, name, number of sets, reps, and weight
    public String toStringWorkout() {
        String s = date.toString() + "\n";
        for (Exercise e : exercises) {
            s += e.toStringExercise() + "\n";
        }
        return s;
    }

    //EFFECTS: returns the workout in string form with name, number of sets, reps, and weight
    public String toStringWorkoutNoDate() {
        String s = "";
        for (Exercise e : exercises) {
            s += e.toStringExercise() + "\n";
        }
        return s;
    }

    //EFFECTS: returns the WorkoutLog as a JSONObject
    public JSONObject toJson() {
        JSONObject j = new JSONObject();
        j.put("Workout", exercisesToJson());
        j.put("date", date.toJson());
        return j;

    }

    //EFFECTS: returns exercises as a JSONArray
    public JSONArray exercisesToJson() {
        JSONArray ja = new JSONArray();
        for (Exercise e : exercises) {
            ja.put(e.toJson());
        }
        return ja;
    }

}
