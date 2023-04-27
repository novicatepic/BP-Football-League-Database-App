package org.unibl.etf.gui;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import org.unibl.etf.classes.Game;
import org.unibl.etf.classes.Player;
import org.unibl.etf.classes.PlayerInGame;
import org.unibl.etf.classes.Worker;
import org.unibl.etf.util.DBUtil;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.awt.Font;

public class ShowPlayers extends JFrame {

	private JPanel contentPane;
	private JLabel[] homePlayerLabels;
	private JLabel[] awayPlayerLabels;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ShowPlayers frame = new ShowPlayers();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private static String SQL_SELECT_PLAYERS = "SELECT * FROM igrac WHERE ZAPOSLENIK_ZapolseniId=";
	private static String SQL_SELECT_WORKERS = "SELECT * FROM zaposlenik WHERE FUDBALSKI_KLUB_IdKluba=";
	private static String SQL_SELECT_WORKER = "SELECT * FROM zaposlenik WHERE ZapolseniId=";
	private static String SQL_SELECT_PLAYERS_IN_GAME = "SELECT * FROM igrac_na_utakmici WHERE ";
	private static String SQL_SELECT_SECOND_PART = "KLUB_NA_UTAKMICI_FUDBALSKI_KLUB_IdKluba=";
	private static String SQL_SELECT_THIRD_PART = " and KLUB_NA_UTAKMICI_UTAKMICA_UtakmicaId=";
	private static String SQL_SELECT_PLAYERS_WHO_ALREADY_PLAYED = "select i.IGRAC_ZAPOSLENIK_ZapolseniId from igrac_na_utakmici i "
			+ "left join klub_na_utakmici f on i.KLUB_NA_UTAKMICI_FUDBALSKI_KLUB_IdKluba=f.FUDBALSKI_KLUB_IdKluba "
			+ "left join igrac ig on i.IGRAC_ZAPOSLENIK_ZapolseniId=ig.ZAPOSLENIK_ZapolseniId "
			+ "where f.UTAKMICA_UtakmicaId=";
	
