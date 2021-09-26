package model;

import java.util.ArrayList;
import java.util.List;

public class EmployeeList {
	//public Employee employee;
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
	
	public boolean checkAccount(String id, String password) {
		boolean check = false;
		for(int i=0; (i<getEmployees().size())&&(check==false);i++) {
			if(
					(id.equals(employees.get(i).getId()))&&
					(password.equals(employees.get(i).getPassword()))) {
				check = true;
			}
		}
		return check;
	}
	
	public void changePassword(String id, String password, String newPassword) {
		for(int i=0; i<getEmployees().size() ;i++) {
			if(password.equals(employees.get(i).getPassword())&&
					id.equals(employees.get(i).getId())) {
				employees.get(i).setPassword(newPassword);
			}
		}
	}
	
	public boolean checkCreatorAccount(String id, String password) {
		boolean check = false;
		if( (id.equals("1004191932")) && (password.equals("jorge")) ) {
			check = true;
		}
		
		return check;
	}
	

}
