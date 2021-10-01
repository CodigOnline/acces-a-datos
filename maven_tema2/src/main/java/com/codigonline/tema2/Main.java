package com.codigonline.tema2;

import java.nio.file.Path;
import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {

        String urlMysql = "jdbc:mysql://localhost/m06";
        String urlH2 = "jdbc:h2:" + Path.of("m06").toAbsolutePath().toString();
        String username = "root";
        String password = "secret";


        System.out.println("Iniciando conexión con el servidor");
        //Connection connection = MySQLConnection.newInstance(urlMysql, username, password);
        Connection connection = H2Connection.newInstance(urlH2, username, password);
        try {
            connection.close();
        } catch (SQLException ex) {
            System.err.println("No se ha podido cerrar la connexión con el servidor");
            System.err.println(ex.getMessage());
            System.exit(-1);
        }
        System.out.println("Finalizando conexión con el servidor");

    }
}