	private ShowPlayers frame;
	public void setFrame(ShowPlayers frame) {
		this.frame = frame;
	}
	private int homeClubId;
	private int awayClubId;
	private int gameId;
	public void setIds(int first, int second, int third) {
		homeClubId = first;
		awayClubId = second;
		gameId = third;
	}
	/**
	 * Create the frame.
	 */
	private JPanel homePlayersPanel;
	private JPanel awayPlayersPanel;
	public ShowPlayers() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1239, 773);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		homePlayersPanel = new JPanel();
		homePlayersPanel.setBounds(21, 108, 560, 496);
		contentPane.add(homePlayersPanel);
		homePlayersPanel.setLayout(new GridLayout(22, 1));
		awayPlayersPanel = new JPanel();
		awayPlayersPanel.setBounds(610, 108, 591, 496);
		contentPane.add(awayPlayersPanel);
		awayPlayersPanel.setLayout(new GridLayout(22, 1));
		
		JLabel lblNewLabel = new JLabel("HOME PLAYERS");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(175, 27, 208, 52);
		contentPane.add(lblNewLabel);
		
		JLabel lblAwayPlayers = new JLabel("AWAY PLAYERS");
		lblAwayPlayers.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblAwayPlayers.setHorizontalAlignment(SwingConstants.CENTER);
		lblAwayPlayers.setBounds(797, 27, 208, 52);
		contentPane.add(lblAwayPlayers);
		
		JButton homePlayersButton = new JButton("Add home player");
		homePlayersButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddPlayer addPlayer = new AddPlayer();
				addPlayer.setFrame(addPlayer);
				//List<Worker> workers = selectWorkersFromClub(homeClubId);
				List<Player> players = selectPlayersFromClub(homeClubId);
				List<Integer> playersInt = selectPlayersWhoAlreadyPlayed(gameId);
				List<Player> playersToPass = new ArrayList<>();
				for(Player p : players) {
					boolean found = false;
					for(Integer i : playersInt) {
						if(p.getZAPOSLENIK_ZapolseniId() == i) {
							found = true;
						}
					}
					if(!found) {
						playersToPass.add(p);
					}
				}
				addPlayer.setPlayers(playersToPass);
				addPlayer.setClubId(homeClubId);
				addPlayer.setGameId(gameId);
				addPlayer.setParentFrame(frame);
				addPlayer.setVisible(true);
			}
		});
		homePlayersButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		homePlayersButton.setBounds(70, 645, 454, 70);
		contentPane.add(homePlayersButton);
		
		JButton awayPlayersButton = new JButton("Add away player");
		awayPlayersButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddPlayer addPlayer = new AddPlayer();
				addPlayer.setFrame(addPlayer);
				List<Player> players = selectPlayersFromClub(awayClubId);
				List<Integer> playersInt = selectPlayersWhoAlreadyPlayed(gameId);
				List<Player> playersToPass = new ArrayList<>();
				for(Player p : players) {
					boolean found = false;
					for(Integer i : playersInt) {
						if(p.getZAPOSLENIK_ZapolseniId() == i) {
							found = true;
						}
					}
					if(!found) {
						playersToPass.add(p);
					}
				}
				addPlayer.setPlayers(playersToPass);
				addPlayer.setClubId(awayClubId);
				addPlayer.setGameId(gameId);
				addPlayer.setParentFrame(frame);
				addPlayer.setVisible(true);
			}
		});
		awayPlayersButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		awayPlayersButton.setBounds(686, 645, 454, 70);
		contentPane.add(awayPlayersButton);
		
		JLabel lblNewLabel_1 = new JLabel("Goals");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(161, 79, 60, 19);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Assists");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1_1.setBounds(241, 79, 60, 19);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Yellow?");
		lblNewLabel_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1_1_1.setBounds(311, 79, 60, 19);
		contentPane.add(lblNewLabel_1_1_1);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("Red?");
		lblNewLabel_1_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1_1_1_1.setBounds(381, 79, 60, 19);
		contentPane.add(lblNewLabel_1_1_1_1);
		
		JLabel lblNewLabel_1_1_1_1_1 = new JLabel("Started?");
		lblNewLabel_1_1_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1_1_1_1_1.setBounds(451, 79, 60, 19);
		contentPane.add(lblNewLabel_1_1_1_1_1);
		
		JLabel lblNewLabel_1_1_1_1_1_1 = new JLabel("Minutes");
		lblNewLabel_1_1_1_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1_1_1_1_1_1.setBounds(521, 79, 60, 19);
		contentPane.add(lblNewLabel_1_1_1_1_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Name");
		lblNewLabel_1_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1_2.setBounds(21, 79, 60, 19);
		contentPane.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_3 = new JLabel("LName");
		lblNewLabel_1_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1_3.setBounds(91, 79, 60, 19);
		contentPane.add(lblNewLabel_1_3);
		
		JLabel lblNewLabel_1_2_1 = new JLabel("Name");
		lblNewLabel_1_2_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_2_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1_2_1.setBounds(610, 79, 60, 19);
		contentPane.add(lblNewLabel_1_2_1);
		
		JLabel lblNewLabel_1_3_1 = new JLabel("LName");
		lblNewLabel_1_3_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_3_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1_3_1.setBounds(686, 79, 60, 19);
		contentPane.add(lblNewLabel_1_3_1);
		
		JLabel lblNewLabel_1_4 = new JLabel("Goals");
		lblNewLabel_1_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_4.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1_4.setBounds(756, 79, 60, 19);
		contentPane.add(lblNewLabel_1_4);
		
		JLabel lblNewLabel_1_1_2 = new JLabel("Assists");
		lblNewLabel_1_1_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1_1_2.setBounds(826, 79, 60, 19);
		contentPane.add(lblNewLabel_1_1_2);
		
		JLabel lblNewLabel_1_1_1_2 = new JLabel("Yellow?");
		lblNewLabel_1_1_1_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1_1_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1_1_1_2.setBounds(907, 79, 60, 19);
		contentPane.add(lblNewLabel_1_1_1_2);
		
		JLabel lblNewLabel_1_1_1_1_2 = new JLabel("Red?");
		lblNewLabel_1_1_1_1_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1_1_1_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1_1_1_1_2.setBounds(977, 79, 60, 19);
		contentPane.add(lblNewLabel_1_1_1_1_2);
		
		JLabel lblNewLabel_1_1_1_1_1_2 = new JLabel("Started?");
		lblNewLabel_1_1_1_1_1_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1_1_1_1_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1_1_1_1_1_2.setBounds(1047, 79, 60, 19);
		contentPane.add(lblNewLabel_1_1_1_1_1_2);
		
		JLabel lblNewLabel_1_1_1_1_1_1_1 = new JLabel("Minutes");
		lblNewLabel_1_1_1_1_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1_1_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1_1_1_1_1_1_1.setBounds(1128, 79, 60, 19);
		contentPane.add(lblNewLabel_1_1_1_1_1_1_1);
		
		JButton refreshButton = new JButton("Refresh");
		refreshButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				homePlayersPanel.removeAll();
				awayPlayersPanel.removeAll();
				initPlayers();
				frame.invalidate();
				frame.validate();
				frame.repaint();
			}
		});
		refreshButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		refreshButton.setBounds(1047, 10, 118, 44);
		contentPane.add(refreshButton);
		
		List<Worker> workers = selectWorkersFromClub(1);
		/*for(Worker w : workers) {
			System.out.println(w);
		}*/
		
		List<Player> players = selectPlayersFromClub(1);
		/*for(Player w : players) {
			System.out.println(w);
		}*/
		
		/*List<Integer> pl = selectPlayersWhoAlreadyPlayed(7);
		for(Integer i : pl) {
			System.out.println("PL: " + i);
		}*/
		
	}
	
	public void initPlayers() {
		List<PlayerInGame> homePlayers = selectPlayersInGame(homeClubId);
		List<PlayerInGame> awayPlayers = selectPlayersInGame(awayClubId);
		//System.out.println("HOME PLAY: " + homePlayers.size());
		int size1 = homePlayers.size();
		int size2 = awayPlayers.size();
		homePlayersPanel.setLayout(new GridLayout(size1, 8));
		awayPlayersPanel.setLayout(new GridLayout(size2, 8));
		for(int i=0; i<size1; i++) {
			List<Worker> worker = selectWorkerBasedOnId(homePlayers.get(i).getIgrac_zaposlenik_zapolseniId());
			JLabel l00 = new JLabel(worker.get(0).getIme());
			JLabel l01 = new JLabel(worker.get(0).getPrezime());
			JLabel l1 = new JLabel(String.valueOf(homePlayers.get(i).getBrojGolovaNaUtakmici()));
			JLabel l2 = new JLabel(String.valueOf(homePlayers.get(i).getBrojAsistencijaNaUtakmici()));
			JLabel l3 = new JLabel(String.valueOf(homePlayers.get(i).isDobioZuti()));
			JLabel l4 = new JLabel(String.valueOf(homePlayers.get(i).isDobioCrveni()));
			JLabel l5 = new JLabel(String.valueOf(homePlayers.get(i).isPoceoUtakmicu()));
			JLabel l6 = new JLabel(String.valueOf(homePlayers.get(i).getOdigraoMinuta()));
			l00.setHorizontalAlignment(SwingConstants.CENTER);
			l00.setVerticalAlignment(SwingConstants.CENTER);
			l01.setHorizontalAlignment(SwingConstants.CENTER);
			l01.setVerticalAlignment(SwingConstants.CENTER);
			l1.setHorizontalAlignment(SwingConstants.CENTER);
			l1.setVerticalAlignment(SwingConstants.CENTER);
			l2.setHorizontalAlignment(SwingConstants.CENTER);
			l2.setVerticalAlignment(SwingConstants.CENTER);
			l3.setHorizontalAlignment(SwingConstants.CENTER);
			l3.setVerticalAlignment(SwingConstants.CENTER);
			l4.setHorizontalAlignment(SwingConstants.CENTER);
			l4.setVerticalAlignment(SwingConstants.CENTER);
			l5.setHorizontalAlignment(SwingConstants.CENTER);
			l5.setVerticalAlignment(SwingConstants.CENTER);
			l6.setHorizontalAlignment(SwingConstants.CENTER);
			l6.setVerticalAlignment(SwingConstants.CENTER);
			//homePlayersPanel.add(new JLabel(String.valueOf(homePlayers.get(i).getIgrac_zaposlenik_zapolseniId())));
			homePlayersPanel.add(l00);
			homePlayersPanel.add(l01);
			homePlayersPanel.add(l1);
			homePlayersPanel.add(l2);
			homePlayersPanel.add(l3);
			homePlayersPanel.add(l4);
			//homePlayersPanel.add(new JLabel(String.valueOf(homePlayers.get(i).getKlub_na_utakmici_fudbalski_klub_idKluba())));
			//homePlayersPanel.add(new JLabel(String.valueOf(homePlayers.get(i).getKlub_na_utakmici_utakmica_utakmicaId())));
			homePlayersPanel.add(l5);
			homePlayersPanel.add(l6);
		}
		for(int i=0; i<size2; i++) {
			List<Worker> worker = selectWorkerBasedOnId(awayPlayers.get(i).getIgrac_zaposlenik_zapolseniId());
			JLabel l00 = new JLabel(worker.get(0).getIme());
			JLabel l01 = new JLabel(worker.get(0).getPrezime());
			JLabel l1 = new JLabel(String.valueOf(awayPlayers.get(i).getBrojGolovaNaUtakmici()));
			JLabel l2 = new JLabel(String.valueOf(awayPlayers.get(i).getBrojAsistencijaNaUtakmici()));
			JLabel l3 = new JLabel(String.valueOf(awayPlayers.get(i).isDobioZuti()));
			JLabel l4 = new JLabel(String.valueOf(awayPlayers.get(i).isDobioCrveni()));
			JLabel l5 = new JLabel(String.valueOf(awayPlayers.get(i).isPoceoUtakmicu()));
			JLabel l6 = new JLabel(String.valueOf(awayPlayers.get(i).getOdigraoMinuta()));
			l00.setHorizontalAlignment(SwingConstants.CENTER);
			l00.setVerticalAlignment(SwingConstants.CENTER);
			l01.setHorizontalAlignment(SwingConstants.CENTER);
			l01.setVerticalAlignment(SwingConstants.CENTER);
			l1.setHorizontalAlignment(SwingConstants.CENTER);
			l1.setVerticalAlignment(SwingConstants.CENTER);
			l2.setHorizontalAlignment(SwingConstants.CENTER);
			l2.setVerticalAlignment(SwingConstants.CENTER);
			l3.setHorizontalAlignment(SwingConstants.CENTER);
			l3.setVerticalAlignment(SwingConstants.CENTER);
			l4.setHorizontalAlignment(SwingConstants.CENTER);
			l4.setVerticalAlignment(SwingConstants.CENTER);
			l5.setHorizontalAlignment(SwingConstants.CENTER);
			l5.setVerticalAlignment(SwingConstants.CENTER);
			l6.setHorizontalAlignment(SwingConstants.CENTER);
			l6.setVerticalAlignment(SwingConstants.CENTER);
			//awayPlayersPanel.add(new JLabel(String.valueOf(awayPlayers.get(i).getIgrac_zaposlenik_zapolseniId())));
			awayPlayersPanel.add(l00);
			awayPlayersPanel.add(l01);
			awayPlayersPanel.add(l1);
			awayPlayersPanel.add(l2);
			awayPlayersPanel.add(l3);
			awayPlayersPanel.add(l4);
			//awayPlayersPanel.add(new JLabel(String.valueOf(awayPlayers.get(i).getKlub_na_utakmici_fudbalski_klub_idKluba())));
			//awayPlayersPanel.add(new JLabel(String.valueOf(awayPlayers.get(i).getKlub_na_utakmici_utakmica_utakmicaId())));
			awayPlayersPanel.add(l5);
			awayPlayersPanel.add(l6);
		}
		
	}
	
	/*private List<Worker> workers;
	private void populateWorkers(int id) {
		workers = selectWorkersFromClub(id);
	}*/
	
	private List<Integer> selectPlayersWhoAlreadyPlayed(int gameId) {
		List<Integer> retVal = new ArrayList<Integer>();
		Connection c = null;
		Statement s = null;
		ResultSet rs = null;

		try {
			c = DBUtil.getConnection();
			s = c.createStatement();
			String oldValue = SQL_SELECT_PLAYERS_WHO_ALREADY_PLAYED;
			SQL_SELECT_PLAYERS_WHO_ALREADY_PLAYED += String.valueOf(gameId);
			rs = s.executeQuery(SQL_SELECT_PLAYERS_WHO_ALREADY_PLAYED);		
			SQL_SELECT_PLAYERS_WHO_ALREADY_PLAYED = oldValue;
			while (rs.next()) {
				/*retVal.add(new Worker(rs.getInt("ZapolseniId"), rs.getString("Ime"), 
							rs.getString("Prezime"), rs.getDate("DatumZaposlenja"), rs.getInt("FUDBALSKI_KLUB_IdKluba")));*/
				retVal.add(rs.getInt("IGRAC_ZAPOSLENIK_ZapolseniId"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs, s, c);
		}

		return retVal;
	}
	
	public List<PlayerInGame> selectPlayersInGame(int club) {
		List<PlayerInGame> retVal = new ArrayList<PlayerInGame>();
		Connection c = null;
		Statement s = null;
		ResultSet rs = null;

		try {
			c = DBUtil.getConnection();
			s = c.createStatement();
			String oldValue = SQL_SELECT_SECOND_PART;
			String oldValue2 = SQL_SELECT_THIRD_PART;
			SQL_SELECT_SECOND_PART += String.valueOf(club);
			SQL_SELECT_THIRD_PART += String.valueOf(gameId);
			String query = SQL_SELECT_PLAYERS_IN_GAME + SQL_SELECT_SECOND_PART + SQL_SELECT_THIRD_PART;
			rs = s.executeQuery(query);		
			SQL_SELECT_SECOND_PART = oldValue;
			SQL_SELECT_THIRD_PART = oldValue2;
			while (rs.next()) {
				retVal.add(new PlayerInGame(rs.getInt("IGRAC_ZAPOSLENIK_ZapolseniId"), rs.getInt("BrojGolovaNaUtakmici"), 
							rs.getInt("BrojAsistencijaNaUtakmici"), rs.getBoolean("DobioZuti"), rs.getBoolean("DobioCrveni"), 
							rs.getInt("KLUB_NA_UTAKMICI_FUDBALSKI_KLUB_IdKluba"), rs.getInt("KLUB_NA_UTAKMICI_UTAKMICA_UtakmicaId"),
							rs.getBoolean("PoceoUtakmicu"), rs.getInt("OdigraoMinuta")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs, s, c);
		}

		return retVal;
	}
	
	public List<Worker> selectWorkerBasedOnId(int id) {
		List<Worker> retVal = new ArrayList<Worker>();
		Connection c = null;
		Statement s = null;
		ResultSet rs = null;

		try {
			c = DBUtil.getConnection();
			s = c.createStatement();
			String oldValue = SQL_SELECT_WORKER;
			SQL_SELECT_WORKER += String.valueOf(id);
			rs = s.executeQuery(SQL_SELECT_WORKER);		
			SQL_SELECT_WORKER = oldValue;
			while (rs.next()) {
				retVal.add(new Worker(rs.getInt("ZapolseniId"), rs.getString("Ime"), 
							rs.getString("Prezime"), rs.getDate("DatumZaposlenja"), rs.getInt("FUDBALSKI_KLUB_IdKluba")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs, s, c);
		}

		return retVal;
	}
	
	public List<Worker> selectWorkersFromClub(int clubId) {
		List<Worker> retVal = new ArrayList<Worker>();
		Connection c = null;
		Statement s = null;
		ResultSet rs = null;

		try {
			c = DBUtil.getConnection();
			s = c.createStatement();
			String oldValue = SQL_SELECT_WORKERS;
			SQL_SELECT_WORKERS += String.valueOf(clubId);
			rs = s.executeQuery(SQL_SELECT_WORKERS);		
			SQL_SELECT_WORKERS = oldValue;
			while (rs.next()) {
				retVal.add(new Worker(rs.getInt("ZapolseniId"), rs.getString("Ime"), 
							rs.getString("Prezime"), rs.getDate("DatumZaposlenja"), rs.getInt("FUDBALSKI_KLUB_IdKluba")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs, s, c);
		}

		return retVal;
	}
	
	public List<Player> selectPlayersFromClub(int clubId) {
		List<Worker> workers = selectWorkersFromClub(clubId);
		List<Player> retVal = new ArrayList<Player>();
		Connection c = null;
		Statement s = null;
		ResultSet rs = null;

		try {
			c = DBUtil.getConnection();
			s = c.createStatement();
			for(Worker w : workers) {
				String oldValue = SQL_SELECT_PLAYERS;
				SQL_SELECT_PLAYERS += String.valueOf(w.getZapolseniId());
				rs = s.executeQuery(SQL_SELECT_PLAYERS);		
				SQL_SELECT_PLAYERS = oldValue;
				while (rs.next()) {
					retVal.add(new Player(rs.getInt("ZAPOSLENIK_ZapolseniId"), rs.getInt("BrojOpreme"), 
								rs.getInt("BrojGolova"), rs.getInt("BrojAsistencija"), rs.getInt("BrojZutihKartona"), 
								rs.getInt("BrojCrvenihKartona")));
					retVal.get(retVal.size()-1).setDisplay(w.getIme()+" "+w.getPrezime());
				}
			}	
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs, s, c);
		}

		return retVal;
	}
}
