package com.vmanager.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

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

@NamedQuery(name = "updatedOtpGeneratedTime", query = "UPDATE VendorEntity et SET et.otpGenratedTime=:time "
		+ "WHERE et.vendorEmail=:email")

@NamedQuery(name = "updateFailedAttemptCount", query = "UPDATE VendorEntity et SET et.failedAttempt=:failedOTP "
		+ "WHERE et.vendorEmail=:email")
@NamedQuery(name = "updateAccLockTime", query = "UPDATE VendorEntity et SET et.accountLockTime=:lockTime "
		+ "WHERE et.vendorEmail=:email")

@NamedQuery(name = "expireOTPAndResetAttempt", query = "UPDATE VendorEntity et SET et.otp=:otp, "
		+ "et.failedAttempt=:resetAttempt WHERE et.vendorEmail=:email")

@NamedQuery(name = "updateStatusByEmail", query = "UPDATE VendorEntity et SET et.applyStatus=:status "
		+ "WHERE et.vendorEmail=:email")

/*
 * @NamedQuery(name = "updateUpdatedDateAndUpdatedBy", query =
 * "UPDATE VendorEntity et SET et.updatedBy=:updatedBy, " +
 * "et.updatedDate=:updateDate WHERE et.id=:id")
 */

//et.vendorNname=:vName, 
/*
 * @NamedQuery(name = "updateDetails", query = "UPDATE VendorEntity et SET " +
 * "et.vendorLocation=:location, et.ownerName=:vOwner, et.contactNumber=:number "
 * + "WHERE et.vendorEmail=:email")
 */

@NamedQuery(name = "updateDetails", query = "UPDATE VendorEntity et SET et.vendorNname=:vName, "
		+ "et.vendorLocation=:location, et.ownerName=:vOwner, et.contactNumber=:number, "
		+ "et.vendorEmail=:email WHERE et.id=:id")

@NamedQuery(name = "findById", query = "SELECT et FROM VendorEntity et WHERE et.id=:id")

@NamedQuery(name = "updateUpdatedDateAndBy", query = "UPDATE VendorEntity et SET et.updatedBy=:by, et.updatedDate=:date WHERE et.id=:id")

@NamedQuery(name = "deleteAccount", query = "DELETE FROM VendorEntity et WHERE et.vendorEmail=:email")

@NamedQuery(name = "updateImage", query = "UPDATE VendorEntity et SET et.imageName=:image WHERE et.id=:id")

public class VendorEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "v_id")
	private int id;

	@Column(name = "v_name")
	private String vendorNname;

	@Column(name = "v_location")
	private String vendorLocation;

	@Column(name = "v_gst_number")
	private String vendorGSTNumber;

	@Column(name = "v_company_start_date")
	private String companyStartDate;

	@Column(name = "v_owner_name")
	private String ownerName;

	@Column(name = "v_service_type")
	private String serviceType;

	@Column(name = "v_contact_number")
	private Long contactNumber;

	@Column(name = "v_alternate_number")
	private Long alternateContactNumber;

	@Column(name = "v_email", unique = true)
	private String vendorEmail;

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
	private Integer otp;

	@Column(name = "otp_generated_time")
	private LocalDateTime otpGenratedTime;

	@Column(name = "failed_attempt")
	private int failedAttempt;

	@Column(name = "account_non_locked")
	private boolean accountNonLocked;

	@Column(name = "account_lock_time")
	private LocalDateTime accountLockTime;

	@Column(name = "apply_status")
	private String applyStatus;

	@Column(name = "image_name")
	private String imageName;

}
