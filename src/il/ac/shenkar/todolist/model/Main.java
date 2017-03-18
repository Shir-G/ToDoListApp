package il.ac.shenkar.todolist.model;

public class Main {

	public static void main(String[] args) {
		HibernateToDoListDAO DAO = HibernateToDoListDAO.getInstance();
		
		User u1 = new User("DanitL", "Danit", "Levin", "1234");
		User u2 = new User("JD", "Jane", "Doe", "nonono");
		User u3 = new User("GameBoy", "Gal", "Dan", "noPassword");
		
		Item i1 = new Item("buy water", 1);
		Item i2 = new Item("clean house", 2);
		Item i3 = new Item("sleep all day long", 3);
		Item i4 = new Item("eat food as much as I can", 3);
		Item i5 = new Item("study for math test", 1);
		
		try {
			DAO.addUser(u1);
			DAO.addUser(u2);
			DAO.addUser(u3);
			
			DAO.addItem(i1);
			DAO.addItem(i2);
			DAO.addItem(i3);
			DAO.addItem(i4);
			DAO.addItem(i5);
			
			i5.setDescription("newnewnewnew");
			DAO.updateItem(i5);
			
			DAO.deleteUser(DAO.getAllUsers().get(0).getId());
			DAO.deleteAllUserItems(2);
		} 
		catch (ToDoListException e) {
			e.printStackTrace();
		}

	}
}
