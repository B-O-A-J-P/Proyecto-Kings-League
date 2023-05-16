package com.boajp.vista;

import com.boajp.modelo.EquipoEntidad;
import com.boajp.modelo.Persona;
import com.boajp.utilidades.EstilosDeVistas;
import com.boajp.vista.carta.CartaAbstracta;
import com.boajp.vista.carta.CartaMiembro;
import com.boajp.vista.carta.EquipoCarta;
import com.boajp.vista.carta.GridDeCartas;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class PanelEquipos extends JPanel {
    private JPanel vistaMiembros;
    private ArrayList<EquipoEntidad> equipoEntidads;
    private ArrayList<CartaAbstracta> cartasEquipos;
    private ArrayList<CartaAbstracta> cartasMiembros;
    public PanelEquipos(ArrayList<EquipoEntidad> equipos, JScrollPane scrollPane) {
        setBackground(EstilosDeVistas.COLOR_DE_FONDO);
        setLayout(new GridBagLayout());
        this.equipoEntidads = equipos;

        GridBagConstraints constraintsMiembros = new GridBagConstraints(0, 0, 1, 1, 1, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0);
        GridBagConstraints constraintsEquipos = new GridBagConstraints(0, 1, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0);

        generarMiembrosDummy();
        vistaMiembros = new JPanel(new FlowLayout());
        for (CartaAbstracta carta: cartasMiembros) {
            vistaMiembros.add(carta);
        }
        vistaMiembros.setBackground(EstilosDeVistas.COLOR_DE_FONDO);
        JScrollPane scrollPane1 = new JScrollPane(vistaMiembros);
        scrollPane1.setMaximumSize(new Dimension(scrollPane.getViewport().getWidth(), -1));
        scrollPane1.setBorder(BorderFactory.createEmptyBorder());
        add(scrollPane1, constraintsMiembros);

        cartasEquipos = new ArrayList<>();
        for ( EquipoEntidad equipo : equipos ) {
            cartasEquipos.add(new EquipoCarta(equipo.getNombre()));
        }

        GridDeCartas gridDeEquipos = new GridDeCartas(cartasEquipos, scrollPane);
        add(gridDeEquipos, constraintsEquipos);
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

