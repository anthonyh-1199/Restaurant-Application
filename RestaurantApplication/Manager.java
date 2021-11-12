package restaurantapplication;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Manager extends Employee {

	/* CONSTRUCTORS */

	public Manager(int employeeId, String employeeFirstname, String employeeLastname, String employeePosition,
			String employeePassword) {

		super(employeeId, employeeFirstname, employeeLastname, employeePosition, employeePassword);

	}

	/* CLASS METHODS */

	// Adds an employee record to the Employees CSV file

	public void addEmployee(Employee employee, String employeesFilename) {
		try {
			// employee file opened to add new order 
			BufferedWriter employeeWriter = new BufferedWriter(new FileWriter(employeesFilename, true));
			employeeWriter.write(employee.getId() + "," + employee.getFirstname() + "," + employee.getLastname() + ","
					+ employee.getPosition() + "," + employee.getPassword() + "\n");
			// writer object is closed after add new employee
			employeeWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Removes an employee record from the Employees CSV file

	public void removeEmployee(Employee employee, String employeesFilename) {
		try {
			// array list declared to store the values from employees file
			ArrayList<Employee> employeeList = new ArrayList<Employee>();
			// file reader object is created to read the employees file
			FileReader employeeReader = new FileReader(new File(employeesFilename));
			BufferedReader buffReader = new BufferedReader(employeeReader);
			String employeeData;
			// read the data from file line by line
			String header = employeeData = buffReader.readLine();
			while ((employeeData = buffReader.readLine()) != null) {
				// split the file data using ',' then add in employees list
				String[] orderArray = employeeData.split(",");
				employeeList.add(new Employee(Integer.parseInt(orderArray[0]), orderArray[1], orderArray[2],
						orderArray[3], orderArray[4]));
			}
			// close the reader
			buffReader.close();
			// employees file opened to update orders 
			BufferedWriter employeeWriter = new BufferedWriter(new FileWriter(employeesFilename));
			employeeWriter.write(header + "\n");
			// iterate over the employees list
			for (Employee employees : employeeList) {
				// when the employee is not correspond to the employee to delete then write on file
				if (employees.getId() != employee.getId()) {
					employeeWriter
							.write(employees.getId() + "," + employees.getFirstname() + "," + employees.getLastname()
									+ "," + employees.getPosition() + "," + employees.getPassword() + "\n");
				}
			}
			// writer object is closed after remove employee
			employeeWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Adds a table record to the Tables CSV file

	public void addTable(Table table, String tablesFilename) {
		try {
			// tables file opened to add new order 
			BufferedWriter tableWriter = new BufferedWriter(new FileWriter(tablesFilename, true));
			tableWriter.write(table.getNumber() + "," + table.getMaximumCapacity() + "," + table.getCurrentCapacity()
					+ "," + table.isClean() + "\n");
			// writer object is closed after add new table
			tableWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Removes a table record from the Tables CSV file

	public void removeTable(Table table, String tablesFilename) {
		try {
			// array list declared to store the values from tables file
			ArrayList<Table> tableList = new ArrayList<Table>();
			// file reader object is created to read the tables file
			FileReader tableReader = new FileReader(new File(tablesFilename));
			BufferedReader buffReader = new BufferedReader(tableReader);
			String tableData;
			// read the data from file line by line
			String header = tableData = buffReader.readLine();
			while ((tableData = buffReader.readLine()) != null) {
				// split the file data using ',' then add in tables list
				String[] tableArray = tableData.split(",");
				tableList.add(new Table(Integer.parseInt(tableArray[0]), Integer.parseInt(tableArray[1]), Integer.parseInt(tableArray[2]),
						Boolean.parseBoolean(tableArray[3])));
			}
			// close the reader
			buffReader.close();
			// tables file opened to update orders 
			BufferedWriter tableWriter = new BufferedWriter(new FileWriter(tablesFilename));
			tableWriter.write(header + "\n");
			// iterate over the tables list
			for (Table tables : tableList) {
				// when the table is not correspond to the table to delete then write on file
				if (table.getNumber() != tables.getNumber()) {
					tableWriter.write(tables.getNumber() + "," + tables.getMaximumCapacity() + "," + tables.getCurrentCapacity() + "," + tables.isClean()+ "\n");
				}
			}
			// writer object is closed after remove table
			tableWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
