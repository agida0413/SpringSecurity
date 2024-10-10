package com.sist.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class LoginController {

	@GetMapping("/login")
	public String loginform() {
		return "/login";
	}
	
	@GetMapping("/logout")
	public String lotug() {
		return "/logout";
	}
	
	@GetMapping("/accessDeny")
	public String acd() {
		return "/accessDeny";
	}
	
}
