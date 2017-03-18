package il.ac.shenkar.todolist.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="users")
public class User {

	private Integer id;
	private String userName;
	private String firstName;
	private String lastName;
	private String password;
	
	public User() {
		// TODO Auto-generated constructor stub
	}

	public User(String userName, String firstName, String lastName, String password) {
		setUserName(userName);
		setFirstName(firstName);
		setLastName(lastName);
		setPassword(password);
	}
	
	@Id
	@GeneratedValue
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public String toString() {
		return "\n> User: \n\tId: " + this.id + 
				"\n\tUserName: " + this.userName + 
				"\n\tFirstName: " + this.firstName +
				"\n\tLastName: " + this.lastName;
	}

}
