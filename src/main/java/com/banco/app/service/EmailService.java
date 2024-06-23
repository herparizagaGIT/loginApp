package com.banco.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
	@Autowired
	private JavaMailSender emailSender;

	public void enviarCorreo(String destinatario, String asunto, String cuerpo) {
		SimpleMailMessage mensaje = new SimpleMailMessage();
		mensaje.setTo(destinatario);
		mensaje.setSubject(asunto);
		mensaje.setText(cuerpo);
		emailSender.send(mensaje);
	}
}
