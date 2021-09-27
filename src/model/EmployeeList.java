package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class EmployeeList {
	private List<Employee> employees;
	private String FILE_IMPORT_TXT_PATH = "data/EmployeesData.txt";
	private String FILE_EXPORT_TXT_PATH = "data/ExportedEmployeeData.txt";
	private String FILE_SAVE_PATH = "data2/Employees.apo2";
	
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
	
	
	public void sortByEmployeeNameAndEmployeeID() {
		Comparator<Employee> s1 = new  EmployeeNameAndIDComparator();
		Collections.sort(employees,s1);
	}
	
	
	public void exportEmployees() throws IOException {
		FileWriter fw = new FileWriter(FILE_EXPORT_TXT_PATH, true);
		for(int i=0; i<employees.size();i++) {
			Employee employee = employees.get(i);
			fw.write(employee.getName()+";"+employee.getId()+";"+employee.getBirthday()+";"+employee.getName()+"\n");
		}
		fw.close();
	}
	
	
	public void importEmployees() throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(FILE_IMPORT_TXT_PATH));
		String line = br.readLine();
		while(line != null) {
			String[] parts = line.split(";");
			addEmployee(parts[0], parts[1],parts[2],parts[4]);
			line = br.readLine();
		}
		br.close();
	}
	
	
	
	public void saveEmployees() throws FileNotFoundException, IOException {
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_SAVE_PATH));
		oos.writeObject(employees);
		oos.close();
	}
	
	@SuppressWarnings("unchecked")
	public void loadEmployees() throws FileNotFoundException, IOException, ClassNotFoundException {
		File file = new File(FILE_SAVE_PATH);
		if(file.exists()) {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
			employees = (List<Employee>) ois.readObject(); 
			ois.close();
		}
	}

}
