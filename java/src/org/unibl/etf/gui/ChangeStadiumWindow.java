package org.unibl.etf.gui;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import org.unibl.etf.classes.FootballClub;
import org.unibl.etf.classes.Stadium;

public class ChangeStadiumWindow extends JFrame {

	private JPanel contentPane;
	private JTextField nameField;
	private JTextField capacityField;
	private JTextField townField;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChangeStadiumWindow frame = new ChangeStadiumWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private StadiumGui frame;
	public void setStadiumFrame(StadiumGui frame) {
		this.frame = frame;
	}
	private int id;
	public void setStadiumId(int id) {
		this.id = id;
	}
	
	public void setData() {
		List<Stadium> data = frame.getData();
		Stadium s = null;
		for(Stadium stad : data) {
			if(stad.getStadionId() == id) {
				s = stad;
			}
		}
		nameField.setText(s.getNaziv());
		capacityField.setText(String.valueOf(s.getKapacitet()));
		townField.setText(s.getGrad());
	}
	
	private ChangeStadiumWindow changeFrame;
	public void setChangeStadiumFrame(ChangeStadiumWindow frame) {
		changeFrame = frame;
	}
	/**
	 * Create the frame.
	 */
	public ChangeStadiumWindow() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 723, 503);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Name = ");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(35, 78, 172, 62);
		contentPane.add(lblNewLabel);
		
		JLabel lblFoundatioDate = new JLabel("Capacity = ");
		lblFoundatioDate.setHorizontalAlignment(SwingConstants.CENTER);
		lblFoundatioDate.setBounds(45, 150, 172, 62);
		contentPane.add(lblFoundatioDate);
		
		JLabel lblNumberOfTrophies = new JLabel("Town = ");
		lblNumberOfTrophies.setHorizontalAlignment(SwingConstants.CENTER);
		lblNumberOfTrophies.setBounds(35, 222, 172, 62);
		contentPane.add(lblNumberOfTrophies);
		
		nameField = new JTextField();
		nameField.setBounds(254, 89, 165, 41);
		contentPane.add(nameField);
		nameField.setColumns(10);
		
		capacityField = new JTextField();
		capacityField.setColumns(10);
		capacityField.setBounds(254, 161, 165, 41);
		contentPane.add(capacityField);
		
		townField = new JTextField();
		townField.setColumns(10);
		townField.setBounds(254, 233, 165, 41);
		contentPane.add(townField);
		
		JButton saveButton = new JButton("SAVE");
		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<Stadium> data = frame.getData();
				Stadium s = null;
				for(Stadium stad : data) {
					if(stad.getStadionId() == id) {
						s = stad;
					}
				}
				if(s != null) {
					boolean anything = false;
					if(!s.getNaziv().equals(nameField.getText())) {
						s.setNaziv(nameField.getText());
						anything = true;
					}
					if(!String.valueOf(s.getKapacitet()).equals(capacityField.getText())) {
						s.setKapacitet(Integer.valueOf(capacityField.getText()));
						anything = true;
					}
					if(!s.getGrad().equals(townField.getText())) {
						s.setGrad(townField.getText());
						anything = true;
					}
					if(anything) {
						frame.update(s);
						try {
							Thread.sleep(1000);
						} catch(InterruptedException ex) {
							ex.printStackTrace();
						}
						changeFrame.dispose();
						frame.dispose();
						StadiumGui sg = new StadiumGui();
						sg.setFrame(sg);
						sg.setVisible(true);
					}
				}
			}
		});
		saveButton.setBounds(35, 399, 643, 57);
		contentPane.add(saveButton);
		
		JLabel lblNewLabel_1 = new JLabel("CHANGE STADIUM");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(35, 10, 642, 29);
		contentPane.add(lblNewLabel_1);
	}
}
