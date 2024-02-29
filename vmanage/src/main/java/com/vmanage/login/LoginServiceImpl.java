package com.vmanage.login;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vmanage.email.EmailSender;
import com.vmanage.entities.VendorEntity;
import com.vmanage.otp.OtpGenerator;
import com.vmanage.repository.VendorRepository;
import com.vmanage.service.VendorService;

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
	
	@Override
	public void sendOtp(String email) {

		VendorEntity byEmail = this.service.findByEmail(email);
		System.out.println("byEmail: " + byEmail);
		if (byEmail.getVendorEmail().equalsIgnoreCase(email)) {
			String otp = this.otpGenerator.generateOtp();
			System.out.println("OTP: "+otp);
			
			// this.service.updateOtpByEmail(otp, email);

			boolean emailSender2 = this.emailSender.emailSender(email, "santosha7022@outlook.com", "One Time Password", otp);
			
			if (emailSender2) {
				 this.service.updateOtpByEmail(otp, email);
				System.out.println("sent to mail.");
			} else {
				System.err.println("otp not sent.");
			}
		}

	}


	@Override
	public String verifyOtp(String otp) {
		
		List<VendorEntity> all = this.repository.findAll();
		
		for (VendorEntity vendorEntity : all) {
			if (vendorEntity.getOtp().equals(otp)) {
				System.out.println("OTP MATCH.");
				return "";
			}
			else {
				System.out.println("OTP NOT MATCH");
				return "Otp not match";
			}
		}
		
		return null;
	}

}
