package org.unibl.etf.gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class NewFixtureGui extends JFrame {

	private JPanel contentPane;
	private JTextField newDateField;
	private JTextField homeTeamGoalsField;
	private JTextField awayTeamGoalsField;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NewFixtureGui frame = new NewFixtureGui();
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
	public NewFixtureGui() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1151, 741);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Date = ");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(35, 117, 172, 62);
		contentPane.add(lblNewLabel);
		
		JLabel lblFoundatioDate = new JLabel("Home team goals = ");
		lblFoundatioDate.setHorizontalAlignment(SwingConstants.CENTER);
		lblFoundatioDate.setBounds(35, 284, 172, 62);
		contentPane.add(lblFoundatioDate);
		
		newDateField = new JTextField();
		newDateField.setBounds(217, 128, 165, 41);
		contentPane.add(newDateField);
		newDateField.setColumns(10);
		
		homeTeamGoalsField = new JTextField();
		homeTeamGoalsField.setColumns(10);
		homeTeamGoalsField.setBounds(217, 295, 165, 41);
		contentPane.add(homeTeamGoalsField);
		
		JComboBox chooseHomeTeamBox = new JComboBox();
		chooseHomeTeamBox.setBounds(868, 95, 68, 21);
		contentPane.add(chooseHomeTeamBox);
		
		JLabel lblChooseStadium = new JLabel("Home team = ");
		lblChooseStadium.setHorizontalAlignment(SwingConstants.CENTER);
		lblChooseStadium.setBounds(709, 74, 172, 62);
		contentPane.add(lblChooseStadium);
		
		JButton saveButton = new JButton("SAVE");
		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		saveButton.setBounds(254, 620, 643, 57);
		contentPane.add(saveButton);
		
		JLabel lblAwayTeam = new JLabel("Away team = ");
		lblAwayTeam.setHorizontalAlignment(SwingConstants.CENTER);
		lblAwayTeam.setBounds(709, 179, 172, 62);
		contentPane.add(lblAwayTeam);
		
		JComboBox chooseAwayTeamBox = new JComboBox();
		chooseAwayTeamBox.setBounds(868, 200, 68, 21);
		contentPane.add(chooseAwayTeamBox);
		
		JComboBox chooseFixtureBox = new JComboBox();
		chooseFixtureBox.setBounds(217, 49, 132, 34);
		contentPane.add(chooseFixtureBox);
		
		JLabel lblFixtureNum = new JLabel("Fixture num = ");
		lblFixtureNum.setHorizontalAlignment(SwingConstants.CENTER);
		lblFixtureNum.setBounds(35, 35, 172, 62);
		contentPane.add(lblFixtureNum);
		
		JLabel lblReferee = new JLabel("Referee = ");
		lblReferee.setHorizontalAlignment(SwingConstants.CENTER);
		lblReferee.setBounds(35, 204, 172, 62);
		contentPane.add(lblReferee);
		
		JComboBox refereeBox = new JComboBox();
		refereeBox.setBounds(217, 218, 132, 34);
		contentPane.add(refereeBox);
		
		JLabel lblAwayTeamGoals = new JLabel("Away team goals = ");
		lblAwayTeamGoals.setHorizontalAlignment(SwingConstants.CENTER);
		lblAwayTeamGoals.setBounds(35, 370, 172, 62);
		contentPane.add(lblAwayTeamGoals);
		
		awayTeamGoalsField = new JTextField();
		awayTeamGoalsField.setColumns(10);
		awayTeamGoalsField.setBounds(217, 378, 165, 41);
		contentPane.add(awayTeamGoalsField);
	}
}
