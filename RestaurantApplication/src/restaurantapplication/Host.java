package restaurantapplication;

public class Host extends Employee {
	
    /* CONSTRUCTORS */

	public Host(int employeeId, String employeeFirstname, String employeeLastname, String employeePosition) {
		
		super(employeeId, employeeFirstname, employeeLastname, employeePosition);

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
