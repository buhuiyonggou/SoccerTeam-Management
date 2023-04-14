import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import org.junit.Before;
import org.junit.Test;
import soccerteam.Position;
import soccerteam.TeamImpl;

/**
 * A test class for teams All public methods and exceptions are tested.
 * Tests for jersey numbers are included.
 */
public class TeamImplTest {
  private TeamImpl newTeam1;
  private TeamImpl newTeam2;

  /**
   * a constructor with random players. 19 players are added.
   */
  @Before public void setUp1() {
    newTeam1 = new TeamImpl();
    long randomGenerator = 1104944;
    Random random = new Random(randomGenerator);

    int currentYear = LocalDate.now().getYear();
    int minYear = currentYear - 10;
    int maxYear = currentYear - 6;

    for (int i = 1; i <= 19; i++) {
      Position[] positions = Position.values();
      Position randomPosition = positions[random.nextInt(positions.length)];
      int randomSkillLevel = random.nextInt(5) + 1;

      int randomYear = minYear + random.nextInt(maxYear - minYear);
      int randomMonth = random.nextInt(12) + 1;
      int randomDay = random.nextInt(28) + 1;

      LocalDate randomDateOfBirth = LocalDate.of(randomYear, randomMonth, randomDay);

      newTeam1.addPlayer("Player", Integer.toString(i), randomDateOfBirth, randomPosition,
          randomSkillLevel);
    }
  }

  /**
   * constructor with certain players 8 players are added. used for testing exception.
   */
  @Before public void setUp2() {
    newTeam2 = new TeamImpl();
    newTeam2.addPlayer("Aohn", "Doe",
        LocalDate.of(2015, 1, 1), Position.GOALIE, 5);
    newTeam2.addPlayer("Bane", "Doe",
        LocalDate.of(2014, 1, 15), Position.DEFENDER, 4);
    newTeam2.addPlayer("Cim", "Smith",
        LocalDate.of(2017, 2, 1), Position.DEFENDER, 3);
    newTeam2.addPlayer("Dill", "Smith",
        LocalDate.of(2016, 7, 20), Position.MIDFIELDER, 2);
    newTeam2.addPlayer("Eack", "Brown",
        LocalDate.of(2014, 9, 10), Position.MIDFIELDER, 5);
    newTeam2.addPlayer("Fenny", "Brown",
        LocalDate.of(2013, 11, 5), Position.MIDFIELDER, 4);
    newTeam2.addPlayer("Goe", "Johnson",
        LocalDate.of(2015, 3, 15), Position.FORWARD, 3);
    newTeam2.addPlayer("Hessica", "Johnson",
        LocalDate.of(2016, 4, 4), Position.DEFENDER, 2);
  }

  /**
   * test if players can be added successfully.
   */
  @Test public void testAddPlayer() {
    String firstName = "Frank";
    String lastName = "Ali";
    LocalDate dateOfBirth = LocalDate.of(2013, 11, 11);
    Position position = Position.DEFENDER;
    int skillLevel = 1;

    newTeam1.addPlayer(firstName, lastName, dateOfBirth, position, skillLevel);
    String teamInfo = newTeam1.getTeam();
    assertTrue(teamInfo.contains(firstName) && teamInfo.contains(lastName));
    //Additional test for existed players
    assertTrue(teamInfo.contains("Frank") && teamInfo.contains("Ali"));
  }

  /**
   * test if players with the same name but different date of birth can be added.
   */
  @Test public void testAddUniquePlayer1() {
    newTeam1.addPlayer("Frank", "Ali",
        LocalDate.of(2013, 11, 11), Position.DEFENDER, 4);
    newTeam1.addPlayer("Frank", "Ali",
        LocalDate.of(2014, 02, 02), Position.FORWARD, 5);
    String teamInfo = newTeam1.getTeam();
    assertTrue(teamInfo.contains("Frank, Ali, 2013-11-11, Skill Level: 4"));
    assertTrue(teamInfo.contains("Frank, Ali, 2014-02-02, Skill Level: 5"));
  }

  /**
   * test if players with same name and birthday are refused.
   */
  @Test public void testAddUniquePlayer2() {
    newTeam1.addPlayer("Frank", "Ali",
        LocalDate.of(2014, 11, 11), Position.DEFENDER, 4);
    newTeam1.addPlayer("Frank", "Ali",
        LocalDate.of(2014, 11, 11), Position.FORWARD, 5);
    newTeam1.addPlayer("Frank", "Ali",
        LocalDate.of(2014, 11, 11), Position.FORWARD, 3);
    String teamInfo = newTeam1.getTeam();
    assertFalse(teamInfo.contains("Frank, Ali, 2014-11-11, Skill Level: 5"));
    assertFalse(teamInfo.contains("Frank, Ali, 2014-11-11, Skill Level: 2"));
  }

  /**
   * test when the team is full, can new player replace the one with the lowest skill level.
   */
  @Test public void testDropPlayer() {
    // make Frank at the first of newTeam with lowest skillLevel
    newTeam1.addPlayer("Frank", "0",
        LocalDate.of(2012, 7, 31), Position.GOALIE, 1);
    // add 1 more player to the maximum team, Frank, 0 is dropped
    newTeam1.addPlayer("Extra", "Player",
        LocalDate.of(2013, 6, 1), Position.FORWARD, 4);

    String teamInfo = newTeam1.getTeam();
    assertFalse(teamInfo.contains("Frank") && teamInfo.contains("Ali"));
  }

