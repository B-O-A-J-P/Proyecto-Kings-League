package com.boajp.servicios;

import com.boajp.modelo.ContratoEquipoJugadorEntidad;
import com.boajp.repositorios.ContratoEquipoJugadorRepositorio;

import java.util.List;

public class ContratosEquiposJugadoresServicio {
    private ContratoEquipoJugadorRepositorio contratoEquipoJugadorRepositorio;

    public ContratosEquiposJugadoresServicio() {
        contratoEquipoJugadorRepositorio = new ContratoEquipoJugadorRepositorio();
    }

    public String[] getColumnas() {
        return new ContratoEquipoJugadorEntidad().getAtributos();
    }

    public String[][] getFilas() {
        List<ContratoEquipoJugadorEntidad> lista = contratoEquipoJugadorRepositorio.seleccionarTodosLosContratosDeJugador();
        String[][] filas = new String[lista.size()][lista.get(0).getAtributos().length];
        for ( int x = 0; x < filas.length; x++) {
            filas[x] = lista.get(x).toArray();
        }
        return filas;
    }
}
