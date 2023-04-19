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
	private JButton saveButton;
	/**
	 * Launch the application.
	 */
	private int id;
	public void setTeamId(int id) {
		this.id = id;
	}
	
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
	private JComboBox chooseStadiumBox;
	public void setTeamFrame(TeamGui frame) {
		this.frame = frame;
	}
	
	public void addStadiumsToCB() {
		for(Stadium s : stadiums) {
			chooseStadiumBox.addItem(s);
		}
	}
	
	public void clickButton() {
		saveButton.doClick();
	}
	
	/**
	 * Create the frame.
	 */
	public ChangeTeamWindow() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 723, 448);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Name = ");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(35, 64, 172, 62);
		contentPane.add(lblNewLabel);
		
		JLabel lblFoundatioDate = new JLabel("Foundation date = ");
		lblFoundatioDate.setHorizontalAlignment(SwingConstants.CENTER);
		lblFoundatioDate.setBounds(35, 136, 172, 62);
		contentPane.add(lblFoundatioDate);
		
		JLabel lblNumberOfTrophies = new JLabel("Number of trophies won = ");
		lblNumberOfTrophies.setHorizontalAlignment(SwingConstants.CENTER);
		lblNumberOfTrophies.setBounds(35, 208, 172, 62);
		contentPane.add(lblNumberOfTrophies);
		
		nameField = new JTextField();
		nameField.setBounds(254, 75, 165, 41);
		contentPane.add(nameField);
		nameField.setColumns(10);
		
		dateField = new JTextField();
		dateField.setColumns(10);
		dateField.setBounds(254, 147, 165, 41);
		contentPane.add(dateField);
		
		trophiesWonField = new JTextField();
		trophiesWonField.setColumns(10);
		trophiesWonField.setBounds(254, 219, 165, 41);
		contentPane.add(trophiesWonField);
		
		chooseStadiumBox = new JComboBox();
		chooseStadiumBox.setBounds(585, 130, 93, 21);
		contentPane.add(chooseStadiumBox);
		
		
		
		JLabel lblChooseStadium = new JLabel("Choose stadium = ");
		lblChooseStadium.setHorizontalAlignment(SwingConstants.CENTER);
		lblChooseStadium.setBounds(440, 109, 172, 62);
		contentPane.add(lblChooseStadium);
		
		saveButton = new JButton("SAVE");
		boolean firstClick = true;
		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					List<FootballClub> data = frame.getData();
					FootballClub s = null;
					for(FootballClub stad : data) {
						if(stad.getIdKluba() == id) {
							s = stad;
						}
					}
					Stadium s2 = null;
					for(Stadium s1 : stadiums) {
						if(s1.getStadionId() == s.getStadionId()) {
							chooseStadiumBox.setSelectedItem(s1);
							s2 = s1;
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
						if(s2 != null && s2.getStadionId() != s.getStadionId()) {
							s.setStadionId(s2.getStadionId());
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
		//saveButton.doClick();
		saveButton.setBounds(35, 335, 643, 57);
		contentPane.add(saveButton);
		
		JLabel lblNewLabel_1 = new JLabel("CHANGE CLUB");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(35, 10, 642, 29);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Default stadium selected!");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(465, 64, 194, 38);
		contentPane.add(lblNewLabel_2);
	}
}