  /**
   * test if a qualified team can be created.
   */
  @Test public void testMakeTeam1() {
    newTeam1.makeTeam();
  }

  /**
   * test if a unqualified team is refused to be created.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testMakeTeam2() {
    newTeam2.makeTeam();
  }

  /**
   * Test if jersey Numbers are successfully assigned as well.
   */
  @Test public void testGetTeam() {
    newTeam1.addPlayer("Extra", "Player",
        LocalDate.of(2013, 6, 1), Position.FORWARD, 4);
    newTeam1.makeTeam();
    assertTrue(newTeam1.getTeam().contains("Jersey Number: 20"));
    assertTrue(newTeam1.getTeam().contains("Jersey Number: 1"));
    assertTrue(newTeam1.getTeam().contains("Jersey Number: 7"));
    assertTrue(newTeam1.getTeam().contains("Jersey Number: 9"));
    assertTrue(newTeam1.getTeam().contains("Jersey Number: 15"));
  }

  /**
   * Test if jersey numbers are assigned randomly and uniquely.
   */
  @Test public void testJerseyNumbers() {
    newTeam2.addPlayer("Iames", "Lee",
        LocalDate.of(2017, 3, 25), Position.MIDFIELDER, 1);
    newTeam2.addPlayer("Julie", "Lee",
        LocalDate.of(2016, 8, 11), Position.FORWARD, 5);
    newTeam2.makeTeam();
    String teamInfo = newTeam2.getTeam();
    String[] lines = teamInfo.split("\n");

    Set<Integer> jerseyNumbers = new HashSet<>();
    for (String line : lines) {
      if (line.contains("Jersey Number:")) {
        int jerseyNumber = Integer.parseInt(line.split("Jersey Number:")[1].trim());
        assertFalse("Duplicate jersey number: " + jerseyNumber,
            jerseyNumbers.contains(jerseyNumber));
        jerseyNumbers.add(jerseyNumber);
      }
    }
  }

  /**
   * Test if players are alphabetically ordered by last name in the candidate.
   */
  @Test public void getTeamAlphabetic() {
    newTeam2.addPlayer("Iames", "Lee",
        LocalDate.of(2017, 3, 25), Position.MIDFIELDER, 1);
    newTeam2.addPlayer("Julie", "Lee",
        LocalDate.of(2016, 8, 11), Position.FORWARD, 5);
    newTeam2.makeTeam();
    String teamInfo = newTeam2.getTeam();
    String[] lines = teamInfo.split("\n");
    String lastName1 = lines[1].split(",")[1].trim();
    String lastName2 = lines[3].split(",")[1].trim();
    String lastName3 = lines[5].split(",")[1].trim();
    assertEquals("Brown", lastName1);
    assertEquals("Doe", lastName2);
    assertEquals("Johnson", lastName3);
  }

  /**
   * test if a lineUp contains players with high level skills and preferred positions. If not valid
   * slots for their preferred positions, actual position in the lineUP could be changed.
   */
  @Test public void testGetLineUp() {
    newTeam2.addPlayer("Iames", "Lee",
        LocalDate.of(2017, 3, 25), Position.MIDFIELDER, 1);
    newTeam2.addPlayer("Julie", "Lee",
        LocalDate.of(2016, 8, 11), Position.FORWARD, 5);
    newTeam2.makeTeam();
    String lineUp = newTeam2.getLineUp();

    int goalieIndex = lineUp.indexOf("GOALIE");
    int defenderIndex = lineUp.indexOf("DEFENDER");
    int midfielderIndex = lineUp.indexOf("MIDFIELDER");
    int forwardIndex = lineUp.indexOf("FORWARD");
    assertTrue(goalieIndex < defenderIndex && defenderIndex < midfielderIndex
        && midfielderIndex < forwardIndex);

    int getPlayerIndex1 = lineUp.indexOf("Aohn");
    int getPlayerIndex2 = lineUp.indexOf("Bane");
    int getPlayerIndex3 = lineUp.indexOf("Eack");
    assertTrue(getPlayerIndex1 < getPlayerIndex2 && getPlayerIndex2 < getPlayerIndex3);
  }

  /**
   * test if players in the bench are inclued in the candidate, but not overlap with those in
   * lineUP.
   */
  @Test public void testGetBench() {
    newTeam2.addPlayer("Iames", "Lee",
        LocalDate.of(2017, 3, 25), Position.MIDFIELDER, 1);
    newTeam2.addPlayer("Julie", "Lee",
        LocalDate.of(2016, 8, 11), Position.FORWARD, 5);
    newTeam2.makeTeam();
    String bench = newTeam2.getBench();
    assertTrue(bench.contains("Goe, Johnson, 2015-03-15, Skill Level: 3"));
    assertTrue(bench.contains("Hessica, Johnson, 2016-04-04, Skill Level: 2"));
    assertTrue(bench.contains("Iames, Lee, 2017-03-25, Skill Level: 1"));

    int getPlayerIndex1 = bench.indexOf("Goe");
    int getPlayerIndex2 = bench.indexOf("Hessica");
    int getPlayerIndex3 = bench.indexOf("Iames");
    assertTrue(getPlayerIndex1 < getPlayerIndex2 && getPlayerIndex2 < getPlayerIndex3);
  }
}