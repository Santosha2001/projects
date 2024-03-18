package com.vmanage.restcontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.vmanage.entities.VendorEntity;
import com.vmanage.login.LoginService;
import com.vmanage.service.VendorService;

@Controller
@RequestMapping("/")
public class LoginController {

	@Autowired
	private LoginService loginService;

	@Autowired
	private VendorService vendorService;

	public LoginController() {
		System.out.println("LoginController created.");
	}

	/* SENDING OTP TO EMAIL */
	@PostMapping("/sendOTP")
	public String loginUsingOtp(@RequestParam String vendorEmail, Model model) {

		System.out.println("otp sent to email : " + vendorEmail);

		boolean sendOtp = this.loginService.sendOtp(vendorEmail);
		if (sendOtp) {
			model.addAttribute("mail", vendorEmail);
			return "loginSuccess";
		}

		return "login";
	}

	/* VERIFY OTP */
	@PostMapping(value = "/otpVerify")
	public String verifyOtp(@RequestParam String vendorEmail, @RequestParam Integer otp, Model model) {

		// USER VIEW
		VendorEntity vendorEntity = vendorService.findByEmail(vendorEmail);
		model.addAttribute("vendorEntity", vendorEntity);
		System.out.println(otp + " " + vendorEmail);

		boolean verifyOtp = loginService.verifyOtp(otp, vendorEmail);

		if (verifyOtp) {
			model.addAttribute("otpMatched", "LOGIN SUCCESS.");
			return "userView";
		} else {
			if (vendorEntity.getFailedAttempt() < 3) {
				model.addAttribute("wrongOTP", "*Wrong OTP");
				return "loginSuccess";
			} else {
				model.addAttribute("otpExpired", "OTP EXPIRED. PLEASE REGENERATE OTP.");
				return "loginSuccess";
			}

		}

	}

}
