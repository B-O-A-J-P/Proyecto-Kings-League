package com.boajp.repositorios;

import com.boajp.modelo.TemporadaEntidad;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

public class TemporadaRepositorio {

    private final EntityManagerFactory entityManagerFactory;
    private final EntityManager entityManager;
    private EntityTransaction transaction;

    public TemporadaRepositorio() {
        entityManagerFactory = Persistence.createEntityManagerFactory("default");
        entityManager = entityManagerFactory.createEntityManager();
    }

    public void insertar(TemporadaEntidad temporada) throws Exception{
        transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(temporada);
            transaction.commit();
        } catch (Exception exception){
            transaction.rollback();
            throw new Exception("Error al intentar insertar temporada");
        }
    }

    public void eliminar(TemporadaEntidad temporada) throws Exception{
        transaction = entityManager.getTransaction();
        try {
            TemporadaEntidad temp = entityManager.find(TemporadaEntidad.class, temporada.getCodTemporada());;
            transaction.begin();
            if (temp != null)
                entityManager.remove(temp);
            transaction.commit();
        } catch (Exception exception){
            transaction.rollback();
            throw new Exception("Error al intentar eliminar temporada");
        }
    }

    public void modificar(TemporadaEntidad temporada) throws Exception{
        transaction = entityManager.getTransaction();
        try {
            TemporadaEntidad temp = entityManager.find(TemporadaEntidad.class, temporada.getCodTemporada());
            transaction.begin();
            temp.setAno(temporada.getAno());
            temp.setFechaInicioInscripcion(temporada.getFechaInicioInscripcion());
            temp.setFechaFinInscripcion(temporada.getFechaFinInscripcion());
            temp.setListaEquipos(temporada.getListaEquipos());
            temp.setListaSplits(temporada.getListaSplits());
            temp.setListaJugadores(temporada.getListaJugadores());
            if (temp != null)
                entityManager.persist(temp);
            transaction.commit();

        } catch (Exception exception) {
            transaction.rollback();
            throw new Exception("Error al intentar modificar temporada");
        }
    }

    public List<TemporadaEntidad> buscarTodasTemporadas() throws Exception {
        try {
            String jpql = "SELECT t FROM TemporadaEntidad t";
            TypedQuery<TemporadaEntidad> query = entityManager.createQuery(jpql, TemporadaEntidad.class);
            return query.getResultList();
        } catch (Exception exception) {
            throw new Exception("Error al intentar extraer temporadas.", exception);
        }
    }

}
