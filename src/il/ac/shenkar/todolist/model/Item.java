package il.ac.shenkar.todolist.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

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

	public Item(String description, Integer userId) {
		setDescription(description);
		setUserId(userId);
	}

	@Id
	@GeneratedValue
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	@Override
	public String toString() {
		return "\n> Item: \n\tId: " + this.id + "\n\tDescription: " + this.description + "\n\tUser-Id: " + this.userId;
	}
		
}
