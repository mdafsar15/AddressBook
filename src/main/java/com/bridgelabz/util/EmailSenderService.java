package com.bridgelabz.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

public class EmailSenderService {
	@Autowired
	private JavaMailSender mailSender;

	public void sendEmail(String email, String subject, String body) {
		SimpleMailMessage message = new SimpleMailMessage();

		message.setFrom("navyas8118@gmail.com");
		message.setTo(email);
		message.setText(body);
		message.setSubject(subject);
		mailSender.send(message);
		System.out.println("Mail Sent Successfully");
	}

}
