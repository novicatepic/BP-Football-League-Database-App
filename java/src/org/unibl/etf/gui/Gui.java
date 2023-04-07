package org.unibl.etf.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class Gui extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Gui frame = new Gui();
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
	public Gui() {
		setTitle("Jelen SuperLiga");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 201, 482);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton clubButton = new JButton("Visit teams");
		clubButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TeamGui teamGui = new TeamGui();
				teamGui.setVisible(true);
			}
		});
		clubButton.setForeground(new Color(0, 0, 0));
		
		
		clubButton.setBounds(24, 42, 121, 51);
		contentPane.add(clubButton);
		
		JButton stadiumButton = new JButton("Visit stadiums");
		stadiumButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StadiumGui stadiumGui = new StadiumGui();
				stadiumGui.setVisible(true);
			}
		});
		
		stadiumButton.setForeground(Color.BLACK);
		stadiumButton.setBounds(24, 145, 121, 51);
		contentPane.add(stadiumButton);
		
		JButton btnVisitWiners = new JButton("Visit winners");
		btnVisitWiners.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WinnerGui winnerGui = new WinnerGui();
				winnerGui.setVisible(true);
			}
		});
		
		btnVisitWiners.setForeground(Color.BLACK);
		btnVisitWiners.setBounds(24, 254, 121, 51);
		contentPane.add(btnVisitWiners);
		
		JButton btnVisitWiners_1 = new JButton("Visit fixtures");
		btnVisitWiners_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FixtureChooseGui fixtureGui = new FixtureChooseGui();
				fixtureGui.setVisible(true);
			}
		});
		
		
		btnVisitWiners_1.setForeground(Color.BLACK);
		btnVisitWiners_1.setBounds(24, 357, 121, 51);
		contentPane.add(btnVisitWiners_1);
	}
}
