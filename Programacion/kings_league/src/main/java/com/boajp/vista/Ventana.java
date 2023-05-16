package com.boajp.vista;

import com.boajp.modelo.JugadorEntidad;
import com.boajp.utilidades.*;
import com.boajp.vista.carta.*;
import com.boajp.vista.formulario.Formulario;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Ventana extends JFrame {
    private Insets CUERPO_INSETS = new Insets(0, 0, 0, 0);
    private Insets PIE_INSETS = new Insets(0, 0, 0, 0);
    private GridBagConstraints CUERPO = new GridBagConstraints(1, 0, 1, 3, 0, 1, GridBagConstraints.NORTH, GridBagConstraints.BOTH, CUERPO_INSETS, 0, 0);
    private GridBagConstraints PIE = new GridBagConstraints(0, 1, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, PIE_INSETS, 0, 0);
    private BarraDeNavegacion barraDeNavegacion;
    private JScrollPane scrollPane;
    private JPanel panelCuerpo;

    public Ventana() {
        setBackground(EstilosDeVistas.COLOR_DE_FONDO);
        setLayout(new BorderLayout(0, 0));
        setSize(new Dimension(1280, 720));

        panelCuerpo = new JPanel();
        panelCuerpo.setLayout(new GridBagLayout());
        panelCuerpo.setBackground(EstilosDeVistas.COLOR_DE_FONDO);

        scrollPane = new JScrollPane(panelCuerpo);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());

        // NAV
        barraDeNavegacion = new BarraDeNavegacion();
        add(barraDeNavegacion, BorderLayout.NORTH);

        add(scrollPane, BorderLayout.CENTER);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    public void mostrarFormulario() {
        panelCuerpo.removeAll();
        Formulario formulario = new Formulario();
        panelCuerpo.add(formulario);
        if (!this.isVisible())
            this.setVisible(true);
        panelCuerpo.revalidate();
        panelCuerpo.repaint();
    }

    public void mostrarPanelDeInicio(ArrayList<CartaAbstracta> cartas, ArrayList<CartaAbstracta> cartasEquipos) {
        panelCuerpo.removeAll();
        PanelDeInicio panelDeInicio = new PanelDeInicio(cartas, cartasEquipos, scrollPane);
        panelCuerpo.add(panelDeInicio, CUERPO);
        if (!this.isVisible())
            setVisible(true);
        panelCuerpo.revalidate();
        panelCuerpo.repaint();
    }

    public void mostrarPanelDeJugadores(ArrayList<JugadorEntidad> jugadores) {
        panelCuerpo.removeAll();
        PanelJugadores panelJugadores = new PanelJugadores(jugadores, scrollPane);
        panelCuerpo.add(panelJugadores, CUERPO);
        if (!this.isVisible())
            setVisible(true);
        panelCuerpo.revalidate();
        panelCuerpo.repaint();
    }

    public BarraDeNavegacion getBarraDeNavegacion() {
        return barraDeNavegacion;
    }

    public JScrollPane getScrollPane() {
        return scrollPane;
    }

    public JPanel getPanelCuerpo() {
        return panelCuerpo;
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
        for (int x = 0; x < filas.length; x++) {
            filas[x] = new Object[]{x + 1, "Equipo " + x, x + 10};
        }
        ClasificacionTabla clasificacionTabla = new ClasificacionTabla(filas, new String[]{"Posición", "Equipo", "Puntos"});
        Carta clasificacion = new Carta(cabeceraClasificacion, clasificacionTabla);
        ArrayList<CartaAbstracta> cartas = new ArrayList<>();
        cartas.add(calendario);
        cartas.add(clasificacion);


        ArrayList<JugadorEntidad> jugadores = new ArrayList<>();
        for (int x = 0; x < 100; x++) {
            JugadorEntidad jugadorEntidad = new JugadorEntidad();
            jugadorEntidad.setNombre("Nombre" + x);
            jugadorEntidad.setApellido("Apellido" + x);
            jugadores.add(jugadorEntidad);
        }


        ArrayList<CartaAbstracta> cartasEquipos = new ArrayList<>();
        for (int x = 1; x <= 12; ++x) {
            cartasEquipos.add(new EquipoCarta("Equipo uno dos tres", Color.LIGHT_GRAY, Color.GRAY));
        }
        Ventana ventana = new Ventana();
        ventana.mostrarPanelDeInicio(cartas, cartasEquipos);
        //ventana.mostrarFormulario();
        //ventana.mostrarPanelDeJugadores(jugadores);
    }
}

