package com.vmanager.repository;

import java.time.LocalDate;
import java.util.List;

import com.vmanager.entities.AdminEntity;

public interface AdminRepository {

	List<AdminEntity> findAllAdmins();

	void updateStatusByEmail(String status, String email);

	void updateUpdatedDateAndUpdatedBy(String updatedBy, LocalDate updatedDate, String email);
}
