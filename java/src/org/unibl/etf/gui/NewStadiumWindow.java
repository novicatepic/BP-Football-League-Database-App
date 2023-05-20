package org.unibl.etf.gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import org.unibl.etf.classes.Stadium;

public class NewStadiumWindow extends JFrame {

	private JPanel contentPane;
	private JTextField nameField;
	private JTextField dateField;
	private JTextField trophiesWonField;

	private StadiumGui frame;
	public void setStadiumFrame(StadiumGui frame) {
		this.frame = frame;
	}
	
	private NewStadiumWindow stadiumFrame;
	public void setNewStadiumFrame(NewStadiumWindow frame) {
		stadiumFrame = frame;
	}
	
	/**
	 * Create the frame.
	 */
	public NewStadiumWindow() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 723, 503);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Name = ");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(35, 35, 172, 62);
		contentPane.add(lblNewLabel);
		
		JLabel lblFoundatioDate = new JLabel("Capacity = ");
		lblFoundatioDate.setHorizontalAlignment(SwingConstants.CENTER);
		lblFoundatioDate.setBounds(35, 147, 172, 62);
		contentPane.add(lblFoundatioDate);
		
		JLabel lblNumberOfTrophies = new JLabel("Town = ");
		lblNumberOfTrophies.setHorizontalAlignment(SwingConstants.CENTER);
		lblNumberOfTrophies.setBounds(35, 279, 172, 62);
		contentPane.add(lblNumberOfTrophies);
		
		nameField = new JTextField();
		nameField.setBounds(254, 49, 165, 41);
		contentPane.add(nameField);
		nameField.setColumns(10);
		
		dateField = new JTextField();
		dateField.setColumns(10);
		dateField.setBounds(254, 154, 165, 41);
		contentPane.add(dateField);
		
		trophiesWonField = new JTextField();
		trophiesWonField.setColumns(10);
		trophiesWonField.setBounds(254, 287, 165, 41);
		contentPane.add(trophiesWonField);
		
		JButton saveButton = new JButton("SAVE");
		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = nameField.getText();
				int capacity = Integer.valueOf(dateField.getText());
				String town = trophiesWonField.getText();
				Stadium s = new Stadium();
				s.setNaziv(name);
				s.setKapacitet(capacity);
				s.setGrad(town);
				frame.insert(s);
				try {
					Thread.sleep(1000);
				} catch(InterruptedException ex) {
					ErrorBox errorBox = new ErrorBox();
					errorBox.setVisible(true);
					errorBox.setText(ex.getMessage());
					ex.printStackTrace();
				}
				stadiumFrame.dispose();
				frame.dispose();
				StadiumGui sg = new StadiumGui();
				sg.setFrame(sg);
				sg.setVisible(true);
			}
		});
		saveButton.setBounds(35, 399, 643, 57);
		contentPane.add(saveButton);
	}

}
