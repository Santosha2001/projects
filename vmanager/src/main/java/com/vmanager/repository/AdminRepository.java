package com.vmanager.repository;

import java.util.List;

import com.vmanager.entities.AdminEntity;

public interface AdminRepository {

	List<AdminEntity> findAllAdmins();

}
