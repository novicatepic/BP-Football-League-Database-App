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
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;

public class ChangeTeamWindow extends JFrame {

	private JPanel contentPane;
	private JTextField nameField;
	private JTextField dateField;
	private JTextField trophiesWonField;
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
		lblNewLabel.setBounds(35, 64, 172, 62);
		contentPane.add(lblNewLabel);
		
		JLabel lblFoundatioDate = new JLabel("Foundation date = ");
		lblFoundatioDate.setHorizontalAlignment(SwingConstants.CENTER);
		lblFoundatioDate.setBounds(35, 147, 172, 62);
		contentPane.add(lblFoundatioDate);
		
		JLabel lblNumberOfTrophies = new JLabel("Number of trophies won = ");
		lblNumberOfTrophies.setHorizontalAlignment(SwingConstants.CENTER);
		lblNumberOfTrophies.setBounds(35, 236, 172, 62);
		contentPane.add(lblNumberOfTrophies);
		
		nameField = new JTextField();
		nameField.setBounds(254, 75, 165, 41);
		contentPane.add(nameField);
		nameField.setColumns(10);
		
		dateField = new JTextField();
		dateField.setColumns(10);
		dateField.setBounds(254, 154, 165, 41);
		contentPane.add(dateField);
		
		trophiesWonField = new JTextField();
		trophiesWonField.setColumns(10);
		trophiesWonField.setBounds(254, 247, 165, 41);
		contentPane.add(trophiesWonField);
		
		nameField.setText("/");
		dateField.setText("/");
		trophiesWonField.setText("/");
		
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
				
			}
		});
		saveButton.setBounds(35, 399, 643, 57);
		contentPane.add(saveButton);
		
		JLabel lblNewLabel_1 = new JLabel("CHANGE CLUB");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(35, 10, 642, 29);
		contentPane.add(lblNewLabel_1);
	}
}
