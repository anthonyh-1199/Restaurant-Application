package restaurantapplication;

public class Host extends Employee {
	
    /* CONSTRUCTORS */

	public Host(int employeeId, String employeeFirstname, String employeeLastname, String employeePosition, String employeePassword) {
		
		super(employeeId, employeeFirstname, employeeLastname, employeePosition, employeePassword);

	}
	
	public Host(Employee e) {
		
		super(e.getId(), e.getFirstname(), e.getLastname(), e.getPosition(), e.getPassword());
		
	}
	
	/* CLASS METHODS */
	
	//Changes the current capacity of a Table object
	
	public void setTableCapacity(Table table, int currentCapacity) {
		
		table.setCurrentCapacity(currentCapacity);
		
		table.setDirty();
		
	}
	
	//Sets the variables of a Table to their defaults
	
	public void resetTable(Table table) {
		
		table.setCurrentCapacity(0);
		
		table.setClean();
		
	}

}
