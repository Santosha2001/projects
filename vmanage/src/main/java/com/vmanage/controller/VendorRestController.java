package com.vmanage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vmanage.service.VendorService;

@RestController
public class VendorRestController {

	public VendorRestController() {
		System.out.println("VendorRestController created.");
	}

	@Autowired
	private VendorService service;

	@PostMapping("/validateEmail")
	public ResponseEntity<Object> checkMail(@RequestParam("vendorEmail") String email) {

		String fetchMails = this.service.fetchMails(email);

		return new ResponseEntity<Object>(fetchMails, HttpStatus.OK);
	}

}
