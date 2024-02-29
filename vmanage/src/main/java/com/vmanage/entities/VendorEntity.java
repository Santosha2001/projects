package com.vmanage.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "vendor")
@Data
@RequiredArgsConstructor
@NamedQuery(name = "isExist", query = "SELECT et FROM VendorEntity as et WHERE et.vendorGSTNumber=:gst OR "
		+ "et.contactNumber=:number OR et.vendorEmail=:vMail OR et.website=:vWebsite")
@NamedQuery(name = "findAll", query = "SELECT et FROM VendorEntity et")
@NamedQuery(name = "findByEmail", query = "SELECT et FROM VendorEntity et WHERE et.vendorEmail=:email")
@NamedQuery(name = "updateOtpByEmail", query = "UPDATE VendorEntity et SET et.otp=:otp WHERE "
		+ "et.vendorEmail=:email")
@NamedQuery(name = "findOtp", query = "SELECT et.otp FROM VendorEntity et")

public class VendorEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "v_id")
	private int id;

	@NotNull(message = "Name can't be empty")
	@Size(min = 3, max = 20, message = "Name should be in specified range.")
	@Column(name = "v_name")
	private String vendorNname;

	@NotNull(message = "Location can't be empty")
	@Size(min = 3, max = 20, message = "Location should be in specified range.")
	@Column(name = "v_location")
	private String vendorLocation;

	@NotNull(message = "GST can't be empty")
	@Size(min = 15, max = 15, message = "GST should 15 characters.")
	@Column(name = "v_gst_number")
	private String vendorGSTNumber;

	@NotNull
	@Column(name = "v_company_start_date")
	private String companyStartDate;

	@NotNull(message = "Owner name can't be empty")
	@Size(min = 3, max = 20, message = "Owner name should be in specified range.")
	@Column(name = "v_owner_name")
	private String ownerName;

	@NotNull(message = "Service Type can't be empty")
	@Column(name = "v_service_type")
	private String serviceType;

	@NotNull
	@Digits(integer = 10, fraction = 0)
	@Column(name = "v_contact_number")
	private Long contactNumber;

	@NotNull
	@Digits(integer = 10, fraction = 0)
	@Column(name = "v_alternate_number")
	private Long alternateContactNumber;

	@NotEmpty
	@Email
	@Column(name = "v_email")
	private String vendorEmail;

	@NotNull(message = "Website Type can't be empty")
	@Size(min = 3, max = 20, message = "Website should be in specified range.")
	@Column(name = "v_website")
	private String website;

	@Column(name = "v_created_by")
	private String createdBy;

	@Column(name = "v_created_date")
	private LocalDate createdDate;

	@Column(name = "v_updated_by")
	private String updatedBy;

	@Column(name = "v_updated_date")
	private LocalDate updatedDate;

	@Column(name = "v_otp")
	private String otp;

	@Column(name = "otp_generated_time")
	private LocalDateTime otpGenratedTime;
}
