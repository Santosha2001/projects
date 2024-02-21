package com.vmanage.service;

import com.vmanage.entities.VendorEntity;

public interface VendorService {

	VendorEntity save(VendorEntity entity);

	String isExistByGstOrNumberOrMailOrSite(String gst, Long number, String email, String website);

	boolean sendEmail(String email, String username);

	String fetchMails(String email);
}