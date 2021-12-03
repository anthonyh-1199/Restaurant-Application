package restaurantapplication.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import restaurantapplication.Employee;

public class LoginGUI extends JPanel implements ActionListener {
	
	/* INITIALIZE VARIABLES */
	
	private ApplicationFrame appFrame;
	private JButton loginButton;
	private JLabel idLabel;
	private JLabel passwordLabel;
	private JPasswordField passwordField;
	private JTextField idField;
	
	/* CONSTRUCTOR */

	public LoginGUI(ApplicationFrame appFrame) {
		
		//Assign parameters
		
		this.appFrame = appFrame;
		
		appFrame.currentUser = null;

		//Format layout
		
		this.setLayout(null);
		
		//Format panel border
		
		this.setBorder (new TitledBorder (new EtchedBorder(), ("User Login")));
		
		//Format idLabel
		
		idLabel = new JLabel("Employee ID: ");
		
		idLabel.setBounds(74, 50, 80, 25);
		
		this.add(idLabel);
		
		//Format idField
		
		idField = new JTextField(20);
		
		idField.setBounds(159, 50, 165, 25);
		
		this.add(idField);
		
		//Format passwordLabel
		
		passwordLabel = new JLabel("Password: ");
		
		passwordLabel.setBounds(74, 80, 80, 25);
		
		this.add(passwordLabel);
		
		//Format passwordField
		
		passwordField = new JPasswordField(20);
		
		passwordField.setBounds(159, 80, 165, 25);
		
		this.add(passwordField);
		
		//Format loginButton
		
		loginButton = new JButton("Login");
		
		loginButton.setBounds(189, 120, 65, 25);	
		
		loginButton.addActionListener(this);
		
		this.add(loginButton);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		/* INITIALIZE VARIABLES */
		
		int idInput = -1;
		String passwordInput = String.valueOf(passwordField.getPassword());

		/* PARSE USER INPUT */
		
		try {
			
			idInput = Integer.parseInt(idField.getText());

			if (!(appFrame.getModel().getEmployeesMap()).containsKey(idInput)) {
				
				JOptionPane.showMessageDialog(this,
						"Error: User not found.",
					    "Login error",
					    JOptionPane.ERROR_MESSAGE);

				return;
				
			}
			
		} catch(Exception x) {
			
			JOptionPane.showMessageDialog(this,
					"Error: Invalid employee ID.",
				    "Login error",
				    JOptionPane.ERROR_MESSAGE);

			return;
			
		}
		
		/* VALIDATE USER PASSWORD */
		
		Employee employee = (appFrame.getModel().getEmployeesMap()).get(idInput);
		
		if (passwordInput.equals(employee.getPassword())) {
			
			appFrame.currentUser = employee;

			appFrame.changePanel(employee.getPosition());
			
		} else {
			
			JOptionPane.showMessageDialog(this,
			    "Error: Incorrect password.",
			    "Login error",
			    JOptionPane.ERROR_MESSAGE);

		}

	}
	
}
