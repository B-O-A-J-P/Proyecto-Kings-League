package com.boajp.repositorios;

import com.boajp.modelo.EquipoEntidad;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import java.util.List;

public class EquipoRepositorio {

    private final EntityManagerFactory emf;
    private final EntityManager em;

    public EquipoRepositorio() {
        emf = Persistence.createEntityManagerFactory("default"); //Cambiar "default" por el nombre de su unidad de persistencia
        em = emf.createEntityManager();
    }

    public void insertar(EquipoEntidad equipo) throws Exception {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.persist(equipo);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw new Exception("Error al intentar insertar el equipo");
        }
    }

    public void eliminar(EquipoEntidad equipo) throws Exception {
        EntityTransaction transaction = em.getTransaction();
        EquipoEntidad e = em.find(EquipoEntidad.class, equipo.getCodEquipo());
        try {
            transaction.begin();
            if (e != null) {
                em.remove(e);
                transaction.commit();
            }
        } catch (Exception ex) {
            transaction.rollback();
            throw new Exception("Error al intentar eliminar el equipo");
        }
    }

    public void modificar(EquipoEntidad equipo) throws Exception {
        EntityTransaction transaction = em.getTransaction();
        EquipoEntidad e = em.find(EquipoEntidad.class, equipo.getCodEquipo());
        try {
            transaction.begin();
            if (e != null) {
                e.setNombre(equipo.getNombre());
                e.setLogo(equipo.getLogo());
                e.setPresupuesto(equipo.getPresupuesto());
                em.persist(e);
                transaction.commit();
            }
        } catch (Exception ex) {
            transaction.rollback();
            throw new Exception("Error al intentar modificar el equipo");
        }
    }

    public List<EquipoEntidad> seleccionarTodosLosEquipos() {
        return em.createQuery("SELECT e FROM EquipoEntidad e", EquipoEntidad.class)
                .getResultList();
    }

    public EquipoEntidad seleccionarEquipoPorId(int id) {
        return em.find(EquipoEntidad.class, id);
    }

}
