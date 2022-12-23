package com.trialplanner.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trialplanner.dao.TrialPlannerDB;
import com.trialplanner.email.EmailUtility;
import com.trialplanner.model.Questionaire;
import com.trialplanner.model.RespondentDetail;

@Service
public class UserService {
	
	EmailUtility emailUtility;
	
	@Autowired
	TrialPlannerDB trialPlannerOracleDBImpl;
	
	public void sendEmail(RespondentDetail respondentDetail, String surveyId) {
		emailUtility = new EmailUtility();
		String mailContent = "Hello " + respondentDetail.getFirstName() + "\t\n This is a mail from Mugdha's personal website \t\n" 
		+ "http://localhost:8080/survey/" + respondentDetail.getId() + "/" + surveyId;
		emailUtility.sendEmail(respondentDetail.getEmail(), mailContent);
	}
	
	public List<Questionaire> getQuestionaire(String surveyId, String userId) {

		return trialPlannerOracleDBImpl.getQuestionaire(surveyId, userId);
	}
	
	public Long validateUser(String emailId, String password) {
		return trialPlannerOracleDBImpl.validateUser(emailId, password);
	}

	public void saveResponse(String surveyId, String userId, String questionaireId, String response) {
		trialPlannerOracleDBImpl.saveResponse(surveyId, userId, questionaireId, response);
	}
}
