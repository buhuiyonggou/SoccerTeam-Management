package soccerteam;

import java.time.LocalDate;

/**
 * This interface represents the operations offered by players. Objects created by this model are
 * valid to being used in the soccer team.
 */
public interface Player {

  /**
   * A getter to get a player's first name.
   * @return a string representing player's first name.
   */
  String getFirstName();

  /**
   * A getter to get a player's last name.
   * @return a string representing player's last name.
   */
  String getLastName();

  /**
   * A getter to get a player's birth date.
   * @return a birthday with type LocalDate。
   */
  LocalDate getDateOfBirth();

  /**
   * A getter to get a player's preferred position,
   * which may be not identical to his/her position in the team.
   * @return an enum type position.
   */
  Position getPreferredPosition();

  /**
   * A getter to get a player's skill level, ranging from 1 to 5.
   * @return an integer representing player's skill level.
   */
  int getSkillLevel();

  /**
   * A getter to get a player's jersey number,
   * which is applicable only after the team is created.
   * @return an integer representing player's jersey number in a team.
   */
  int getJerseyNumber();
}

