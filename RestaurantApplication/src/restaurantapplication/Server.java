package restaurantapplication;

public class Server extends Employee {
	
    /* CONSTRUCTORS */

	public Server(int employeeId, String employeeFirstname, String employeeLastname, String employeePosition, String employeePassword) {
		
		super(employeeId, employeeFirstname, employeeLastname, employeePosition, employeePassword);

	}
	
	/* CLASS METHODS */

	//Adds an order record to the Orders CSV file
	
	public void addOrder(Order order, String ordersFilename) {

		
		
	}
	
	//Removes an order record from the Orders CSV file
	
	public void removeOrder(Order order, String ordersFilename) {
		
		
		
	}
	
}
