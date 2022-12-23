package com.trialplanner.model;

public class RespondentDetail {

	private int id;
	private String email;
	private String firstName;
	private String lastName;
	private String city;
	private String state;
	
	public RespondentDetail() {
		super();
	}

	public RespondentDetail(String email, String firstName, String lastName, String city, String state) {
		super();
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.city = city;
		this.state = state;
	}

	public RespondentDetail(int id, String email, String firstName, String lastName) {
		super();
		this.id = id;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	public RespondentDetail(int id, String email, String firstName, String lastName, String city, String state) {
		super();
		this.id = id;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.city = city;
		this.state = state;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

}
