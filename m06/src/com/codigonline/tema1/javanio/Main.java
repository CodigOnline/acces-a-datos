package com.codigonline.tema1.javanio;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

public class Main {
    public static void main(String[] args) {
        Path path = Path.of("ficheros/nio/aleatorio2.txt");
        //Path path = Path.of("ficheros","aleatorio.txt");
        System.out.println(path.normalize().toString());
        System.out.println(path.toAbsolutePath());

        path.toFile().getParentFile().mkdir();
        File file = path.toFile();
        try {
            file.createNewFile();
        }catch (IOException ex){
            System.out.println("Error al crear el fichero en el path: "+path.toString());
        }
    }
}
