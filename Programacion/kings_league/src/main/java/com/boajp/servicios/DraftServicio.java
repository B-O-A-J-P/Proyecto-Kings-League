package com.boajp.servicios;

import com.boajp.modelo.DraftEntidad;
import com.boajp.repositorios.DraftRepositorio;

import java.util.List;

public class DraftServicio {
    private DraftRepositorio draftRepositorio;

    public DraftServicio() {
        draftRepositorio = new DraftRepositorio();
    }

    public String[] getColumnas() {
        return new DraftEntidad().getAtributos();
    }

    public String[][] getFilas() {
        List<DraftEntidad> lista = draftRepositorio.seleccionarTodosLosDrafts();
        String[][] filas = new String[lista.size()][lista.get(0).getAtributos().length];
        for ( int x = 0; x < filas.length; x++ ) {
            filas[x] = lista.get(x).toArray();
        }
        return filas;
    }
}
