package com.boajp.vista;

import com.boajp.modelo.EquipoEntidad;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;

public class clasificacion {
    private JPanel pClasificacion;

    private static DefaultTableModel model;
    private static JTable table;

    public clasificacion() {

        String[] columnas = {"Posicion","Logo","Equipo", "Puntos", "Goles"};
        model = new DefaultTableModel(columnas, 0);
        table = new JTable(model);


        File xmlFile = new File("src/main/java/com/boajp/xml/kings_league_clasificacion.xml");

        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);

            doc.getDocumentElement().normalize();

            NodeList nodeList = doc.getElementsByTagName("equipo");


            for (int i = 0; i < nodeList.getLength(); i++) {
                Element equipo = (Element) nodeList.item(i);
                String logo = equipo.getElementsByTagName("logo").item(0).getTextContent();
                String nombre = equipo.getElementsByTagName("nombre").item(0).getTextContent();
                String puntos = equipo.getElementsByTagName("puntos").item(0).getTextContent();
                String goles = equipo.getElementsByTagName("suma_goles").item(0).getTextContent();

                model.addRow(new Object[] {i+1,logo,nombre, puntos, goles});
            }
            pClasificacion.add(new JScrollPane(table));
        } catch (Exception e) {
            e.printStackTrace();
        }



    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("clasificacion");
        frame.setContentPane(new clasificacion().pClasificacion);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public JPanel getpClasificacion() {
        return pClasificacion;
    }
}
