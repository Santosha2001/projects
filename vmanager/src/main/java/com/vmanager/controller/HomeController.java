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

import com.vmanager.entities.VendorEntity;
import com.vmanager.service.VendorService;

@Controller
public class HomeController {

	@Autowired
	private VendorService service;

	public HomeController() {
		System.out.println("Home controller");
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
	public String register(@Valid VendorEntity vendorEntity, BindingResult bindingResult, HttpSession session,
			Model model) {
		System.out.println("VendorEntity has errors: " + bindingResult.hasErrors());

		model.addAttribute("vendorEntity", vendorEntity);

		if (bindingResult.hasErrors()) {
			List<ObjectError> objectErrors = bindingResult.getAllErrors();
			objectErrors.forEach(a -> System.err.println(a.getObjectName() + " " + a.getDefaultMessage()));

			model.addAttribute("error", objectErrors);

			return "redirect:/loadRegister";

		} else {
			String uniqueError = service.isExistByGstOrNumberOrMailOrSite(vendorEntity.getVendorGSTNumber(),
					vendorEntity.getContactNumber(), vendorEntity.getVendorEmail(), vendorEntity.getWebsite());
			if (uniqueError != null) {

				model.addAttribute("uniqueError", uniqueError);

				return "redirect:/loadRegister";
			}
			this.service.save(vendorEntity);
			this.service.sendEmail(vendorEntity.getVendorEmail(), vendorEntity.getOwnerName());

			session.setAttribute("message", "Registered successfully. Please Login.");

			return "redirect:/loadRegister";
		}
	}
}
