package il.ac.shenkar.todolist.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.FileAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.SimpleLayout;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.tool.hbm2ddl.SchemaExport;
/**
 *implements the interface IToDoListDAO 
 *
 */
public class HibernateToDoListDAO implements IToDoListDAO {

	private static SessionFactory factory;
	private static final Logger log = Logger.getLogger(HibernateToDoListDAO.class);
	private static HibernateToDoListDAO instance = null;
	
	private static AnnotationConfiguration config;
	
	/**
	 * constructor
	 */
	private HibernateToDoListDAO() {
		// configuring mySql schemas with annotations
		config = new AnnotationConfiguration();
		config.addAnnotatedClass(User.class);
		config.addAnnotatedClass(Item.class);
		config.configure("hibernate.cfg.xml");
		
//		new SchemaExport(config).create(true,true);
		
		factory = new AnnotationConfiguration().configure().buildSessionFactory();
		
		try {
			log.addAppender(new FileAppender(new SimpleLayout(), "logs.txt"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * implementation of singleton
	 * @return
	 */
	public static HibernateToDoListDAO getInstance(){
		if (instance == null){
			instance = new HibernateToDoListDAO();
		}
		return instance;
	}
	
	/**
	 * adds User to DB
	 * @return User
	 */
	@Override
	public User addUser(User user) throws ToDoListException {
		Session session = factory.openSession();
		Transaction transaction = null;
		
		try {
			if (checkIfUserExists(user.getUserName()) != null) {
				throw new ToDoListException("User with this User Name (" + user.getUserName() + ") already exists");
			}
			
			transaction = session.beginTransaction();
			session.save(user);
			transaction.commit();
			log.info("addUser: " + user);
		}
		catch(Exception e) {
			if (transaction != null) transaction.rollback();
			throw new ToDoListException("File: HibernateToDoListDAO, function: addUser", e);
		}
		finally {
			session.close();
		}
		
		return user;
	}
	
	/**
	 * adds task to DB
	 * @return Item
	 */
	@Override
	public Item addItem(Item item) throws ToDoListException {
		Session session = factory.openSession();
		Transaction transaction = null;
		
		try {
			transaction = session.beginTransaction();
			session.save(item);
			transaction.commit();
			log.info("addItem: " + item);
		}
		catch(Exception e) {
			if (transaction != null) transaction.rollback();
			throw new ToDoListException("File: HibernateToDoListDAO, function: addItem", e);
		}
		finally {
			session.close();
		}
		
		return item;
	}

	/**
	 * updates Item on DB
	 * @return Item
	 */
	@Override
	public Item updateItem(Item item) throws ToDoListException {
		Session session = factory.openSession();
		Transaction transaction = null;
		
		try {
			transaction = session.beginTransaction();
			session.update(item);
			transaction.commit();
			log.info("updateItem: " + item);
		}
		catch(Exception e) {
			if (transaction != null) transaction.rollback();
			throw new ToDoListException("File: HibernateToDoListDAO, function: updateItem", e);
		}
		finally {
			session.close();
		}
		
		return item;
	}
	
	/**
	 * returns a list of tasks that belongs to a certain user
	 * @return List
	 */
	@Override
	public List<Item> getAllUserItems(int userId) throws ToDoListException {
		Session session = factory.openSession();
		List<Item> itemList = new ArrayList<Item>();
		
		try {
			Criteria criteria = session.createCriteria(Item.class).add(Restrictions.eq("userId", userId));
			for (final Object o : criteria.list()) {
				itemList.add((Item) o);
	        }
			log.info("getAllUserItems: " + itemList);
		}
		catch(Exception e) {
			throw new ToDoListException("File: HibernateToDoListDAO, function: getAllUserItems", e);
		}
		finally {
			session.close();
		}
		
		return itemList;
	}
	
	/**
	 * returns User from DB
	 * @return User
	 */
	@Override
	public User getUser(int userId) throws ToDoListException {
		Session session = factory.openSession();
		User user = null;
		
		try {
			Criteria criteria = session.createCriteria(User.class).add(Restrictions.eq("id", userId));
			user = (User)criteria.uniqueResult();
			log.info("getUser: " + user);
		}
		catch(Exception e) {
			throw new ToDoListException("File: HibernateToDoListDAO, function: getUser", e);
		}
		finally {
			session.close();
		}
		
		return user;
	}
	
	/**
	 * returns item from DB
	 * @return Item 
	 */
	@Override
	public Item getItem(int itemId) throws ToDoListException {
		Session session = factory.openSession();
		Item item = null;
		
		try {
			Criteria criteria = session.createCriteria(Item.class).add(Restrictions.eq("id", itemId));
			item = (Item)criteria.uniqueResult();
			log.info("getItem: " + item);
		}
		catch(Exception e) {
			throw new ToDoListException("File: HibernateToDoListDAO, function: getItem", e);
		}
		finally {
			session.close();
		}
		
		return item;
	}
	
	/**
	 * deletes user from DB
	 * @return User
	 */
	@Override
	public User deleteUser(int userId) throws ToDoListException {
		Session session = factory.openSession();
		Transaction transaction = null;
		User user = null;
		
		try {
			transaction = session.beginTransaction();
			user = (User)session.createCriteria(User.class).add(Restrictions.eq("id", userId)).uniqueResult();
			session.delete(user);
			List<Item> itemsOfUser = getAllUserItems(user.getId());
			for(Item item : itemsOfUser) session.delete(item);
			
			transaction.commit();
			log.info("deleteUser: " + user);
		}
		catch(Exception e) {
			if (transaction != null) transaction.rollback();
			throw new ToDoListException("File: HibernateToDoListDAO, function: deleteUser", e);
		}
		finally {
			session.close();
		}
		
		return user;
	}
	
	/**
	 * deletes item from DB
	 * @return Item
	 */
	@Override
	public Item deleteItem(int itemId) throws ToDoListException {
		Session session = factory.openSession();
		Transaction transaction = null;
		Item item = null;
		
		try {
			transaction = session.beginTransaction();
			item = (Item)session.createCriteria(Item.class).add(Restrictions.eq("id", itemId)).uniqueResult();
			session.delete(item);
			transaction.commit();
			log.info("deleteItem: " + item);
		}
		catch(Exception e) {
			if (transaction != null) transaction.rollback();
			throw new ToDoListException("File: HibernateToDoListDAO, function: deleteItem", e);
		}
		finally {
			session.close();
		}
		
		return item;
	}
	
	/**
	 * deletes all tasks that belongs to a certain user
	 * @return List
	 */
	@Override
	public List<Item> deleteAllUserItems(int userId) throws ToDoListException {
		Session session = factory.openSession();
		List<Item> itemList = new ArrayList<Item>();
		Transaction transaction = null;
		
		try {
			transaction = session.beginTransaction();
			Criteria criteria = session.createCriteria(Item.class).add(Restrictions.eq("userId", userId));
			for (final Object o : criteria.list()) {
				itemList.add((Item) o);
				session.delete((Item) o);
		    }
			transaction.commit();
			log.info("deleteAllUserItems");
		}
		catch(Exception e) {
			if (transaction != null) transaction.rollback();
			throw new ToDoListException("File: HibernateToDoListDAO, function: deleteAllUserItems", e);
		}
		finally {
			session.close();
		}
		
		return itemList;
	}
	
	/**
	 * returns all users existing on DB
	 * @return List
	 */
	@Override
	public List<User> getAllUsers() throws ToDoListException {
		Session session = factory.openSession();
		List<User> userList = new ArrayList<User>();
		
		try {
			session.beginTransaction();
			Criteria criteria = session.createCriteria(User.class);
			for (final Object o : criteria.list()) {
				userList.add((User) o);
	        }
			log.info("getAllUsers: " + userList);
		}
		catch(Exception e) {
			throw new ToDoListException("File: HibernateToDoListDAO, function: getAllUsers", e);
		}
		finally {
			session.close();
		}
		
		return userList;
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
			userList = getAllUsers();
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
