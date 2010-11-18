package com.smes.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.smes.dao.hibernate.UserDaoImpl;
import com.smes.view.frm.Credential;

public class LoginValidator implements Validator {

	private UserDaoImpl userDao;
	
	public UserDaoImpl getUserDao() {
		return userDao;
	}
	
	public void setUserDao(UserDaoImpl userDao) {
		this.userDao = userDao;
	}
	
	@Override
	public boolean supports(Class<?> clazz) {
		return Credential.class.equals(clazz);
	}

	@Override
	public void validate(Object object, Errors error) {
		Credential credential = (Credential) object;
		if (!userDao.isValidUser(credential.getUserName(),
					credential.getPassword(),
					credential.getCompanyName())){
			error.rejectValue("message", null, null, "Invalid credential");
		}
	}
}
