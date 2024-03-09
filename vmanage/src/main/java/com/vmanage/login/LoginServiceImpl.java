package com.vmanage.login;

import java.time.Duration;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vmanage.entities.VendorEntity;
import com.vmanage.repository.VendorRepository;
import com.vmanage.service.VendorService;
import com.vmanage.util.EmailSender;
import com.vmanage.util.OtpGenerator;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	private EmailSender emailSender;

	@Autowired
	private OtpGenerator otpGenerator;

	@Autowired
	private VendorService service;

	@Autowired
	private VendorRepository repository;

	/* SEND OTP TO MAIL */
	@Override
	public void sendOtp(String email) {

		VendorEntity byEmail = this.service.findByEmail(email);

		if (byEmail.getVendorEmail().equalsIgnoreCase(email)) {

			Integer otp = this.otpGenerator.generateOtp();
			System.out.println("OTP: " + otp);

			boolean emailSender2 = this.emailSender.emailSender(email, "santosha5856@gmail.com", "One Time Password",
					"Your OTP for login is " + otp + ". Don't share with anyone.");

			this.service.updateOtpGeneratedTime(LocalDateTime.now(), email);

			if (emailSender2) {
				this.service.updateOtpByEmail(otp, email);

				System.out.println("otp sent to mail.");
			} else {
				System.err.println("otp not sent.");
			}
		}
	}

	/* VERIFY OTP */
	@Override
	public VendorEntity verifyOtp(Integer otp, String email) {

		VendorEntity byEmail = this.repository.findByEmail(email);
		
		LocalDateTime otpGenratedTime = byEmail.getOtpGenratedTime();
		LocalDateTime currentTime = LocalDateTime.now();	
		LocalDateTime accountLockTime = byEmail.getAccountLockTime();
		
		System.out.println("EMAIL: " + email);
		System.out.println("OTP: " + otp);

		if (byEmail != null && !"".equals(byEmail.getVendorEmail())
				&& byEmail.getVendorEmail().equalsIgnoreCase(email)) {
			
			if (byEmail.getOtp() != null && byEmail.getOtp().equals(otp)
					&& Duration.between(otpGenratedTime, currentTime).getSeconds() < (5 * 60)) {
				System.out.println("otp valid.");
				
				// resetAttemptCount(email);
			} else if(byEmail.getOtp() != null && byEmail.getOtp().equals(otp)
					&& Duration.between(otpGenratedTime, currentTime).getSeconds()>(5*60)) {
				System.out.println("otp expired.");
				expireOTPAndResetAttempt(null, 0, email);
				
			} else if(!byEmail.getOtp().equals(otp)) {
				
				if (byEmail.getFailedAttempt()<ATTEMPT_TIME-1) {
					updateFailedAttemptCount(byEmail.getFailedAttempt(), email);
					
				} else if(byEmail.getFailedAttempt()==ATTEMPT_TIME-1) {
					updateFailedAttemptCount(byEmail.getFailedAttempt(), email);
					accountLockTime(LocalDateTime.now(), email);
					
				} else {
					expireOTPAndResetAttempt(null, 0, email);
					System.out.println("account is expired.");
	
					if (Duration.between(accountLockTime, currentTime).getSeconds()>(1*60)) {
						unlockAccountTimeExpired(email);
						System.out.println("account will unlocked after 1 minute.");
					} 
				}
			} 	

		}

		return byEmail;
	}

	/* INCREASE FAILED ATTEMPT COUNT */
	@Override
	public void updateFailedAttemptCount(int failedAttempt, String email) {
		this.repository.updateFailedAttemptCount(failedAttempt + 1, email);
		System.out.println("failed count updated: "+failedAttempt);
	}

	/* LOCK THE USER IF CROSS MAXIMUN FAILED ATTEMPT */
	@Override
	public void lockAccount(String email) {
		
		VendorEntity byEmail = this.repository.findByEmail(email);
		byEmail.setAccountNonLocked(false);
		//byEmail.setAccountLockTime(LocalDateTime.now());
		
		accountLockTime(LocalDateTime.now(), email);
		
//		System.out.println("setAccountLockTime(): "+byEmail.getAccountLockTime());
		
//		this.repository.save(byEmail);		
	}

	/* RESET ATTEMPT */
	/*
	@Override
	public void resetAttemptCount(String email) {
		
		VendorEntity byEmail = this.repository.findByEmail(email);
		
		byEmail.setFailedAttempt(0);
		byEmail.setAccountLockTime(null);
		System.out.println("Failed attempt count reseted.");
		
//		this.repository.save(byEmail);
				
	}
	*/
	
	public static final long ATTEMPT_TIME =3;

	/* UNLOCK ACCOUNT TIME EXPERIED */
	@Override
	public boolean unlockAccountTimeExpired(String email) {
		VendorEntity byEmail = this.repository.findByEmail(email);
		
	 LocalDateTime accountLockTime = byEmail.getAccountLockTime();
	 
//		long currentTime = System.currentTimeMillis();
	 
	 LocalDateTime currentTime=LocalDateTime.now();

		if (Duration.between(accountLockTime, currentTime).getSeconds()>(1*60)) {
			byEmail.setAccountNonLocked(true);
			byEmail.setAccountLockTime(null);
			byEmail.setFailedAttempt(0);
			
			// resetAttemptCount(email);
			
//			this.repository.save(byEmail);		

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
	public void expireOTPAndResetAttempt(Integer OTP,int resetAttempt, String email) {
		
		this.repository.expireOTPAndAttempt(OTP, resetAttempt, email);
		
	}
	

	
	

	
	

	
	

	
	
	
	
	

}
