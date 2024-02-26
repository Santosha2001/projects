package com.vmanage.login.service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vmanage.email.EmailSender;
import com.vmanage.entities.VendorEntity;
import com.vmanage.otp.OtpGenerator;
import com.vmanage.repository.VendorRepository;

@Service
public class VendorLogInService {

	@Autowired
	private OtpGenerator otpGenerator;
	
	@Autowired
	private EmailSender emailSender;
	
	@Autowired
	private VendorRepository repository;
	
	public VendorLogInService() {
		System.out.println("VendorLogInService created.");
	}
	
	
	public String register(String email) {
		
		String otp = this.otpGenerator.generateOtp();
		String from="santosha7022@outlook.com";
		String subject="One Time Password";
		String to=email;
		String text=otp;
		
		
		
		
		List<VendorEntity> list = this.repository.findAll();
		
		for (VendorEntity vendorEntity : list) {
			if (vendorEntity.getVendorEmail().equalsIgnoreCase(email)) {
				this.emailSender.emailSender(to, from, subject, text);
				if (vendorEntity.getOtp().equals(to) && 
						Duration.between(vendorEntity.getOtpGenratedTime(), LocalDateTime.now())
						.getSeconds()<5*60) {
					
				} else {

				}
			} else {

			}
		}
		
		
		
		
		return null;
	}
}
