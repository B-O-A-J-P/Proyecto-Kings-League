package com.boajp.modelo;

import jakarta.persistence.*;

import java.util.Arrays;
import java.util.Collection;

@Entity
@Table(name = "EQUIPOS", schema = "HR")
public class EquipoEntidad {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "COD_EQUIPO")
    private int codEquipo;
    @Basic
    @Column(name = "NOMBRE")
    private String nombre;
    @Basic
    @Column(name = "LOGO")
    private byte[] logo;
    @Basic
    @Column(name = "PRESUPUESTO")
    private long presupuesto;
    @OneToMany(mappedBy = "equipo")
    private Collection<ClasificacionEntidad> clasificaciones;
    @OneToMany(mappedBy = "equipo")
    private Collection<ContratoEquipoJugadorEntidad> contratosJugadores;
    @OneToMany(mappedBy = "equipo")
    private Collection<ContratoEquipoMiembroEntidad> contratosMiembros;
    @OneToMany(mappedBy = "equipo")
    private Collection<InformacionPartidoEntidad> informacionPartidos;
    @OneToMany(mappedBy = "equipoUno")
    private Collection<PartidoEntidad> listaPartidos;
    @OneToMany(mappedBy = "equipo")
    private Collection<RegistroEquipoEntidad> registrosTemporadas;

    public int getCodEquipo() {
        return codEquipo;
    }

    public void setCodEquipo(int codEquipo) {
        this.codEquipo = codEquipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public byte[] getLogo() {
        return logo;
    }

    public void setLogo(byte[] logo) {
        this.logo = logo;
    }

    public long getPresupuesto() {
        return presupuesto;
    }

    public void setPresupuesto(long presupuesto) {
        this.presupuesto = presupuesto;
    }

    public EquipoEntidad() {
    }

    public EquipoEntidad(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EquipoEntidad that = (EquipoEntidad) o;

        if (codEquipo != that.codEquipo) return false;
        if (presupuesto != that.presupuesto) return false;
        if (nombre != null ? !nombre.equals(that.nombre) : that.nombre != null) return false;
        if (!Arrays.equals(logo, that.logo)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = codEquipo;
        result = 31 * result + (nombre != null ? nombre.hashCode() : 0);
        result = 31 * result + Arrays.hashCode(logo);
        result = 31 * result + (int) (presupuesto ^ (presupuesto >>> 32));
        return result;
    }

    public Collection<ClasificacionEntidad> getClasificaciones() {
        return clasificaciones;
    }

    public void setClasificaciones(Collection<ClasificacionEntidad> clasificacionesByCodEquipo) {
        this.clasificaciones = clasificacionesByCodEquipo;
    }

    public Collection<ContratoEquipoJugadorEntidad> getContratosJugadores() {
        return contratosJugadores;
    }

    public void setContratosJugadores(Collection<ContratoEquipoJugadorEntidad> contratosEquipoJugadorsByCodEquipo) {
        this.contratosJugadores = contratosEquipoJugadorsByCodEquipo;
    }

    public Collection<ContratoEquipoMiembroEntidad> getContratosMiembros() {
        return contratosMiembros;
    }

    public void setContratosMiembros(Collection<ContratoEquipoMiembroEntidad> contratosEquipoMiembrosByCodEquipo) {
        this.contratosMiembros = contratosEquipoMiembrosByCodEquipo;
    }

    public Collection<InformacionPartidoEntidad> getInformacionPartidos() {
        return informacionPartidos;
    }

    public void setInformacionPartidos(Collection<InformacionPartidoEntidad> informacionPartidosByCodEquipo) {
        this.informacionPartidos = informacionPartidosByCodEquipo;
    }

    public Collection<PartidoEntidad> getListaPartidos() {
        return listaPartidos;
    }

    public void setListaPartidos(Collection<PartidoEntidad> partidosByCodEquipo) {
        this.listaPartidos = partidosByCodEquipo;
    }

    public Collection<RegistroEquipoEntidad> getRegistrosTemporadas() {
        return registrosTemporadas;
    }

    public void setRegistrosTemporadas(Collection<RegistroEquipoEntidad> registrosEquiposByCodEquipo) {
        this.registrosTemporadas = registrosEquiposByCodEquipo;
    }
}
