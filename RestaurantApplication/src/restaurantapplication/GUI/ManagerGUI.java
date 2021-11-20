package restaurantapplication.GUI;

import javax.swing.*;

public class ManagerGUI extends JPanel {
	
	/* INITIALIZE VARIABLES */

	private ApplicationFrame appFrame;
	
	/* CONSTRUCTOR */

	public ManagerGUI(ApplicationFrame appFrame) {

		//Assign parameters
		
		this.appFrame = appFrame;
		
		JLabel employeeLabel = new JLabel("Manager");
		
		this.add(employeeLabel);
		
	}
	
}
