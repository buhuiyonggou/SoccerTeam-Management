package soccerteam;

import java.time.LocalDate;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * This interface represents the operations offered by teams. Users add players and make team from
 * these functions. Getters return toString for objects in TreeSet/TreeMap.
 */
public interface Team {
  public void addPlayer(String firstName, String lastName, LocalDate dateOfBirth,
      Position preferredPosition, int skillLevel) throws IllegalArgumentException;

  /**
   * create a team, containing all players added in. In this team, jersey numbers are assigned but
   * positionInTeam is not determined.
   *
   * @throws IllegalArgumentException if size of team is less than 9.
   */
  public void makeTeam() throws IllegalArgumentException;

  /**
   * a getter provides a string displaying players' information in the team.
   *
   * @return a string displaying players' information in the team.
   */
  public String getTeam();

  /**
   * a getter provides a string displaying players in the lineUP. a private function makeLine is
   * included in this function. players are reordered by skill levels, then assign to their
   * preferred positions. If preferred position is full, add the player to a wait list. If lineUp
   * still has slots for different positions, adding players from the wait list, regardless their
   * preferred position.
   *
   * @return a string representing players in each enum position.
   */
  public String getLineUp();

  /**
   * provides a string displaying players in the bench. a private function makeBench is included in
   * this function. Players who are already in the lineUp will not be in the bench.
   *
   * @return a string displaying players' information in the bench.
   */
  public String getBench();
}
