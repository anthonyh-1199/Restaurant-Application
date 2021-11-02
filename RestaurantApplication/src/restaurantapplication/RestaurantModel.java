package restaurantapplication;

import java.util.HashMap;
import java.util.Map.Entry;
import java.io.*;

/*

  This class contains several data structures that represent key items in the restaurant, this includes:
  
  - a hashmap representing the tables in the restaurant where customers are seated and served at
  - a hashmap representing the individual employees that work in the restaurant
  - a hashmap representing the orders that are given and prepared by the kitchen

*/

public class RestaurantModel {

    /* INITIALIZE VARIABLES */
	
	private final String TABLES_FILENAME;
	private final String EMPLOYEES_FILENAME;
	private final String ORDERS_FILENAME;
	
	private HashMap<Integer, Table> tablesMap = new HashMap<Integer, Table>();
	private HashMap<Integer, Employee> employeesMap = new HashMap<Integer, Employee>();
	private HashMap<Integer, Order> ordersMap = new HashMap<Integer, Order>();
	
    /* CONSTRUCTORS */
	
    public RestaurantModel(String tablesFilename, String employeesFilename, String ordersFilename) {
    	
    	TABLES_FILENAME = tablesFilename;
    	EMPLOYEES_FILENAME = employeesFilename;
    	ORDERS_FILENAME = ordersFilename;
        
        loadTables();
        loadEmployees();
        loadOrders();
        
        //TESTING CODE - print out hashmaps
        
        for(Entry<Integer, Table> entry : tablesMap.entrySet()) {

            System.out.println(entry.getValue().toString());
            
        }
        
        for(Entry<Integer, Employee> entry : employeesMap.entrySet()) {

            System.out.println(entry.getValue().toString());
            
        }

        ordersMap.put(1, new Order(1, "burger and fries", "finished"));
        
        updateOrders();
        
        updateTables();
        
        for(Entry<Integer, Order> entry : ordersMap.entrySet()) {

            System.out.println(entry.getValue().toString());
            
        }
        
    }
    
    /* CLASS METHODS */
    
    private void loadTables() {
    	
    	//Initialize variables
    	
    	String row = "";  
        String splitBy = ",";  
        
        //Clear hashmap
        
        tablesMap.clear();
        
        //Extract data from the CSV file
        
        try {  
            
            BufferedReader reader = new BufferedReader(new FileReader(TABLES_FILENAME));  
            
            reader.readLine();
            
            while ((row = reader.readLine()) != null) {
                
                //Get the data from the current line
                
                String[] data = row.split(splitBy);
                
                //Parse the data into a Table object
                
                int tableNumber = Integer.parseInt(data[0]);
                int maximumCapacity = Integer.parseInt(data[1]);
                int currentCapacity = Integer.parseInt(data[2]);
                boolean isClean = Boolean.parseBoolean(data[3]);
                
                Table table = new Table(tableNumber, maximumCapacity, currentCapacity, isClean);
                
                //Add the object to the hashmap
                
                tablesMap.put(tableNumber, table);
                
            }  
            
            reader.close();
            
        } catch (Exception e) {  
            
            e.printStackTrace();  
            
        }  
        
    }
    
    private void loadEmployees() {
    	
    	//Initialize variables
    	
    	String row = "";  
        String splitBy = ",";  
        
        //Clear hashmap
        
        employeesMap.clear();
        
        //Extract data from the CSV file
        
        try {  
            
            BufferedReader reader = new BufferedReader(new FileReader(EMPLOYEES_FILENAME)); 
            
            reader.readLine();
            
            while ((row = reader.readLine()) != null) {
                
                //Get the data from the current line
                
                String[] data = row.split(splitBy);
                
                //Parse the data into an Employee object
                
                int employeeId = Integer.parseInt(data[0]);
                String employeeFirstname = data[1];
                String employeeLastname = data[2];
                String employeePosition = data[3];
                
                Employee employee = new Employee(employeeId, employeeFirstname, employeeLastname, employeePosition);
                
                //Add the object to the hashmap
                
                employeesMap.put(employeeId, employee);
                
            }  
            
            reader.close();
            
        } catch (Exception e) {  
            
            e.printStackTrace();  
            
        }  
        
    }
    
