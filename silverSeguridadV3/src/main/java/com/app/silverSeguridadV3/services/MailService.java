
package com.app.silverSeguridadV3.services;

import com.app.silverSeguridadV3.models.MailRequest;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
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





            //XWPFTable tablaDeudor = document.get;

            //row.getCell(0).setText("Nombre: Pedrito Sanchez");
            int numTabla = 0;
            for (XWPFTable tabla : document.getTables()) {
                if(numTabla == 0){
                    tabla.getRow(0).getCell(0).setText("Jorge Yago");
                    tabla.getRow(0).getCell(1).setText("49129916F");
                    tabla.getRow(1).getCell(0).setText("Francia 15");
                    tabla.getRow(2).getCell(0).setText("41720");
                    tabla.getRow(2).getCell(1).setText("Los palacios y villafranca");
                    tabla.getRow(2).getCell(2).setText("España");
                    tabla.getRow(3).getCell(0).setText("ES 1233 3244 2344 32423");
                    numTabla ++;
                } else if (numTabla == 1) {
                    XWPFTableCell cell = tabla.getRow(6).getCell(0);
                    // Create a new paragraph
                    XWPFParagraph paragraph = cell.addParagraph();
                    // Create a new run
                    XWPFRun run = paragraph.createRun();
                    // Add the image to the run
                    InputStream img = new FileInputStream("firma.png");
                    run.addPicture(img, XWPFDocument.PICTURE_TYPE_JPEG, "firma1.png", Units.toEMU(100), Units.toEMU(100));
                    numTabla ++;
                }

            }








            // Guardar el documento modificado
            FileOutputStream out = new FileOutputStream("sepa.docx");
            document.write(out);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            throw new RuntimeException(e);
        }
    }


}



