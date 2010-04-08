package com.smes.service;

import java.util.Collection;

import com.smes.dao.UserDao;
import com.smes.domain.hibernate.User;

public class UserService {
	private UserDao userDao;
	
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	public User getUser (int userId){
		return userDao.getUser(userId);
	}
	
	public User getUser (String userName){
		return userDao.getUser(userName);
	}
	
	public void saveUser (User user){
		userDao.saveUser(user);
	}
	
	public void deleteUser (User user){
		userDao.delete(user);
	}
	
	public void deleteUser (int userId){
		userDao.delete(userId);
	}
	
	public Collection<User> getUsers (){
		return userDao.getUsers();
	}
}
