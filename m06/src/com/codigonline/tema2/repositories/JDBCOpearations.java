package com.codigonline.tema2.repositories;

import java.sql.*;

public abstract class JDBCOpearations {

    protected static Statement crearSentencia(Connection connection) {
        try {
            return connection.createStatement();
        } catch (SQLException ex) {
            System.err.println("No se ha podido crear la sentencia");
            System.err.println(ex.getMessage());
            System.exit(-1);
        }
        return null;
    }

    protected static PreparedStatement crearSentencia(Connection connection, String query){
        try {
            return connection.prepareStatement(query);
        } catch (SQLException ex) {
            System.err.println("No se ha podido crear la sentencia");
            System.err.println(ex.getMessage());
            System.exit(-1);
        }
        return null;
    }
    public static void crearTabla(Connection con, String query) {
        Statement sentencia = crearSentencia(con);
        try {
            sentencia.execute(query);
        } catch (SQLException ex) {
            System.err.println("No se ha podido ejecutar la consulta: " + query);
            System.err.println(ex.getMessage());
            System.exit(-2);
        }
    }

}