    private void loadOrders() {
    	
    	//Initialize variables
    	
    	String row = "";  
        String splitBy = ",";  
        
        //Clear hashmap
        
        employeesMap.clear();
        
        //Extract data from the CSV file
        
        try {
            
            BufferedReader reader = new BufferedReader(new FileReader(ORDERS_FILENAME)); 
            
            reader.readLine();
            
            while ((row = reader.readLine()) != null) {
                
                //Get the data from the current line
                
                String[] data = row.split(splitBy);
                
                //Parse the data into an Order object
                
                int orderNumber = Integer.parseInt(data[0]);
                String orderDescription = data[1];
                String orderStatus = data[2];
                
                Order order = new Order(orderNumber, orderDescription, orderStatus);
                
                //Add the object to the hashmap
                
                ordersMap.put(orderNumber, order);
                
            }
            
            reader.close();
            
        } catch (Exception e) {
            
            e.printStackTrace();  
            
        }  
        
    }
    
    /* ACCESSORS */
    
    public HashMap<Integer, Table> getTablesMap() {
    	
    	return tablesMap;
    	
    }
    
    public HashMap<Integer, Employee> getEmployeesMap() {
    	
    	return employeesMap;
    	
    }
    
    /* MUTATORS */
    
    public void updateTables() {
    	
    	//Check to make sure the hashmap isn't empty
    	
    	if (tablesMap.isEmpty()) {
    		
    		System.out.println("Error: There are no tables to update!");
    		
    		return;
    		
    	}
    	
    	//Initialize variables

        StringBuilder newData = new StringBuilder();
        
        //Add header row to new data
        
        newData.append("number,maximumcapacity,currentcapacity,clean\n");
        
        //Add each Table's data to the new data
        
        for(Entry<Integer, Table> entry : tablesMap.entrySet()) {
        	
        	Table t = entry.getValue();
        	
        	newData.append(t.getNumber() + "," + t.getMaximumCapacity() + "," + t.getCurrentCapacity() + "," + t.isClean() + "\n");
        	
        }
        
        try (PrintWriter writer = new PrintWriter(TABLES_FILENAME)) {
        	
        	writer.write(newData.toString());
        	
        } catch (Exception e) {  
            
            e.printStackTrace();  
            
        }  

    }
    
    public void updateEmployees() {
    	
    	//Check to make sure the hashmap isn't empty
    	
    	if (employeesMap.isEmpty()) {
    		
    		System.out.println("Error: There are no tables to update!");
    		
    		return;
    		
    	}
    	
    	//Initialize variables

        StringBuilder newData = new StringBuilder();
        
        //Add header row to new data
        
        newData.append("id,firstname,lastname,position\n");
        
        //Add each Employee's data to the new data
        
        for(Entry<Integer, Employee> entry : employeesMap.entrySet()) {
        	
        	Employee e = entry.getValue();
        	
        	newData.append(e.getId() + "," + e.getFirstname() + "," + e.getLastname() + "," + e.getPosition() + "\n");
        	
        }
        
        try (PrintWriter writer = new PrintWriter(EMPLOYEES_FILENAME)) {
        	
        	writer.write(newData.toString());
        	
        } catch (Exception e) {  
            
            e.printStackTrace();  
            
        }  

    }
    
    public void updateOrders() {
    	
    	//Check to make sure the hashmap isn't empty
    	
    	if (ordersMap.isEmpty()) {
    		
    		System.out.println("Error: There are no orders to update!");
    		
    		return;
    		
    	}
    	
    	//Initialize variables

        StringBuilder newData = new StringBuilder();
        
        //Add header row to new data
        
        newData.append("number,description,status\n");
        
        //Add each Table's data to the new data
        
        for(Entry<Integer, Order> entry : ordersMap.entrySet()) {
        	
        	Order o = entry.getValue();
        	
        	newData.append(o.getNumber() + "," + o.getDescription() + "," + o.getStatus() + "\n");
        	
        }
        
        try (PrintWriter writer = new PrintWriter(ORDERS_FILENAME)) {
        	
        	writer.write(newData.toString());
        	
        } catch (Exception e) {  
            
            e.printStackTrace();  
            
        }  

    }

}
