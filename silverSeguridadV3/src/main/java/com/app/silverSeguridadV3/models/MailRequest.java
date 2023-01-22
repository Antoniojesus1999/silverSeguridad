package com.app.silverSeguridadV3.models;


import lombok.Data;

import javax.persistence.Convert;

@Data()

public class MailRequest {
    private String nombre;
    private String apellido;

    private String firma;
    private byte[] firmaArray;


}