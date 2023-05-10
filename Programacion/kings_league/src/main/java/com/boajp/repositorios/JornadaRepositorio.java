package com.boajp.repositorios;

import com.boajp.modelo.JornadaEntidad;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class JornadaRepositorio {

    private final EntityManagerFactory entityManagerFactory;
    private final EntityManager entityManager;
    private EntityTransaction transaction;

    public JornadaRepositorio() {
        entityManagerFactory = Persistence.createEntityManagerFactory("default");
        entityManager = entityManagerFactory.createEntityManager();
    }

    public void insertar(JornadaEntidad jornada) throws Exception {
        transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(jornada);
            transaction.commit();
        } catch (Exception exception) {
            transaction.rollback();
            throw new Exception("Error al intentar insertar jornada");
        }
    }

    public void eliminar(JornadaEntidad jornada) throws Exception {
        transaction = entityManager.getTransaction();
        try {
            JornadaEntidad jornadaEncontrada = entityManager.find(JornadaEntidad.class, jornada.getCodJornada());
            transaction.begin();
            if (jornadaEncontrada != null)
                entityManager.remove(jornadaEncontrada);
            transaction.commit();
        } catch (Exception exception) {
            transaction.rollback();
            throw new Exception("Error al intentar eliminar jornada");
        }
    }

    public void modificar(JornadaEntidad jornada) throws Exception {
        transaction = entityManager.getTransaction();
        try {
            JornadaEntidad jornadaEncontrada = entityManager.find(JornadaEntidad.class, jornada.getCodJornada());
            transaction.begin();
            jornadaEncontrada.setNumero(jornada.getNumero());
            jornadaEncontrada.setFecha(jornada.getFecha());
            jornadaEncontrada.setUbicacion(jornada.getUbicacion());
            jornadaEncontrada.setSplit(jornada.getSplit());
            jornadaEncontrada.setListaPartidos(jornada.getListaPartidos());
            if (jornadaEncontrada != null)
                entityManager.persist(jornadaEncontrada);
            transaction.commit();

        } catch (Exception exception) {
            transaction.rollback();
            throw new Exception("Error al intentar modificar jornada");
        }
    }

    public List<JornadaEntidad> buscarTodasJornadas() throws Exception {
        try {
            String jpql = "SELECT j FROM JornadaEntidad j";
            TypedQuery<JornadaEntidad> query = entityManager.createQuery(jpql, JornadaEntidad.class);
            return query.getResultList();
        } catch (Exception exception) {
            throw new Exception("Error al intentar extraer jornadas.", exception);
        }
    }
}
