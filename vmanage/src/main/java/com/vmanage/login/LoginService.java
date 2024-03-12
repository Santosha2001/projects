package com.vmanage.login;

import java.time.LocalDateTime;

import com.vmanage.entities.AdminEntity;
import com.vmanage.entities.VendorEntity;

public interface LoginService {

	void sendOtp(String email);

	VendorEntity verifyOtp(Integer otp, String email);

	void updateFailedAttemptCount(int failedAttempt, String email);

	boolean unlockAccountTimeExpired(String email);

	void accountLockTime(LocalDateTime accountLocakTime, String email);

	void expireOTPAndResetAttempt(Integer OTP, int resetAttempt, String email);

	AdminEntity findAdminByNameAndPassword(String name, String password);
}
