package com.vmanage.login;

import java.time.LocalDateTime;

import com.vmanage.entities.AdminEntity;
import com.vmanage.entities.VendorEntity;

public interface LoginService {

	boolean sendOtp(String email);

	boolean verifyOtp(Integer otp, String email);

	void updateFailedAttemptCount(int failedAttempt, String email);

	boolean unlockAccountTimeExpired(String email);

	void accountLockTime(LocalDateTime accountLocakTime, String email);

	void expireOTPAndResetAttempt(Integer OTP, int resetAttempt, String email);

	boolean findAdminByNameAndPassword(String name, String password);

}
