package com.vmanage.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.vmanage.ajax.service.VendorAjaxService;
import com.vmanage.login.LoginService;

@EnableWebMvc
@RestController
@RequestMapping("/")
public class VendorAjaxRestController {

	@Autowired
	@Qualifier(value = "ajaxService")
	private VendorAjaxService ajaxService;
	
	@Autowired
	private LoginService loginService;

	public VendorAjaxRestController() {
		System.out.println("VendorAjaxRestController created.");
	}

	@GetMapping(value = "/gstAjax/{gst}")
	public String gstAjax(@PathVariable String gst) {
		String byGstAjax = this.ajaxService.findByGstAjax(gst);
		System.out.println("Running gst ajax: " + gst);
		return byGstAjax;
	}

	@GetMapping(value = "/mobileAjax/{mobile}")
	public String mobileAjax(@PathVariable Long mobile) {
		String byMobileAjax = this.ajaxService.findByMobileAjax(mobile);
		System.out.println("Contact number ajax: " + mobile);
		return byMobileAjax;
	}

	@GetMapping(value = "/emailAjax/{email:.+}")
	public String emailAjax(@PathVariable String email) {
		String byEmail = this.ajaxService.findByEmail(email);
		System.out.println("Email ajax: " + email);
		return byEmail;
	}

	@GetMapping(value = "/siteAjax/{website:.+}")
	public String websiteAjax(@PathVariable String website) {
		String byWebsiteAjax = this.ajaxService.findByWebsiteAjax(website);
		System.out.println("website ajax: " + website);
		return byWebsiteAjax;
	}

	@GetMapping(value = "/mailLogInAjax/{email:.+}")
	public String emailAjaxForLogIn(@PathVariable String email) {
		String emailLogInAjax = this.ajaxService.emailLogInAjax(email);
		System.out.println("mail login ajax: " + email);
		return emailLogInAjax;
	}
	
	//OTP VERIFY AJAX
	@GetMapping(value = "/otpVerifyAjax/{otp}")
	public String otpVerifyAjax(@PathVariable String otp) {
		
		String verifyOtp = this.loginService.verifyOtp(otp);
		System.out.println("OTP AJAX: "+ otp);
		
		return verifyOtp;
	}
}
