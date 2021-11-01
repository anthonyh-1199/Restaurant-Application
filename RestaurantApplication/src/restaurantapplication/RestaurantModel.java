package restaurantapplication;

import java.util.HashMap;
import java.util.Map.Entry;
import java.io.*;

/*

This class contains a hashmap that contains the individual tables in the restaurant where customers will be seated and served at,
as well as a hashmap that contains the individual employees that work in the restaurant

*/

public class RestaurantModel {

    /* INITIALIZE VARIABLES */
	
	private HashMap<Integer, Table> tablesMap = new HashMap<Integer, Table>();
	private HashMap<Integer, Employee> employeesMap = new HashMap<Integer, Employee>();
	
    /* CONSTRUCTORS */
	
    public RestaurantModel(String tablesFilename, String employeesFilename) {
        
        loadTables(tablesFilename);
        loadEmployees(employeesFilename);
        
        //TESTING CODE - print out hashmaps
        
        for(Entry<Integer, Table> entry : tablesMap.entrySet()) {

            System.out.println(entry.getValue().toString());
            
        }
        
        for(Entry<Integer, Employee> entry : employeesMap.entrySet()) {

            System.out.println(entry.getValue().toString());
            
        }
        
    }
    
    /* CLASS METHODS */
    
    private void loadTables(String filename) {
    	
    	String row = "";  
        String splitBy = ",";  
        
        tablesMap.clear();
        
        try {  
            
            BufferedReader reader = new BufferedReader(new FileReader(filename));  
            
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
                
                //Add the object to Tables
                
                tablesMap.put(tableNumber, table);
                
            }  
            
            reader.close();
            
        } catch (IOException e) {  
            
            e.printStackTrace();  
            
        }  
        
    }
    
    private void loadEmployees(String filename) {
    	
    	String row = "";  
        String splitBy = ",";  
        
        employeesMap.clear();
        
        try {  
            
            BufferedReader reader = new BufferedReader(new FileReader(filename)); 
            
            reader.readLine();
            
            while ((row = reader.readLine()) != null) {
                
                //Get the data from the current line
                
                String[] data = row.split(splitBy);
                
                //Parse the data into a Table object
                
                int employeeId = Integer.parseInt(data[0]);
                String employeeFirstname = data[1];
                String employeeLastname = data[2];
                String employeePosition = data[3];
                
                Employee employee = new Employee(employeeId, employeeFirstname, employeeLastname, employeePosition);
                
                //Add the object to Tables
                
                employeesMap.put(employeeId, employee);
                
            }  
            
            reader.close();
            
        } catch (IOException e) {  
            
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
    

	
}
