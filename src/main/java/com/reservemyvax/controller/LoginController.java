package com.reservemyvax.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.reservemyvax.model.User;
import com.reservemyvax.service.UserService;

@RestController
@RequestMapping("/login")
public class LoginController {
	
	@Autowired
	UserService userService;

	@RequestMapping(value="/createuser", method=RequestMethod.POST)
	public boolean createUser(@RequestBody User user) {
	    return userService.createUser(user) == 1 ? true : false;
	}
	
	@RequestMapping(value="/validateuser", method=RequestMethod.GET)
	public boolean validateUser(@RequestBody User user) {
	    return userService.validateUser(user);
	}
	
}
