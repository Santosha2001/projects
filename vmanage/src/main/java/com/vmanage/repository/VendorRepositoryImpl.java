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

		EntityManager entityManager = factory.createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		try {
			transaction.begin();
			entityManager.persist(vendorEntity);
			transaction.commit();

		} catch (PersistenceException e) {
			System.err.println("PersistenceException: in save " + e.getMessage());
			transaction.rollback();

		} finally {
			entityManager.close();
		}
	}

	/* IS EXIST */
	@Override
	public VendorEntity isExistByGstOrNumberOrMailOrSite(String gst, Long number, String email, String website) {
		EntityManager entityManager = factory.createEntityManager();
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
		}
		return entity;
	}

	/* FIND ALL ENTITIES */
	@Override
	public List<VendorEntity> findAll() {
		EntityManager entityManager = factory.createEntityManager();
		List<VendorEntity> list = new ArrayList<VendorEntity>();

		try {
			Query query = entityManager.createNamedQuery("findAll");
			list = query.getResultList();

		} catch (PersistenceException e) {
			System.err.println("PersistenceException: in findAll " + e.getMessage());

		} finally {
			entityManager.close();
		}
		return list;
	}

	/* UPDATED OTP BY EMAIL */
	@Override
	public void updateOtpByEmail(Integer otp, String email) {
		EntityManager entityManager = factory.createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();

		try {
			transaction.begin();
			Query query = entityManager.createNamedQuery("updateOtpByEmail");
			query.setParameter("otp", otp);
			query.setParameter("email", email);

			int result = query.executeUpdate();
			System.out.println("result: " + result);

			transaction.commit();

		} catch (PersistenceException e) {
			System.err.println("PersistenceException: in updateOtpByEmail " + e.getMessage());
			transaction.rollback();

		} finally {
			entityManager.close();
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

		} catch (Exception e) {
			System.err.println("PersistenceException: in findByEmail " + e.getMessage());

		} finally {
			entityManager.close();
		}
		return entity;
	}

	/* FIND ALL OTP's FROM THE DATABSE */

	/*
	 * @Override public List<int[]> findOtp(int otp) {
	 * 
	 * EntityManager entityManager = factory.createEntityManager(); List<int[]> list
	 * = new ArrayList<int[]>();
	 * 
	 * try { Query query = entityManager.createNamedQuery("findOtp"); list =
	 * query.getResultList();
	 * 
	 * } catch (PersistenceException e) {
	 * System.err.println("PersistenceException: in findOtp " + e.getMessage());
	 * 
	 * } finally { entityManager.close(); } return list; }
	 */

	/* UPDATE OTP GENERATED TIME BY EMAIL */
	@Override
	public void updatedOtpGeneratedTime(LocalDateTime otpGeneratedTime, String email) {
		EntityManager entityManager = factory.createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();

		try {
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
		}
	}

	/* FIND OTP BY EMAIL */
	/*
	@Override
	public Integer findOtpByEmail(Integer otp, String email) {

		EntityManager entityManager = factory.createEntityManager();
		System.out.println("EntityManager");

		try {
			Query query = entityManager.createNamedQuery("findOtpByEmail");
			query.setParameter("mail", email);
			query.getSingleResult();

		} catch (PersistenceException e) {
			System.out.println("PersistenceException: in findOtpByEmail " + e.getMessage());
		} finally {
			entityManager.close();
			System.out.println("EntityManager closed.");
		}
		return otp;

	}
	*/

}
