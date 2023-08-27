package ui;

import model.Date;
import model.Exercise;
import model.Workout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewWorkoutUI extends AddWorkoutUI implements ActionListener {
    private Workout workout;

    //CONSTRUCTER
    public ViewWorkoutUI(Workout workout, MenuUI menuUI) {
        super(workout.getDate(), menuUI);
        this.workout = workout;
        text.setText(workout.toStringWorkoutNoDate());

    }

    //MODIFIES: this
    //EFFECTS: initializes and sets up the button panel
    @Override
    public void initializeButtonPanel() {
        buttonPanel = new JPanel();
        buttonPanel.setPreferredSize(new Dimension(500, 100));

        finishWorkoutButton = new JButton("OK");
        finishWorkoutButton.setPreferredSize(new Dimension(400, 60));
        finishWorkoutButton.setFont(new Font("Arial", Font.BOLD, 25));
        finishWorkoutButton.addActionListener(this::actionPerformed);

        buttonPanel.add(finishWorkoutButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == finishWorkoutButton) {
            this.dispose();
        }
    }
}
