package restaurantapplication;

public class Cook extends Employee {
	
    /* CONSTRUCTORS */

	public Cook(int employeeId, String employeeFirstname, String employeeLastname, String employeePosition, String employeePassword) {
		
		super(employeeId, employeeFirstname, employeeLastname, employeePosition, employeePassword);

	}
	
	/* CLASS METHODS */
	
	//Adds an order record to the RestaurantModel's ordersMap
	
	public void addOrder(Order order, RestaurantModel model) {

		
		
	}
	
	//Removes an order record from the RestaurantModel's ordersMap
	
	public void removeOrder(Order order, RestaurantModel model) {
		
		
		
	}
	
	//Changes the status of an Order object (either Cooking, Ready For Pickup, or Finished)
	
	public void setOrderStatus(Order order, String status) {
		
		order.setStatus(status);
		
	}

}
