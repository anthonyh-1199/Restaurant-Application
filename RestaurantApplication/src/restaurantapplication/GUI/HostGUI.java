package restaurantapplication.GUI;

import javax.swing.*;

public class HostGUI extends JPanel {
	
	/* INITIALIZE VARIABLES */

	private ApplicationFrame appFrame;
	
	/* CONSTRUCTOR */

	public HostGUI(ApplicationFrame appFrame) {

		//Assign parameters
		
		this.appFrame = appFrame;
		
		JLabel employeeLabel = new JLabel("Host");
		
		this.add(employeeLabel);
		
	}
	
}
