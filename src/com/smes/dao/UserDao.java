package com.smes.dao;

import java.util.Collection;

import com.smes.domain.hibernate.User;

/**
 * User data access object.
 * @author Lemuel M. Javellana
 *
 */
public interface UserDao {
	
	/**
	 * Get the User
	 * @param userId the User's unique id.
	 * @return the User.
	 */
	User getUser (int userId);
	
	/**
	 * Get the User
	 * @param userName the user's name
	 * @return the User.
	 */
	User getUser (String userName);
	
	/**
	 * Save user
	 * @param user the user to be saved.
	 */
	void saveUser (User user);
	
	/**
	 * Delete user
	 * @param user the user that will be deleted.
	 */
	void delete (User user);
	
	/**
	 * Delete user
	 * @param userId the unique id of the user that will be deleted.
	 */
	void delete (int userId);
	
	/**
	 * Get all of the users. Ordy by user name
	 * @return The users.
	 */
	Collection<User> getUsers ();
}
