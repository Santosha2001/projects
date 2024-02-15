package com.vmanage.repository;

import org.springframework.stereotype.Repository;

import com.vmanage.entities.VendorEntity;

@Repository
public class VendorRepositoryImpl implements VendorRepository {

	public VendorRepositoryImpl() {
		System.out.println("VendorRepositoryImpl created.");
	}

	@Override
	public void saveRepo(VendorEntity entity) {
		System.out.println(entity);
	}
}
