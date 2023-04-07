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
		setBounds(100, 100, 712, 518);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel playerPanel = new JPanel();
		playerPanel.setBounds(20, 10, 429, 461);
		contentPane.add(playerPanel);
		playerPanel.setLayout(new GridLayout(22, 1));
		JPanel playerButtonPanel = new JPanel();
		playerButtonPanel.setBounds(475, 10, 213, 461);
		contentPane.add(playerButtonPanel);
		playerButtonPanel.setLayout(new GridLayout(22, 1));
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
		
		for(int i = 0; i < 22; i++) {
			playerButtons[i].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e)  {
					ShowPlayerInfo playerInfo = new ShowPlayerInfo();
					//fixtureShowGui.setFixtureNumber(fixture);
					playerInfo.setVisible(true);
					setVisible(false);
					dispose();
				}
			});
		}
		
	}

}
