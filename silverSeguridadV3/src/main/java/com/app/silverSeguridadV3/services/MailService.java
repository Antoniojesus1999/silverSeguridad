package com.app.silverSeguridadV3.services;

import com.app.silverSeguridadV3.models.MailRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {

    @Autowired
    private JavaMailSender mailSender;



    public void enviarCorreo(MailRequest data) {
        System.out.println(data.getNombre()+data.getApellido());
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("antoniojesuspv99@gmail.com");
        message.setSubject("Asunto del correo");
        message.setText("Parametro nombre -> "+data.getNombre()+ " Parametro apellido: "+data.getApellido());
        mailSender.send(message);
    }

}
