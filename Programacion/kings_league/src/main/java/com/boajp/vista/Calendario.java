

package com.boajp.vista;

import javax.swing.*;

import javax.swing.table.DefaultTableModel;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;


import java.awt.*;
import java.io.File;
import java.io.IOException;


public class Calendario {
    private JPanel pCalendario;

    public Calendario(){
        try {

            //para que las tablas se coloquen una debajo de otra
                GridLayout gridLayout = new GridLayout(0,1);
                pCalendario.setLayout(gridLayout);

            //Leer archivo XML
                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                DocumentBuilder builder = factory.newDocumentBuilder();
                Document document = builder.parse(new File("src/main/java/com/boajp/xml/Calendario_kings_league.xml"));

            // Obtener la lista de temporadas, splits y jornadas del documento
                NodeList temporadaList = document.getElementsByTagName("temporada");
                Element temporadaElement = (Element) temporadaList.item(0);

                NodeList splitList = temporadaElement.getElementsByTagName("split");
                Element splitElement = (Element) splitList.item(0);

                NodeList jornadaList = splitElement.getElementsByTagName("jornada");


            Color azul = new Color(36,147,187);




            // Recorrer la lista de jornadas y crear un JTable para cada una
            for (int i = 0; i < jornadaList.getLength(); i++) {
                //obtener el elemento correspondiente a la jornada actual
                Element jornadaElement = (Element) jornadaList.item(i);

                // Obtener la fecha de la jornada
                String fechaJornada = jornadaElement.getElementsByTagName("fecha").item(0).getTextContent();

                // Crear el JLabel con el texto "Jornada" y el número y fecha de la jornada
                JLabel label = new JLabel("Jornada " + (i+1) + " - " + fechaJornada);
                label.setForeground(azul);;
                label.setFont(new Font("DialogInput",Font.BOLD,16));
                pCalendario.add(label);

                // Crear un JTable para mostrar los partidos de la jornada
                String[] columnas = { "Equipo local", "Logo", "Hora" , "logo","Equipo visitante"};
                DefaultTableModel modelo = new DefaultTableModel(columnas, 0);
                JTable tabla = new JTable(modelo);

                // Agregar los partidos a la tabla
                NodeList partidoList = jornadaElement.getElementsByTagName("partido");


                //recorrer lista de partidos y agregarlos a la tabla
                for (int j = 0; j < partidoList.getLength(); j++) {
                    Element partidoElement = (Element) partidoList.item(j);
                    String horaPartido = partidoElement.getElementsByTagName("hora").item(0).getTextContent();

                    Element equipoLocalElement = (Element) partidoElement.getElementsByTagName("equipo").item(0);
                    String nombreEquipoLocal = equipoLocalElement.getElementsByTagName("nombre").item(0).getTextContent();
                    String logoEquipoLocal = equipoLocalElement.getElementsByTagName("logo").item(0).getTextContent();

                    Element equipoVisitanteElement = (Element) partidoElement.getElementsByTagName("equipo").item(1);
                    String nombreEquipoVisitante = equipoVisitanteElement.getElementsByTagName("nombre").item(0).getTextContent();
                    String logoEquipoVisitante = equipoVisitanteElement.getElementsByTagName("logo").item(0).getTextContent();

                    Object[] fila = { nombreEquipoLocal,logoEquipoLocal,horaPartido,logoEquipoVisitante,nombreEquipoVisitante};
                    modelo.addRow(fila);
                    tabla.setModel(modelo);
                    //cambiar altura fila de la tabla
                    tabla.setRowHeight(40);

                }

                //ocultar cabecera de la tabla
                    tabla.setTableHeader(null);


                // Agregar el JTable al JPanel
                pCalendario.add(label);
                pCalendario.add(tabla);

            }

            // Crear un JFrame y agregar el JPanel
            JFrame frame = new JFrame("Calendario");
            frame.add(pCalendario);
            frame.pack();
            frame.setVisible(true);

        // Agregar un JScrollPane al JFrame para que tenga scroll
            JScrollPane scrollPane = new JScrollPane(pCalendario);
            frame.add(scrollPane);



        // Mostrar el JFrame
            frame.setVisible(true);
        }

        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        new Calendario();
    }
}

