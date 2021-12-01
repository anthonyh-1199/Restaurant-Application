package restaurantapplication;

import java.util.ArrayList;

public class Order {

    /* INITIALIZE VARIABLES */
    
    private int orderNumber;
    private int tableId;
    private String orderDescription;
    private String orderStatus;
    
    /* CONSTRUCTORS */
    
    public Order(int orderNumber, String orderDescription, int tableId, String orderStatus) {
    	
    	this.orderNumber = orderNumber;
    	this.orderDescription = orderDescription;
    	this.tableId = tableId;
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
    
    public int getTableId() {
    	
    	return tableId;
    	
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
    
    public void setTableId(int tableId) {
    	
    	this.tableId = tableId;
    	
    }
    
    public void setStatus(String orderStatus) {
    	
    	this.orderStatus = orderStatus;
    	
    }
    
    /* TOSTRING METHOD */
    
    @Override
    public String toString() {

    	String s = "Order #" + orderNumber + " : " + orderDescription + " - " + "for table #" + tableId + " Status: " + orderStatus;
    	
    	return s;
    	
    }
	
}
