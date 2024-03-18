package com.vmanage.admin.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
	private VendorRepository repository2;

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

	/* APPROVE VENDOR STATUS BY ADMIN */
	@Override
	public boolean approveStatus(String email) {

		repository.updateStatusByEmail("APPROVED", email);

		// repository.updateUpdatedDateAndUpdatedBy(, LocalDate.now(), email);

		return true;
	}

}
