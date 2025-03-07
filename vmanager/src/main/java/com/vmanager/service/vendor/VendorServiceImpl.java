package com.vmanager.service.vendor;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import com.vmanager.dto.VendorDTO;
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

	/* SAVE DTO */
	@Override
	public VendorDTO saveVendor(VendorDTO dto) {

		dto.setCreatedBy(dto.getVendorNname());
		dto.setCreatedDate(LocalDate.now());
		dto.setApplyStatus("PENDING");

		VendorEntity vendorEntity = new VendorEntity();

		// converting dto object to entity object
		BeanUtils.copyProperties(dto, vendorEntity);

		this.repository.saveVendor(vendorEntity);
		return dto;
	}

	/* ENTITY EXISTENCE CHECK */
	@Override
	public String isExistByGstOrNumberOrMailOrSite(String gst, Long number, String email, String website) {

		VendorDTO dto = new VendorDTO();
		VendorEntity entity = repository.isExistByGstOrNumberOrMailOrSite(gst, number, email, website);

		if (ObjectUtils.isEmpty(entity)) {
			return null;
		} else {
			System.out.println(entity);
			BeanUtils.copyProperties(entity, dto);

			if (dto.getVendorGSTNumber().equals(gst)) {
				return "GST already exist.";
			} else if (dto.getContactNumber().equals(number)) {
				return "Number already exist.";
			} else if (dto.getVendorEmail().equals(email)) {
				return "Email already exist";
			} else if (dto.getWebsite().equals(website)) {
				return "Website already exist";
			}
			System.out.println("Details not present save.");
		}
		return null;
	}

	/* SAEND MAIL */
	@Override
	public boolean sendEmailToVendor(String email, String username) {

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
	public VendorDTO findByEmail(String email) {

		VendorDTO dto = new VendorDTO();
		VendorEntity byEmail = this.repository.findByEmail(email);

		System.out.println("byMail: " + byEmail);

		if (byEmail == null) {
			return null;
		} else if (byEmail != null && !byEmail.getVendorEmail().isEmpty()) {
			BeanUtils.copyProperties(byEmail, dto);
			return dto;
		}
		System.out.println(dto);

		return dto;
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

	/* APPROVE VENDOR STATUS BY ADMIN */
	@Override
	public boolean approveVendorStatusByEmail(String email) {

		repository.updateVendorStatusByEmail("APPROVED", email);
		return true;
	}

	/* UPDATED DETAILS */
	/*
	@Override
	public boolean updateVendorByEmail(String location, String ownerName, Long contact,
			String email) {
//String vendorName, 
		VendorDTO dto = new VendorDTO();
		VendorEntity vendorEntity = this.repository.findByEmail(email);

		System.out.println("entity in detailsUpdated: " + vendorEntity);

		if (ObjectUtils.isEmpty(vendorEntity)) {
			return false;
		} else if (!ObjectUtils.isEmpty(vendorEntity.getVendorEmail())
				&& vendorEntity.getVendorEmail().equalsIgnoreCase(email)
				&& vendorEntity.getApplyStatus().equalsIgnoreCase("APPROVED")) {

			BeanUtils.copyProperties(vendorEntity, dto);
			System.out.println("dto in detailsUpdated: " + dto);

			//repository.updateVendorDetails(dto.getVendorNname(), dto.getVendorLocation(), dto.getOwnerName(), dto.getContactNumber(), dto.getVendorEmail());
repository.updateVendorDetails( location, ownerName, contact, email);
repository.updateUpdatedDateAndUpdatedBy(ownerName, LocalDate.now(), email);
			//this.repository.updateUpdatedDateAndUpdatedBy(dto.getVendorNname(), LocalDate.now(), dto.getVendorEmail());

			System.out.println("EMAIL " + email + " details updated.");

			return true;
		}

		return false;
	}
	*/
	
	@Override
	public boolean detailsUpdated(String vendorName, String location, String ownerName, Long contact, String email,
			int id) {

		VendorEntity vendorEntity = this.repository.findById(id);

		if (!ObjectUtils.isEmpty(vendorEntity.getId()) && vendorEntity.getId() == id
				&& vendorEntity.getApplyStatus().equalsIgnoreCase("APPROVED")) {

			repository.updateDetails(vendorName, location, ownerName, contact, email, id);

			this.repository.updateUpdatedDateAndUpdatedBy(vendorName, LocalDate.now(), id);

			System.out.println("ID " + id + " details updated.");

			return true;
		}

		return false;
	}

	/* DELETE VENDOR */
	@Override
	public boolean deleteVendorByEmail(String email) {

		VendorEntity entity = this.repository.findByEmail(email);

		if (entity.getVendorEmail().equalsIgnoreCase(email)) {
			this.repository.deleteVendorAccount(email);
			System.out.println("Account deleted.");

			return true;
		}

		return false;
	}

	@Override
	public List<VendorEntity> findAllVendors() {

		List<VendorEntity> entities = new ArrayList<VendorEntity>();
		entities.forEach(a -> System.out.println(a));

		return entities;
	}

	@Override
	public boolean isVendorImageUpdatedByEmail(MultipartFile file, int id) {

		VendorEntity vendorEntity = repository.findById(id);

		String imageName = file.isEmpty() ? "default.jpg" : file.getOriginalFilename();
		vendorEntity.setImageName(imageName);

		if (!ObjectUtils.isEmpty(id)) {

			try {
				new ClassPathResource("C:\\Users\\HP\\Desktop\\image upload").getFile();
			} catch (Exception e) {
				System.out.println("file upload exception: " + e.getMessage());
			}

			Path path = Paths.get("C:\\Users\\HP\\Desktop\\image upload\\" + file.getOriginalFilename());

			System.out.println("File Path: " + path);

			try {
				Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
			} catch (IOException e) {
				e.printStackTrace();
			}

			this.repository.updateVendorImage(imageName, id);
			return true;

		}

		return false;
	}

}
