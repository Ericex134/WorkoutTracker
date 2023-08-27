package model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class WeightLog {
    private ArrayList<Weight> weights;
    private EventLog eventLog;

    public WeightLog() {
        weights = new ArrayList<Weight>();
    }


    //MODIFIES: this
    //EFFECTS: adds weight to the WeightLog
    public void add(Weight w) {
        if (w != null) {
            weights.add(w);
        }
        eventLog.getInstance().logEvent(new Event("A weight was added to the workout log"));
    }

    //MODIFIES: this
    //EFFECTS: returns the amount of weights logged
    public int size() {
        return weights.size();
    }

    public ArrayList<Weight> getWeights() {
        return weights;
    }

    //EFFECTS: returns WeightLog as a JSONArray
    public JSONArray weightsToJson() {
        JSONArray ja = new JSONArray();
        for (Weight w : weights) {
            ja.put(w.toJson());
        }
        return ja;
    }

    public String toStringWeight() {
        String s = "";
        for (int i = weights.size() - 1; i >= 0; i--) {
            s += weights.get(i).stringWeight() + "\n";
        }
        return s;
    }



}
