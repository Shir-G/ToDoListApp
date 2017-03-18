package il.ac.shenkar.todolist.controller;

import java.io.*;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import il.ac.shenkar.todolist.model.*;

/**
 * Servlet implementation class Controller
 */
@WebServlet("/Controller/*")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HibernateToDoListDAO DAO = null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Controller() {
        super();
        DAO = HibernateToDoListDAO.getInstance();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		String path = request.getPathInfo();
		RequestDispatcher dispatcher = null;
		
		switch (path) {
		
		//user registration page
		case "/register":
			User user = getNewUser(request);
			try {
				user = DAO.addUser(user);
				Cookie userCookie = new Cookie("userName", user.getUserName());
				response.addCookie(userCookie);
				session.setAttribute("userId", user.getId());
				
				request.setAttribute("items", DAO.getAllUserItems(user.getId()));
				dispatcher = getServletContext().getRequestDispatcher("/todolist.jsp");
				dispatcher.forward(request, response);
			} 
			catch (ToDoListException e) {
				displayError(request, response, dispatcher, e);
			}
			
			break;
			
		//user login page	
		case "/login":			
			try {
				User userForLogin = getExistUser(request);
				if (userForLogin == null) {
					throw new ToDoListException("There is no user with this User Name and Password.");
				}
				Cookie userCookie = new Cookie("userName", userForLogin.getUserName());
				response.addCookie(userCookie);
				session.setAttribute("userId", userForLogin.getId());
				request.setAttribute("items", DAO.getAllUserItems(userForLogin.getId()));
				dispatcher = getServletContext().getRequestDispatcher("/todolist.jsp");
				dispatcher.forward(request, response);
			} 
			catch (ToDoListException e) {
				displayError(request, response, dispatcher, e);
			}
			
			break;
			
		//in case user want to logout	
		case "/logout":
			try{
				int userIdForLogout = getUserId(request);
				if (userIdForLogout == -1) {
					throw new ToDoListException("");
				}
				Cookie[] cookies = request.getCookies();
				Cookie cookie = getCookieByName(cookies, "userName");
				cookie.setValue(null);
				cookie.setMaxAge(0);
				response.addCookie(cookie);
				
				dispatcher = getServletContext().getRequestDispatcher("/homePage.jsp");
				dispatcher.forward(request, response);
			}
			catch (ToDoListException e) {
				displayError(request, response, dispatcher, e);
			}
			
			break;
			
		//in case user wants to add task to list
		case "/addItem":
			Item item = getNewItem(request);
			try {
				item = DAO.addItem(item);
				request.setAttribute("items", DAO.getAllUserItems(item.getUserId()));
				dispatcher = getServletContext().getRequestDispatcher("/todolist.jsp");
				dispatcher.forward(request, response);
			} 
			catch (ToDoListException e) {
				displayError(request, response, dispatcher, e);
			}
			
			break;
			
		//in case user wants to delete task from list
		case "/deleteItem":
			int itemId = getItemToDelete(request);
			Item deletedItem;
			try {
				deletedItem = DAO.deleteItem(itemId);
				request.setAttribute("items", DAO.getAllUserItems(deletedItem.getUserId()));
				dispatcher = getServletContext().getRequestDispatcher("/todolist.jsp");
				dispatcher.forward(request, response);
			} 
			catch (ToDoListException e) {
				displayError(request, response, dispatcher, e);
			}
			
			break;
			
		//in case user wants to delete all tasks on list	
		case "/deleteAll":
			try {
				int userId = getUserId(request);
				DAO.deleteAllUserItems(userId);
				request.setAttribute("items", DAO.getAllUserItems(userId));
				dispatcher = getServletContext().getRequestDispatcher("/todolist.jsp");
				dispatcher.forward(request, response);
			} 
			catch (ToDoListException e) {
				displayError(request, response, dispatcher, e);
			}
			
			break;		
			
		//default redirection to homepage 	
		default:		
			dispatcher = getServletContext().getRequestDispatcher("/homePage.jsp");
			dispatcher.forward(request, response);
			break;
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		String path = request.getPathInfo();
		RequestDispatcher dispatcher = null;
		
		switch (path) {
		case "/login":
			String userFromCookie = request.getParameter("userFromCookie");

			User userForLogin = null;
			try {
				userForLogin = checkIfUserExists(userFromCookie);
				
				if (userForLogin == null) throw new ToDoListException("There is no user associated with this cookies");
				session.setAttribute("userId", userForLogin.getId());
				request.setAttribute("items", DAO.getAllUserItems(userForLogin.getId()));
				dispatcher = getServletContext().getRequestDispatcher("/todolist.jsp");
				dispatcher.forward(request, response);
			} 
			catch (ToDoListException e) {
				displayError(request, response, dispatcher, e);
			}
			
			break;
			
		//user want to edit task
		case "/updateItem":
			try {
				Item item = createUpdatedItem(request);
				DAO.updateItem(item);
				request.setAttribute("items", DAO.getAllUserItems(item.getUserId()));
				dispatcher = getServletContext().getRequestDispatcher("/todolist.jsp");
				dispatcher.forward(request, response);
			} 
			catch (ToDoListException e) {
				displayError(request, response, dispatcher, e);
			}
			
			break;
		}
	}
	
	/**
	 * private function for internal use in this class
	 */
	private Cookie getCookieByName(Cookie[] cookies, String cookieName) {
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals(cookieName)) return cookie;
			}
		}
		return null;
	}
	
	/**
	 * error to be displayed when something goes wrong ,redirect it to error page
	 * @param request
	 * @param response
	 * @param dispatcher
	 * @param e
	 * @throws ServletException
	 * @throws IOException
	 */
	private void displayError(HttpServletRequest request, HttpServletResponse response, RequestDispatcher dispatcher, Throwable e) throws ServletException, IOException {
		Cookie[] cookies = request.getCookies();
		Cookie cookie = getCookieByName(cookies, "userName");
		if (cookie != null) {
			cookie.setValue(null);
			cookie.setMaxAge(0);
			response.addCookie(cookie);
		}
		
		request.setAttribute("errorMsg", e.getMessage());
		dispatcher = getServletContext().getRequestDispatcher("/error.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * set new User on DB
	 * @param request
	 * @return User
	 */
	private User getNewUser(HttpServletRequest request) {
		String userName = request.getParameter("userName");
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String password = request.getParameter("password");
		
		return new User(userName, firstName, lastName, password);
	}
	
	/**
	 * set new Item on DB
	 * @param request
	 * @return Item
	 */
	private Item getNewItem(HttpServletRequest request) {
		String itemName = request.getParameter("itemName");
		int userId = Integer.parseInt(request.getParameter("userId"));
		
		return new Item(itemName, userId);
	}
	
	/**
	 * delete item from DB
	 * @param request
	 * @return int
	 */
	private int getItemToDelete(HttpServletRequest request) {
		int itemId = Integer.parseInt(request.getParameter("itemId"));
		
		return itemId;
	}
	
	/**
	 * returns userId if found in DB
	 * @param request
	 * @return int
	 * @throws ToDoListException
	 */
	private int getUserId(HttpServletRequest request) throws ToDoListException {
		int userId = Integer.parseInt(request.getParameter("userId"));
		
		if (userId== -1) throw new ToDoListException("can`t find this user");
		return userId;
	}
	
	/**
	 * updates Item on DB
	 * @param request
	 * @return Item
	 */
	private Item createUpdatedItem(HttpServletRequest request) {
		int itemId = Integer.parseInt(request.getParameter("name"));
		String itemName = request.getParameter("value");
		
		Item item;
		try {
			item = DAO.getItem(itemId);
			item.setDescription(itemName);
			return item;
		} 
		catch (ToDoListException e) {
			e.printStackTrace();
		}
		
		return null; 
	}
	
	/**
	 * validates user credentials when trying to login
	 * @param request
	 * @return User 
	 * @throws ToDoListException
	 */
	private User getExistUser(HttpServletRequest request) throws ToDoListException {
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		
		try {
			User user = checkIfUserExists(userName);
			if (user != null) {
				if (user.getPassword().equals(password)) {
					return user;
				}
			}
		} 
		catch (ToDoListException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * Verifying if user exists on DB
	 * @param userName
	 * @return User
	 * @throws ToDoListException
	 */
	private User checkIfUserExists(String userName) throws ToDoListException {
		List<User> userList;
		try {
			userList = DAO.getAllUsers();
			for (User user : userList) {
				if (user.getUserName().equals(userName)) {
					return user;
				}
			}
		} 
		catch (ToDoListException e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
