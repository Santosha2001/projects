package com.vmanager.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class VendorDTO {

	private int id;

	@NotNull(message = "Name can't be empty")
	@Size(min = 3, max = 20, message = "Name should be in specified range.")
	private String vendorNname;

	@NotNull(message = "Location can't be empty")
	@Size(min = 3, max = 20, message = "Location should be in specified range.")
	private String vendorLocation;

	@NotNull(message = "GST can't be empty")
	@Size(min = 15, max = 15, message = "GST should 15 characters.")
	private String vendorGSTNumber;

	@NotNull(message = "Please provide date.")
	private String companyStartDate;

	@NotNull(message = "Owner name can't be empty")
	@Size(min = 3, max = 20, message = "Owner name should be in specified range.")
	private String ownerName;

	@NotNull(message = "Service Type can't be empty")
	private String serviceType;

	@NotNull(message = "Number can't be empty.")
	@Digits(integer = 10, fraction = 0)
	private Long contactNumber;

	@NotNull
	@Digits(integer = 10, fraction = 0)
	private Long alternateContactNumber;

	@NotEmpty(message = "Email can't be empty.")
	@Email
	private String vendorEmail;

	@NotNull(message = "Website Type can't be empty")
	@Size(min = 3, max = 20, message = "Website should be in specified range.")
	private String website;

	private String createdBy;

	private LocalDate createdDate;

	private String updatedBy;

	private LocalDate updatedDate;

	private Integer otp;

	private LocalDateTime otpGenratedTime;

	private int failedAttempt;

	private boolean accountNonLocked;

	private LocalDateTime accountLockTime;

	private String applyStatus;
	
	private String imageName;

}
