package com.app.silverSeguridadV3.controllers;

import com.app.silverSeguridadV3.models.MailRequest;
import com.app.silverSeguridadV3.services.MailService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")

public class MailController {
    @Autowired
    private MailService mailService;
    @PostMapping("/mail")
    @ApiOperation(value = "Enviar mail", tags = "Etiqueta 1, ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Mensaje de respuesta correcto"),
            @ApiResponse(code = 400, message = "Error en los parámetros de entrada"),
            @ApiResponse(code = 500, message = "Error 500")
    })
    public void metodoPost(@RequestBody MailRequest data) {

        mailService.enviarCorreo(data);
    }
}
