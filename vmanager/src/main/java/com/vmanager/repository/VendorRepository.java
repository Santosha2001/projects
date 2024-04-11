package com.vmanager.repository;

import java.time.LocalDateTime;
import java.util.List;

import com.vmanager.entities.VendorEntity;

public interface VendorRepository {

	void save(VendorEntity entity);

	VendorEntity isExistByGstOrNumberOrMailOrSite(String gst, Long number, String email, String website);

	List<VendorEntity> findAll();

	VendorEntity findByEmail(String email);

	void updateOtpByEmail(Integer otp, String email);

	void updatedOtpGeneratedTime(LocalDateTime otpGeneratedTime, String email);

	void updateFailedAttemptCount(int failedCount, String email);

	void updateAccountLockTime(LocalDateTime accountLockTime, String email);

	void expireOTPAndAttempt(Integer OTP, int resetAttempt, String email);

}
