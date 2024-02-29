package com.vmanage.repository;

import java.time.LocalDateTime;
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

	/* SAVE ENTITY */
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

	/* IS EXIST */
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

	/* FIND ALL ENTITIES */
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

	/* UPDATED OTP BY EMAIL */
	@Override
	public void updateOtpByEmail(String otp, String email) {
		EntityManager entityManager = factory.createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();

		try {
			transaction.begin();
			System.out.println("Transaction started in updateOtpByEmail");
			Query query = entityManager.createNamedQuery("updateOtpByEmail");
			query.setParameter("otp", otp);
			query.setParameter("email", email);

			int result = query.executeUpdate();
			System.out.println("result: " + result);

			transaction.commit();
			System.out.println("Transaction committed.");

		} catch (PersistenceException e) {
			System.err.println("PersistenceException: " + e.getMessage());
			transaction.rollback();

		} finally {
			entityManager.close();
			System.out.println("EntityManager closed.");

		}
	}

	/* FIND ENTITY BY EMAIL */
	@Override
	public VendorEntity findByEmail(String email) {
		EntityManager entityManager = factory.createEntityManager();
		VendorEntity entity = new VendorEntity();

		try {
			Query query = entityManager.createNamedQuery("findByEmail");
			Query query2 = query.setParameter("email", email);

			entity = (VendorEntity) query2.getSingleResult();
			System.out.println("ENTITY: " + entity);

		} catch (Exception e) {
			System.err.println("PersistenceException: " + e.getMessage());

		} finally {
			entityManager.close();
			System.out.println("EntityManager closed.");
		}
		return entity;
	}

	/* FIND ALL OTP's FROM THE DATABSE */
	@Override
	public List<String> findOtp(String otp) {

		EntityManager entityManager = factory.createEntityManager();
		System.out.println("EntityManager created.");
		List<String> list = new ArrayList<String>();

		try {
			Query query = entityManager.createNamedQuery("findOtp");
			list = query.getResultList();

		} catch (PersistenceException e) {
			System.err.println("PersistenceException: " + e.getMessage());

		} finally {
			entityManager.close();
		}

		return list;
	}

	@Override
	public void updatedOtpGeneratedTime(LocalDateTime otpGeneratedTime, String email) {
		EntityManager entityManager = factory.createEntityManager();
		System.out.println("EntityManager created.");
		EntityTransaction transaction = entityManager.getTransaction();

		try {
			System.out.println("EntityTransaction created in updatedOtpGeneratedTime");
			transaction.begin();
			Query query = entityManager.createNamedQuery("updatedOtpGeneratedTime");
			query.setParameter("time", otpGeneratedTime);
			query.setParameter("email", email);

			int executeUpdate = query.executeUpdate();
			System.out.println("updated rows: " + executeUpdate);
			
			transaction.commit();

		} catch (PersistenceException e) {
			System.out.println("PersistenceException: in updatedOtpGeneratedTime " + e.getMessage());
			e.printStackTrace();
			transaction.rollback();
		} finally {
			entityManager.close();
			System.out.println("EntityManager closed.");
		}

	}

}
