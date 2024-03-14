package com.vmanage.admin.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceException;

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

}
