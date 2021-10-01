package com.codigonline.tema1.xml.XMLDOM;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

public class Main {
    public static void main(String[] args) {

        // leerXML();
        escribirXML();

    }

    private static void leerXML() {
        Path path = Path.of("ficheros/ms.xml");
        File xml = path.toFile();
        /*PRIMEROS PASOS DE CONFIGURACIÓN*/
        DocumentBuilder builder = createBuilder();
        Document document = null;
        try {
            document = builder.parse(xml);
        } catch (IOException | SAXException ex) {
            System.err.println("Error al crear el Document");
            System.err.println(ex.getMessage());
            System.exit(-2);
        }

        /*TRATAR EL FICHERO*/
        NodeList listaInicial = document.getElementsByTagName("Tests").item(0).getChildNodes();
        switchElement(listaInicial);
    }

    private static void switchElement(NodeList list) {
        for (int i = 0; i < list.getLength(); i++) {
            Node node = list.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                switch (node.getNodeName()) {
                    case "Test":
                        String id = node.getAttributes().getNamedItem("TestId").getNodeValue();
                        String type = node.getAttributes().getNamedItem("TestType").getNodeValue();
                        System.out.print(node.getNodeName() + "\t-\t" + id + "\t-\t" + type);
                        System.out.println();
                        NodeList listaHijos = node.getChildNodes();
                        switchElement(listaHijos);
                        break;
                    case "Name":
                    case "CommandLine":
                        System.out.println("\t" + node.getNodeName() + " -> " + node.getTextContent());
                        break;
                    case "Input":
                        System.out.print("\t\t" + node.getNodeName() + ":" + node.getTextContent() + " -> ");
                        break;
                    case "Output":
                        System.out.println(node.getNodeName() + ":" + node.getTextContent());
                        break;
                }
            }
        }
    }

    private static void escribirXML() {
        DocumentBuilder builder = createBuilder();
        DOMImplementation implementation = builder.getDOMImplementation();
        Document document = implementation.createDocument(null, null, null);
        document.setXmlVersion("1.0");
        document.setXmlStandalone(true);

        Element alumnos = document.createElement("Alumnos");
        document.appendChild(alumnos);

        Element alumno = document.createElement("Alumno");
        alumno.setAttribute("nombre", "Alvaro");
        alumno.setAttribute("edad", "28");
        Element direccion = document.createElement("direccion");
        direccion.setTextContent("C/Falsa 1234");
        Element telefono = document.createElement("telefono");
        telefono.setTextContent("987654321");
        alumno.appendChild(direccion);
        alumno.appendChild(telefono);
        alumnos.appendChild(alumno);

        Source origen = new DOMSource(document);
        Result result = new StreamResult(new File("ficheros/alumnos.xml"));
        Transformer transformer = null;
        try {
            transformer = TransformerFactory.newInstance().newTransformer();
        } catch (TransformerConfigurationException ex) {
            System.err.println("Error al crear el Transfomer");
            System.out.println(ex.getMessage());
            System.exit(-3);
        }
        try {
            transformer.transform(origen, result);
        } catch (TransformerException ex) {
            System.err.println("Error al transformar el origen en el destino");
            System.out.println(ex.getMessage());
            System.exit(-3);
        }

    }

    private static DocumentBuilder createBuilder(){
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;
        try {
            builder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException ex) {
            System.err.println("Error al crear el DocumentBuilder");
            System.err.println(ex.getMessage());
            System.exit(-1);
        }
        return builder;
    }
}

/*<alumnos>
<alumno nombre="Alvaro" edad="28">
<direccion>C/Falsa 1234</direccion>
<telefono>987654321</telefono>
</alumno>
</alumnos>*/
