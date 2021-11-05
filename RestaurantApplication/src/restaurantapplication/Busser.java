package restaurantapplication;

public class Busser extends Employee {
	
    /* CONSTRUCTORS */

	public Busser(int employeeId, String employeeFirstname, String employeeLastname, String employeePosition) {
		
		super(employeeId, employeeFirstname, employeeLastname, employeePosition);

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
