package com.vmanage.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vmanage.email.EmailSender;
import com.vmanage.entities.VendorEntity;
import com.vmanage.repository.VendorRepository;


@Service
public class VendorServiceImpl implements VendorService {

	@Autowired
	private VendorRepository repository;
	@Autowired
	private EmailSender emailSender;

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
	public boolean sendEmail(String email) {

		System.out.println("Sending email.");

		/*String portNumber = "587";
		String hostName = "smtp.office365.com";
		String fromEmail = "santosha7022@outlook.com";
		String password = "Rathod@2001";
		String to = email;

		String subject = "Account creation success.";
		String text = "Congragulations, your account created. Login for more information.";

		Properties prop = new Properties();

		prop.put("mail.smtp.host", hostName);
		prop.put("mail.smtp.port", portNumber);
		prop.put("mail.smtp.starttls.enable", true);
		prop.put("mail.debug", true);
		prop.put("mail.smtp.auth", true);
		prop.put("mail.transport.protocol", "smtp");

		Session session = Session.getInstance(prop, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(fromEmail, password);
			}
		});

		MimeMessage message = new MimeMessage(session);
		try {
			message.setFrom(new InternetAddress(fromEmail));
			message.setSubject(subject);
			message.setText(text);
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			Transport.send(message);

			return true;
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		*/
		
		String subject = "Welcome, Your account created.";
		String text = "Congragulations, your account created. Login for more information.";
		String from="santosha7022@outlook.com";
		String to=email;
		
		boolean sender = this.emailSender.emailSender(to, from, subject, text);
		if (sender) {
			System.out.println("Email sent.");
			return true;
		}
		return false;
	}

}
