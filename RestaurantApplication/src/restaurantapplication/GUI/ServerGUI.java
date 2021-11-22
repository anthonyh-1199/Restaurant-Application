package restaurantapplication.GUI;

import java.awt.event.*;
import javax.swing.*;

import restaurantapplication.Server;

public class ServerGUI extends JPanel {
	
	/* INITIALIZE VARIABLES */

	private JButton logoutButton;
	private Server currentUser;

	/* CONSTRUCTOR */

	public ServerGUI(ApplicationFrame appFrame) {

		//Assign parameters

		this.currentUser = new Server(appFrame.currentUser);

		//Format layout

		this.setLayout(null);

		//Format employeeLabel

		JLabel employeeLabel = new JLabel("Server - " + currentUser.getFirstname() + " " + currentUser.getLastname() + " #" + currentUser.getId());
		
		employeeLabel.setBounds(50, 50, 180, 25);
		
		this.add(employeeLabel);

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
