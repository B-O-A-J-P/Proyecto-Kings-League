package com.boajp.modelo;

import jakarta.persistence.*;

@Entity
@Table(name = "AGENDAS", schema = "HR")
public class AgendaEntidad {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "COD_AGENDA")
    private int codAgenda;
    @Basic
    @Column(name = "EMAIL")
    private String email;
    @Basic
    @Column(name = "TELEFONO")
    private String telefono;

    public AgendaEntidad() {
    }

    public AgendaEntidad(String email, String telefono) {
        this.email = email;
        this.telefono = telefono;
    }

    public int getCodAgenda() {
        return codAgenda;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AgendaEntidad that = (AgendaEntidad) o;

        if (codAgenda != that.codAgenda) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (telefono != null ? !telefono.equals(that.telefono) : that.telefono != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = codAgenda;
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (telefono != null ? telefono.hashCode() : 0);
        return result;
    }


}
