package model;

import org.json.JSONObject;

// Represents an exercise with a given name, weight, number of sets, and number of reps
public class Exercise {
    private String name;
    private int reps;
    private int sets;
    private int weight;

    public Exercise(String name, int sets, int reps, int weight) {
        this.name = name;
        this.reps = reps;
        this.sets = sets;
        this.weight = weight;
    }

    public String getName() {
        return this.name;
    }

    public int getReps() {
        return this.reps;
    }

    public int getWeight() {
        return this.weight;
    }

    public int getSets() {
        return this.sets;
    }



    //EFFECTS: returns the exercise with its sets x reps and weight as a String
    public String toStringExercise() {
        return this.name + ", " + this.sets + " sets x " + this.reps + " reps, " + this.weight + "lbs";

    }

    //EFFECTS: returns the Exercise as a JSONObject
    public JSONObject toJson() {
        JSONObject j = new JSONObject();
        j.put("name", name);
        j.put("sets", sets);
        j.put("reps", reps);
        j.put("weight", weight);

        return j;

    }


}
