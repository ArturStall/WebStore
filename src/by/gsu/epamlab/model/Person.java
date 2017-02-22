package by.gsu.epamlab.model;

public class Person {
	private String login;
	private String name;
	private String lastName;
	private String email;
	
	public Person() {
		super();
	}

	public Person(String login, String name, String lastName, String email) {
		super();
		this.login = login;
		this.name = name;
		this.lastName = lastName;
		this.email = email;
	}

	public String getLogin() {
		return login;
	}

	public String getName() {
		return name;
	}

	public String getLastName() {
		return lastName;
	}

	public String getEmail() {
		return email;
	}	
}