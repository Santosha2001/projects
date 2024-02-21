package com.vmanage.service;

import java.time.LocalDate;
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

	@Override
	public String fetchMails(String email) {
		VendorEntity byEmail = this.repository.findByEmail(email);

		return (byEmail == null) ? "Unique" : "Duplicate";
	}
}
