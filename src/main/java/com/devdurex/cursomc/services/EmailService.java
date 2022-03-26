package com.devdurex.cursomc.services;

import org.springframework.mail.SimpleMailMessage;

import com.devdurex.cursomc.domain.Pedido;

public interface EmailService {
	
	void sendOrderConfirmationEmail(Pedido obj);
	
	
	void sendEmail(SimpleMailMessage msg);

}
