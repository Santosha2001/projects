package com.vmanage.admin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vmanage.admin.repository.AdminRepository;
import com.vmanage.entities.AdminEntity;
import com.vmanage.entities.VendorEntity;
import com.vmanage.repository.VendorRepository;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminRepository repository;
	
	@Autowired
	private VendorRepository vendorRepository;

	/* FIND ADMIN BY NAME AND PASSWORD */
	@Override
	public boolean findAdminByNameAndPassword(String name, String password) {

		List<AdminEntity> allAdmins = repository.findAllAdmins();
		for (AdminEntity adminEntity : allAdmins) {
			if (adminEntity != null) {
				if (adminEntity.getAdminName().equalsIgnoreCase(name)
						&& adminEntity.getAdminPassword().equalsIgnoreCase(password)) {
					System.out.println("Admin login success.");
					return true;
				}
			}
		}

		return false;
	}

	@Override
	public boolean approveStatus(String email) {
		
		VendorEntity byEmail = vendorRepository.findByEmail(email);
		
		if (byEmail.getVendorEmail().equalsIgnoreCase(email)) {
			byEmail.setApplyStatus("APPROVED");
			
			vendorRepository.save(byEmail);
			System.out.println(byEmail);
			System.out.println("status updated.");
			
			return true;
		}
		
		return false;
	}
	
	

}
