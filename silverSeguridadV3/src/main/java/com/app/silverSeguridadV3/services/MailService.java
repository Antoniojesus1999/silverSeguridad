package com.app.silverSeguridadV3.services;

import com.app.silverSeguridadV3.models.MailRequest;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Logger;

@Service
public class MailService {
    private static Logger log = Logger.getLogger(String.valueOf(MailService.class));
    @Autowired
    private JavaMailSender mailSender;



    public void enviarCorreo(MailRequest data) {
        FileInputStream in = null;
        try {
            in = new FileInputStream("documento.docx");
        } catch (FileNotFoundException e) {

            log.severe("Error al buscar el archivo");
            throw new RuntimeException(e);
        }

        try {
            XWPFDocument document = new XWPFDocument(in);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //in.close();
        /*System.out.println(data.getNombre()+data.getApellido());
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("antoniojesuspv99@gmail.com");
        message.setSubject("Asunto del correo");
        message.setText("Parametro nombre -> "+data.getNombre()+ " Parametro apellido: "+data.getApellido());
        mailSender.send(message);*/
    }

}
