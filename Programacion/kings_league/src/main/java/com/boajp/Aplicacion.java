package com.boajp;


import com.boajp.controladores.Controlador;


public class Aplicacion {
    private static Controlador controlador;

    static public void main(String... args) {
        controlador = new Controlador();
        Controlador.mostrarPanelDeInicio();
    }

    public static Controlador getVentanaControlador() {
        return controlador;
    }
}

