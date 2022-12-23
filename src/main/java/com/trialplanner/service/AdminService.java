package com.trialplanner.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trialplanner.dao.TrialPlannerDB;
import com.trialplanner.model.QuestionnaireResponse;
import com.trialplanner.model.RespondentDetail;
import com.trialplanner.model.SurveyDetail;

@Service
public class AdminService {
	
	@Autowired
	TrialPlannerDB trialPlannerMongoDBImpl;
	
	@Autowired
	TrialPlannerDB trialPlannerOracleDBImpl;
	
	
	public void importRespondents(List<RespondentDetail> centerDetails) {
		//trialPlannerDB.connection();
		
		//save in mongo database
		trialPlannerMongoDBImpl.importRespondents(trialPlannerOracleDBImpl.insertRespondents(centerDetails));
	}
	
	public List<RespondentDetail> getRespondentDetails() {
		return trialPlannerMongoDBImpl.getRespondentDetails();
	}
	
	public List<SurveyDetail> getSurveys() {
		return trialPlannerOracleDBImpl.getSurveys();
	}
	
	public List<RespondentDetail> getSurveyRespondents(String surveyId) {
		return trialPlannerOracleDBImpl.getSurveyRespondents(surveyId);
	}
	
	public List<QuestionnaireResponse> getResponses(String surveyId, String userId) {
		return trialPlannerOracleDBImpl.getResponses(surveyId, userId);
	}
}
