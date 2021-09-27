package model;

import java.util.Comparator;

public class EmployeeNameAndIDComparator implements Comparator<Employee>{

	@Override
	public int compare(Employee o1, Employee o2) {
		int result = o1.getName().compareTo(o2.getName());
		if(result == 1) {
			return 1;
		}else if(result == -1) {
			return -1;
		}else {
			return o1.getId().compareTo(o2.getId());
		}
	}
	
}
