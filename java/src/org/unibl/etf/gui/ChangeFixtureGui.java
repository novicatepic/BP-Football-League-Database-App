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
	private JTextField homeTeamGoals;
	private JTextField awayTeamGoals;

	private ChangeFixtureGui frame;
	public void setFrame(ChangeFixtureGui frame) {
		this.frame = frame;
	}
	
	private FixtureShowGui showGuiFrame;
	public void setFrame(FixtureShowGui frame) {
		showGuiFrame = frame;
	}
	
	private int gameId;
	public void setGameId(int id) {
		gameId = id;
	}
	
	private String clubIds;
	public void setClubIds(String ids) {
		clubIds = ids;
	}
	
	private String result;
	private int homeGoals;
	private int awayGoals;
	public void setResult(String result) {
		this.result = result;
		String[] split = result.split("-");
		homeGoals = Integer.valueOf(split[0]);
		awayGoals = Integer.valueOf(split[1]);
		homeTeamGoals.setText(split[0]);
		awayTeamGoals.setText(split[1]);
	}
	
	/**
	 * Create the frame.
	 */
	public ChangeFixtureGui() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 725, 467);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNumberOfTrophies = new JLabel("Home team goals = ");
		lblNumberOfTrophies.setHorizontalAlignment(SwingConstants.CENTER);
		lblNumberOfTrophies.setBounds(36, 70, 172, 62);
		contentPane.add(lblNumberOfTrophies);
		
		homeTeamGoals = new JTextField();
		
		homeTeamGoals.setColumns(10);
		homeTeamGoals.setBounds(251, 82, 165, 41);
		contentPane.add(homeTeamGoals);
		JLabel lblNewLabel_1 = new JLabel("CHANGE FIXTURE");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(35, 10, 642, 29);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNumberOfTrophies_1 = new JLabel("Away team goals = ");
		lblNumberOfTrophies_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNumberOfTrophies_1.setBounds(36, 177, 172, 62);
		contentPane.add(lblNumberOfTrophies_1);
		
		awayTeamGoals = new JTextField();
		awayTeamGoals.setColumns(10);
		awayTeamGoals.setBounds(250, 184, 165, 41);
		contentPane.add(awayTeamGoals);
		JButton saveButton = new JButton("SAVE");
		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					boolean anything = false;
					int homeGoals2 = Integer.valueOf(homeTeamGoals.getText());
					int awayGoals2 = Integer.valueOf(awayTeamGoals.getText());
					String[] split = clubIds.split("-");
					if(homeGoals2 < 0 || awayGoals2 < 0) {
						throw new Exception("Home or away goals < 0");
					}
					if(homeGoals2 != homeGoals) {
						anything = true;
					}
					if(awayGoals2 != awayGoals) {
						anything = true;
					}
					if(anything) {
						showGuiFrame.updateTeamInGame(homeGoals2, awayGoals2, Integer.valueOf(split[0]), gameId);
						showGuiFrame.updateTeamInGame(awayGoals2, homeGoals2, Integer.valueOf(split[1]), gameId);
						try {
							Thread.sleep(1000);
						} catch(InterruptedException ex) {
							ErrorBox errorBox = new ErrorBox();
							errorBox.setVisible(true);
							errorBox.setText(ex.getMessage());
							ex.printStackTrace();
						}
						showGuiFrame.dispose();
						frame.dispose();
						FixtureShowGui sg = new FixtureShowGui();
						sg.setFrame(sg);
						sg.setVisible(true);
					}
 				} catch(Exception ee) {
 					ErrorBox errorBox = new ErrorBox();
					errorBox.setVisible(true);
					errorBox.setText(ee.getMessage());
					ee.printStackTrace();
 				}
				
				
			}
		});
		saveButton.setBounds(40, 361, 643, 57);
		contentPane.add(saveButton);
		

	}
}
