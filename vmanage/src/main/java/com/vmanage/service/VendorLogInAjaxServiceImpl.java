package com.vmanage.service;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vmanage.email.EmailSender;
import com.vmanage.entities.VendorEntity;
import com.vmanage.repository.VendorRepository;

@Service
public class VendorLogInAjaxServiceImpl implements VendorLogInAjaxService {

	@Autowired
	private VendorRepository repository;

	@Autowired
	private EmailSender emailSender;

//	private int min = 100000;
//	private int max = 999999;
//	Random random = new Random();
//	private int otp;

	public VendorLogInAjaxServiceImpl() {
		System.out.println("VendorLogInAjaxService created.");
	}

	// EMAIL AJAX FOR LOG IN.
	@Override
	public String emailLogInAjax(String email) {
		List<VendorEntity> list = this.repository.findAll();
		for (VendorEntity vendorEntity : list) {
			if (vendorEntity.getVendorEmail().equalsIgnoreCase(email)) {
				return "";
			} else {
				return "Email not registered.";
			}
		}
		return null;
	}
	/*
	@Override
	public String logInUsingMailAndOtp(String email, int otp) {
		System.out.println("sending otp.");

		String subject = "One Time Password.";
		String text = "Your OTP for login.";
		String from = "santosha7022@outlook.com";
		String to = email;

		this.emailSender.emailSender(to, from, subject, text);
		return null;
	}
	*/
	
	/*
	@Override
	public boolean updateOtpByMail(String email) {

		List<VendorEntity> list = this.repository.findAll();
		for (VendorEntity vendorEntity : list) {
			if (vendorEntity != null && vendorEntity.getVendorEmail().equalsIgnoreCase(email)) {
				otp = random.nextInt(max - min) + min;
				this.repository.updateOtpByEmail(otp, email);
				if (vendorEntity.getOtp() == this.otp) {
					System.out.println("OTP equals");
					return true;
				}
			}
		}
		return false;
	}
	*/

}
