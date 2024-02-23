package com.vmanage.email;

import java.util.Properties;

import org.springframework.stereotype.Component;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

@Component
public class EmailSender {

	public EmailSender() {
		System.out.println("Email sending.");
	}

	public boolean emailSender(String to, String from, String subject, String text) {

		int portNumber = 587;
		String hostName = "smtp.office365.com";
		// String from = "santosha7022@outlook.com";
		String password = "santhu@2001";

		// 1. SETTING SMTP PROPERTIES
		Properties prop = new Properties();

		prop.put("mail.smtp.host", hostName);
		prop.put("mail.smtp.port", portNumber);
		prop.put("mail.smtp.starttls.enable", true);
		prop.put("mail.debug", true);
		prop.put("mail.smtp.auth", true);
		prop.put("mail.transport.protocol", "smtp");

		// 2. CREATING SESSION OBJECT
		Session session = Session.getInstance(prop, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(from, password);
			}
		});

		// 3. CREATING MESSAGE OBJECT
		MimeMessage message = new MimeMessage(session);
		try {
			message.setFrom(new InternetAddress(from));
			message.setSubject(subject);
			message.setText(text);
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

			// 4. SAENDING MESSAGE
			Transport.send(message);
			return true;

		} catch (MessagingException e) {
			e.printStackTrace();
		}
		return false;

	}
}
