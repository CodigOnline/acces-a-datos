package com.codigonline.tema2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class H2Connection {
    private H2Connection(){}
    public static Connection newInstance(String url, String username, String password){

        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException ex) {
            System.err.println("No se ha podido estblecer connexión con el servidor");
            System.err.println(ex.getMessage());
            System.exit(-1);
        }
        return connection;
    }
}
