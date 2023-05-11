package com.boajp.modelo;

import jakarta.persistence.*;

@Entity
@Table(name = "REGISTROS_EQUIPOS", schema = "HR")
public class RegistroEquipoEntidad {
    @ManyToOne
    @Id
    @JoinColumn(name = "COD_TEMPORADA", referencedColumnName = "COD_TEMPORADA", nullable = false)
    private TemporadaEntidad temporada;
    @ManyToOne
    @Id
    @JoinColumn(name = "COD_EQUIPO", referencedColumnName = "COD_EQUIPO", nullable = false)
    private EquipoEntidad equipo;

    public TemporadaEntidad getTemporada() {
        return temporada;
    }

    public void setTemporada(TemporadaEntidad temporadasByCodTemporada) {
        this.temporada = temporadasByCodTemporada;
    }

    public EquipoEntidad getEquipo() {
        return equipo;
    }

    public void setEquipo(EquipoEntidad equiposByCodEquipo) {
        this.equipo = equiposByCodEquipo;
    }
}
