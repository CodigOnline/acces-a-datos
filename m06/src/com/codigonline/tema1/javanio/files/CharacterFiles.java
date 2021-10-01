package com.codigonline.tema1.javanio.files;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class CharacterFiles {
    public static void main(String[] args) {
        Path path = Path.of("ficheros/fichero_caracter.txt");
        escribir(path);
        leer(path);
    }
    private static void escribir(Path path){
        try {
            Files.writeString(path, "Hola mundo1!!\n");
            Files.writeString(path, "Hola mundo2!!\n", StandardOpenOption.APPEND);//INDICARLE QUE DESEAMOS ESCRIBIR A CONTINUACIÓN DEL FICHERO
            Files.writeString(path, "Hola mundo3!!\n", StandardOpenOption.APPEND);
            Files.writeString(path, "Hola mundo4!!\n", StandardOpenOption.APPEND);
        }catch (IOException ex){
            System.err.println("Error al escribir en el fichero: "+path.toString());
            System.err.println(ex.getMessage());
            System.exit(-1);
        }
        System.out.println("texto escrito correctamente");
    }
    private static void leer(Path path){
        try {
           List<String> textos = Files.readAllLines(path);
           for (String texto:textos){
               System.out.println(texto);
           }
        }catch (IOException ex){
            System.err.println("Error al leer del fichero: "+path.toString());
            System.err.println(ex.getMessage());
            System.exit(-1);
        }
        System.out.println("texto leído correctamente");
    }
}
