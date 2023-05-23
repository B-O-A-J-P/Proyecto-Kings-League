




package com.boajp.vistas;

import javax.swing.*;

import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import com.boajp.utilidades.EstilosDeVistas;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Element;


import java.awt.*;
import java.io.File;


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

            //String home = System.getProperty("user.home");

            Document document = builder.parse(new File( "src/main/java/com/boajp/xml/CALENDARIO_JORNADAS.xml"));

            // Obtener la lista de temporadas, splits y jornadas del documento
            NodeList temporadaList = document.getElementsByTagName("temporada");
            Element temporadaElement = (Element) temporadaList.item(0);


            NodeList splitList = temporadaElement.getElementsByTagName("SPLITS_XML");
            Element splitElement = (Element) splitList.item(0);

            NodeList jornadaList = splitElement.getElementsByTagName("JORNADAS_XML");


            Color azul = new Color(0, 0, 0);




            // Recorrer la lista de jornadas y crear un JTable para cada una
            for (int i = 0; i < jornadaList.getLength(); i++) {
                //obtener el elemento correspondiente a la jornada actual
                Element jornadaElement = (Element) jornadaList.item(i);

                // Obtener la fecha de la jornada
                String fechaJornada = jornadaElement.getElementsByTagName("FECHA").item(0).getTextContent();

                // Crear el JLabel con el texto "Jornada" y el número y fecha de la jornada
                JLabel label = new JLabel("Jornada " + (i+1) + " - " + fechaJornada);
                label.setForeground(azul);;
                label.setFont(new Font("DialogInput",Font.BOLD,32));
                pCalendario.add(label);

                // Crear un JTable para mostrar los partidos de la jornada
                String[] columnas = { "Equipo local", "Logo", "Hora" , "logo","Equipo visitante"};
                DefaultTableModel modelo = new DefaultTableModel(columnas, 0);
                JTable tabla = new JTable(modelo);


                // Agregar los partidos a la tabla
                NodeList partidoList = jornadaElement.getElementsByTagName("PARTIDOS_XML");


                //recorrer lista de partidos y agregarlos a la tabla
                for (int j = 0; j < partidoList.getLength(); j++) {
                    Element partidoElement = (Element) partidoList.item(j);
                    String horaPartido = partidoElement.getElementsByTagName("HORA").item(0).getTextContent();

                    Element equipoLocalElement = (Element) partidoElement.getElementsByTagName("EQUIPO_LOCAL").item(0);
                    String nombreEquipoLocal = equipoLocalElement.getElementsByTagName("NOMBRE").item(0).getTextContent();
                    String logoEquipoLocal = equipoLocalElement.getElementsByTagName("LOGO").item(0).getTextContent();

                    Element equipoVisitanteElement = (Element) partidoElement.getElementsByTagName("EQUIPO_VISITANTE").item(0);
                    String nombreEquipoVisitante = equipoVisitanteElement.getElementsByTagName("NOMBRE").item(0).getTextContent();
                    String logoEquipoVisitante = equipoVisitanteElement.getElementsByTagName("LOGO").item(0).getTextContent();

                    Object[] fila = { nombreEquipoLocal,logoEquipoLocal,horaPartido,logoEquipoVisitante,nombreEquipoVisitante};
                    modelo.addRow(fila);
                    tabla.setModel(modelo);
                    //cambiar altura fila de la tabla
                    tabla.setRowHeight(40);

                    // Hacer que la tabla sea no editable
                    tabla.setEnabled(false);

                }


                //ocultar cabecera de la tabla
                tabla.setTableHeader(null);
                tabla.setSize(2000,2000);


                tabla.setBackground(Color.gray);

                // Ajustar el ancho de las columnas
                TableColumnModel columnModel = tabla.getColumnModel();
                for (int z = 0; z < columnModel.getColumnCount(); z++) {
                    columnModel.getColumn(z).setPreferredWidth(250);
                    tabla.setFont(new Font("DialogInput", Font.BOLD, 17));
                }

                // Centrar los datos en las celdas
                DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
                centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
                for (int x = 0; x < columnModel.getColumnCount(); x++) {
                    tabla.getColumnModel().getColumn(x).setCellRenderer(centerRenderer);
                }


                pCalendario.add(label);
                pCalendario.add(tabla);

            }
        }

        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public JPanel getpCalendario() {
        return pCalendario;
    }

    public static void main(String[] args){
        new Calendario();
    }
}





