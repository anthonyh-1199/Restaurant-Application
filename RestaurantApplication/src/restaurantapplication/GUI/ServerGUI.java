package restaurantapplication.GUI;

import javax.swing.*;

public class ServerGUI extends JPanel {
	
	/* INITIALIZE VARIABLES */

	private ApplicationFrame appFrame;
	
	/* CONSTRUCTOR */

	public ServerGUI(ApplicationFrame appFrame) {

		//Assign parameters
		
		this.appFrame = appFrame;
		
		JLabel employeeLabel = new JLabel("Server");
		
		this.add(employeeLabel);
		
	}
	
}
