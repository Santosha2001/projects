package com.vmanage.login;

import java.time.LocalDateTime;

import com.vmanage.entities.VendorEntity;

public interface LoginService {

	void sendOtp(String email);

	VendorEntity verifyOtp(Integer otp, String email);

	void updateFailedAttemptCOunt(int failedAttempt, String email);
	
	void lockAccount(String email);
	
	void resetAttemptCount(String email);
	
	boolean unlockAccountTimeExpired(String email);
	
//	void accountLockTime(LocalDateTime accountLocakTime,String email);
}
