package com.boajp.vistas;

import com.boajp.utilidades.EstilosDeVistas;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.awt.*;
import java.io.File;


public class Clasificacion {
    private JPanel pClasificacion;

    private static DefaultTableModel model;
    private static JTable table;

    public Clasificacion() {

        String[] columnas = {"Posicion","Logo","Equipo", "Puntos", "Goles"};
        model = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Hacer todas las celdas no editables
            }
        };

        table = new JTable(model);
        table.setRowHeight(40); // Aumentar la altura de las filas

        // Ajustar el ancho de las columnas
        TableColumnModel columnModel = table.getColumnModel();
        for (int i = 0; i < columnModel.getColumnCount(); i++) {
            columnModel.getColumn(i).setPreferredWidth(250);
            table.setFont(new Font("DialogInput", Font.BOLD, 17));
        }

        //String home = System.getProperty("user.home");


        File xmlFile = new File("src/main/java/com/boajp/xml/clasificacion.xml");

        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);

            doc.getDocumentElement().normalize();

            NodeList nodeList = doc.getElementsByTagName("EQUIPO_TIPO");


            for (int i = 0; i < nodeList.getLength(); i++) {
                Element equipo = (Element) nodeList.item(i);
                String logo = equipo.getElementsByTagName("LOGO").item(0).getTextContent();
                String nombre = equipo.getElementsByTagName("NOMBRE").item(0).getTextContent();
                String puntos = equipo.getElementsByTagName("PUNTOS").item(0).getTextContent();
                String goles = equipo.getElementsByTagName("GOLES").item(0).getTextContent();

                model.addRow(new Object[] {i+1,logo,nombre, puntos, goles});


            }
            table.setModel(model);
            table.setBackground(Color.gray);
            pClasificacion.setBackground(EstilosDeVistas.COLOR_DE_FONDO);
            pClasificacion.add(table);
            // Contenedor para el encabezado de la tabla
            JPanel headerContainer = new JPanel();
            headerContainer.setLayout(new BoxLayout(headerContainer, BoxLayout.Y_AXIS));

            // Agregar el encabezado de la tabla con los nombres de las columnas
            JTableHeader header = table.getTableHeader();
            header.setFont(new Font("DialogInput", Font.BOLD, 20));
            headerContainer.add(header);

            pClasificacion.setLayout(new BorderLayout());
            pClasificacion.add(headerContainer, BorderLayout.NORTH);
            pClasificacion.add(table, BorderLayout.CENTER);

            table.setModel(model);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("clasificacion");
        frame.setContentPane(new Clasificacion().pClasificacion);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public JPanel getpClasificacion() {
        return pClasificacion;
    }
}
