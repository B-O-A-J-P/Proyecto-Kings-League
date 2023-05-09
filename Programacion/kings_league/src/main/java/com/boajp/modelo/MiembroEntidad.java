package com.boajp.modelo;

import jakarta.persistence.*;

import java.util.Collection;

@Entity
@Table(name = "MIEMBROS", schema = "HR")
public class MiembroEntidad extends Persona{
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "COD_MIEMBRO")
    private short codMiembro;
    @OneToMany(mappedBy = "miembro")
    private Collection<ContratoEquipoMiembroEntidad> contratos;
    @ManyToOne
    @JoinColumn(name = "COD_AGENDA", referencedColumnName = "COD_AGENDA", nullable = false)
    private AgendasEntidad agenda;

    public short getCodMiembro() {
        return codMiembro;
    }

    public void setCodMiembro(short codMiembro) {
        this.codMiembro = codMiembro;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MiembroEntidad that = (MiembroEntidad) o;

        if (codMiembro != that.codMiembro) return false;
        if (dni != null ? !dni.equals(that.dni) : that.dni != null) return false;
        if (nombre != null ? !nombre.equals(that.nombre) : that.nombre != null) return false;
        if (apellido != null ? !apellido.equals(that.apellido) : that.apellido != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) codMiembro;
        result = 31 * result + (dni != null ? dni.hashCode() : 0);
        result = 31 * result + (nombre != null ? nombre.hashCode() : 0);
        result = 31 * result + (apellido != null ? apellido.hashCode() : 0);
        return result;
    }

    public Collection<ContratoEquipoMiembroEntidad> getContratos() {
        return contratos;
    }

    public void setContratos(Collection<ContratoEquipoMiembroEntidad> contratosEquipoMiembrosByCodMiembro) {
        this.contratos = contratosEquipoMiembrosByCodMiembro;
    }

    public AgendasEntidad getAgenda() {
        return agenda;
    }

    public void setAgenda(AgendasEntidad agendasByCodAgenda) {
        this.agenda = agendasByCodAgenda;
    }
}
