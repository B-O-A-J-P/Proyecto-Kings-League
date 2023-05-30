package com.boajp.servicios;

import com.boajp.modelo.JornadaEntidad;
import com.boajp.repositorios.JornadaRepositorio;

import java.util.List;

public class JornadasServicio {

    private JornadaRepositorio jornadaRepositorio;

    public JornadasServicio() {
        jornadaRepositorio = new JornadaRepositorio();
    }

    public JornadaEntidad buscarJornada(int codigo) {
        return jornadaRepositorio.buscarJornada(codigo);
    }

    public void insertarJornada(JornadaEntidad jornadaEntidad) {
        jornadaRepositorio.insertar(jornadaEntidad);
    }

    public void eliminarJornada(JornadaEntidad jornadaEntidad) {
        jornadaRepositorio.eliminar(jornadaEntidad);
    }

    public void modificarJornada(JornadaEntidad jornadaEntidad) {
        jornadaRepositorio.modificar(jornadaEntidad);
    }

    public List<JornadaEntidad> getTodasJornadas() {
        return jornadaRepositorio.buscarTodasJornadas();
    }

    public String[][] getFilas() {
        List<JornadaEntidad> listaDeJornadas = jornadaRepositorio.buscarTodasJornadas();
        String[][] filas = new String[listaDeJornadas.size()][listaDeJornadas.get(0).getAtributos().length];
        for ( int x = 0; x < listaDeJornadas.size(); x++ ) {
            filas[x] = listaDeJornadas.get(x).toArray();
        }
        return filas;
    }

    public String[] getColumnas() {
        return new JornadaEntidad().getAtributos();
    }

}
