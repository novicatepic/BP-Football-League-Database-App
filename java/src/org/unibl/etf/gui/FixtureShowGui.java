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

public class FixtureShowGui extends JFrame {

	private JPanel contentPane;
	private int fixtureNumber = 0;
	private final JLabel[] gameLabels = new JLabel[10];
	private final JButton[] gameButtons = new JButton[10];
	JLabel whichFixtureLabel;
	private JPanel resultPanel;
	private JButton btnNewButton;
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
		setBounds(100, 100, 675, 495);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		whichFixtureLabel = new JLabel();
		whichFixtureLabel.setHorizontalAlignment(SwingConstants.CENTER);
		whichFixtureLabel.setBounds(277, 411, 130, 13);
		getContentPane().add(whichFixtureLabel);	
		JPanel fixturePanel = new JPanel();
		fixturePanel.setBounds(61, 16, 382, 352);
		contentPane.add(fixturePanel);
		fixturePanel.setLayout(new GridLayout(1, 0, 0, 0));
		fixturePanel.setLayout(new GridLayout(10, 1));
		
		resultPanel = new JPanel();
		resultPanel.setBounds(476, 16, 161, 352);
		resultPanel.setLayout(new GridLayout(1, 0, 0, 0));
		resultPanel.setLayout(new GridLayout(10, 1));
		contentPane.add(resultPanel);
		/*btnNewButton = new JButton("New button");
		btnNewButton.setBounds(511, 403, 85, 21);
		contentPane.add(btnNewButton);*/
		for(int i = 0; i < 10; i++) {
			gameLabels[i] = new JLabel();
			gameLabels[i].setHorizontalAlignment(SwingConstants.CENTER);
			gameLabels[i].setText(String.valueOf(i));
			fixturePanel.add(gameLabels[i]);
		}
		
		for(int i = 0; i < 10; i++) {
			gameButtons[i] = new JButton();
			gameButtons[i].setHorizontalAlignment(SwingConstants.CENTER);
			gameButtons[i].setText(String.valueOf(i));
			resultPanel.add(gameButtons[i]);
		}
		
		for(int i = 0; i < 10; i++) {
			gameButtons[i].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e)  {
					GameInfo gameInfo = new GameInfo();
					//fixtureShowGui.setFixtureNumber(fixture);
					gameInfo.setVisible(true);
					setVisible(false);
					dispose();
				}
			});
		}
		
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
