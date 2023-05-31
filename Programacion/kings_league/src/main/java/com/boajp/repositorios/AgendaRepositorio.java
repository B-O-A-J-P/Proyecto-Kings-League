package com.boajp.repositorios;

import com.boajp.modelo.AgendaEntidad;
import com.boajp.vistas.componentes.PanelDeError;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

public class AgendaRepositorio {
    private final EntityManagerFactory entityManagerFactory;

    public AgendaRepositorio(){
        entityManagerFactory = AdministradorPersistencia.getEntityManagerFactory();
    }

    public void insertar (AgendaEntidad agenda) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction ();
        try {
            transaction.begin();
            entityManager.persist(agenda);
            transaction.commit();
        }catch (Exception exception){
            transaction.rollback();
            new PanelDeError(exception.getCause().getCause().getCause().getMessage());
        } finally {
            entityManager.close();
        }
    }

    public void eliminar (AgendaEntidad agenda) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            AgendaEntidad a = entityManager.find(AgendaEntidad.class, agenda.getCodAgenda());
            if (agenda != null) {
                entityManager.remove(agenda);
            }
            transaction.commit();
        }catch (Exception exception){
            transaction.rollback();
            new PanelDeError(exception.getCause().getCause().getCause().getMessage());
        } finally {
            entityManager.close();
        }
    }

    public void modificar (AgendaEntidad agenda) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            AgendaEntidad a = entityManager.find(AgendaEntidad.class, agenda.getCodAgenda());
            a.setEmail(agenda.getEmail());
            a.setTelefono(agenda.getTelefono());
            entityManager.persist(a);
        }catch (Exception exception){
            transaction.rollback();
            new PanelDeError(exception.getCause().getCause().getCause().getMessage());
        } finally {
            entityManager.close();
        }
    }

    public List<AgendaEntidad> seleccionarTodosLasAgendas() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            String sql = "SELECT a FROM AgendaEntidad a";
            TypedQuery<AgendaEntidad> resultado = entityManager.createQuery(sql, AgendaEntidad.class);
            return resultado.getResultList();
        } catch (Exception exception){
            new PanelDeError(exception.getCause().getCause().getCause().getMessage());
        } finally {
            entityManager.close();
        }
        return new ArrayList<>();
    }
}
