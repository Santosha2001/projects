package com.vmanage.service;

import com.vmanage.entities.VendorEntity;

public interface VendorService {

	VendorEntity save(VendorEntity entity);

	String isExistByNameOrMailOrSite(String name, String email, String website);
}