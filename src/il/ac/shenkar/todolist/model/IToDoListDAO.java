package il.ac.shenkar.todolist.model;

import java.util.List;

/**
 * interface that determines which methods need to be implemented in order to 
 * communicate with our DB
 *
 */
public interface IToDoListDAO {
	
	public User 		addUser(User user) throws ToDoListException; //adds User to DB
	public Item 		addItem(Item item) throws ToDoListException; //adds task to DB
	public List<Item> 	getAllUserItems(int userId) throws ToDoListException; //returns a list of tasks that belongs to a certain user
	public User			getUser(int userId) throws ToDoListException; //returns User from DB
	public List<User>	getAllUsers() throws ToDoListException; //returns all users existing on DB
	public User			deleteUser(int userId) throws ToDoListException; //deletes user from DB
	public Item			deleteItem(int itemId) throws ToDoListException; //deletes item from DB
	public List<Item>	deleteAllUserItems(int userId) throws ToDoListException; //deletes all tasks that belongs to a certain user
	public Item 		updateItem(Item item) throws ToDoListException; //updates Item on DB
	public Item 		getItem(int itemId) throws ToDoListException; //returns item from DB
}