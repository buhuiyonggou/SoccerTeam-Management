package soccerteam;

import static soccerteam.Position.DEFENDER;
import static soccerteam.Position.FORWARD;
import static soccerteam.Position.GOALIE;
import static soccerteam.Position.MIDFIELDER;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 * This class represents the implementation of the TeamDisplay interface for a soccer team
 * management system. It extends JFrame to provide a graphical user interface that allows users to
 * interact with the system, manage teams, and display team information.
 */
public class TeamDisplayImpl extends JFrame implements TeamDisplay {
  private final JLabel teamDisplayLabel;
  private final JLabel displayPlayersLabel;
  private final JButton exitButton;
  private final JPanel mainPanel;
  private final JButton addButton;
  private final JButton cancelButton;
  private final JButton createTeamButton;
  private final JButton displayTeamButton;
  private final JButton displayLineUpButton;
  private final JButton displayBenchButton;
  private final JTextArea teamInfoArea;
  private final JTextField firstNameField;
  private final JTextField lastNameField;
  private final JTextField dateOfBirthField;
  private final JComboBox<Position> preferredPositionCombo;
  private final JComboBox<String> skillLevelCombo;

  /**
   * Constructs a new TeamDisplayImpl instance with the specified title. Initializes the user
   * interface components, including labels, text fields, combo boxes, and buttons. Sets up the
   * layout and action listeners for various interface components.
   *
   * @param title the title of the JFrame window.
   */
  public TeamDisplayImpl(String title) {
    super(title);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    // create the main panel
    mainPanel = new JPanel(new CardLayout());
    mainPanel.setPreferredSize(new Dimension(600, 600));

    // create a label to display relative information
    displayPlayersLabel = new JLabel("Players Added:");
    displayPlayersLabel.setHorizontalAlignment(SwingConstants.CENTER);

    // create label for fields and make them center, reminding what should input
    JLabel firstNameLabel = new JLabel("First Name:");
    firstNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
    JLabel lastNameLabel = new JLabel("Last Name:");
    lastNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
    JLabel dateOfBirthLabel = new JLabel("Date of Birth:");
    dateOfBirthLabel.setHorizontalAlignment(SwingConstants.CENTER);
    JLabel preferredPositionLabel = new JLabel("Preferred Position:");
    preferredPositionLabel.setHorizontalAlignment(SwingConstants.CENTER);
    JLabel skillLevelLabel = new JLabel("Skill Level:");
    skillLevelLabel.setHorizontalAlignment(SwingConstants.CENTER);

    // Create fields and buttons to display information
    firstNameField = new JTextField();
    lastNameField = new JTextField();
    dateOfBirthField = new JTextField();

    // create comboBox for selection
    Position[] positions = { null, GOALIE, DEFENDER, MIDFIELDER, FORWARD };
    preferredPositionCombo = new JComboBox<>(positions);
    String[] skills = { "", "1", "2", "3", "4", "5" };
    skillLevelCombo = new JComboBox<>(skills);

    addButton = new JButton("Add");
    cancelButton = new JButton("Cancel");

    // create input panel
    JPanel inputPanel = new JPanel(new GridLayout(5, 3));

    //adding buttons and fields to the inputPanel
    inputPanel.add(firstNameLabel);
    inputPanel.add(firstNameField);
    inputPanel.add(addButton);

    inputPanel.add(lastNameLabel);
    inputPanel.add(lastNameField);
    inputPanel.add(addButton);

    inputPanel.add(dateOfBirthLabel);
    inputPanel.add(dateOfBirthField);
    inputPanel.add(addButton);

    inputPanel.add(preferredPositionLabel);
    inputPanel.add(preferredPositionCombo);
    inputPanel.add(addButton);

    inputPanel.add(skillLevelLabel);
    inputPanel.add(skillLevelCombo);
    inputPanel.add(addButton);

    // create addPlayerPanel for making the layout of players
    JPanel addPlayerPanel = new JPanel(new BorderLayout());
    JPanel addPlayerButtonsPanel = new JPanel(new GridLayout(1, 3));

    addPlayerButtonsPanel.add(addButton);
    addPlayerButtonsPanel.add(cancelButton);

    addPlayerPanel.add(displayPlayersLabel, BorderLayout.NORTH);
    addPlayerPanel.add(inputPanel, BorderLayout.CENTER);
    addPlayerPanel.add(addPlayerButtonsPanel, BorderLayout.SOUTH);

    // create a TextArea to display team info
    teamInfoArea = new JTextArea();
    teamInfoArea.setEditable(false);
    teamInfoArea.setLineWrap(true);
    teamInfoArea.setWrapStyleWord(true);

    // create another panel to display team information
    // add label and info area to the teamLayoutPanel
    JPanel teamLayoutPanel = new JPanel(new BorderLayout());
    teamLayoutPanel.add(teamInfoArea, BorderLayout.CENTER);

    createTeamButton = new JButton("Create_Team");
    displayTeamButton = new JButton("Display_Team");
    displayLineUpButton = new JButton("Display_LineUp");
    displayBenchButton = new JButton("Display_Bench");

    // create displayPanel at the top
    JPanel displayTeamPanel = new JPanel(new BorderLayout());
    JPanel displayTeamButtonsPanel = new JPanel(new GridLayout(1, 4));
    displayTeamPanel.add(teamLayoutPanel);
    teamDisplayLabel = new JLabel("You haven't created a team yet");
    teamLayoutPanel.add(teamDisplayLabel, BorderLayout.NORTH);
    teamDisplayLabel.setHorizontalAlignment(SwingConstants.CENTER);

    // Set up action listeners for Display_Team, Display_LineUp, and Back buttons in displayPanel
    displayTeamButtonsPanel.add(createTeamButton);
    displayTeamPanel.add(displayTeamButtonsPanel, BorderLayout.SOUTH);
    displayTeamButtonsPanel.add(displayTeamButton);
    displayTeamButtonsPanel.add(displayLineUpButton);
    displayTeamButtonsPanel.add(displayBenchButton);

    JButton addPlayersButton = new JButton("Go to add players");
    JButton displayButton = new JButton("Go to display the team");
    exitButton = new JButton("EXIT");

    addPlayersButton.addActionListener(evt -> switchToAddPlayerPanel());
    displayButton.addActionListener(evt -> switchToDisplayPanel());

    // binding click activity to switch between panels
    mainPanel.add(addPlayerPanel, "Go to add players");
    mainPanel.add(displayTeamPanel, "Go to display the team");

    // create the bottom panel of the main panel
    JPanel buttonPanel = new JPanel();
    buttonPanel.add(addPlayersButton);
    buttonPanel.add(displayButton);
    buttonPanel.add(exitButton);

    add(mainPanel, BorderLayout.CENTER);
    add(buttonPanel, BorderLayout.SOUTH);

    // Adjust fonts
    Font switchButtonFont = new Font("Times New Roman", Font.BOLD, 18);
    addPlayersButton.setFont(switchButtonFont);
    displayButton.setFont(switchButtonFont);
    exitButton.setFont(switchButtonFont);

    Font labelFont = new Font("Times New Roman", Font.PLAIN, 18);
    teamDisplayLabel.setFont(labelFont);
    displayPlayersLabel.setFont(labelFont);

    Font textAreaFont = new Font("Times New Roman", Font.PLAIN, 16);
    teamInfoArea.setFont(textAreaFont);

    pack();
    setVisible(true);
  }

