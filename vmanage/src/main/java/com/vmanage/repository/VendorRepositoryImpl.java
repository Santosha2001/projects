package com.vmanage.repository;

import java.util.ArrayList;
import java.util.List;

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
	public void save(VendorEntity vendorEntity) {
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
			if (entity == null) {
				return null;
			}
			System.err.println("PersistenceException in exist: " + e.getMessage());
		} finally {
			entityManager.close();
			System.out.println("EM closed.");
		}
		return entity;
	}

	@Override
	public List<VendorEntity> findAll() {
		EntityManager entityManager = factory.createEntityManager();
		System.out.println("EntityManager: " + entityManager);
		List<VendorEntity> list = new ArrayList<VendorEntity>();

		try {
			Query query = entityManager.createNamedQuery("findAll");
			list = query.getResultList();
		} catch (PersistenceException e) {
			System.err.println("PersistenceException: " + e.getMessage());
		} finally {
			entityManager.close();
			System.out.println("EntityManager closaed.");
		}

		return list;
	}
	
	/*
	@Override
	public void updateOtpByEmail(int otp, String email) {

		EntityManager entityManager = factory.createEntityManager();
		System.out.println("EntityManager: " + entityManager);
		EntityTransaction transaction = entityManager.getTransaction();
		System.out.println("EntityTransaction: " + transaction);

		try {
			Query query = entityManager.createNamedQuery("updateOtpByEmail");
			query.setParameter("otp", otp);
			query.setParameter("mail", email);
			query.executeUpdate();
			transaction.commit();
			System.out.println("Transaction committed.");
		} catch (PersistenceException e) {
			System.err.println("PersistenceException: " + e.getMessage());
			transaction.rollback();
		} finally {
			entityManager.close();
			System.out.println("EntityManager closaed.");
		}

	}
	*/

}
