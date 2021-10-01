package com.codigonline.tema2.repositories;

import com.codigonline.tema2.entities.Direccion;
import com.codigonline.tema2.entities.Persona;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonaRepository implements Repository<Persona> {
    Connection connection;

    public PersonaRepository(Connection connection) {
        this.connection = connection;
    }

    public List<Persona> findAllWithDirecciones(){
        Statement estado = JDBCOpearations.crearSentencia(connection);
        String query = "SELECT * FROM personas p INNER JOIN direcciones d ON p.id=d.persona_id";
        ArrayList<Persona> personas = new ArrayList<>();
        try {
            estado.executeQuery(query);
        } catch (SQLException ex) {
            System.err.println("No se han podido recuperar los datos: " + query);
            System.err.println(ex.getMessage());
            return personas;
        }
        try {
            ResultSet resultSet = estado.getResultSet();

            while (!resultSet.isLast()) {
                resultSet.next();
                Persona persona = new Persona(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getInt(4)
                );
                Direccion direccion = new Direccion(
                        resultSet.getInt(5),
                        resultSet.getInt(6),
                        resultSet.getString(7)
                );
                if(personas.contains(persona)){
                    //RECUPERAR LA PERSONA DE LA LISTA
                    //AÑADIR LA NUEVA DIRECCION
                    //ACTUALIZAR LA LISTA
                    int pos = personas.indexOf(persona);
                    Persona p = personas.get(pos);
                    p.addDireccion(direccion);
                    personas.set(pos,p);
                }
                else{
                    persona.addDireccion(direccion);
                    personas.add(persona);
                }


            }
            return personas;
        } catch (SQLException ex) {
            System.err.println("No se han podido recuperar los datos");
            System.err.println(ex.getMessage());
        }
        return personas;
    }

    @Override
    public List<Persona> findAll() {
        Statement estado = JDBCOpearations.crearSentencia(connection);
        String query = "SELECT * FROM personas";
        ArrayList<Persona> personas = new ArrayList<>();
        try {
            estado.executeQuery(query);
        } catch (SQLException ex) {
            System.err.println("No se han podido recuperar los datos: " + query);
            System.err.println(ex.getMessage());
            return personas;
        }
        try {
            ResultSet resultSet = estado.getResultSet();

            while (!resultSet.isLast()) {
                resultSet.next();
                Persona persona = new Persona(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getInt(4)
                );
                personas.add(persona);
            }
            return personas;
        } catch (SQLException ex) {
            System.err.println("No se han podido recuperar los datos");
            System.err.println(ex.getMessage());
        }
        return personas;
    }

    @Override
    public Persona findOneById(int id) {
        String query = "SELECT * FROM personas where id = ?";
        PreparedStatement estado = JDBCOpearations.crearSentencia(connection, query);
        try {
            estado.setInt(1, id);
            estado.executeQuery();
        } catch (SQLException ex) {
            System.err.println("No se han podido recuperar los datos: " + query);
            System.err.println(ex.getMessage());
            return null;
        }
        try {
            ResultSet resultSet = estado.getResultSet();
            resultSet.next();
            return new Persona(
                    resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getInt(4)
            );
        } catch (SQLException ex) {
            System.err.println("No se han podido recuperar los datos");
            System.err.println(ex.getMessage());
        }
        return null;
    }

    @Override
    public Persona save(Persona persona) {
        String query = "INSERT INTO personas (nombre,password,telefono) VALUES(?,?,?)";
        PreparedStatement estado = null;
        try {
            estado = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        } catch (SQLException ex) {
            System.err.println("No se ha podido crear la sentencia");
            System.err.println(ex.getMessage());
            System.exit(-1);
        }
        try {
            estado.setString(1, persona.getNombre());
            estado.setString(2, persona.getPassword());
            estado.setInt(3, persona.getTelefono());
            estado.executeUpdate();
            ResultSet key = estado.getGeneratedKeys();
            key.next();
            persona.setId(key.getInt(1));
            System.out.println("Datos insertados correctamente");
            return persona;
        } catch (SQLException ex) {
            System.err.println("Error al insertar datos");
            System.err.println(ex.getMessage());
        }
        return null;
    }

    @Override
    public void updateById(int id, Persona persona) {
        String query = "update personas set NOMBRE = ?, PASSWORD=?,telefono=? where id=?";
        PreparedStatement estado = JDBCOpearations.crearSentencia(connection, query);
        try {
            estado.setString(1, persona.getNombre());
            estado.setString(2, persona.getPassword());
            estado.setInt(3, persona.getTelefono());
            estado.setInt(4, id);
            estado.executeUpdate();
            System.out.println("Datos actualizados correctamente");
        } catch (SQLException ex) {
            System.err.println("Error al actualizar datos");
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void deleteById(int id) {
        String query = "delete from personas where id =?";
        PreparedStatement estado = JDBCOpearations.crearSentencia(connection, query);
        try {
            estado.setInt(1, id);
            estado.executeUpdate();
            System.out.println("Datos eliminados correctamente");
        } catch (SQLException ex) {
            System.err.println("Error al eliminar datos");
            System.err.println(ex.getMessage());
        }
    }
}
