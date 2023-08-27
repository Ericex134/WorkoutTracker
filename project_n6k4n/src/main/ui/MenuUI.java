package ui;

import model.Date;
import model.Event;
import model.EventLog;
import model.Weight;
import model.WorkoutLog;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class MenuUI extends JFrame implements ActionListener, WindowListener {
    private WorkoutLog wl;
    private JsonReader jsonReader;
    private JsonWriter jsonWriter;
    private String source = "./data/workoutlog.json";
    private DateUI dateUI;
    private EventLog eventLog;

    //Header Panel Components
    private ImageIcon dumbell;
    private JLabel imageLabel;
    private JLabel workoutLogText;
    private Font titleFont;

    //Center Panel Components
    private JTextPane workoutsTextPane;
    private JTextPane weightsTextPane;
    private JScrollPane workoutsPane;
    private JScrollPane weightsPane;
    private String workoutsText;
    private String weightsText;
    private Font textFont;

    //Panels
    private JPanel mainButtonPanel;
    private JPanel topPanel;
    private JPanel centrePanel;

    //Buttons
    private JButton addWorkoutButton;
    private JButton viewWorkoutButton;
    private JButton addWeightButton;
    private JButton viewWeightsButton;
    private JButton saveWorkoutLogButton;
    private JButton loadWorkoutLogButton;


    //Constructor
    public MenuUI() {
        initializeFrame();
        initalizeButtons();
        initializeHeaderLabels();
        initializeFields();

        initializeButtonPanel(mainButtonPanel);
        initializeTopPanel(topPanel);
        initializeCentrePanel(centrePanel);

        this.add(mainButtonPanel, BorderLayout.SOUTH);
        this.add(topPanel, BorderLayout.NORTH);
        this.add(centrePanel, BorderLayout.CENTER);

        this.setVisible(true);
    }

    //MODIFIES: this
    //EFFECTS: initializes all the fields
    private void initializeFields() {
        jsonWriter = new JsonWriter(source);
        jsonReader = new JsonReader(source);
        dateUI = new DateUI();
        wl = new WorkoutLog();
        mainButtonPanel = new JPanel();
        topPanel = new JPanel();
        centrePanel = new JPanel();
    }

    //MODIFIES: this
    //EFFECTS: sets up the centre panel and panes
    private void initializeCentrePanel(JPanel panel) {
        panel.setLayout(new FlowLayout(FlowLayout.CENTER));
        panel.setBackground(new Color(154, 191, 227));
        panel.setPreferredSize(new Dimension(1280, 500));

        setupScrollPanes();

        panel.add(workoutsPane);
        panel.add(weightsPane);
    }

    //MODIFIES: this
    //EFFECTS: sets up the scroll panes for the center panels
    private void setupScrollPanes() {
        setupTextPanes();

        workoutsPane = new JScrollPane(workoutsTextPane);
        workoutsPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        workoutsPane.setPreferredSize(new Dimension(550, 455));
        weightsPane = new JScrollPane(weightsTextPane);
        weightsPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        weightsPane.setPreferredSize(new Dimension(550, 455));
    }

    //MODIFIES: this
    //EFFECTS: Sets up the text panes for the scroll panes
    private void setupTextPanes() {
        textFont = new Font("Comic Sans", Font.PLAIN, 20);
        workoutsText = "";
        weightsText = "";
        workoutsTextPane = new JTextPane();
        weightsTextPane = new JTextPane();
        workoutsTextPane.setFont(textFont);
        weightsTextPane.setFont(textFont);
        workoutsTextPane.setPreferredSize(new Dimension(550, 600));
        weightsTextPane.setPreferredSize(new Dimension(550, 600));
        workoutsTextPane.setEditable(false);
        weightsTextPane.setEditable(false);
    }

    //MODIFIES: button
    //EFFECTS: customizes the button and returns it
    public JButton customizeButton(JButton button) {
        button.setBorderPainted(true);
        button.setPreferredSize(new Dimension(200, 100));
        button.setHorizontalTextPosition(JButton.CENTER);

        return button;
    }

    //MODIFIES: this
    //EFFECTS: initializes the label for the header
    public void initializeHeaderLabels() {
        Image image = Toolkit.getDefaultToolkit().getImage("./data/Dumbell2.png");
        dumbell = new ImageIcon(image);
        imageLabel = new JLabel();
        imageLabel.setIcon(dumbell);
        workoutLogText = new JLabel("Workout Log              ");
        titleFont = new Font("Comic Sans", Font.BOLD, 50);
        workoutLogText.setFont(titleFont);
    }

    //MODIFIES: this
    //EFFECTS: customizes the JFrame
    public void initializeFrame() {
        //setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(new Dimension(1200, 720));
        setResizable(false);
        setTitle("WorkoutLog");
        setLayout(new BorderLayout());
        this.addWindowListener(this);
    }

    //MODIFIES: this
    //EFFECTS: instantiates the buttons and sets them up
    public void initalizeButtons() {
        addWorkoutButton = new JButton("Add Workout");
        viewWorkoutButton = new JButton("View Workouts");
        addWeightButton = new JButton("Add Weight");
        viewWeightsButton = new JButton("View Weights");
        saveWorkoutLogButton = new JButton("Save Workout Log");
        loadWorkoutLogButton = new JButton("Load Workout Log");
        addActionListenerToButtons();
    }

    //MODIFIES: this
    //EFFECTS: adds action listeners to all the buttons
    private void addActionListenerToButtons() {
        addWorkoutButton.addActionListener(this);
        viewWorkoutButton.addActionListener(this);
        addWeightButton.addActionListener(this);
        viewWeightsButton.addActionListener(this);
        saveWorkoutLogButton.addActionListener(this);
        loadWorkoutLogButton.addActionListener(this);
    }

    //MODIFIES: this
    //EFFECTS: initializes and sets up the button panel
    public void initializeButtonPanel(JPanel panel) {
        panel.setLayout(new GridLayout(1, 4, 0, 0));
        panel.setPreferredSize(new Dimension(1280, 100));
        panel.add(customizeButton(addWorkoutButton));
        panel.add(customizeButton(viewWorkoutButton));
        panel.add(customizeButton(addWeightButton));
        panel.add(customizeButton(viewWeightsButton));
    }

    //MODIFIES: this
    //EFFECTS: Initializes and sets up top panel
    public void initializeTopPanel(JPanel panel) {
        panel.setLayout(new FlowLayout());
        panel.setPreferredSize(new Dimension(1280, 120));
        topPanel.add(imageLabel);
        topPanel.add(workoutLogText);
        panel.add(customizeButton(saveWorkoutLogButton));
        panel.add(customizeButton(loadWorkoutLogButton));
        panel.setBackground(new Color(226, 226, 224));


    }

    //MODIFIES: this
    //EFFECTS: sets the text for the workout pane
    public void setWorkoutText(String string) {
        workoutsTextPane.setText(string);
    }


    public WorkoutLog getWl() {
        return wl;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addWorkoutButton) {
            new AddWorkoutUI(dateUI.getDateFromPane(), this);
        }
        if (e.getSource() == viewWorkoutButton) {
            if (wl.getWorkouts().size() > 0) {
                new ViewWorkoutUI(wl.getWorkouts().get(chooseWorkout()), this);
            }
        }
        if (e.getSource() == addWeightButton) {
            wl.addWeight(dateUI.getWeightFromPane());
            weightsTextPane.setText(wl.getWeightLog().toStringWeight());

        }
        if (e.getSource() == viewWeightsButton) {
            new ViewWeightUI(wl.getWeightLog().toStringWeight());
        }
        if (e.getSource() == saveWorkoutLogButton) {
            saveWorkoutLog();
        }
        if (e.getSource() == loadWorkoutLogButton) {
            loadWorkoutLog();
            workoutsTextPane.setText(wl.toStringDates());
            weightsTextPane.setText(wl.getWeightLog().toStringWeight());
        }
    }

    //EFFECTS: returns the integer number of the workout chosen
    private int chooseWorkout() {
        Integer[] numbers = new Integer[wl.getWorkouts().size()];
        for (int i = 0; i < wl.getWorkouts().size(); i++) {
            numbers[i] = i + 1;
        }

        JComboBox optionsBox = new JComboBox(numbers);
        Object[] options = {"Choose Workout Number: ", optionsBox};

        JOptionPane.showConfirmDialog(null,
                options, "Choose Workout To View", JOptionPane.DEFAULT_OPTION);
        int result = optionsBox.getSelectedIndex();
        return result;
    }

    //EFFECTS: saves workout log to source
    private void saveWorkoutLog() {
        try {
            jsonWriter.open();
            jsonWriter.write(wl);
            jsonWriter.close();
            JOptionPane.showConfirmDialog(null, "Saved workout log to " + source,
                    "Saved File", JOptionPane.DEFAULT_OPTION);
        } catch (FileNotFoundException e) {
            JOptionPane.showConfirmDialog(null, "Unable to write to file: " + source,
                    "Saved File", JOptionPane.DEFAULT_OPTION);
        }
    }

    //MODIFIES: this
    //EFFECTS: loads workout log from source
    private void loadWorkoutLog() {
        try {
            wl = jsonReader.readWorkoutLog();
            JOptionPane.showConfirmDialog(null, "Loaded workout log from " + source,
                    "Loaded File", JOptionPane.DEFAULT_OPTION);
        } catch (IOException e) {
            JOptionPane.showConfirmDialog(null, "Unable to read from file: " + source,
                    "Loaded File", JOptionPane.DEFAULT_OPTION);
        }
    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {
        for (Event event : eventLog.getInstance()) {
            System.out.println(event);
        }
    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}
