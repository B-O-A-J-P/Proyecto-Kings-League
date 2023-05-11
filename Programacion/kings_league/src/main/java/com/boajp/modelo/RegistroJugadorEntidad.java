package com.boajp.modelo;

import jakarta.persistence.*;

@Entity
@Table(name = "REGISTROS_JUGADORES", schema = "HR")
public class RegistroJugadorEntidad {
    @OneToOne(mappedBy = "registroJugador")
    private DraftEntidad draft;
    @ManyToOne
    @Id
    @JoinColumn(name = "COD_TEMPORADA", referencedColumnName = "COD_TEMPORADA", nullable = false)
    private TemporadaEntidad temporada;
    @ManyToOne
    @Id
    @JoinColumn(name = "COD_JUGADOR", referencedColumnName = "COD_JUGADOR", nullable = false)
    private JugadorEntidad jugador;

    public DraftEntidad getDraft() {
        return draft;
    }

    public void setDraft(DraftEntidad draft) {
        this.draft = draft;
    }

    public TemporadaEntidad getTemporada() {
        return temporada;
    }

    public void setTemporada(TemporadaEntidad temporada) {
        this.temporada = temporada;
    }

    public JugadorEntidad getJugador() {
        return jugador;
    }

    public void setJugador(JugadorEntidad jugador) {
        this.jugador = jugador;
    }
}
