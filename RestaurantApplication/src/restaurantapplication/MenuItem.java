package restaurantapplication;

public class MenuItem {

    /* INITIALIZE VARIABLES */
    
    private int itemNumber;
    private String itemName;
    private String itemDescription;
    private float itemCost;
    
    /* CONSTRUCTORS */
    
    public MenuItem(int itemNumber, String itemName, String itemDescription, float itemCost) {
        
        this.itemNumber = itemNumber;
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.itemCost = itemCost;
        
    }
    
    /* CLASS METHODS */
    
    /* ACCESSORS */
    
    public int getNumber() {
    	
    	return itemNumber;
    	
    }
    
    public String getName() {
    	
    	return itemName;
    	
    }
    
    public String getDescription() {
    	
    	return itemDescription;
    	
    }
    
    public float getCost() {
    	
    	return itemCost;
    	
    }
    
    /* MUTATORS */
    
    public void setNumber(int itemNumber) {
    	
    	this.itemNumber = itemNumber;
    	
    }
    
    public void setName(String itemName) {
    	
    	this.itemName = itemName;
    	
    }
    
    public void setDescription(String itemDescription) {
    	
    	this.itemDescription = itemDescription;
    	
    }
    
    public void setCost(float itemCost) {
    	
    	this.itemCost = itemCost;
    	
    }
    
    /* TOSTRING METHOD */
    
    @Override
    public String toString() {
    	
    	String s = "Item #" + itemNumber + " : " + itemName + " - " + itemDescription + " - $" + itemCost;
    	
    	return s;
    	
    }
    
}
