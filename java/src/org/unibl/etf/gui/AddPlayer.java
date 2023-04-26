package org.unibl.etf.gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
import org.unibl.etf.classes.Player;
import org.unibl.etf.classes.PlayerInGame;
import org.unibl.etf.classes.Stadium;
import org.unibl.etf.classes.TeamInGame;
import org.unibl.etf.dao.PlayerInGameDAO;
import org.unibl.etf.util.DBUtil;

import javax.swing.JCheckBox;

public class AddPlayer extends JFrame implements PlayerInGameDAO {
	private static final String SQL_INSERT_PLAYER = "INSERT INTO igrac_na_utakmici (IGRAC_ZAPOSLENIK_ZapolseniId, BrojGolovaNaUtakmici, "
			+ "BrojAsistencijaNaUtakmici, DobioZuti, DobioCrveni, KLUB_NA_UTAKMICI_FUDBALSKI_KLUB_IdKluba, "
			+ "KLUB_NA_UTAKMICI_UTAKMICA_UtakmicaId, PoceoUtakmicu, OdigraoMinuta) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String SQL_SELECT_ALL = "SELECT * FROM igrac_na_utakmici";

	
	private JPanel contentPane;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args)  {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddPlayer frame = new AddPlayer();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private JComboBox choosePlayerBox;
	private JTextField numGoalsField;
	private JTextField numAssistsField;
	private JTextField minutesPlayedField;

	private AddPlayer frame;
	public void setFrame(AddPlayer frame) {
		this.frame = frame;
	}
	
	private List<Player> players;
	public void setPlayers(List<Player> players) {
		this.players = players;
		for(Player p : players) {
			choosePlayerBox.addItem(p);
		}
	}
	private int clubId;
	private int gameId;
	
	public void setClubId(int id) {
		this.clubId = id;
	}
	
	public void setGameId(int id) {
		this.gameId = id;
	}
	
	private ShowPlayers parentFrame;
	public void setParentFrame(ShowPlayers frame) {
		parentFrame = frame;
	}
	
	/**
	 * Create the frame.
	 */
	public AddPlayer() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 656, 741);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Goals in game = ");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(35, 117, 172, 62);
		contentPane.add(lblNewLabel);
		
		JLabel lblFoundatioDate = new JLabel("Assists in game = ");
		lblFoundatioDate.setHorizontalAlignment(SwingConstants.CENTER);
		lblFoundatioDate.setBounds(35, 200, 172, 62);
		contentPane.add(lblFoundatioDate);
		
		numGoalsField = new JTextField();
		numGoalsField.setBounds(217, 128, 165, 41);
		contentPane.add(numGoalsField);
		numGoalsField.setColumns(10);
		
		numAssistsField = new JTextField();
		numAssistsField.setColumns(10);
		numAssistsField.setBounds(217, 211, 165, 41);
		contentPane.add(numAssistsField);

		
		choosePlayerBox = new JComboBox();
		choosePlayerBox.setBounds(217, 49, 132, 34);
		contentPane.add(choosePlayerBox);
		
		JLabel lblFixtureNum = new JLabel("Choose a player = ");
		lblFixtureNum.setHorizontalAlignment(SwingConstants.CENTER);
		lblFixtureNum.setBounds(35, 35, 172, 62);
		contentPane.add(lblFixtureNum);
		
		JCheckBox yellowCardCheckBox = new JCheckBox("Yellow Card");
		yellowCardCheckBox.setBounds(217, 274, 165, 54);
		contentPane.add(yellowCardCheckBox);
		
		JCheckBox redCardCheckBox = new JCheckBox("Red card");
		redCardCheckBox.setBounds(217, 341, 165, 54);
		contentPane.add(redCardCheckBox);
		
		JCheckBox chckbxStartedGame = new JCheckBox("Started game");
		chckbxStartedGame.setBounds(217, 411, 165, 54);
		contentPane.add(chckbxStartedGame);
		
		JLabel lblMinutesPlayed = new JLabel("Minutes played = ");
		lblMinutesPlayed.setHorizontalAlignment(SwingConstants.CENTER);
		lblMinutesPlayed.setBounds(35, 468, 172, 62);
		contentPane.add(lblMinutesPlayed);
		
		minutesPlayedField = new JTextField();
		minutesPlayedField.setColumns(10);
		minutesPlayedField.setBounds(217, 471, 165, 41);
		contentPane.add(minutesPlayedField);
		JButton saveButton = new JButton("SAVE");
		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Player p = (Player)choosePlayerBox.getSelectedItem();
				int goals = Integer.valueOf(numGoalsField.getText());
				int assists = Integer.valueOf(numAssistsField.getText());
				boolean gotYellow = yellowCardCheckBox.isSelected();
				boolean gotRed = redCardCheckBox.isSelected();
				boolean startedGame = chckbxStartedGame.isSelected();
				int minutesPlayed = Integer.valueOf(minutesPlayedField.getText());
				PlayerInGame pig = new PlayerInGame();
				pig.setBrojGolovaNaUtakmici(goals);
				pig.setBrojAsistencijaNaUtakmici(assists);
				pig.setDobioZuti(gotYellow);
				pig.setDobioCrveni(gotRed);
				pig.setPoceoUtakmicu(startedGame);
				pig.setOdigraoMinuta(minutesPlayed);
				pig.setIgrac_zaposlenik_zapolseniId(p.getZAPOSLENIK_ZapolseniId());
				pig.setKlub_na_utakmici_fudbalski_klub_idKluba(clubId);
				pig.setKlub_na_utakmici_utakmica_utakmicaId(gameId);
				insert(pig);
				frame.dispose();
				//parentFrame.dispose();
				try {
					Thread.sleep(500);
				} catch(Exception e1) {
					e1.printStackTrace();
				}
				/*ShowPlayers sp = new ShowPlayers();
				sp.setFrame(sp);
				sp.setVisible(true);*/
			}
		});
		saveButton.setBounds(71, 618, 494, 57);
		contentPane.add(saveButton);
	}

	@Override
	public List<PlayerInGame> selectAll() {
		List<PlayerInGame> retVal = new ArrayList<PlayerInGame>();
		Connection c = null;
		Statement s = null;
		ResultSet rs = null;

		try {
			c = DBUtil.getConnection();
			s = c.createStatement();
			rs = s.executeQuery(SQL_SELECT_ALL);

			while (rs.next()) {
				retVal.add(new PlayerInGame(rs.getInt("IGRAC_ZAPOSLENIK_ZapolseniId"), rs.getInt("BrojGolovaNaUtakmici"), rs.getInt("BrojAsistencijaNaUtakmici"),
						rs.getBoolean("DobioZuti"), rs.getBoolean("DobioCrveni"), rs.getInt("KLUB_NA_UTAKMICI_FUDBALSKI_KLUB_IdKluba"), rs.getInt("KLUB_NA_UTAKMICI_UTAKMICA_UtakmicaId"),
						rs.getBoolean("PoceoUtakmicu"), rs.getInt("OdigraoMinuta")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs, s, c);
		}

		return retVal;
	}

	@Override
	public int insert(PlayerInGame i) {
		int retVal = 0;
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			c = DBUtil.getConnection();
			Object values[] = { i.getIgrac_zaposlenik_zapolseniId(), i.getBrojGolovaNaUtakmici(), i.getBrojAsistencijaNaUtakmici(), 
					i.isDobioZuti(), i.isDobioCrveni(), i.getKlub_na_utakmici_fudbalski_klub_idKluba(), i.getKlub_na_utakmici_utakmica_utakmicaId(),
					i.isPoceoUtakmicu(), i.getOdigraoMinuta()};
			ps = DBUtil.prepareStatement(c, SQL_INSERT_PLAYER, true, values);
			retVal = ps.executeUpdate();
			/*if (retVal != 0) {
				rs = ps.getGeneratedKeys();
				if (rs.next())
					i.setStadionId(rs.getInt(1));
			}*/
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs, ps, c);
		}

		return retVal;
	}
}
