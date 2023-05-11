package com.boajp.modelo;

import jakarta.persistence.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Collection;

@Entity
@Table(name = "TEMPORADAS", schema = "HR")
public class TemporadaEntidad {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "COD_TEMPORADA")
    private short codTemporada;
    @Basic
    @Column(name = "ANO")
    private short ano;
    @Basic
    @Column(name = "FECHA_INICIO_INSCRIPCION")
    private LocalDate fechaInicioInscripcion;
    @Basic
    @Column(name = "FECHA_FIN_INSCRIPCION")
    private LocalDate fechaFinInscripcion;
    @OneToMany(mappedBy = "temporada")
    private Collection<RegistroEquipoEntidad> listaEquipos;
    @OneToMany(mappedBy = "temporada")
    private Collection<RegistroJugadorEntidad> listaJugadores;
    @OneToMany(mappedBy = "temporada")
    private Collection<SplitEntidad> listaSplits;

    public TemporadaEntidad() {
    }

    public TemporadaEntidad(short ano, LocalDate fechaInicioInscripcion, LocalDate fechaFinInscripcion, Collection<RegistroEquipoEntidad> listaEquipos, Collection<RegistroJugadorEntidad> listaJugadores, Collection<SplitEntidad> listaSplits) {
        this.ano = ano;
        this.fechaInicioInscripcion = fechaInicioInscripcion;
        this.fechaFinInscripcion = fechaFinInscripcion;
        this.listaEquipos = listaEquipos;
        this.listaJugadores = listaJugadores;
        this.listaSplits = listaSplits;
    }

    public short getCodTemporada() {
        return codTemporada;
    }

    public short getAno() {
        return ano;
    }

    public void setAno(short ano) {
        this.ano = ano;
    }

    public LocalDate getFechaInicioInscripcion() {
        return fechaInicioInscripcion;
    }

    public void setFechaInicioInscripcion(LocalDate fechaInicioInscripcion) {
        this.fechaInicioInscripcion = fechaInicioInscripcion;
    }

    public LocalDate getFechaFinInscripcion() {
        return fechaFinInscripcion;
    }

    public void setFechaFinInscripcion(LocalDate fechaFinInscripcion) {
        this.fechaFinInscripcion = fechaFinInscripcion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TemporadaEntidad that = (TemporadaEntidad) o;

        if (codTemporada != that.codTemporada) return false;
        if (ano != that.ano) return false;
        if (fechaInicioInscripcion != null ? !fechaInicioInscripcion.equals(that.fechaInicioInscripcion) : that.fechaInicioInscripcion != null)
            return false;
        if (fechaFinInscripcion != null ? !fechaFinInscripcion.equals(that.fechaFinInscripcion) : that.fechaFinInscripcion != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) codTemporada;
        result = 31 * result + (int) ano;
        result = 31 * result + (fechaInicioInscripcion != null ? fechaInicioInscripcion.hashCode() : 0);
        result = 31 * result + (fechaFinInscripcion != null ? fechaFinInscripcion.hashCode() : 0);
        return result;
    }

    public Collection<RegistroEquipoEntidad> getListaEquipos() {
        return listaEquipos;
    }

    public void setListaEquipos(Collection<RegistroEquipoEntidad> registrosEquiposByCodTemporada) {
        this.listaEquipos = registrosEquiposByCodTemporada;
    }

    public Collection<RegistroJugadorEntidad> getListaJugadores() {
        return listaJugadores;
    }

    public void setListaJugadores(Collection<RegistroJugadorEntidad> registrosJugadoresByCodTemporada) {
        this.listaJugadores = registrosJugadoresByCodTemporada;
    }

    public Collection<SplitEntidad> getListaSplits() {
        return listaSplits;
    }

    public void setListaSplits(Collection<SplitEntidad> splitsByCodTemporada) {
        this.listaSplits = splitsByCodTemporada;
    }
}
