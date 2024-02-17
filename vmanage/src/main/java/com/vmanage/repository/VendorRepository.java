package com.vmanage.repository;

import com.vmanage.entities.VendorEntity;

public interface VendorRepository {

	void saveRepo(VendorEntity entity);
	
	VendorEntity isExistByNameOrMailOrSite(String name,String email, String website);

}