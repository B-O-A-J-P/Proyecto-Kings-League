package com.boajp.modelo;

import jakarta.persistence.*;

import java.util.Collection;

@Entity
@Table(name = "JUGADORES", schema = "HR")
public class JugadorEntidad extends Persona{
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "COD_JUGADOR")
    private int codJugador;

    @Basic
    @Column(name = "PIE")
    private String pie;
    @Basic
    @Column(name = "ALTURA")
    private Byte altura;
    @OneToMany(mappedBy = "jugador")
    private Collection<ContratoEquipoJugadorEntidad> contratos;
    @ManyToOne
    @JoinColumn(name = "COD_AGENDA", referencedColumnName = "COD_AGENDA", nullable = false)
    private AgendasEntidad agenda;
    @OneToMany(mappedBy = "temporada")
    private Collection<RegistroJugadorEntidad> registrosDeTemporadas;

    public int getCodJugador() {
        return codJugador;
    }

    public void setCodJugador(int codJugador) {
        this.codJugador = codJugador;
    }


    public String getPie() {
        return pie;
    }

    public void setPie(String pie) {
        this.pie = pie;
    }

    public Byte getAltura() {
        return altura;
    }

    public void setAltura(Byte altura) {
        this.altura = altura;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JugadorEntidad that = (JugadorEntidad) o;

        if (codJugador != that.codJugador) return false;
        if (dni != null ? !dni.equals(that.dni) : that.dni != null) return false;
        if (nombre != null ? !nombre.equals(that.nombre) : that.nombre != null) return false;
        if (apellido != null ? !apellido.equals(that.apellido) : that.apellido != null) return false;
        if (pie != null ? !pie.equals(that.pie) : that.pie != null) return false;
        if (altura != null ? !altura.equals(that.altura) : that.altura != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = codJugador;
        result = 31 * result + (dni != null ? dni.hashCode() : 0);
        result = 31 * result + (nombre != null ? nombre.hashCode() : 0);
        result = 31 * result + (apellido != null ? apellido.hashCode() : 0);
        result = 31 * result + (pie != null ? pie.hashCode() : 0);
        result = 31 * result + (altura != null ? altura.hashCode() : 0);
        return result;
    }

    public Collection<ContratoEquipoJugadorEntidad> getContratos() {
        return contratos;
    }

    public void setContratos(Collection<ContratoEquipoJugadorEntidad> contratosEquipoJugadorsByCodJugador) {
        this.contratos = contratosEquipoJugadorsByCodJugador;
    }

    public AgendasEntidad getAgenda() {
        return agenda;
    }

    public void setAgenda(AgendasEntidad agendasByCodAgenda) {
        this.agenda = agendasByCodAgenda;
    }

    public Collection<RegistroJugadorEntidad> getRegistrosDeTemporadas() {
        return registrosDeTemporadas;
    }

    public void setRegistrosDeTemporadas(Collection<RegistroJugadorEntidad> registrosJugadoresByCodJugador) {
        this.registrosDeTemporadas = registrosJugadoresByCodJugador;
    }
}
