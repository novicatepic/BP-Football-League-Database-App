package org.unibl.etf.gui;

import java.awt.EventQueue;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import org.unibl.etf.classes.Stadium;
import org.unibl.etf.dao.StadiumDAO;
import org.unibl.etf.util.DBUtil;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class StadiumGui extends JFrame implements StadiumDAO {

	private static final String SQL_SELECT_ALL = "SELECT * FROM stadion";
	private static final String SQL_INSERT = "INSERT INTO stadion (StadionId, Naziv, Kapacitet, Grad) VALUES (null, ?, ?, ?)";
	private static final String SQL_UPDATE = "UPDATE stadion SET Naziv=?, Kapacitet=?, Grad=? WHERE StadionId=?";
	private static final String SQL_DELETE = "DELETE FROM stadion WHERE StadionId=?";
	
	private JPanel contentPane;
	public static final int NUM_TEAMS = 20;

	private JLabel[][] labels = new JLabel[NUM_TEAMS][4];
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StadiumGui frame = new StadiumGui();
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
	public StadiumGui() {
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				setBounds(100, 100, 752, 595);
				setResizable(false);
				setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				contentPane = new JPanel();
				contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

				setContentPane(contentPane);
				contentPane.setLayout(null);
				
				JButton addButton = new JButton("Add stadium");
				addButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						NewStadiumWindow nsw = new NewStadiumWindow();
						nsw.setVisible(true);
					}
				});
				addButton.setBounds(76, 474, 116, 44);
				contentPane.add(addButton);
				
				JButton changeButton = new JButton("Change stadium");
				changeButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						ChangeStadiumWindow csw = new ChangeStadiumWindow();
						csw.setVisible(true);
						
					}
				});
				changeButton.setBounds(323, 474, 116, 44);
				contentPane.add(changeButton);
				
				JButton deleteButton = new JButton("Delete stadium");
				deleteButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						DeleteStadiumWindow dsw = new DeleteStadiumWindow();
						dsw.setVisible(true);
					}
				});
				deleteButton.setBounds(588, 474, 116, 44);
				contentPane.add(deleteButton);
				
				JPanel panel = new JPanel();
				panel.setBounds(57, 92, 671, 366);
				contentPane.add(panel);
				panel.setLayout(new GridLayout(NUM_TEAMS, 4));
				
				for(int i = 0; i < NUM_TEAMS; i++) {
					labels[i] = new JLabel[4];
				}
				
				for(int i = 0; i < NUM_TEAMS; i++) {
					for(int j = 0; j < 4; j++) {
						labels[i][j] = new JLabel();
						labels[i][j].setText(String.valueOf(i+j));
						labels[i][j].setHorizontalAlignment(JLabel.CENTER);
					}
				}
				
				for(int i = 0; i < NUM_TEAMS; i++) {
					for(int j = 0; j < 4; j++) {
						panel.add(labels[i][j]);
					}
					
				}
				
				JLabel lblNewLabel = new JLabel("ID");
				lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
				lblNewLabel.setBounds(82, 55, 66, 27);
				contentPane.add(lblNewLabel);
				
				JLabel lblFoundationDate = new JLabel("NAME");
				lblFoundationDate.setHorizontalAlignment(SwingConstants.CENTER);
				lblFoundationDate.setBounds(252, 55, 85, 27);
				contentPane.add(lblFoundationDate);
				
				JLabel lblNumtrophies = new JLabel("CAPACITY");
				lblNumtrophies.setHorizontalAlignment(SwingConstants.CENTER);
				lblNumtrophies.setBounds(413, 55, 85, 27);
				contentPane.add(lblNumtrophies);
				
				JLabel lblStadiumname = new JLabel("TOWN");
				lblStadiumname.setHorizontalAlignment(SwingConstants.CENTER);
				lblStadiumname.setBounds(582, 55, 85, 27);
				contentPane.add(lblStadiumname);
				
				JLabel lblNewLabel_1 = new JLabel("STADIUMS");
				lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
				lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
				lblNewLabel_1.setBounds(56, 11, 611, 27);
				contentPane.add(lblNewLabel_1);
				
				List<Stadium> stadiums = selectAll();
				for(Stadium s : stadiums) {
					System.out.println(s);
				}
				
	}

	@Override
	public List<Stadium> selectAll() {
		List<Stadium> retVal = new ArrayList<Stadium>();
		Connection c = null;
		Statement s = null;
		ResultSet rs = null;

		try {
			c = DBUtil.getConnection();
			s = c.createStatement();
			rs = s.executeQuery(SQL_SELECT_ALL);

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

	@Override
	public int insert(Stadium s) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Stadium s) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(Stadium s) {
		// TODO Auto-generated method stub
		return 0;
	}

}
