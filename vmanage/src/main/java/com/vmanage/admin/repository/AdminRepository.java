package com.vmanage.admin.repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.vmanage.entities.AdminEntity;

public interface AdminRepository {

	List<AdminEntity> findAllAdmins();

	void updateStatusByEmail(String status, String email);

	void updateUpdatedDateAndUpdatedBy(String updatedBy, LocalDate updatedDate,String email);
}
