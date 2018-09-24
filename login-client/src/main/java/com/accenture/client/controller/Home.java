package com.accenture.client.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Home {

	@RequestMapping("/homeadmin")
	public String redirectAdmin(){
		return "ViewAll";
	}

	@RequestMapping("/homeuser")
	public String redirectUser(){
		return "HomeUser";
	}

	@RequestMapping("/invalidacc")
	public String redirectWrong(){
		return "InvalidUser";
	}

}
