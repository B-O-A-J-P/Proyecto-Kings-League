package com.boajp;


import com.boajp.controlador.VentanaControlador;
import com.boajp.vista.CartaMiembro;

import javax.swing.*;

public class Aplicacion {

    static public void main(String... args) {

        VentanaControlador ventanaControlador = new VentanaControlador();
        ventanaControlador.crearCarta();

    }

}
