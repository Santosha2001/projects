package com.vmanager.service.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vmanager.entities.AdminEntity;
import com.vmanager.repository.AdminRepository;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminRepository adminRepository;

	/* FIND ADMIN BY NAME AND PASSWORD */
	@Override
	public boolean findAdminByNameAndPassword(String name, String password) {

		List<AdminEntity> allAdmins = adminRepository.findAllAdmins();
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

	

}
