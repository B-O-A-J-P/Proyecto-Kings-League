package com.boajp.repositorios;

import com.boajp.modelo.ClasificacionEntidad;
import jakarta.persistence.*;

import java.util.List;

public class ClasificacionRepositorio {

    private final EntityManagerFactory entityManagerFactory;

    public ClasificacionRepositorio() {
        entityManagerFactory = AdministradorPersistencia.getEntityManagerFactory();
    }

    public void insertar(ClasificacionEntidad clasificacion) throws Exception {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(clasificacion);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw new Exception("Error al intentar insertar la clasificación");
        } finally {
            entityManager.close();
        }
    }

    public void eliminar(ClasificacionEntidad clasificacion) throws Exception {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            ClasificacionEntidad c = entityManager.find(ClasificacionEntidad.class, clasificacion.getSplit());
            if (c != null) {
                entityManager.remove(c);
            }
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw new Exception("Error al intentar eliminar la clasificación");
        } finally {
            entityManager.close();
        }
    }

    public void modificar(ClasificacionEntidad clasificacion) throws Exception {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            ClasificacionEntidad c = entityManager.find(ClasificacionEntidad.class, clasificacion.getSplit());
            if (c != null) {
                c.setPosicion(clasificacion.getPosicion());
                c.setSplit(clasificacion.getSplit());
                c.setEquipo(clasificacion.getEquipo());
                entityManager.persist(c);
            }
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw new Exception("Error al intentar modificar la clasificación");
        } finally {
            entityManager.close();
        }
    }

    public List<ClasificacionEntidad> seleccionarTodasLasClasificaciones() throws Exception{
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            String sql = "SELECT c FROM ClasificacionEntidad c JOIN FETCH c.equipo JOIN FETCH c.split";
            TypedQuery<ClasificacionEntidad> resultado = entityManager.createQuery(sql, ClasificacionEntidad.class);
            return  resultado.getResultList();
        } catch (Exception exception) {
            throw new Exception("Error al intentar extraer clasificaciones.", exception);
        } finally {
            entityManager.close();
        }
    }


    public List<ClasificacionEntidad> buscarUltimaClasificacion() throws Exception {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            String query = "SELECT c FROM ClasificacionEntidad c WHERE c.split.codSplit = (SELECT MAX(ce.split.codSplit) FROM ClasificacionEntidad ce ) ORDER BY c.posicion ASC";
            TypedQuery<ClasificacionEntidad> resultado = entityManager.createQuery(query, ClasificacionEntidad.class);
            return resultado.getResultList();
        } catch (NoResultException e) {
            return null;
        } catch (Exception exception) {
            throw new Exception("Error al intentar extraer clasificaciones.", exception);
        } finally {
            entityManager.close();
        }
    }


}

