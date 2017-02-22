package com.vsm.stockmarket.services.email;

import org.springframework.mail.SimpleMailMessage;

import com.vsm.stockmarket.exception.EmailSenderServiceException;

public interface EmailSenderService {

	public void sendEmail(SimpleMailMessage message)
			throws EmailSenderServiceException;
}
