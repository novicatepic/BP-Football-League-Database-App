package org.unibl.etf.gui;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShowPlayers extends JFrame {

	private JPanel contentPane;
	private JLabel[] playerLabels = new JLabel[22];
	private JButton[] playerButtons = new JButton[22];
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ShowPlayers frame = new ShowPlayers();
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
	public ShowPlayers() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 838, 744);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel playerPanel = new JPanel();
		playerPanel.setBounds(128, 115, 208, 528);
		contentPane.add(playerPanel);
		playerPanel.setLayout(new GridLayout(22, 1));
		JPanel playerButtonPanel = new JPanel();
		playerButtonPanel.setBounds(506, 115, 203, 528);
		contentPane.add(playerButtonPanel);
		playerButtonPanel.setLayout(new GridLayout(22, 1));
		
		JLabel lblNewLabel = new JLabel("HOME PLAYERS");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(128, 27, 208, 52);
		contentPane.add(lblNewLabel);
		
		JLabel lblAwayPlayers = new JLabel("AWAY PLAYERS");
		lblAwayPlayers.setHorizontalAlignment(SwingConstants.CENTER);
		lblAwayPlayers.setBounds(501, 27, 208, 52);
		contentPane.add(lblAwayPlayers);
		
		JPanel panel = new JPanel();
		panel.setBounds(441, 115, 55, 528);
		contentPane.add(panel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(61, 115, 55, 528);
		contentPane.add(panel_1);
		for(int i = 0; i < 22; i++) {
			playerLabels[i] = new JLabel();
			playerLabels[i].setHorizontalAlignment(SwingConstants.CENTER);
			playerLabels[i].setText(String.valueOf(i));
			playerPanel.add(playerLabels[i]);
		}
		
		for(int i = 0; i < 22; i++) {
			playerButtons[i] = new JButton();
			playerButtons[i].setHorizontalAlignment(SwingConstants.CENTER);
			playerButtons[i].setText(String.valueOf(i));
			playerButtonPanel.add(playerButtons[i]);
		}
		
		/*for(int i = 0; i < 22; i++) {
			playerButtons[i].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e)  {
					ShowPlayerInfo playerInfo = new ShowPlayerInfo();
					//fixtureShowGui.setFixtureNumber(fixture);
					playerInfo.setVisible(true);
					setVisible(false);
					dispose();
				}
			});
		}*/
		
	}

}
