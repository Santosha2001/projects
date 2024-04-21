package com.vmanager.login;

import java.time.LocalDateTime;

public interface LoginService {

	boolean sendOtpToVendorEmail(String email);

	boolean verifyOtp(Integer otp, String email);

	void updateVendorFailedAttemptCount(int failedAttempt, String email);

	boolean unlockVendorAccountTimeExpired(String email);

	void vendorAccountLockTime(LocalDateTime accountLocakTime, String email);

	void expireOTPAndResetAttempt(Integer OTP, int resetAttempt, String email);
}
