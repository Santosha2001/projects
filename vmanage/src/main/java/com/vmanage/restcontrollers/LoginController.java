package com.vmanage.restcontrollers;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.vmanage.entities.AdminEntity;
import com.vmanage.entities.VendorEntity;
import com.vmanage.login.LoginService;
import com.vmanage.login.LoginServiceImpl;
import com.vmanage.repository.VendorRepository;
import com.vmanage.service.VendorService;

@Controller
@RequestMapping("/")
public class LoginController {

	@Autowired
	private LoginService loginService;

	@Autowired
	private VendorRepository repository;
	
	@Autowired
	private VendorService service;

	public LoginController() {
		System.out.println("LoginController created.");
	}

	/* SENDING OTP TO EMAIL */
	@PostMapping("/sendOTP")
	public String loginUsingOtp(@RequestParam String vendorEmail, Model model) {

		vendorEmail = vendorEmail.replace(",", "");
		System.out.println("sendung otp  to email : " + vendorEmail);

		boolean sendOtp = this.loginService.sendOtp(vendorEmail);
		if (sendOtp) {
			model.addAttribute("mail", vendorEmail);
			return "loginSuccess";
		}

		return "login";
	}

	/* VERIFY OTP */
	@PostMapping(value = "/otpVerify")
	public String verifyOtp( @RequestParam String vendorEmail,@RequestParam Integer otp, Model model) {

		
		System.out.println(otp+" "+vendorEmail);
		boolean verifyOtp = loginService.verifyOtp(otp, vendorEmail);
		if (verifyOtp) {
			model.addAttribute("otpMatched", "LOGIN SUCCESS.");
			return "userView";
		} else {
			// model.addAttribute("wrongOTP", "*Wrong OTP");
			model.addAttribute("otpExpired", "OTP EXPIRED. PLEASE REGENERATE OTP.");
			return "loginSuccess";
		}

	}

	/* ADMIN LOGIN */
	@PostMapping(value = "/adminLogin")
	public String adminLogin(@RequestParam String adminName, @RequestParam String adminPassword, Model model) {
		System.out.println("adminName : " + adminName);
		System.out.println("adminPassword : " + adminPassword);

		boolean byNameAndPassword = loginService.findAdminByNameAndPassword(adminName, adminPassword);
		if (byNameAndPassword) {

			List<VendorEntity> vendorList = this.repository.findAll();
			model.addAttribute("vendorsList", vendorList);
			vendorList.forEach(System.out::println);

			return "AdminLoginSuccess";
		}

		System.out.println("Admin login failed.");
		model.addAttribute("adminLoginFail", "Admin Login Failed.");
		return "AdminLogin";
	}
	
	@GetMapping("/approve")
	public String verify(@RequestParam String email, Model model) {
		
		
		System.out.println(email);
		
		VendorEntity vendorEntity = service.findByEmail(email);
		
		model.addAttribute("vendorEntity", vendorEntity);
		
		return "StatusApproved";
	}

}
