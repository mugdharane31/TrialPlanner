package com.reservemyvax.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.reservemyvax.model.CenterDetail;
import com.reservemyvax.model.CenterList;
import com.reservemyvax.service.AdminService;

@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	AdminService adminService;
	
	@RequestMapping(value="/importcenters", method=RequestMethod.POST)
	public int[][] importCenters(@RequestBody CenterList centerList) {
	    return adminService.importCenters(centerList.getCenterDetails());
	}
	
	@RequestMapping(value="/addcenter", method=RequestMethod.POST)
	public int[][] addCenters(@RequestBody CenterList centerList) {
	    return adminService.importCenters(centerList.getCenterDetails());
	}
	
	@RequestMapping(value="/getcenterdetails", method=RequestMethod.GET)
	public CenterList getCenterDetails() {
		List<CenterDetail> centerDetail = adminService.getCenterDetails();
		CenterList centerList = new CenterList();
		centerList.setCenterDetails(centerDetail);
		return centerList;
	}
	
	@RequestMapping(value="/updatecenterdetails", method=RequestMethod.POST)
	public boolean updateCenterDetails(@RequestBody CenterList centerList) {
		return adminService.updateCenterDetails(centerList.getCenterDetails().get(0));
	}
}
