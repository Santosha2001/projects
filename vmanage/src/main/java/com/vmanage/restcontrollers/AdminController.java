package com.vmanage.restcontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vmanage.admin.service.AdminService;

@RestController
@RequestMapping("/")
public class AdminController {
	
	@Autowired
	private AdminService adminService;

	public AdminController() {
		System.out.println("AdminController created.");
	}
	
	@GetMapping("/statusUpdate")
	public String approveStatus(@RequestParam String email) {
		System.out.println(email);
		adminService.statusApprove(email);
		
		return "StatusApproved";
	}
}
