package com.vmanage.resource;

import java.time.Duration;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.vmanage.entities.VendorEntity;
import com.vmanage.login.LoginService;
import com.vmanage.login.LoginServiceImpl;

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

		vendorEmail = vendorEmail.replace(",", "");
		System.out.println("sendung otp  to email : " + vendorEmail);

		if (vendorEmail != null && !vendorEmail.isEmpty()) {
			this.loginService.sendOtp(vendorEmail);
			model.addAttribute("mail", vendorEmail);

			return "loginSuccess";
		}

		return "login";
	}

	/* VERIFY OTP */
	@GetMapping(value = "/otpVerify")
	public String verifyOtp(@RequestParam Integer otp, @RequestParam String vendorEmail, Model model) {

		VendorEntity byEmail = this.loginService.verifyOtp(otp, vendorEmail);
		
		if (byEmail != null && byEmail.getVendorEmail().equalsIgnoreCase(vendorEmail)) {
			
			if (byEmail.getOtp() != null && byEmail.getOtp().equals(otp)
					&& Duration.between(byEmail.getOtpGenratedTime(), LocalDateTime.now()).getSeconds() < (1 * 60)) {
				
				System.out.println("OTP MATCHED.");
				model.addAttribute("otpMatched", "Login Success.");

				loginService.resetAttemptCount(vendorEmail);

			} else if (!byEmail.getOtp().equals(otp) && byEmail.isAccountNonLocked()) {
				if (byEmail.getFailedAttempt() < LoginServiceImpl.ATTEMPT_TIME - 1) {
					loginService.updateFailedAttemptCOunt(byEmail.getFailedAttempt(), vendorEmail);
				} else if(byEmail.getFailedAttempt()<=LoginServiceImpl.ATTEMPT_TIME-1) {
					loginService.lockAccount(vendorEmail);
					System.out.println("LOCKED ACCOUNT.");
				}
			} else if (!byEmail.isAccountNonLocked()) {
				if (loginService.unlockAccountTimeExpired(vendorEmail)) {
					System.out.println("ACCOUNT IS UNLOCKED.");
					loginService.resetAttemptCount(vendorEmail);
				} else {
					System.out.println("ACCOUNT IS LOCKED.");
				}

			}
		}

		return "loginSuccess";

	}

}
