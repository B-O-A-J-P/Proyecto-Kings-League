package com.boajp.modelo;

import jakarta.persistence.*;

import java.sql.Date;
import java.time.LocalDate;

@Entity
@Table(name = "CONTRATOS_EQUIPO_JUGADOR", schema = "HR")
public class ContratoEquipoJugadorEntidad {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "COD_CONTRATO")
    private int codContrato;
    @Basic
    @Column(name = "SALARIO")
    private int salario;
    @Basic
    @Column(name = "CLAUSULA")
    private int clausula;
    @Basic
    @Column(name = "FECHA_INICIO")
    private LocalDate fechaInicio;
    @Basic
    @Column(name = "FECHA_FIN")
    private LocalDate fechaFin;
    @ManyToOne
    @JoinColumn(name = "COD_EQUIPO", referencedColumnName = "COD_EQUIPO", nullable = false)
    private EquipoEntidad equipo;
    @ManyToOne
    @JoinColumn(name = "COD_JUGADOR", referencedColumnName = "COD_JUGADOR", nullable = false)
    private JugadorEntidad jugador;


    public ContratoEquipoJugadorEntidad() {
    }

    public ContratoEquipoJugadorEntidad(int salario, int clausula, LocalDate fechaInicio, LocalDate fechaFin, EquipoEntidad equipo, JugadorEntidad jugador) {
        this.salario = salario;
        this.clausula = clausula;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.equipo = equipo;
        this.jugador = jugador;
    }

    public int getCodContrato() {
        return codContrato;
    }

    public int getSalario() {
        return salario;
    }

    public void setSalario(int salario) {
        this.salario = salario;
    }

    public int getClausula() {
        return clausula;
    }

    public void setClausula(int clausula) {
        this.clausula = clausula;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContratoEquipoJugadorEntidad that = (ContratoEquipoJugadorEntidad) o;

        if (codContrato != that.codContrato) return false;
        if (salario != that.salario) return false;
        if (clausula != that.clausula) return false;
        if (fechaInicio != null ? !fechaInicio.equals(that.fechaInicio) : that.fechaInicio != null) return false;
        if (fechaFin != null ? !fechaFin.equals(that.fechaFin) : that.fechaFin != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = codContrato;
        result = 31 * result + salario;
        result = 31 * result + clausula;
        result = 31 * result + (fechaInicio != null ? fechaInicio.hashCode() : 0);
        result = 31 * result + (fechaFin != null ? fechaFin.hashCode() : 0);
        return result;
    }

    public EquipoEntidad getEquipo() {
        return equipo;
    }

    public void setEquipo(EquipoEntidad equiposByCodEquipo) {
        this.equipo = equiposByCodEquipo;
    }

    public JugadorEntidad getJugador() {
        return jugador;
    }

    public void setJugador(JugadorEntidad jugadoresByCodJugador) {
        this.jugador = jugadoresByCodJugador;
    }
}
