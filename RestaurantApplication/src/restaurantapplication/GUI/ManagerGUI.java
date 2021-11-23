package restaurantapplication.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import restaurantapplication.Manager;

public class ManagerGUI extends JPanel {
	
	/* INITIALIZE VARIABLES */

	private JButton logoutButton;
	private ApplicationFrame appFrame;
	private Manager currentUser;
	
	/* CONSTRUCTOR */

	public ManagerGUI(ApplicationFrame appFrame) {

		//Assign parameters

		this.currentUser = new Manager(appFrame.currentUser);

		//Format panel border
		
		this.setBorder (new TitledBorder(new EtchedBorder(), ("Manager - " + currentUser.getFirstname() + " " + currentUser.getLastname() + " #" + currentUser.getId())));
		
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
