import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import org.junit.Before;
import org.junit.Test;
import soccerteam.Player;
import soccerteam.PlayerImpl;
import soccerteam.Position;

/**
 * A test class for players All public methods and exceptions are tested. Tests for jersey numbers
 * are included in TeamImplTest.
 */
public class PlayerImplTest {
  private PlayerImpl player1;
  private PlayerImpl player2;

  /**
   * create two valid players used by testers.
   */
  @Before public void setUp() {
    player1 = new PlayerImpl("Astuka", "Soryu",
        LocalDate.of(2014, 12, 4), Position.FORWARD, 4);

    player2 = new PlayerImpl("Amber", "UB-001",
        LocalDate.of(2015, 8, 31), Position.GOALIE, 3);
  }

  /**
   * test if IllegalArgumentException successfully throws when invalid ages are given.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testAgeException() {
    Player player3 = new PlayerImpl("Oumae", "Kumiko",
        LocalDate.of(2012, 8, 21),
        Position.MIDFIELDER, 4);

    Player player4 = new PlayerImpl("Kousaka", "Reina",
        LocalDate.of(2021, 5, 15), Position.FORWARD,
        3);
  }

  /**
   * test if IllegalArgumentException successfully throws when invalid skill levels are given.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testSkillException() {
    Player player3 = new PlayerImpl("Oumae", "Kumiko",
        LocalDate.of(2012, 8, 21),
        Position.MIDFIELDER, -1);

    Player player4 = new PlayerImpl("Kousaka", "Reina",
        LocalDate.of(2021, 5, 15), Position.FORWARD,
        6);
  }

  /**
   * test if players' first name can be obtained.
   */
  @Test public void testGetFirstName() {
    assertEquals("Astuka", player1.getFirstName());
    assertEquals("Amber", player2.getFirstName());
  }

  /**
   * test if players' last name can be obtained.
   */
  @Test public void testGetLastName() {
    assertEquals("Soryu", player1.getLastName());
    assertEquals("UB-001", player2.getLastName());
  }

  /**
   * test if players' date of birth can be obtained.
   */
  @Test public void testGetDateOfBirth() {
    LocalDate birthDay1 = player1.getDateOfBirth();
    LocalDate birthDay2 = player2.getDateOfBirth();
    assertEquals("2014-12-04", birthDay1.toString());
    assertEquals("2015-08-31", birthDay2.toString());
  }

  /**
   * test if players' preferred position can be obtained.
   */
  @Test public void testGetPreferredPosition() {
    assertEquals(Position.FORWARD, player1.getPreferredPosition());
    assertEquals(Position.GOALIE, player2.getPreferredPosition());
  }

  /**
   * test when players are added to a team, if their skill level can be obtained.
   */
  @Test public void testGetSkillLevel() {
    assertEquals(4, player1.getSkillLevel());
    assertEquals(3, player2.getSkillLevel());
  }

  /**
   * test if players who are not enrolled in a team keep default jersey number.
   */
  @Test public void testGetJerseyNumber() {
    assertEquals(-1, player1.getJerseyNumber());
    assertEquals(-1, player2.getJerseyNumber());
  }
}