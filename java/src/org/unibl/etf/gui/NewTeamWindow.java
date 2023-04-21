package org.unibl.etf.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.unibl.etf.classes.FootballClub;
import org.unibl.etf.classes.Stadium;
import org.unibl.etf.util.DBUtil;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;

public class NewTeamWindow extends JFrame {

	private JPanel contentPane;
	private JTextField nameField;
	private JTextField dateField;
	private JTextField trophiesWonField;

	private TeamGui frame;
	public void setTeamFrame(TeamGui frame) {
		this.frame = frame;
	}
	private List<Stadium> stadiums;
	public void passStadiums(List<Stadium> stadiums) {
		this.stadiums = stadiums;
	}
	private JComboBox chooseStadiumBox;
	public void populateData() {
		for(Stadium s : stadiums) {
			chooseStadiumBox.addItem(s);
		}
	}
	
	private NewTeamWindow teamFrame;
	public void setTeamFrame(NewTeamWindow frame) {
		teamFrame = frame;
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NewTeamWindow frame = new NewTeamWindow();
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
	public NewTeamWindow() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 723, 503);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Name = ");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(35, 35, 172, 62);
		contentPane.add(lblNewLabel);
		
		JLabel lblFoundatioDate = new JLabel("Foundation date = ");
		lblFoundatioDate.setHorizontalAlignment(SwingConstants.CENTER);
		lblFoundatioDate.setBounds(35, 147, 172, 62);
		contentPane.add(lblFoundatioDate);
		
		JLabel lblNumberOfTrophies = new JLabel("Number of trophies won = ");
		lblNumberOfTrophies.setHorizontalAlignment(SwingConstants.CENTER);
		lblNumberOfTrophies.setBounds(35, 279, 172, 62);
		contentPane.add(lblNumberOfTrophies);
		
		nameField = new JTextField();
		nameField.setBounds(254, 49, 165, 41);
		contentPane.add(nameField);
		nameField.setColumns(10);
		
		dateField = new JTextField();
		dateField.setColumns(10);
		dateField.setBounds(254, 154, 165, 41);
		contentPane.add(dateField);
		
		trophiesWonField = new JTextField();
		trophiesWonField.setColumns(10);
		trophiesWonField.setBounds(254, 287, 165, 41);
		contentPane.add(trophiesWonField);
		
		chooseStadiumBox = new JComboBox();
		chooseStadiumBox.setBounds(606, 56, 71, 21);
		contentPane.add(chooseStadiumBox);
		
		JLabel lblChooseStadium = new JLabel("Choose stadium = ");
		lblChooseStadium.setHorizontalAlignment(SwingConstants.CENTER);
		lblChooseStadium.setBounds(441, 35, 172, 62);
		contentPane.add(lblChooseStadium);
		
		JButton saveButton = new JButton("SAVE");
		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String name = nameField.getText();
					String[] dateFieldParse = dateField.getText().split("-");
					if(dateFieldParse.length != 3) {
						throw new Exception("Problem!");
					}
					Date date = Date.valueOf(dateField.getText());
					int numTrophiesWon = Integer.valueOf(trophiesWonField.getText());
					FootballClub s = new FootballClub();
					s.setNaziv(name);
					s.setDatumOsnivanja(date);
					s.setBrojOsvojenihTrofeja(numTrophiesWon);
					/*String id = String.valueOf(chooseStadiumBox.getSelectedItem());
					int realId = Integer.valueOf(id);*/
					Stadium stadium = (Stadium)chooseStadiumBox.getSelectedItem();
					s.setStadionId(stadium.getStadionId());
					frame.insert(s);
					try {
						Thread.sleep(1000);
					} catch(InterruptedException ex) {
						ex.printStackTrace();
					}
					frame.dispose();
					teamFrame.dispose();
					TeamGui sg = new TeamGui();
					sg.setFrame(sg);
					sg.setVisible(true);
				} catch(Exception e1) {
					e1.printStackTrace();
				}				
			}
		});
		saveButton.setBounds(35, 399, 643, 57);
		contentPane.add(saveButton);
	}
	
	
}
