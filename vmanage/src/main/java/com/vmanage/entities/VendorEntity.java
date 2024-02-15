package com.vmanage.entities;

import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class VendorEntity {

	@NotNull(message = "Name can't be empty")
	@Size(min = 3, max = 20, message = "Name should be in specified range.")
	private String vendorNname;

	@NotNull(message = "Location can't be empty")
	@Size(min = 3, max = 20, message = "Location should be in specified range.")
	private String vendorLocation;

	@NotNull(message = "GST can't be empty")
	@Size(min = 3, max = 20, message = "GST should be in specified range.")
	private String vendorGSTNumber;

	@NotNull
	private String companyStartDate;

	@NotNull(message = "Owner name can't be empty")
	@Size(min = 3, max = 20, message = "Owner name should be in specified range.")
	private String ownerName;

	@NotNull(message = "Service Type can't be empty")
	@Size(min = 3, max = 20, message = "Service Type should be in specified range.")
	private String serviceType;

	@NotNull
	private Long contactNumber;

	@NotNull
	private Long alternateContactNumber;

	@NotEmpty
	@Email
	private String vendorEmail;

	@NotNull(message = "Website Type can't be empty")
	@Size(min = 3, max = 20, message = "Website should be in specified range.")
	private String website;

	private String createdBy;

	private LocalDate createdDate;

	private String updatedBy;

	private LocalDate updatedDate;
}
