package com.smes.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.smes.validator.LoginValidator;
import com.smes.view.frm.Credential;

@Controller
@RequestMapping ("/login.htm")
@SessionAttributes ("credential")
public class LoginController {
	private final LoginValidator loginValidator;
	
	@Autowired
	public LoginController (LoginValidator loginValidator){
		this.loginValidator = loginValidator;
	}

	@RequestMapping (method = RequestMethod.GET)
	public String showLoginForm (ModelMap model) {
		Credential credential = new Credential();
		model.addAttribute(credential);
		return "login/Login";
	}
	
	@RequestMapping (method = RequestMethod.POST)
	public String onSubmit (@ModelAttribute ("credential") Credential credential,
			BindingResult result){
		loginValidator.validate(credential, result);
		if (result.hasErrors())
			return "login/Login";
		System.out.println("on submit");
		return "ca/CAHome";
	}
}
