package com.app.silverSeguridadV3.controllers;

import com.app.silverSeguridadV3.models.MailRequest;
import com.app.silverSeguridadV3.services.MailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;


@RestController
@RequestMapping("/api")

public class MailController {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private MailService mailService;

    @PostMapping("/mail")
    public void metodoPost(@RequestBody MailRequest data) throws IOException {
        mailService.leerDocumentoWordDoc(data);
    }
}
