package soccerteam;

/**
 * This interface represents the features of a soccer team management system. It provides methods
 * for adding players, creating teams, displaying team information, and managing the starting
 * lineup, bench, and resetting the information.
 */
public interface Features {

  /**
   * Adds a player to the team with the provided details.
   *
   * @param firstName            The first name of the player.
   * @param lastName             The last name of the player.
   * @param dateOfBirthStr       The date of birth of the player as a string.
   * @param preferredPositionStr The preferred position of the player.
   * @param skillLevelStr        The skill level of the player.
   */
  void addPlayers(String firstName, String lastName, String dateOfBirthStr,
      Object preferredPositionStr, Object skillLevelStr);

  /**
   * Creates a new soccer team.
   */
  void createTeam();

  /**
   * Displays the list of players in the team.
   */
  void displayTeamPlayers();

  /**
   * Displays the starting lineup of the team.
   */
  void displayStartingLineup();

  /**
   * Displays the list of players on the bench.
   */
  void displayBench();

  /**
   * Resets all the information related to the team.
   */
  void resetInformation();

  /**
   * Exits the application.
   */
  void exit();

  /**
   * Starts the application with the provided view.
   *
   * @param view The view component to display the application.
   */
  void go(TeamDisplay view);
}
