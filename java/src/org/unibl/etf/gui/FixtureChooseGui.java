package org.unibl.etf.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.unibl.etf.exceptions.InvalidFixtureNumber;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FixtureChooseGui extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FixtureChooseGui frame = new FixtureChooseGui();
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
	public FixtureChooseGui() {
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 211);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel chooseLabel = new JLabel("Unesite broj kola:");
		chooseLabel.setHorizontalAlignment(SwingConstants.CENTER);
		chooseLabel.setBounds(36, 34, 362, 55);
		contentPane.add(chooseLabel);
		
		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setText("0");
		textField.setBounds(172, 99, 96, 19);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Potvrdi unos");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//System.out.println(textField.getText());
				int fixture = Integer.valueOf(textField.getText());
				boolean fixtureOK = (fixture >= 1 && fixture <= 38) ? true : false;
				try {
					if(fixtureOK) {
						FixtureShowGui fixtureShowGui = new FixtureShowGui();
						fixtureShowGui.setFixtureNumber(fixture);
						fixtureShowGui.setVisible(true);
						setVisible(false);
						dispose();
					}
					else {
						throw new InvalidFixtureNumber();
					}
				} catch(InvalidFixtureNumber ie) {
					ie.printStackTrace();
				}
				
			}
		});
		btnNewButton.setBounds(164, 143, 125, 21);
		contentPane.add(btnNewButton);
	}
}
