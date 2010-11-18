package com.smes.web;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.smes.domain.hibernate.User;
import com.smes.service.UserService;
import com.smes.validator.LoginValidator;
import com.smes.view.frm.Credential;

@Controller
@RequestMapping ("/login")
@SessionAttributes ("credential")
public class LoginController {
	private final LoginValidator loginValidator;
	private final UserService userService;
	@Autowired
	public LoginController (LoginValidator loginValidator, UserService userService){
		this.loginValidator = loginValidator;
		this.userService = userService;
	}

	@RequestMapping (method = RequestMethod.GET)
	public String showLoginForm (ModelMap model) {
		Credential credential = new Credential();
		model.addAttribute(credential);
		return "loginPage";
	}
	
	@RequestMapping (method = RequestMethod.POST)
	public String onSubmit (@ModelAttribute ("credential") Credential credential,
			BindingResult result, HttpSession session){
		loginValidator.validate(credential, result);
		if (result.hasErrors())
			return "loginPage";
		User user = userService.getUser(credential.getUserName());
		credential.setUserId(user.getUserId());
		credential.setCompanyId(user.getCompany().getCompanyId());
		CredentialHandler.setCredential(session, credential);
		return "loginPageSuccecss";
	}
}
