package com.vmanager.service.vendor;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.vmanager.dto.VendorDTO;
import com.vmanager.entities.VendorEntity;

public interface VendorService {

	VendorDTO saveVendor(VendorDTO dto);

	String isExistByGstOrNumberOrMailOrSite(String gst, Long number, String email, String website);

	boolean sendEmailToVendor(String email, String username);

	List<VendorEntity> findAllVendors();

	VendorDTO findByEmail(String email);

	void updateOtpByEmail(Integer otp, String email);

	void updateOtpGeneratedTime(LocalDateTime otpGeneratedTime, String email);

	boolean approveVendorStatusByEmail(String email);

	// String vendorName,
	// boolean updateVendorByEmail(String location, String ownerName, Long contact,
	// String email);
	boolean detailsUpdated(String vendorName, String location, String ownerName, Long contact, String email, int id);

	boolean deleteVendorByEmail(String email);

	boolean isVendorImageUpdatedByEmail(MultipartFile file,int id);

}