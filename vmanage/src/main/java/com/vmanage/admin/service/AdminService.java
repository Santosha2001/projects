package com.vmanage.admin.service;

public interface AdminService {

	boolean findAdminByNameAndPassword(String name, String password);

	boolean approveStatus(String email);
}
