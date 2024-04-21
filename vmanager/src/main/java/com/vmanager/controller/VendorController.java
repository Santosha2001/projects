package com.vmanager.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.vmanager.dto.VendorDTO;
import com.vmanager.login.LoginService;
import com.vmanager.service.vendor.VendorService;

@Controller
public class VendorController {

	@Autowired
	private VendorService vendorService;

	@Autowired
	private LoginService loginService;

	public VendorController() {
		System.out.println("Vendor controller");
	}

	// home handler
	@GetMapping("/")
	private String homeHandler() {
		System.out.println("Home handler called.");
		return "index";
	}

	// registration handler
	@GetMapping("/loadRegister")
	private String register() {
		System.out.println("Registraion page handler called.");
		return "Registration";
	}

	// login handler
	@GetMapping("/loadLoginPage")
	private String loginHandler() {
		System.out.println("Login page handler called.");
		return "Login";
	}

	// user register handler
	@PostMapping("/register")
	public String register(@Valid VendorDTO dto, BindingResult bindingResult, Model model, HttpSession session,
			RedirectAttributes redirectAttributes) {
		System.out.println("VendorEntity has errors: " + bindingResult.hasErrors());

		model.addAttribute("vendorEntity", dto);

		if (bindingResult.hasErrors()) {
			List<ObjectError> objectErrors = bindingResult.getAllErrors();
			objectErrors.forEach(a -> System.err.println(a.getObjectName() + " " + a.getDefaultMessage()));

			model.addAttribute("errors", objectErrors);

			return "redirect:/loadRegister";

		} else {
			String uniqueError = vendorService.isExistByGstOrNumberOrMailOrSite(dto.getVendorGSTNumber(),
					dto.getContactNumber(), dto.getVendorEmail(), dto.getWebsite());
			if (uniqueError != null) {

				model.addAttribute("uniqueError", uniqueError);
				System.out.println("vendorEntity not saved.");

				session.setAttribute("unsaveMsg", "Registration Un-success.");

				return "redirect:/loadRegister";
			} else {
				this.vendorService.saveVendor(dto);
				this.vendorService.sendEmailToVendor(dto.getVendorEmail(), dto.getOwnerName());

				session.setAttribute("saveMsg", "Registration Success.");
			}
			return "redirect:/loadRegister";
		}
	}

	/* SENDING OTP TO EMAIL */
	@PostMapping("/generateOTP")
	public String loginUsingOtp(@RequestParam String vendorEmail, Model model) {

		System.out.println("otp sent to email : " + vendorEmail);

		boolean sendOtp = this.loginService.sendOtpToVendorEmail(vendorEmail);
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
	public String verifyOtp(@RequestParam String vendorEmail, @RequestParam Integer otp, Model model,
			HttpSession session) {

		VendorDTO vendorEntity = vendorService.findByEmail(vendorEmail);
		System.out.println("vendorDTO: " + vendorEntity);

		model.addAttribute("vendorEntity", vendorEntity);

		boolean isOtpMatched = loginService.verifyOtp(otp, vendorEmail);
		if (isOtpMatched) {
			session.setAttribute("login-success", "Login Success.");
			return "UserDashBoard";
		} else {
			if (vendorEntity.getFailedAttempt() < 3) {
				session.setAttribute("login-failed", "Login failed.");
				return "redirect:/loadLoginSuccess";

			} else {
				session.setAttribute("re-login", "Please regenarate OTP.");
				return "redirect:/loadLoginPage";
			}
		}
	}

	// load update details
	@GetMapping("/load-update")
	public String loadUpdate(@RequestParam String email, Model model) {
		System.out.println("load update.");

		VendorDTO dto = vendorService.findByEmail(email);
		// System.out.println("vendorEntity in loadUpdate: "+vendorEntity);

		model.addAttribute("vendorEntity", dto);

		return "UpdateDetails";
	}

	@PostMapping("/update-details")
	public String updateDetails(@RequestParam String vendorNname, @RequestParam String vendorLocation,
			@RequestParam String ownerName, @RequestParam Long contactNumber, @RequestParam String vendorEmail,
			HttpSession session, Model model) {

		boolean detailsUpdated = this.vendorService.updateVendorByEmail(vendorNname, vendorLocation, ownerName,
				contactNumber, vendorEmail);

		VendorDTO dto = this.vendorService.findByEmail(vendorEmail);
		model.addAttribute("vendorEntity", dto);

		if (detailsUpdated) {
			session.setAttribute("update-success", "Update Successfully");
			return "UserDashBoard";
		}
		session.setAttribute("update-failed", "Update is not allowed until status approved.");
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

		boolean deleteAccount = this.vendorService.deleteVendorByEmail(email);
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
