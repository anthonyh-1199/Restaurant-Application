package restaurantapplication.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import restaurantapplication.Cook;
import restaurantapplication.Order;

public class CookGUI extends JPanel {
	
	/* INITIALIZE VARIABLES */

	private JButton logoutButton;
	private JButton setReadyButton;
	private JComboBox selectOrderCombo;
	private JLabel currentOrdersLabel;
	private JScrollPane selectOrderScroll;
	private JTextArea selectOrderText;
	private ApplicationFrame appFrame;
	private Cook currentUser;
	
	/* CONSTRUCTOR */

	public CookGUI(ApplicationFrame appFrame) {

		//Assign parameters
		
		this.appFrame = appFrame;

		this.currentUser = new Cook(appFrame.currentUser);

		//Format layout

		this.setLayout(null);

		//Format panel border
		
		this.setBorder (new TitledBorder(new EtchedBorder(), ("Cook - " + currentUser.getFirstname() + " " + currentUser.getLastname() + " #" + currentUser.getId())));
		
		//Format currentOrdersLabel
		
		currentOrdersLabel = new JLabel("Current orders: ");
		
		currentOrdersLabel.setBounds(50, 80, 180, 25);
		
		this.add(currentOrdersLabel);
		
		//Format selectOrderText
		
		selectOrderText = new JTextArea("");

		selectOrderText.setEditable(false);

		selectOrderScroll = new JScrollPane(selectOrderText);

		selectOrderScroll.setBounds(150, 84, 250, 130);
		
		this.add(selectOrderScroll);

		//Format selectOrderCombo

		selectOrderCombo = new JComboBox();
		
		refreshSelectOrderCombo();

		selectOrderCombo.setBounds(70, 110, 45, 25);

		selectOrderCombo.addActionListener(

			new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					
					//Set contents of changeOrderText to match the selected item
					
					if (selectOrderCombo.getSelectedItem() == null) {
						
						return;
						
					}
					
					if (selectOrderCombo.getSelectedItem().toString().equals("")) {
						
						selectOrderText.setText("");
						
						refreshSelectOrderCombo();
						
						return;
						
					}

					String orderDescription = appFrame.getModel().getOrdersMap().get((Integer.parseInt(selectOrderCombo.getSelectedItem().toString()))).getDescription().replace("\\n", "\n");

					selectOrderText.setText(orderDescription);

				}

			}

		);

		this.add(selectOrderCombo);
		
		//Format setReadyButton
		
		setReadyButton = new JButton("<html>Ready for<br>&nbsp;&nbsp;pickup<html>");

		setReadyButton.setBounds(50, 153, 88, 40);
		
		setReadyButton.addActionListener(
				
			new ActionListener() {
				
				public void actionPerformed(ActionEvent e) {
					
					//Check if the selected Order is blank
					
					if (selectOrderCombo.getSelectedItem() == null) {

						return;
						
					}
					
					if (selectOrderCombo.getSelectedItem().toString().equals("")) {

						JOptionPane.showMessageDialog(CookGUI.this,
							"Error: No order was selected.",
						    "Error",
						    JOptionPane.ERROR_MESSAGE);
						
						refreshSelectOrderCombo();
						
						return;
						
					}
					
					//Update the status of the selected Order as "Ready for pickup"
					
					Order selectedOrder = appFrame.getModel().getOrdersMap().get((Integer.parseInt(selectOrderCombo.getSelectedItem().toString())));
					
					currentUser.setOrderStatus(selectedOrder, "Ready for pickup");
					
					//Update the model
					
					appFrame.getModel().updateOrders();
					
					//Show successful dialog box
					
					JOptionPane.showMessageDialog(CookGUI.this,
						"Order #" + selectOrderCombo.getSelectedItem().toString() + " marked for pickup.",
					    "Success",
					    JOptionPane.PLAIN_MESSAGE);
					
					//Clear selectOrderText
					
					selectOrderText.setText("");
					
					//Refresh selectOrderCombo()
					
					refreshSelectOrderCombo();

				}
				
			}
			
		);

		this.add(setReadyButton);

		//Format loginButton

		logoutButton = new JButton("Logout");

		logoutButton.setBounds(180, 355, 75, 25);
		
		logoutButton.addActionListener(
				
			new ActionListener() {
				
				public void actionPerformed(ActionEvent e) {
					
					appFrame.changePanel("login");
				
				}
				
			}
			
		);
		
		this.add(logoutButton);
		
	}
	
	/* CLASS METHODS */
	
	private void refreshSelectOrderCombo() {
		
		selectOrderCombo.removeAllItems();
		
		HashMap<Integer, Order> orders = (appFrame.getModel().getOrdersMap());
		
		List<String> list = new ArrayList<String>();
		
		list.add("");
		
		for (Integer key : orders.keySet()) {

			//If the order is not completed, add it to the list
			
			if (orders.get(key).getStatus().equals("In the kitchen")) {
				
				list.add(key.toString());

			}
			
		}
		
		selectOrderCombo.setModel(new DefaultComboBoxModel(list.toArray()));

	}
	
}
