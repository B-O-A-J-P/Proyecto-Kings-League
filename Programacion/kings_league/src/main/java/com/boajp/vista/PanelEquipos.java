package com.boajp.vista;

import com.boajp.modelo.EquipoEntidad;
import com.boajp.modelo.Persona;
import com.boajp.utilidades.EstilosDeVistas;
import com.boajp.vista.carta.*;
import com.boajp.vista.carta.CartaMiembro;
import com.boajp.vista.componentes.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class PanelEquipos extends JPanel {
    private JPanel vistaMiembros;
    private ArrayList<EquipoEntidad> equipoEntidads;
    private ArrayList<CartaAbstracta> cartasEquipos;
    private ArrayList<CartaAbstracta> cartasMiembros;
    private JPanel panelDeMiembros;
    private JScrollPane scrollPane;
    private GridBagConstraints constraintsMiembros = new GridBagConstraints(0, 0, 1, 1, 1, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0);
    private GridBagConstraints constraintsEquipos = new GridBagConstraints(0, 1, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0);
    public PanelEquipos(ArrayList<EquipoEntidad> equipos, JScrollPane scrollPane) {
        setBackground(EstilosDeVistas.COLOR_DE_FONDO);
        setLayout(new GridBagLayout());
        this.scrollPane = scrollPane;
        this.equipoEntidads = equipos;

        iniciarPanelMiembros();

        cartasEquipos = new ArrayList<>();
        for ( EquipoEntidad equipo : equipos ) {
            cartasEquipos.add(new EquipoCarta(equipo.getNombre()));
        }

        GridDeCartas gridDeEquipos = new GridDeCartas(cartasEquipos, scrollPane);
        add(gridDeEquipos, constraintsEquipos);
    }

    public void iniciarPanelMiembros() {
        panelDeMiembros = new JPanel(new BorderLayout());
        generarMiembrosDummy();

        GridBagConstraints gridBagConstraints = new GridBagConstraints(0,0, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 20, 0, 20) , 0, 0);

        JPanel panelBotonIzquierdo = new JPanel(new GridBagLayout());
        panelBotonIzquierdo.setBackground(EstilosDeVistas.COLOR_DE_FONDO);
        JButton botonIzquierdo = new BotonCircular("<", 50);
        panelBotonIzquierdo.add(botonIzquierdo, gridBagConstraints);
        JPanel panelBotonDerecho = new JPanel(new GridBagLayout());
        panelBotonDerecho.setBackground(EstilosDeVistas.COLOR_DE_FONDO);
        JButton botonDerecho = new BotonCircular(">", 50);
        panelBotonDerecho.add(botonDerecho, gridBagConstraints);

        GridDeDraft gridDeDraft = new GridDeDraft(cartasMiembros, scrollPane);
        gridDeDraft.anadirBotonAnterior(botonIzquierdo);
        gridDeDraft.anadirBotonSiguiente(botonDerecho);


        panelDeMiembros.setBackground(EstilosDeVistas.COLOR_DE_FONDO);
        panelDeMiembros.add(panelBotonDerecho, BorderLayout.EAST);
        panelDeMiembros.add(panelBotonIzquierdo, BorderLayout.WEST);
        panelDeMiembros.add(gridDeDraft, BorderLayout.CENTER);

        add(panelDeMiembros, constraintsMiembros);
    }

    public void getMiembrosDeEquipo() {
        return;
    }

    public void generarMiembrosDummy(){
        cartasMiembros = new ArrayList<>();
        for (int x = 0; x < 10; x++){
            cartasMiembros.add(new CartaMiembro(new Persona("Nombre " + x, "Apellido " + x, ""+x)));
        }
    }

    public static void main(String... args){
        JFrame frame = new JFrame();
        frame.setLayout(new BorderLayout());
        ArrayList<EquipoEntidad> equipos = new ArrayList<>();
        for ( int x = 0; x < 12; ++x) {
            EquipoEntidad equipoEntidad = new EquipoEntidad("Equipo " + x);
            equipos.add(equipoEntidad);
        }
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.setViewportView(new PanelEquipos(equipos, scrollPane));
        frame.add(scrollPane);


        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

}

