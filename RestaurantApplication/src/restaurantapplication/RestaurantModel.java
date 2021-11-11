package restaurantapplication;

import java.util.ArrayList;
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
	private final String MENU_FILENAME;
	private final String ORDERS_FILENAME;
	
	private HashMap<Integer, Table> tablesMap = new HashMap<>();
	private HashMap<Integer, Employee> employeesMap = new HashMap<>();
	private HashMap<Integer, MenuItem> menuMap = new HashMap<>();
	private HashMap<Integer, Order> ordersMap = new HashMap<>();
	
    /* CONSTRUCTORS */
	
    public RestaurantModel(String tablesFilename, String employeesFilename, String menuFilename, String ordersFilename) {
    	
    	TABLES_FILENAME = tablesFilename;
    	EMPLOYEES_FILENAME = employeesFilename;
    	MENU_FILENAME = menuFilename;
    	ORDERS_FILENAME = ordersFilename;
        
        loadTables();
        loadEmployees();
        loadMenu();
        loadOrders();

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
                String employeePassword = data[4];
                
                Employee employee = new Employee(employeeId, employeeFirstname, employeeLastname, employeePosition, employeePassword);
                
                //Add the object to the hashmap
                
                employeesMap.put(employeeId, employee);
                
            }  
            
            reader.close();
            
        } catch (Exception e) {  
            
            e.printStackTrace();  
            
        }  
        
    }
    
    private void loadMenu() {
    	
    	//Initialize variables
    	
    	String row = "";  
        String splitBy = ",";  
        
        //Clear hashmap
        
        menuMap.clear();

        try {
        	
        	//Check if file exists
        	
            File menuFile = new File(MENU_FILENAME);
            
            if (!menuFile.exists()) {
            	
            	//If the file does not exist, create it

            	menuFile.createNewFile();

            }
            
        	//Extract data from the CSV file
            
            BufferedReader reader = new BufferedReader(new FileReader(MENU_FILENAME)); 
            
            reader.readLine();
            
            while ((row = reader.readLine()) != null) {
                
                //Get the data from the current line
                
                String[] data = row.split(splitBy);
                
                //Parse the data into a MenuItem object

                int itemNumber = Integer.parseInt(data[0]);
                String itemName = data[1];
                String itemDescription = data[2];
                float itemCost = Float.parseFloat(data[0]);
                
                MenuItem item = new MenuItem(itemNumber, itemName, itemDescription, itemCost);
                
                //Add the object to the hashmap
                
                menuMap.put(itemNumber, item);
                
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
                
                ArrayList<MenuItem> orderItems = new ArrayList<>();
                
                for (String s : data[1].split("-")) {
                	
                	orderItems.add(menuMap.get(Integer.parseInt(s)));
                	
                }

                String orderStatus = data[2];
                
                Order order = new Order(orderNumber, orderItems, orderStatus);
                
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
    
    public HashMap<Integer, MenuItem> getMenu() {
    	
    	return menuMap;
    	
    }
    
    public HashMap<Integer, Order> getOrdersMap() {
    	
    	return ordersMap;
    	
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
        	
        	newData.append(e.getId() + "," + e.getFirstname() + "," + e.getLastname() + "," + e.getPosition() + "," + e.getPassword() + "\n");
        	
        }
        
        try (PrintWriter writer = new PrintWriter(EMPLOYEES_FILENAME)) {
        	
        	writer.write(newData.toString());
        	
        } catch (Exception e) {  
            
            e.printStackTrace();  
            
        }  

    }
    
    public void updateMenu() {
    	
    	//Check to make sure the hashmap isn't empty
    	
    	if (menuMap.isEmpty()) {
    		
    		System.out.println("Error: There are no menu items to update!");
    		
    		return;
    		
    	}
    	
    	//Initialize variables

        StringBuilder newData = new StringBuilder();
        
        //Add header row to new data
        
        newData.append("item number,item name,item description,item cost\n");
        
        //Add each MenuItem's data to the new data
        
        for(Entry<Integer, MenuItem> entry : menuMap.entrySet()) {
        	
        	MenuItem i = entry.getValue();
        	
        	newData.append(i.getNumber() + "," + i.getName() + "," + i.getDescription() + "," + i.getCost() + "\n");
        	
        }
        
        try (PrintWriter writer = new PrintWriter(MENU_FILENAME)) {
        	
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
        
        //Add each Order's data to the new data
        
        for(Entry<Integer, Order> entry : ordersMap.entrySet()) {
        	
        	Order o = entry.getValue();
        	
        	String s = "";
        	
        	for (MenuItem i : o.getItems()) {
        		
        		s += i.getNumber() + "-";
        		
        	}
        	
        	newData.append(o.getNumber() + "," + s + "," + o.getStatus() + "\n");
        	
        }
        
        try (PrintWriter writer = new PrintWriter(ORDERS_FILENAME)) {
        	
        	writer.write(newData.toString());
        	
        } catch (Exception e) {  
            
            e.printStackTrace();  
            
        }  

    }

}
