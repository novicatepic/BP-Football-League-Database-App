package org.unibl.etf.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.unibl.etf.classes.FootballClub;
import org.unibl.etf.classes.Stadium;
import org.unibl.etf.dao.FootballClubDAO;
import org.unibl.etf.util.DBUtil;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.GridLayout;
import javax.swing.JComboBox;
import java.awt.Font;

public class TeamGui extends JFrame implements FootballClubDAO {

	private static final String SQL_SELECT_ALL = "SELECT * FROM fudblaski_klub";
	private static final String SQL_INSERT = "INSERT INTO fudblaski_klub (IdKluba, Naziv, DatumOsnivanja, BrojOsvojenihTrofeja, StadionId) VALUES (null, ?, ?, ?, ?)";
	private static final String SQL_UPDATE = "UPDATE fudblaski_klub SET Naziv=?, DatumOsnivanja=?, BrojOsvojenihTrofeja=?, StadionId=? WHERE IdKluba=?";
	private static final String SQL_DELETE = "DELETE FROM fudblaski_klub WHERE IdKluba=?";
	private static final String SQL_SELECT_ALL_STADIUMS = "SELECT * FROM stadion";
	private JPanel contentPane;
	public static int NUM_TEAMS;
	private JLabel[][] labels;/* labels = new JLabel[NUM_TEAMS][5];*/
	private static TeamGui frame;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new TeamGui();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public List<FootballClub> getData() {
		return clubs;
	}
	
