package com.vmanage.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vmanage.email.EmailSender;
import com.vmanage.entities.VendorEntity;
import com.vmanage.otp.OtpGenerator;
import com.vmanage.service.VendorService;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	private EmailSender emailSender;

	@Autowired
	private OtpGenerator otpGenerator;

	@Autowired
	private VendorService service;

	@Override
	public void sendOtp(String email) {

		VendorEntity byEmail = this.service.findByEmail(email);
		System.out.println("byEmail: " + byEmail);
		if (byEmail.getVendorEmail().equalsIgnoreCase(email)) {
			String otp = this.otpGenerator.generateOtp();
			System.out.println("OTP: "+otp);
			
			boolean emailSender2 = this.emailSender.emailSender(email, "santosha7022@outlook.com", "One Time Password", otp);
			
			if (emailSender2) {
				 this.service.updateOtpByEmail(otp, email);
				System.out.println("otp updated.");
			} else {
				System.err.println("otp not updated.");
			}
		}
	}
}
