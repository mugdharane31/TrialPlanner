package com.reservemyvax.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "center_detail")
public class CenterDetail {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="center_id")
    private Long centerId;
	
	@Column(name="center_name")
	private String centerName;
	
	@Column(name="address")
	private String address;
	
	@Column(name="num_of_slots")
	private Long numOfSlots;

	@Column(name="date")
	private String date;

	public Long getCenterId() {
		return centerId;
	}

	public void setCenterId(Long centerId) {
		this.centerId = centerId;
	}

	public String getCenterName() {
		return centerName;
	}

	public void setCenterName(String centerName) {
		this.centerName = centerName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Long getNumOfSlots() {
		return numOfSlots;
	}

	public void setNumOfSlots(Long numOfSlots) {
		this.numOfSlots = numOfSlots;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

}
