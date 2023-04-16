package org.unibl.etf.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.unibl.etf.classes.FootballClub;
import org.unibl.etf.classes.Stadium;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import java.awt.Font;
import java.util.List;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DeleteClubWindow extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DeleteClubWindow frame = new DeleteClubWindow();
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
	private JComboBox comboBox;
	private TeamGui frame;
	public void setStadiumFrame(TeamGui frame) {
		this.frame = frame;
	}
	List<FootballClub> data;
	
	public void populateData() {
		data = frame.getData();
		for(FootballClub s : data) {
			comboBox.addItem(new String(String.valueOf(s.getIdKluba())));
		}
	}
	
	public DeleteClubWindow() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 287);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("DELETE CLUB");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(55, 10, 334, 39);
		contentPane.add(lblNewLabel);
		
		comboBox = new JComboBox();
		comboBox.setBounds(76, 59, 64, 30);
		contentPane.add(comboBox);
		
		JButton btnNewButton = new JButton("DELETE");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = String.valueOf(comboBox.getSelectedItem());
				
				FootballClub s = null;
				for(FootballClub stad : data) {
					if(stad.getIdKluba() == Integer.valueOf(id)) {
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
					TeamGui sg = new TeamGui();
					sg.setVisible(true);
				}
			}
		});
		btnNewButton.setBounds(33, 196, 366, 44);
		contentPane.add(btnNewButton);
	}
}
