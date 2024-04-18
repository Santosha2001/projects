package com.vmanager.login;

import java.time.Duration;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vmanager.entities.VendorEntity;
import com.vmanager.repository.VendorRepository;
import com.vmanager.service.VendorService;
import com.vmanager.util.EmailSender;
import com.vmanager.util.OtpGenerator;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	private VendorService service;

	@Autowired
	private OtpGenerator otpGenerator;

	@Autowired
	private EmailSender emailSender;

	@Autowired
	private VendorRepository repository;

	public static final long ATTEMPT_TIME = 3;

	public LoginServiceImpl() {
		System.out.println("LoginServiceImpl.");
	}

	@Override
	public boolean sendOtp(String email) {

		VendorEntity byEmail = this.service.findByEmail(email);
		if (byEmail.getVendorEmail().equalsIgnoreCase(email)) {
			Integer otp = otpGenerator.generateOtp();
			System.out.println("OTP: " + otp);

			boolean emailSender2 = emailSender.emailSender(email, "santosha5856@gmail.com", "One Time Password",
					"Your OTP for login is " + otp + ". Don't share with anyone.");

			this.service.updateOtpGeneratedTime(LocalDateTime.now(), email);

			if (emailSender2) {
				this.service.updateOtpByEmail(otp, email);
				System.out.println("otp sent to mail.");

				return true;
			} else {
				System.err.println("otp not sent.");
				return false;
			}
		}

		return false;
	}

	@Override
	public boolean verifyOtp(Integer otp, String email) {
		VendorEntity byEmail = this.repository.findByEmail(email);

		LocalDateTime otpGenratedTime = byEmail.getOtpGenratedTime();
		LocalDateTime currentTime = LocalDateTime.now();
		LocalDateTime accountLockTime = byEmail.getAccountLockTime();

		if (byEmail.getVendorEmail() != null && byEmail.getVendorEmail().equalsIgnoreCase(email)) {

			if (byEmail.getOtp() != null && byEmail.getOtp().equals(otp)
					&& Duration.between(otpGenratedTime, currentTime).getSeconds() < (5 * 60)) {
				System.out.println("otp valid.");
				return true;

			} else if (byEmail.getOtp() != null && byEmail.getOtp().equals(otp)
					&& Duration.between(otpGenratedTime, currentTime).getSeconds() > (5 * 60)) {
				System.out.println("otp expired.");
				expireOTPAndResetAttempt(null, 0, email);
				return false;

			} else if (!byEmail.getOtp().equals(otp)) {

				return validateOTP(email, byEmail, currentTime, accountLockTime);
			}
		}
		return false;
	}

	/* VERIFY OTP */
	private boolean validateOTP(String email, VendorEntity byEmail, LocalDateTime currentTime,
			LocalDateTime accountLockTime) {
		if (byEmail.getFailedAttempt() < ATTEMPT_TIME - 1) {
			updateFailedAttemptCount(byEmail.getFailedAttempt(), email);

		} else if (byEmail.getFailedAttempt() == ATTEMPT_TIME - 1) {
			updateFailedAttemptCount(byEmail.getFailedAttempt(), email);
			accountLockTime(LocalDateTime.now(), email);

		} else {
			expireOTPAndResetAttempt(null, 0, email);
			System.out.println("account is expired.");

			if (Duration.between(accountLockTime, currentTime).getSeconds() > (1 * 60)) {
				unlockAccountTimeExpired(email);
				System.out.println("account will unlocked after 1 minute.");
			}
			return false;
		}
		return false;
	}

	@Override
	public void updateFailedAttemptCount(int failedAttempt, String email) {
		this.repository.updateFailedAttemptCount(failedAttempt + 1, email);
		System.out.println("failed count updated: " + failedAttempt);

	}

	@Override
	public boolean unlockAccountTimeExpired(String email) {
		VendorEntity byEmail = this.repository.findByEmail(email);

		LocalDateTime accountLockTime = byEmail.getAccountLockTime();
		LocalDateTime currentTime = LocalDateTime.now();

		if (Duration.between(accountLockTime, currentTime).getSeconds() > (1 * 60)) {
			byEmail.setAccountNonLocked(true);
			byEmail.setAccountLockTime(null);
			byEmail.setFailedAttempt(0);

			System.out.println("account non locked.");
			return true;
		}

		return false;
	}

	@Override
	public void accountLockTime(LocalDateTime accountLocakTime, String email) {
		this.repository.updateAccountLockTime(accountLocakTime, email);
		System.out.println("account lock time updated.");

	}

	@Override
	public void expireOTPAndResetAttempt(Integer OTP, int resetAttempt, String email) {
		this.repository.expireOTPAndAttempt(OTP, resetAttempt, email);

	}

}
