package restaurantapplication.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import restaurantapplication.Busser;

public class BusserGUI extends JPanel {
	
	/* INITIALIZE VARIABLES */

	private JButton logoutButton;
	private ApplicationFrame appFrame;
	private Busser currentUser;
	
	/* CONSTRUCTOR */

	public BusserGUI(ApplicationFrame appFrame) {

		//Assign parameters

		this.currentUser = new Busser(appFrame.currentUser);

		//Format panel border
		
		this.setBorder (new TitledBorder(new EtchedBorder(), ("Busser - " + currentUser.getFirstname() + " " + currentUser.getLastname() + " #" + currentUser.getId())));
		
		//Format loginButton

		logoutButton = new JButton("Logout");

		logoutButton.setBounds(145, 120, 75, 25);	
		
		logoutButton.addActionListener(
				
			new ActionListener() {
				
				public void actionPerformed(ActionEvent e) {
					
					appFrame.changePanel("login");
				
				}
				
			}
			
		);
		
		this.add(logoutButton);
		
	}
	
}
