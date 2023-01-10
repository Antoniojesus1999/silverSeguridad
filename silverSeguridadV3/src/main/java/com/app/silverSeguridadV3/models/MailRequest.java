package com.app.silverSeguridadV3.models;


import lombok.Data;

@Data()

public class MailRequest {
    private String nombre;
    private String apellido;
    private String pais;
    private String ciudad;
    private String numeroCuenta;
}