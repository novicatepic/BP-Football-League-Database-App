package org.unibl.etf.gui;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.SwingConstants;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;
import java.awt.Font;

public class FixtureShowGui extends JFrame {

	private JPanel contentPane;
	private int fixtureNumber = 0;
	private final JLabel[] gameLabels = new JLabel[10];
	private final JButton[] gameButtons = new JButton[10];
	JLabel whichFixtureLabel;
	private JPanel awayTeamsPanel;
	private JButton btnNewButton;
	private JPanel resultsPanel;
	private JPanel datePanel;
	private JComboBox gameweekBox;
	private JPanel changeFixturePanel;
	private JLabel lblNewLabel;
	private JButton newFixtureButton;
	private JButton showPlayersButton;
	private JButton submitFixtureButton;
	private JPanel deleteFixturePanel;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FixtureShowGui frame = new FixtureShowGui();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public FixtureShowGui() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setBounds(100, 100, 1291, 784);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		whichFixtureLabel = new JLabel();
		whichFixtureLabel.setHorizontalAlignment(SwingConstants.CENTER);
		whichFixtureLabel.setBounds(277, 411, 130, 13);
		getContentPane().add(whichFixtureLabel);	
		JPanel homeTeamsPanel = new JPanel();
		homeTeamsPanel.setBounds(50, 193, 170, 462);
		contentPane.add(homeTeamsPanel);
		homeTeamsPanel.setLayout(new GridLayout(1, 0, 0, 0));
		homeTeamsPanel.setLayout(new GridLayout(10, 1));
		
		awayTeamsPanel = new JPanel();
		awayTeamsPanel.setBounds(237, 193, 170, 462);
		awayTeamsPanel.setLayout(new GridLayout(1, 0, 0, 0));
		awayTeamsPanel.setLayout(new GridLayout(10, 1));
		contentPane.add(awayTeamsPanel);
		
		resultsPanel = new JPanel();
		resultsPanel.setBounds(417, 193, 170, 462);
		contentPane.add(resultsPanel);
		resultsPanel.setLayout(new GridLayout(1, 0, 0, 0));
		
		datePanel = new JPanel();
		datePanel.setBounds(597, 193, 170, 462);
		contentPane.add(datePanel);
		datePanel.setLayout(new GridLayout(1, 0, 0, 0));
		
		gameweekBox = new JComboBox();
		gameweekBox.setToolTipText("KOLO");
		gameweekBox.setBounds(96, 94, 71, 44);
		contentPane.add(gameweekBox);
		
		changeFixturePanel = new JPanel();
		changeFixturePanel.setBounds(816, 193, 170, 462);
		contentPane.add(changeFixturePanel);
		changeFixturePanel.setLayout(new GridLayout(1, 0, 0, 0));
		
		lblNewLabel = new JLabel("FIXTURES");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(50, 10, 1031, 59);
		contentPane.add(lblNewLabel);
		
		newFixtureButton = new JButton("Add a new fixture");
		newFixtureButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NewFixtureGui nfg = new NewFixtureGui();
				nfg.setVisible(true);
			}
		});
		newFixtureButton.setBounds(72, 665, 222, 72);
		contentPane.add(newFixtureButton);
		
		showPlayersButton = new JButton("Show players");
		showPlayersButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ShowPlayers sp = new ShowPlayers();
				sp.setVisible(true);
			}
		});
		showPlayersButton.setBounds(360, 665, 222, 72);
		contentPane.add(showPlayersButton);
		
		submitFixtureButton = new JButton("Submit");
		submitFixtureButton.setBounds(198, 91, 138, 50);
		contentPane.add(submitFixtureButton);
		
		deleteFixturePanel = new JPanel();
		deleteFixturePanel.setBounds(994, 193, 170, 462);
		contentPane.add(deleteFixturePanel);
		deleteFixturePanel.setLayout(new GridLayout(1, 0, 0, 0));
		/*btnNewButton = new JButton("New button");
		btnNewButton.setBounds(511, 403, 85, 21);
		contentPane.add(btnNewButton);*/
		for(int i = 0; i < 10; i++) {
			gameLabels[i] = new JLabel();
			gameLabels[i].setHorizontalAlignment(SwingConstants.CENTER);
			gameLabels[i].setText(String.valueOf(i));
			homeTeamsPanel.add(gameLabels[i]);
		}
		
		/*for(int i = 0; i < 10; i++) {
			gameButtons[i] = new JButton();
			gameButtons[i].setHorizontalAlignment(SwingConstants.CENTER);
			gameButtons[i].setText(String.valueOf(i));
			awayTeamsPanel.add(gameButtons[i]);
		}*/
		
		/*for(int i = 0; i < 10; i++) {
			gameButtons[i].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e)  {
					GameInfo gameInfo = new GameInfo();
					//fixtureShowGui.setFixtureNumber(fixture);
					gameInfo.setVisible(true);
					setVisible(false);
					dispose();
				}
			});
		}*/
		
	}
	
	public void setFixtureNumber(int fixNum) {
		fixtureNumber = fixNum;
		System.out.println(fixtureNumber);
		whichFixtureLabel.setText("Kolo broj " + fixtureNumber);
	}
	
	
	
	/**
	 * Create the frame.
	 */
	/*public FixtureShowGui(int whichFixture) {
		System.out.println("Arg with 1 " + whichFixture);
		fixtureNumber = whichFixture;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		JLabel whichFixtureLabel = new JLabel("New label");
		whichFixtureLabel.setHorizontalAlignment(SwingConstants.CENTER);
		whichFixtureLabel.setBounds(40, 10, 623, 72);
		getContentPane().add(whichFixtureLabel);	
		whichFixtureLabel.setText("Kolo broj " + fixtureNumber);	
	}*/

}
