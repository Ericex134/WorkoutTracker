package ui;

import model.*;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class WorkoutLogApp {
    private WorkoutLog wl;
    private Scanner scanner;
    private JsonWriter jsonWriter;
    private String source = "./data/workoutlog.json";
    private JsonReader jsonReader;

    public WorkoutLogApp() {
        wl = new WorkoutLog();
        scanner = new Scanner(System.in);
        jsonWriter = new JsonWriter(source);
        jsonReader = new JsonReader(source);
    }

    //MODIFIES: this
    //EFFECTS: runs the program, gives the options
    public void run() {
        while (true) {
            printOptions();
            try {
                int option = Integer.valueOf(scanner.nextLine());
                if (option == 7) {
                    break;
                }
                runOption(option);
            } catch (NumberFormatException e) {
                run();
                break;
            } catch (IndexOutOfBoundsException e) {
                run();
                break;
            }

        }
    }

    //EFFECTS: prints the options the user can pick from
    public void printOptions() {
        System.out.println("What would you like to do? (Enter number)");
        System.out.println("1 - Add Workout");
        System.out.println("2 - View Workouts");
        System.out.println("3 - Log Bodyweight");
        System.out.println("4 - View Weight Progress");
        System.out.println("5 - Save Workout Log");
        System.out.println("6 - Load Workout Log");
        System.out.println("7 - Quit");
    }

    //REQUIRES: i is in the range [1, 4]
    //MODIFIES: this
    //EFFECTS: runs the given option
    public void runOption(int i) {
        if (i == 1) {
            runAddWorkout();
        } else if (i == 2) {
            runViewWorkouts();
        } else if (i == 3) {
            runLogWeight();
        } else if (i == 4) {
            runViewWeights();
        } else if (i == 5) {
            saveWorkoutLog();
        } else if (i == 6) {
            loadWorkoutLog();
        } else {
            System.out.println("Invalid option try again");
        }
    }

    //MODIFIES: this
    //EFFECTS: runs view workouts option, shows all the workouts the user can view
    public void runViewWorkouts() {
        System.out.println();
        System.out.println(wl.toStringDates());
        System.out.println();
        System.out.println("Choose the workout number to view: ");
        int num = Integer.valueOf(scanner.nextLine());
        if (num > wl.getWorkouts().size()) {
            throw new IndexOutOfBoundsException();
        }
        System.out.println(wl.toStringWorkoutLog(num));
        System.out.println("");
    }

    //MODIFIES: this
    //EFFECTS: runs the add workout option, allows user to add a workout
    public void runAddWorkout() {
        Date date = getDateOfWorkout();
        Workout w = new Workout(date);
        while (true) {
            printAddWorkoutOptions();
            int option = Integer.valueOf(scanner.nextLine());
            if (option == 2) {
                break;
            }

            w.add(runAddExercise());
        }
        wl.addWorkout(w);

    }

    //EFFECTS: prints the options for the add workout option
    public void printAddWorkoutOptions() {
        System.out.println("What would you like to do (Enter number)");
        System.out.println("1 - Add Exercise");
        System.out.println("2 - Finish Workout");
    }

    //MODIFIES: this
    //EFFECTS: runs add exercise option, allows user to add an exercise (name, sets, reps, weight) to the workout
    public Exercise runAddExercise() {
        System.out.println("Enter name of exercise: ");
        String name = scanner.nextLine();
        System.out.println("Enter number of sets: ");
        int sets = Integer.valueOf(scanner.nextLine());
        System.out.println("Enter number of reps: ");
        int reps = Integer.valueOf(scanner.nextLine());
        System.out.println("Enter weight (lbs): ");
        int weight = Integer.valueOf(scanner.nextLine());


        Exercise e = new Exercise(name, sets, reps, weight);
        return new Exercise(name, sets, reps, weight);

    }

    //MODIFIES: this
    //EFFECTS: prompts user to enter the date of the workout, and returns that date
    public Date getDateOfWorkout() {
        System.out.println("Enter the month you did the workout (number 1-12): ");
        int month = Integer.valueOf(scanner.nextLine());
        System.out.println("Enter the day you did the workout: ");
        int day = Integer.valueOf(scanner.nextLine());
        System.out.println("Enter the year you did the workout: ");
        int year = Integer.valueOf(scanner.nextLine());
        return new Date(year, month, day);
    }

    //MODIFIES: this
    //EFFECTS: runs log weight option, prompts user to input their weight and date, and adds it to the weights list
    public void runLogWeight() {
        System.out.println("Enter your weight (lbs): ");
        double weight = Double.valueOf(scanner.nextLine());
        Date date = getDateOfWeight();
        Weight w = new Weight(date, weight);
        wl.addWeight(w);

    }

    //EFFECTS: prompts user to enter a date they weighed theirselves, and returns the date
    public Date getDateOfWeight() {
        System.out.println("Enter the month you weighed yourself (number 1-12): ");
        int month = Integer.valueOf(scanner.nextLine());
        System.out.println("Enter the day you you weighed yourself : ");
        int day = Integer.valueOf(scanner.nextLine());
        System.out.println("Enter the year you you weighed yourself : ");
        int year = Integer.valueOf(scanner.nextLine());
        return new Date(year, month, day);
    }

    //MODIFIES: this
    //EFFECTS: prints out all the weights in the list from most recent input to least recent input
    public void runViewWeights() {
        System.out.println();
        for (int i = wl.getWeightLog().size() - 1; i >= 0; i--) {
            System.out.println(wl.getWeightLog().getWeights().get(i).stringWeight());
        }
        System.out.println();
    }

    private void saveWorkoutLog() {
        try {
            jsonWriter.open();
            jsonWriter.write(wl);
            jsonWriter.close();
            System.out.println("Saved workout log to " + source);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + source);
        }
    }

    private void loadWorkoutLog() {
        try {
            wl = jsonReader.readWorkoutLog();
            System.out.println("Loaded workout log from " + source);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + source);
        }
    }


}
