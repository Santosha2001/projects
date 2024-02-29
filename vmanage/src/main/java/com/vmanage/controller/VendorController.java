package com.vmanage.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.vmanage.entities.VendorEntity;
import com.vmanage.service.VendorService;

@Controller
@RequestMapping("/")
@EnableWebMvc
public class VendorController {

	@Autowired
	private VendorService service;

	public VendorController() {
		System.out.println("VendorController created.");
	}

	@PostMapping("/register")
	public String save(@Valid VendorEntity vendorEntity, BindingResult bindingResult, Model model) {
		System.out.println("VendorEntity has errors: " + bindingResult.hasErrors());

		/* FOR RETAINING THE VALES IN THE FORM FIELDS */
		model.addAttribute("vendorEntity", vendorEntity);

		if (bindingResult.hasErrors()) {
			List<ObjectError> objectErrors = bindingResult.getAllErrors();
			objectErrors.forEach(a -> System.err.println(a.getObjectName() + " " + a.getDefaultMessage()));

			/* FOR BACKEND(JAVA) VALIDATION OF FIELDS */
			model.addAttribute("error", objectErrors);

			return "Registration";

		} else {
			String uniqueError = service.isExistByGstOrNumberOrMailOrSite(vendorEntity.getVendorGSTNumber(),
					vendorEntity.getContactNumber(), vendorEntity.getVendorEmail(), vendorEntity.getWebsite());
			if (uniqueError != null) {

				/* EXISTENCE CHECK OF FIELDS USING BACKEND */
				model.addAttribute("uniqueError", uniqueError);

				return "Registration";
			}
			this.service.save(vendorEntity);
			this.service.sendEmail(vendorEntity.getVendorEmail(), vendorEntity.getOwnerName());

			model.addAttribute("noErrors", "Registered successfully. Please Login.");

			return "Registration";
		}
	}
}
