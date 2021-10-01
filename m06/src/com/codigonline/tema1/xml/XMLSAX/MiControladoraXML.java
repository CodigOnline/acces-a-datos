package com.codigonline.tema1.xml.XMLSAX;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class MiControladoraXML extends DefaultHandler {
    @Override
    public void startDocument() throws SAXException {
        super.startDocument();
        System.out.println("Iniciando documento...");
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes);
        switch (qName) {
            case "Tests":
                System.out.println("Listado de test para ejecutar:");
                break;
            case "Test":
                String id = attributes.getValue("TestId");//0
                String tipo = attributes.getValue(1);
                System.out.println("\t Test: " + id + ", del tipo: " + tipo);
                //RECUPERAR LOS ATRIBUTOS
                break;
            case "Name":
                System.out.print("\t\tNombre del Test :\t");
                break;
            case "CommandLine":
                System.out.print("\t\tComando para ejecutar el test: \t");
                break;
            case "Input":
                System.out.print("\t\tDatos de entrada: \t");
                break;
            case "Output":
                System.out.print("\t\tDatos de salida: \t");
                break;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        super.characters(ch, start, length);
        String texto = new String(ch, start, length).trim();
        if (texto.length() > 0)
            System.out.println(texto);
    }
}
