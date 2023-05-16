package com.boajp.vista;

import javax.swing.*;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;
import java.util.Arrays;

import com.boajp.vista.carta.*;
import org.apache.batik.bridge.Viewport;

public class PanelDeInicio extends JPanel {
    private ArrayList<CartaAbstracta> cartas;
    private ArrayList<CartaAbstracta> cartasEquipos;
    private JScrollPane scrollPane;

    public PanelDeInicio(ArrayList<CartaAbstracta> cartas, ArrayList<CartaAbstracta> cartasEquipos, JScrollPane scrollPane) {
        this.cartas = cartas;
        this.cartasEquipos = cartasEquipos;
        this.scrollPane = scrollPane;

        setOpaque(false);
        setLayout(new GridBagLayout());
        GridBagConstraints constraintsCartas =  new GridBagConstraints(0, 0, 1, 1, 1, 0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(10, 40, 10, 40), 0, 0);
        GridBagConstraints constraintsEquipos =  new GridBagConstraints(0, 1, 1, 1, 1, 0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(10, 40, 10, 40), 0, 0);

        GridDeCartas gridDeCartas = new GridDeCartas(this.cartas, scrollPane);
        add(gridDeCartas, constraintsCartas);

        GridDeCartas gridDeCartasEquipos = new GridDeCartas(this.cartasEquipos, scrollPane);
        add(gridDeCartasEquipos, constraintsEquipos);
    }

    public void anadirCartas(CartaAbstracta... cartas) {
        this.cartas.addAll(Arrays.asList(cartas));
    }

    public void anadirCartasDeEquipo(CartaAbstracta... cartas) {
        this.cartasEquipos.addAll(Arrays.asList(cartas));
    }

    public ArrayList<CartaAbstracta> getCartas() {
        return cartas;
    }

    public void setCartas(ArrayList<CartaAbstracta> cartas) {
        this.cartas = cartas;
    }

    public ArrayList<CartaAbstracta> getCartasEquipos() {
        return cartasEquipos;
    }

    public void setCartasEquipos(ArrayList<CartaAbstracta> cartasEquipos) {
        this.cartasEquipos = cartasEquipos;
    }

    public JScrollPane getScrollPane() {
        return scrollPane;
    }

    public void setScrollPane(JScrollPane scrollPane) {
        this.scrollPane = scrollPane;
    }

    public static void main(String... args) {
        //Calendario
        Cabecera cabecera = new Cabecera("Jornada 3", "23 de Febrero de 2023");
        JLabel[][] list = new JLabel[6][3];
        for (int x = 0; x < 6; x++) {
            list[x] =
                    new JLabel[]{new JLabel("Equipo uno"), new JLabel("vs"), new JLabel("Equipod dos")};
        }
        CalendarioTabla calendarioTabla = new CalendarioTabla(list);
        Carta calendario = new Carta(cabecera, calendarioTabla);
        //Clasificación
        Cabecera cabeceraClasificacion = new Cabecera("Clasificación");
        Object[][] filas = new Object[2][3];
        for(int x = 0; x < filas.length; x++) {
            filas[x] = new Object[]{x+1, "Equipo " + x, x+10};
        }
        ClasificacionTabla clasificacionTabla = new ClasificacionTabla(filas, new String[]{"Posición", "Equipo", "Puntos"});
        Carta clasificacion = new Carta(cabeceraClasificacion, clasificacionTabla);
        ArrayList<CartaAbstracta> cartas = new ArrayList<>();
        cartas.add(calendario);
        cartas.add(clasificacion);


        ArrayList<CartaAbstracta> cartasEquipos = new ArrayList<>();
        for (int x = 1; x <= 12; ++x) {
            cartasEquipos.add(new EquipoCarta("Equipo uno dos tres", Color.LIGHT_GRAY, Color.GRAY));
        }

        JFrame frame = new JFrame();
        frame.setLayout(new BorderLayout());
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportView(new PanelDeInicio(cartas, cartasEquipos, scrollPane));
        frame.add(scrollPane);

        frame.setSize(1280, 720);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
