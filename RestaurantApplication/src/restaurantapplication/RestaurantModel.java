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
	
	private final String TABLES_FILENAME;
	private final String EMPLOYEES_FILENAME;
	
	private HashMap<Integer, Table> tablesMap = new HashMap<Integer, Table>();
	private HashMap<Integer, Employee> employeesMap = new HashMap<Integer, Employee>();
	
    /* CONSTRUCTORS */
	
    public RestaurantModel(String tablesFilename, String employeesFilename) {
    	
    	TABLES_FILENAME = tablesFilename;
    	EMPLOYEES_FILENAME = employeesFilename;
        
        loadTables();
        loadEmployees();
        
        //TESTING CODE - print out hashmaps
        
        for(Entry<Integer, Table> entry : tablesMap.entrySet()) {

            System.out.println(entry.getValue().toString());
            
        }
        
        for(Entry<Integer, Employee> entry : employeesMap.entrySet()) {

            System.out.println(entry.getValue().toString());
            
        }
        
        Table tableone = tablesMap.get(1);
        
        tableone.setCurrentCapacity(0);
        
        updateTables();
        
        for(Entry<Integer, Table> entry : tablesMap.entrySet()) {

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
                
                //Add the object to Tables
                
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
    	
    	//Initialize variables

        StringBuilder newData = new StringBuilder();
        
        //Add header row to new data
        
        newData.append("id,firstname,lastname,position\n");
        
        //Add each Table's data to the new data
        
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

}
