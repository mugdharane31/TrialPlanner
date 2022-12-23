package com.trialplanner.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.trialplanner.service.UserService;

@RestController
@RequestMapping("/login")
public class LoginController {
	
	@Autowired
	UserService userService;

	
	@RequestMapping(value="/validateuser", method=RequestMethod.GET)
	public Long validateUser(@RequestParam String emailId, @RequestParam String password) {
	    return userService.validateUser(emailId, password);
	}
	
}
