package il.ac.shenkar.todolist.model;

/**
 * our project exception 
 *
 */
public class ToDoListException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * constructor
	 * @param message
	 */
	public ToDoListException(String message) {
		super(message);
	}
	
	/**
	 * constructor
	 * @param message
	 * @param throwable
	 */
	public ToDoListException(String message, Throwable throwable) {
		super(message, throwable);
	}

}
