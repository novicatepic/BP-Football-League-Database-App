package org.unibl.etf.gui;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import org.unibl.etf.classes.Stadium;

import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;

public class DeleteStadiumWindow extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DeleteStadiumWindow frame = new DeleteStadiumWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	private JComboBox comboBox;
	private StadiumGui frame;
	public void setStadiumFrame(StadiumGui frame) {
		this.frame = frame;
	}
	List<Stadium> data;
	
	public void populateData() {
		data = frame.getData();
		for(Stadium s : data) {
			comboBox.addItem(new String(String.valueOf(s.getStadionId())));
		}
	}
	
	/**
	 * Create the frame.
	 */
	public DeleteStadiumWindow() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 287);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("DELETE STADIUM");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(55, 10, 334, 39);
		contentPane.add(lblNewLabel);
		
		comboBox = new JComboBox();
		comboBox.setBounds(72, 76, 38, 21);
		contentPane.add(comboBox);
		//List<Stadium> data = frame.getData();
		
		
		
		JButton btnNewButton = new JButton("DELETE");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String id = String.valueOf(comboBox.getSelectedItem());
				
				Stadium s = null;
				for(Stadium stad : data) {
					if(stad.getStadionId() == Integer.valueOf(id)) {
						s = stad;
					}
				}
				if(s != null) {
					frame.delete(s);
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
		});
		btnNewButton.setBounds(33, 196, 366, 44);
		contentPane.add(btnNewButton);
	}

	public void dispose() {
		this.dispose();
	}
	
}
