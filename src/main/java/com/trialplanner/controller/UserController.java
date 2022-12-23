package com.trialplanner.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.trialplanner.model.Questionaire;
import com.trialplanner.model.RespondentDetail;
import com.trialplanner.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserService userService;

	@RequestMapping(value="/sendemail", method=RequestMethod.POST)
	public void sendEmail(@RequestBody RespondentDetail respondentDetail, @RequestParam String surveyId) {
	    userService.sendEmail(respondentDetail, surveyId);
	}
	
	@RequestMapping(value="/getquestionaire", method=RequestMethod.GET)
	public List<Questionaire> getQuestionaire(@RequestParam String userId, @RequestParam String surveyId) {
		List<Questionaire> questions = userService.getQuestionaire(surveyId, userId);
		return questions;
	}
	
	@RequestMapping(value="/saveresponse", method=RequestMethod.POST)
	public void saveResponse(@RequestParam String surveyId, @RequestParam String userId, @RequestParam String questionaireId, @RequestParam String response) {
		userService.saveResponse(surveyId, userId, questionaireId, response);
	}
}
