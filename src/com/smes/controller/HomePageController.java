package com.smes.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import com.smes.dao.hibernate.UserDaoImpl;

public class HomePageController extends AbstractController {
	private UserDaoImpl userDao;
	
	
	public UserDaoImpl getUserDao() {
		return userDao;
	}


	public void setUserDao(UserDaoImpl userDao) {
		this.userDao = userDao;
	}


	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
			ControllerUtil.verifyUser(request, userDao);
			return new ModelAndView("jsp/home/HomePage.jsp");
	}
}
