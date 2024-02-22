package com.vmanage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vmanage.entities.VendorEntity;
import com.vmanage.repository.VendorRepository;

@Service(value = "ajaxService")
public class VendorAjaxServiceImpl implements VendorAjaxService {

	@Autowired
	private VendorRepository repository;

	public VendorAjaxServiceImpl() {
		System.out.println("VendorAjaxServiceImpl created.");
	}

	// GST USING AJAX
	@Override
	public String findByGstAjax(String gst) {

		List<VendorEntity> list = this.repository.findAll();

		for (VendorEntity vendorEntity : list) {
			System.out.println(vendorEntity.getVendorGSTNumber() + "  " + gst);
			if (vendorEntity.getVendorGSTNumber().equalsIgnoreCase(gst)) {
				System.out.println(("equal."));

				return "GST exist";
			} else {
				System.out.println("gst not found.");
			}
		}
		return null;
	}

	// MOBILE NUMBER USING AJAX
	@Override
	public String findByMobileAjax(Long mobile) {

		List<VendorEntity> list = this.repository.findAll();

		for (VendorEntity vendorEntity : list) {
			System.out.println(vendorEntity.getContactNumber() + "  " + mobile);
			if (vendorEntity.getContactNumber().equals(mobile)) {
				System.out.println(("equal."));

				return "Contact number exist";
			} else {
				System.out.println("contact not found.");
			}
		}
		return null;
	}

	// EMAIL USING AJAX
	@Override
	public String findByEmail(String email) {
		List<VendorEntity> list = this.repository.findAll();
		for (VendorEntity vendorEntity : list) {
			System.out.println(vendorEntity.getVendorEmail() + "  " + email);

			if (vendorEntity.getVendorEmail().equalsIgnoreCase(email)) {
				System.out.println(("equal."));

				return "Email exist.";
			} else {
				System.out.println("Email not exist.");
			}
		}
		return null;
	}

	// WEBSITE USING AJAX
	@Override
	public String findByWebsiteAjax(String website) {
		List<VendorEntity> list = this.repository.findAll();
		for (VendorEntity vendorEntity : list) {
			System.out.println(vendorEntity.getWebsite() + "  " + website);

			if (vendorEntity.getWebsite().equalsIgnoreCase(website)) {
				System.out.println(("equal."));
				return "Website exist.";
			} else {
				System.out.println("Website not exist.");
			}
			// return (vendorEntity.getWebsite().equalsIgnoreCase(website)) ? "website
			// already registered." : "";
		}
		return null;
	}

}
