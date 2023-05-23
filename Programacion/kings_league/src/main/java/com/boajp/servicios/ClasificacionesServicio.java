package com.boajp.servicios;

import com.boajp.modelo.ClasificacionEntidad;
import com.boajp.repositorios.ClasificacionRepositorio;

import java.util.List;

public class ClasificacionesServicio {
    private ClasificacionRepositorio clasificacionRepositorio;

    public ClasificacionesServicio() {
        clasificacionRepositorio = new ClasificacionRepositorio();
    }

    public String[] getColumnas() {
        return new ClasificacionEntidad().getAtributos();
    }

    public String[][] getFilas() throws Exception{
        List<ClasificacionEntidad> lista = clasificacionRepositorio.seleccionarTodasLasClasificaciones();
        String[][] filas = new String[lista.size()][lista.get(0).getAtributos().length];
        for ( int x = 0; x < filas.length; x++) {
            filas[x] = lista.get(x).toArray();
        }
        return filas;
    }
}
