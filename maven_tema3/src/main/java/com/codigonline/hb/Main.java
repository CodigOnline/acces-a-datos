package com.codigonline.hb;

import com.codigonline.hb.entities.Empresa;
import com.codigonline.hb.entities.Persona;
import com.codigonline.hb.repository.EmpresaRepository;
import com.codigonline.hb.repository.PersonaRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Scanner;

public class Main {
    private static Scanner teclado;
    private static PersonaRepository personaRepository;
    private static EmpresaRepository empresaRepository;

    public static void main(String[] args) {

        System.out.println("Iniciando configuración hibernate....");
        final StandardServiceRegistry registro = new StandardServiceRegistryBuilder().configure().build();
        final SessionFactory factory = new MetadataSources(registro).buildMetadata().buildSessionFactory();
        System.out.println("Abriendo conexión a BD....");
        final Session session = factory.openSession();
        System.out.println("Conexión abierta a BD....");


        personaRepository = new PersonaRepository(session);
        //CREAMOS LA EMPRESA
        empresaRepository = new EmpresaRepository(session);

        teclado = new Scanner(System.in);
        //mostarMenu();
        /*Empresa empresa = new Empresa("CodigOnline.com","C/Falsa 1234");



        //CREAMOS LA PERSONA
        Calendar calendar = new Calendar.Builder().build();
        calendar.set(1993, Calendar.NOVEMBER, 25);
        for (int i = 0; i < 10; i++) {
            Persona persona = new Persona("Persona " + (i + 1), 27, calendar.getTime(),empresa);
            session.save(persona);
        }

        Persona persona = new Persona("Juan del Campo", 234, Persona.newDate(25, 11, 1800));
        personaRepository.save(persona);
        persona.setEdad(36);
        personaRepository.update(persona);
        personaRepository.deleteById(persona.getId());

        personaRepository.findAll().forEach(p -> System.out.println(p));
*/

        System.out.println("Cerrando conexión a BD....");
        session.close();
        System.out.println("Conexión terminada a BD....");

    }

    private static void mostarMenu() {
        int seleccion;
        do {
            System.out.println("Por favor, selecciona una de las siguientes opciones");
            System.out.println("1. Crear Empresa");
            System.out.println("2. Crear Persona");
            System.out.println("3. Asignar empresa a persona");
            System.out.println("4. Mostrar todas las empresas");
            System.out.println("5. Mostrar todas las personas");
            System.out.println("0. Salir");
            seleccion = getNumero();
            switch (seleccion) {
                case 0:
                    System.out.println("Gracias por utilizar esta herramienta");
                    break;
                case 1:
                    System.out.println("Has seleccionado crear Empresa");
                    crearEmpresa();
                    break;
                case 2:
                    System.out.println("Has seleccionado crear Persona");
                    crearPersona();
                    break;
                case 3:
                    System.out.println("Selecciona la empresa que deseas:");
                    mostrarEmpresas();
                    int empresa = getNumero();
                    System.out.println("Selecciona la persona que deseas:");
                    mostrarPersonas();
                    int persona = getNumero();
                    //INSERT EN LA COLUMNA EMPRESA DENTRO DE PERSONA
                    Persona personaDB = personaRepository.findOneById(persona);
                    Empresa empresaDB = empresaRepository.findOneById(empresa);
                    personaDB.setEmpresa(empresaDB);
                    personaRepository.update(personaDB);
                    break;
                case 4:
                    System.out.println("Has seleccionado mostrar empresas");
                    mostrarEmpresas();
                    break;
                case 5:
                    System.out.println("Has seleccionado mostrar personas");
                    mostrarPersonas();
                    break;
                default:
                    System.out.println("Opción pulsada incorrecta");
            }
        } while (seleccion != 0);
    }

    private static void mostrarPersonas() {
       /* personaRepository.findAll().forEach(persona -> {
            System.out.println(persona);
        });*/
        for (Persona persona : personaRepository.findAll()) {
            System.out.println(persona);
        }
    }

    private static void mostrarEmpresas() {
        empresaRepository.findAll().forEach(empresa -> {
            System.out.println(empresa);
        });
    }

    private static void crearPersona() {
        System.out.println("Introduce el nombre de la persona");
        String nombre = teclado.nextLine();
        System.out.println("Introduce la edad");
        int edad = getNumero();
        System.out.println("Introduce el año de nacimiento");
        int year = getNumero();
        System.out.println("Introduce el mes de nacimiento");
        int mes = getNumero();
        System.out.println("Introduce el dia de nacimiento");
        int dia = getNumero();
        Persona persona = new Persona(nombre, edad, Persona.newDate(dia, mes, year));
        personaRepository.save(persona);
    }

    private static void crearEmpresa() {
        System.out.println("Introduce el nombre de la empresa:");
        String nombre = teclado.nextLine();
        System.out.println("Introduce la direccion de la empresa:");
        String direccion = teclado.nextLine();
        Empresa empresa = new Empresa(nombre, direccion);
        empresaRepository.save(empresa);
    }

    private static int getNumero() {
        int num = teclado.nextInt();
        teclado.nextLine();
        return num;
    }
}
