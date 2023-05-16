package com.boajp.vista.carta;

import javax.swing.*;
import java.awt.*;

public class Carta extends CartaAbstracta {
    private final JPanel CABECERA;
    private final JPanel CUERPO;
    private final Color COLOR_DE_FONDO = new Color(0, 0, 0, 0);
    public int anchura = 400;
    public int altura = 400;
    public Carta(CabeceraAbstracta cabecera, CuerpoAbstracto cuerpo) {
        setMinimumSize(new Dimension(anchura, altura));
        setPreferredSize(new Dimension(anchura, altura));
        setMaximumSize(new Dimension(anchura, altura));
        setLayout(new GridBagLayout());

        GridBagConstraints constraintsCabecera = new GridBagConstraints(0, 0, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(10, 0, 0, 0), 0, 0);
        GridBagConstraints constraintsCuerpo = new GridBagConstraints(0, 1, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 20, 20, 20), 0, 0);

        this.CABECERA = cabecera;
        cabecera.setBackground(null);
        cabecera.setOpaque(false);
        this.CUERPO = cuerpo;
        cuerpo.setBackground(null);
        cuerpo.setBackground(null);
        cuerpo.setOpaque(false);

        add(cabecera, constraintsCabecera);
        add(cuerpo, constraintsCuerpo);
    }

    @Override
    public int getAnchura() {
        return anchura;
    }

    @Override
    public int getAltura() {
        return altura;
    }

    public static void main(String... args){
        JFrame frame = new JFrame();

        frame.setLayout(new FlowLayout());
        Cabecera cabecera = new Cabecera("Jornada 3", "23 de Febrero de 2023");
        JLabel[][] list = new JLabel[6][3];
        for (int x = 0; x < list.length; x++) {
            list[x] = new JLabel[]{new JLabel("Equipo uno"), new JLabel("vs"), new JLabel("Equipod dos")};

        }

        Carta carta = new Carta(cabecera, new CalendarioTabla(list));
        frame.add(carta);

        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}

