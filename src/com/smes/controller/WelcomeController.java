package com.smes.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class WelcomeController {
	public static final Logger logger = Logger.getLogger(WelcomeController.class);
	
	@RequestMapping ("/welcome.htm")
	public String redirect (){
		logger.info("Entering welcome Controller");
		return "welcome";
	}
}
