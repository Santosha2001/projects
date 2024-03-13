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

import com.vmanage.entities.AdminEntity;
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

	/* IS EXIST GST, MOBILE, EMAIL, WEBSITE */
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
			query.executeUpdate();
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

	/* UPDATE OTP GENERATED TIME BY EMAIL */
	@Override
	public void updatedOtpGeneratedTime(LocalDateTime otpGeneratedTime, String email) {
		EntityManager entityManager = factory.createEntityManager();

		try {
			entityManager.getTransaction().begin();
			Query query = entityManager.createNamedQuery("updatedOtpGeneratedTime");
			query.setParameter("time", otpGeneratedTime);
			query.setParameter("email", email);
			query.executeUpdate();
			entityManager.getTransaction().commit();

		} catch (PersistenceException e) {
			System.out.println("PersistenceException: in updatedOtpGeneratedTime " + e.getMessage());
			e.printStackTrace();
			entityManager.getTransaction().rollback();
		} finally {
			entityManager.close();
		}
	}

	/* UPDATE FAILED ATTEMPT CPUNT */
	@Override
	public void updateFailedAttemptCount(int failedCount, String email) {
		EntityManager entityManager = factory.createEntityManager();

		try {
			entityManager.getTransaction().begin();
			Query query = entityManager.createNamedQuery("updateFailedAttemptCount");
			query.setParameter("failedOTP", failedCount);
			query.setParameter("email", email);
			query.executeUpdate();
			entityManager.getTransaction().commit();

		} catch (PersistenceException e) {
			System.out.println("PersistenceException in updateFailedAttemptCount: " + e.getMessage());
			entityManager.getTransaction().rollback();

		} finally {
			entityManager.close();
		}

	}

	/* UPDATE ACCOUNT LOCK TIME */
	@Override
	public void updateAccountLockTime(LocalDateTime accountLockTime, String email) {
		EntityManager entityManager = factory.createEntityManager();

		try {
			entityManager.getTransaction().begin();
			Query query = entityManager.createNamedQuery("updateAccLockTime");
			query.setParameter("lockTime", accountLockTime);
			query.setParameter("email", email);
			query.executeUpdate();
			entityManager.getTransaction().commit();

		} catch (PersistenceException e) {
			System.out.println("PersistenceException: in updateAccountLockTime " + e.getMessage());
			entityManager.getTransaction().rollback();

		} finally {
			entityManager.close();
		}
	}

	/* EXPERING OTP, RESETING FAILED ATTEMPTS */
	@Override
	public void expireOTPAndAttempt(Integer OTP, int resetAttempt, String email) {
		EntityManager entityManager = factory.createEntityManager();

		try {
			entityManager.getTransaction().begin();
			Query query = entityManager.createNamedQuery("expireOTPAndResetAttempt");
			query.setParameter("otp", OTP);
			query.setParameter("resetAttempt", resetAttempt);
			query.setParameter("email", email);
			query.executeUpdate();
			entityManager.getTransaction().commit();

		} catch (PersistenceException e) {
			System.out.println("expireOTPAndAttempt " + e.getMessage());
			entityManager.getTransaction().rollback();
		} finally {
			entityManager.close();
		}
	}

	/* FIND ALL ADMINS */
	@Override
	public List<AdminEntity> findAllAdmins() {

		EntityManager entityManager = factory.createEntityManager();
		List<AdminEntity> adminEntities = new ArrayList<AdminEntity>();

		try {
			entityManager.getTransaction().begin();
			adminEntities = entityManager.createNamedQuery("findAllAdmins").getResultList();
			entityManager.getTransaction().commit();

		} catch (PersistenceException e) {
			System.out.println("PersistenceException: in admin findAll " + e.getMessage());
			entityManager.getTransaction().rollback();
		} finally {
			entityManager.close();
		}

		return adminEntities;
	}

	/* APPROVE STATUS */
	@Override
	public VendorEntity approveStatus(String status, String email) {
		EntityManager entityManager = factory.createEntityManager();
		VendorEntity entity=new VendorEntity();
		try {
			entityManager.getTransaction().begin();
			Query query = entityManager.createNamedQuery("approveStatus");
			query.setParameter("approve", status);
			query.setParameter("email", email);

			query.executeUpdate();
			entityManager.getTransaction().commit();
			
			return entity;

		} catch (PersistenceException e) {
			System.out.println("PersistenceException: in approveStatus " + e.getMessage());
			entityManager.getTransaction().rollback();
		} finally {
			entityManager.close();
		}
		return null;
	}

}
