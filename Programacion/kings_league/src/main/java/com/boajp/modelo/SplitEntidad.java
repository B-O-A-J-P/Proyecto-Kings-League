package com.boajp.modelo;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collection;

@Entity
@Table(name = "SPLITS", schema = "HR")
public class SplitEntidad {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "COD_SPLIT")
    private int codSplit;
    @Basic
    @Column(name = "NOMBRE")
    private String nombre;
    @Basic
    @Column(name = "FECHA_INICIO")
    private LocalDate fechaInicio;
    @Basic
    @Column(name = "FECHA_FIN")
    private LocalDate fechaFin;
    @OneToMany(mappedBy = "split")
    private Collection<ClasificacionEntidad> tablaClasificaciones;
    @OneToMany(mappedBy = "split")
    private Collection<JornadaEntidad> listaJornadas;
    @ManyToOne
    @JoinColumn(name = "COD_TEMPORADA", referencedColumnName = "COD_TEMPORADA", nullable = false)
    private TemporadaEntidad temporada;

    public SplitEntidad() {
    }

    public SplitEntidad(String nombre, LocalDate fechaInicio, LocalDate fechaFin, TemporadaEntidad temporadaEntidad) {
        this.nombre = nombre;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.temporada = temporadaEntidad;
    }

    public SplitEntidad(String nombre, LocalDate fechaInicio, LocalDate fechaFin, Collection<ClasificacionEntidad> tablaClasificaciones, Collection<JornadaEntidad> listaJornadas, TemporadaEntidad temporada) {
        this.nombre = nombre;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.tablaClasificaciones = tablaClasificaciones;
        this.listaJornadas = listaJornadas;
        this.temporada = temporada;
    }

    public String[] getAtributos() {
        return new String[] {"Código de temporada", "Código de split", "Periodo", "Fecha de inicio", "Fecha fin"};
    }

    public String[] toArray() {
        return new String[] {String.valueOf(temporada.getCodTemporada()), String.valueOf(codSplit), nombre, getFechaInicioString(), getFechaFinString()};
    }

    public String getFechaInicioString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return fechaInicio.format(formatter);
    }

    public String getFechaFinString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return fechaFin.format(formatter);
    }

    public int getCodSplit() {
        return codSplit;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

        SplitEntidad that = (SplitEntidad) o;

        if (codSplit != that.codSplit) return false;
        if (nombre != null ? !nombre.equals(that.nombre) : that.nombre != null) return false;
        if (fechaInicio != null ? !fechaInicio.equals(that.fechaInicio) : that.fechaInicio != null) return false;
        if (fechaFin != null ? !fechaFin.equals(that.fechaFin) : that.fechaFin != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = codSplit;
        result = 31 * result + (nombre != null ? nombre.hashCode() : 0);
        result = 31 * result + (fechaInicio != null ? fechaInicio.hashCode() : 0);
        result = 31 * result + (fechaFin != null ? fechaFin.hashCode() : 0);
        return result;
    }

    public Collection<ClasificacionEntidad> getTablaClasificaciones() {
        return tablaClasificaciones;
    }

    public void setTablaClasificaciones(Collection<ClasificacionEntidad> clasificacionesByCodSplit) {
        this.tablaClasificaciones = clasificacionesByCodSplit;
    }

    public Collection<JornadaEntidad> getListaJornadas() {
        return listaJornadas;
    }

    public void setListaJornadas(Collection<JornadaEntidad> jornadasByCodSplit) {
        this.listaJornadas = jornadasByCodSplit;
    }

    public TemporadaEntidad getTemporada() {
        return temporada;
    }

    public void setTemporada(TemporadaEntidad temporadasByCodTemporada) {
        this.temporada = temporadasByCodTemporada;
    }
}
