package restaurantapplication;

public class Order {

    /* INITIALIZE VARIABLES */
    
    private int orderNumber;
    private String orderDescription;
    private String orderStatus;
    
    /* CONSTRUCTORS */
    
    public Order(int orderNumber, String orderDescription, String orderStatus) {
    	
    	this.orderNumber = orderNumber;
    	this.orderDescription = orderDescription;
    	this.orderStatus = orderStatus;
    	
    }
    
    /* CLASS METHODS */
    
    /* ACCESSORS */
    
    public int getNumber() {
    	
    	return orderNumber;
    	
    }
    
    public String getDescription() {
    	
    	return orderDescription;
    	
    }
    
    public String getStatus() {
    	
    	return orderStatus;
    	
    }
    
    /* MUTATORS */
    
    public void setNumber(int orderNumber) {
    	
    	this.orderNumber = orderNumber;
    	
    }
    
    public void setDescription(String orderDescription) {
    	
    	this.orderDescription = orderDescription;
    	
    }
    
    public void setStatus(String orderStatus) {
    	
    	this.orderStatus = orderStatus;
    	
    }
    
    /* TOSTRING METHOD */
    
    @Override
    public String toString() {
    	
    	String s = "Order #" + orderNumber + " : " + orderDescription + " - " + orderStatus;
    	
    	return s;
    	
    }
	
}
