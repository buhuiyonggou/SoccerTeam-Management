package soccerteam;

import java.time.LocalDate;
import java.time.Period;

/**
 * This class is used to create players of a soccer team. Players are viewed as identical if they
 * have the same firstName, lastName, and dateOfBirth. Constants are included.
 */
public class PlayerImpl implements Player {
  private static final int MAXIMUM_NUMBER = 20;
  private static final int MINIMUM_NUMBER = 1;
  private static final int MAXIMUM_AGE = 10;
  private static final int MINIMUM_AGE = 6;
  private static final int MAXIMUM_SKILL_LEVEL = 5;
  private static final int MINIMUM_SKILL_LEVEL = 1;
  private final String firstName;
  private final String lastName;
  private final LocalDate dateOfBirth;
  private final Position preferredPosition;
  private final int skillLevel;
  private int jerseyNumber = -1; // -1 as default

  /**
   * A constructor to create players. If given information is not valid, report exceptions.
   * Fields: skillLevel, jerseyNumber,
   * and positionInTeam are given when a player is enrolled teams.
   * @param firstName         a string representing a player's first name.
   * @param lastName          a string representing a player's last name.
   * @param dateOfBirth       a LocalDate representing a player's birthdate.
   * @param preferredPosition an enum type position.
   * @param skillLevel        an integer representing skill level that assigned by the user.
   * @throws IllegalStateException some fields are invalid when being assigned.
   */
  public PlayerImpl(String firstName, String lastName, LocalDate dateOfBirth,
      Position preferredPosition, int skillLevel) throws IllegalArgumentException {
    this.firstName = firstName;
    this.lastName = lastName;
    int age = Period.between(dateOfBirth, LocalDate.now()).getYears();
    if (age < MINIMUM_AGE || age >= MAXIMUM_AGE) {
      throw new IllegalArgumentException(
          "The team only accept player older than 6 and under 10 years old.");
    }
    this.dateOfBirth = dateOfBirth;
    this.preferredPosition = preferredPosition;

    if (skillLevel < MINIMUM_SKILL_LEVEL || skillLevel > MAXIMUM_SKILL_LEVEL) {
      throw new IllegalArgumentException("Skill level must be between 1 and 5.");
    }
    this.skillLevel = skillLevel;
  }

  @Override public String getFirstName() {
    return this.firstName;
  }

  @Override public String getLastName() {
    return this.lastName;
  }

  @Override public LocalDate getDateOfBirth() {
    return this.dateOfBirth;
  }

  @Override public Position getPreferredPosition() {
    return this.preferredPosition;
  }

  @Override public int getSkillLevel() {
    return this.skillLevel;
  }

  @Override public int getJerseyNumber() {
    return this.jerseyNumber;
  }

  /**
   * A helper function that assign the jersey number to a player. This function can only be called
   * when the team is created.
   *
   * @param jerseyNumber an integer that representing
   * @throws IllegalStateException jerseyNumber is not in the range 1 to 20.
   */
  protected void assignJerseyNumber(int jerseyNumber) throws IllegalArgumentException {
    if (jerseyNumber < MINIMUM_NUMBER || jerseyNumber > MAXIMUM_NUMBER) {
      throw new IllegalArgumentException("The jersey number should be chosen from 1 to 20.");
    }
    this.jerseyNumber = jerseyNumber;
  }

  @Override public String toString() {
    return getFirstName() + ", " + getLastName() + ", " + getDateOfBirth() + ", " + "Skill Level: "
        + getSkillLevel() + ", " + "Jersey Number: " + getJerseyNumber() + "\n";
  }
}
