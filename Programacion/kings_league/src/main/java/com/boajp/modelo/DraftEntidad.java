package com.boajp.modelo;

import jakarta.persistence.*;

@Entity
@Table(name = "DRAFT", schema = "HR")
public class DraftEntidad {
    @Basic
    @Column(name = "POSICION")
    private byte posicion;

    @OneToOne
    @Id
    @JoinColumns({@JoinColumn(name = "COD_TEMPORADA", referencedColumnName = "COD_TEMPORADA", nullable = false), @JoinColumn(name = "COD_JUGADOR", referencedColumnName = "COD_JUGADOR", nullable = false)})
    private RegistroJugadorEntidad registroJugador;

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

        DraftEntidad that = (DraftEntidad) o;

        if (posicion != that.posicion) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return (int) posicion;
    }

    public RegistroJugadorEntidad getRegistroJugador() {
        return registroJugador;
    }

    public void setRegistroJugador(RegistroJugadorEntidad registrosJugadores) {
        this.registroJugador = registrosJugadores;
    }
}
