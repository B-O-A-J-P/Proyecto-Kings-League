package com.boajp.modelo;

import jakarta.persistence.*;

@Entity
@Table(name = "CLASIFICACIONES", schema = "HR")
public class ClasificacionEntidad {
    @Basic
    @Column(name = "POSICION")
    private byte posicion;
    @ManyToOne
    @Id
    @JoinColumn(name = "COD_SPLIT", referencedColumnName = "COD_SPLIT", nullable = false)
    private SplitEntidad split;
    @ManyToOne
    @Id
    @JoinColumn(name = "COD_EQUIPO", referencedColumnName = "COD_EQUIPO", nullable = false)
    private EquipoEntidad equipo;

    public ClasificacionEntidad() {
    }

    public ClasificacionEntidad(byte posicion, SplitEntidad split, EquipoEntidad equipo) {
        this.posicion = posicion;
        this.split = split;
        this.equipo = equipo;
    }

    public byte getPosicion() {
        return posicion;
    }

    public void setPosicion(byte posicion) {
        this.posicion = posicion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ClasificacionEntidad that = (ClasificacionEntidad) o;

        if (posicion != that.posicion) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return (int) posicion;
    }

    public SplitEntidad getSplit() {
        return split;
    }

    public void setSplit(SplitEntidad splitsByCodSplit) {
        this.split = splitsByCodSplit;
    }

    public EquipoEntidad getEquipo() {
        return equipo;
    }

    public void setEquipo(EquipoEntidad equiposByCodEquipo) {
        this.equipo = equiposByCodEquipo;
    }
}
