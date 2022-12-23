package com.trialplanner.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.trialplanner.model.Questionaire;
import com.trialplanner.model.QuestionnaireResponse;
import com.trialplanner.model.RespondentDetail;
import com.trialplanner.model.SurveyDetail;
import com.trialplanner.resources.ReadPropertiesFile;

@Repository
@Component("trialPlannerOracleDBImpl")
public class TrialPlannerOracleDBImpl implements TrialPlannerDB{
    private static Properties properties = ReadPropertiesFile.readPropertiesFile("application.properties");
	
	/*
	 * @Autowired private JdbcTemplate jdbcTemplate;
	 */
	
	@Override
	public List<RespondentDetail> insertRespondents(List<RespondentDetail> respondentList) {
		List<RespondentDetail> respondentListRet = new ArrayList<RespondentDetail>();
        try{
        	Class.forName(properties.getProperty("class.forname"));
    	    Connection con = DriverManager.getConnection(properties.getProperty("dburl"), properties.getProperty("dbusername"),
    		    properties.getProperty("dbpassword"));
			PreparedStatement stmt;
			for(RespondentDetail resp : respondentList) {
				RespondentDetail respTmp = resp;
			
				PreparedStatement pst = con.prepareStatement("select RESP_SEQ.NEXTVAL from dual");
				long sqlIdentifier = 0;
				ResultSet rs = pst.executeQuery();
				if(rs.next())
					sqlIdentifier = rs.getLong(1);
			    // Inserting data in database
				stmt = con.prepareStatement("insert into respondents values (?,?,?,?,?)");
				stmt.setLong(1, sqlIdentifier);
				stmt.setString(2, resp.getFirstName());
				stmt.setString(3, resp.getLastName());
				stmt.setString(4, resp.getEmail());
				stmt.setString(5, resp.getEmail());
				stmt.executeUpdate();
				respTmp.setId((int) sqlIdentifier);
				respondentListRet.add(respTmp);
            }
            con.close();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }

		return respondentListRet;
	}

	@Override
	public List<SurveyDetail> getSurveys() { 
		List<SurveyDetail> surveyDetailret = new ArrayList<SurveyDetail>();
        try{
        	Class.forName(properties.getProperty("class.forname"));
    	    Connection con = DriverManager.getConnection(properties.getProperty("dburl"), properties.getProperty("dbusername"),
    		    properties.getProperty("dbpassword"));
		
			PreparedStatement pst = con.prepareStatement("select survey_id, survey_name from survey");
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				SurveyDetail surveyTmp = new SurveyDetail();
				surveyTmp.setSurveyId((int) rs.getLong(1));
				surveyTmp.setSurveyName(rs.getString(2));

				surveyDetailret.add(surveyTmp);
			}

            con.close();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }

