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
	private JTextField resultField;
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
		setBounds(100, 100, 723, 503);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Date = ");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(35, 35, 172, 62);
		contentPane.add(lblNewLabel);
		
		JLabel lblFoundatioDate = new JLabel("Result = ");
		lblFoundatioDate.setHorizontalAlignment(SwingConstants.CENTER);
		lblFoundatioDate.setBounds(35, 147, 172, 62);
		contentPane.add(lblFoundatioDate);
		
		newDateField = new JTextField();
		newDateField.setBounds(254, 49, 165, 41);
		contentPane.add(newDateField);
		newDateField.setColumns(10);
		
		resultField = new JTextField();
		resultField.setColumns(10);
		resultField.setBounds(254, 154, 165, 41);
		contentPane.add(resultField);
		
		JComboBox chooseStadiumBox = new JComboBox();
		chooseStadiumBox.setBounds(254, 252, 29, 21);
		contentPane.add(chooseStadiumBox);
		
		JLabel lblChooseStadium = new JLabel("Home team = ");
		lblChooseStadium.setHorizontalAlignment(SwingConstants.CENTER);
		lblChooseStadium.setBounds(45, 231, 172, 62);
		contentPane.add(lblChooseStadium);
		
		JButton saveButton = new JButton("SAVE");
		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		saveButton.setBounds(35, 399, 643, 57);
		contentPane.add(saveButton);
		
		JLabel lblAwayTeam = new JLabel("Away team = ");
		lblAwayTeam.setHorizontalAlignment(SwingConstants.CENTER);
		lblAwayTeam.setBounds(368, 231, 172, 62);
		contentPane.add(lblAwayTeam);
		
		JComboBox chooseStadiumBox_1 = new JComboBox();
		chooseStadiumBox_1.setBounds(575, 252, 29, 21);
		contentPane.add(chooseStadiumBox_1);
	}

}
