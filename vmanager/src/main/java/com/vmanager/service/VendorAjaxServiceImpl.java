package com.vmanager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vmanager.entities.VendorEntity;
import com.vmanager.repository.VendorRepository;

@Service(value = "ajaxService")
public class VendorAjaxServiceImpl implements VendorAjaxService {

	@Autowired
	private VendorRepository repository;

	public VendorAjaxServiceImpl() {
		System.out.println("VendorAjaxServiceImpl created.");
	}

	/* GST AJAX VALIDATON */
	@Override
	public String findByGstAjax(String gst) {

		List<VendorEntity> list = this.repository.findAll();
		for (VendorEntity vendorEntity : list) {
			System.out.println(vendorEntity.getVendorGSTNumber() + "  " + gst);
			if (vendorEntity.getVendorGSTNumber().equalsIgnoreCase(gst)) {
				System.out.println(("equal."));

				return "*gst already exist";
			} else {
				System.out.println("gst not found.");
			}
		}
		return null;
	}

	/* MOBILE NUMBER AJAX VALIDATIOIN */
	@Override
	public String findByMobileAjax(Long mobile) {

		List<VendorEntity> list = this.repository.findAll();

		for (VendorEntity vendorEntity : list) {
			System.out.println(vendorEntity.getContactNumber() + "  " + mobile);
			if (vendorEntity.getContactNumber().equals(mobile)) {
				System.out.println(("equal."));

				return "*number already exist";
			} else {
				System.out.println("contact not found.");
			}
		}
		return null;
	}

	/* EMAIL AJAX VALIDATION */
	@Override
	public String findByEmail(String email) {
		List<VendorEntity> list = this.repository.findAll();
		for (VendorEntity vendorEntity : list) {
			System.out.println(vendorEntity.getVendorEmail() + "  " + email);

			if (vendorEntity.getVendorEmail().equalsIgnoreCase(email)) {
				System.out.println(("equal."));

				return "*email already exist.";
			} else {
				System.out.println("Email not exist.");
			}
		}
		return null;
	}

	/* WEBSITE AJAX VALIDATION */
	@Override
	public String findByWebsiteAjax(String website) {
		List<VendorEntity> list = this.repository.findAll();
		for (VendorEntity vendorEntity : list) {
			System.out.println(vendorEntity.getWebsite() + "  " + website);

			if (vendorEntity.getWebsite().equalsIgnoreCase(website)) {
				System.out.println(("equal."));
				return "*website already exist.";
			} else {
				System.out.println("Website not exist.");
			}
		}
		return null;
	}

	/* EMAIL AJAX VALIDATION WHILE LOGIN */
	@Override
	public String emailLogInAjax(String email) {
		List<VendorEntity> list = this.repository.findAll();
		for (VendorEntity vendorEntity : list) {
			if (vendorEntity.getVendorEmail().equalsIgnoreCase(email)) {
				return "";
			} else {
				return "*email not found.";
			}
		}
		return null;
	}

}