  @Override public void addFeatures(Features features) {
    addButton.addActionListener(evt -> {
      String firstName = firstNameField.getText();
      String lastName = lastNameField.getText();
      String dateOfBirth = dateOfBirthField.getText();
      Object preferredPosition = preferredPositionCombo.getSelectedItem();
      Object skillLevel = skillLevelCombo.getSelectedItem();
      features.addPlayers(firstName, lastName, dateOfBirth, preferredPosition, skillLevel);
    });
    cancelButton.addActionListener(evt -> features.resetInformation());
    createTeamButton.addActionListener(evt -> features.createTeam());
    displayTeamButton.addActionListener(evt -> features.displayTeamPlayers());
    displayLineUpButton.addActionListener(evt -> features.displayStartingLineup());
    displayBenchButton.addActionListener(evt -> features.displayBench());
    exitButton.addActionListener(evt -> features.exit());
  }

  @Override public void resetFocus() {
    this.setFocusable(true);
    this.requestFocus();
  }

  @Override public void displayPlayerMessage(String message) {
    displayPlayersLabel.setText(message);
  }

  @Override public void resetAllInputs() {
    firstNameField.setText("");
    lastNameField.setText("");
    dateOfBirthField.setText("");
    preferredPositionCombo.setSelectedIndex(0);
    skillLevelCombo.setSelectedIndex(0);
  }

  @Override public void displayTeam(String team, String notification) {
    teamDisplayLabel.setText(notification);
    teamInfoArea.setText(team);
  }

  @Override public void displayLineUp(String lineUp, String notification) {
    teamDisplayLabel.setText(notification);
    teamInfoArea.setText(lineUp);
  }

  @Override public void displayBench(String bench, String notification) {
    teamDisplayLabel.setText(notification);
    teamInfoArea.setText(bench);
  }

  @Override public void switchToAddPlayerPanel() {
    CardLayout cl = (CardLayout) mainPanel.getLayout();
    cl.show(mainPanel, "Go to add players");
  }

  @Override public void switchToDisplayPanel() {
    CardLayout cl = (CardLayout) mainPanel.getLayout();
    cl.show(mainPanel, "Go to display the team");
  }
}
