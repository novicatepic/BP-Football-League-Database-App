package org.unibl.etf.gui;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.unibl.etf.classes.Fixture;
import org.unibl.etf.classes.FootballClub;
import org.unibl.etf.classes.Game;
import org.unibl.etf.classes.MainReferee;
import org.unibl.etf.classes.Referee;
import org.unibl.etf.classes.Stadium;
import org.unibl.etf.classes.TeamInGame;
import org.unibl.etf.dao.FixtureDAO;
import org.unibl.etf.dao.GameDAO;
import org.unibl.etf.dao.MainRefereeDAO;
import org.unibl.etf.dao.RefereeDAO;
import org.unibl.etf.dao.TeamInGameDAO;
import org.unibl.etf.util.DBUtil;

import com.mysql.cj.protocol.x.SyncFlushDeflaterOutputStream;

import javax.swing.SwingConstants;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;
import java.awt.Font;

public class FixtureShowGui extends JFrame implements FixtureDAO, RefereeDAO ,MainRefereeDAO, GameDAO, TeamInGameDAO {
	private static final String SQL_SELECT_ALL_FIXTURES = "SELECT * FROM kolo";
	private static final String SQL_SELECT_ALL_REFEREES = "SELECT * FROM sudija";
	private static final String SQL_SELECT_ALL_MAIN_REFEREES = "SELECT * FROM glavni_sudija";
	private static final String SQL_SELECT_ALL_MAIN_GAMES = "SELECT * FROM utakmica";
	private static final String SQL_INSERT_GAME = "INSERT INTO utakmica (UtakmicaId, KOLO_IdKola, GLAVNI_SUDIJA_SUDIJA_SudijaId, DatumUtakmice) VALUES (null, ?, ?, ?)";
	private static final String SQL_DELETE_GAME = "DELETE FROM utakmica WHERE UtakmicaId=?";
	private static final String SQL_SELECT_ALL_TEAMS_IN_GAME = "SELECT * FROM klub_na_utakmici";
	private static final String SQL_INSERT_TEAM_IN_GAME = "INSERT INTO klub_na_utakmici (FUDBALSKI_KLUB_IdKluba, UTAKMICA_UtakmicaId, BrojPostignutihGolova, IsDomacin, BrojPrimljenihGolova) VALUES (?, ?, ?, ?, ?)";
	private static final String SQL_UPDATE_TEAM_IN_GAME = "UPDATE klub_na_utakmici SET BrojPostignutihGolova=?, BrojPrimljenihGolova=? WHERE FUDBALSKI_KLUB_IdKluba=? and UTAKMICA_UtakmicaId=?";
	private static final String SQL_DELETE_TEAM_IN_GAME = "DELETE FROM klub_na_utakmici WHERE UTAKMICA_UtakmicaId=?";
	
	private static final String SQL_SELECT_REFEREE_NAMES = "select s.SudijaId, s.Ime, s.Prezime from sudija s join glavni_sudija gs on s.SudijaId=gs.SUDIJA_SudijaId";
	private static String SQL_SELECT_TEAMS_THAT_PLAYED_FIXTURE = "select f.IdKluba, f.Naziv from klub_na_utakmici k left join fudbalski_klub f on f.IdKluba=k.FUDBALSKI_KLUB_IdKluba"
			+ " left join utakmica u on u.UtakmicaId=k.UTAKMICA_UtakmicaId where KOLO_IdKola=";
	
	private static String SQL_SELECT_ALL_GAMES_BASED_ON_FIXTURE = "SELECT * FROM utakmica WHERE KOLO_IdKola=";
	private static String SQL_SELECT_TEAMS_WHO_PLAYED_BASED_ON_GAME = "SELECT * FROM klub_na_utakmici WHERE UTAKMICA_UtakmicaId=";
	private static String SQL_SELECT_TEAM_WHO_PLAYED_BASED_ON_TEAMS = "SELECT * FROM fudbalski_klub WHERE IdKluba=";
	
