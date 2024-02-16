package com.vmanage.repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import com.vmanage.entities.VendorEntity;

@Repository
public class VendorRepositoryImpl implements VendorRepository {

	@Autowired
	private EntityManagerFactory factory;

	public VendorRepositoryImpl() {
		System.out.println("VendorRepositoryImpl created.");
	}

	@Override
	public void saveRepo(VendorEntity vendorEntity) {
		System.out.println(vendorEntity + "\n");

		EntityManager entityManager = factory.createEntityManager();
		System.out.println("EntityManager created: " + entityManager);
		EntityTransaction transaction = entityManager.getTransaction();
		System.out.println("EntityTransaction created: " + transaction);
		try {
			transaction.begin();
			entityManager.persist(vendorEntity);
			transaction.commit();
		} catch (PersistenceException e) {
			System.err.println("PersistenceException: " + e.getMessage());
			transaction.rollback();
		} finally {
			entityManager.close();
			System.out.println("EntityManager closed.");
		}
	}
}
