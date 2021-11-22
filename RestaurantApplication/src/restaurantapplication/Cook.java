package restaurantapplication;

public class Cook extends Employee {
	
    /* CONSTRUCTORS */

	public Cook(int employeeId, String employeeFirstname, String employeeLastname, String employeePosition, String employeePassword) {
		
		super(employeeId, employeeFirstname, employeeLastname, employeePosition, employeePassword);

	}
	
	public Cook(Employee e) {
		
		super(e.getId(), e.getFirstname(), e.getLastname(), e.getPosition(), e.getPassword());
		
	}
	
	/* CLASS METHODS */
	
	//Adds an order record to the RestaurantModel's ordersMap
	
	public void addOrder(Order order, RestaurantModel model) {

		model.getOrdersMap().put(order.getNumber(), order);
		
		model.updateOrders();
		
	}
	
	//Removes an order record from the RestaurantModel's ordersMap
	
	public void removeOrder(Order order, RestaurantModel model) {
		
		model.getOrdersMap().remove(order.getNumber());

		model.updateOrders();
		
	}
	
	//Changes the status of an Order object (either Cooking, Ready For Pickup, or Finished)
	
	public void setOrderStatus(Order order, String status) {
		
		order.setStatus(status);
		
	}

}
