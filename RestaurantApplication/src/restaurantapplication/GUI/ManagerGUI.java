package restaurantapplication.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import restaurantapplication.Employee;
import restaurantapplication.Manager;
import restaurantapplication.Table;

public class ManagerGUI extends JPanel {
	
	/* INITIALIZE VARIABLES */

	private ApplicationFrame appFrame;
	private JButton logoutButton;
	private JButton updateEmployeeButton;
	private JButton updateTableButton;
	private JComboBox employeePositionCombo;
	private JComboBox tableStatusCombo;
	private JComboBox updateRecordIdCombo;
	private JComboBox updateRecordTypeCombo;
	private JLabel employeeNameLabel;
	private JLabel employeePasswordLabel;
	private JLabel employeePositionLabel;
	private JLabel tableCapacityLabel;
	private JLabel tableMaxCapacityLabel;
	private JLabel tableStatusLabel;
	private JLabel updateRecordLabel;
	private JTextArea employeeFirstNameText;
	private JTextArea employeeLastNameText;
	private JTextArea employeePasswordText;
	private JTextArea tableCapacityText;
	private JTextArea tableMaxCapacityText;
	private Manager currentUser;

	/* CONSTRUCTOR */

	public ManagerGUI(ApplicationFrame appFrame) {

		//Assign parameters

		this.currentUser = new Manager(appFrame.currentUser);
		
		this.appFrame = appFrame;
		
		//Format layout

		this.setLayout(null);

		//Format panel border
		
		this.setBorder (new TitledBorder(new EtchedBorder(), ("Manager - " + currentUser.getFirstname() + " " + currentUser.getLastname() + " #" + currentUser.getId())));

		//Format updateRecordLabel
		
		updateRecordLabel = new JLabel("Update record: ");
		
		updateRecordLabel.setBounds(50, 80, 180, 25);
		
		this.add(updateRecordLabel);
		
		//Format updateRecordTypeCombo
		
		String[] updateRecordTypeComboArray = new String[]{"", "Employee", "Table"};
		
		updateRecordTypeCombo = new JComboBox(updateRecordTypeComboArray);

		updateRecordTypeCombo.setBounds(145, 80, 90, 25);
		
		updateRecordTypeCombo.addActionListener(
				
			new ActionListener() {
				
				public void actionPerformed(ActionEvent e) {
					
					//If the selected item isn't empty, make the table data components visible
					
					if (updateRecordTypeCombo.getSelectedItem() == null) {

						return;
						
					}

					switch (updateRecordTypeCombo.getSelectedItem().toString()) {
					
						case "Employee":
						case "Table":
							
							updateRecordIdCombo.setVisible(true);
							
							break;

						default:
							
							updateRecordIdCombo.setVisible(false);
							
							hideEmployeeOptions();
							
							hideTableOptions();
							
							break;
					
					}
					
					refreshUpdateRecordIdCombo();
				
				}
				
			}
			
		);

		this.add(updateRecordTypeCombo);
		
		//Format updateRecordIdCombo

		updateRecordIdCombo = new JComboBox();

		updateRecordIdCombo.setBounds(260, 80, 45, 25);
		
		updateRecordIdCombo.addActionListener(
			
			new ActionListener() {
				
				public void actionPerformed(ActionEvent e) {
					
					//If the selected item isn't empty, make the table data components visible
					
					if (updateRecordIdCombo.getSelectedItem() == null) {

						return;
						
					}

					switch (updateRecordIdCombo.getSelectedItem().toString()) {
					
						case "":
							
							hideEmployeeOptions();
							
							hideTableOptions();
							
						default:
							
							if (updateRecordIdCombo.getSelectedItem().toString().equals("")) {
								
								hideEmployeeOptions();
								
								hideTableOptions();
								
								break;
								
							}
							
							switch (updateRecordTypeCombo.getSelectedItem().toString()) {
							
								case "Employee":
									
									//Set table options to the selected table
									
									Employee selectedEmployee = appFrame.getModel().getEmployeesMap().get(Integer.parseInt(updateRecordIdCombo.getSelectedItem().toString()));
									
									updateEmployeeOptions(selectedEmployee);
									
									showEmployeeOptions();
									
									hideTableOptions();
									
									break;
									
								case "Table":

									//Set table options to the selected table
									
									Table selectedTable = appFrame.getModel().getTablesMap().get(Integer.parseInt(updateRecordIdCombo.getSelectedItem().toString()));
									
									updateTableOptions(selectedTable);
									
									hideEmployeeOptions();
									
									showTableOptions();
									
									break;
							
							}

							break;
					
					}

				}
				
			}
			
		);
		
		this.add(updateRecordIdCombo);
		
		/* EMPLOYEE OPTIONS COMPONENTS */
		
		//Format employeeNameLabel
		
		employeeNameLabel = new JLabel("Employee name: ");
		
		employeeNameLabel.setBounds(85, 125, 120, 18);
		
		this.add(employeeNameLabel);
		
		//Format employeeFirstNameLabel
		
		employeeFirstNameText = new JTextArea("Constantine");
		
		employeeFirstNameText.setBounds(185, 126, 80, 18);
		
		this.add(employeeFirstNameText);
		
		//Format employeeLastNameLabel
		
		employeeLastNameText = new JTextArea("Constantine");

		employeeLastNameText.setBounds(275, 126, 80, 18);

		this.add(employeeLastNameText);
		
		//Format employeePositionLabel
		
		employeePositionLabel = new JLabel("Employee position: ");
		
		employeePositionLabel.setBounds(85, 156, 120, 18);
		
		this.add(employeePositionLabel);
		
		//Format employeePositionCombo
		
		String[] employeePositionComboArray = new String[]{"Host", "Server", "Cook", "Busser", "Manager"};
		
		employeePositionCombo = new JComboBox(employeePositionComboArray);

		employeePositionCombo.setBounds(200, 155, 90, 22);
		
		this.add(employeePositionCombo);
		
		//Format employeePasswordLabel
		
		employeePasswordLabel = new JLabel("Employee password: ");
		
		employeePasswordLabel.setBounds(85, 187, 150, 18);
		
		this.add(employeePasswordLabel);
		
		//Format employeePasswordText
		
		employeePasswordText = new JTextArea("Constantine");
		
		employeePasswordText.setBounds(210, 188, 80, 18);
		
		this.add(employeePasswordText);
		
		//Format updateEmployeeButton
		
		updateEmployeeButton = new JButton("Update employee");
		
		updateEmployeeButton.setBounds(147, 227, 135, 25);
		
		updateEmployeeButton.addActionListener(

			new ActionListener() {
				
				public void actionPerformed(ActionEvent e) {

					//Get the current version of the Table object

					Employee selectedEmployee = appFrame.getModel().getEmployeesMap().get(Integer.parseInt(updateRecordIdCombo.getSelectedItem().toString()));
					
					//Security check - employees cannot change their own positions
					
					if (!employeePositionCombo.getSelectedItem().toString().toLowerCase().equals(selectedEmployee.getPosition())) {
						
						if (selectedEmployee.getId() == currentUser.getId()) {
							
							JOptionPane.showMessageDialog(ManagerGUI.this,
									"Forbidden: Users cannot change their own position.",
								    "Forbidden Action",
								    JOptionPane.ERROR_MESSAGE);
							
							return;
							
						}
						
					}

					//Update the status of the Table object
					
					selectedEmployee.setFirstname(employeeFirstNameText.getText());
					
					selectedEmployee.setLastname(employeeLastNameText.getText());
					
					selectedEmployee.setPassword(employeePasswordText.getText());
					
					selectedEmployee.setPosition(employeePositionCombo.getSelectedItem().toString().toLowerCase());

					//Update the model
					
					appFrame.getModel().updateEmployees();
					
					//Show successful dialog box
					
					JOptionPane.showMessageDialog(ManagerGUI.this,
							"Employee record successfully updated.",
						    "Success",
						    JOptionPane.PLAIN_MESSAGE);
					
					//Hide table data
					
					hideTableOptions();

					tableStatusCombo.setSelectedIndex(0);

					//Refresh updateOrderCombo

					refreshUpdateRecordIdCombo();

				}
				
			}
			
		);
		
		this.add(updateEmployeeButton);

		/* TABLE OPTIONS COMPONENTS */
		
		//Format tableCapacityLabel
		
		tableCapacityLabel = new JLabel("Current capacity: ");
		
		tableCapacityLabel.setBounds(85, 125, 120, 18);
		
		this.add(tableCapacityLabel);
		
		//Format tableCapacityText
		
		tableCapacityText = new JTextArea("11");
		
		tableCapacityText.setBounds(195, 126, 30, 18);
		
		this.add(tableCapacityText);
		
		//Format tableMaxCapacityLabel
		
		tableMaxCapacityLabel = new JLabel("Maximum capacity: ");
		
		tableMaxCapacityLabel.setBounds(85, 156, 120, 18);
		
		this.add(tableMaxCapacityLabel);
		
		//Format tableMaxCapacityText
		
		tableMaxCapacityText = new JTextArea("11");
		
		tableMaxCapacityText.setBounds(208, 157, 30, 18);
		
		this.add(tableMaxCapacityText);
		
		//Format tableStatusLabel
		
		tableStatusLabel = new JLabel("Status: ");
		
		tableStatusLabel.setBounds(85, 187, 120, 18);
		
		this.add(tableStatusLabel);
		
		//Format tableStatusCombo
		
		String[] tableStatusComboArray = new String[]{"Clean", "Dirty"};
		
		tableStatusCombo = new JComboBox(tableStatusComboArray);

		tableStatusCombo.setBounds(135, 186, 60, 22);

		this.add(tableStatusCombo);
		
		//Format updateTableButton
		
		updateTableButton = new JButton("Update table");
		
		updateTableButton.setBounds(164, 225, 105, 25);
		
		updateTableButton.addActionListener(

			new ActionListener() {
				
				public void actionPerformed(ActionEvent e) {

					//Get the current version of the Table object

					Table selectedTable = appFrame.getModel().getTablesMap().get(Integer.parseInt(updateRecordIdCombo.getSelectedItem().toString()));
					
					//Update the status of the Table object
					
					try {

						selectedTable.setCurrentCapacity(Integer.parseInt(tableCapacityText.getText()));
						
						selectedTable.setMaximumCapacity(Integer.parseInt(tableMaxCapacityText.getText()));
						
					} catch(Exception ex) {
						
						JOptionPane.showMessageDialog(ManagerGUI.this,
								"Error: Invalid table capacity.",
							    "Error",
							    JOptionPane.ERROR_MESSAGE);
						
						return;
						
					}
					
					if (tableStatusCombo.getSelectedIndex() == 0) {

						selectedTable.setClean();
						
					} else {
					
						selectedTable.setDirty();
					
					}
					
					//Update the model
					
					appFrame.getModel().updateTables();
					
					//Show successful dialog box
					
					JOptionPane.showMessageDialog(ManagerGUI.this,
							"Table record successfully updated.",
						    "Success",
						    JOptionPane.PLAIN_MESSAGE);
					
					//Hide table data
					
					hideTableOptions();

					tableStatusCombo.setSelectedIndex(0);

					//Refresh updateOrderCombo

					refreshUpdateRecordIdCombo();

				}
				
			}
			
		);
	
		this.add(updateTableButton);

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
		
		//Refresh ComboBox components to initial values
		
		refreshUpdateRecordIdCombo();
		
		//Hide components by default
		
		updateRecordIdCombo.setVisible(false);
		hideEmployeeOptions();
		hideTableOptions();
		
	}
	
