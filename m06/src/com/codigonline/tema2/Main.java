package com.codigonline.tema2;

import com.codigonline.tema2.entities.Persona;
import com.codigonline.tema2.repositories.JDBCOpearations;
import com.codigonline.tema2.repositories.PersonaRepository;

import java.nio.file.Path;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        String urlMysql = "jdbc:mysql://localhost/m06";
        String urlH2 = "jdbc:h2:" + Path.of("m06").toAbsolutePath().toString();
        String username = "root";
        String password = "secret";

        System.out.println("Iniciando conexión con el servidor");
        //Connection connection = MySQLConnection.newInstance(urlMysql, username, password);
        Connection connection = H2Connection.newInstance(urlH2, username, password);
/*        String crearTablaPersona = "create table if not exists personas(id INTEGER PRIMARY KEY AUTO_INCREMENT, nombre VARCHAR(30) NOT NULL,password VARCHAR(30) NOT NULL, telefono INTEGER(9) NOT NULL)";
        String crearTablaDirecciones = "create table if not exists direcciones(\n" +
                "    id INTEGER PRIMARY KEY AUTO_INCREMENT,\n" +
                "    persona_id INTEGER NOT NULL,\n" +
                "    direccion VARCHAR(50) NOT NULL,\n" +
                "    FOREIGN KEY (persona_id) REFERENCES personas(id)\n" +
                ")";
        JDBCOpearations.crearTabla(connection, crearTablaPersona);
        JDBCOpearations.crearTabla(connection, crearTablaDirecciones);

        Persona persona = new Persona("Alvaro","asdfgh",98765);

        persona = repository.save(persona);
        System.out.println(persona.toString());
        persona.setPassword("esto_Es_iun__pasword");
        repository.updateById(persona.getId(),persona);
        Persona buscada = repository.findOneById(persona.getId());
        System.out.println(buscada);*/
        PersonaRepository repository = new PersonaRepository(connection);
        repository.findAllWithDirecciones().forEach(persona-> System.out.println(persona));


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
