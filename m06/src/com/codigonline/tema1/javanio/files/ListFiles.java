package com.codigonline.tema1.javanio.files;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ListFiles {

    public static void main(String[] args) {
        Path path = Path.of("ficheros");
        listarArchivos(path);
    }

    private static void listarArchivos(Path path) {
        try {
            Stream<Path> stream = Files.list(path);
            ArrayList<Path> lista = (ArrayList<Path>) stream.collect(Collectors.toList());

            lista.forEach(item->{
                if (item.toFile().isDirectory()){
                    listarArchivos(Path.of(item.toString()));
                }
                else{
                    System.out.println(item.toString());
                }
            });

/*            for(Path item : lista){
                System.out.println(item);
            }

            for (int i = 0; i < lista.size(); i++) {
                System.out.println(lista.get(i));
            }*/

        } catch (IOException ex) {
            System.err.println("No se ha podido recuperar la lista del path: " + path.toString());
            System.err.println(ex.getMessage());
            System.exit(-1);
        }
    }
}
