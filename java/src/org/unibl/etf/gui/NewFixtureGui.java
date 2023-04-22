package org.unibl.etf.gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import org.unibl.etf.classes.Fixture;
import org.unibl.etf.classes.FootballClub;
import org.unibl.etf.classes.Game;
import org.unibl.etf.classes.MainReferee;
import org.unibl.etf.classes.Stadium;
import org.unibl.etf.classes.TeamInGame;

public class NewFixtureGui extends JFrame {

	private JPanel contentPane;
	private JTextField newDateField;
	private JTextField homeTeamGoalsField;
	private JTextField awayTeamGoalsField;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NewFixtureGui frame = new NewFixtureGui();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	
	private FixtureShowGui fixtureShowFrame;
	public void setFixtureShowFrame(FixtureShowGui frame) {
		fixtureShowFrame = frame;
	}
	
	private NewFixtureGui frame;
	public void setFrame(NewFixtureGui frame) {
		this.frame = frame;
	}
	
	List<Fixture> fixtures = new ArrayList<>();
	public void populateFixtures() {
		fixtures = fixtureShowFrame.getFixtures();
		for(Fixture f : fixtures) {
			chooseFixtureBox.addItem(f);
		}
	}
	
	List<MainReferee> mainReferees = new ArrayList<>();
	public void populateMainReferees() {
		mainReferees = fixtureShowFrame.getMainReferees();
		for(MainReferee f : mainReferees) {
			refereeBox.addItem(f);
		}
	}
	
	List<FootballClub> teams = new ArrayList<>();
	public void populateTeams() {
		teams = fixtureShowFrame.getTeams();
		for(FootballClub f : teams) {
			chooseHomeTeamBox.addItem(f);
			chooseAwayTeamBox.addItem(f);
		}
	}
	
