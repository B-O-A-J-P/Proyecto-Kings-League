package com.boajp;


import com.boajp.modelo.JugadorEntidad;
import com.boajp.repositorios.JugadorRepositorio;

import javax.swing.*;


public class Aplicacion {

    static public void main(String... args) {
        JugadorRepositorio jugadorRepositorio = new JugadorRepositorio();
        try {
            JugadorEntidad jugadorEntidad = new JugadorEntidad("jorge", "egea" ,"2334", "derecho", ((byte) 190));
            jugadorRepositorio.insertar(jugadorEntidad);
        } catch (Exception exception) {
            System.out.println(exception.getMessage() + " || " + exception.getClass());
        }

    }

}
