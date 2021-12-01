package restaurantapplication.GUI;

import java.awt.event.*;
import java.util.*;

import javax.swing.*;
import javax.swing.border.*;

import restaurantapplication.Order;
import restaurantapplication.Server;

public class ServerGUI extends JPanel {
	
	/* INITIALIZE VARIABLES */

	private ApplicationFrame appFrame;
	private JButton addOrderButton;
	private JButton updateOrderButton;
	private JButton logoutButton;
	private JComboBox updateOrderCombo;
	private JLabel addOrderLabel;
	private JLabel addTableLable;
	private JLabel updateOrderLabel;
	private JTextArea addOrderText;
	private JTextArea addTableText;
	private JTextArea updateOrderText;
	private JTextArea currentOrdersText;
	private JScrollPane updateOrderScroll;
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
		
		//Format addTableLable
		
		addTableLable = new JLabel("Table #");
		
		addTableLable.setBounds(50, 110, 70, 25);
		
		this.add(addTableLable);
		
		//Format addTableText
		
		addTableText = new JTextArea("");
		
		addTableText.setBounds(100, 113, 30, 20);

		this.add(addTableText);
		
		//Format addOrderButton

		addOrderButton = new JButton("Add order");

		addOrderButton.setBounds(50, 140, 90, 25);
		
		addOrderButton.addActionListener(
				
			new ActionListener() {
				
				public void actionPerformed(ActionEvent e) {
					
					//Initialize variables
					
					int orderId, tableId;
					String orderDescription, orderStatus;
					
					//Check that the description of the new Order object isn't blank
					
					if (addOrderText.getText().isBlank()) {
						
						JOptionPane.showMessageDialog(ServerGUI.this,
								"Error: Blank order description.",
							    "Error",
							    JOptionPane.ERROR_MESSAGE);
						
						return;
						
					}
					
					//Check that the table ID in addTableText is valid

					try {
						
						tableId = Integer.parseInt(addTableText.getText());

						if (!(appFrame.getModel().getEmployeesMap()).containsKey(tableId)) {
							
							JOptionPane.showMessageDialog(ServerGUI.this,
									"Error: Table ID not found.",
								    "Error",
								    JOptionPane.ERROR_MESSAGE);

							return;
							
						}
						
					} catch(Exception x) {
						
						JOptionPane.showMessageDialog(ServerGUI.this,
								"Error: Invalid table ID.",
							    "Error",
							    JOptionPane.ERROR_MESSAGE);

						return;
						
					}
					
					//Initialize parameters for new Order object

					try {
					
						orderId = Collections.max(appFrame.getModel().getOrdersMap().keySet()) + 1;
						
					} catch(Exception ex) {
						
						orderId = 1;
						
					}
					
					orderDescription = addOrderText.getText().replace("\n", "\\n");

					orderStatus = "In the kitchen";
					
					//Create new Order object and add it to the model

					currentUser.addOrder(new Order(orderId, orderDescription, tableId, orderStatus), appFrame.getModel());
					
					//Show successful dialog box
					
					JOptionPane.showMessageDialog(ServerGUI.this,
							"Order successfully added.",
						    "Success",
						    JOptionPane.PLAIN_MESSAGE);
					
					//Clear JTextAreas
					
					addOrderText.setText("");
					addTableText.setText("");

					//Refresh updateOrderCombo
					
					refreshUpdateOrderCombo();

				}
				
			}
			
		);
		
		this.add(addOrderButton);

		//Format changeOrderLabel
		
		updateOrderLabel = new JLabel("Change order: ");
		
		updateOrderLabel.setBounds(50, 190, 180, 25);
		
		this.add(updateOrderLabel);

		//Format changeOrderCombo

		updateOrderCombo = new JComboBox();
		
		refreshUpdateOrderCombo();

		updateOrderCombo.setBounds(70, 220, 45, 25);

		updateOrderCombo.addActionListener(

			new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					
					//Set contents of changeOrderText to match the selected item
					
					if (updateOrderCombo.getSelectedItem() == null) {
						
						return;
						
					}
					
					if (updateOrderCombo.getSelectedItem().toString().equals("")) {
						
						updateOrderText.setText("");
						
						return;
						
					}

					String orderDescription = appFrame.getModel().getOrdersMap().get((Integer.parseInt(updateOrderCombo.getSelectedItem().toString()))).getDescription().replace("\\n", "\n");

					updateOrderText.setText(orderDescription);
					
					//Refresh updateOrderCombo
					
					refreshUpdateOrderCombo();

				}

			}

		);

		this.add(updateOrderCombo);
		
		//Format changeOrderButton
		
		updateOrderButton = new JButton("Update");
		
		updateOrderButton.setBounds(55, 250, 75, 25);
		
		updateOrderButton.addActionListener(

			new ActionListener() {
				
				public void actionPerformed(ActionEvent e) {
					
					//Check that the description of the new Order object isn't blank
					
					if (updateOrderText.getText().isBlank()) {
						
						JOptionPane.showMessageDialog(ServerGUI.this,
								"Error: Blank order description.",
							    "Error",
							    JOptionPane.ERROR_MESSAGE);
						
						return;
						
					}
					
					//Get the current version of the Order object
					
					Order currentOrder = appFrame.getModel().getOrdersMap().get((Integer.parseInt(updateOrderCombo.getSelectedItem().toString())));
					
					//Update the description of the Order object
					
					currentOrder.setDescription(updateOrderText.getText().replace("\n", "\\n"));
					
					//Update the model
					
					appFrame.getModel().updateOrders();
					
					//Show successful dialog box
					
					JOptionPane.showMessageDialog(ServerGUI.this,
							"Order successfully updated.",
						    "Success",
						    JOptionPane.PLAIN_MESSAGE);
					
					//Clear addOrderText
					
					updateOrderText.setText("");
					
					//Refresh updateOrderCombo
					
					refreshUpdateOrderCombo();

				}
				
			}
			
		);
	
		this.add(updateOrderButton);
		
		
		
		
		//Format changeOrderDescription

		updateOrderText = new JTextArea("");

		updateOrderScroll = new JScrollPane(updateOrderText);

		updateOrderScroll.setBounds(150, 192, 250, 100);
		
		this.add(updateOrderScroll);
		
		
		
		
		
		
		
		

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

	private void refreshUpdateOrderCombo() {
		
		updateOrderCombo.removeAllItems();
		
		HashMap<Integer, Order> orders = (appFrame.getModel().getOrdersMap());
		
		List<String> list = new ArrayList<String>();
		
		list.add("");
		
		for (Integer key : orders.keySet()) {

			//If the order is not completed, add it to the list
			
			if (!orders.get(key).getStatus().equals("Completed")) {
				
				list.add(key.toString());

			}
			
		}
		
		updateOrderCombo.setModel(new DefaultComboBoxModel(list.toArray()));

	}
	
}
