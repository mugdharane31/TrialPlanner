package com.trialplanner.model;

public class Questionaire {
	
	private int surveyId;
	
	private int questionaireId;

	private String question;
	
	private String response;

	public int getSurveyId() {
		return surveyId;
	}

	public void setSurveyId(int surveyId) {
		this.surveyId = surveyId;
	}

	public int getQuestionaireId() {
		return questionaireId;
	}

	public void setQuestionaireId(int questionaireId) {
		this.questionaireId = questionaireId;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

}
