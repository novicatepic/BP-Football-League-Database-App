package org.unibl.etf.gui;

import java.awt.EventQueue;
import java.awt.Font;
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

public class ChangeFixtureGui extends JFrame {

	private JPanel contentPane;
	private JTextField resultField;
	private JTextField dateField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChangeFixtureGui frame = new ChangeFixtureGui();
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
	public ChangeFixtureGui() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 725, 604);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Home team = ");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(35, 64, 172, 62);
		contentPane.add(lblNewLabel);
		
		JLabel lblFoundatioDate = new JLabel("Away team = ");
		lblFoundatioDate.setHorizontalAlignment(SwingConstants.CENTER);
		lblFoundatioDate.setBounds(35, 147, 172, 62);
		contentPane.add(lblFoundatioDate);
		
		JLabel lblNumberOfTrophies = new JLabel("Result = ");
		lblNumberOfTrophies.setHorizontalAlignment(SwingConstants.CENTER);
		lblNumberOfTrophies.setBounds(35, 236, 172, 62);
		contentPane.add(lblNumberOfTrophies);
		
		resultField = new JTextField();
		resultField.setColumns(10);
		resultField.setBounds(254, 247, 165, 41);
		contentPane.add(resultField);
		resultField.setText("/");
		
		JButton saveButton = new JButton("SAVE");
		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		saveButton.setBounds(35, 477, 643, 57);
		contentPane.add(saveButton);
		
		JLabel lblNewLabel_1 = new JLabel("CHANGE FIXTURE");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(35, 10, 642, 29);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblDate = new JLabel("Date = ");
		lblDate.setHorizontalAlignment(SwingConstants.CENTER);
		lblDate.setBounds(35, 335, 172, 62);
		contentPane.add(lblDate);
		
		dateField = new JTextField();
		dateField.setText("/");
		dateField.setColumns(10);
		dateField.setBounds(254, 345, 165, 41);
		contentPane.add(dateField);
		
		JComboBox homeComboBox = new JComboBox();
		homeComboBox.setBounds(298, 85, 65, 21);
		contentPane.add(homeComboBox);
		
		JComboBox awayComboBox = new JComboBox();
		awayComboBox.setBounds(298, 168, 65, 21);
		contentPane.add(awayComboBox);
	}
}