	private JPanel contentPane;
	private int fixtureNumber = 0;
	JLabel whichFixtureLabel;
	private JPanel awayTeamsPanel;
	private JPanel resultsPanel;
	private JPanel datePanel;
	private JComboBox gameweekBox;
	private JPanel changeFixturePanel;
	private JLabel lblNewLabel;
	private JButton newFixtureButton;
	private JButton submitFixtureButton;
	private JPanel deleteFixturePanel;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FixtureShowGui frame = new FixtureShowGui();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private FixtureShowGui frame;
	public void setFrame(FixtureShowGui frame) {
		this.frame = frame;
	}

	private JButton[] changeButtons;
	private JButton[] deleteButtons;
	private JButton[] showPlayersButtons;
	private JLabel[] homeTeamLabels;
	private JLabel[] awayTeamLabels;
	private JLabel[] dateLabels;
	private JLabel[] resultLabels;
	private JLabel[] idLabels;
	private JLabel[] clubIdLabels;
	
	public FixtureShowGui() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setBounds(100, 100, 1352, 784);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		whichFixtureLabel = new JLabel();
		whichFixtureLabel.setHorizontalAlignment(SwingConstants.CENTER);
		whichFixtureLabel.setBounds(277, 411, 130, 13);
		getContentPane().add(whichFixtureLabel);	
		JPanel homeTeamsPanel = new JPanel();
		homeTeamsPanel.setBounds(320, 193, 170, 462);
		contentPane.add(homeTeamsPanel);
		//homeTeamsPanel.setLayout(new GridLayout(1, 0, 0, 0));
		homeTeamsPanel.setLayout(new GridLayout(10, 1));
		
		awayTeamsPanel = new JPanel();
		awayTeamsPanel.setBounds(491, 193, 170, 462);
		//awayTeamsPanel.setLayout(new GridLayout(1, 0, 0, 0));
		awayTeamsPanel.setLayout(new GridLayout(10, 1));
		//awayTeamsPanel.add(new JButton("BUTTON"));
		contentPane.add(awayTeamsPanel);
		
		resultsPanel = new JPanel();
		resultsPanel.setBounds(660, 193, 170, 462);
		contentPane.add(resultsPanel);
		resultsPanel.setLayout(new GridLayout(1, 0, 0, 0));
		
		datePanel = new JPanel();
		datePanel.setBounds(829, 193, 170, 462);
		contentPane.add(datePanel);
		datePanel.setLayout(new GridLayout(1, 0, 0, 0));
		
		JPanel gameIdPanel = new JPanel();
		gameIdPanel.setBounds(21, 193, 117, 462);
		contentPane.add(gameIdPanel);
		gameIdPanel.setLayout(new GridLayout(10, 1));
		
		gameweekBox = new JComboBox();
		gameweekBox.setToolTipText("KOLO");
		gameweekBox.setBounds(96, 94, 71, 44);
		contentPane.add(gameweekBox);
		
		changeFixturePanel = new JPanel();
		changeFixturePanel.setBounds(999, 193, 106, 462);
		contentPane.add(changeFixturePanel);
		changeFixturePanel.setLayout(new GridLayout(10, 1));
		
		lblNewLabel = new JLabel("FIXTURES");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(50, 10, 1031, 59);
		contentPane.add(lblNewLabel);
		
