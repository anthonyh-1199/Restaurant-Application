package restaurantapplication;

public class Cook extends Employee {
	
    /* CONSTRUCTORS */

	public Cook(int employeeId, String employeeFirstname, String employeeLastname, String employeePosition) {
		
		super(employeeId, employeeFirstname, employeeLastname, employeePosition);

	}
	
	/* CLASS METHODS */
	
	//Adds an order record to the Orders CSV file
	
	public void addOrder(Order order, String ordersFilename) {

		
		
	}
	
	//Removes an order record from the Orders CSV file
	
	public void removeOrder(Order order, String ordersFilename) {
		
		
		
	}
	
	//Changes the status of an Order object (either Cooking, Ready For Pickup, or Finished)
	
	public void setOrderStatus(Order order, String status) {
		
		order.setStatus(status);
		
	}

}
