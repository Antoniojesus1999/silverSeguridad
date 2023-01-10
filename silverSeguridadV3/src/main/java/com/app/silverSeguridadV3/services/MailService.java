
package com.app.silverSeguridadV3.services;

import com.app.silverSeguridadV3.models.MailRequest;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.xwpf.usermodel.*;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTP;
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

    public void leerDocumentoWordDoc(MailRequest data) throws IOException {
//"sepa.docx"
        boolean contratoEncontrado = false;
        boolean precioEncontrado = false;
        boolean aEncontrado = false;
        try {
            // Abrir el archivo de Word
            FileInputStream fis = new FileInputStream("sepa.docx");
            XWPFDocument document = new XWPFDocument(fis);

            // Iterar sobre todas las oraciones del documento
            for (XWPFParagraph p : document.getParagraphs()) {
                List<XWPFRun> runs = p.getRuns();
                if (runs != null) {
                    // Iterar sobre cada palabra de la oración
                    for (XWPFRun r : runs) {
                        String text = r.getText(0);
                        if (contratoEncontrado){
                            contratoEncontrado = false;
                            text = "contrato";
                            r.setText(text, 0);
                            r.setUnderline(UnderlinePatterns.valueOf(HSSFFont.U_DOUBLE));
                        }else if (precioEncontrado){
                            text = "price";
                            r.setText(text, 0);
                            r.setUnderline(UnderlinePatterns.valueOf(HSSFFont.U_DOUBLE));
                            precioEncontrado = false;
                        }
                        else if (aEncontrado){
                            text = "antonio jesus";
                            r.setText(text, 0);
                            aEncontrado = false;
                        }
                        if (text != null && text.contains("CONTRATO:")) {
                            contratoEncontrado = true;
                        } else if (text != null && text.contains("Precio:")) {
                            precioEncontrado = true;
                        }
                        else if (text != null && text.contains(")")) {
                            aEncontrado = true;
                        }
                    }
                }
            }

            List<XWPFTable> tables = document.getTables();
            for (XWPFTable table : tables){
                System.out.println("body "+ table.getBody());
                System.out.println("text " +table.getText().replace("EE","TOMA!"));
                System.out.println("table.getRows() " +table.getRows());
                System.out.println("table.getRow(0) " +table.getRow(0));
                List<XWPFTableRow> rows = table.getRows();
                for (XWPFTableRow row : rows) {

                    List<XWPFTableCell> cells = row.getTableCells();
                    for (XWPFTableCell cell :cells){
                        for (XWPFParagraph p : cell.getParagraphs()) {
                            List<XWPFRun> runs = p.getRuns();
                            if (runs != null) {
                                // Iterar sobre cada palabra de la oración
                                for (XWPFRun r : runs) {
                                    String text = r.getText(0);
                                    if (text != null && text.contains("Nombre:")) {
                                        text = "Nombre: Antonio Jesús ";
                                        r.setText(text);
                                    }
                                }

                            }

                        }
                    }
                }

            }
            // Guardar el documento modificado
            FileOutputStream out = new FileOutputStream("sepa.docx");
            document.write(out);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
}}



