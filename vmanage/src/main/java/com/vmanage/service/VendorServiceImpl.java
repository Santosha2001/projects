package com.vmanage.service;

import java.time.LocalDate;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vmanage.entities.VendorEntity;
import com.vmanage.repository.VendorRepository;

@Service
public class VendorServiceImpl implements VendorService {

	@Autowired
	private VendorRepository repository;

	public VendorServiceImpl() {
		System.out.println("VendorServiceImpl created.");
	}

	@Override
	public VendorEntity save(VendorEntity entity) {
		entity.setCreatedBy(entity.getOwnerName());
		entity.setCreatedDate(LocalDate.now());

		this.repository.saveRepo(entity);
		return entity;
	}

	@Override
	public String isExistByGstOrNumberOrMailOrSite(String gst, Long number, String email, String website) {

		VendorEntity entity = repository.isExistByGstOrNumberOrMailOrSite(gst, number, email, website);

		if (entity != null) {
			if (entity.getVendorGSTNumber().equals(gst)) {
				return "GST already exist.";
			} else if (entity.getContactNumber().equals(number)) {
				return "Number already exist.";
			} else if (entity.getVendorEmail().equals(email)) {
				return "Email already exist";
			} else if (entity.getWebsite().equals(website)) {
				return "Website already exist";
			}

			System.out.println("Details not present save it.");
		}

		return null;
	}

	@Override
	public boolean sendMail() {

		String portNumber = "587";
		String hostName = "smtp.gmail.com";
		String fromEmail = "santosha5856@gmail.com";
		String password = "Rathod@2001";
		String to = "santosha.xworkz@gmail.com";
		String msg = "Welcome to our projct.";
		String subject = "Account creation.";

		Properties prop = System.getProperties();

		prop.put("mail.smtp.host", hostName);
		prop.put("mail.smtp.port", portNumber);
		prop.put("mail.smtp.starttls.enable", true);
		prop.put("mail.debug", true);
		prop.put("mail.smtp.auth", true);
		prop.put("mail.transport.protocol", "smtp");

		// step 1: creating the session.
		Session session = Session.getInstance(prop, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(fromEmail, password);
			}
		});

		// step 2: composing the mail.
		MimeMessage message = new MimeMessage(session);
		try {
			message.setFrom(new InternetAddress(fromEmail));
			message.setSubject(subject);
			message.setText(msg);

			// step 3: send mail.
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			Transport.send(message);

			return true;

		}

		catch (MessagingException e) {
			e.printStackTrace();
		}

		return false;
	}
}
