package com.vmanager.repository;

import java.time.LocalDate;
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

import com.vmanager.entities.VendorEntity;

@Repository
public class VendorRepositoryImpl implements VendorRepository {

	@Autowired
	private EntityManagerFactory factory;

	public VendorRepositoryImpl() {
		System.out.println("VendorRepositoryImpl created.");
	}

	/* SAVE ENTITY */
	@Override
	public void saveVendor(VendorEntity vendorEntity) {

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
	public List<VendorEntity> findAllVendors() {
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

	/* UPDATE VENDOR STATUS */
	@Override
	public void updateVendorStatusByEmail(String status, String email) {

		EntityManager entityManager = factory.createEntityManager();
		try {
			entityManager.getTransaction().begin();
			Query query = entityManager.createNamedQuery("updateStatusByEmail");
			query.setParameter("status", status);
			query.setParameter("email", email);
			query.executeUpdate();

			entityManager.getTransaction().commit();

		} catch (PersistenceException e) {
			System.out.println("PersistenceException: in updateStatusByEmail " + e.getMessage());
			entityManager.getTransaction().rollback();
		} finally {
			entityManager.close();
		}

	}

	/* UPDATE DETAILS */
	@Override
	public void updateVendorDetails(String vendorName, String location, String ownerName, Long contact, String email) {

		EntityManager entityManager = factory.createEntityManager();
		try {
			entityManager.getTransaction().begin();
			Query query = entityManager.createNamedQuery("updateDetails");
			query.setParameter("vName", vendorName);
			query.setParameter("location", location);
			query.setParameter("vOwner", ownerName);
			query.setParameter("number", contact);
			query.setParameter("email", email);

			query.executeUpdate();
			entityManager.getTransaction().commit();

		} catch (PersistenceException e) {
			System.out.println("PersistenceException in updateDetails: " + e.getMessage());
			entityManager.getTransaction().rollback();
		} finally {
			entityManager.close();
		}
	}

	@Override
	public VendorEntity findById(int id) {
		EntityManager entityManager = factory.createEntityManager();
		VendorEntity entity = new VendorEntity();

		try {
			Query query = entityManager.createNamedQuery("findById");
			Query query2 = query.setParameter("id", id);
			entity = (VendorEntity) query2.getSingleResult();

		} catch (Exception e) {
			System.err.println("PersistenceException: in findById " + e.getMessage());

		} finally {
			entityManager.close();
		}
		return entity;
	}

	/* UPDATE UPDATED DATE AND UPDATED BY */
	@Override
	public void updateUpdatedDateAndUpdatedBy(String updatedBy, LocalDate date, String email) {
		EntityManager entityManager = factory.createEntityManager();

		try {
			entityManager.getTransaction().begin();
			Query query = entityManager.createNamedQuery("updateUpdatedDateAndBy");
			query.setParameter("by", updatedBy);
			query.setParameter("date", date);
			query.setParameter("email", email);

			query.executeUpdate();
			entityManager.getTransaction().commit();

		} catch (PersistenceException e) {
			System.out.println("PersistenceException in updateUpdatedDate: " + e.getMessage());
			entityManager.getTransaction().rollback();
		} finally {
			entityManager.close();
		}
	}

	// delete account
	@Override
	public void deleteVendorAccount(String email) {

		EntityManager entityManager = factory.createEntityManager();
		try {
			entityManager.getTransaction().begin();
			Query query = entityManager.createNamedQuery("deleteAccount");
			query.setParameter("email", email);
			int delete = query.executeUpdate();
			System.out.println("delete email: " + delete);

			entityManager.getTransaction().commit();

		} catch (PersistenceException e) {
			System.out.println("PersistenceException in deleteAccount: " + e.getMessage());
			entityManager.getTransaction().rollback();
		} finally {
			entityManager.close();
		}
	}

}
