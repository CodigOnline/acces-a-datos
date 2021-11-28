package com.codigonline.hb.entities;

import java.util.Set;

public class Empresa {
    private int id;
    private String nombre;
    private String direccion;
    private Set<Persona> personas; //el set no admite repeticiones

    public Empresa() {
    }

    public Empresa(String nombre, String direccion) {
        this.nombre = nombre;
        this.direccion = direccion;
    }

    public Empresa(String nombre, String direccion, Set<Persona> personas) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.personas = personas;
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

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Set<Persona> getPersonas() {
        return personas;
    }

    public void setPersonas(Set<Persona> personas) {
        this.personas = personas;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Empresa:  id: ")
                .append(id)
                .append(", nombre: ")
                .append(nombre)
                .append(", dirección: ")
                .append(direccion)
                .append(",personas:\n");
        if (personas != null)
            personas.forEach(p -> builder.append("\t").append(p.getNombre()));

        return builder.toString();
    }
}
