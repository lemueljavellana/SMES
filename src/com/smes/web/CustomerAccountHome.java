package com.smes.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CustomerAccountHome {

	@RequestMapping ("cah")
	public String redirect (){
		return "cah";
	}
}
