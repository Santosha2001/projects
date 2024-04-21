package com.vmanager.login;

import java.time.Duration;
import java.time.LocalDateTime;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.vmanager.dto.VendorDTO;
import com.vmanager.entities.VendorEntity;
import com.vmanager.repository.VendorRepository;
import com.vmanager.service.vendor.VendorService;
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

	/* SEND OTP TO MAIL */
	@Override
	public boolean sendOtpToVendorEmail(String email) {

		VendorDTO dto = new VendorDTO();
		VendorEntity byEmail = this.repository.findByEmail(email);
		System.out.println("bymail in sendOtp: " + byEmail);

		if (ObjectUtils.isEmpty(byEmail)) {
			return false;
		} else if (byEmail.getVendorEmail().equalsIgnoreCase(email)) {
			BeanUtils.copyProperties(byEmail, dto);

			System.out.println("dto in send otp: " + dto);
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

	/* VERIFY OTP */
	@Override
	public boolean verifyOtp(Integer otp, String email) {

		VendorDTO dto = new VendorDTO();
		VendorEntity byEmail = this.repository.findByEmail(email);

		LocalDateTime otpGenratedTime = byEmail.getOtpGenratedTime();
		LocalDateTime currentTime = LocalDateTime.now();
		LocalDateTime accountLockTime = byEmail.getAccountLockTime();

		System.out.println("byEmail in verifyOTP: " + byEmail);

		if (ObjectUtils.isEmpty(byEmail)) {
			return false;
		}

		else if (byEmail.getVendorEmail() != null && byEmail.getVendorEmail().equalsIgnoreCase(email)) {

			BeanUtils.copyProperties(byEmail, dto);
			System.out.println("dto in verifyOTP: " + dto);
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
			updateVendorFailedAttemptCount(byEmail.getFailedAttempt(), email);

		} else if (byEmail.getFailedAttempt() == ATTEMPT_TIME - 1) {
			updateVendorFailedAttemptCount(byEmail.getFailedAttempt(), email);
			vendorAccountLockTime(LocalDateTime.now(), email);

		} else {
			expireOTPAndResetAttempt(null, 0, email);
			System.out.println("account is expired.");

			if (Duration.between(accountLockTime, currentTime).getSeconds() > (1 * 60)) {
				unlockVendorAccountTimeExpired(email);
				System.out.println("account will unlocked after 1 minute.");
			}
			return false;
		}
		return false;
	}

	@Override
	public void updateVendorFailedAttemptCount(int failedAttempt, String email) {
		this.repository.updateFailedAttemptCount(failedAttempt + 1, email);
		System.out.println("failed count updated: " + failedAttempt);

	}

	@Override
	public boolean unlockVendorAccountTimeExpired(String email) {
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
	public void vendorAccountLockTime(LocalDateTime accountLocakTime, String email) {
		this.repository.updateAccountLockTime(accountLocakTime, email);
		System.out.println("account lock time updated.");

	}

	@Override
	public void expireOTPAndResetAttempt(Integer OTP, int resetAttempt, String email) {
		this.repository.expireOTPAndAttempt(OTP, resetAttempt, email);

	}

}
