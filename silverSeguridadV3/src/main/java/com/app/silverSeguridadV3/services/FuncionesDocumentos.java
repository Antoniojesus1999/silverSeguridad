package com.app.silverSeguridadV3.services;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FuncionesDocumentos {
    public static void copiarArchivo(String rutaLectura, String rutaDestino){
        File file = new File(rutaLectura);
        File copy = new File(rutaDestino);
        try {
            FileUtils.copyFile(file, copy);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
