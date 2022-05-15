package com.reservemyvax.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reservemyvax.dao.ReserveMyVaxDAO;
import com.reservemyvax.model.CenterDetail;
import com.reservemyvax.model.User;
import com.reservemyvax.model.VaccinationDetail;

@Service
public class UserService {
	
	@Autowired
	ReserveMyVaxDAO reserveMyVaxDAO;
	
	public Long createUser(User user) {
		User usr = findByEmail(user);
		if(usr == null){
			return reserveMyVaxDAO.save(user);
		}else {
			return 0L;
		}
	}
	
	public boolean validateUser(User user) {
		return reserveMyVaxDAO.validateUser(user);
	}

	public User findByEmail(User user) {
		return reserveMyVaxDAO.findByEmail(user.getEmailId());
	}
	
	public List<VaccinationDetail> vaccRecords(Long userId) {
		return reserveMyVaxDAO.vaccRecords(userId);
	}
	
	public List<VaccinationDetail> pendingVacc(Long userId) {
		return reserveMyVaxDAO.pendingVacc(userId);
	}
	
	public List<CenterDetail> availableCenters(Long vaccId) {
		return reserveMyVaxDAO.availableCenters(vaccId);
	}	
	
	public boolean bookVacc(Long centerId) {
		return reserveMyVaxDAO.bookVacc(centerId);
	}
	
}
