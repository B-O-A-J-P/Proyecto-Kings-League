package com.boajp.servicios;

import com.boajp.modelo.RegistroEquipoEntidad;
import com.boajp.repositorios.RegistroEquipoRepositorio;

import java.util.List;

public class RegistrosEquipoServicio {
    private RegistroEquipoRepositorio registroEquipoRepositorio;

    public RegistrosEquipoServicio() {
        registroEquipoRepositorio = new RegistroEquipoRepositorio();
    }

    public String[] getColumnas() {
        return new RegistroEquipoEntidad().getAtributos();
    }

    public String[][] getFilas() {
        List<RegistroEquipoEntidad> lista = registroEquipoRepositorio.buscarTodosRegistrosDeEquipo();
        String[][] filas = new String[lista.size()][lista.get(0).getAtributos().length];
        for ( int x = 0; x < filas.length; x++ ) {
            filas[x] = lista.get(x).toArray();
        }
        return filas;
    }
}
