package com.vmanager.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "admin")
@Data
@RequiredArgsConstructor

@NamedQuery(name = "findAllAdmins", query = "SELECT et FROM AdminEntity et")
public class AdminEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "a_id")
	private int id;

	@Column(name = "a_name")
	private String adminName;

	@Column(name = "a_password")
	private String adminPassword;
}