		newFixtureButton = new JButton("Add a new fixture");
		newFixtureButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NewFixtureGui nfg = new NewFixtureGui();
				nfg.setFixtureShowFrame(frame);
				nfg.setFrame(nfg);
				Fixture f = (Fixture)gameweekBox.getSelectedItem();
				nfg.populateFixture(f);
				nfg.populateMainReferees();
				List<String> teamsWhoPlayed = selectTeamsWhoPlayedFixture(f.getBrojKola());
				List<FootballClub> teamsWhoPass = new ArrayList<>();
				for(FootballClub fc : teams) {
					boolean found = false;
					for(String twp : teamsWhoPlayed) {
						if(fc.getIdKluba() == Integer.valueOf(twp)) {
							found = true;
						}
					}
					if(!found) {
						teamsWhoPass.add(fc);
					}
				}
				nfg.populateTeams(teamsWhoPass);
				nfg.setVisible(true);
			}
		});
		newFixtureButton.setBounds(72, 665, 222, 72);
		contentPane.add(newFixtureButton);
		
		submitFixtureButton = new JButton("Submit");
		JPanel showPlayersPanel = new JPanel();
		showPlayersPanel.setBounds(1210, 193, 106, 462);
		contentPane.add(showPlayersPanel);
		showPlayersPanel.setLayout(new GridLayout(10, 1));
		//int length = 0;
		submitFixtureButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				homeTeamsPanel.removeAll();
				awayTeamsPanel.removeAll();
				datePanel.removeAll();
				resultsPanel.removeAll();
				changeFixturePanel.removeAll();
				deleteFixturePanel.removeAll();
				showPlayersPanel.removeAll();
				gameIdPanel.removeAll();
				clubIdsPanel.removeAll();
				Fixture fix = (Fixture)gameweekBox.getSelectedItem();
				//System.out.println(fix.getBrojKola());
				List<Game> gamesInFixture = selectGamesBasedOnFixture(fix.getBrojKola());
				int length = gamesInFixture.size();
				//System.out.println("LENDZ="+length);
				homeTeamsPanel.setLayout(new GridLayout(length, 1));
				awayTeamsPanel.setLayout(new GridLayout(length, 1));
				resultsPanel.setLayout(new GridLayout(length, 1));
				datePanel.setLayout(new GridLayout(length, 1));
				changeFixturePanel.setLayout(new GridLayout(length, 1));
				deleteFixturePanel.setLayout(new GridLayout(length, 1));
				showPlayersPanel.setLayout(new GridLayout(length, 1));
				gameIdPanel.setLayout(new GridLayout(length, 1));
				clubIdsPanel.setLayout(new GridLayout(length, 1));
				changeButtons = new JButton[length];
				deleteButtons = new JButton[length];
				showPlayersButtons = new JButton[length];
				homeTeamLabels = new JLabel[length];
				awayTeamLabels = new JLabel[length];
				dateLabels = new JLabel[length];
				resultLabels = new JLabel[length];
				idLabels = new JLabel[length];
				clubIdLabels = new JLabel[length];
				for(int i=0; i < length; i++) {
					changeButtons[i] = new JButton("CHANGE");
					deleteButtons[i] = new JButton("DELETE");
					showPlayersButtons[i] = new JButton("SHOW PLAYERS");
					homeTeamLabels[i] = new JLabel();
					awayTeamLabels[i] = new JLabel();
					dateLabels[i] = new JLabel();
					resultLabels[i] = new JLabel();
					idLabels[i] = new JLabel();
					clubIdLabels[i] = new JLabel();
				}
				for(int i = 0; i < length; i++) {
					showPlayersPanel.add(showPlayersButtons[i]);
					changeFixturePanel.add(changeButtons[i]);
					deleteFixturePanel.add(deleteButtons[i]);
				}
				//contentPane.add(changeFixturePanel);
				for(int i=0; i < gamesInFixture.size(); i++) {
					List<TeamInGame> teamsInGame = selectTeamsWhoPlayedInGame(gamesInFixture.get(i));
					dateLabels[i].setText(gamesInFixture.get(i).getDatumUtakmice().toString());
					dateLabels[i].setHorizontalAlignment(SwingConstants.CENTER);
					dateLabels[i].setVerticalAlignment(SwingConstants.CENTER);
					idLabels[i].setText(String.valueOf(gamesInFixture.get(i).getUtakmicaId()));
					idLabels[i].setHorizontalAlignment(SwingConstants.CENTER);
					idLabels[i].setVerticalAlignment(SwingConstants.CENTER);
					gameIdPanel.add(idLabels[i]);
					datePanel.add(dateLabels[i]);
					for(int j=0; j<2; j++) {
						homeTeamLabels[i].setHorizontalAlignment(SwingConstants.CENTER);
						homeTeamLabels[i].setVerticalAlignment(SwingConstants.CENTER);
						awayTeamLabels[i].setHorizontalAlignment(SwingConstants.CENTER);
						awayTeamLabels[i].setVerticalAlignment(SwingConstants.CENTER);
						resultLabels[i].setHorizontalAlignment(SwingConstants.CENTER);
						resultLabels[i].setVerticalAlignment(SwingConstants.CENTER);
						clubIdLabels[i].setHorizontalAlignment(SwingConstants.CENTER);
						clubIdLabels[i].setVerticalAlignment(SwingConstants.CENTER);
						if(teamsInGame.get(0).isDomacin()) {
							List<FootballClub> singleTeam = selectTeamInAMatch(teamsInGame.get(0).getFudbalski_klub_idKluba());
							List<FootballClub> singleTeam2 = selectTeamInAMatch(teamsInGame.get(1).getFudbalski_klub_idKluba());
							homeTeamLabels[i].setText(singleTeam.get(0).toString());					
							homeTeamsPanel.add(homeTeamLabels[i]);
							awayTeamLabels[i].setText(singleTeam2.get(0).toString());					
							awayTeamsPanel.add(awayTeamLabels[i]);
							String resultStr = "";
							resultStr += teamsInGame.get(0).getBrojPostignutihGolova() + "-" + teamsInGame.get(1).getBrojPostignutihGolova();
							resultLabels[i].setText(resultStr);					
							resultsPanel.add(resultLabels[i]);
							clubIdLabels[i].setText(String.valueOf(teamsInGame.get(0).getFudbalski_klub_idKluba())
									+"-"+teamsInGame.get(1).getFudbalski_klub_idKluba());					
							clubIdsPanel.add(clubIdLabels[i]);
						} else {
							List<FootballClub> singleTeam = selectTeamInAMatch(teamsInGame.get(1).getFudbalski_klub_idKluba());
							List<FootballClub> singleTeam2 = selectTeamInAMatch(teamsInGame.get(0).getFudbalski_klub_idKluba());
							homeTeamLabels[i].setText(singleTeam.get(0).toString());					
							homeTeamsPanel.add(homeTeamLabels[i]);
							awayTeamLabels[i].setText(singleTeam2.get(0).toString());					
							awayTeamsPanel.add(awayTeamLabels[i]);
							String resultStr = "";
							resultStr += teamsInGame.get(1).getBrojPostignutihGolova() + "-" + teamsInGame.get(0).getBrojPostignutihGolova();
							resultLabels[i].setText(resultStr);					
							resultsPanel.add(resultLabels[i]);
							clubIdLabels[i].setText(String.valueOf(teamsInGame.get(1).getFudbalski_klub_idKluba())
									+"-"+teamsInGame.get(0).getFudbalski_klub_idKluba());					
							clubIdsPanel.add(clubIdLabels[i]);
						}
					}
				}

				for(int i=0; i<length; i++) {
					final int temp = i;
					changeButtons[i].addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							int toChange = Integer.valueOf(idLabels[temp].getText());
							ChangeFixtureGui cfg = new ChangeFixtureGui();
							cfg.setFrame(cfg);
							cfg.setFrame(frame);
							cfg.setGameId(toChange);
							cfg.setClubIds(clubIdLabels[temp].getText());
							cfg.setResult(resultLabels[temp].getText());
							cfg.setVisible(true);
						}
					});
					
					deleteButtons[i].addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							int toDelete = Integer.valueOf(idLabels[temp].getText());
							deleteTeamInGame(toDelete);
							deleteGame(toDelete);
							try {
								Thread.sleep(1000);
							} catch(InterruptedException ex) {
								ex.printStackTrace();
							}
							frame.dispose();
							FixtureShowGui sg = new FixtureShowGui();
							sg.setFrame(sg);
							sg.setVisible(true);
						}
					});
					
					showPlayersButtons[i].addActionListener(new ActionListener() {					
						@Override
						public void actionPerformed(ActionEvent e) {
							int gameId = Integer.valueOf(idLabels[temp].getText());
							String clubId = String.valueOf(clubIdLabels[temp].getText());
							String[] parse = clubId.split("-");
							int homeClubId = Integer.valueOf(parse[0]);
							int awayClubId = Integer.valueOf(parse[1]);
							ShowPlayers sp = new ShowPlayers();
							sp.setFrame(sp);
							sp.setIds(homeClubId,awayClubId,gameId);
							sp.initPlayers();
							sp.setVisible(true);
						}
					});
				}
				
				frame.invalidate();
				frame.validate();
				frame.repaint();			
			}
		});
		submitFixtureButton.setBounds(198, 91, 138, 50);
		contentPane.add(submitFixtureButton);
		
		deleteFixturePanel = new JPanel();
		deleteFixturePanel.setBounds(1104, 193, 106, 462);
		contentPane.add(deleteFixturePanel);
		deleteFixturePanel.setLayout(new GridLayout(1, 0, 0, 0));
		
		JLabel lblNewLabel_1 = new JLabel("Home team");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(320, 161, 170, 35);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Away team");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setBounds(491, 161, 170, 35);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Result");
		lblNewLabel_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1_1.setBounds(660, 161, 170, 35);
		contentPane.add(lblNewLabel_1_1_1);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("Date");
		lblNewLabel_1_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1_1_1.setBounds(829, 161, 170, 35);
		contentPane.add(lblNewLabel_1_1_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("GameId");
		lblNewLabel_1_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_2.setBounds(21, 161, 116, 35);
		contentPane.add(lblNewLabel_1_2);
		
		clubIdsPanel = new JPanel();
		clubIdsPanel.setBounds(150, 193, 117, 462);
		contentPane.add(clubIdsPanel);
		clubIdsPanel.setLayout(new GridLayout(10, 1));
		
		lblNewLabel_1_3 = new JLabel("ClubIds");
		lblNewLabel_1_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_3.setBounds(151, 161, 116, 35);
		contentPane.add(lblNewLabel_1_3);
		
		
		readFixtures();
		readGames();
		for(Fixture f : fixtures) {
			gameweekBox.addItem(f);
		}
		
		readReferees();
		/*for(Referee ref : referees) {
			System.out.println(ref);
		}*/
		
		readMainReferees();
		/*for(MainReferee ref : mainReferees) {
			System.out.println(ref);
		}*/
		/*for(MainReferee mr : mainReferees) {
			for(Referee r : referees) {
				if(r.getSudijaId() ==)
			}
		}*/
		
		readClubs();
		
		List<String> refNames = selectRefNames();
		for(String name : refNames) {
			String[] split = name.split("-");
			for(MainReferee ref : mainReferees) {
				if(ref.getSudija_sudijaId() == Integer.valueOf(split[0])) {
					ref.setToPrint(split[1] + " " + split[2]);
				}
			}
		}
		/*for(FootballClub f : teams) {
			System.out.println(f);
		}*/	
		
		/*List<String> teams1 = selectTeamsWhoPlayedFixture(1);
		for(String team : teams1) {
			System.out.println(team);
		}*/
		
	}
	
	public void setFixtureNumber(int fixNum) {
		fixtureNumber = fixNum;
		//System.out.println(fixtureNumber);
		whichFixtureLabel.setText("Kolo broj " + fixtureNumber);
	}

	private List<Fixture> fixtures;
	private void readFixtures() {
		fixtures = selectAllFixtures();
	}
	
	private List<Referee> referees;
	private void readReferees() {
		referees = selectAllReferees();
	}
	
	private List<MainReferee> mainReferees;
	private void readMainReferees() {
		mainReferees = selectAllMainReferees();
	}
	
	private List<FootballClub> teams;
	private void readClubs() {
		TeamGui teamGui = new TeamGui();
		teams = teamGui.selectAll();
		teamGui.dispose();
	}
	
	private List<Game> games;
	private JPanel clubIdsPanel;
	private JLabel lblNewLabel_1_3;
	private void readGames() {
		games = selectAllGames();
	}
	
	public List<Game> getGames() {
		return games;
	}
	
	public List<Fixture> getFixtures() {
		return fixtures;
	}
	
	public List<MainReferee> getMainReferees() {
		return mainReferees;
	}
	
	public List<FootballClub> getTeams() {
		return teams;
	}
	
	//private ArrayList<String> refNames;
	private List<String> selectRefNames() {
		List<String> retVal = new ArrayList<String>();
		Connection c = null;
		Statement s = null;
		ResultSet rs = null;

		try {
			c = DBUtil.getConnection();
			s = c.createStatement();
			rs = s.executeQuery(SQL_SELECT_REFEREE_NAMES);		
			while (rs.next()) {
				retVal.add(new String(rs.getString("SudijaId") + "-" + rs.getString("Ime") + "-" + rs.getString("Prezime")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs, s, c);
		}

		return retVal;
	}
	
	public List<String> selectTeamsWhoPlayedFixture(int fixtureNum) {
		List<String> retVal = new ArrayList<String>();
		Connection c = null;
		Statement s = null;
		ResultSet rs = null;

		try {
			c = DBUtil.getConnection();
			s = c.createStatement();
			String oldValue = SQL_SELECT_TEAMS_THAT_PLAYED_FIXTURE;
			SQL_SELECT_TEAMS_THAT_PLAYED_FIXTURE += String.valueOf(fixtureNum);
			rs = s.executeQuery(SQL_SELECT_TEAMS_THAT_PLAYED_FIXTURE);		
			SQL_SELECT_TEAMS_THAT_PLAYED_FIXTURE = oldValue;
			while (rs.next()) {
				/*retVal.add(new Game(rs.getInt("UtakmicaId"), rs.getInt("KOLO_IdKola"), 
							rs.getInt("GLAVNI_SUDIJA_SUDIJA_SudijaId"), rs.getDate("DatumUtakmice")));*/
				retVal.add(new String(String.valueOf(rs.getInt("IdKluba")/*+"!"+rs.getString("Naziv")*/)));
				//System.out.println(retVal.get(retVal.size()-1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs, s, c);
		}

		return retVal;
	}
	
	public List<Game> selectGamesBasedOnFixture(int fixtureNum) {
		List<Game> retVal = new ArrayList<Game>();
		Connection c = null;
		Statement s = null;
		ResultSet rs = null;

		try {
			c = DBUtil.getConnection();
			s = c.createStatement();
			String oldValue = SQL_SELECT_ALL_GAMES_BASED_ON_FIXTURE;
			SQL_SELECT_ALL_GAMES_BASED_ON_FIXTURE += String.valueOf(fixtureNum);
			rs = s.executeQuery(SQL_SELECT_ALL_GAMES_BASED_ON_FIXTURE);		
			SQL_SELECT_ALL_GAMES_BASED_ON_FIXTURE = oldValue;
			while (rs.next()) {
				retVal.add(new Game(rs.getInt("UtakmicaId"), rs.getInt("KOLO_IdKola"), 
							rs.getInt("GLAVNI_SUDIJA_SUDIJA_SudijaId"), rs.getDate("DatumUtakmice")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs, s, c);
		}

		return retVal;
	}
	
	public List<TeamInGame> selectTeamsWhoPlayedInGame(Game g) {
		List<TeamInGame> retVal = new ArrayList<TeamInGame>();
		Connection c = null;
		Statement s = null;
		ResultSet rs = null;

		try {
			c = DBUtil.getConnection();
			s = c.createStatement();
			String oldValue = SQL_SELECT_TEAMS_WHO_PLAYED_BASED_ON_GAME;
			SQL_SELECT_TEAMS_WHO_PLAYED_BASED_ON_GAME += String.valueOf(g.getUtakmicaId());
			rs = s.executeQuery(SQL_SELECT_TEAMS_WHO_PLAYED_BASED_ON_GAME);	
			SQL_SELECT_TEAMS_WHO_PLAYED_BASED_ON_GAME = oldValue;
			while (rs.next()) {
				retVal.add(new TeamInGame(rs.getInt("FUDBALSKI_KLUB_IdKluba"), rs.getInt("UTAKMICA_UtakmicaId"), 
							rs.getInt("BrojPostignutihGolova"), rs.getBoolean("IsDomacin"), rs.getInt("BrojPrimljenihGolova")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs, s, c);
		}

		return retVal;
	}
	
	public List<FootballClub> selectTeamInAMatch(int clubId) {
		List<FootballClub> retVal = new ArrayList<FootballClub>();
		Connection c = null;
		Statement s = null;
		ResultSet rs = null;

		try {
			c = DBUtil.getConnection();
			s = c.createStatement();
			String oldValue = SQL_SELECT_TEAM_WHO_PLAYED_BASED_ON_TEAMS;
			SQL_SELECT_TEAM_WHO_PLAYED_BASED_ON_TEAMS += String.valueOf(clubId);
			rs = s.executeQuery(SQL_SELECT_TEAM_WHO_PLAYED_BASED_ON_TEAMS);	
			SQL_SELECT_TEAM_WHO_PLAYED_BASED_ON_TEAMS = oldValue;
			while (rs.next()) {
				retVal.add(new FootballClub(rs.getInt("IdKluba"), rs.getString("Naziv"), 
							rs.getDate("DatumOsnivanja"), rs.getInt("BrojOsvojenihTrofeja"), rs.getInt("STADION_StadioId")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs, s, c);
		}

		return retVal;
	}
	
	@Override
	public List<Fixture> selectAllFixtures() {
		List<Fixture> retVal = new ArrayList<Fixture>();
		Connection c = null;
		Statement s = null;
		ResultSet rs = null;

		try {
			c = DBUtil.getConnection();
			s = c.createStatement();
			rs = s.executeQuery(SQL_SELECT_ALL_FIXTURES);

			while (rs.next()) {
				retVal.add(new Fixture(rs.getInt("BrojKola")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs, s, c);
		}

		return retVal;
	}
	
	@Override
	public List<MainReferee> selectAllMainReferees() {
		List<MainReferee> retVal = new ArrayList<MainReferee>();
		Connection c = null;
		Statement s = null;
		ResultSet rs = null;

		try {
			c = DBUtil.getConnection();
			s = c.createStatement();
			rs = s.executeQuery(SQL_SELECT_ALL_MAIN_REFEREES);

			while (rs.next()) {
				retVal.add(new MainReferee(rs.getInt("SUDIJA_SudijaId")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs, s, c);
		}

		return retVal;
	}

	@Override
	public List<Referee> selectAllReferees() {
		List<Referee> retVal = new ArrayList<Referee>();
		Connection c = null;
		Statement s = null;
		ResultSet rs = null;

		try {
			c = DBUtil.getConnection();
			s = c.createStatement();
			rs = s.executeQuery(SQL_SELECT_ALL_REFEREES);

			while (rs.next()) {
				retVal.add(new Referee(rs.getInt("SudijaId"), rs.getString("Ime"), rs.getString("Prezime"),
						rs.getInt("GodineRada"), rs.getInt("BrojUtakmica")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs, s, c);
		}

		return retVal;
	}

	@Override
	public List<Game> selectAllGames() {
		List<Game> retVal = new ArrayList<Game>();
		Connection c = null;
		Statement s = null;
		ResultSet rs = null;

		try {
			c = DBUtil.getConnection();
			s = c.createStatement();
			rs = s.executeQuery(SQL_SELECT_ALL_MAIN_GAMES);

			while (rs.next()) {
				retVal.add(new Game(rs.getInt("UtakmicaId"), rs.getInt("KOLO_IdKola"), rs.getInt("GLAVNI_SUDIJA_SUDIJA_SudijaId"),
						rs.getDate("DatumUtakmice")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs, s, c);
		}

		return retVal;
	}

	@Override
	public int insertGame(Game i) {
		int retVal = 0;
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			c = DBUtil.getConnection();
			Object values[] = { i.getKolo_idKola(), i.getGlavni_sudija_sudija_sudijaId(), i.getDatumUtakmice()};
			ps = DBUtil.prepareStatement(c, SQL_INSERT_GAME, true, values);
			retVal = ps.executeUpdate();
			if (retVal != 0) {
				rs = ps.getGeneratedKeys();
				if (rs.next()) {
					i.setUtakmicaId(rs.getInt(1));
				}
					
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs, ps, c);
		}
		games.add(i); //so I can access this value in newfixturegui window!!!
		return retVal;
	}

	@Override
	public int deleteGame(int i) {
		int retVal = 0;
		Connection c = null;
		PreparedStatement ps = null;

		try {
			//System.out.println("deleted1!");
			c = DBUtil.getConnection();
			Object values[] = { i };
			ps = DBUtil.prepareStatement(c, SQL_DELETE_GAME, false, values);
			retVal = ps.executeUpdate();
			//System.out.println("deleted2!");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(ps, c);
		}
		return retVal;
	}

	@Override
	public List<TeamInGame> selectAllTeamsInGame() {
		List<TeamInGame> retVal = new ArrayList<TeamInGame>();
		Connection c = null;
		Statement s = null;
		ResultSet rs = null;

		try {
			c = DBUtil.getConnection();
			s = c.createStatement();
			rs = s.executeQuery(SQL_SELECT_ALL_TEAMS_IN_GAME);

			while (rs.next()) {
				retVal.add(new TeamInGame(rs.getInt("FUDBALSKI_KLUB_IdKluba"), rs.getInt("UTAKMICA_UtakmicaId"), 
						rs.getInt("BrojPostignutihGolova"),
						rs.getBoolean("IsDomacin"),
						rs.getInt("BrojPrimljenihGolova")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs, s, c);
		}

		return retVal;
	}

	@Override
	public int insertTeamInGame(TeamInGame i) {
		int retVal = 0;
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			c = DBUtil.getConnection();
			Object values[] = { i.getFudbalski_klub_idKluba(), i.getUtakmica_utakmicaId(), 
					i.getBrojPostignutihGolova(), i.isDomacin(), i.getBrojPrimljenihGolova()};
			ps = DBUtil.prepareStatement(c, SQL_INSERT_TEAM_IN_GAME, false, values);
			retVal = ps.executeUpdate();
			/*if (retVal != 0) {
				rs = ps.getGeneratedKeys();
				if (rs.next())
					i.setUtakmicaId(rs.getInt(1));
			}*/
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs, ps, c);
		}

		return retVal;
	}

	@Override
	public int updateTeamInGame(int hGoals, int aGoals, int clubId, int gameId) {
		int retVal = 0;
		Connection c = null;
		PreparedStatement ps = null;

		try {
			c = DBUtil.getConnection();
			Object values[] = { hGoals, aGoals, clubId, gameId};
			ps = DBUtil.prepareStatement(c, SQL_UPDATE_TEAM_IN_GAME, false, values);
			retVal = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(ps, c);
		}
		return retVal;
	}

	@Override
	public int deleteTeamInGame(int i) {
		int retVal = 0;
		Connection c = null;
		PreparedStatement ps = null;

		try {
			c = DBUtil.getConnection();
			Object values[] = { i };
			ps = DBUtil.prepareStatement(c, SQL_DELETE_TEAM_IN_GAME, false, values);
			retVal = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(ps, c);
		}
		return retVal;
	}
}
