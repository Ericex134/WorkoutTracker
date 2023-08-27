package ui;

import model.Date;
import model.Exercise;
import model.Workout;
import model.WorkoutLog;

import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddWorkoutUI extends JFrame implements ActionListener {
    private Workout workout;
    private  MenuUI ui;

    private AddExericiseUI exericiseUI;

    private JButton addExerciseButton;
    protected JButton finishWorkoutButton;

    protected JPanel buttonPanel;
    private JPanel datePanel;
    private JLabel dateText;
    private Font dateFont;

    private JScrollPane pane;
    protected JTextPane text;
    private String exercisesText;
    private Font textFont;


    //CONSTRUCTER
    public AddWorkoutUI(Date date, MenuUI menuUI) {
        this.ui = menuUI;
        workout = new Workout(date);
        exericiseUI = new AddExericiseUI();
        exercisesText = "";

        initializeFrame();
        initializeButtonPanel();
        initializeDateHeader(date);
        initializePane();


        this.add(datePanel);
        this.add(pane);
        this.add(buttonPanel);
        this.setVisible(true);

    }

    //MODIFIES:this
    //EFFECTS: initializes the center pane
    private void initializePane() {
        initializeText();

        pane = new JScrollPane(text);
        pane.setPreferredSize(new Dimension(480, 570));
        pane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    }

    //MODIFIES: this
    //EFFECTS: initializes and sets up the text pane
    private void initializeText() {
        textFont = new Font("Arial", Font.PLAIN, 20);
        text = new JTextPane();
        text.setPreferredSize(new Dimension(450, 600));
        text.setEditable(false);
        text.setFont(textFont);
        StyledDocument doc = text.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);
    }

    //MODIFIES: this
    //EFFECTS: initializes and sets up the date header
    public void initializeDateHeader(Date date) {
        dateFont = new Font("Arial", Font.BOLD, 20);
        datePanel = new JPanel();
        datePanel.setPreferredSize(new Dimension(500, 30));
        dateText = new JLabel("Date of workout: " + date.toString());
        dateText.setFont(dateFont);
        datePanel.add(dateText);

    }

    //MODIFIES: this
    //EFFECTS: initializes and sets up the button panel
    public void initializeButtonPanel() {
        initializeButtons();
        buttonPanel = new JPanel();
        buttonPanel.setPreferredSize(new Dimension(500, 100));

        buttonPanel.add(customizeButton(addExerciseButton));
        buttonPanel.add(customizeButton(finishWorkoutButton));
    }

    //MODIFIES: this
    //EFFECTS: initializes and sets up all buttons and adds action listeners to them
    public void initializeButtons() {
        addExerciseButton = new JButton("Add Exercise");
        finishWorkoutButton = new JButton("Finish Workout");
        addExerciseButton.addActionListener(this);
        finishWorkoutButton.addActionListener(this);

    }

    //MODIFIES: button
    //EFFECTS: cutomizes the buttons
    public JButton customizeButton(JButton button) {
        button.setBorderPainted(true);
        button.setPreferredSize(new Dimension(200, 60));
        button.setHorizontalTextPosition(JButton.CENTER);

        return button;
    }

    //MODIFIES: this
    //EFFECTS: initializes the JFrame window
    public void initializeFrame() {
        //setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(new Dimension(500, 720));
        setResizable(false);
        setTitle("Add Workout");
        setLayout(new FlowLayout());

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addExerciseButton) {
            Exercise ex = exericiseUI.getExerciseFromPane();
            workout.add(ex);
            exercisesText += ex.toStringExercise() + "\n";
            text.setText(exercisesText);
        }
        if (e.getSource() == finishWorkoutButton) {
            ui.getWl().addWorkout(workout);
            ui.setWorkoutText(ui.getWl().toStringDates());
            this.dispose();
        }

    }
}
