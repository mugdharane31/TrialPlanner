package com.reservemyvax.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reservemyvax.dao.ReserveMyVaxDAO;
import com.reservemyvax.model.CenterDetail;

@Service
public class AdminService {

	@Autowired
	ReserveMyVaxDAO reserveMyVaxDAO;
	
	public int[][] importCenters(List<CenterDetail> centerDetails) {
		return reserveMyVaxDAO.importCenters(centerDetails);
	}
	
	public List<CenterDetail> getCenterDetails() {
		return reserveMyVaxDAO.getCenterDetails();
	}
	
	public boolean updateCenterDetails(CenterDetail centerDetail) {
		return reserveMyVaxDAO.updateCenterDetails(centerDetail);
	}
	
	
}
