package il.ac.shenkar.todolist.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * class User describes a row on the users table
 *
 */
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

	/**
	 * constructor
	 * @param userName
	 * @param firstName
	 * @param lastName
	 * @param password
	 */
	public User(String userName, String firstName, String lastName, String password) {
		setUserName(userName);
		setFirstName(firstName);
		setLastName(lastName);
		setPassword(password);
	}
	
	/**
	 * returns Users id
	 * @return Integer
	 */
	@Id
	@GeneratedValue
	public Integer getId() {
		return id;
	}

	/**
	 * sets Users id
	 * @param id
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * returns Users username 
	 * @return String
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * sets Users username
	 * @param userName
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	/**
	 * returns Users firstname
	 * @return String
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * sets Users firstname
	 * @param firstName
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * returns Users lastname
	 * @returnString
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * sets Users lastname
	 * @param lastName
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * returns Users password
	 * @return String
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * sets Users password
	 * @param password
	 */
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
