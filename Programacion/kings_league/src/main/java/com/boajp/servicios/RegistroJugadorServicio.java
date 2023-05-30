package com.boajp.servicios;

import com.boajp.modelo.RegistroJugadorEntidad;
import com.boajp.repositorios.RegistroJugadorRepositorio;

import java.util.List;

public class RegistroJugadorServicio {
    private RegistroJugadorRepositorio registroJugadorRepositorio;

    public RegistroJugadorServicio() {
        registroJugadorRepositorio = new RegistroJugadorRepositorio();
    }

    public String[] getColumnas() {
        return new RegistroJugadorEntidad().getAtributos();
    }

    public String[][] getFilas() {
        List<RegistroJugadorEntidad> lista = registroJugadorRepositorio.buscarTodosRegistrosJugadores();
        String[][] filas = new String[lista.size()][lista.get(0).getAtributos().length];
        for ( int x = 0; x < filas.length; x++ ) {
            filas[x] = lista.get(x).toArray();
        }
        return filas;
    }
}
