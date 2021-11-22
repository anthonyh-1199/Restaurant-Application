package restaurantapplication;

public class Busser extends Employee {
	
    /* CONSTRUCTORS */

	public Busser(int employeeId, String employeeFirstname, String employeeLastname, String employeePosition, String employeePassword) {
		
		super(employeeId, employeeFirstname, employeeLastname, employeePosition, employeePassword);

	}
	
	public Busser(Employee e) {
		
		super(e.getId(), e.getFirstname(), e.getLastname(), e.getPosition(), e.getPassword());
		
	}
	
	/* CLASS METHODS */
	
	//Sets the status of a Table as being clean or dirty
	
	public void setTableStatus(Table table, boolean status) {
		
		if (status) {
			
			table.setClean();
			
		} else {
			
			table.setDirty();
			
		}
		
	}

}
