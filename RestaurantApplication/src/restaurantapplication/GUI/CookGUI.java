package restaurantapplication.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import restaurantapplication.Cook;

public class CookGUI extends JPanel {
	
	/* INITIALIZE VARIABLES */

	private JButton logoutButton;
	private ApplicationFrame appFrame;
	private Cook currentUser;
	
	/* CONSTRUCTOR */

	public CookGUI(ApplicationFrame appFrame) {

		//Assign parameters

		this.currentUser = new Cook(appFrame.currentUser);

		//Format panel border
		
		this.setBorder (new TitledBorder(new EtchedBorder(), ("Cook - " + currentUser.getFirstname() + " " + currentUser.getLastname() + " #" + currentUser.getId())));
		
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
