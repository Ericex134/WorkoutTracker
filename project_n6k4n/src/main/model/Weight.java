package model;

import org.json.JSONObject;

// Represents the bodyweight of an individual, with a given weight, and the date it was logged
public class Weight {
    private Date date;
    private double weight;

    public Weight(Date date, double weight) {
        this.date = date;
        this.weight = weight;
    }

    public Date getDate() {
        return date;
    }

    public double getWeight() {
        return weight;
    }


    // EFFECTS: returns the given weight and date as a String
    public String stringWeight() {
        return weight + "lbs" + " - " + date.toString();
    }

    //EFFECTS: Returns the Weight as a JSONObject
    public JSONObject toJson() {
        JSONObject j = new JSONObject();
        j.put("Weight", weight);
        j.put("Date", date.toJson());
        return j;

    }

}
