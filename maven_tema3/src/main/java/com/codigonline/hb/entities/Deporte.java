package com.codigonline.hb.entities;

import java.util.Set;

public class Deporte {
    private int id;
    private String nombre;
    private boolean aireLibre;
    private Set<Persona> personas;
    public Deporte() {
    }

    public Deporte(String nombre, boolean aireLibre) {
        this.nombre = nombre;
        this.aireLibre = aireLibre;
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

    public boolean isAireLibre() {
        return aireLibre;
    }

    public void setAireLibre(boolean aireLibre) {
        this.aireLibre = aireLibre;
    }

    public Set<Persona> getPersonas() {
        return personas;
    }

    public void setPersonas(Set<Persona> personas) {
        this.personas = personas;
    }
}
