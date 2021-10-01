package com.codigonline.tema2.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Persona {
    private int id;
    private String nombre;
    private String password;
    private int telefono;
    private List<Direccion> direcciones;

    public Persona(int id, String nombre, String password, int telefono) {
        this.id = id;
        this.nombre = nombre;
        this.password = password;
        this.telefono = telefono;
    }

    public Persona(String nombre, String password, int telefono) {
        this.nombre = nombre;
        this.password = password;
        this.telefono = telefono;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public List<Direccion> getDirecciones() {
        return direcciones;
    }

    public void setDirecciones(List<Direccion> direcciones) {
        if (direcciones == null) {
            direcciones = new ArrayList<>();
        }
        this.direcciones = direcciones;
    }

    public void addDireccion(Direccion direccion) {
        if (direcciones == null) {
            direcciones = new ArrayList<>();
        }
        this.direcciones.add(direccion);
    }

    @Override
    public String toString() {
        return "Persona{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", password='" + password + '\'' +
                ", telefono=" + telefono +
                ", direcciones=" + direcciones +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Persona persona = (Persona) o;
        return id == persona.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
