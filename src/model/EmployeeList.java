package model;

import java.util.ArrayList;
import java.util.List;

public class EmployeeList {
	public Employee employee;
	private List<Employee> employees;
	
	public EmployeeList() {
		employees = new ArrayList<Employee>();
	}
	
	public void addEmployee(String name, String id, String birthday, String password) {
		employees.add(new Employee(name, id, birthday,password));
	}
	
	public List<Employee> getEmployees(){
		return employees;
	}
}
