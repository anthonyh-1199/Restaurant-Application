package restaurantapplication.GUI;

import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

import restaurantapplication.Server;

public class ServerGUI extends JPanel {
	
	/* INITIALIZE VARIABLES */

	private JButton logoutButton;
	JLabel employeeLabel;
	JLabel changeOrderLabel;
	JLabel addOrderLabel;
	JTextArea currentOrdersText;
	private Server currentUser;

	/* CONSTRUCTOR */

	public ServerGUI(ApplicationFrame appFrame) {

		//Assign parameters

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
		
		//Format changeOrderLabel
		
		addOrderLabel = new JLabel("Add order: ");
		
		addOrderLabel.setBounds(50, 80, 180, 25);
		
		this.add(addOrderLabel);
		
		//Format changeOrderLabel
		
		changeOrderLabel = new JLabel("Change order: ");
		
		changeOrderLabel.setBounds(50, 110, 180, 25);
		
		this.add(changeOrderLabel);

		//Format loginButton

		logoutButton = new JButton("Logout");

		logoutButton.setBounds(145, 120, 75, 25);	
		
		logoutButton.addActionListener(
				
			new ActionListener() {
				
				public void actionPerformed(ActionEvent e) {
					
					appFrame.changePanel("login");
				
				}
				
			}
			
		);
		
		this.add(logoutButton);
		
	}
	
}
