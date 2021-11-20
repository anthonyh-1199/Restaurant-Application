package restaurantapplication.GUI;

import javax.swing.*;

public class BusserGUI extends JPanel {
	
	/* INITIALIZE VARIABLES */

	private ApplicationFrame appFrame;
	
	/* CONSTRUCTOR */

	public BusserGUI(ApplicationFrame appFrame) {

		//Assign parameters
		
		this.appFrame = appFrame;
		
		JLabel employeeLabel = new JLabel("Busser");
		
		this.add(employeeLabel);
		
	}
	
}
