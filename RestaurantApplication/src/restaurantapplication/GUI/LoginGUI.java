package restaurantapplication.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import restaurantapplication.Employee;
import restaurantapplication.RestaurantModel;

public class LoginGUI extends JPanel implements ActionListener {
	
	/* INITIALIZE VARIABLES */
	
	private JButton loginButton;
	private JLabel errorLabel;
	private JLabel idLabel;
	private JLabel passwordLabel;
	private JPasswordField passwordField;
	private JTextField idField;
	private RestaurantModel restaurantModel;
	
	/* CONSTRUCTOR */

	public LoginGUI(RestaurantModel restaurantModel) {
		
		//Assign parameters
		
		this.restaurantModel = restaurantModel;
		
		//Format layout
		
		this.setLayout(null);
		
		//Format idLabel
		
		idLabel = new JLabel("Employee ID: ");
		
		idLabel.setBounds(50, 50, 80, 25);
		
		this.add(idLabel);
		
		//Format idField
		
		idField = new JTextField(20);
		
		idField.setBounds(135, 50, 165, 25);
		
		this.add(idField);
		
		//Format passwordLabel
		
		passwordLabel = new JLabel("Password: ");
		
		passwordLabel.setBounds(50, 80, 80, 25);
		
		this.add(passwordLabel);
		
		//Format passwordField
		
		passwordField = new JPasswordField(20);
		
		passwordField.setBounds(135, 80, 165, 25);
		
		this.add(passwordField);
		
		//Format loginButton
		
		loginButton = new JButton("Login");
		
		loginButton.setBounds(145, 120, 65, 25);	
		
		loginButton.addActionListener(this);
		
		this.add(loginButton);
		
		//Format errorLabel
		
		errorLabel = new JLabel("", SwingConstants.CENTER);

		errorLabel.setBounds(50, 150, 250, 25);	

		this.add(errorLabel);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		/* INITIALIZE VARIABLES */
		
		int idInput = -1;
		String passwordInput = String.valueOf(passwordField.getPassword());

		/* PARSE USER INPUT */
		
		try {
			
			idInput = Integer.parseInt(idField.getText());

			if (!(restaurantModel.getEmployeesMap()).containsKey(idInput)) {

				errorLabel.setText("Error: User not found");

				return;
				
			}
			
		} catch(Exception x) {
			
			errorLabel.setText("Error: Invalid employee ID");

			return;
			
		}
		
		/* VALIDATE USER PASSWORD */
		
		Employee employee = (restaurantModel.getEmployeesMap()).get(idInput);
		
		if (passwordInput.equals(employee.getPassword())) {
			
			//TO-DO : add logic for changing to next panel
			
		} else {
			
			errorLabel.setText("Error: Incorrect password");
			
		}

	}
	
}
