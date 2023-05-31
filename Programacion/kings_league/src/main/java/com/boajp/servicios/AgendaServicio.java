package com.boajp.servicios;

import com.boajp.modelo.AgendaEntidad;
import com.boajp.repositorios.AgendaRepositorio;

import java.util.List;

public class AgendaServicio {
    private AgendaRepositorio agendaRepositorio;

    public AgendaServicio() {
        agendaRepositorio = new AgendaRepositorio();
    }

    public String[] getColumnas() {
        return new AgendaEntidad().getAtributos();
    }

    public String[][] getFilas() {
        List<AgendaEntidad> lista = agendaRepositorio.seleccionarTodosLasAgendas();
        String[][] filas = new String[lista.size()][lista.get(0).getAtributos().length];
        for ( int x = 0; x < filas.length; x++ ) {
            filas[x] = lista.get(x).toArray();
        }
        return filas;
    }
}
