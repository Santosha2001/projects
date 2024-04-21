package com.vmanager.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.vmanager.service.vendor.VendorAjaxService;

@RestController
public class VendorRestController {

	@Autowired
	@Qualifier(value = "ajaxService")
	private VendorAjaxService ajaxService;

	public VendorRestController() {
		System.out.println("VendorAjaxRestController created.");
	}

	/* GST AJAX */
	@GetMapping("/gstAjax/{gst}")
	public String gstAjax(@PathVariable String gst) {
		String byGstAjax = this.ajaxService.getVendorGST(gst);
		System.out.println("gst ajax: " + gst);
		return byGstAjax;
	}

	/* MOBILE NUMBER AJAX */
	@GetMapping(value = "/mobileAjax/{mobile}")
	public String mobileAjax(@PathVariable Long mobile) {
		String byMobileAjax = this.ajaxService.getVendorMobile(mobile);
		System.out.println("number ajax: " + mobile);
		return byMobileAjax;
	}

	/* EMAIL AJAX */
	@GetMapping(value = "/emailAjax/{email:.+}")
	public String emailAjax(@PathVariable String email) {
		String byEmail = this.ajaxService.getVendorEmail(email);
		System.out.println("ajax: " + email);
		return byEmail;
	}

	/* WEBSITE AJAX */
	@GetMapping(value = "/siteAjax/{website:.+}")
	public String websiteAjax(@PathVariable String website) {
		String byWebsiteAjax = this.ajaxService.getVendorWebsite(website);
		System.out.println("website ajax: " + website);
		return byWebsiteAjax;
	}

	/* EMAIL AJAX FOR LOGIN */
	@GetMapping(value = "/mailLogInAjax/{email:..+}")
	public String emailAjaxForLogIn(@PathVariable String email) {
		String emailLogInAjax = this.ajaxService.getVendorLoginEmail(email);
		System.out.println("mail login ajax: " + email);
		return emailLogInAjax;
	}
}
