package il.ac.shenkar.todolist.model;

public class ToDoListException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ToDoListException(String message) {
		super(message);
	}
	
	public ToDoListException(String message, Throwable throwable) {
		super(message, throwable);
	}

}
