package model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class WorkoutLog {
    private ArrayList<Workout> workouts;
    private WeightLog weights;
    private EventLog eventLog;

    public WorkoutLog() {
        weights = new WeightLog();
        workouts = new ArrayList<Workout>();
    }

    public ArrayList<Workout> getWorkouts() {
        
        return workouts;
    }

    public WeightLog getWeightLog() {
        return weights;
    }

    public void setWeights(WeightLog weights) {
        this.weights = weights;
    }

    //EFFECTS: returns all workout dates as a String
    public String toStringDates() {
        String s = "";
        for (int i = 0; i < workouts.size(); i++) {
            s += (i + 1) + " - " + workouts.get(i).getDate().toString() + "\n";
        }
        return s;
    }


    //EFFECTS: returns the workout in the form of a string of index i-1
    public String toStringWorkoutLog(int i) {
        return workouts.get(i - 1).toStringWorkout();
    }

    //MODIFIES: this
    //EFFECTS: adds a workout to the log
    public void addWorkout(Workout w) {
        eventLog.getInstance().logEvent(new Event("A workout was added to the Workout Log"));
        workouts.add(w);
    }

    //EFFECTS: returns the WorkoutLog as a JSONObject
    public JSONObject toJson() {
        JSONObject j = new JSONObject();
        j.put("WorkoutLog", workoutsToJson());
        j.put("WeightLog", weights.weightsToJson());

        return j;

    }

    //EFFECTS: Returns workouts as a JSONArray
    public JSONArray workoutsToJson() {
        JSONArray ja = new JSONArray();
        for (Workout w : workouts) {
            ja.put(w.toJson());
        }
        return ja;
    }

    //MODIFIES: this
    //EFFECTS: adds a weight to the weight log
    public void addWeight(Weight w) {
        weights.add(w);
    }

}
