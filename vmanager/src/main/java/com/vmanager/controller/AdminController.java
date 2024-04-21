package com.vmanager.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.vmanager.dto.VendorDTO;
import com.vmanager.entities.VendorEntity;
import com.vmanager.repository.VendorRepository;
import com.vmanager.service.admin.AdminService;
import com.vmanager.service.vendor.VendorService;

@Controller
public class AdminController {

	@Autowired
	private AdminService adminService;

	@Autowired
	private VendorRepository repository;

	@Autowired
	private VendorService service;

	public AdminController() {
		System.out.println("Admin Controller.");
	}

	@GetMapping("/loadAdmin")
	public String loadAdmin() {
		System.out.println("admin login");
		return "AdminLogin";
	}

	@PostMapping(value = "/adminLogin")
	public String adminLogin(@RequestParam String adminName, @RequestParam String adminPassword, HttpSession session,
			Model model) {
		System.out.println("adminName : " + adminName);
		System.out.println("adminPassword : " + adminPassword);

		boolean byNameAndPassword = adminService.findAdminByNameAndPassword(adminName, adminPassword);
		if (byNameAndPassword) {

			List<VendorEntity> vendorList = repository.findAllVendors();
			model.addAttribute("vendorsList", vendorList);
			vendorList.forEach(System.out::println);

			return "AdminDashBoard";
		}

		session.setAttribute("admin-login-failed", "Login failed.");
		return "redirect:/loadAdmin";
	}

	@GetMapping("/approve")
	public String approveStatus(@RequestParam String email, Model model) {

		System.out.println(email);

		VendorDTO vendorEntity = service.findByEmail(email);
		model.addAttribute("vendorEntity", vendorEntity);

		return "StatusApprove";
	}

	@PostMapping("/statusUpdate")
	public String updateStatus(@RequestParam String vendorEmail, Model model) {

		boolean approveStatus = service.approveVendorStatusByEmail(vendorEmail);
		if (approveStatus) {
			System.out.println("status updated.");

			List<VendorEntity> vendorList = repository.findAllVendors();
			model.addAttribute("vendorsList", vendorList);
			return "StatusApproved";
		}

		return "StatusApprove";

	}

}
