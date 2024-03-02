package com.vmanage.resource;

import javax.validation.Valid;

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
	public String verifyOtp(@RequestParam Integer otp, @RequestParam String email, Model model) {

		Integer otp2 = this.loginService.findOtp(otp, email);

		System.out.println("EMAIL: " + email);
		System.out.println("OTP: " + otp);

		if (email != null) {
			if (otp2 != null && !"".equals(otp2) && otp2.equals(otp)) {
				System.out.println("OTP MATCHED.");
				model.addAttribute("otpMatched", "Login Success.");

				return "loginSuccess";
			}
		} else {
			System.out.println("OTP NOT MATCHED.");
			model.addAttribute("otpNotMatched", "Login Failed.");
		}

//		Integer verifyOtp = this.loginService.verifyOtp(otp);
//		if ( verifyOtp!=null && verifyOtp.equals(otp)) {
//			System.out.println("OTP MATCHED.");
//			model.addAttribute("otpMatched", "Login Success.");
//			
//			return "loginSuccess";
//		} 
//		
//		System.out.println("OTP NOT MATCHED.");
//		model.addAttribute("otpNotMatched", "Login Failed.");

		return "login";

	}

}