	/* CLASS METHODS */
	
	private void hideEmployeeOptions() {

		employeeNameLabel.setVisible(false);
		employeeFirstNameText.setVisible(false);
		employeeLastNameText.setVisible(false);
		employeePositionLabel.setVisible(false);
		employeePasswordLabel.setVisible(false);
		employeePositionCombo.setVisible(false);
		employeePasswordText.setVisible(false);
		updateEmployeeButton.setVisible(false);

	}
	
	private void showEmployeeOptions() {

		employeeNameLabel.setVisible(true);
		employeeFirstNameText.setVisible(true);
		employeeLastNameText.setVisible(true);
		employeePositionLabel.setVisible(true);
		employeePasswordLabel.setVisible(true);
		employeePositionCombo.setVisible(true);
		employeePasswordText.setVisible(true);
		updateEmployeeButton.setVisible(true);
		
	}
	
	private void hideTableOptions() {

		tableCapacityLabel.setVisible(false);
		tableCapacityText.setVisible(false);
		tableMaxCapacityLabel.setVisible(false);
		tableMaxCapacityText.setVisible(false);
		tableStatusLabel.setVisible(false);
		tableStatusCombo.setVisible(false);
		updateTableButton.setVisible(false);
		
	}
	
	private void showTableOptions() {

		tableCapacityLabel.setVisible(true);
		tableCapacityText.setVisible(true);
		tableMaxCapacityLabel.setVisible(true);
		tableMaxCapacityText.setVisible(true);
		tableStatusLabel.setVisible(true);
		tableStatusCombo.setVisible(true);
		updateTableButton.setVisible(true);
		
	}
	
