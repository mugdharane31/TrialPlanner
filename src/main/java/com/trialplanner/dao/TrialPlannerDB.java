package com.trialplanner.dao;

import java.util.List;

import com.trialplanner.model.Questionaire;
import com.trialplanner.model.QuestionnaireResponse;
import com.trialplanner.model.RespondentDetail;
import com.trialplanner.model.SurveyDetail;


public interface TrialPlannerDB {

	public void importRespondents(List<RespondentDetail> respondentList);
	
	public List<RespondentDetail> getRespondentDetails();
	public List<RespondentDetail> insertRespondents(List<RespondentDetail> respondentList);
	
	public List<SurveyDetail> getSurveys();
	
	public List<Questionaire> getQuestionaire(String surveyId, String userId);
	
	public Long validateUser(String emailId, String password);
	
	public void saveResponse(String surveyId, String userId, String questionaireId, String response);
	
	public List<QuestionnaireResponse> getResponses(String surveyId, String userId);
	
	public List<RespondentDetail> getSurveyRespondents(String surveyId);

}
