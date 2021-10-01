package com.codigonline.tema1.javanio.files;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class BinaryFiles {
    public static void main(String[] args) {
        Path path = Path.of("ficheros/fichero_binario");
        escribir(path);
        leer(path);
    }

    private static void escribir(Path path) {
        Curso curso = new Curso(1, "´´lkjbhvgcfxdzxfcghjklñ´´oíñuilyktjrtxcfghvjbknlñ", 99);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(); //CREAMOS UN OBJETO PARA ESCRIBIR DATOS EN LA RAM
        ObjectOutputStream objectOutputStream = null;
        try {
            objectOutputStream = new ObjectOutputStream(byteArrayOutputStream); //CREAMOS UN OBJETO PARA ESCRIBIR OBJETOS EN EL LUGAR INDICADO.
            //EN ESTE CASO ESCRIBIREMOS EN EL BYTEARRAYOUTPUTSTREAM --> RAM
        } catch (IOException ex) {
            System.err.println("Error al crear el object outputStream");
            System.err.println(ex.getMessage());
            System.exit(-1);
        }
        try {
            objectOutputStream.writeObject(curso);
        } catch (IOException ex) {
            System.err.println("Error al escribir en la memoria RAM");
            System.err.println(ex.getMessage());
            System.exit(-2);
        }
        try {
            Files.write(path, byteArrayOutputStream.toByteArray());
        } catch (IOException ex) {
            System.err.println("Error al escribir en el fichero");
            System.err.println(ex.getMessage());
            System.exit(-2);
        }
        System.out.println("Fichero escrito correctamente");

    }

    private static void leer(Path path) {
        byte[] bytesFichero = new byte[]{};
        try {
            bytesFichero = Files.readAllBytes(path); //LEEMOS FICHERO
        } catch (IOException ex) {
            System.err.println("Error al leeer los bytes del fichero: " + path.toString());
            System.err.println(ex.getMessage());
            System.exit(-1);
        }
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytesFichero); //GUARDAMOS EN LA RAM
        ObjectInputStream objectInputStream = null;
        try {
            objectInputStream = new ObjectInputStream(byteArrayInputStream);
        } catch (IOException ex) {
            System.err.println("Error al crear el objectOutputStream");
            System.err.println(ex.getMessage());
            System.exit(-2);
        }
        try {
            Curso curso = (Curso) objectInputStream.readObject();
            System.out.println(curso.toString());
        }catch (IOException| ClassNotFoundException ex){
            System.err.println("Error al leer el objeto de la memoria ram y convertirlo en Curso");
            System.err.println(ex.getMessage());
            System.exit(-3);
        }

    }
}
