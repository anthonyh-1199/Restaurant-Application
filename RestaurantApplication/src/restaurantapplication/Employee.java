package restaurantapplication;

public class Employee {
	
    /* INITIALIZE VARIABLES */
    
    private int employeeId;
    private String employeeFirstname;
    private String employeeLastname;
    private String employeePosition;
    
    /* CONSTRUCTORS */
    
    public Employee(int employeeId, String employeeFirstname, String employeeLastname, String employeePosition) {
    	
    	this.employeeId = employeeId;
    	this.employeeFirstname = employeeFirstname;
    	this.employeeLastname = employeeLastname;
    	this.employeePosition = employeePosition;
    	
    }
    
    /* CLASS METHODS */
    
    /* ACCESSORS */
    
    public int getId() {
    	
    	return employeeId;
    	
    }
    
    public String getFirstname() {
    	
    	return employeeFirstname;
    	
    }
    
    public String getLastname() {
    	
    	return employeeLastname;
    	
    }
    
    public String getPosition() {
    	
    	return employeePosition;
    	
    }
    
    /* MUTATORS */
    
    public void setId(int employeeId) {
    	
    	this.employeeId = employeeId;
    	
    }
    
    public void setFirstname(String employeeFirstname) {
    	
    	this.employeeFirstname = employeeFirstname;
    	
    }
    
    public void setLastname(String employeeLastname) {
    	
    	this.employeeLastname = employeeLastname;
    	
    }
    
    public void setPosition(String employeePosition) {
    	
    	this.employeePosition = employeePosition;
    	
    }

}
