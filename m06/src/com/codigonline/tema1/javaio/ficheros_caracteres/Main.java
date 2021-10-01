package com.codigonline.tema1.javaio.ficheros_caracteres;

import java.io.*;

public class Main {
    public static void main(String[] args) {
        File file = new File("ficheros/caracteres.txt");
        escribir(file);
        leer(file);
    }

    private static void escribir(File file) {
        //1 FileWriter
        //2 Escribir
        //3 Cerrar
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(file); //INICIALIZAMOS
        } catch (IOException ex) {
            System.err.println("Error: Error con el FileWriter");
            System.err.println(ex.getMessage());
            System.exit(-1);
        }
        try {
            fileWriter.write("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Etiam viverra erat eu suscipit congue. Sed hendrerit sit amet nisl ac posuere. Donec lacus sapien, volutpat nec malesuada quis, interdum dictum turpis. Curabitur non orci finibus, sodales est ac, facilisis ligula. Nunc eget imperdiet dolor, nec laoreet lacus. Aenean convallis justo eu tortor consectetur, sit amet luctus ipsum aliquam. Suspendisse lorem augue, interdum in laoreet maximus, tincidunt et dui. Donec aliquet est quam, a eleifend justo lobortis vitae. Aenean sed bibendum turpis. Sed lacus arcu, volutpat in hendrerit eu, dapibus venenatis odio. Mauris pretium lacinia libero. Quisque dignissim mauris quis elementum mattis. In arcu sapien, suscipit eget gravida ac, condimentum et ex.\n" +
                    "\n" +
                    "Orci varius natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Pellentesque bibendum a eros at placerat. Quisque vitae enim ligula. Proin viverra ullamcorper velit, eu lacinia magna sagittis ut. Pellentesque ut massa libero. Duis porttitor vehicula dolor non blandit. Donec dictum porttitor venenatis. Maecenas iaculis gravida ex vitae bibendum. Quisque ut interdum augue. Vestibulum interdum fringilla risus id efficitur. Vivamus sed enim rutrum, ornare odio ac, imperdiet arcu. Proin felis ex, bibendum sit amet neque sit amet, porta gravida nulla. Mauris ornare quam in commodo iaculis.\n" +
                    "\n" +
                    "Aenean semper massa vel ligula efficitur maximus. Duis lobortis lacinia lorem at venenatis. Mauris felis dui, porta nec gravida eget, sodales vitae massa. Integer scelerisque, mi at semper hendrerit, mi risus vehicula libero, ut placerat magna dolor eu turpis. Integer eget maximus sapien. In libero massa, tincidunt fermentum neque nec, commodo posuere orci. Fusce quis pharetra massa. Cras et felis porttitor, porttitor purus eu, aliquet neque. Aliquam leo est, tempus quis turpis a, cursus congue diam. Cras efficitur tincidunt tellus ac tristique. Aenean purus mi, aliquam quis euismod ac, tristique nec magna. Vivamus pretium nisi vitae luctus pretium.\n" +
                    "\n" +
                    "Suspendisse non porttitor lacus. Morbi sit amet risus condimentum, sollicitudin sapien id, placerat tellus. Duis vestibulum imperdiet libero, sit amet aliquet eros. Suspendisse mollis accumsan dui, eget lacinia justo tristique et. In imperdiet vehicula tellus, eget eleifend metus vehicula quis. Nullam blandit nunc vel eleifend tincidunt. Curabitur accumsan elit convallis, sodales ipsum pretium, convallis est. Integer semper sodales tristique. Vestibulum volutpat quam laoreet ante imperdiet, ultricies cursus nunc volutpat. In at mi consectetur, rutrum enim sed, pellentesque arcu. Donec luctus tempor lacinia.\n" +
                    "\n" +
                    "Donec fringilla malesuada sapien cursus gravida. Pellentesque faucibus viverra sapien, ut consectetur sem tristique in. Curabitur feugiat, tortor ac congue elementum, arcu mauris laoreet eros, sed porttitor erat mi in leo. Phasellus tincidunt viverra sagittis. Etiam sed nibh volutpat, vestibulum ipsum in, semper ex. Pellentesque accumsan velit metus, in tincidunt ligula aliquet ut. Maecenas varius risus eget ligula interdum, quis ullamcorper odio tempor. Nunc vel placerat nisl. Donec auctor ac magna quis semper.\n" +
                    "\n" +
                    "Donec suscipit nisi porta, varius ipsum sed, rhoncus dui. Etiam felis ligula, auctor at orci quis, tempor luctus sem. Suspendisse nec odio tellus. Etiam et laoreet erat. Suspendisse placerat justo mi, non lacinia ante faucibus a. Ut fringilla porta orci eget suscipit. Suspendisse ac augue sit amet turpis");
        } catch (IOException ex) {
            System.err.println("Error: Error con el FileWriter");
            System.err.println(ex.getMessage());
            System.exit(-2);
        }
        try {
            fileWriter.close();
        } catch (IOException ex) {
            System.err.println("Error: Error con el FileWriter");
            System.err.println(ex.getMessage());
            System.exit(-3);
        }
    }

    private static void leer(File file){
        FileReader fileReader = null;
        try{
            fileReader = new FileReader(file);
        }catch (FileNotFoundException ex){
            System.err.println("No se ha podido crear el FileReader");
            System.err.println(ex.getMessage());
            System.exit(-1);
        }
        try {
            int read;
            while ((read = fileReader.read()) != -1) {
                System.out.print((char)read);
            }
            System.out.println();
        }catch (IOException ex){
            System.err.println("Error al leer desde el FileReader");
            System.err.println(ex.getMessage());
            System.exit(-2);
        }
        try {
            fileReader.close();
        }catch (IOException ex){
            System.err.println("Error al cerrar el fileReader");
            System.err.println(ex.getMessage());
            System.exit(-3);
        }
    }
}
