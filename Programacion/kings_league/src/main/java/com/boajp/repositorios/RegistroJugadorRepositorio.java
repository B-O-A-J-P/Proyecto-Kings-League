package com.boajp.repositorios;

import com.boajp.modelo.RegistroJugadorEntidad;
import com.boajp.vistas.componentes.PanelDeError;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

public class RegistroJugadorRepositorio {

    private final EntityManagerFactory entityManagerFactory;

    public RegistroJugadorRepositorio() {
        entityManagerFactory = AdministradorPersistencia.getEntityManagerFactory();
    }

    public void insertar(RegistroJugadorEntidad registroJugador) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(registroJugador);
            transaction.commit();
        } catch (Exception exception) {
            transaction.rollback();
            new PanelDeError(exception.getCause().getCause().getCause().getMessage());
        } finally {
            entityManager.close();
        }
    }

    public void eliminar(RegistroJugadorEntidad registroJugador) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            RegistroJugadorEntidad registroJugadorEncontrado = entityManager.find(RegistroJugadorEntidad.class, registroJugador.getJugador().getCodJugador());
            if (registroJugadorEncontrado != null)
                entityManager.remove(registroJugadorEncontrado);
            transaction.commit();
        } catch (Exception exception) {
            transaction.rollback();
            new PanelDeError(exception.getCause().getCause().getCause().getMessage());
        } finally {
            entityManager.close();
        }
    }

    public void modificar(RegistroJugadorEntidad registroJugador) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            RegistroJugadorEntidad registroJugadorEncontrado = entityManager.find(RegistroJugadorEntidad.class, registroJugador.getJugador().getCodJugador());
            registroJugadorEncontrado.setTemporada(registroJugador.getTemporada());
            entityManager.persist(registroJugadorEncontrado);
            transaction.commit();
        } catch (Exception exception) {
            transaction.rollback();
            new PanelDeError(exception.getCause().getCause().getCause().getMessage());
        } finally {
            entityManager.close();
        }
    }

    public List<RegistroJugadorEntidad> buscarTodosRegistrosJugadores() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            String jpql = "SELECT r FROM RegistroJugadorEntidad r JOIN FETCH r.temporada JOIN FETCH r.jugador JOIN FETCH r.draft";
            TypedQuery<RegistroJugadorEntidad> query = entityManager.createQuery(jpql, RegistroJugadorEntidad.class);
            return query.getResultList();
        } catch (Exception exception) {
            new PanelDeError(exception.getCause().getCause().getCause().getMessage());
        } finally {
            entityManager.close();
        }
        return new ArrayList<>();
    }

    public List<RegistroJugadorEntidad> buscarJugadoresRegistradosUltimaTemporada() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            String query = "SELECT rj FROM RegistroJugadorEntidad rj WHERE rj.temporada.codTemporada = (SELECT MAX(m.temporada.codTemporada) FROM RegistroJugadorEntidad m)";
            TypedQuery<RegistroJugadorEntidad> resultado = entityManager.createQuery(query, RegistroJugadorEntidad.class);
            return resultado.getResultList();
        } catch (Exception exception) {
            new PanelDeError(exception.getCause().getCause().getCause().getMessage());
        } finally {
            entityManager.close();
        }
        return new ArrayList<>();
    }

}
