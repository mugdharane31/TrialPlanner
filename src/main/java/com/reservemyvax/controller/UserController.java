package com.reservemyvax.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.reservemyvax.model.CenterDetail;
import com.reservemyvax.model.VaccinationDetail;
import com.reservemyvax.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserService userService;
	
	@RequestMapping(value="/vaccrecords", method=RequestMethod.GET)
	public List<VaccinationDetail> vaccRecords(@RequestParam String userId) {
	    return userService.vaccRecords(Long.parseLong(userId));
	}
	
	@RequestMapping(value="/pendingvacc", method=RequestMethod.GET)
	public List<VaccinationDetail> pendingVacc(@RequestParam String userId) {
	    return userService.pendingVacc(Long.parseLong(userId));
	}
	
	@RequestMapping(value="/availablecenters", method=RequestMethod.GET)
	public List<CenterDetail> availableCenters(@RequestParam String vaccId) {
	    return userService.availableCenters(Long.parseLong(vaccId));
	}
	
	@RequestMapping(value="/bookvacc", method=RequestMethod.POST)
	public boolean bookVacc(@RequestParam String centerId) {
		System.out.println("bookvacc");
	    return userService.bookVacc(Long.parseLong(centerId));
	}
}
