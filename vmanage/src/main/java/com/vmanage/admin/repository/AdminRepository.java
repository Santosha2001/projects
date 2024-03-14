package com.vmanage.admin.repository;

import java.util.List;

import com.vmanage.entities.AdminEntity;

public interface AdminRepository {

	List<AdminEntity> findAllAdmins();

	void updateStatusByEmail(String status, String email);
}
