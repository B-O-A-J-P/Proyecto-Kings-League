package com.boajp.repositorios;

import com.boajp.modelo.SplitEntidad;
import com.boajp.modelo.TemporadaEntidad;
import com.boajp.servicios.TemporadasServicio;
import com.boajp.vistas.componentes.PanelDeError;
import jakarta.persistence.*;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SplitRepositorio {

    private final EntityManagerFactory entityManagerFactory;

    public SplitRepositorio(){
        entityManagerFactory = AdministradorPersistencia.getEntityManagerFactory();
    }

    public void insertar (SplitEntidad split) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            TemporadaEntidad temporada = entityManager.find(TemporadaEntidad.class, split.getTemporada().getCodTemporada());
            split.setTemporada(temporada);
            transaction.begin();
            entityManager.merge(split);
            transaction.commit();
        } catch (Exception exception) {
            transaction.rollback();
            new PanelDeError(exception.getCause().getCause().getCause().getMessage());
        } finally {
            entityManager.close();
        }
    }

    public void eliminar (SplitEntidad split) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            SplitEntidad s = entityManager.find(SplitEntidad.class, split.getCodSplit());
            if (s != null) {
                entityManager.remove(s);
            }
            transaction.commit();
        }catch (Exception exception){
            transaction.rollback();
            new PanelDeError(exception.getCause().getCause().getCause().getMessage());
        } finally {
            entityManager.close();
        }
    }

    public void eliminar (int codigo) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        SplitEntidad s = entityManager.find(SplitEntidad.class, codigo);
        try {
            transaction.begin();
            if (s != null) {
                entityManager.remove(s);
            }
            transaction.commit();
        }catch (Exception exception){
            transaction.rollback();
            new PanelDeError(exception.getCause().getCause().getCause().getMessage());
        } finally {
            entityManager.close();
        }
    }

    public void modificar (SplitEntidad split) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction ();
        try {
            transaction.begin();
            SplitEntidad s = entityManager.find(SplitEntidad.class, split.getCodSplit());
            TemporadaEntidad temporada = entityManager.find(TemporadaEntidad.class, split.getTemporada().getCodTemporada());
            s.setNombre(split.getNombre());
            s.setFechaFin(split.getFechaFin());
            s.setFechaInicio(split.getFechaInicio());
            s.setTemporada(temporada);
            s.setListaJornadas(split.getListaJornadas());
            s.setTablaClasificaciones(split.getTablaClasificaciones());
            entityManager.merge(s);
            transaction.commit();
        }catch (Exception exception){
            transaction.rollback();
            new PanelDeError(exception.getCause().getCause().getCause().getMessage());
        } finally {
            entityManager.close();
        }
    }

    public SplitEntidad buscarSplit(int codigo) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            String jpql = "SELECT s FROM SplitEntidad s WHERE s.codSplit = :codigo";
            TypedQuery<SplitEntidad> query = entityManager.createQuery(jpql, SplitEntidad.class);
            query.setParameter("codigo", codigo);
            return query.getSingleResult();
        } catch (Exception exception) {
            new PanelDeError(exception.getCause().getCause().getCause().getMessage());
        } finally {
            entityManager.close();
        }
        return null;
    }

    public List<SplitEntidad> buscarSplits(){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            String jpql = "SELECT s FROM SplitEntidad s";
            TypedQuery<SplitEntidad> query = entityManager.createQuery(jpql, SplitEntidad.class);
            return query.getResultList();
        } catch (Exception exception) {
            new PanelDeError(exception.getCause().getCause().getCause().getMessage());
        } finally {
            entityManager.close();
        }
        return new ArrayList<>();
    }

}