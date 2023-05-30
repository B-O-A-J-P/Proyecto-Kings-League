package com.boajp.servicios;

import com.boajp.modelo.SplitEntidad;
import com.boajp.repositorios.SplitRepositorio;

import java.util.List;

public class SplitsServicio {
    private SplitRepositorio splitRepositorio;
    public SplitsServicio() {
        splitRepositorio = new SplitRepositorio();
    }

    public void anadirSplit(SplitEntidad splitEntidad) {
        splitRepositorio.insertar(splitEntidad);
    }

    public void eliminarSplit(SplitEntidad splitEntidad) {
        splitRepositorio.eliminar(splitEntidad);
    }

    public void modificarSplit(SplitEntidad splitEntidad)  {
        splitRepositorio.modificar(splitEntidad);
    }

    public SplitEntidad buscarSplit(int codigo) {
        return splitRepositorio.buscarSplit(codigo);
    }

    public String[][] getFilasSplits() {
        List<SplitEntidad> splits = splitRepositorio.buscarSplits();
        String[][] filas = new String[splits.size()][splits.get(0).getAtributos().length];
        for ( int x = 0; x < splits.size(); x++ ) {
            filas[x] = splits.get(x).toArray();
        }
        return filas;
    }

    public String[] getCodigos() {
        List<SplitEntidad> lista = splitRepositorio.buscarSplits();
        String[] codigos = new String[lista.size()];
        for ( int x = 0; x < lista.size(); x++ ) {
            codigos[x] = String.valueOf(lista.get(x).getCodSplit());
        }
        return codigos;
    }

    public String[] getColumnas() {
        return new SplitEntidad().getAtributos();
    }
}
