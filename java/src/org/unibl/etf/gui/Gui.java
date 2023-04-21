package org.unibl.etf.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;

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
		setBounds(100, 100, 203, 370);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton clubButton = new JButton("Visit teams");
		clubButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TeamGui teamGui = new TeamGui();
				teamGui.setFrame(teamGui);
				teamGui.setVisible(true);
			}
		});
		clubButton.setForeground(new Color(0, 0, 0));
		
		
		clubButton.setBounds(24, 63, 121, 51);
		contentPane.add(clubButton);
		
		JButton stadiumButton = new JButton("Visit stadiums");
		stadiumButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StadiumGui stadiumGui = new StadiumGui();
				stadiumGui.setFrame(stadiumGui);
				//stadiumGui.setScrollBar();
				stadiumGui.setVisible(true);
			}
		});
		
		stadiumButton.setForeground(Color.BLACK);
		stadiumButton.setBounds(24, 145, 121, 51);
		contentPane.add(stadiumButton);
		
		JLabel lblNewLabel = new JLabel("WELCOME");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 10, 160, 36);
		contentPane.add(lblNewLabel);
		
		JButton btnVisitWiners_1 = new JButton("Visit fixtures");
		btnVisitWiners_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FixtureShowGui fixtureGui = new FixtureShowGui();
				fixtureGui.setVisible(true);
			}
		});
		
		
		btnVisitWiners_1.setForeground(Color.BLACK);
		btnVisitWiners_1.setBounds(24, 239, 121, 51);
		contentPane.add(btnVisitWiners_1);
	}
}
