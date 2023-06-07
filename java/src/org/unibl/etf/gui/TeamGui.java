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
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
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

	private static final String SQL_SELECT_ALL = "SELECT * FROM fudbalski_klub";
	private static final String SQL_INSERT = "INSERT INTO fudbalski_klub (IdKluba, Naziv, DatumOsnivanja, BrojOsvojenihTrofeja, STADION_StadioId) VALUES (null, ?, ?, ?, ?)";
	private static final String SQL_UPDATE = "UPDATE fudbalski_klub SET Naziv=?, DatumOsnivanja=?, BrojOsvojenihTrofeja=?, STADION_StadioId=? WHERE IdKluba=?";
	private static final String SQL_DELETE = "DELETE FROM fudbalski_klub WHERE IdKluba=?";
	private static final String SQL_SELECT_ALL_STADIUMS = "SELECT * FROM stadion";
	private JPanel contentPane;
	public static int NUM_TEAMS;
	private JLabel[][] labels;/* labels = new JLabel[NUM_TEAMS][5];*/
	private JButton[][] buttons;
	private static TeamGui frame;

	public void setFrame(TeamGui frame2) {
		frame= frame2;
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
		/*for(FootballClub s : clubs) {
			System.out.println(s);
		}*/
		/*for(Stadium s : stadiums) {
			System.out.println(s);
		}*/
		NUM_TEAMS = clubs.size();
		buttons = new JButton[NUM_TEAMS][2];
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1151, 786);
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
				ntw.setTeamFrame(ntw);
				ntw.passStadiums(stadiums);
				ntw.populateData();
				ntw.setVisible(true);
			}
		});
		addButton.setBounds(57, 695, 116, 44);
		contentPane.add(addButton);
		
		JPanel panel = new JPanel();
		panel.setBounds(57, 85, 1070, 611);
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
			buttons[i] = new JButton[2];
		}
		
		for(int i = 0; i < NUM_TEAMS; i++) {
			for(int j = 0; j < 2; j++) {
				buttons[i][j] = new JButton();
				if(j==0) {
					buttons[i][j].setText("CHANGE");
				} else {
					buttons[i][j].setText("DELETE");
				}
			}
		}
		
		for(int i = 0; i < NUM_TEAMS; i++) {
				int j=0;
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
						for(Stadium s : stadiums) {
							if(s.getStadionId() == clubs.get(i).getStadionId()) {
								labels[i][j].setText(s.getNaziv());
							}
						}
						//labels[i][j].setText(String.valueOf(clubs.get(i).getStadionId()));
						break;
				}
				}
		}
		
		for(int i = 0; i < NUM_TEAMS; i++) {
			for(int j = 0; j < 7; j++) {
				if(j<5) {
					panel.add(labels[i][j]);
				} else {
					panel.add(buttons[i][j-5]);
				}
			}		
		}
		
		for(int i = 0; i < NUM_TEAMS; i++) {
			for(int j = 0; j < 2; j++) {
				final int temp = i;
				if(j == 0) {							
					buttons[i][j].addActionListener(new ActionListener() {						
						@Override
						public void actionPerformed(ActionEvent e) {
							/*for(Stadium s : stadiums) {
								System.out.println(s);
							}*/
							ChangeTeamWindow ctw = new ChangeTeamWindow();
							ctw.setTeamFrame(frame);
							ctw.setTeamId(Integer.valueOf(labels[temp][0].getText()));
							ctw.passStadiums(stadiums);
							ctw.addData();
							ctw.setTeamChangeFrame(ctw);
							//ctw.clickButton();
							ctw.setVisible(true);
						}
					});
				} else {
					buttons[i][j].addActionListener(new ActionListener() {						
						@Override
						public void actionPerformed(ActionEvent e) {
							int id = Integer.valueOf(labels[temp][0].getText());

							FootballClub s = null;
							for(FootballClub stad : clubs) {
								if(stad.getIdKluba() == Integer.valueOf(id)) {
									s = stad;
								}
							}
							if(s != null) {
								frame.delete(s);
								try {
									Thread.sleep(1000);
								} catch(InterruptedException ex) {
									ErrorBox errorBox = new ErrorBox();
									errorBox.setVisible(true);
									errorBox.setText(ex.getMessage());
									ex.printStackTrace();
								}
								frame.dispose();
								TeamGui sg = new TeamGui();
								sg.setFrame(sg);
								sg.setVisible(true);
							}
						}
					});
				}
			}
		}
		
		JLabel lblNewLabel = new JLabel("NAME");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(207, 60, 66, 27);
		contentPane.add(lblNewLabel);
		
		JLabel lblFoundationDate = new JLabel("DATE");
		lblFoundationDate.setHorizontalAlignment(SwingConstants.CENTER);
		lblFoundationDate.setBounds(341, 60, 85, 27);
		contentPane.add(lblFoundationDate);
		
		JLabel lblNumtrophies = new JLabel("NUM_TROPHIES");
		lblNumtrophies.setHorizontalAlignment(SwingConstants.CENTER);
		lblNumtrophies.setBounds(486, 60, 85, 27);
		contentPane.add(lblNumtrophies);
		
		JLabel lblStadiumname = new JLabel("STADIUM_NAME");
		lblStadiumname.setHorizontalAlignment(SwingConstants.CENTER);
		lblStadiumname.setBounds(633, 60, 107, 27);
		contentPane.add(lblStadiumname);
		
		JLabel lblNewLabel_1 = new JLabel("TEAMS");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(57, 10, 671, 40);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblId = new JLabel("Id");
		lblId.setHorizontalAlignment(SwingConstants.CENTER);
		lblId.setBounds(57, 60, 66, 27);
		contentPane.add(lblId);	
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
						rs.getInt("BrojOsvojenihTrofeja"), rs.getInt("STADION_StadioId")));
			}
		} catch (SQLException e) {
			ErrorBox errorBox = new ErrorBox();
			errorBox.setVisible(true);
			errorBox.setText(e.getMessage());
			e.printStackTrace();
		} finally {
			DBUtil.close(rs, s, c);
		}

		return retVal;
	}
	
	public void loadData() {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(new File("./resources/klubovi2.txt")));
			String read = "";
			ArrayList<FootballClub> teams = new ArrayList<>();
			while((read=reader.readLine()) != null) {
				String[] split = read.split("#");
				FootballClub s = new FootballClub();
				s.setNaziv(split[0]);
				s.setDatumOsnivanja(Date.valueOf(split[1]));
				s.setBrojOsvojenihTrofeja(Integer.valueOf(split[2]));
				s.setStadionId(Integer.valueOf(split[3]));
				teams.add(s);
			}
			for(FootballClub t : teams) {
				insert(t);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch(IOException e1) {
			e1.printStackTrace();
		}
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
			ErrorBox errorBox = new ErrorBox();
			errorBox.setVisible(true);
			errorBox.setText(e.getMessage());
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
			ErrorBox errorBox = new ErrorBox();
			errorBox.setVisible(true);
			errorBox.setText(e.getMessage());
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
			ErrorBox errorBox = new ErrorBox();
			errorBox.setVisible(true);
			errorBox.setText(e.getMessage());
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
			ErrorBox errorBox = new ErrorBox();
			errorBox.setVisible(true);
			errorBox.setText(e.getMessage());
			e.printStackTrace();
		} finally {
			DBUtil.close(rs, s, c);
		}

		return retVal;
	}
}
