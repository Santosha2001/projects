package com.vmanager.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.vmanager.entities.VendorEntity;
import com.vmanager.login.LoginService;
import com.vmanager.service.VendorService;

@Controller
public class VendorLoginController {

	@Autowired
	private LoginService loginService;

	@Autowired
	private VendorService vendorService;

	public VendorLoginController() {
		System.out.println("Login Controller");
	}

	/* SENDING OTP TO EMAIL */
	@PostMapping("/generateOTP")
	public String loginUsingOtp(@RequestParam String vendorEmail, Model model) {

		System.out.println("otp sent to email : " + vendorEmail);

		boolean sendOtp = this.loginService.sendOtp(vendorEmail);
		if (sendOtp) {
			model.addAttribute("mail", vendorEmail);
			return "LoginSuccess";
		}

		return "redirect:/loadLoginPage";
	}

	@GetMapping("/loadLoginSuccess")
	public String loadLoginSuccess() {

		return "LoginSuccess";
	}

	/* VERIFY OTP */
	@PostMapping(value = "/login-success")
	public String verifyOtp(@RequestParam String vendorEmail, @RequestParam Integer otp, Model model) {

		// USER VIEW
		VendorEntity vendorEntity = vendorService.findByEmail(vendorEmail);
		model.addAttribute("vendorEntity", vendorEntity);
		System.out.println(otp + " " + vendorEmail);

		boolean verifyOtp = loginService.verifyOtp(otp, vendorEmail);

		if (verifyOtp) {
			model.addAttribute("otpMatched", "LOGIN SUCCESS.");
			return "UserDashBoard";
		} else {
			if (vendorEntity.getFailedAttempt() < 3) {
				return "redirect:/loadLoginSuccess";
			} else {
				model.addAttribute("otpExpired", "OTP EXPIRED. PLEASE REGENERATE OTP.");
				return "redirect:/loadLoginPage";
			}

		}

	}

	// load update details
	@GetMapping("/load-update")
	public String loadUpdate(@RequestParam String email, Model model) {
		System.out.println("load update.");
		VendorEntity vendorEntity = vendorService.findByEmail(email);
		model.addAttribute("vendorEntity", vendorEntity);

		return "UpdateDetails";
	}

	@PostMapping("/update-details")
	public String updateDetails(@RequestParam String vendorNname, @RequestParam String vendorLocation,
			@RequestParam String ownerName, @RequestParam Long contactNumber, @RequestParam String vendorEmail,
			@RequestParam int id,HttpSession session, Model model) {

		boolean detailsUpdated = this.vendorService.detailsUpdated(vendorNname, vendorLocation, ownerName,
				contactNumber, vendorEmail, id);
		
		VendorEntity vendorEntity = this.vendorService.findByEmail(vendorEmail);
		model.addAttribute("vendorEntity", vendorEntity);
		
		if (detailsUpdated) {

			session.setAttribute("message", "Update Successfully");
			return "UserDashBoard";
		}
		session.setAttribute("message", "Update is not allowed until status approved.");
		return "UserDashBoard";
	}

	// load delete account page
	@GetMapping("/load-delete-account")
	public String loadDeleteAccount() {

		System.out.println("Load Delete Account.");

		return "DeleteAccount";
	}

	// delete account page
	@PostMapping("/delete-account")
	public String deleteAccount(@RequestParam String email, HttpSession session) {

		boolean deleteAccount = this.vendorService.deleteAccount(email);
		if (deleteAccount) {
			System.out.println("Account deleted.");
			session.setAttribute("message", "Are you sure want to delete account.");

			return "redirect:/loadRegister";
		}
		System.out.println("Account not deleted.");
		session.setAttribute("message", "Something went wrong please try again.");

		return "redirect:/load-delete-account";
	}

}
