package com.boajp.repositorios;

import com.boajp.modelo.JugadorEntidad;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import java.util.List;

public class JugadorRepositorio {

    private final EntityManagerFactory entityManagerFactory;

    public JugadorRepositorio() {
        entityManagerFactory = AdministradorPersistencia.getEntityManagerFactory();
    }

    public void insertar(JugadorEntidad jugador) throws Exception {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(jugador);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw e;
        } finally {
            entityManager.close();
        }
    }

    public void eliminar(JugadorEntidad jugador) throws Exception {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            JugadorEntidad j = entityManager.find(JugadorEntidad.class, jugador.getCodJugador());
            if (j != null) {
                entityManager.remove(j);
            }
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw new Exception("Error al intentar eliminar el jugador");
        } finally {
            entityManager.close();
        }
    }

    public void eliminar(int codigoJugador) throws Exception {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            JugadorEntidad j = entityManager.find(JugadorEntidad.class, codigoJugador);
            if (j != null) {
                entityManager.remove(j);
            }
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw new Exception("Error al intentar eliminar el jugador");
        } finally {
            entityManager.close();
        }
    }

    public void modificar(JugadorEntidad jugador) throws Exception {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            JugadorEntidad j = entityManager.find(JugadorEntidad.class, jugador.getCodJugador());
            if (j != null) {
                j.setNombre(jugador.getNombre());
                j.setApellido(jugador.getApellido());
                j.setPie(jugador.getPie());
                j.setAltura(jugador.getAltura());
                j.setContratos(jugador.getContratos());
                j.setRegistrosDeTemporadas(jugador.getRegistrosDeTemporadas());
                entityManager.merge(j);
            }
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw new Exception("Error al intentar modificar el jugador");
        } finally {
            entityManager.close();
        }
    }

    public List<JugadorEntidad> seleccionarTodosLosJugadores() throws Exception{
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            return entityManager.createQuery("SELECT j FROM JugadorEntidad j", JugadorEntidad.class).getResultList();
        } catch (Exception exception) {
            throw new Exception("Error al intentar extraer jugadors");
        }
    }

    public JugadorEntidad buscar(int id) throws Exception{
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            return entityManager.find(JugadorEntidad.class, id);
        } catch (Exception exception) {
            throw new Exception("Error al intentar extraer jugador");
        } finally {
            entityManager.close();
        }
    }
}