		return surveyDetailret;
	}

	@Override
	public List<Questionaire> getQuestionaire(String surveyId, String userId) { 
		List<Questionaire> questionaireret = new ArrayList<Questionaire>();
        try{
        	Class.forName(properties.getProperty("class.forname"));
    	    Connection con = DriverManager.getConnection(properties.getProperty("dburl"), properties.getProperty("dbusername"),
    		    properties.getProperty("dbpassword"));
		
			PreparedStatement pst = con.prepareStatement("select SURVEY_ID, QUESTIONAIRE_ID, QUESTION_TEXT from QUESTIONAIRE where SURVEY_ID=" + surveyId);
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				Questionaire questionaireTmp = new Questionaire();
				questionaireTmp.setSurveyId((int) rs.getLong(1));
				questionaireTmp.setQuestionaireId((int) rs.getLong(2));
				questionaireTmp.setQuestion(rs.getString(3));
				PreparedStatement pst1 = con.prepareStatement("select response from RESPONDENT_RESPONSE where QUESTIONAIRE_ID = " 
				+ rs.getLong(2) + " and SURVEY_ID=" + rs.getLong(1) + " and USERID=" + userId);
				ResultSet rs1 = pst1.executeQuery();
				if(rs1.next()) {
					questionaireTmp.setResponse(rs1.getString(1));
				}else {
					questionaireTmp.setResponse("None");
				}
				questionaireret.add(questionaireTmp);
			}

            con.close();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }

		return questionaireret;
	}
	
	@Override
	public Long validateUser(String emailId, String password) {
		
		try{
        	Class.forName(properties.getProperty("class.forname"));
    	    Connection con = DriverManager.getConnection(properties.getProperty("dburl"), properties.getProperty("dbusername"),
    		    properties.getProperty("dbpassword"));
		
			PreparedStatement pst = con.prepareStatement("SELECT USERID FROM respondents WHERE email = '" + emailId +"' and password = '" + password + "'");
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				return (Long) rs.getLong(1);
			}
            con.close();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
		return (long) -1;
	}

	@Override
	public void saveResponse(String surveyId, String userId, String questionaireId, String response) {
		try{
        	Class.forName(properties.getProperty("class.forname"));
    	    Connection con = DriverManager.getConnection(properties.getProperty("dburl"), properties.getProperty("dbusername"),
    		    properties.getProperty("dbpassword"));
			PreparedStatement stmt;
			
			PreparedStatement pst = con.prepareStatement("select * from RESPONDENT_RESPONSE where QUESTIONAIRE_ID = " + questionaireId 
					+ " and SURVEY_ID="+surveyId + " and USERID=" + userId);
			ResultSet rs = pst.executeQuery();
			if(rs.next()) {
				PreparedStatement pstdel = con.prepareStatement("UPDATE RESPONDENT_RESPONSE SET RESPONSE = '" + response + "' where QUESTIONAIRE_ID = " + questionaireId 
						+ " and SURVEY_ID="+surveyId + " and USERID=" + userId);
				pstdel.executeQuery();
			}else {
			    // Inserting data in database
				stmt = con.prepareStatement("insert into RESPONDENT_RESPONSE values (?,?,?,?)");
				stmt.setLong(1, Long.parseLong(questionaireId));
				stmt.setLong(2, Long.parseLong(surveyId));
				stmt.setLong(3, Long.parseLong(userId));
				stmt.setString(4, response);
				stmt.executeUpdate();
			}
            con.close();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
		
	}
	
	@Override
	public List<QuestionnaireResponse> getResponses(String surveyId, String userId) {
		List<QuestionnaireResponse> questResponseret = new ArrayList<QuestionnaireResponse>();
        try{
        	Class.forName(properties.getProperty("class.forname"));
    	    Connection con = DriverManager.getConnection(properties.getProperty("dburl"), properties.getProperty("dbusername"),
    		    properties.getProperty("dbpassword"));
		
			PreparedStatement pst = con.prepareStatement("select q.QUESTIONAIRE_ID, q.QUESTION_TEXT, rr.RESPONSE from "
					+ "QUESTIONAIRE q, respondent_response rr where q.questionaire_id=rr.questionaire_id and rr.survey_id = "+ surveyId + " and rr.userid = " + userId );
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				QuestionnaireResponse questResponseTmp = new QuestionnaireResponse();
				questResponseTmp.setQuestionaireId((int) rs.getLong(1));
				questResponseTmp.setQuestion(rs.getString(2));
				questResponseTmp.setResponse(rs.getString(3));

				questResponseret.add(questResponseTmp);
			}

            con.close();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }

		return questResponseret;
	}
	
	@Override
	public void importRespondents(List<RespondentDetail> respondentList) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<RespondentDetail> getRespondentDetails() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RespondentDetail> getSurveyRespondents(String surveyId) {
		List<RespondentDetail> respondentDetailret = new ArrayList<RespondentDetail>();
        try{
        	Class.forName(properties.getProperty("class.forname"));
    	    Connection con = DriverManager.getConnection(properties.getProperty("dburl"), properties.getProperty("dbusername"),
    		    properties.getProperty("dbpassword"));
		
			PreparedStatement pst = con.prepareStatement("select * from RESPONDENTS where userid in ( select userid from RESPONDENT_RESPONSE where survey_id = " + surveyId  + ")");
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				RespondentDetail respondentTmp = new RespondentDetail((int) rs.getLong(1), rs.getString(4), rs.getString(2), rs.getString(3));

				respondentDetailret.add(respondentTmp);
			}

            con.close();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }

		return respondentDetailret;
	}

}
