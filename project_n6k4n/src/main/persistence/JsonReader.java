package persistence;

import model.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: reads WorkoutLog from file and returns it;
    // throws IOException if an error occurs reading data from file
    public WorkoutLog readWorkoutLog() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseWorkoutLog(jsonObject);
    }

    //EFFECTS: parses WeightLog from JSONObject
    public WeightLog parseWeightLog(JSONObject json) {
        WeightLog wl = new WeightLog();
        addWeights(wl, json);
        return wl;
    }

    //EFFECTS: parses WorkoutLog from JSONObject
    public WorkoutLog parseWorkoutLog(JSONObject jsonObject) {
        WorkoutLog wl = new WorkoutLog();
        WeightLog weights = parseWeightLog(jsonObject);
        wl.setWeights(weights);
        addWorkouts(wl, jsonObject);
        return wl;
    }

    //MODIFIES: wl
    //EFFECTS: parses weights from JSONObject and adds them to wl
    public void addWeights(WeightLog wl, JSONObject json) {
        JSONArray jsonArray = json.getJSONArray("WeightLog");
        for (Object w : jsonArray) {
            JSONObject weight = (JSONObject) w;
            addWeight(wl, weight);
        }
    }

    //MODIFIES: wl
    //EFFECTS: parses Weight from JSONObject and adds it to wl
    public void addWeight(WeightLog wl, JSONObject json) {
        Date d = parseDate(json.getJSONObject("Date"));
        double weight = json.getDouble("Weight");

        Weight w = new Weight(d, weight);
        wl.add(w);
    }


    //MODIFIES: wl
    //EFFECTS: parses workouts from JSONObject and adds them to wl
    public void addWorkouts(WorkoutLog wl, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("WorkoutLog");
        for (Object j : jsonArray) {
            JSONObject workout = (JSONObject) j;
            addWorkout(wl, workout);
        }
    }

    //MODIFIES: wl
    //EFFECTS: parses workout from JSONObject and adds them to wl
    public void addWorkout(WorkoutLog wl, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("Workout");
        Date date = parseDate(jsonObject.getJSONObject("date"));
        Workout w = new Workout(date);

        for (Object j : jsonArray) {
            JSONObject exercise = (JSONObject) j;
            addExercises(w, exercise);
        }
        wl.addWorkout(w);
    }

    //EFFECTS: parses Date from JSONObject and returns it
    public Date parseDate(JSONObject json) {
        int year = json.getInt("Year");
        int month = json.getInt("Month");
        int day = json.getInt("Day");
        return new Date(year, month, day);

    }

    //MODIFIES: w
    //EFFECTS: parses exercises from jsonObject and adds them to w
    public void addExercises(Workout w, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        int sets = jsonObject.getInt("sets");
        int reps = jsonObject.getInt("reps");
        int weight = jsonObject.getInt("weight");
        Exercise e = new Exercise(name, sets, reps, weight);
        w.add(e);
    }

}
