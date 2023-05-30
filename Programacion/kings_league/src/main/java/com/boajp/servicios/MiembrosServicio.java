package com.boajp.servicios;

import com.boajp.modelo.MiembroEntidad;
import com.boajp.repositorios.MiembroRepositorio;

import java.util.List;

public class MiembrosServicio {
    private MiembroRepositorio miembroRepositorio;

    public MiembrosServicio() {
        miembroRepositorio = new MiembroRepositorio();
    }

    public void insertar(MiembroEntidad miembro) {
        miembroRepositorio.insertar(miembro);
    }

    public MiembroEntidad buscar(int codigo) {
        return miembroRepositorio.buscar(codigo);
    }

    public void modificar(MiembroEntidad miembro) {
        miembroRepositorio.modificar(miembro);
    }

    public void eliminar(int codigo) {
        miembroRepositorio.eliminar(codigo);
    }

    public String[] getColumnas() {
        return new MiembroEntidad().getAtributos();
    }

    public String[][] getFilas() {
        List<MiembroEntidad> lista = miembroRepositorio.seleccionarTodosLosMiembros();
        String[][] filas = new String[lista.size()][lista.get(0).getAtributos().length];
        for (int x = 0; x < filas.length; x++) {
            filas[x] = lista.get(x).toArray();
        }
        return filas;
    }

}
