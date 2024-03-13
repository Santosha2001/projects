package com.vmanage.restcontrollers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class AdminController {

	public AdminController() {
		System.out.println("AdminController created.");
	}
	
	@GetMapping("")
	public String approveStatus() {
		
		return "";
	}
}
