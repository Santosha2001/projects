package com.vmanager.service.vendor;

public interface VendorAjaxService {

	String getVendorGST(String gst);

	String getVendorMobile(Long mobile);

	String getVendorEmail(String email);

	String getVendorWebsite(String website);

	String getVendorLoginEmail(String email);

}
