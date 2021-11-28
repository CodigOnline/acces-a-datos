package com.codigonline.hb.entities;

import java.util.Calendar;
import java.util.Date;
import java.util.Set;

public class Persona {
    private int id;
    private String nombre;
    private int edad;
    private Date fechaNacimiento;
    private Empresa empresa;
    private Set<Deporte> deportes;

    public Persona() {
    }

    public Persona(int id, String nombre, int edad, Date fechaNacimiento) {
        this(nombre, edad, fechaNacimiento);
        this.id = id;
    }

    public Persona(String nombre, int edad, Date fechaNacimiento) {
        this.nombre = nombre;
        this.edad = edad;
        this.fechaNacimiento = fechaNacimiento;
    }

    public Persona(String nombre, int edad, Date fechaNacimiento, Empresa empresa) {
        this(nombre, edad, fechaNacimiento);
        this.empresa = empresa;
    }

    public static Date newDate(int dia, int mes, int anio) {
        Calendar calendar = new Calendar.Builder().build();
        calendar.set(anio, (mes - 1), dia);
        return calendar.getTime();
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

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public String getFechaNacimientoString() {
        Calendar calendar = new Calendar.Builder().build();
        calendar.setTime(fechaNacimiento);
        return calendar.get(Calendar.DATE) + "/" + calendar.get(Calendar.MONTH)+1 + "/" + calendar.get(Calendar.YEAR);
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public Set<Deporte> getDeportes() {
        return deportes;
    }

    public void setDeportes(Set<Deporte> deportes) {
        this.deportes = deportes;
    }

    @Override
    public String toString() {
        return "Persona\t: " +
                "id: " + id +
                ", nombre: '" + nombre + '\'' +
                ", edad: " + edad +
                ", fecha:" + getFechaNacimientoString();
    }
}
