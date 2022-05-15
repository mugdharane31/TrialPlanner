package com.reservemyvax.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ParameterizedPreparedStatementSetter;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.reservemyvax.model.CenterDetail;
import com.reservemyvax.model.User;
import com.reservemyvax.model.UserVacc;
import com.reservemyvax.model.VaccinationDetail;

@Repository
public class ReserveMyVaxDAOImpl implements ReserveMyVaxDAO{

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	private int batchSize = 10;
	
	@Override
	public Long save(User user) {
		return (long) jdbcTemplate.update("INSERT INTO user (first_name, last_name, email_id, password) "
				+ "values (?, ?, ?, ?)"
				, new Object[] {user.getFirstName(), user.getLastName(), user.getEmailId(), user.getPassword()});
	}

	@Override
	public User findByEmail(String emailId) {
		
		String sql = "SELECT * FROM user WHERE email_id = ?";
		try {
	        return (User) jdbcTemplate.queryForObject(
				sql, 
				new Object[]{emailId}, 
				new BeanPropertyRowMapper(User.class));
		}catch(EmptyResultDataAccessException e) {
			return null;
		}
		
	}

	@Override
	public boolean validateUser(User user) {

		String sql = "SELECT * FROM user WHERE email_id = ? and password = ?";
		try {
	        User retUser = (User) jdbcTemplate.queryForObject(
				sql, 
				new Object[]{user.getEmailId(), user.getPassword()}, 
				new BeanPropertyRowMapper(User.class));
	        if(retUser != null) {
	        	return true;
	        }
		}catch(EmptyResultDataAccessException e) {
			return false;
		}
		return false;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public int[][] importCenters(List<CenterDetail> centerDetails) {
		
		int[][] updateCounts = jdbcTemplate.batchUpdate(
                "insert into center_detail (center_name, address, num_of_slots, date) values(?,?,?,?)",
                centerDetails,
                batchSize,
                new ParameterizedPreparedStatementSetter<CenterDetail>() {
                    public void setValues(PreparedStatement ps, CenterDetail argument) 
						throws SQLException {
                        ps.setString(1, argument.getCenterName());
                        ps.setString(2, argument.getAddress());
                        ps.setLong(3, argument.getNumOfSlots());
                        ps.setString(4, argument.getDate());
                    }
                });
        return updateCounts;
	}

	@Override
	public List<CenterDetail> getCenterDetails() {
		String sql = "SELECT * FROM center_detail";

        List<CenterDetail> centerDetail = jdbcTemplate.query(
                sql,
                new BeanPropertyRowMapper(CenterDetail.class));

        return centerDetail;
		
	}

	@Override
	public boolean updateCenterDetails(CenterDetail centerDetail) {
		int ret = (int)jdbcTemplate.update("update center_detail set center_name = ?, address = ?"
				+ ", num_of_slots = ?, date= ? where center_id = ? "
				, new Object[] {centerDetail.getCenterName(), centerDetail.getAddress()
				, centerDetail.getNumOfSlots(), centerDetail.getDate(), centerDetail.getCenterId()});
		return ret == 1 ? true : false;
	}

	@Override
	public List<VaccinationDetail> vaccRecords(Long userId) {
		
		String sql1 = "SELECT * FROM vaccination_detail";

        List<VaccinationDetail> vaccDetails = jdbcTemplate.query(
                sql1,
                new BeanPropertyRowMapper(VaccinationDetail.class));
		
        String sql2 = "SELECT * FROM user_vacc";

        List<UserVacc> userVaccs = jdbcTemplate.query(
                sql2,
                new BeanPropertyRowMapper(UserVacc.class));
        
        for(VaccinationDetail vaccDetail: vaccDetails) {
        	for(UserVacc userVacc: userVaccs) {
        		if(vaccDetail.getVaccId() == userVacc.getVaccId()) {
        			vaccDetail.setCompleted(true);
        			break;
        		}
        	}
        	
        }
        return vaccDetails;
	}

	@Override
	public List<VaccinationDetail> pendingVacc(Long userId) {
        
        String sql1 = "SELECT * FROM vaccination_detail";

        List<VaccinationDetail> vaccDetails = jdbcTemplate.query(
                sql1,
                new BeanPropertyRowMapper(VaccinationDetail.class));
		
        String sql2 = "SELECT * FROM user_vacc where user_id = " + userId;

        List<UserVacc> userVaccs = jdbcTemplate.query(
                sql2,
                new BeanPropertyRowMapper(UserVacc.class));
        
        
    	for(UserVacc userVacc: userVaccs) {
    		for(VaccinationDetail vaccDetail: vaccDetails) {
    			if(vaccDetail.getVaccId() == userVacc.getVaccId()) {
        			vaccDetail.setCompleted(true);
        			break;
        		}
    		}
        }
    	
    	List<VaccinationDetail> vaccDetailsRet = new ArrayList<VaccinationDetail>();
    	
    	for(VaccinationDetail vaccDetail: vaccDetails) {
    		if(!vaccDetail.isCompleted()) {
    			vaccDetailsRet.add(vaccDetail);
    		}
    	}
        return vaccDetailsRet;
	}

	@Override
	public List<CenterDetail> availableCenters(Long vaccId) {
		String sql = "SELECT * FROM center_detail where num_of_slots > 0 and vacc_id = " + vaccId;

        List<CenterDetail> centerDetail = jdbcTemplate.query(sql,
                new BeanPropertyRowMapper(CenterDetail.class));

        return centerDetail;
	}

	@Override
	public synchronized boolean bookVacc(Long centerId) {
		String sql1 = "SELECT * FROM center_detail WHERE center_id = ?";
		try {
	        
			CenterDetail centerDetail = (CenterDetail)jdbcTemplate.queryForObject(sql1, 
				new Object[]{centerId}, new BeanPropertyRowMapper(CenterDetail.class));
			Long num = centerDetail.getNumOfSlots();
			num = num - 1;
			
			int ret = (int)jdbcTemplate.update("update center_detail set num_of_slots = ?"
					+ " where center_id = ? "
					, new Object[] {num, centerId});
			return ret == 1 ? true : false;
			
			
		}catch(EmptyResultDataAccessException e) {
			return false;
		}
	}
}
