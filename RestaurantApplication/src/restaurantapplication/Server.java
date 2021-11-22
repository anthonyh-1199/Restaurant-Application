package restaurantapplication;

public class Server extends Employee {

    /* CONSTRUCTORS */

	public Server(int employeeId, String employeeFirstname, String employeeLastname, String employeePosition, String employeePassword) {

		super(employeeId, employeeFirstname, employeeLastname, employeePosition, employeePassword);

	}
	
	public Server(Employee e) {
		
		super(e.getId(), e.getFirstname(), e.getLastname(), e.getPosition(), e.getPassword());
		
	}

	/* CLASS METHODS */

	//Adds an order entry to the RestaurantModel's ordersMap

	public void addOrder(Order order, RestaurantModel model) {

		model.getOrdersMap().put(order.getNumber(), order);
		
		model.updateOrders();

	}

	//Removes an order entry from the RestaurantModel's ordersMap

	public void removeOrder(Order order, RestaurantModel model) {

		model.getOrdersMap().remove(order.getNumber());

		model.updateOrders();

	}

}
