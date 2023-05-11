package com.boajp.repositorios;

import com.boajp.modelo.JugadorEntidad;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import java.util.List;

public class JugadorRepositorio {

    private final EntityManagerFactory emf;
    private final EntityManager em;

    public JugadorRepositorio() {
        emf = Persistence.createEntityManagerFactory("default"); // Cambiar "default" por el nombre de su unidad de persistencia
        em = emf.createEntityManager();
    }

    public void insertar(JugadorEntidad jugador) throws Exception {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.persist(jugador);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw e;
        }
    }

    public void eliminar(JugadorEntidad jugador) throws Exception {
        EntityTransaction transaction = em.getTransaction();
        JugadorEntidad j = em.find(JugadorEntidad.class, jugador.getCodJugador());
        try {
            transaction.begin();
            if (j != null) {
                em.remove(j);
                transaction.commit();
            }
        } catch (Exception e) {
            transaction.rollback();
            throw new Exception("Error al intentar eliminar el jugador");
        }
    }

    public void modificar(JugadorEntidad jugador) throws Exception {
        EntityTransaction transaction = em.getTransaction();
        JugadorEntidad j = em.find(JugadorEntidad.class, jugador.getCodJugador());
        try {
            transaction.begin();
            if (j != null) {
                j.setNombre(jugador.getNombre());
                j.setApellido(jugador.getApellido());
                j.setPie(jugador.getPie());
                j.setAltura(jugador.getAltura());
                j.setAgenda(jugador.getAgenda());
                j.setContratos(jugador.getContratos());
                j.setRegistrosDeTemporadas(jugador.getRegistrosDeTemporadas());
                em.persist(j);
                transaction.commit();
            }
        } catch (Exception e) {
            transaction.rollback();
            throw new Exception("Error al intentar modificar el jugador");
        }
    }

    public List<JugadorEntidad> seleccionarTodosLosJugadores() {
        return em.createQuery("SELECT j FROM JugadorEntidad j", JugadorEntidad.class)
                .getResultList();
    }

    public JugadorEntidad seleccionarJugadorPorId(int id) {
        return em.find(JugadorEntidad.class, id);
    }
}
