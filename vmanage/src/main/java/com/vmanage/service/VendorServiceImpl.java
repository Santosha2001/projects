package com.vmanage.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vmanage.entities.VendorEntity;
import com.vmanage.repository.VendorRepository;

@Service
public class VendorServiceImpl implements VendorService {

	@Autowired
	private VendorRepository repository;

	public VendorServiceImpl() {
		System.out.println("VendorServiceImpl created.");
	}

	@Override
	public VendorEntity save(VendorEntity entity) {
		entity.setCreatedBy(entity.getOwnerName());
		entity.setCreatedDate(LocalDate.now());

		this.repository.saveRepo(entity);
		return entity;

	}

	@Override
	public String isExistByNameOrMailOrSite(String name, String email, String website) {

		VendorEntity entity = repository.isExistByNameOrMailOrSite(name, email, website);

		if (entity != null) {
			if (entity.getVendorNname().equals(name)) {
				return "name already exist.";
			} else if (entity.getVendorEmail().equals(email)) {
				return "Email already exist";
			} else if (entity.getWebsite().equals(website)) {
				return "Website already exist";
			}

			System.out.println("Details not present save it.");
		}

		return null;
	}
}
