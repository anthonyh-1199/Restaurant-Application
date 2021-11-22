package restaurantapplication;

public class Employee {
	
    /* INITIALIZE VARIABLES */
    
    private int employeeId;
    private String employeeFirstname;
    private String employeeLastname;
    private String employeePosition;
    private String employeePassword;
    
    /* CONSTRUCTORS */
    
    public Employee(int employeeId, String employeeFirstname, String employeeLastname, String employeePosition, String employeePassword) {
    	
    	this.employeeId = employeeId;
    	this.employeeFirstname = employeeFirstname.substring(0, 1).toUpperCase() + employeeFirstname.substring(1);
    	this.employeeLastname = employeeLastname.substring(0, 1).toUpperCase() + employeeLastname.substring(1);
    	this.employeePosition = employeePosition;
    	this.employeePassword = employeePassword;
    	
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
    
    public String getPassword() {
    	
    	return employeePassword;
    	
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
    
    public void setPassword(String employeePassword) {
    	
    	this.employeePassword = employeePassword;
    	
    }
    
    /* TOSTRING METHOD */
    
    @Override
    public String toString() {
    	
    	String s = "Employee #" + employeeId + " : " + employeeFirstname + " " + employeeLastname + " - " + employeePosition;
    	
    	return s;
    	
    }

}
