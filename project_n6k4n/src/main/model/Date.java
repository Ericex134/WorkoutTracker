package model;

import org.json.JSONObject;

// Represents a date is the mm/dd/yyyy format
public class Date {
    private int year;
    private int month;
    private int day;

    public Date(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }



    //EFFECTS: returns a string in the MM/DD/YYYY format
    public String toString() {
        return this.month + "/" + this.day + "/" + this.year;
    }

    //EFFECTS: returns the date as a JSONObject
    public JSONObject toJson() {
        JSONObject j = new JSONObject();
        j.put("Year", year);
        j.put("Month", month);
        j.put("Day", day);
        return j;

    }

}
