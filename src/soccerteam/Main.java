package soccerteam;

import java.time.LocalDate;

/**
 * Run a TicTacToe game interactively on the console.
 */
public class Main {
  /**
   * Run a TicTacToe game interactively on the console.
   *
   * @param args not used
   */
  public static void main(String[] args) {
    Team m = new TeamImpl();
    TeamDisplay v = new TeamDisplayImpl("U-10 Soccer Team");
    Features c = new TeamController(v, m);
    c.go(v);
  }
}
