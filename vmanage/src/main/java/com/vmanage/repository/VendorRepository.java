package com.vmanage.repository;

import com.vmanage.entities.VendorEntity;

public interface VendorRepository {

	void saveRepo(VendorEntity entity);

	VendorEntity isExistByGstOrNumberOrMailOrSite(String gst, Long number, String email, String website);

}