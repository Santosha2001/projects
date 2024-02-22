package com.vmanage.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vmanage.email.EmailSender;
import com.vmanage.entities.VendorEntity;
import com.vmanage.repository.VendorRepository;

@Service
public class VendorServiceImpl implements VendorService {

	@Autowired
	private VendorRepository repository;

	@Autowired
	private EmailSender emailSender;

	public VendorServiceImpl() {
		System.out.println("VendorServiceImpl created.");
	}

	@Override
	public VendorEntity save(VendorEntity entity) {
		entity.setCreatedBy(entity.getOwnerName());
		entity.setCreatedDate(LocalDate.now());

		this.repository.saveRepo(entity);
		return entity;
	}

	@Override
	public String isExistByGstOrNumberOrMailOrSite(String gst, Long number, String email, String website) {

		VendorEntity entity = repository.isExistByGstOrNumberOrMailOrSite(gst, number, email, website);

		if (entity != null) {
			if (entity.getVendorGSTNumber().equals(gst)) {
				return "GST already exist.";
			} else if (entity.getContactNumber().equals(number)) {
				return "Number already exist.";
			} else if (entity.getVendorEmail().equals(email)) {
				return "Email already exist";
			} else if (entity.getWebsite().equals(website)) {
				return "Website already exist";
			}

			System.out.println("Details not present save it.");
		}

		return null;
	}

	@Override
	public boolean sendEmail(String email, String username) {
		System.out.println("Sending email.");

		String subject = "Welcome, " + username + " Your account created.";
		String text = "Congragulations, your account created. Login for more information.";
		String from = "santosha7022@outlook.com";
		String to = email;

		boolean sender = this.emailSender.emailSender(to, from, subject, text);
		if (sender) {
			System.out.println("Email sent.");
			return true;
		}
		return false;
	}

	boolean exist = false;

	@Override
	public String findByMailAjax(String email) {
		List<VendorEntity> byEmail = this.repository.findAll();
		for (VendorEntity vendorEntity : byEmail) {
			System.out.println(vendorEntity.getVendorEmail() + " mail from ajax request--> " + email);
//			if (!vendorEntity.getVendorEmail().equalsIgnoreCase(email)) {
//				System.out.println("email does not exist, you use it.");
//			} else {
//				return "email already exist";
//			}
			if (vendorEntity.getVendorEmail().equalsIgnoreCase(email)) {
				exist = true;
				System.out.println("EMAIL--> " + exist);
				return "mail already exist.";
			} else {
				System.out.println("mail not exist");
			}
		}
		return null;
	}

	@Override
	public String findByGstAjax(String gst) {

		List<VendorEntity> list = this.repository.findAll();

		for (VendorEntity vendorEntity : list) {
			System.out.println(vendorEntity.getVendorGSTNumber() + " " + gst);
			if (vendorEntity.getVendorGSTNumber().equalsIgnoreCase(gst)) {
				return "GST already exist";
			} else {
				System.out.println("gst not found.");
			}
		}
		return null;
	}

	@Override
	public String findByMobileAjax(Long mobile) {

		List<VendorEntity> list = this.repository.findAll();

		for (VendorEntity vendorEntity : list) {
			System.out.println(vendorEntity.getContactNumber() + " " + mobile);
			if (vendorEntity.getContactNumber().equals(mobile)) {
				return "Contact number exist";
			} else {
				System.out.println("contact not found.");
			}
		}
		return null;
	}

}
