package com.smes.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TestAnnotation {
	
	@RequestMapping ("/helloWorld.htm")
	public ModelAndView helloWorld (){
		System.out.println("hello world");
		return null;
	}
}
