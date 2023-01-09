package com.app.silverSeguridadV3.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FuncionesDocumentos {
    public static void copiarArchivo(String rutaLectura, String rutaDestino){
        // Asume que hay un archivo llamado "original.rtf" en el directorio actual
        Path originalPath = Paths.get("sepa.doc");
        // Asume que queremos copiar el archivo en el mismo directorio con el nombre "copia.rtf"
        Path copyPath = Paths.get("copia.rtf");

        try {
            // Copia el archivo original a la ubicación de la copia
            Files.copy(originalPath, copyPath);
            System.out.println("El archivo se ha copiado con éxito.");
        } catch (IOException e) {
            System.out.println("Ocurrió un error al intentar copiar el archivo: " + e.getMessage());
        }
    }
}
