package restaurantapplication.GUI;

import javax.swing.*;

public class CookGUI extends JPanel {
	
	/* INITIALIZE VARIABLES */

	private ApplicationFrame appFrame;
	
	/* CONSTRUCTOR */

	public CookGUI(ApplicationFrame appFrame) {

		//Assign parameters
		
		this.appFrame = appFrame;
		
		JLabel employeeLabel = new JLabel("Cook");
		
		this.add(employeeLabel);
		
	}
	
}
