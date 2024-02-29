package com.vmanage.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
			System.out.println("OTP SENT TO MAIL");
			model.addAttribute("mail", vendorEmail);
		}
		return "login";
	}
	
	/* VERIFY OTP */
}
