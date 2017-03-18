package il.ac.shenkar.todolist.model;

import java.util.List;

public interface IToDoListDAO {
	
	public User 		addUser(User user) throws ToDoListException;
	public Item 		addItem(Item item) throws ToDoListException;
	public List<Item> 	getAllUserItems(int userId) throws ToDoListException;
	public User			getUser(int userId) throws ToDoListException;
	public List<User>	getAllUsers() throws ToDoListException;
	public User			deleteUser(int userId) throws ToDoListException;
	public Item			deleteItem(int itemId) throws ToDoListException;
	public List<Item>	deleteAllUserItems(int userId) throws ToDoListException;
	public Item 		updateItem(Item item) throws ToDoListException;
	public Item 		getItem(int itemId) throws ToDoListException;
}