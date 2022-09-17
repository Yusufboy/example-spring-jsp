package com.renova.demoApplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.renova.demoApplication.auth.CustomUserDetailsService;

@Controller
@RequestMapping("/e-commerce")
public class UserController {

	@Autowired
	private final CustomUserDetailsService customUserDetailsService;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage(ModelMap model) {
		return "login";
    }

//	@RequestMapping(value = "/login", method = RequestMethod.POST)
//	public String checkLogin(ModelMap model,@RequestParam String username,@RequestParam String password) {	
//		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//		if(encoder.matches(password,customUserDetailsService.loadUserByUsername(username).getPassword())) {
//			return "view-products";
//		}
//		else {
//			return "login";
//		}
//    }

	public UserController(CustomUserDetailsService customUserDetailsService) {
		super();
		this.customUserDetailsService = customUserDetailsService;
	}
}