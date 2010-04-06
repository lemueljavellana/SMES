package com.smes.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.view.RedirectView;

import com.smes.service.UserService;
import com.smes.view.frm.Credential;


public class LoginController extends SimpleFormController {
	private UserService userService;
		
	public UserService getUserService() {
		return userService;
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	@Override
	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {
		Credential credential = (Credential)command;
		request.getSession().setAttribute(Credential.class.getName(), credential);
		return new ModelAndView(new RedirectView(getSuccessView()));
	}
}
