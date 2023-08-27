package ui;

import model.Exercise;

import javax.swing.*;

public class AddExericiseUI {
    private Object[] fields;
    private Integer[] reps;
    private Integer[] sets;
    private Integer[] weights;

    private JTextField exName;
    private JComboBox repsBox;
    private JComboBox setsBox;
    private JComboBox weightsBox;

    //CONSTRUCTER
    public AddExericiseUI() {
        initializeOptions();
        initializeBoxes();

        fields = new Object[]{"Name:", exName, "Sets:", setsBox, "Reps:", repsBox,
                "Weight (lbs):", weightsBox};
    }

    //MODIFIES: this
    //EFFECTS: sets up and initializes the combo boxes
    private void initializeBoxes() {
        exName = new JTextField();
        exName.setEditable(true);
        repsBox = new JComboBox(reps);
        setsBox = new JComboBox(sets);
        weightsBox = new JComboBox(weights);
    }

    //MODIFIES: this
    //EFFECTS: initializes and sets up the options for the combo boxes
    public void initializeOptions() {
        initializeReps();
        initializeSets();
        initializeWeights();
    }

    //MODIFIES:this
    //EFFECTS: initializes and creates array of weights from 0-350 in increments of 5
    private void initializeWeights() {
        weights = new Integer[70];
        for (int i = 0; i < 70; i++) {
            weights[i] = (i + 1) * 5;
        }
    }

    //MODIFIES: this
    //EFFECTS: initializes and creates an array of sets from 0-10
    private void initializeSets() {
        sets = new Integer[10];
        for (int i = 0; i < 10; i++) {
            sets[i] = i + 1;
        }
    }

    //MODIFIES: this
    //EFFECTS: initializes and creates an array of reps from 0-30
    private void initializeReps() {
        reps = new Integer[30];
        for (int i = 0; i < 30; i++) {
            reps[i] = i + 1;
        }
    }

    //EFFECTS: returns the data from the pane into a exercise
    public Exercise getExerciseFromPane() {
        int result = JOptionPane.showConfirmDialog(null,
                fields, "Enter Exercise", JOptionPane.DEFAULT_OPTION);

        String name = "";
        int reps = 0;
        int sets = 0;
        int weight = 0;
        if (result == 0) {
            sets = (int) setsBox.getSelectedItem();
            reps = (int) repsBox.getSelectedItem();
            weight = (int) weightsBox.getSelectedItem();
            name = exName.getText();
        }
        return new Exercise(name, sets, reps, weight);
    }
}
