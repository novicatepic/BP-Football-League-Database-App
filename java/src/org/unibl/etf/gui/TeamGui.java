package org.unibl.etf.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.GridLayout;
import javax.swing.JComboBox;
import java.awt.Font;

public class TeamGui extends JFrame {

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
					TeamGui frame = new TeamGui();
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
	public TeamGui() {
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 881, 595);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton addButton = new JButton("Add club");
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NewTeamWindow ntw = new NewTeamWindow();
				ntw.setVisible(true);
			}
		});
		addButton.setBounds(76, 474, 116, 44);
		contentPane.add(addButton);
		
		JButton changeButton = new JButton("Change club");
		changeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ChangeTeamWindow ctw = new ChangeTeamWindow();
				ctw.setVisible(true);
			}
		});
		changeButton.setBounds(323, 474, 116, 44);
		contentPane.add(changeButton);
		
		JButton deleteButton = new JButton("Delete club");
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DeleteClubWindow deleteClubWindow = new DeleteClubWindow();
				deleteClubWindow.setVisible(true);
			}
		});
		deleteButton.setBounds(588, 474, 116, 44);
		contentPane.add(deleteButton);
		
		JPanel panel = new JPanel();
		panel.setBounds(57, 85, 671, 373);
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
		
		JLabel lblNewLabel = new JLabel("NAME");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(88, 60, 66, 27);
		contentPane.add(lblNewLabel);
		
		JLabel lblFoundationDate = new JLabel("DATE");
		lblFoundationDate.setHorizontalAlignment(SwingConstants.CENTER);
		lblFoundationDate.setBounds(249, 60, 85, 27);
		contentPane.add(lblFoundationDate);
		
		JLabel lblNumtrophies = new JLabel("NUM_TROPHIES");
		lblNumtrophies.setHorizontalAlignment(SwingConstants.CENTER);
		lblNumtrophies.setBounds(415, 60, 85, 27);
		contentPane.add(lblNumtrophies);
		
		JLabel lblStadiumname = new JLabel("STADIUM_NAME");
		lblStadiumname.setHorizontalAlignment(SwingConstants.CENTER);
		lblStadiumname.setBounds(584, 60, 85, 27);
		contentPane.add(lblStadiumname);
		
		//changeButton.setEnabled(false);
		//deleteButton.setEnabled(false);
		
		JComboBox chooseTeamBox = new JComboBox();
		chooseTeamBox.setBounds(759, 71, 66, 34);
		contentPane.add(chooseTeamBox);
		
		JLabel lblNewLabel_1 = new JLabel("TEAMS");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(57, 10, 671, 40);
		contentPane.add(lblNewLabel_1);
		
		for(int i = 0; i < NUM_TEAMS; i++) {
			for(int j = 0; j < 4; j++) {
				chooseTeamBox.addItem(new String(labels[i][j].getText()));
			}
		}
		
		chooseTeamBox.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JComboBox comboBox = (JComboBox) e.getSource();

	            // Print the selected items and the action command.
	            Object selected = comboBox.getSelectedItem();
	            System.out.println("Selected Item  = " + selected);
				
			}
		});
		
	}
}
