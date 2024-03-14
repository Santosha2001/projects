package com.vmanage.restcontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.vmanage.admin.service.AdminService;
import com.vmanage.entities.VendorEntity;
import com.vmanage.repository.VendorRepository;
import com.vmanage.service.VendorService;

@Controller
@RequestMapping("/")
public class AdminController {

	@Autowired
	private AdminService adminService;

	@Autowired
	private VendorRepository repository;

	@Autowired
	private VendorService service;

	public AdminController() {
		System.out.println("AdminController created.");
	}

	/* ADMIN LOGIN */

	@PostMapping(value = "/adminLogin")
	public String adminLogin(@RequestParam String adminName, @RequestParam String adminPassword, Model model) {
		System.out.println("adminName : " + adminName);
		System.out.println("adminPassword : " + adminPassword);

		boolean byNameAndPassword = adminService.findAdminByNameAndPassword(adminName, adminPassword);
		if (byNameAndPassword) {

			List<VendorEntity> vendorList = repository.findAll();
			model.addAttribute("vendorsList", vendorList);
			vendorList.forEach(System.out::println);

			return "AdminSigninSuccess";
		}

		System.out.println("Admin login failed.");
		model.addAttribute("adminLoginFail", "Admin Login Failed.");
		return "AdminLogin";
	}

	@GetMapping("/approve")
	public String approveStatus(@RequestParam String email, Model model) {

		System.out.println(email);

		VendorEntity vendorEntity = service.findByEmail(email);
		model.addAttribute("vendorEntity", vendorEntity);

		return "StatusApproved";
	}

	@PostMapping("/statusUpdate")
	public String updateStatus(@RequestParam String vendorEmail, Model model) {
		System.out.println("email " + vendorEmail);

		boolean approveStatus = adminService.approveStatus(vendorEmail);
		if (approveStatus) {
			System.out.println("status updated.");
			model.addAttribute("updated.", "Status updated.");

			return "StatusSuccess";
		}
		model.addAttribute("notUpdated", "Status update failed.");
		return "StatusApproved";

	}

}
