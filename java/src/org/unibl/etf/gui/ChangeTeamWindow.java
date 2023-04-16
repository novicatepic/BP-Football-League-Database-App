package org.unibl.etf.gui;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import org.unibl.etf.classes.FootballClub;
import org.unibl.etf.classes.Stadium;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.List;
import java.awt.Font;

public class ChangeTeamWindow extends JFrame {

	private JPanel contentPane;
	private JTextField nameField;
	private JTextField dateField;
	private JTextField trophiesWonField;
	private JTextField clubIdField;
	private JTextField stadiumIdField;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChangeTeamWindow frame = new ChangeTeamWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	private List<Stadium> stadiums;
	public void passStadiums(List<Stadium> stadiums) {
		this.stadiums = stadiums;
	}
	private TeamGui frame;
	public void setTeamFrame(TeamGui frame) {
		this.frame = frame;
	}
	
	/**
	 * Create the frame.
	 */
	public ChangeTeamWindow() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 723, 503);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Name = ");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(35, 92, 172, 62);
		contentPane.add(lblNewLabel);
		
		JLabel lblFoundatioDate = new JLabel("Foundation date = ");
		lblFoundatioDate.setHorizontalAlignment(SwingConstants.CENTER);
		lblFoundatioDate.setBounds(35, 164, 172, 62);
		contentPane.add(lblFoundatioDate);
		
		JLabel lblNumberOfTrophies = new JLabel("Number of trophies won = ");
		lblNumberOfTrophies.setHorizontalAlignment(SwingConstants.CENTER);
		lblNumberOfTrophies.setBounds(35, 236, 172, 62);
		contentPane.add(lblNumberOfTrophies);
		
		nameField = new JTextField();
		nameField.setBounds(254, 103, 165, 41);
		contentPane.add(nameField);
		nameField.setColumns(10);
		
		dateField = new JTextField();
		dateField.setColumns(10);
		dateField.setBounds(254, 175, 165, 41);
		contentPane.add(dateField);
		
		trophiesWonField = new JTextField();
		trophiesWonField.setColumns(10);
		trophiesWonField.setBounds(254, 247, 165, 41);
		contentPane.add(trophiesWonField);
		
		JComboBox chooseStadiumBox = new JComboBox();
		chooseStadiumBox.setBounds(649, 85, 29, 21);
		contentPane.add(chooseStadiumBox);
		
		JLabel lblChooseStadium = new JLabel("Choose stadium = ");
		lblChooseStadium.setHorizontalAlignment(SwingConstants.CENTER);
		lblChooseStadium.setBounds(441, 64, 172, 62);
		contentPane.add(lblChooseStadium);
		
		JButton saveButton = new JButton("SAVE");
		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					List<FootballClub> data = frame.getData();
					FootballClub s = null;
					for(FootballClub stad : data) {
						if(stad.getIdKluba() == Integer.valueOf(clubIdField.getText())) {
							s = stad;
						}
					}
					if(s != null) {
						boolean anything = false;
						if(!"".equals(nameField.getText())) {
							s.setNaziv(nameField.getText());
							anything = true;
						}
						if(!"".equals(dateField.getText())) {
							String[] dateFieldParse = dateField.getText().split("-");
							if(dateFieldParse.length != 3) {
								throw new Exception("Problem!");
							}
							Date date = Date.valueOf(dateField.getText());
							s.setDatumOsnivanja(date);
							anything = true;
						}
						if(!"".equals(trophiesWonField.getText())) {
							s.setBrojOsvojenihTrofeja(Integer.valueOf(trophiesWonField.getText()));
							anything = true;
						}
						if(!"".equals(stadiumIdField.getText())) {
							if(stadiums.contains(Integer.valueOf(stadiumIdField.getText()))) {
								s.setStadionId(Integer.valueOf(stadiumIdField.getText()));
							} else {
								throw new Exception("Nepostojeci stadion");
							}
							//s.setGrad(trophiesWonField.getText());
							anything = true;
						}
						if(anything) {
							frame.update(s);
							try {
								Thread.sleep(1000);
							} catch(InterruptedException ex) {
								ex.printStackTrace();
							}
							frame.dispose();
							TeamGui sg = new TeamGui();
							sg.setVisible(true);
						}
					}
				} catch(Exception e12) {
					e12.printStackTrace();
				}
				
			}
		});
		saveButton.setBounds(35, 399, 643, 57);
		contentPane.add(saveButton);
		
		JLabel lblNewLabel_1 = new JLabel("CHANGE CLUB");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(35, 10, 642, 29);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNumberOfTrophies_1 = new JLabel("Id = ");
		lblNumberOfTrophies_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNumberOfTrophies_1.setBounds(35, 41, 172, 62);
		contentPane.add(lblNumberOfTrophies_1);
		
		clubIdField = new JTextField();
		clubIdField.setColumns(10);
		clubIdField.setBounds(254, 52, 165, 41);
		contentPane.add(clubIdField);
		
		JLabel lblNumberOfTrophies_1_1 = new JLabel("Stadium Id = ");
		lblNumberOfTrophies_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNumberOfTrophies_1_1.setBounds(35, 304, 172, 62);
		contentPane.add(lblNumberOfTrophies_1_1);
		
		stadiumIdField = new JTextField();
		stadiumIdField.setColumns(10);
		stadiumIdField.setBounds(254, 314, 165, 41);
		contentPane.add(stadiumIdField);
	}
}
