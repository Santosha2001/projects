package com.vmanage.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vmanage.entities.VendorEntity;
import com.vmanage.repository.VendorRepository;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private VendorRepository repository;
	
	
	@Override
	public void statusApprove(String email) {
		
		VendorEntity byEmail = this.repository.findByEmail(email);
		if (byEmail!=null) {
			if (byEmail.getVendorEmail().equalsIgnoreCase(email)) {
				
				byEmail.setApplyStatus("APPROVED");
			}
		}
		
		

	}

}
