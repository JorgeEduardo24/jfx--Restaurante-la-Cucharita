package model;

public class Employee {
	protected String name;
	protected String identificationCard;
	protected String birthday;
	protected String password;
	private static int userCounter;
	private final int id;
	
	private Employee() {
		this.id = ++Employee.userCounter;
	}
	
	public Employee(String name, String identificationCard, String birthday, String password) {
		this();
		this.name = name;
		this.identificationCard = identificationCard;
		this.birthday = birthday;
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIdentificationCard() {
		return identificationCard;
	}

	public void setIdentificationCard(String identificationCard) {
		this.identificationCard = identificationCard;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public int getId() {
		return id;
	}
	
	
}
