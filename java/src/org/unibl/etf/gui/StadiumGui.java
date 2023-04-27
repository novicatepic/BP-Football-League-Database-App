package org.unibl.etf.gui;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JScrollPane;

public class StadiumGui extends JFrame implements StadiumDAO {

	private static final String SQL_SELECT_ALL = "SELECT * FROM stadion";
	private static final String SQL_INSERT = "INSERT INTO stadion (StadionId, Naziv, Kapacitet, Grad) VALUES (null, ?, ?, ?)";
	private static final String SQL_UPDATE = "UPDATE stadion SET Naziv=?, Kapacitet=?, Grad=? WHERE StadionId=?";
	private static final String SQL_DELETE = "DELETE FROM stadion WHERE StadionId=?";
	
	private JPanel contentPane;
	private static int NUM_TEAMS;
	private static StadiumGui frame;
	private JLabel[][] labels;/* = new JLabel[NUM_TEAMS][4];*/
	private JButton[][] buttons;
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

	public void setFrame(StadiumGui frame2) {
		frame = frame2;
	}
	
	public List<Stadium> getData() {
		return stadiums;
	}
	
	/**
	 * Create the frame.
	 */
	List<Stadium> stadiums;
	public StadiumGui() {
		stadiums = selectAll();
		/*for(Stadium s : stadiums) {
			System.out.println(s);
		}*/
		NUM_TEAMS = stadiums.size();

		labels = new JLabel[NUM_TEAMS][4];
		buttons = new JButton[NUM_TEAMS][2];
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				setBounds(100, 100, 1076, 799);
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
						nsw.setStadiumFrame(frame);
						nsw.setNewStadiumFrame(nsw);
						nsw.setVisible(true);
					}
				});
				addButton.setBounds(71, 708, 116, 44);
				contentPane.add(addButton);
				
				panel = new JPanel();
				panel.setBounds(57, 79, 939, 632);
				contentPane.add(panel);
				panel.setLayout(new GridLayout(NUM_TEAMS, 6));
				
				for(int i = 0; i < NUM_TEAMS; i++) {
					labels[i] = new JLabel[4];
				}
				
				for(int i = 0; i < NUM_TEAMS; i++) {
					buttons[i] = new JButton[2];
				}
				
				for(int i = 0; i < NUM_TEAMS; i++) {
					for(int j = 0; j < 4; j++) {
						labels[i][j] = new JLabel();
					}
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
					//for(int j = 0; j < 4; j++) {
						int j=0;
						//labels[i][j] = new JLabel();
						for(int k = 0; k < 4; k++) {
							switch(j) {
							case 0:
								labels[i][j].setText(String.valueOf(stadiums.get(i).getStadionId()));
								j++;
								break;
							case 1:
								labels[i][j].setText(stadiums.get(i).getNaziv());
								j++;
								break;
							case 2:
								labels[i][j].setText(String.valueOf(stadiums.get(i).getKapacitet()));
								j++;
								break;
							case 3:
								labels[i][j].setText(stadiums.get(i).getGrad());
								break;
						}
						}
				}
				
				for(int i = 0; i < NUM_TEAMS; i++) {
					for(int j = 0; j < 6; j++) {
						if(j<4) {
							panel.add(labels[i][j]);
						} else {
							panel.add(buttons[i][j-4]);
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
									ChangeStadiumWindow csw = new ChangeStadiumWindow();
									csw.setStadiumFrame(frame);
									csw.setChangeStadiumFrame(csw);
									csw.setStadiumId(Integer.valueOf(labels[temp][0].getText()));
									csw.setData();
									csw.setVisible(true);
								}
							});
						} else {
							buttons[i][j].addActionListener(new ActionListener() {						
								@Override
								public void actionPerformed(ActionEvent e) {
									int id = Integer.valueOf(labels[temp][0].getText());
									
									Stadium s = null;
									for(Stadium stad : stadiums) {
										if(stad.getStadionId() == id) {
											s = stad;
										}
									}
									if(s != null) {
										frame.delete(s);
										try {
											Thread.sleep(1000);
										} catch(InterruptedException ex) {
											ex.printStackTrace();
										}
										frame.dispose();
										StadiumGui sg = new StadiumGui();
										sg.setFrame(sg);
										sg.setVisible(true);
									}
								}
							});
						}
					}
				}
				
				JLabel lblNewLabel = new JLabel("ID");
				lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
				lblNewLabel.setBounds(57, 55, 66, 27);
				contentPane.add(lblNewLabel);
				
				JLabel lblFoundationDate = new JLabel("NAME");
				lblFoundationDate.setHorizontalAlignment(SwingConstants.CENTER);
				lblFoundationDate.setBounds(205, 55, 85, 27);
				contentPane.add(lblFoundationDate);
				
				JLabel lblNumtrophies = new JLabel("CAPACITY");
				lblNumtrophies.setHorizontalAlignment(SwingConstants.CENTER);
				lblNumtrophies.setBounds(382, 55, 85, 27);
				contentPane.add(lblNumtrophies);
				
				JLabel lblStadiumname = new JLabel("TOWN");
				lblStadiumname.setHorizontalAlignment(SwingConstants.CENTER);
				lblStadiumname.setBounds(544, 55, 85, 27);
				contentPane.add(lblStadiumname);
				
				JLabel lblNewLabel_1 = new JLabel("STADIUMS");
				lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
				lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
				lblNewLabel_1.setBounds(56, 11, 611, 27);
				contentPane.add(lblNewLabel_1);			
				
				//scrollPane.setBounds(50, 30, 300, 50);
				//scrollPane.add(contentPane);
		        
	}
	private JScrollPane scrollPane;
	private JPanel panel;
	public void setScrollBar() {
		/*Container container = panel; // get the container that holds the grid
		GridLayout gridLayout = (GridLayout) container.getLayout();

		// get the current size of the container
		Dimension containerSize = container.getSize();

		// calculate the number of rows that can fit in the container
		Component component = container.getComponent(0); // use the first component in the grid
		Dimension componentPreferredSize = component.getPreferredSize();
		int numRows = containerSize.height / componentPreferredSize.height;

		// update the grid layout with the new number of rows
		gridLayout.setRows(numRows);

		// repaint the container to update the display
		container.repaint();*/
		//scrollPane = new JScrollPane(contentPane, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		//frame.add(scrollPane);
		//scrollPane.add();
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
	public int insert(Stadium i) {
		
		int retVal = 0;
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			c = DBUtil.getConnection();
			Object values[] = { i.getNaziv(), i.getKapacitet(), i.getGrad() };
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
	public int update(Stadium i) {
		int retVal = 0;
		Connection c = null;
		PreparedStatement ps = null;

		try {
			c = DBUtil.getConnection();
			Object values[] = { i.getNaziv(), i.getKapacitet(), i.getGrad(), i.getStadionId()};
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
	public int delete(Stadium i) {
		int retVal = 0;
		Connection c = null;
		PreparedStatement ps = null;

		try {
			c = DBUtil.getConnection();
			Object values[] = { i.getStadionId() };
			ps = DBUtil.prepareStatement(c, SQL_DELETE, false, values);
			retVal = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(ps, c);
		}
		return retVal;
	}
}
