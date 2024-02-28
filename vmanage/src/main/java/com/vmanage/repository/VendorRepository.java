package com.vmanage.repository;

import java.util.List;

import com.vmanage.entities.VendorEntity;

public interface VendorRepository {

	void save(VendorEntity entity);

	VendorEntity isExistByGstOrNumberOrMailOrSite(String gst, Long number, String email, String website);

	List<VendorEntity> findAll();

	VendorEntity findByEmail(String email);

	VendorEntity updateOtpByEmail(String otp, String email);
}