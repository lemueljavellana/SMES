package com.smes.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping ("/test")
public class TestContoller {
	public static final Logger logger = Logger.getLogger(TestContoller.class);
	
	@RequestMapping (value = "/me/{name}", method=RequestMethod.GET)
	public void testMe (@PathVariable String name, Model model){
		logger.debug("name : "+ name);
	}
}
