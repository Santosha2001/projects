package com.vmanage.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.vmanage.service.VendorService;

@EnableWebMvc
@RestController
@RequestMapping("/")
public class VendorAjaxRestController {

	@Autowired
	private VendorService service;

	public VendorAjaxRestController() {
		System.out.println("VendorAjaxRestController created.");
	}

	/*
	 * @GetMapping(value = "/mailAjax/{email}") public String mailAjax(@PathVariable
	 * String email) {
	 * 
	 * String byEmail = this.service.findByMailAjax(email);
	 * System.out.println("Runnig ajax controller for email: " + email);
	 * 
	 * return byEmail; }
	 */

	@GetMapping(value = "/gstAjax/{gst}")
	public String gstAjax(@PathVariable String gst) {
		String byGstAjax = this.service.findByGstAjax(gst);
		System.out.println("Running gst ajax: " + gst);
		return byGstAjax;
	}
	
	@GetMapping(value = "/mobileAjax/{mobile}")
	public String mobileAjax(@PathVariable Long mobile) {
		String byMobileAjax = this.service.findByMobileAjax(mobile);
		System.out.println("Contact number ajax: "+mobile);
		return byMobileAjax;
	}
}
