package restaurantapplication.GUI;

import java.awt.event.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import javax.swing.*;
import javax.swing.border.*;

import restaurantapplication.MenuItem;
import restaurantapplication.Order;
import restaurantapplication.RestaurantModel;
import restaurantapplication.Server;

public class ServerGUI extends JPanel {
	
	/* INITIALIZE VARIABLES */

	private ApplicationFrame appFrame;
	private JButton logoutButton;
	private JButton addOrderButton;
	private JComboBox changeOrderCombo;
	private JLabel changeOrderLabel;
	private JLabel addOrderLabel;
	private JTextArea addOrderText;
	private JTextArea changeOrderDescriptions;
	private JTextArea currentOrdersText;
	private JScrollPane changeOrderScroll;
	private JScrollPane addOrderScroll;
	private Server currentUser;

	/* CONSTRUCTOR */

	public ServerGUI(ApplicationFrame appFrame) {

		//Assign parameters
		
		this.appFrame = appFrame;

		this.currentUser = new Server(appFrame.currentUser);

		//Format layout

		this.setLayout(null);

		//Format panel border
		
		this.setBorder (new TitledBorder(new EtchedBorder(), ("Server - " + currentUser.getFirstname() + " " + currentUser.getLastname() + " #" + currentUser.getId())));

		//Format currentOrdersText

		currentOrdersText = new JTextArea(16, 58);
		currentOrdersText.setEditable(false); // set textArea non-editable
	    JScrollPane scroll = new JScrollPane(currentOrdersText);
	    scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

	    this.add(scroll);

		//Format addOrderLabel
		
		addOrderLabel = new JLabel("Add order: ");
		
		addOrderLabel.setBounds(50, 80, 180, 25);
		
		this.add(addOrderLabel);
		
		//Format addOrderText
		
		addOrderText = new JTextArea("");

		addOrderScroll = new JScrollPane(addOrderText);

		addOrderScroll.setBounds(150, 84, 250, 100);
		
		this.add(addOrderScroll);
		
		//Format addOrderButton

		addOrderButton = new JButton("Add order");

		addOrderButton.setBounds(50, 110, 90, 25);
		
		addOrderButton.addActionListener(
				
			new ActionListener() {
				
				public void actionPerformed(ActionEvent e) {
					
					//Check that the description of the new Order object isn't blank
					
					if (addOrderText.getText().isBlank()) {
						
						JOptionPane.showMessageDialog(ServerGUI.this,
								"Error: Blank order description.",
							    "Error",
							    JOptionPane.ERROR_MESSAGE);
						
						return;
						
					}
					
					//Initialize parameters for new Order object
					
					int orderId;
					
					try {
					
						orderId = Collections.max(appFrame.getModel().getOrdersMap().keySet()) + 1;
						
					} catch(Exception ex) {
						
						orderId = 1;
						
					}
					
					String orderDescription = addOrderText.getText().replace("\n", "\\n");

					int tableId = 1;
					
					String orderStatus = "In the kitchen";
					
					//Create new Order object and add it to the model

					currentUser.addOrder(new Order(orderId, orderDescription, tableId, orderStatus), appFrame.getModel());
					
					//Show successful dialog box
					
					JOptionPane.showMessageDialog(ServerGUI.this,
							"Order successfully added.",
						    "Success",
						    JOptionPane.PLAIN_MESSAGE);
					
					//Clear addOrderText
					
					addOrderText.setText("");

				}
				
			}
			
		);
		
		this.add(addOrderButton);

		//Format changeOrderLabel
		
		changeOrderLabel = new JLabel("Change order: ");
		
		changeOrderLabel.setBounds(50, 190, 180, 25);
		
		//this.add(changeOrderLabel);

		//Format changeOrderCombo

		changeOrderCombo = new JComboBox(GetUnfinishedOrderNumbers().toArray());

		changeOrderCombo.setBounds(70, 220, 45, 25);

		changeOrderCombo.addActionListener(

			new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					
					if (changeOrderCombo.getSelectedItem().toString().equals("")) {
						
						changeOrderDescriptions.setText("Order description");
						
						return;
						
					}
					
					
					String orderDescription = appFrame.getModel().getOrdersMap().get((Integer.parseInt(changeOrderCombo.getSelectedItem().toString()))).getDescription().replace("\\n", "\n");

					changeOrderDescriptions.setText(orderDescription);

				}

			}

		);

		//this.add(changeOrderCombo);
		
		
		
		
		
		
		//Format changeOrderDescription

		changeOrderDescriptions = new JTextArea("");

		changeOrderScroll = new JScrollPane(changeOrderDescriptions);

		changeOrderScroll.setBounds(150, 192, 250, 100);
		
		//this.add(changeOrderScroll);
		
		
		
		
		
		
		
		

		//Format logoutButton

		logoutButton = new JButton("Logout");

		logoutButton.setBounds(145, 300, 75, 25);	
		
		logoutButton.addActionListener(
				
			new ActionListener() {
				
				public void actionPerformed(ActionEvent e) {
					
					appFrame.changePanel("login");
				
				}
				
			}
			
		);
		
		this.add(logoutButton);
		
	}

	private List<String> GetUnfinishedOrderNumbers() {
		
		HashMap<Integer, Order> orders = (appFrame.getModel().getOrdersMap());
		
		List<String> list = new ArrayList<String>();
		
		list.add("");
		
		for (Integer key : orders.keySet()) {

			//If the order is not completed, add it to the list
			
			if (!orders.get(key).getStatus().equals("Completed")) {
				
				list.add(key.toString());
				
			}
			
		}
		
		return list;
		
	}
	
}
