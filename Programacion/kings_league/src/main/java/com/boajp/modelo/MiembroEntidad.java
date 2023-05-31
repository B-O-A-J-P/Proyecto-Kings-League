package com.boajp.modelo;

import jakarta.persistence.*;

import java.util.Collection;

@Entity
@Table(name = "MIEMBROS", schema = "HR")
public class MiembroEntidad extends Persona{
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "COD_MIEMBRO")
    private short codMiembro;
    @OneToMany(mappedBy = "miembro")
    private Collection<ContratoEquipoMiembroEntidad> contratos;
    @ManyToOne
    @JoinColumn(name = "COD_AGENDA", referencedColumnName = "COD_AGENDA")
    private AgendaEntidad agenda;

    public MiembroEntidad() {
    }

    public MiembroEntidad(String nombre, String apellido, String dni, Collection<ContratoEquipoMiembroEntidad> contratos, AgendaEntidad agenda) {
        super(nombre, apellido, dni);
        this.contratos = contratos;
        this.agenda = agenda;
    }

    public String[] getAtributos() {
        return new String[]{"Código de miembro", "Nombre", "Apellido", "Dni"};
    }

    public String[] toArray() {
        return new String[]{
                String.valueOf(codMiembro),
                getNombre(),
                getApellido(),
                getDni()
        };
    }

    public short getCodMiembro() {
        return codMiembro;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MiembroEntidad that = (MiembroEntidad) o;

        if (codMiembro != that.codMiembro) return false;
        if (getDni() != null ? !getDni().equals(that.getDni()) : that.getDni() != null) return false;
        if (getNombre() != null ? !getNombre().equals(that.getNombre()) : that.getNombre() != null) return false;
        if (getApellido() != null ? !getApellido().equals(that.getApellido()) : that.getApellido() != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) codMiembro;
        result = 31 * result + (getDni() != null ? getDni().hashCode() : 0);
        result = 31 * result + (getNombre() != null ? getNombre().hashCode() : 0);
        result = 31 * result + (getApellido() != null ? getApellido().hashCode() : 0);
        return result;
    }

    public Collection<ContratoEquipoMiembroEntidad> getContratos() {
        return contratos;
    }

    public void setContratos(Collection<ContratoEquipoMiembroEntidad> contratosEquipoMiembrosByCodMiembro) {
        this.contratos = contratosEquipoMiembrosByCodMiembro;
    }

    public AgendaEntidad getAgenda() {
        return agenda;
    }

    public void setAgenda(AgendaEntidad agendasByCodAgenda) {
        this.agenda = agendasByCodAgenda;
    }
}
