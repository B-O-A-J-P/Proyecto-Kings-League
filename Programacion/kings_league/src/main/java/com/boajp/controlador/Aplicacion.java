package com.boajp.controlador;


import com.boajp.vista.vLoginRegistro;

import javax.swing.*;

public class Aplicacion {

    private static JFrame vIL;
    static public void main(String... args) {
        crearVentanaLoginRegistro();
    }

    public static void crearVentanaLoginRegistro(){
        vIL = new JFrame("vLoginRegistro");
        vIL.setContentPane(new vLoginRegistro().getpPrincipal());
        vIL.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        vIL.pack();
        vIL.setVisible(true);

        vIL.setLocationRelativeTo(null);
        vIL.setSize(500,400);
    }
}
