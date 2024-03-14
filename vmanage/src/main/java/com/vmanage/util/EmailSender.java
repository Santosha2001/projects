package com.vmanage.util;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Component;

@Component
public class EmailSender {

	public EmailSender() {
		System.out.println("EmailSender created.");
	}

	public boolean emailSender(String to, String from, String subject, String text) {

		int portNumber = 587;
		String hostName = "smtp.gmail.com";
		String password = "nnwd qtqd suad dkau";

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
