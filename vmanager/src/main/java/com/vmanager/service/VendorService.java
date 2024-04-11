package com.vmanager.service;

import java.time.LocalDateTime;

import com.vmanager.entities.VendorEntity;

public interface VendorService {

	VendorEntity save(VendorEntity entity);

	String isExistByGstOrNumberOrMailOrSite(String gst, Long number, String email, String website);

	boolean sendEmail(String email, String username);

	VendorEntity findByEmail(String email);

	void updateOtpByEmail(Integer otp, String email);

	void updateOtpGeneratedTime(LocalDateTime otpGeneratedTime, String email);

}