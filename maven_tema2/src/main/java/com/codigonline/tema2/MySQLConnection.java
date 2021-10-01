package com.codigonline.tema2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnection {
    private static String driver = "com.mysql.cj.jdbc.Driver";

    private MySQLConnection() {
    }

    public static Connection newInstance(String url, String username, String password) {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException ex) {
            System.err.println("No se ha podido encontrar el Driver: " + driver);
            System.err.println(ex.getMessage());
            System.exit(-1);
        }
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
