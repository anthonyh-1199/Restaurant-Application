package restaurantapplication.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import restaurantapplication.Busser;
import restaurantapplication.Table;

public class BusserGUI extends JPanel {
	
	/* INITIALIZE VARIABLES */

	private ApplicationFrame appFrame;
	private JButton updateTableButton;
	private JButton logoutButton;
	private JComboBox selectTableCombo;
	private JComboBox tableStatusCombo;
	private JLabel tableStatusesLabel;
	private Busser currentUser;
	
	/* CONSTRUCTOR */

	public BusserGUI(ApplicationFrame appFrame) {

		//Assign parameters
		
		this.appFrame = appFrame;

		this.currentUser = new Busser(appFrame.currentUser);

		//Format layout

		this.setLayout(null);

		//Format panel border
		
		this.setBorder (new TitledBorder(new EtchedBorder(), ("Busser - " + currentUser.getFirstname() + " " + currentUser.getLastname() + " #" + currentUser.getId())));
		
		//Format tableStatusesLabel
		
		tableStatusesLabel = new JLabel("Table statuses: ");
		
		tableStatusesLabel.setBounds(50, 80, 180, 25);
		
		this.add(tableStatusesLabel);
		
		//Format selectTableCombo
		
		selectTableCombo = new JComboBox();
		
		refreshSelectTableCombo();

		selectTableCombo.setBounds(150, 80, 45, 25);
		
		selectTableCombo.addActionListener(

			new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					
					//If the selected item isn't empty, make the table data components visible
					
					if (selectTableCombo.getSelectedItem() == null) {
						
						hideTableOptions();
						
						return;
						
					}
					
					if (selectTableCombo.getSelectedItem().toString().equals("")) {
						
						hideTableOptions();
						
						return;
						
					}

					Table selectedTable = appFrame.getModel().getTablesMap().get(Integer.parseInt(selectTableCombo.getSelectedItem().toString()));
					
					updateTableOptions(selectedTable);

					showTableOptions();

				}
				
			}

		);

		this.add(selectTableCombo);
		
		//Format tableStatusCombo
		
		String[] tableStatusComboArray = new String[]{"Clean", "Dirty"};
		
		tableStatusCombo = new JComboBox(tableStatusComboArray);

		tableStatusCombo.setBounds(210, 80, 60, 25);

		this.add(tableStatusCombo);
		
		//Format tableUpdateButton
		
		updateTableButton = new JButton("Update");
		
		updateTableButton.setBounds(280, 80, 80, 25);
		
		updateTableButton.addActionListener(

			new ActionListener() {
				
				public void actionPerformed(ActionEvent e) {

					//Get the current version of the Table object

					Table currentTable = appFrame.getModel().getTablesMap().get(Integer.parseInt(selectTableCombo.getSelectedItem().toString()));
					
					//Update the status of the Table object

					if (tableStatusCombo.getSelectedIndex() == 0) {
						
						currentUser.setTableStatus(currentTable, true);
						
					} else {
					
						currentUser.setTableStatus(currentTable, false);
					
					}
					
					//Update the model
					
					appFrame.getModel().updateTables();
					
					//Show successful dialog box
					
					JOptionPane.showMessageDialog(BusserGUI.this,
							"Table successfully updated.",
						    "Success",
						    JOptionPane.PLAIN_MESSAGE);
					
					//Hide table data

					hideTableOptions();

					//Refresh updateOrderCombo

					refreshSelectTableCombo();

				}
				
			}
			
		);
		
		this.add(updateTableButton);

		//Format logoutButton

		logoutButton = new JButton("Logout");

		logoutButton.setBounds(165, 130, 75, 25);	
		
		logoutButton.addActionListener(
				
			new ActionListener() {
				
				public void actionPerformed(ActionEvent e) {
					
					appFrame.changePanel("login");
				
				}
				
			}
			
		);

		this.add(logoutButton);
		
		//Hide table option components by default
		
		hideTableOptions();

	}
	
	private void hideTableOptions() {
		
		tableStatusCombo.setVisible(false);
		updateTableButton.setVisible(false);
		
	}
	
	private void showTableOptions() {
		
		tableStatusCombo.setVisible(true);
		updateTableButton.setVisible(true);
		
	}
	
	private void updateTableOptions(Table table) {

		if (table.isClean()) {
			
			tableStatusCombo.setSelectedIndex(0);
			
		} else {
			
			tableStatusCombo.setSelectedIndex(1);
			
		}

	}

	private void refreshSelectTableCombo() {
		
		selectTableCombo.removeAllItems();
		
		HashMap<Integer, Table> tables = (appFrame.getModel().getTablesMap());
		
		List<String> list = new ArrayList<String>();
		
		list.add("");
		
		for (Integer key : tables.keySet()) {

			list.add(key.toString());

		}
		
		selectTableCombo.setModel(new DefaultComboBoxModel(list.toArray()));

	}

}