package soccerteam;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * This class is used to create three categories of soccer teams. team candidate is the big name
 * list of all players added, in which jersey numbers are given. players in team lineUP will be
 * included in certain positions. Constants are included.
 */
public class TeamImpl implements Team {
  private static final int MAXIMUM_NUMBER = 20;
  private static final int MINIMUM_NUMBER = 10;
  private static final int GOALIE_SLOTS = 1;
  private static final int DEFENDER_SLOTS = 2;
  private static final int MIDFIELDER_SLOTS = 3;
  private static final int FORWARD_SLOTS = 1;
  private static final Comparator<Player> PLAYER_COMPARATOR =
      Comparator.comparing(Player::getLastName)
          .thenComparing(Player::getFirstName)
          .thenComparing(Player::getDateOfBirth);
  private final TreeSet<Player> candidate;
  private final Map<Position, TreeSet<Player>> lineUp;
  private final TreeSet<Player> bench;
  private boolean teamCreated = false;

  /**
   * a constructor to create required teams. team candidate uses TreeSet as players are ordered by
   * names and birthday. team lineUP uses TreeMap as corresponding positions are taken as keys, and
   * collections of players are values. team bench uses TreeSet as players who are not in lineUP are
   * ordered by name.
   */
  public TeamImpl() {
    this.candidate = new TreeSet<>(
        Comparator.comparing(Player::getLastName).thenComparing(Player::getFirstName)
            .thenComparing(Player::getDateOfBirth));
    this.lineUp = new TreeMap<>();
    this.bench = new TreeSet<>(
        Comparator.comparing(Player::getLastName).thenComparing(Player::getFirstName)
            .thenComparing(Player::getDateOfBirth));
  }

  @Override public void addPlayer(String firstName, String lastName, LocalDate dateOfBirth,
      Position preferredPosition, int skillLevel) {
    Player newPlayer = new PlayerImpl(firstName, lastName, dateOfBirth, preferredPosition,
        skillLevel);
    candidate.add(newPlayer);
    if (candidate.size() > MAXIMUM_NUMBER) {
      dropPlayer();
    }
  }

  /**
   * a helper function applied by addPlayer. if there are more than 20 players in the team， the one
   * with the lowest skill level is dropped.
   */
  protected void dropPlayer() {
    Player lowestSkillPlayer = null;
    for (Player player : candidate) {
      if (lowestSkillPlayer == null || player.getSkillLevel() < lowestSkillPlayer.getSkillLevel()) {
        lowestSkillPlayer = player;
      }
    }
    if (lowestSkillPlayer != null) {
      candidate.remove(lowestSkillPlayer);
    }
  }

  @Override public void makeTeam() throws IllegalArgumentException {
    if (candidate.size() < MINIMUM_NUMBER) {
      throw new IllegalArgumentException(
          "Please add more members to the team. Now we have " + candidate.size() + " players");
    }
    ArrayList<Integer> jerseyNumberPool = new ArrayList<>();
    for (int i = 0; i < MAXIMUM_NUMBER; i++) {
      jerseyNumberPool.add(i + 1);
    }
    Collections.shuffle(jerseyNumberPool);

    Iterator<Player> playerIterator = candidate.iterator();
    int jerseyNumberIndex = 0;
    while (playerIterator.hasNext()) {
      Player player = playerIterator.next();
      int assignedNumber = jerseyNumberPool.get(jerseyNumberIndex);
      ((PlayerImpl) player).assignJerseyNumber(assignedNumber);
      jerseyNumberIndex++;
    }
    teamCreated = true;
  }

  @Override public String getTeam() {
    if (!teamCreated) {
      makeTeam();
      teamCreated = true;
    }

    StringBuilder teamInfo = new StringBuilder();
    teamInfo.append("The current team contains:\n");
    for (Player player : candidate) {
      teamInfo.append(player.toString());
    }
    return teamInfo.toString();
  }

