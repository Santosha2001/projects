package com.vmanage.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.vmanage.entities.VendorEntity;
import com.vmanage.login.LoginService;

@Controller
@RequestMapping("/otp")
public class LoginController {

	@Autowired
	private LoginService loginService;

	public LoginController() {
		System.out.println("LoginController created.");
	}

	/* SENDING OTP TO EMAIL */
	@PostMapping("/sendOTP")
	public String loginUsingOtp(@RequestParam String vendorEmail, Model model) {
		System.out.println("sendung otp  to email : " + vendorEmail);

		if (vendorEmail != null && !vendorEmail.isEmpty()) {
			this.loginService.sendOtp(vendorEmail);
			model.addAttribute("mail", vendorEmail);

			return "loginSuccess";
		}
		
		return "login";
	}

	/* VERIFY OTP */
	@PostMapping(value = "/otpVerify")
	public String verifyOtp(@RequestParam Integer otp, String email, Model model) {

		
		Integer verifyOtp = this.loginService.verifyOtp(otp, email);
		if (email != null && verifyOtp!=null && verifyOtp.equals(otp)) {
			System.out.println("OTP MATCHED.");
			model.addAttribute("otpMatched", "Login Success.");
			
			return "loginSuccess";
		} 
		
		System.out.println("OTP NOT MATCHED.");
		model.addAttribute("otpNotMatched", "Login Failed.");
		
		return "login";
		
		
		/*
		Integer verofyOTP = this.loginService.verofyOTP(otp, email);
		
		if (verofyOTP!=null && !"".equals(verofyOTP) && verofyOTP.equals(otp)) {
			System.out.println("OTP MATCH");
			
			model.addAttribute("otpMatched", "Login Success.");
			
			return "loginSuccess";
		}
		
		System.out.println("OTP NOT MATCHED.");
		model.addAttribute("otpNotMatched", "Login Failed.");
		
		return "loginSuccess";
		*/
	}
}
