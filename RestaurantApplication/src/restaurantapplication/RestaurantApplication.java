package restaurantapplication;

import javax.swing.*;

import restaurantapplication.GUI.*;

public class RestaurantApplication {

    public static void main(String[] args) {
    	
    	/* INITIALIZE RESTAURANT MODEL */
    	
    	RestaurantModel model = new RestaurantModel("Tables.csv", "Employees.csv", "Menu.csv", "Orders.csv");
    	
    	/* INITIALIZE AND FORMAT MAIN GUI FRAME */

    	JFrame appFrame = new JFrame();
    	
    	appFrame.setSize(900, 900);
    	appFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	
    	
    	JPanel loginPanel = new LoginGUI(model);
    	
    	appFrame.add(loginPanel);
    	
    	appFrame.setVisible(true);

    }
	
}
