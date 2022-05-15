package com.reservemyvax.dao;

import java.util.List;

import com.reservemyvax.model.CenterDetail;
import com.reservemyvax.model.User;
import com.reservemyvax.model.VaccinationDetail;

public interface ReserveMyVaxDAO {

	Long save(User user);
	
	User findByEmail(String email);
	
	boolean validateUser(User user);

	int[][] importCenters(List<CenterDetail> centerDetails);
	
	List<CenterDetail> getCenterDetails();
	
	boolean updateCenterDetails(CenterDetail centerDetail);
	
	List<VaccinationDetail> vaccRecords(Long userId);
	
	List<VaccinationDetail> pendingVacc(Long userId);
	
	List<CenterDetail> availableCenters(Long vaccId);
	
	boolean bookVacc(Long centerId);
}
