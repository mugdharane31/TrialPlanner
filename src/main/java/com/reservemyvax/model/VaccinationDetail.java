package com.reservemyvax.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "vaccination_detail")
public class VaccinationDetail {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="vacc_id")
    private Long vaccId;
	
	@Column(name="vacc_name")
	private String vaccName;
	
	private boolean completed;

	public Long getVaccId() {
		return vaccId;
	}

	public void setVaccId(Long vaccId) {
		this.vaccId = vaccId;
	}

	public String getVaccName() {
		return vaccName;
	}

	public void setVaccName(String vaccName) {
		this.vaccName = vaccName;
	}

	public boolean isCompleted() {
		return completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}
	
}
