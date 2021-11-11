package restaurantapplication;

import java.util.ArrayList;

public class Order {

    /* INITIALIZE VARIABLES */
    
    private int orderNumber;
    private ArrayList<MenuItem> orderItems;
    private String orderStatus;
    
    /* CONSTRUCTORS */
    
    public Order(int orderNumber, ArrayList<MenuItem> orderItems, String orderStatus) {
    	
    	this.orderNumber = orderNumber;
    	this.orderItems = orderItems;
    	this.orderStatus = orderStatus;
    	
    }
    
    /* CLASS METHODS */
    
    /* ACCESSORS */
    
    public int getNumber() {
    	
    	return orderNumber;
    	
    }
    
    public ArrayList<MenuItem> getItems() {
    	
    	return orderItems;
    	
    }
    
    public String getStatus() {
    	
    	return orderStatus;
    	
    }
    
    /* MUTATORS */
    
    public void setNumber(int orderNumber) {
    	
    	this.orderNumber = orderNumber;
    	
    }
    
    public void setItems(ArrayList<MenuItem> orderItems) {
    	
    	this.orderItems = orderItems;
    	
    }
    
    public void setStatus(String orderStatus) {
    	
    	this.orderStatus = orderStatus;
    	
    }
    
    /* TOSTRING METHOD */
    
    @Override
    public String toString() {
    	
    	String items = "";
    	
    	for (MenuItem i : orderItems) {
    		
    		items += i.getName() + ", ";
    		
    	}
    	
    	String s = "Order #" + orderNumber + " : " + items + "- " + orderStatus;
    	
    	return s;
    	
    }
	
}
