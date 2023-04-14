package soccerteam;

/**
 * This interface represents the view component in a soccer team management system. It defines
 * methods for displaying and updating the user interface, allowing users to interact with the
 * system.
 */
public interface TeamDisplay {

  /**
   * Adds features to the view for user interaction.
   *
   * @param features methods derived from the Features interface.
   */
  void addFeatures(Features features);

  /**
   * Resets the focus of the user interface.
   */
  void resetFocus();

  /**
   * Displays a message to the user.
   *
   * @param message the message to be displayed.
   */
  void displayPlayerMessage(String message);

  /**
   * Resets all input fields in the user interface.
   */
  void resetAllInputs();

  /**
   * Displays the team information along with a notification.
   *
   * @param team         the team information.
   * @param notification a message to display alongside the team information.
   */
  void displayTeam(String team, String notification);

  /**
   * Displays the starting lineup along with a notification.
   *
   * @param lineUp       the starting lineup information.
   * @param notification a message to display alongside the lineup information.
   */
  void displayLineUp(String lineUp, String notification);

  /**
   * Displays the bench players along with a notification.
   *
   * @param bench        the bench player information.
   * @param notification a message to display alongside the bench player information.
   */
  void displayBench(String bench, String notification);

  /**
   * Switches the user interface to the Add Player panel.
   */
  void switchToAddPlayerPanel();

  /**
   * Switches the user interface to the Display panel.
   */
  void switchToDisplayPanel();
}
