package com.boajp.repositorios;

import com.boajp.modelo.JornadaEntidad;
import com.boajp.vistas.componentes.PanelDeError;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

public class JornadaRepositorio {

    private final EntityManagerFactory entityManagerFactory;

    public JornadaRepositorio() {
        entityManagerFactory = AdministradorPersistencia.getEntityManagerFactory();
    }

    public void insertar(JornadaEntidad jornada) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(jornada);
            transaction.commit();
        } catch (Exception exception) {
            transaction.rollback();
            new PanelDeError(exception.getCause().getCause().getCause().getMessage());
        } finally {
            entityManager.close();
        }
    }

    public void eliminar(JornadaEntidad jornada) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            JornadaEntidad jornadaEncontrada = entityManager.find(JornadaEntidad.class, jornada.getCodJornada());
            if (jornadaEncontrada != null)
                entityManager.remove(jornadaEncontrada);
            transaction.commit();
        } catch (Exception exception) {
            transaction.rollback();
            new PanelDeError(exception.getCause().getCause().getCause().getMessage());
        } finally {
            entityManager.close();
        }
    }

    public void modificar(JornadaEntidad jornada) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            JornadaEntidad jornadaEncontrada = entityManager.find(JornadaEntidad.class, jornada.getCodJornada());
            jornadaEncontrada.setNumero(jornada.getNumero());
            jornadaEncontrada.setFecha(jornada.getFecha());
            jornadaEncontrada.setUbicacion(jornada.getUbicacion());
            jornadaEncontrada.setSplit(jornada.getSplit());
            jornadaEncontrada.setListaPartidos(jornada.getListaPartidos());
            entityManager.persist(jornadaEncontrada);
            transaction.commit();
        } catch (Exception exception) {
            transaction.rollback();
            new PanelDeError(exception.getCause().getCause().getCause().getMessage());
        } finally {
            entityManager.close();
        }
    }

    public List<JornadaEntidad> buscarTodasJornadas() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            String jpql = "SELECT j FROM JornadaEntidad j JOIN FETCH j.split";
            TypedQuery<JornadaEntidad> query = entityManager.createQuery(jpql, JornadaEntidad.class);
            return query.getResultList();
        } catch (Exception exception) {
            new PanelDeError(exception.getCause().getCause().getCause().getMessage());
        } finally {
            entityManager.close();
        }
        return new ArrayList<>();
    }

    public JornadaEntidad buscarJornada(int codigo) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            String jpql = "SELECT j FROM JornadaEntidad j JOIN FETCH j.split WHERE j.codJornada = :codigo";
            TypedQuery<JornadaEntidad> query = entityManager.createQuery(jpql, JornadaEntidad.class);
            query.setParameter("codigo", codigo);
            return query.getSingleResult();
        } catch (Exception exception) {
            new PanelDeError(exception.getCause().getCause().getCause().getMessage());
        } finally {
            entityManager.close();
        }
        return null;
    }

    public JornadaEntidad buscarUltimaJornada() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            String query = "SELECT j FROM JornadaEntidad j WHERE j.codJornada = (SELECT MAX(m.codJornada) FROM JornadaEntidad m)";
            TypedQuery<JornadaEntidad> resultado = entityManager.createQuery(query, JornadaEntidad.class);
            return resultado.getSingleResult();
        } catch (NoResultException e) {
            return null;
        } catch (Exception exception) {
            new PanelDeError(exception.getCause().getCause().getCause().getMessage());
        } finally {
           entityManager.close();
        }
        return null;
    }
}
