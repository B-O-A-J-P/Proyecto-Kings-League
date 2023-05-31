package com.boajp.servicios;

import com.boajp.modelo.TemporadaEntidad;
import com.boajp.repositorios.TemporadaRepositorio;

import java.time.LocalDate;
import java.util.List;

public class TemporadasServicio {
    private TemporadaRepositorio temporadaRepositorio;
    public TemporadasServicio() {
        temporadaRepositorio = new TemporadaRepositorio();
    }

    public void modificarTemporada(TemporadaEntidad temporada) {
        temporadaRepositorio.modificar(temporada);
    }

    public List<TemporadaEntidad> getTodasTemporadas() {
        return temporadaRepositorio.buscarTodasTemporadas();
    }

    public TemporadaEntidad getTemporada(int codigo) {
        return temporadaRepositorio.buscarTemporada(codigo);
    }

    public void anadirTemporada(int ano, LocalDate fechaInicioInscripcion, LocalDate fechaFinInscripcion) {
        temporadaRepositorio.insertar(new TemporadaEntidad((short) ano, fechaInicioInscripcion, fechaFinInscripcion));
    }

    public void anadirTemporada(TemporadaEntidad temporadaEntidad) {
        temporadaRepositorio.insertar(temporadaEntidad);
    }

    public void eliminarTemporada(TemporadaEntidad temporadaEntidad) {
        temporadaRepositorio.eliminar(temporadaEntidad);
    }

    public void eliminarTemporada(int cod) {
        temporadaRepositorio.eliminar(cod);
    }

    public void eliminarTemporada(int[] codigos) {
        temporadaRepositorio.eliminar(codigos);
    }

    public String[] getCodigos() {
        List<TemporadaEntidad> temporadaEntidadList = temporadaRepositorio.buscarTodasTemporadas();
        String[] codigos = new String[temporadaEntidadList.size()];
        for ( int x = 0; x < temporadaEntidadList.size(); x++ ) {
            codigos[x] = String.valueOf(temporadaEntidadList.get(x).getCodTemporada());
        }
        return codigos;
    }

    public String[][] getFilas() {
        List<TemporadaEntidad> temporadaEntidadList = temporadaRepositorio.buscarTodasTemporadas();
        String[][] filas = new String[temporadaEntidadList.size()][temporadaEntidadList.get(0).getAtributos().length];
        for ( int x = 0; x < temporadaEntidadList.size(); x++ ) {
            filas[x] = temporadaEntidadList.get(x).toArray();
        }
        return filas;
    }

    public String[] getColumnas() {
        return new TemporadaEntidad().getAtributos();
    }
}
