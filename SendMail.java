package com.topjavatutorial;

import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.ws.rs.PathParam;

import com.sun.mail.smtp.SMTPTransport;

public class SendMail {
	// Send e-mail
		public static void sendEmail(@PathParam("username") String username, @PathParam("password") String password) {

			final String SMTP_SERVER = "smtp.gmail.com";
			final String USERNAME = "vitale.max25@gmail.com";
			final String PASSWORD = "Finestra10";

			final String EMAIL_FROM = "vitale.max25@gmail.com";
			final String EMAIL_TO = username;
			final String EMAIL_TO_CC = "";

			final String EMAIL_SUBJECT = "Primo accesso Vivaio felice";
			final String EMAIL_TEXT = "Benvenuto! La tua password di autenticazione è: " + password
					+ ". Il tuo username è l'indirizzo di posta elettronica usato in fase di registrazione. "
					+ "Con questi dati potrai entrare nel tuo account e cambiare la password al primo accesso. ";
			Properties prop = System.getProperties();
			prop.put("mail.smtp.host", SMTP_SERVER); // optional, defined in SMTPTransport
			prop.put("mail.smtp.auth", "true");
			prop.put("mail.smtp.port", "25"); // default port 25
			prop.put("mail.smtp.starttls.enable", "true");

			javax.mail.Session session = javax.mail.Session.getInstance(prop, null);
			Message msg = new MimeMessage(session);

			try {

				// from
				msg.setFrom(new InternetAddress(EMAIL_FROM));

				// to
				msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(EMAIL_TO, false));

				// cc
				msg.setRecipients(Message.RecipientType.CC, InternetAddress.parse(EMAIL_TO_CC, false));

				// subject
				msg.setSubject(EMAIL_SUBJECT);

				// content
				msg.setText(EMAIL_TEXT);

				msg.setSentDate(new Date());

				// Get SMTPTransport
				SMTPTransport t = (SMTPTransport) session.getTransport("smtp");

				// connect
				t.connect(SMTP_SERVER, USERNAME, PASSWORD);

				// send
				t.sendMessage(msg, msg.getAllRecipients());

				System.out.println("Response: " + t.getLastServerResponse());

				t.close();

			} catch (MessagingException e) {
				e.printStackTrace();
			}
		}

		// Recupero password
		public static void sendPasswordRecuperata(@PathParam("username") String username, @PathParam("password") String password) {

			final String SMTP_SERVER = "smtp.gmail.com";
			final String USERNAME = "vitale.max25@gmail.com";
			final String PASSWORD = "Finestra10";

			final String EMAIL_FROM = "vitale.max25@gmail.com";
			final String EMAIL_TO = username;
			final String EMAIL_TO_CC = "";

			final String EMAIL_SUBJECT = "Recupero password";
			final String EMAIL_TEXT = "La password che hai richiesto è: " + password;

			Properties prop = System.getProperties();
			prop.put("mail.smtp.host", SMTP_SERVER); // optional, defined in SMTPTransport
			prop.put("mail.smtp.auth", "true");
			prop.put("mail.smtp.port", "25"); // default port 25
			prop.put("mail.smtp.starttls.enable", "true");

			javax.mail.Session session = javax.mail.Session.getInstance(prop, null);
			Message msg = new MimeMessage(session);

			try {

				// from
				msg.setFrom(new InternetAddress(EMAIL_FROM));

				// to
				msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(EMAIL_TO, false));

				// cc
				msg.setRecipients(Message.RecipientType.CC, InternetAddress.parse(EMAIL_TO_CC, false));

				// subject
				msg.setSubject(EMAIL_SUBJECT);

				// content
				msg.setText(EMAIL_TEXT);

				msg.setSentDate(new Date());

				// Get SMTPTransport
				SMTPTransport t = (SMTPTransport) session.getTransport("smtp");

				// connect
				t.connect(SMTP_SERVER, USERNAME, PASSWORD);

				// send
				t.sendMessage(msg, msg.getAllRecipients());

				System.out.println("Response: " + t.getLastServerResponse());

				t.close();

			} catch (MessagingException e) {
				e.printStackTrace();
			}
		}
}
