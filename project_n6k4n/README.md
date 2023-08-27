# Workout Journal

## Functionality

This app is a workout Journal that will keep track of all your workouts sessions. Features include:
- Adding exercises to your workout with number of reps, number of sets, and weight lifted
- Keeps track of what date your workout was and what you did
- Keep track of body weight overtime

The application will keep track of your workouts, the weight you did, and also your bodyweight overtime so you can track your progress

## Who will use it
Anyone who works out can benefit from using the app as it will help them keep track of their workouts and progress

## Why this project interests me
This project interests me because I have been working out for a while and want a way to track all my progress. 

## User Story

- As a user, I want to be able to add exercises to my workout with the given number of reps, sets and weight
- As a user, I want to be able to input my bodyweight and track my progress overtime
- As a user, I want to be able to log my workouts and keep track of when I did each workout
- As a user, I want to see my past workouts
- As a user, I want to be able to see my past weights

- As a user, I want to be able to save my WorkoutLog workouts and weights
- As a user, I want to be able to load my WorkoutLog workouts and weights

## INSTRUCTIONS FOR GRADER

- You can generate a workout by pressing the "ADD WORKOUT" button and enter a date
- You can then add exerises to the workout in the new window and press "FINISH WORKOUT" to finish
- You can view the dates of the workout on the menu GUI. To view the full workout, press "VIEW WORKOUT" and select the 
number workout you want to view
- You can add a weight by pressing "ADD WEIGHT"
- My visual component is located in the top right (DUMBBELL)
- You can save the state of the application by pressing "SAVE WORKOUT LOG"
- You can load the state of the application by pressing "LOAD WORKOUT LOG"

## PHASE 4 TASK 2 (Example)
Sun Apr 09 22:17:50 PDT 2023 <br />
An exercise was added to the workout <br />
Sun Apr 09 22:17:51 PDT 2023 <br />
An exercise was added to the workout<br />
Sun Apr 09 22:17:52 PDT 2023<br />
A workout was added to the Workout Log<br />
Sun Apr 09 22:17:56 PDT 2023<br />
A weight was added to the workout log<br />
Sun Apr 09 22:17:59 PDT 2023<br />
A weight was added to the workout log<br />
Sun Apr 09 22:18:03 PDT 2023<br />
An exercise was added to the workout<br />
Sun Apr 09 22:18:04 PDT 2023<br />
An exercise was added to the workout<br />
Sun Apr 09 22:18:05 PDT 2023<br />
An exercise was added to the workout<br />
Sun Apr 09 22:18:06 PDT 2023<br />
A workout was added to the Workout Log<br />
Sun Apr 09 22:18:09 PDT 2023<br />
A weight was added to the workout log<br />

## PHASE 4 TASK 3
First, I would have made an abstract UI class because a lot of the UI classes
have repeated functions that could have been abstracted. Doing this would have made
the organization of the UML diagram more simple. Also, insteaed of incorporating WeightLog
into WorkoutLog, I would keep them as 2 seperate classes as the WorkoutLog class
is already doing too much. Seperating them would have made the code more simple.
Lastly, I would have changed the design of AddWorkoutUI to something similar to
AddWeightUI as the AddWeightUI is much simpler than AddWorkoutUI, and they do
similar things.