package com.trialplanner.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class Functions {

	@RequestMapping("/home")
	public String getHomepage() {
		return "forward:/index.html";
	}
	
	@RequestMapping("/")
    public String getLoginpage() {
        return "forward:/login.html";
    }
	@RequestMapping("survey/{userId}/{surveyId}")
	public ModelAndView handleRequest(@PathVariable("userId") String userId,
			 @PathVariable("surveyId") String surveyId, Model model) {

		model.addAttribute("userId", userId);
		model.addAttribute("surveyId",surveyId);
		return new ModelAndView("survey");
	}
}
