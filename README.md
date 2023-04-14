# CS5004-U10_Soccer_Team

## About/Overview
- The Soccer Team Manager program allows users to manage an under-10 soccer team by adding player information, creating the team, and displaying the team lineup, bench players, and the full team roster. The program ensures that players are added to the team based on their age, preferred position, and skill level.

## List of Features
1. Add player information (first name, last name, date of birth, preferred position, skill level)
2. Create the team
3. Display team roster
4. Display starting lineup
5. Display bench players
6. Reset player information
7. Exit the program

## How To Run
- Open a terminal (or command prompt) window.
- Navigate to the directory containing the jar file.
- Run the following command: 'java -jar pathOfJar'.
- Or you can click the jar file to run it, ignoring warnings.

## What arguments are needed (if any) to run the jar file
- No arguments are required to run the jar file.

## How to Use the Program
1. When the program starts, enter player information (first name, last name, date of birth, preferred position, skill level) in the input fields or combobox provided.
2. Click the "Add" button to add the player to the team roster.
3. Click the "Cancel" button to cancel the registration of a player.
4. Once all players are added (minimal number is required), click the "Create_Team" button to create the team, and "Display_Team" to display the team members. You have to make the team before displaying it.
5. Use the "Display Team", "Display_LineUp", and "Display_Bench" buttons to view the full team roster, starting lineup, and bench players, respectively.
6. Click the "Exit" button to close the program.

## Design/Model Changes
### Version 1.0
- Initial design and model implementation.

### Version 1.1
- Refactored code to improve readability and maintainability. Separated concerns by creating separate classes for Model, View, and Controller.

## Assumptions
1. All input data provided by the user is accurate.
2. A player is added to the team only if all checks are passed.
3. Team could only be generated once, you have to quit the program to re-add players and regenerate a team.
4. The minimum number of players allowed on the team is 10.
5. The maximum number of players allowed on the team is 20.

## Limitations
1. The program accepts any kinds of string as player's name.
2. The program doesn't display players' preferred position, only their position in the lineUp will display.

## Citations
- No external resources were used in the development of this project.