	private void updateEmployeeOptions(Employee employee) {

		employeeFirstNameText.setText(employee.getFirstname());

		employeeLastNameText.setText(employee.getLastname());

		employeePasswordText.setText(employee.getPassword());

		employeePositionCombo.setSelectedItem(employee.getPosition().substring(0, 1).toUpperCase() + employee.getPosition().substring(1));

	}
	
	private void updateTableOptions(Table table) {
		
		tableCapacityText.setText(table.getCurrentCapacity() + "");
		
		tableMaxCapacityText.setText(table.getMaximumCapacity() + "");

		if (table.isClean()) {
			
			tableStatusCombo.setSelectedIndex(0);
			
		} else {
			
			tableStatusCombo.setSelectedIndex(1);
			
		}

	}
	
	private void refreshUpdateRecordIdCombo() {
		
		updateRecordIdCombo.removeAllItems();
		
		//Initialize new combo values
		
		List<String> list = new ArrayList<String>();
		
		list.add("");

		//Parse the relevant HashMap from the model according to updateRecordTypeCombo

		switch (updateRecordTypeCombo.getSelectedIndex()) {
		
			case 1:

				for (Integer key : (appFrame.getModel().getEmployeesMap()).keySet()) {

					list.add(key.toString());

				}
				
				break;
				
			case 2:

				for (Integer key : (appFrame.getModel().getTablesMap()).keySet()) {

					list.add(key.toString());

				}
				
				break;
		
		}

		updateRecordIdCombo.setModel(new DefaultComboBoxModel(list.toArray()));
		
		hideEmployeeOptions();
		hideTableOptions();

	}
	
}
