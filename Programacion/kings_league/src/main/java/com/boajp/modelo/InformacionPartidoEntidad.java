package com.boajp.modelo;

import jakarta.persistence.*;

@Entity
@Table(name = "INFORMACION_PARTIDOS", schema = "HR")
public class InformacionPartidoEntidad {
    @Basic
    @Column(name = "NUMERO_GOLES")
    private byte numeroGoles;
    @Basic
    @Column(name = "RESULTADO")
    private String resultado;
    @ManyToOne
    @Id
    @JoinColumn(name = "COD_PARTIDO", referencedColumnName = "COD_PARTIDO", nullable = false)
    private PartidoEntidad partido;
    @ManyToOne
    @Id
    @JoinColumn(name = "COD_EQUIPO", referencedColumnName = "COD_EQUIPO", nullable = false)
    private EquipoEntidad equipo;

    public byte getNumeroGoles() {
        return numeroGoles;
    }

    public void setNumeroGoles(byte numeroGoles) {
        this.numeroGoles = numeroGoles;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InformacionPartidoEntidad that = (InformacionPartidoEntidad) o;

        if (numeroGoles != that.numeroGoles) return false;
        if (resultado != null ? !resultado.equals(that.resultado) : that.resultado != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) numeroGoles;
        result = 31 * result + (resultado != null ? resultado.hashCode() : 0);
        return result;
    }

    public PartidoEntidad getPartido() {
        return partido;
    }

    public void setPartido(PartidoEntidad partidosByCodPartido) {
        this.partido = partidosByCodPartido;
    }

    public EquipoEntidad getEquipo() {
        return equipo;
    }

    public void setEquipo(EquipoEntidad equiposByCodEquipo) {
        this.equipo = equiposByCodEquipo;
    }
}
