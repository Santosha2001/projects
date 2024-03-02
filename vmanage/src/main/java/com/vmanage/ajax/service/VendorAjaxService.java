package com.vmanage.ajax.service;

public interface VendorAjaxService {

	String findByGstAjax(String gst);

	String findByMobileAjax(Long mobile);

	String findByEmail(String email);

	String findByWebsiteAjax(String website);

	String emailLogInAjax(String email);

	//String otpAjax(int otp);
}
