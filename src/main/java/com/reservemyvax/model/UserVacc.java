package com.reservemyvax.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_vacc")
public class UserVacc {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="user_vacc_id")
    private Long userVaccId;
	
	@Column(name="user_id")
	private Long userId;

	@Column(name="vacc_id")
	private Long vaccId;

	public Long getUserVaccId() {
		return userVaccId;
	}

	public void setUserVaccId(Long userVaccId) {
		this.userVaccId = userVaccId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getVaccId() {
		return vaccId;
	}

	public void setVaccId(Long vaccId) {
		this.vaccId = vaccId;
	}
}
