package com.codigonline.tema1.javaio.ficheros_aleatorios;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.charset.StandardCharsets;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        File file = new File("ficheros/aleatorio.txt");

        //READ
        //READ-WRITE
        RandomAccessFile randomAccessFile = null;
        try {
            randomAccessFile = new RandomAccessFile(file, "rw");
        } catch (FileNotFoundException ex) {
            System.err.println("No se ha encontrado el fichero: " + file.getName());
            System.err.println(ex.getMessage());
            System.exit(-1);
        }
        //ESCRIBIR
        pointer(randomAccessFile);

        escribir(randomAccessFile, "Hola Mundo!!");

        pointer(randomAccessFile);
        seek(randomAccessFile, 0);
        pointer(randomAccessFile);

        leer(randomAccessFile);

        pointer(randomAccessFile);
        seek(randomAccessFile, 6);
        pointer(randomAccessFile);
        escribir(randomAccessFile, "123");
        seek(randomAccessFile, 0);
        pointer(randomAccessFile);
        leer(randomAccessFile);


    }

    private static void escribir(RandomAccessFile randomAccessFile, String texto) {
        System.out.println("Escribiendo:\n\t" + texto);
        try {
            randomAccessFile.writeBytes(texto);
        } catch (IOException ex) {
            System.err.println("No se ha podido escribir en el fichero");
            System.err.println(ex.getMessage());
            System.exit(-2);
        }
    }

    private static void leer(RandomAccessFile randomAccessFile) {
        try {
            String leido;
            while ((leido = randomAccessFile.readLine()) != null) {
                System.out.println(leido);
            }
        } catch (IOException ex) {
            System.err.println("No se ha podido leer del fichero");
            System.err.println(ex.getMessage());
            System.exit(-2);
        }
    }

    private static void pointer(RandomAccessFile randomAccessFile) {
        try {
            System.out.println("Pos: " + randomAccessFile.getFilePointer());
        } catch (IOException ex) {
            System.err.println("No se ha podido comprobar la ubicación del puntero.");
        }
    }

    private static void seek(RandomAccessFile randomAccessFile, int pos) {
        try {
            randomAccessFile.seek(pos);
        } catch (IOException ex) {
            System.err.println("No se ha podido establecer la ubicación del puntero.");
        }
    }
}
