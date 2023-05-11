package com.boajp.modelo;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Collection;

@Entity
@Table(name = "PARTIDOS", schema = "HR")
public class PartidoEntidad {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "COD_PARTIDO")
    private int codPartido;
    @Basic
    @Column(name = "HORA")
    private LocalDateTime hora;
    @Basic
    @Column(name = "FASE")
    private String fase;
    @OneToMany(mappedBy = "partido")
    private Collection<InformacionPartidoEntidad> informacionPartido;
    @ManyToOne
    @JoinColumn(name = "COD_JORNADA", referencedColumnName = "COD_JORNADA", nullable = false)
    private JornadaEntidad jornada;
    @ManyToOne
    @JoinColumn(name = "COD_EQUIPO1", referencedColumnName = "COD_EQUIPO", nullable = false)
    private EquipoEntidad equipoUno;
    @ManyToOne
    @JoinColumn(name = "COD_EQUIPO2", referencedColumnName = "COD_EQUIPO", nullable = false)
    private EquipoEntidad equipoDos;

    public PartidoEntidad(LocalDateTime hora, String fase, Collection<InformacionPartidoEntidad> informacionPartido, JornadaEntidad jornada, EquipoEntidad equipoUno, EquipoEntidad equipoDos) {
        this.hora = hora;
        this.fase = fase;
        this.informacionPartido = informacionPartido;
        this.jornada = jornada;
        this.equipoUno = equipoUno;
        this.equipoDos = equipoDos;
    }

    public PartidoEntidad() {

    }

    public int getCodPartido() {
        return codPartido;
    }


    public LocalDateTime getHora() {
        return hora;
    }

    public void setHora(LocalDateTime hora) {
        this.hora = hora;
    }

    public String getFase() {
        return fase;
    }

    public void setFase(String fase) {
        this.fase = fase;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PartidoEntidad that = (PartidoEntidad) o;

        if (codPartido != that.codPartido) return false;
        if (hora != null ? !hora.equals(that.hora) : that.hora != null) return false;
        if (fase != null ? !fase.equals(that.fase) : that.fase != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = codPartido;
        result = 31 * result + (hora != null ? hora.hashCode() : 0);
        result = 31 * result + (fase != null ? fase.hashCode() : 0);
        return result;
    }

    public Collection<InformacionPartidoEntidad> getInformacionPartido() {
        return informacionPartido;
    }

    public void setInformacionPartido(Collection<InformacionPartidoEntidad> informacionPartidosByCodPartido) {
        this.informacionPartido = informacionPartidosByCodPartido;
    }

    public JornadaEntidad getJornada() {
        return jornada;
    }

    public void setJornada(JornadaEntidad jornadasByCodJornada) {
        this.jornada = jornadasByCodJornada;
    }

    public EquipoEntidad getEquipoUno() {
        return equipoUno;
    }

    public void setEquipoUno(EquipoEntidad equiposByCodEquipo1) {
        this.equipoUno = equiposByCodEquipo1;
    }

    public EquipoEntidad getEquipoDos() {
        return equipoDos;
    }

    public void setEquipoDos(EquipoEntidad equiposByCodEquipo2) {
        this.equipoDos = equiposByCodEquipo2;
    }
}