  /**
   * a helper function applied by getLineUp. players are reordered by skill levels, then assign to
   * their preferred positions. If preferred position is full, add the player to a wait list. If
   * lineUp still has slots for different positions, adding players from the wait list, regardless
   * their preferred position.
   */
  private void makeLineUp() {
    // create values of TreeMap lineUP
    lineUp.put(Position.GOALIE, new TreeSet<>(PLAYER_COMPARATOR));
    lineUp.put(Position.DEFENDER, new TreeSet<>(PLAYER_COMPARATOR));
    lineUp.put(Position.MIDFIELDER, new TreeSet<>(PLAYER_COMPARATOR));
    lineUp.put(Position.FORWARD, new TreeSet<>(PLAYER_COMPARATOR));

    // create a wait list, and reorder candidate by skillLevel
    ArrayList<Player> waitList = new ArrayList<>();
    TreeSet<Player> candidateBySkill = new TreeSet<>(
        Comparator.comparingInt(Player::getSkillLevel).reversed()
            .thenComparing(PLAYER_COMPARATOR));
    candidateBySkill.addAll(candidate);

    // Iterate through waitList and add players to their preferred positions
    // if there is a slot available
    for (Player player : candidateBySkill) {
      Position preferredPosition = player.getPreferredPosition();
      TreeSet<Player> group = lineUp.get(preferredPosition);
      if ((preferredPosition == Position.GOALIE && group.size() < GOALIE_SLOTS) || (
          preferredPosition == Position.DEFENDER && group.size() < DEFENDER_SLOTS) || (
          preferredPosition == Position.MIDFIELDER && group.size() < MIDFIELDER_SLOTS) || (
          preferredPosition == Position.FORWARD && group.size() < FORWARD_SLOTS)) {
        group.add(player);
      } else {
        waitList.add(player);
      }
    }

    // if there is any slot for lineUp, pick player from wait list
    int remainingSlotsGoalie = GOALIE_SLOTS - lineUp.get(Position.GOALIE).size();
    int remainingSlotsDefender = DEFENDER_SLOTS - lineUp.get(Position.DEFENDER).size();
    int remainingSlotsMidfielder = MIDFIELDER_SLOTS - lineUp.get(Position.MIDFIELDER).size();
    int remainingSlotsForward = FORWARD_SLOTS - lineUp.get(Position.FORWARD).size();

    for (Player player : waitList) {
      if (remainingSlotsGoalie == 0 && remainingSlotsDefender == 0 && remainingSlotsMidfielder == 0
          && remainingSlotsForward == 0) {
        break;
      }
      boolean assigned = false;
      // test if the player has been assigned
      for (Position position : Position.values()) {
        if (assigned) {
          break;
        } else {
          TreeSet<Player> group = lineUp.get(position);
          if (position == Position.GOALIE && group.size() < GOALIE_SLOTS) {
            group.add(player);
            remainingSlotsGoalie--;
            assigned = true;
          } else if (position == Position.DEFENDER && group.size() < DEFENDER_SLOTS) {
            group.add(player);
            remainingSlotsDefender--;
            assigned = true;
          } else if (position == Position.MIDFIELDER && group.size() < MIDFIELDER_SLOTS) {
            group.add(player);
            remainingSlotsMidfielder--;
            assigned = true;
          } else if (position == Position.FORWARD && group.size() < FORWARD_SLOTS) {
            group.add(player);
            remainingSlotsForward--;
            assigned = true;
          }
        }
      }
    }
  }

  @Override public String getLineUp() {
    makeLineUp();

    StringBuilder lineUpBuilder = new StringBuilder();

    // Add positions
    lineUpBuilder.append("GOALIE:\n");
    TreeSet<Player> goalies = lineUp.get(Position.GOALIE);
    for (Player goalie : goalies) {
      lineUpBuilder.append(goalie);
    }
    lineUpBuilder.append("\n");

    lineUpBuilder.append("DEFENDERS:\n");
    TreeSet<Player> defenders = lineUp.get(Position.DEFENDER);
    for (Player defender : defenders) {
      lineUpBuilder.append(defender);
    }
    lineUpBuilder.append("\n");

    lineUpBuilder.append("MIDFIELDERS:\n");
    TreeSet<Player> midfielders = lineUp.get(Position.MIDFIELDER);
    for (Player midfielder : midfielders) {
      lineUpBuilder.append(midfielder);
    }
    lineUpBuilder.append("\n");

    lineUpBuilder.append("FORWARDS:\n");
    TreeSet<Player> forwards = lineUp.get(Position.FORWARD);
    for (Player forward : forwards) {
      lineUpBuilder.append(forward);
    }
    lineUpBuilder.append("\n");

    return lineUpBuilder.toString();
  }

  private void makeBench() {
    for (Player player : candidate) {
      boolean playerInLineUp = false;
      for (Position position : Position.values()) {
        TreeSet<Player> group = lineUp.get(position);
        if (group.contains(player)) {
          playerInLineUp = true;
          break;
        }
      }
      if (!playerInLineUp) {
        bench.add(player);
      }
    }
  }

  @Override public String getBench() {
    makeLineUp();
    makeBench();

    StringBuilder benchBuilder = new StringBuilder();
    for (Player player : this.bench) {
      benchBuilder.append(player.toString());
      benchBuilder.append("\n");
    }

    return benchBuilder.toString();
  }
}