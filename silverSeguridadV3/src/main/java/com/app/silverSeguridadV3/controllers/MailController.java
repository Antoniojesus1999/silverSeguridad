package com.app.silverSeguridadV3.controllers;

import com.app.silverSeguridadV3.models.MailRequest;
import com.app.silverSeguridadV3.services.MailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Base64;


@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
public class MailController {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private MailService mailService;

    @PostMapping(value = "/mail", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void metodoPost(@RequestBody MailRequest data) throws IOException {
        System.out.println(data);
        data.setFirmaArray(Base64.getDecoder().decode(data.getFirma()));
        mailService.leerDocumentoWordDoc(data);
    }
}

