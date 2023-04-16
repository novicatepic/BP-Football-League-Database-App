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
	private JTextField idField;
	public void setStadiumFrame(StadiumGui frame) {
		this.frame = frame;
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
		lblNewLabel.setBounds(35, 129, 172, 62);
		contentPane.add(lblNewLabel);
		
		JLabel lblFoundatioDate = new JLabel("Capacity = ");
		lblFoundatioDate.setHorizontalAlignment(SwingConstants.CENTER);
		lblFoundatioDate.setBounds(35, 192, 172, 62);
		contentPane.add(lblFoundatioDate);
		
		JLabel lblNumberOfTrophies = new JLabel("Town = ");
		lblNumberOfTrophies.setHorizontalAlignment(SwingConstants.CENTER);
		lblNumberOfTrophies.setBounds(35, 250, 172, 62);
		contentPane.add(lblNumberOfTrophies);
		
		nameField = new JTextField();
		nameField.setBounds(254, 140, 165, 41);
		contentPane.add(nameField);
		nameField.setColumns(10);
		
		capacityField = new JTextField();
		capacityField.setColumns(10);
		capacityField.setBounds(254, 203, 165, 41);
		contentPane.add(capacityField);
		
		townField = new JTextField();
		townField.setColumns(10);
		townField.setBounds(254, 261, 165, 41);
		contentPane.add(townField);
		
		JButton saveButton = new JButton("SAVE");
		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<Stadium> data = frame.getData();
				Stadium s = null;
				for(Stadium stad : data) {
					if(stad.getStadionId() == Integer.valueOf(idField.getText())) {
						s = stad;
					}
				}
				if(s != null) {
					boolean anything = false;
					if(!"".equals(nameField.getText())) {
						s.setNaziv(nameField.getText());
						anything = true;
					}
					if(!"".equals(capacityField.getText())) {
						s.setKapacitet(Integer.valueOf(capacityField.getText()));
						anything = true;
					}
					if(!"".equals(townField.getText())) {
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
						frame.dispose();
						StadiumGui sg = new StadiumGui();
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
		
		JLabel lblNumberOfTrophies_1 = new JLabel("Id = ");
		lblNumberOfTrophies_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNumberOfTrophies_1.setBounds(35, 72, 172, 62);
		contentPane.add(lblNumberOfTrophies_1);
		
		idField = new JTextField();
		idField.setColumns(10);
		idField.setBounds(254, 83, 165, 41);
		contentPane.add(idField);
	}
}
