package il.ac.shenkar.todolist.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * class Item describing a task 
 * each Item is a row from the table
 *
 */
@Entity
@Table(name="items")
public class Item {

	private Integer id;
	private String description;
	private Integer userId;
	
	public Item() {
		
	}
	
//	public Item(String description) { //change that
//		setDescription(description);
//		setUserId(1); //change that
//	}

	/**
	 * constructor
	 * @param description
	 * @param userId
	 */
	public Item(String description, Integer userId) {
		setDescription(description);
		setUserId(userId);
	}

	/**
	 * returns Items id
	 * @return
	 */
	@Id
	@GeneratedValue
	public Integer getId() {
		return id;
	}

	/**
	 * sets Items id
	 * @param id
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * returns description
	 * @return String
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * sets description
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * returns usersID the Item belongs to
	 * @return Integer
	 */
	public Integer getUserId() {
		return userId;
	}

	/**
	 * sets userID the Item belongs to
	 * @param userId
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	@Override
	public String toString() {
		return "\n> Item: \n\tId: " + this.id + "\n\tDescription: " + this.description + "\n\tUser-Id: " + this.userId;
	}
		
}
