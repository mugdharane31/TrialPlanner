package com.trialplanner.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.trialplanner.service.AdminService;
import com.trialplanner.model.QuestionnaireResponse;
import com.trialplanner.model.RespondentDetail;
import com.trialplanner.model.SurveyDetail;

@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	AdminService adminService;
	
	@RequestMapping(value="/importrespondents", method=RequestMethod.POST)
	public void importRespondents(@RequestBody List<RespondentDetail> respondentList) {
	    adminService.importRespondents(respondentList);
	}
	
	@RequestMapping(value="/getrespondentdetails", method=RequestMethod.GET)
	public List<RespondentDetail> getRespondentDetails() {
		List<RespondentDetail> centerDetail = adminService.getRespondentDetails();

		return centerDetail;
	}
	
	@RequestMapping(value="/getsurveys", method=RequestMethod.GET)
	public List<SurveyDetail> getSurveys() {
	    return adminService.getSurveys();
	}
	
	@RequestMapping(value="/getsurveyrespondents", method=RequestMethod.GET)
	public List<RespondentDetail> getSurveyRespondents(@RequestParam String surveyId) {
	    return adminService.getSurveyRespondents(surveyId);
	}
	
	@RequestMapping(value="/showresponses", method=RequestMethod.GET)
	public List<QuestionnaireResponse> getResponses(@RequestParam String surveyId, @RequestParam String userId) {
	    return adminService.getResponses(surveyId, userId);
	}
}
