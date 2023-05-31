package com.boajp.servicios;

import com.boajp.modelo.ContratoEquipoJugadorEntidad;
import com.boajp.modelo.EquipoEntidad;
import com.boajp.modelo.JugadorEntidad;
import com.boajp.repositorios.ContratoEquipoJugadorRepositorio;

import java.time.LocalDate;
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

    public void insertar(ContratoEquipoJugadorEntidad contrato) {
        contratoEquipoJugadorRepositorio.insertar(contrato);
    }
    public ContratoEquipoJugadorEntidad buscar(EquipoEntidad equipo, JugadorEntidad jugador, LocalDate fecha) {
        return contratoEquipoJugadorRepositorio.buscar(equipo, jugador, fecha);
    }

    public void modificar(ContratoEquipoJugadorEntidad contrato) {
        contratoEquipoJugadorRepositorio.modificar(contrato);
    }

    public void eliminar(ContratoEquipoJugadorEntidad contrato) {
        contratoEquipoJugadorRepositorio.eliminar(contrato);
    }

    public void eliminar(int codigoEquipo, int codigoJugador, LocalDate fecha) {
        contratoEquipoJugadorRepositorio.eliminar(codigoEquipo, codigoJugador, fecha);
    }
}
