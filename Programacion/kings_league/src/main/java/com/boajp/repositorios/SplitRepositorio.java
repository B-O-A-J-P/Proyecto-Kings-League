package com.boajp.repositorios;

import com.boajp.modelo.SplitEntidad;
import com.boajp.modelo.TemporadaEntidad;
import com.boajp.servicios.TemporadasServicio;
import jakarta.persistence.*;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class SplitRepositorio {

    private final EntityManagerFactory entityManagerFactory;

    public SplitRepositorio(){
        entityManagerFactory = AdministradorPersistencia.getEntityManagerFactory();
    }

    public void insertar (SplitEntidad split) throws Exception{
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.merge(split);
            transaction.commit();
        } catch (Exception exception) {
            transaction.rollback();
            System.out.println(exception.getMessage());
            throw exception;
        } finally {
            entityManager.close();
        }
    }

    public void eliminar (SplitEntidad split) throws Exception {
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
            throw new Exception("Error al intentar eliminar el split");
        } finally {
            entityManager.close();
        }
    }

    public void eliminar (int codigo) throws Exception {
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
            throw new Exception("Error al intentar eliminar el split");
        } finally {
            entityManager.close();
        }
    }

    public void modificar (SplitEntidad split) throws Exception {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction ();
        try {
            transaction.begin();
            SplitEntidad s = entityManager.find(SplitEntidad.class, split.getCodSplit());
            s.setNombre(split.getNombre());
            s.setFechaFin(split.getFechaFin());
            s.setFechaInicio(split.getFechaInicio());
            s.setTemporada(split.getTemporada());
            s.setListaJornadas(split.getListaJornadas());
            s.setTablaClasificaciones(split.getTablaClasificaciones());
            entityManager.persist(s);
            transaction.commit();
        }catch (Exception exception){
            transaction.rollback();
            throw new Exception("Error al intentar modificar el split");
        } finally {
            entityManager.close();
        }
    }

    public SplitEntidad buscarSplit(int codigo) throws Exception {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            String jpql = "SELECT s FROM SplitEntidad s WHERE s.codSplit = :codigo";
            TypedQuery<SplitEntidad> query = entityManager.createQuery(jpql, SplitEntidad.class);
            query.setParameter("codigo", codigo);
            return query.getSingleResult();
        } catch (Exception exception) {
            throw new Exception("Error al intentar extraer temporadas.", exception);
        } finally {
            entityManager.close();
        }
    }

    public List<SplitEntidad> buscarSplits() throws Exception {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            String jpql = "SELECT s FROM SplitEntidad s";
            TypedQuery<SplitEntidad> query = entityManager.createQuery(jpql, SplitEntidad.class);
            return query.getResultList();
        } catch (Exception exception) {
            throw new Exception("Error al intentar extraer temporadas.", exception);
        } finally {
            entityManager.close();
        }
    }

}