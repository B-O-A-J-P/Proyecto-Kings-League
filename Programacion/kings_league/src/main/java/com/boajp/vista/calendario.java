package com.boajp.vista;

import javax.swing.*;

import javax.swing.table.DefaultTableModel;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;

import com.boajp.modelo.EquipoEntidad;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Unmarshaller;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


public class calendario {
    private JPanel pCalendario;

    private static DefaultTableModel model;
    private static JTable table;

    public calendario(){
        String[] columnas = {"Equipo Local","Logo Local","Hora","Logo Visitante", "Equipo Visitante"};

        
        model = new DefaultTableModel(columnas, 0);
        table = new JTable(model);


        File archivoXml = new File("src/main/java/com/boajp/xml/Calendario_kings_league.xml");


        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(archivoXml);

            doc.getDocumentElement().normalize();


            NodeList equipos = doc.getElementsByTagName("equipo");

/*
            JAXBContext jaxbContext = JAXBContext.newInstance(EquipoEntidad.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

            EquipoEntidad e= (EquipoEntidad) unmarshaller.unmarshal(archivoXml);

            ArrayList<EquipoEntidad> equi = new ArrayList<>();
*/


            for (int i = 0; i <= 11; i++) {
                for (int y = 0; i <= 6; i++) {

                        Element equipo = (Element) equipos.item(y);

                        String equipoLocal = equipo.getElementsByTagName("nombre").item(0).getTextContent();
                        String logoLocal = equipo.getElementsByTagName("logo").item(0).getTextContent();


                        String equipoVisitante = equipo.getElementsByTagName("nombre").item(1).getTextContent();
                        String logoVisitante = equipo.getElementsByTagName("logo").item(1).getTextContent();

                        model.addRow(new Object[]{equipoLocal, logoLocal, equipoVisitante, logoVisitante});

                }


            }
            pCalendario.add(new JScrollPane(table));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {
        JFrame frame = new JFrame("calendario");
        frame.setContentPane(new calendario().pCalendario);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}