package com.vmanager.service;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.vmanager.entities.VendorEntity;
import com.vmanager.repository.VendorRepository;
import com.vmanager.util.EmailSender;

@Service
public class VendorServiceImpl implements VendorService {

	@Autowired
	private VendorRepository repository;

	@Autowired
	private EmailSender emailSender;

	public VendorServiceImpl() {
		System.out.println("VendorServiceImpl created.");
	}

	/* SAVE ENTITY */
	@Override
	public VendorEntity save(VendorEntity entity) {
		entity.setCreatedBy(entity.getOwnerName());
		entity.setCreatedDate(LocalDate.now());
		entity.setApplyStatus("PENDING");

		this.repository.save(entity);
		return entity;
	}

	/* ENTITY EXISTENCE CHECK */
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
			System.out.println("Details not present save.");
		}
		return null;
	}

	/* SAEND MAIL */
	@Override
	public boolean sendEmail(String email, String username) {
		System.out.println("Sending email.");

		String subject = "Welcome, " + username + " Your account created.";
		String text = "Congragulations, your account created. Login for more information.";
		String from = "santosha5856@gmail.com";
		String to = email;

		boolean sender = this.emailSender.emailSender(to, from, subject, text);
		if (sender) {
			System.out.println("Email sent.");
			return true;
		}
		return false;
	}

	/* FIND ENTITY BY EMAIL */
	@Override
	public VendorEntity findByEmail(String email) {

		VendorEntity byEmail = this.repository.findByEmail(email);
		if (byEmail != null && !byEmail.getVendorEmail().isEmpty()) {
			return byEmail;
		}
		return byEmail;
	}

	/* UPDATE OTP BY EMAIL */
	@Override
	public void updateOtpByEmail(Integer otp, String email) {

		this.repository.updateOtpByEmail(otp, email);

	}

	/* UPDATE OTP GENERATED TIME */
	@Override
	public void updateOtpGeneratedTime(LocalDateTime otpGeneratedTime, String email) {
		this.repository.updatedOtpGeneratedTime(LocalDateTime.now(), email);
	}

	@Override
	public boolean detailsUpdated(String vendorName, String location, String ownerName, Long contact, String email,
			int id) {

		VendorEntity vendorEntity = this.repository.findById(id);

		if (!ObjectUtils.isEmpty(vendorEntity.getId()) && vendorEntity.getId() == id
				&& vendorEntity.getApplyStatus().equalsIgnoreCase("APPROVED")) {

			repository.updateDetails(vendorName, location, ownerName, contact, email, id);

			this.repository.updateUpdatedDate(vendorName, LocalDate.now(), id);

			System.out.println("ID " + id + " details updated.");

			return true;
		}

		return false;
	}

	@Override
	public boolean deleteAccount(String email) {

		VendorEntity entity = this.repository.findByEmail(email);

		if (entity.getVendorEmail().equalsIgnoreCase(email)) {
			this.repository.deleteAccount(email);
			System.out.println("Account deleted.");

			return true;
		}

		return false;
	}

}
