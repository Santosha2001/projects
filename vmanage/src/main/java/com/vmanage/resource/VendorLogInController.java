package com.vmanage.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vmanage.service.VendorLogInAjaxService;

@RestController
@RequestMapping("/")
public class VendorLogInController {

	@Autowired
	private VendorLogInAjaxService ajaxService;

	public VendorLogInController() {
		System.out.println("VendorLogInController created.");
	}

	@GetMapping(value = "/mailLogInAjax/{email:.+}")
	public String emailAjaxForLogIn(@PathVariable String email) {
		String emailLogInAjax = this.ajaxService.emailLogInAjax(email);
		System.out.println("mail login ajax: " + email);
		return emailLogInAjax;
	}

	
}
