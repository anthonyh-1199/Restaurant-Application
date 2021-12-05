package restaurantapplication.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import restaurantapplication.Host;
import restaurantapplication.Table;

public class HostGUI extends JPanel {
	
	/* INITIALIZE VARIABLES */

	private ApplicationFrame appFrame;
	private JButton updateTableButton;
	private JButton logoutButton;
	private JComboBox selectTableCombo;
	private JComboBox tableStatusCombo;
	private JLabel selectedTableLabel;
	private JLabel tableCapacityLabel;
	private JTextArea tableCapacityText;
        private int maxCapacity = 0;

	private Host currentUser;
	
	/* CONSTRUCTOR */

	public HostGUI(ApplicationFrame appFrame) {

		//Assign parameters
		
		this.appFrame = appFrame;

		this.currentUser = new Host(appFrame.currentUser);

		//Format layout

		this.setLayout(null);

		//Format panel border
		
		this.setBorder (new TitledBorder(new EtchedBorder(), ("Host - " + currentUser.getFirstname() + " " + currentUser.getLastname() + " #" + currentUser.getId())));

		//Format selectedTableLabel
		
		selectedTableLabel = new JLabel("<html>Selected table: &nbsp;&nbsp;&nbsp&nbsp#<html>");

		selectedTableLabel.setBounds(135, 80, 180, 25);
		
		this.add(selectedTableLabel);

		//Format selectTableCombo
		
		selectTableCombo = new JComboBox();
		
		refreshSelectTableCombo();

		selectTableCombo.setBounds(245, 80, 45, 25);
		
		selectTableCombo.addActionListener(

				new ActionListener() {

					public void actionPerformed(ActionEvent e) {
						
						//If the selected item isn't empty, make the table data components visible
						
						if (selectTableCombo.getSelectedItem() == null) {
							
							hideTableData();
							
							return;
							
						}
						
						if (selectTableCombo.getSelectedItem().toString().equals("")) {
							
							hideTableData();
							
							return;
							
						}

						Table selectedTable = appFrame.getModel().getTablesMap().get(Integer.parseInt(selectTableCombo.getSelectedItem().toString()));
						
						updateTableData(selectedTable);

						showTableData();

					}

				}

			);

		this.add(selectTableCombo);
		
		//Format tableDataLabel
		
		selectedTableLabel = new JLabel("<html>Table capacity: <br/><br/>Table status: <html>");
		
		selectedTableLabel.setBounds(135, 97, 180, 86);
		
		this.add(selectedTableLabel);
		
		//Format tableCapacityText
		
		tableCapacityText = new JTextArea("");
		
		tableCapacityText.setBounds(247, 116, 18, 17);

		this.add(tableCapacityText);
		
		//Format tableCapacityLabel
		
		tableCapacityLabel = new JLabel("/" + maxCapacity);
		
		tableCapacityLabel.setBounds(270, 97, 180, 55);
		
		this.add(tableCapacityLabel);
		
		//Format tableStatusCombo
		
		String[] tableStatusComboArray = new String[]{"Clean", "Dirty"};
		
		tableStatusCombo = new JComboBox(tableStatusComboArray);

		tableStatusCombo.setBounds(245, 145, 60, 25);

		this.add(tableStatusCombo);
		
		//Format updateTableButton
		
		updateTableButton = new JButton("Update table");
		
		updateTableButton.setBounds(155, 190, 125, 25);	
		
		updateTableButton.addActionListener(

			new ActionListener() {
				
				public void actionPerformed(ActionEvent e) {
					
					/* INITIALIZE VARIABLES */
					
					int tableCapacity;

					/* PARSE tableCapacityText */
					
					if (tableCapacityText.getText().isBlank()) {
						
						tableCapacity = 0;
						
					} else {
					
						try {
							
							tableCapacity = Integer.parseInt(tableCapacityText.getText());
							
						} catch(Exception x) {
							
							JOptionPane.showMessageDialog(HostGUI.this,
									"Error: Invalid table capacity.",
								    "Error",
								    JOptionPane.ERROR_MESSAGE);
	
							return;
							
						}
						
					}

					//Get the current version of the Table object

					Table currentTable = appFrame.getModel().getTablesMap().get(Integer.parseInt(selectTableCombo.getSelectedItem().toString()));
					
                                        maxCapacity = currentTable.getMaximumCapacity();
					//Update the capacity and status of the Table object
					
					currentUser.setTableCapacity(currentTable, tableCapacity);
					if (tableCapacity > -1 && tableCapacity <= maxCapacity ){
                                            if (tableStatusCombo.getSelectedIndex() == 0) {
						
						currentUser.setTableStatus(currentTable, true);
						
                                            } else {
					
						currentUser.setTableStatus(currentTable, false);
					
					} 
                                            //Update the model
					
					appFrame.getModel().updateTables();
					
					//Show successful dialog box
					
					JOptionPane.showMessageDialog(HostGUI.this,
							"Table successfully updated.",
						    "Success",
						    JOptionPane.PLAIN_MESSAGE);
					
                                        }
                                        else {
                                            JOptionPane.showMessageDialog(HostGUI.this,
								"Error: Invalid table capacity.",
								    "Error",
								    JOptionPane.ERROR_MESSAGE);
                                        }
					//Hide table data

					hideTableData();

					//Refresh updateOrderCombo

					refreshSelectTableCombo();
				}
				
			}
			
		);
		
		this.add(updateTableButton);

		//Format logoutButton

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
		
		//Hide table data by default
		
		hideTableData();
		
	}
	
	private void hideTableData() {
		
		selectedTableLabel.setVisible(false);
		tableCapacityText.setVisible(false);
		tableCapacityLabel.setVisible(false);
		tableStatusCombo.setVisible(false);
		updateTableButton.setVisible(false);
		
	}
	
	private void showTableData() {
		
		selectedTableLabel.setVisible(true);
		tableCapacityText.setVisible(true);
		tableCapacityLabel.setVisible(true);
		tableStatusCombo.setVisible(true);
		updateTableButton.setVisible(true);
		
	}
	
	private void updateTableData(Table table) {

		tableCapacityText.setText(table.getCurrentCapacity() + "");
		
		tableCapacityLabel.setText("/ " + table.getMaximumCapacity());
		
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
