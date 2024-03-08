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
		
		LocalDateTime otpGenratedTime = byEmail.getOtpGenratedTime();
		LocalDateTime currentDate = LocalDateTime.now();
		
		LocalDateTime accountLockTime = byEmail.getAccountLockTime();
		
		if (byEmail != null && byEmail.getVendorEmail().equalsIgnoreCase(vendorEmail)) {
			
			if (byEmail.getOtp() != null && byEmail.getOtp().equals(otp)
					&& Duration.between(otpGenratedTime, currentDate).getSeconds() < (5 * 60)) {
				System.out.println("OTP IS VALID.");
				model.addAttribute("otpMatched", "LOGIN SUCCESS.");
				return "userView";
				
			} else if(byEmail.getOtp() != null && byEmail.getOtp().equals(otp) &&
					Duration.between(otpGenratedTime, currentDate).getSeconds()>(5*60)) {
				System.out.println("OTP EXPIRED.");
				loginService.expireOTPAndResetAttempt(null, 0, vendorEmail);
				model.addAttribute("otpNotInTime", "LOGIN EXPIRED, DUE TO NOT IN TIME.");
				
			}
			else if (!byEmail.getOtp().equals(otp)) {
				
				if (byEmail.getFailedAttempt() < LoginServiceImpl.ATTEMPT_TIME - 1) {
					loginService.updateFailedAttemptCount(byEmail.getFailedAttempt(), vendorEmail);
					
				} else if(byEmail.getFailedAttempt()==LoginServiceImpl.ATTEMPT_TIME-1) {
					loginService.updateFailedAttemptCount(byEmail.getFailedAttempt(), vendorEmail);
					loginService.accountLockTime(LocalDateTime.now(), vendorEmail);
					
				}
				else  {
					loginService.expireOTPAndResetAttempt(null, 0, vendorEmail);
					System.out.println("ACCOUNT EXPIRED.");
					
					if(Duration.between(accountLockTime, currentDate).getSeconds()>(1*60)) {
						System.out.println("ACCOUNT IS UNLOCKED.");
						
						loginService.expireOTPAndResetAttempt(null, 0, vendorEmail);
						System.out.println("OTP EXPERIED AND ATTEMPT RESETED..");
						model.addAttribute("unlockedAccount", "ACCOUNT WILL UNLOCKED AFTER 1 MINUTE");
						
						return "loginSuccess";

						
					} 
					model.addAttribute("accountExpired", "ACCOUNT EXPIRED.");
					
				}
			} 
			
		}

		return "loginSuccess";

	}

}