	private JComboBox chooseFixtureBox;
	private JComboBox refereeBox;
	private JComboBox chooseAwayTeamBox;
	private JComboBox chooseHomeTeamBox;
	public NewFixtureGui() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1151, 741);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Date = ");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(35, 117, 172, 62);
		contentPane.add(lblNewLabel);
		
		JLabel lblFoundatioDate = new JLabel("Home team goals = ");
		lblFoundatioDate.setHorizontalAlignment(SwingConstants.CENTER);
		lblFoundatioDate.setBounds(35, 284, 172, 62);
		contentPane.add(lblFoundatioDate);
		
		newDateField = new JTextField();
		newDateField.setBounds(217, 128, 165, 41);
		contentPane.add(newDateField);
		newDateField.setColumns(10);
		
		homeTeamGoalsField = new JTextField();
		homeTeamGoalsField.setColumns(10);
		homeTeamGoalsField.setBounds(217, 295, 165, 41);
		contentPane.add(homeTeamGoalsField);
		
		chooseHomeTeamBox = new JComboBox();
		chooseHomeTeamBox.setBounds(792, 95, 261, 21);
		contentPane.add(chooseHomeTeamBox);
		
		JLabel lblChooseStadium = new JLabel("Home team = ");
		lblChooseStadium.setHorizontalAlignment(SwingConstants.CENTER);
		lblChooseStadium.setBounds(598, 74, 172, 62);
		contentPane.add(lblChooseStadium);
		
		JButton saveButton = new JButton("SAVE");
		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Fixture f = (Fixture)chooseFixtureBox.getSelectedItem();
					String[] dateFieldParse = newDateField.getText().split("-");
					if(dateFieldParse.length != 3) {
						throw new Exception("Problem!");
					}
					Date date = Date.valueOf(newDateField.getText());
					MainReferee mr = (MainReferee)refereeBox.getSelectedItem();
					int homeTeamGoals = Integer.valueOf(homeTeamGoalsField.getText());
					int awayTeamGoals = Integer.valueOf(awayTeamGoalsField.getText());
					FootballClub homeTeam = (FootballClub)chooseHomeTeamBox.getSelectedItem();
					FootballClub awayTeam = (FootballClub)chooseAwayTeamBox.getSelectedItem();
					Game game = new Game();
					game.setDatumUtakmice(date);
					game.setGlavni_sudija_sudija_sudijaId(mr.getSudija_sudijaId());
					game.setKolo_idKola(f.getBrojKola());
					fixtureShowFrame.insertGame(game);
					TeamInGame teamInGame1 = new TeamInGame();
					TeamInGame teamInGame2 = new TeamInGame();
					teamInGame1.setBrojPostignutihGolova(homeTeamGoals);
					teamInGame1.setBrojPrimljenihGolova(awayTeamGoals);
					teamInGame2.setBrojPostignutihGolova(awayTeamGoals);
					teamInGame2.setBrojPrimljenihGolova(homeTeamGoals);
					teamInGame1.setDomacin(true);
					teamInGame2.setDomacin(false);
					teamInGame1.setFudbalski_klub_idKluba(homeTeam.getIdKluba());
					teamInGame2.setFudbalski_klub_idKluba(awayTeam.getIdKluba());
					teamInGame1.setUtakmica_utakmicaId(fixtureShowFrame.getGames().get(fixtureShowFrame.getGames().size()-1).getUtakmicaId());
					teamInGame2.setUtakmica_utakmicaId(fixtureShowFrame.getGames().get(fixtureShowFrame.getGames().size()-1).getUtakmicaId());
					fixtureShowFrame.insertTeamInGame(teamInGame1);
					fixtureShowFrame.insertTeamInGame(teamInGame2);
					try {
						Thread.sleep(1000);
					} catch(InterruptedException ex) {
						ex.printStackTrace();
					}
					fixtureShowFrame.dispose();
					frame.dispose();
					FixtureShowGui sg = new FixtureShowGui();
					sg.setFrame(sg);
					sg.setVisible(true);
				} catch(Exception e1) {
					e1.printStackTrace();
				}	
			}
		});
		saveButton.setBounds(254, 620, 643, 57);
		contentPane.add(saveButton);
		
		JLabel lblAwayTeam = new JLabel("Away team = ");
		lblAwayTeam.setHorizontalAlignment(SwingConstants.CENTER);
		lblAwayTeam.setBounds(610, 179, 172, 62);
		contentPane.add(lblAwayTeam);
		
		chooseAwayTeamBox = new JComboBox();
		chooseAwayTeamBox.setBounds(792, 200, 261, 21);
		contentPane.add(chooseAwayTeamBox);
		
		chooseFixtureBox = new JComboBox();
		chooseFixtureBox.setBounds(217, 49, 132, 34);
		contentPane.add(chooseFixtureBox);
		
		JLabel lblFixtureNum = new JLabel("Fixture num = ");
		lblFixtureNum.setHorizontalAlignment(SwingConstants.CENTER);
		lblFixtureNum.setBounds(35, 35, 172, 62);
		contentPane.add(lblFixtureNum);
		
		JLabel lblReferee = new JLabel("Referee = ");
		lblReferee.setHorizontalAlignment(SwingConstants.CENTER);
		lblReferee.setBounds(35, 204, 172, 62);
		contentPane.add(lblReferee);
		
		refereeBox = new JComboBox();
		refereeBox.setBounds(217, 218, 132, 34);
		contentPane.add(refereeBox);
		
		JLabel lblAwayTeamGoals = new JLabel("Away team goals = ");
		lblAwayTeamGoals.setHorizontalAlignment(SwingConstants.CENTER);
		lblAwayTeamGoals.setBounds(35, 370, 172, 62);
		contentPane.add(lblAwayTeamGoals);
		
		awayTeamGoalsField = new JTextField();
		awayTeamGoalsField.setColumns(10);
		awayTeamGoalsField.setBounds(217, 378, 165, 41);
		contentPane.add(awayTeamGoalsField);
	}
}
