package restaurantapplication;

public class Manager extends Employee {
	
    /* CONSTRUCTORS */

	public Manager(int employeeId, String employeeFirstname, String employeeLastname, String employeePosition, String employeePassword) {
		
		super(employeeId, employeeFirstname, employeeLastname, employeePosition, employeePassword);

	}
	
	public Manager(Employee e) {
		
		super(e.getId(), e.getFirstname(), e.getLastname(), e.getPosition(), e.getPassword());
		
	}
	
	/* CLASS METHODS */
	
	//Adds an employee record to the RestaurantModel's employeesMap
	
	public void addEmployee(Employee employee, RestaurantModel model) {

		model.getEmployeesMap().put(employee.getId(), employee);
		
		model.updateEmployees();
		
	}
	
	//Removes an employee record from the RestaurantModel's employeesMap
	
	public void removeEmployee(Employee employee, RestaurantModel model) {
		
		model.getEmployeesMap().remove(employee.getId());
		
		model.updateEmployees();
		
	}
	
	//Adds a table record to the RestaurantModel's tablesMap
	
	public void addTable(Table table, RestaurantModel model) {

		model.getTablesMap().put(table.getNumber(), table);
		
		model.updateTables();
		
	}
	
	//Removes a table record from the RestaurantModel's tablesMap
	
	public void removeTable(Table table, RestaurantModel model) {
		
		model.getEmployeesMap().remove(table.getNumber());
		
		model.updateTables();
		
	}

}