	/**
	 * Create the frame.
	 */
	List<FootballClub> clubs;
	List<Stadium> stadiums;
	public TeamGui() {
		clubs = selectAll();
		stadiums = selectAllStadiums();
		for(FootballClub s : clubs) {
			System.out.println(s);
		}
		NUM_TEAMS = clubs.size();
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 881, 595);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		labels = new JLabel[NUM_TEAMS][5];
		JButton addButton = new JButton("Add club");
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NewTeamWindow ntw = new NewTeamWindow();
				ntw.setTeamFrame(frame);
				ntw.passStadiums(stadiums);
				ntw.populateData();
				ntw.setVisible(true);
			}
		});
		addButton.setBounds(76, 474, 116, 44);
		contentPane.add(addButton);
		
		JButton changeButton = new JButton("Change club");
		changeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ChangeTeamWindow ctw = new ChangeTeamWindow();
				ctw.setTeamFrame(frame);
				ctw.passStadiums(stadiums);
				ctw.setVisible(true);
			}
		});
		changeButton.setBounds(323, 474, 116, 44);
		contentPane.add(changeButton);
		
		JButton deleteButton = new JButton("Delete club");
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DeleteClubWindow deleteClubWindow = new DeleteClubWindow();				
				deleteClubWindow.setStadiumFrame(frame);
				deleteClubWindow.populateData();
				deleteClubWindow.setVisible(true);
			}
		});
		deleteButton.setBounds(588, 474, 116, 44);
		contentPane.add(deleteButton);
		
		JPanel panel = new JPanel();
		panel.setBounds(57, 85, 800, 373);
		contentPane.add(panel);
		panel.setLayout(new GridLayout(NUM_TEAMS, 4));
		
		for(int i = 0; i < NUM_TEAMS; i++) {
			labels[i] = new JLabel[5];
		}
		
		for(int i = 0; i < NUM_TEAMS; i++) {
			for(int j = 0; j < 5; j++) {
				labels[i][j] = new JLabel();
			}
		}
		
		for(int i = 0; i < NUM_TEAMS; i++) {
			//for(int j = 0; j < 4; j++) {
				int j=0;
				//labels[i][j] = new JLabel();
				for(int k = 0; k < 5; k++) {
					switch(j) {
					case 0:
						labels[i][j].setText(String.valueOf(clubs.get(i).getIdKluba()));
						j++;
						break;
					case 1:
						labels[i][j].setText(clubs.get(i).getNaziv());
						j++;
						break;
					case 2:
						labels[i][j].setText(String.valueOf(clubs.get(i).getDatumOsnivanja()));
						j++;
						break;
					case 3:
						labels[i][j].setText(String.valueOf(clubs.get(i).getBrojOsvojenihTrofeja()));
						j++;
						break;
					case 4:
						labels[i][j].setText(String.valueOf(clubs.get(i).getStadionId()));
						break;
				}
				}
		}
		
		for(int i = 0; i < NUM_TEAMS; i++) {
			for(int j = 0; j < 5; j++) {
				panel.add(labels[i][j]);
			}
			
		}
		
		JLabel lblNewLabel = new JLabel("NAME");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(227, 60, 66, 27);
		contentPane.add(lblNewLabel);
		
		JLabel lblFoundationDate = new JLabel("DATE");
		lblFoundationDate.setHorizontalAlignment(SwingConstants.CENTER);
		lblFoundationDate.setBounds(361, 60, 85, 27);
		contentPane.add(lblFoundationDate);
		
		JLabel lblNumtrophies = new JLabel("NUM_TROPHIES");
		lblNumtrophies.setHorizontalAlignment(SwingConstants.CENTER);
		lblNumtrophies.setBounds(506, 60, 85, 27);
		contentPane.add(lblNumtrophies);
		
		JLabel lblStadiumname = new JLabel("STADIUM_NAME");
		lblStadiumname.setHorizontalAlignment(SwingConstants.CENTER);
		lblStadiumname.setBounds(672, 60, 85, 27);
		contentPane.add(lblStadiumname);
		
		JLabel lblNewLabel_1 = new JLabel("TEAMS");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(57, 10, 671, 40);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblId = new JLabel("Id");
		lblId.setHorizontalAlignment(SwingConstants.CENTER);
		lblId.setBounds(78, 60, 66, 27);
		contentPane.add(lblId);
		
		/*for(int i = 0; i < NUM_TEAMS; i++) {
			for(int j = 0; j < 4; j++) {
				chooseTeamBox.addItem(new String(labels[i][j].getText()));
			}
		}*/
		
	}

	@Override
	public List<FootballClub> selectAll() {
		List<FootballClub> retVal = new ArrayList<FootballClub>();
		Connection c = null;
		Statement s = null;
		ResultSet rs = null;

		try {
			c = DBUtil.getConnection();
			s = c.createStatement();
			rs = s.executeQuery(SQL_SELECT_ALL);

			while (rs.next()) {
				retVal.add(new FootballClub(rs.getInt("IdKluba"), rs.getString("Naziv"), rs.getDate("DatumOsnivanja"),
						rs.getInt("BrojOsvojenihTrofeja"), rs.getInt("StadionId")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs, s, c);
		}

		return retVal;
	}

	@Override
	public int insert(FootballClub i) {

		int retVal = 0;
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			c = DBUtil.getConnection();
			Object values[] = { i.getNaziv(), i.getDatumOsnivanja(), i.getBrojOsvojenihTrofeja(), i.getStadionId()};
			ps = DBUtil.prepareStatement(c, SQL_INSERT, true, values);
			retVal = ps.executeUpdate();
			if (retVal != 0) {
				rs = ps.getGeneratedKeys();
				if (rs.next())
					i.setStadionId(rs.getInt(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs, ps, c);
		}

		return retVal;
	}

	@Override
	public int update(FootballClub i) {
		int retVal = 0;
		Connection c = null;
		PreparedStatement ps = null;

		try {
			c = DBUtil.getConnection();
			Object values[] = { i.getNaziv(), i.getDatumOsnivanja(), i.getBrojOsvojenihTrofeja(), i.getStadionId(), i.getIdKluba()};
			ps = DBUtil.prepareStatement(c, SQL_UPDATE, false, values);
			retVal = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(ps, c);
		}
		return retVal;
	}

	@Override
	public int delete(FootballClub i) {
		int retVal = 0;
		Connection c = null;
		PreparedStatement ps = null;

		try {
			c = DBUtil.getConnection();
			Object values[] = { i.getIdKluba() };
			ps = DBUtil.prepareStatement(c, SQL_DELETE, false, values);
			retVal = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(ps, c);
		}
		return retVal;
	}
	
	public List<Stadium> selectAllStadiums() {
		List<Stadium> retVal = new ArrayList<Stadium>();
		Connection c = null;
		Statement s = null;
		ResultSet rs = null;

		try {
			c = DBUtil.getConnection();
			s = c.createStatement();
			rs = s.executeQuery(SQL_SELECT_ALL_STADIUMS);

			while (rs.next()) {
				retVal.add(new Stadium(rs.getInt("StadionId"), rs.getString("Naziv"), rs.getInt("Kapacitet"),
						rs.getString("Grad")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs, s, c);
		}

		return retVal;
	}
}
