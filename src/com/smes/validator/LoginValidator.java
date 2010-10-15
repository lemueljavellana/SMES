package com.smes.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.smes.dao.hibernate.UserDaoImpl;
import com.smes.domain.hibernate.User;
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
		User user = userDao.getUser(credential.getUserName());
		
		if (user == null){
			reject(error);
			return;
		}
		if (!user.getPassword().equals(credential.getPassword())) 
			reject(error);
		if (!user.getCompany().getCompanyName().equals(credential.getCompanyName()))
			reject(error);
	} 
	
	private void reject (Errors errors){
		//ValidationUtils.rejectIfEmpty(errors, field, errorCode)
		errors.rejectValue("userName", "userName.required",
                null, "Invalid credentials");
	}
}
