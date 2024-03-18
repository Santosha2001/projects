package com.vmanage.admin.repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.vmanage.entities.AdminEntity;

@Repository
public class AdminRepositoryImpl implements AdminRepository {

	@Autowired
	private EntityManagerFactory factory;

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

	/* UPDATE VENDOR STATUS */
	@Override
	public void updateStatusByEmail(String status, String email) {

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

	@Override
	public void updateUpdatedDateAndUpdatedBy(String updatedBy, LocalDate updatedDate, String email) {
		EntityManager entityManager = factory.createEntityManager();
		try {
			entityManager.getTransaction().begin();
			Query query = entityManager.createNamedQuery("updateUpdatedDateAndUpdatedBy");
			query.setParameter("updatedBy", updatedBy);
			query.setParameter("updateDate", updatedDate);
			query.setParameter("email", email);
			int update = query.executeUpdate();
			System.out.println("update: " + update);

		} catch (PersistenceException e) {
			System.out.println("PersistenceException: in updateUpdatedDateAndUpdatedBy " + e.getMessage());
			entityManager.getTransaction().rollback();
		} finally {
			entityManager.close();
		}
	}

}
