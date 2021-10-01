package com.codigonline.tema2.repositories;

import com.codigonline.tema2.entities.Direccion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DireccionesRepository implements Repository<Direccion> {
    Connection connection;

    public DireccionesRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Direccion> findAll() {
        Statement estado = JDBCOpearations.crearSentencia(connection);
        String query = "SELECT * FROM direcciones";
        ArrayList<Direccion> direcciones = new ArrayList<>();
        try {
            estado.executeQuery(query);
        } catch (SQLException ex) {
            System.err.println("No se han podido recuperar los datos: " + query);
            System.err.println(ex.getMessage());
            return direcciones;
        }
        try {
            ResultSet resultSet = estado.getResultSet();

            while (!resultSet.isLast()) {
                resultSet.next();
                Direccion direccion = new Direccion(
                        resultSet.getInt(1),
                        resultSet.getInt(2),
                        resultSet.getString(3)
                );
                direcciones.add(direccion);
            }
            return direcciones;
        } catch (SQLException ex) {
            System.err.println("No se han podido recuperar los datos");
            System.err.println(ex.getMessage());
        }
        return direcciones;
    }

    @Override
    public Direccion findOneById(int id) {
        String query = "SELECT * FROM direcciones where id = ?";
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
            return new Direccion(
                    resultSet.getInt(1),
                    resultSet.getInt(2),
                    resultSet.getString(3)
            );
        } catch (SQLException ex) {
            System.err.println("No se han podido recuperar los datos");
            System.err.println(ex.getMessage());
        }
        return null;
    }

    @Override
    public Direccion save(Direccion direccion) {
        String query = "INSERT INTO direcciones (persona_id,direccion) VALUES(?,?)";
        PreparedStatement estado = null;
        try {
            estado = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        } catch (SQLException ex) {
            System.err.println("No se ha podido crear la sentencia");
            System.err.println(ex.getMessage());
            System.exit(-1);
        }
        try {
            estado.setInt(1, direccion.getPersonaId());
            estado.setString(2, direccion.getDireccion());
            estado.executeUpdate();
            ResultSet key = estado.getGeneratedKeys();
            key.next();
            direccion.setId(key.getInt(1));
            System.out.println("Datos insertados correctamente");
            return direccion;
        } catch (SQLException ex) {
            System.err.println("Error al insertar datos");
            System.err.println(ex.getMessage());
        }
        return null;
    }

    @Override
    public void updateById(int id, Direccion direccion) {
        String query = "update direcciones set persona_id = ?, direccion=? where id=?";
        PreparedStatement estado = JDBCOpearations.crearSentencia(connection, query);
        try {
            estado.setInt(1, direccion.getId());
            estado.setString(2, direccion.getDireccion());
            estado.setInt(3, id);
            estado.executeUpdate();
            System.out.println("Datos actualizados correctamente");
        } catch (SQLException ex) {
            System.err.println("Error al actualizar datos");
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void deleteById(int id) {
        String query = "delete from direcciones where id =?";
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
