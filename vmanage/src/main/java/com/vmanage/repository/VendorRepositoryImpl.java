package com.vmanage.repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
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

	@Override
	public VendorEntity isExistByGstOrNumberOrMailOrSite(String gst, Long number, String email, String website) {
		EntityManager entityManager = factory.createEntityManager();
		System.out.println("EM created.");
		VendorEntity entity = null;

		try {
			Query query = entityManager.createNamedQuery("isExist");
			query.setParameter("gst", gst);
			query.setParameter("number", number);
			query.setParameter("vMail", email);
			query.setParameter("vWebsite", website);
			entity = (VendorEntity) query.getSingleResult();

		} catch (PersistenceException e) {
			System.err.println("PersistenceException: " + e.getMessage());

		} finally {
			entityManager.close();
			System.out.println("EM closed.");
		}
		return entity;
	}

	@Override
	public VendorEntity findByEmail(String email) {
		EntityManager entityManager = factory.createEntityManager();
		System.out.println("EntityManager: " + entityManager);
		VendorEntity entity = null;

		try {
			Query query = entityManager.createNamedQuery("findByEmail");
			query.setParameter("email", email);
			entity = (VendorEntity) query.getSingleResult();
		} catch (PersistenceException e) {
			System.err.println("PersistenceException: " + e.getMessage());
		} finally {
			entityManager.close();
			System.out.println("EntityManager closed.");
		}
		return entity;
	}

}
