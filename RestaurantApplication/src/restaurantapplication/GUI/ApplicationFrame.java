package restaurantapplication.GUI;

import java.awt.Container;
import javax.swing.JFrame;
import javax.swing.JPanel;
import restaurantapplication.RestaurantModel;

public class ApplicationFrame extends JFrame {
	
	/* INITIALIZE VARIABLES */
	
	RestaurantModel model;
	
	/* CONSTRUCTOR */

	public ApplicationFrame() {
		
    	//Initialize variables
    	
    	this.model = new RestaurantModel("Tables.csv", "Employees.csv", "Menu.csv", "Orders.csv");
    	
    	//Format frame

    	this.setSize(900, 900);
    	this.setResizable(false);
    	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	
    	//Set default GUI panel
    	
    	this.add(new LoginGUI(this));
    	
    	this.setVisible(true);
		
	}
	
	/* ACCESSOR METHODS */
	
	public RestaurantModel getModel() {
		
		return model;
		
	}
	
	/* CLASS METHODS */
	
    public void changePanel(String nextPanel) {
    	
    	Container contentPane = this.getContentPane();
    	
    	//Remove current panel
    	
    	contentPane.removeAll();
    	contentPane.invalidate();
    	
    	//Add next panel
    	
    	switch (nextPanel) {
    	
    		case "login":
    			this.add(new LoginGUI(this));
    			break;
    			
    		case "busser":
    			this.add(new BusserGUI(this));
    			break;
    			
    		case "cook":
    			this.add(new CookGUI(this));
    			break;
    			
    		case "host":
    			this.add(new HostGUI(this));
    			break;
    			
    		case "manager":
    			this.add(new ManagerGUI(this));
    			break;
    			
    		case "server":
    			this.add(new ServerGUI(this));
    			break;
    	
    	}
    	
    	//Refresh view
    	
    	contentPane.validate();
    	contentPane.setVisible(true);
    	
    }
	
}
