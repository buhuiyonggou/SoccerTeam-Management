package soccerteam;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Objects;
import java.util.TreeSet;

/**
 * This class represents the controller of a soccer team management system. It implements the
 * Features interface and manages the interaction between the view (TeamDisplay) and the model
 * (Team).
 */
public class TeamController implements Features {
  private TeamDisplay view;
  private final Team model;
  private int playerCounter = 0;
  private boolean canCreateTeam = false;
  private boolean lineUpGenerated = false;
  private final TreeSet<String> uniquePlayerSet = new TreeSet<>();
  private boolean teamCreated = false;

  /**
   * Constructs a new TeamController with the provided view and model.
   *
   * @param view  The view component to display the application.
   * @param model The model representing the soccer team.
   */
  public TeamController(TeamDisplay view, Team model) {
    this.view = view;
    this.model = model;
    view.displayPlayerMessage("Make your U-10 soccer team: ");
  }

  /**
   * A helper function to decide if exceptions are reported.
   *
   * @param firstName            first name of a player(string).
   * @param lastName             last name of a player(string).
   * @param dateOfBirthStr       date of birth of a player(string).
   * @param preferredPositionObj preferred position of a player(Object).
   * @param skillLevelObj        skill level of a player(Object).
   * @return a boolean result indicating if input information is valid to generate players.
   */
  protected boolean isInputValid(String firstName, String lastName, String dateOfBirthStr,
      Object preferredPositionObj, Object skillLevelObj) {
    if (firstName.isEmpty() || lastName.isEmpty() || dateOfBirthStr.isEmpty()
        || preferredPositionObj == null || Objects.equals(skillLevelObj.toString(), "")) {
      view.displayPlayerMessage("Please input complete player information.");
      return false;
    }

    try {
      LocalDate.parse(dateOfBirthStr, DateTimeFormatter.ISO_LOCAL_DATE);
    } catch (DateTimeParseException e) {
      view.displayPlayerMessage("Invalid date format. Please use the format: yyyy-MM-dd.");
      return false;
    }

    boolean positionIsValid = false;
    for (Position position : Position.values()) {
      if (position.equals(preferredPositionObj)) {
        positionIsValid = true;
        break;
      }
    }
    if (!positionIsValid) {
      view.displayPlayerMessage("Please input a valid position.");
      return false;
    }

    try {
      Integer.parseInt(skillLevelObj.toString());
    } catch (IllegalArgumentException e) {
      view.displayPlayerMessage("Invalid skill level.");
      return false;
    }
    return true;
  }

  @Override public void addPlayers(String firstName, String lastName, String dateOfBirthStr,
      Object preferredPositionObj, Object skillLevelObj) {

    if (teamCreated) {
      view.displayPlayerMessage("Team has been created, you can't add more players.");
      return;
    }

    if (!isInputValid(firstName, lastName, dateOfBirthStr, preferredPositionObj, skillLevelObj)) {
      return;
    }

    LocalDate dateOfBirth = LocalDate.parse(dateOfBirthStr, DateTimeFormatter.ISO_LOCAL_DATE);
    Position preferredPosition = Position.valueOf(preferredPositionObj.toString());
    int skillLevel = Integer.parseInt(skillLevelObj.toString());

    String playerIdentifier = firstName + " " + lastName + " " + dateOfBirth;

    if (!uniquePlayerSet.contains(playerIdentifier)) {
      try {
        model.addPlayer(firstName, lastName, dateOfBirth, preferredPosition, skillLevel);
        uniquePlayerSet.add(playerIdentifier);
        playerCounter++;
        view.displayPlayerMessage(
            "Player added: " + firstName + " " + lastName + "\n" + playerCounter
                + " Players added");
      } catch (IllegalArgumentException e) {
        view.displayPlayerMessage("Add failed! " + e.getMessage());
      }
    } else {
      view.displayPlayerMessage(
          "This player has already been added. Please confirm and try again.");
    }
    view.resetAllInputs();
  }

  @Override public void createTeam() {
    if (teamCreated) {
      view.displayTeam("", "You can't regenerate the team.");
      return;
    }

    try {
      model.makeTeam();
      view.displayTeam("", "The Team Created");
      canCreateTeam = true;
      teamCreated = true;
    } catch (IllegalArgumentException e) {
      view.displayTeam("", e.getMessage());
      teamCreated = false;
    }
  }

  @Override public void displayTeamPlayers() {
    if (canCreateTeam) {
      String teamInfo = model.getTeam();
      String teamNote = "Team: ";
      view.displayTeam(teamInfo, teamNote);
    } else {
      view.displayTeam("", "Please create a team before trying to display it.");
    }
  }

  @Override public void displayStartingLineup() {
    if (canCreateTeam) {
      String lineUpInfo = model.getLineUp();
      String lineUpNote = "LineUp: ";
      view.displayLineUp(lineUpInfo, lineUpNote);
      lineUpGenerated = true;
    } else {
      view.displayLineUp("",
          "Please create a team before trying to display the starting lineup.");
    }
  }

  @Override public void displayBench() {
    if (canCreateTeam && lineUpGenerated) {
      String benchInfo = model.getBench();
      String benchNote = "Bench: ";
      view.displayBench(benchInfo, benchNote);
    } else if (!canCreateTeam) {
      view.displayBench("",
          "Please create a team before trying to display the bench.");
    } else {
      view.displayBench("",
          "Please generate a lineup before trying to display the bench.");
    }
  }

  @Override public void resetInformation() {
    view.resetAllInputs();
    view.displayPlayerMessage("Player Registration Cancelled.");
  }

  @Override public void exit() {
    System.exit(0);
  }

  @Override public void go(TeamDisplay v) {
    view = v;
    view.addFeatures(this);
  }
}
