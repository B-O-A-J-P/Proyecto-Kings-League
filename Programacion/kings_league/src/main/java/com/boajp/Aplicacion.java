package com.boajp;



import com.boajp.modelo.AgendaEntidad;
import com.boajp.modelo.ClasificacionEntidad;
import com.boajp.modelo.ContratoEquipoJugadorEntidad;
import com.boajp.modelo.TemporadaEntidad;
import com.boajp.repositorios.AgendaRepositorio;
import com.boajp.repositorios.ClasificacionRepositorio;
import com.boajp.repositorios.ContratoEquipoJugadorRepositorio;
import com.boajp.repositorios.TemporadaRepositorio;


import java.util.ArrayList;
import java.util.List;

import com.boajp.modelo.JugadorEntidad;
import com.boajp.repositorios.JugadorRepositorio;

import javax.swing.*;



public class Aplicacion {

    static public void main(String... args) {
        JugadorRepositorio jugadorRepositorio = new JugadorRepositorio();
        try {
            JugadorEntidad jugadorEntidad = new JugadorEntidad("jorge", "egea" ,"2334", "derecho", new Byte(190));
            jugadorRepositorio.insertar(jugadorEntidad);
        } catch (Exception exception) {
            System.out.println(exception.getMessage() + " || " + exception.getClass());
        }


        System.out.println(lista.get(0));
/**************************************************************/
        List<AgendaEntidad> listaA = new ArrayList<>();
        AgendaRepositorio agendaRepositorio = new AgendaRepositorio();
        try {
            listaA = agendaRepositorio.seleccionarTodosLasAgendas();
        } catch (Exception exception) {

        }

        System.out.println(listaA.get(0));
 /**************************************************************/
        List<ClasificacionEntidad> listaB = new ArrayList<>();
        ClasificacionRepositorio clasificacionRepositorio = new ClasificacionRepositorio();
        try {
            listaB = clasificacionRepositorio.seleccionarTodasLasClasificaciones();
        } catch (Exception exception) {

        }

    }


        System.out.println(listaB.get(0));
/**************************************************************/
        List<ContratoEquipoJugadorEntidad> listaC = new ArrayList<>();
        ContratoEquipoJugadorRepositorio contratoEquipoJugadorRepositorio = new ContratoEquipoJugadorRepositorio();
        try {
            listaC = ContratoEquipoJugadorRepositorio.seleccionarTodosLosContratosDeJugador();
        } catch (Exception exception) {

        }

        System.out.println(listaC.get(0));







    }
}
