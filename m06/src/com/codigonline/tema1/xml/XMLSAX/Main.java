package com.codigonline.tema1.xml.XMLSAX;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.nio.file.Path;

public class Main {
    public static void main(String[] args) {
        Path path = Path.of("ficheros/ms.xml");
        leerXML(path);
    }

    private static void leerXML(Path path) {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = null;
        try {
            parser = factory.newSAXParser();
        } catch (ParserConfigurationException | SAXException ex) {
            System.err.println("No se ha podido crear el nuevo SaxParser");
            System.err.println(ex.getMessage());
            System.exit(-1);
        }
        XMLReader reader = null;
        try {
            reader = parser.getXMLReader();
        } catch (SAXException ex) {
            System.err.println("Error al crear el XMLReader");
            System.err.println(ex.getMessage());
            System.exit(-2);
        }
/*            reader.setContentHandler(new DefaultHandler() {
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
            });*/
        reader.setContentHandler(new MiControladoraXML());
        try {
            reader.parse(path.toString());
        } catch (SAXException | IOException ex) {
            System.err.println("Error al parsear el documento: " + path.toString());
            System.err.println(ex.getMessage());
            System.exit(-3);
        }


    }
}
