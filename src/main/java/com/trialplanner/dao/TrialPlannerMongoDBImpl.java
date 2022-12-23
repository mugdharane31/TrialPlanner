package com.trialplanner.dao;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.json.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.trialplanner.model.Questionaire;
import com.trialplanner.model.QuestionnaireResponse;
import com.trialplanner.model.RespondentDetail;
import com.trialplanner.model.SurveyDetail;

@Repository
@Component("trialPlannerMongoDBImpl")
public class TrialPlannerMongoDBImpl implements TrialPlannerDB{

	// Creating a Mongo client 
	private static MongoClient mongo = new MongoClient( "localhost" , 27017 );
	
	// Accessing the database 
	private static MongoDatabase database = mongo.getDatabase("trialplannerDB");
	
	// Retrieving a collection
	private static MongoCollection<Document> collectionResp = database.getCollection("respondents");
	
	@Override
	public void importRespondents(List<RespondentDetail> respondentList) {
		
		
		Document document;
		List<Document> documents = new ArrayList<Document>();
		for(RespondentDetail respDetail: respondentList) {
			document = new Document("userid", respDetail.getId()).append("email", respDetail.getEmail())
					.append("firstName", respDetail.getFirstName())
					.append("lastName", respDetail.getLastName())
					.append("city", respDetail.getCity())
					.append("state", respDetail.getState());
			
			documents.add(document);
		}
		
		//Inserting document into the collection
		collectionResp.insertMany(documents);
		System.out.println("Document inserted successfully");
	}

	@Override
	public List<RespondentDetail> getRespondentDetails() { 
		System.out.println("TrialPlannerMongoDBImpl.getRespondentDetails");
		FindIterable<Document> iterDoc = collectionResp.find();
		MongoCursor<Document> cursor = iterDoc.iterator();
		List<RespondentDetail> respList = new ArrayList<RespondentDetail>();
        try {
            while(cursor.hasNext()) {
            	JSONObject obj = new JSONObject(cursor.next().toJson());
            	RespondentDetail resp = new RespondentDetail(obj.getInt("userid"), obj.getString("email"), obj.getString("firstName"), obj.getString("lastName"), obj.getString("city"), obj.getString("state"));
            	respList.add(resp);
            }
        } finally {
            cursor.close();
        }
		
		return respList;
	}

	@Override
	public List<RespondentDetail> insertRespondents(List<RespondentDetail> respondentList) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SurveyDetail> getSurveys() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Questionaire> getQuestionaire(String surveyId, String userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long validateUser(String emailId, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveResponse(String surveyId, String userId, String questionaireId, String response) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<QuestionnaireResponse> getResponses(String surveyId, String userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RespondentDetail> getSurveyRespondents(String surveyId) {
		// TODO Auto-generated method stub
		return null;
	}

}
