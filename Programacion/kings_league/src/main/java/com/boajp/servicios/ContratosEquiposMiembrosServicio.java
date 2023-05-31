package com.boajp.servicios;

import com.boajp.modelo.ContratoEquipoMiembroEntidad;
import com.boajp.repositorios.ContratoEquipoMiembroRepositorio;

import java.time.LocalDate;
import java.util.List;

public class ContratosEquiposMiembrosServicio {
    private ContratoEquipoMiembroRepositorio contratoEquipoMiembroRepositorio;

    public ContratosEquiposMiembrosServicio() {
        contratoEquipoMiembroRepositorio = new ContratoEquipoMiembroRepositorio();
    }

    public String[] getColumnas() {
        return new ContratoEquipoMiembroEntidad().getAtributos();
    }

    public String[][] getFilas() {
        List<ContratoEquipoMiembroEntidad> lista = contratoEquipoMiembroRepositorio.seleccionarTodosLosContratos();
        String[][] filas = new String[lista.size()][lista.get(0).getAtributos().length];
        for ( int x = 0; x < filas.length; x++) {
            filas[x] = lista.get(x).toArray();
        }
        return filas;
    }

    public void insertar(ContratoEquipoMiembroEntidad contrato) {
        contratoEquipoMiembroRepositorio.insertar(contrato);
    }

    public void eliminar(int codigoEquipo, int codigoMiembro, LocalDate fecha) {
        contratoEquipoMiembroRepositorio.eliminar(codigoEquipo, codigoMiembro, fecha);
    }
}
