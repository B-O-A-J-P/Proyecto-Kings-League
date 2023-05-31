package com.boajp.repositorios;

import com.boajp.modelo.TemporadaEntidad;
import com.boajp.vistas.componentes.PanelDeError;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

public class TemporadaRepositorio {

    private final EntityManagerFactory entityManagerFactory;

    public TemporadaRepositorio() {
        this.entityManagerFactory = AdministradorPersistencia.getEntityManagerFactory();
    }

    public void insertar(TemporadaEntidad temporada) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(temporada);
            transaction.commit();
        } catch (Exception exception){
            transaction.rollback();
            new PanelDeError(exception.getCause().getCause().getCause().getMessage());
        } finally {
            entityManager.close();
        }
    }

    public void eliminar(TemporadaEntidad temporada) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            TemporadaEntidad temp = entityManager.find(TemporadaEntidad.class, temporada.getCodTemporada());;
            if (temp != null)
                entityManager.remove(temp);
            transaction.commit();
        } catch (Exception exception){
            transaction.rollback();
            new PanelDeError(exception.getCause().getCause().getCause().getMessage());
        } finally {
            entityManager.close();
        }
    }

    public void eliminar(int codigo) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            TemporadaEntidad temp = entityManager.find(TemporadaEntidad.class, codigo);;
            if (temp != null)
                entityManager.remove(temp);
            transaction.commit();
        } catch (Exception exception){
            transaction.rollback();
            new PanelDeError(exception.getCause().getCause().getCause().getMessage());
        } finally {
            entityManager.close();
        }
    }

    public void eliminar(int[] codigos) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            for (int x : codigos) {
                TemporadaEntidad temp = entityManager.find(TemporadaEntidad.class, codigos[x]);;
                if (temp != null)
                    entityManager.remove(temp);
            }
            transaction.commit();
        } catch (Exception exception){
            transaction.rollback();
            new PanelDeError(exception.getCause().getCause().getCause().getMessage());
        } finally {
            entityManager.close();
        }
    }

    public void modificar(TemporadaEntidad temporada) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            TemporadaEntidad temp = entityManager.find(TemporadaEntidad.class, temporada.getCodTemporada());
            temp.setAno(temporada.getAno());
            temp.setFechaInicioInscripcion(temporada.getFechaInicioInscripcion());
            temp.setFechaFinInscripcion(temporada.getFechaFinInscripcion());
            temp.setListaEquipos(temporada.getListaEquipos());
            temp.setListaSplits(temporada.getListaSplits());
            temp.setListaJugadores(temporada.getListaJugadores());
            entityManager.persist(temp);
            transaction.commit();
        } catch (Exception exception) {
            transaction.rollback();
            new PanelDeError(exception.getCause().getCause().getCause().getMessage());
        } finally {
            entityManager.close();
        }
    }

    public TemporadaEntidad buscarTemporada(int codigo) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            String jpql = "SELECT t FROM TemporadaEntidad t WHERE t.codTemporada = :codigo";
            TypedQuery<TemporadaEntidad> query = entityManager.createQuery(jpql, TemporadaEntidad.class);
            query.setParameter("codigo", codigo);
            return query.getSingleResult();
        } catch (Exception exception) {
            new PanelDeError(exception.getCause().getCause().getCause().getMessage());
        } finally {
            entityManager.close();
        }
        return null;
    }

    public List<TemporadaEntidad> buscarTodasTemporadas() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            String jpql = "SELECT t FROM TemporadaEntidad t";
            TypedQuery<TemporadaEntidad> query = entityManager.createQuery(jpql, TemporadaEntidad.class);
            return query.getResultList();
        } catch (Exception exception) {
            new PanelDeError(exception.getCause().getCause().getCause().getMessage());
        } finally {
            entityManager.close();
        }
        return new ArrayList<>();
    }

}
