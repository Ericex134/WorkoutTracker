package ui;

import model.Date;
import model.Weight;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class DateUI {
    private Object[] fields;
    private Object[] fieldsWeight;

    private Integer[] years;
    private String[] months;
    private Integer[] days;

    private JComboBox yearsBox;
    private JComboBox monthsBox;
    private  JComboBox daysBox;
    private JTextField weightsBox;


    //CONSTRUCTER
    public DateUI() {
        initializeOptions();
        initializeBoxes();

        fields = new Object[]{"Day", daysBox, "Month", monthsBox, "Year", yearsBox};
        fieldsWeight = new Object[]{"Day", daysBox, "Month", monthsBox, "Year", yearsBox,
                "Enter Weight (lbs)", weightsBox};

    }

    //MODIFIES: this
    //EFFECTS: initializes the options and creates arrays for them
    public void initializeOptions() {
        years = new Integer[]{2020, 2021, 2022, 2023, 2024, 2025, 2026};
        months = new String[]{"January", "February", "March", "April",
                "May", "June", "July", "August", "September",
                "October", "November", "December",};
        days = new Integer[31];

        for (int i = 0; i < 31; i++) {
            days[i] = i + 1;
        }

    }

    //MODIFIES: this
    //EFFECTS: initializes and sets up the combo boxes
    public void initializeBoxes() {
        yearsBox = new JComboBox(years);
        monthsBox = new JComboBox(months);
        daysBox = new JComboBox(days);
        weightsBox = new JTextField();
    }

    //EFFECTS: returns the date with the given information on the pane
    public Date getDateFromPane() {
        int result = JOptionPane.showConfirmDialog(null,
                fields, "Enter Date", JOptionPane.DEFAULT_OPTION);
        int year = 0;
        int month = 0;
        int day = 0;

        if (result == 0) {
            year = (int) yearsBox.getSelectedItem();
            day = (int) daysBox.getSelectedItem();
            String monthString = (String) monthsBox.getSelectedItem();
            month = toNumber(monthString);

        }
        return new Date(year, month, day);
    }

    //EFFECTS: returns the weight from the given information on the pane
    public Weight getWeightFromPane() {
        int result = JOptionPane.showConfirmDialog(null,
                fieldsWeight, "Enter Weight", JOptionPane.DEFAULT_OPTION);

        int year = 0;
        int month = 0;
        int day = 0;
        double weight = 0;

        if (result == 0) {
            year = (int) yearsBox.getSelectedItem();
            day = (int) daysBox.getSelectedItem();
            String monthString = (String) monthsBox.getSelectedItem();
            month = toNumber(monthString);

            try {
                weight = Double.valueOf(weightsBox.getText());
            } catch (Exception e) {
                return null;
            }
        }
        return new Weight(new Date(year, month, day), weight);
    }

    //EFFECTS: converts the months' String to an int
    public int toNumber(String string) {
        for (int i = 0; i < 12; i++) {
            if (string.equals(months[i])) {
                return i + 1;
            }
        }
        return 0;
    }
}
