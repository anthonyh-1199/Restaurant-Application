package restaurantapplication;

/*

This class represents the individual tables in the restaurant where customers will be seated and served at.

*/

public class Table {
    
    /* INITIALIZE VARIABLES */
    
    private int tableNumber;
    private int maximumCapacity;
    private int currentCapacity;
    private boolean isClean;
    
    /* CONSTRUCTORS */
    
    public Table(int tableNumber, int maximumCapacity, boolean isClean) {
        
        this.tableNumber = tableNumber;
        this.maximumCapacity = maximumCapacity;
        this.currentCapacity = 0;
        this.isClean = true;
        
    }
    
    public Table(int tableNumber, int maximumCapacity, int currentCapacity, boolean isClean) {
        
        this.tableNumber = tableNumber;
        this.maximumCapacity = maximumCapacity;
        this.currentCapacity = currentCapacity;
        this.isClean = isClean;
        
    }
    
    /* CLASS METHODS */
    
    /* ACCESSORS */
    
    public int getNumber() {
        
        return tableNumber;
        
    }
    
    public int getMaximumCapacity() {
        
        return maximumCapacity;
        
    }
    
    public int getCurrentCapacity() {
        
        return currentCapacity;
        
    }

    public boolean isClean() {
        
        return isClean;
        
    }
    
    /* MUTATORS */
    
    public void setNumber(int tableNumber) {
        
        this.tableNumber = tableNumber;
        
    }
    
    public void setMaximumCapacity(int maximumCapacity) {
        
        this.maximumCapacity = maximumCapacity;
        
    }
    
    public void setCurrentCapacity(int currentCapacity) {
        
        this.currentCapacity = currentCapacity;
        
    }

    public void setClean() {
        
        this.isClean = true;
        
    }
    
    public void setDirty() {
        
        this.isClean = false;
        
    }
    
    /* TOSTRING METHOD */
    
    @Override
    public String toString() {
    	
    	String s = "Table #" + tableNumber + " : " + currentCapacity + "/" + maximumCapacity;
    	
    	return s;
    	
    }
    
}
