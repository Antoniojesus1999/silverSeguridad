
package com.app.silverSeguridadV3.services;

import com.app.silverSeguridadV3.models.MailRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.Document;
import javax.swing.text.StyledDocument;
import javax.swing.text.rtf.RTFEditorKit;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

@Service
public class MailService {
    private static Logger log = Logger.getLogger(String.valueOf(MailService.class));
    @Autowired
    private JavaMailSender mailSender;


    public void enviarCorreo(MailRequest data) {

        System.out.println(data.getNombre() + data.getApellido());
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("antoniojesuspv99@gmail.com");
        message.setSubject("Asunto del correo");
        message.setText("Parametro nombre -> " + data.getNombre() + " Parametro apellido: " + data.getApellido());
        mailSender.send(message);
    }

    public MailRequest leerDocumentoWordDoc(MailRequest data)  {
        return data;
    }
}


