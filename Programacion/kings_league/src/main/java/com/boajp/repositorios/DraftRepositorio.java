package com.boajp.repositorios;

import com.boajp.modelo.DraftEntidad;
import com.boajp.vistas.componentes.PanelDeError;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

public class DraftRepositorio {

    private final EntityManagerFactory entityManagerFactory;

    public DraftRepositorio(){
        entityManagerFactory = AdministradorPersistencia.getEntityManagerFactory();
    }

    public void insertar (DraftEntidad draft) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(draft);
            transaction.commit();
        }catch (Exception exception){
            transaction.rollback();
            new PanelDeError(exception.getCause().getCause().getCause().getMessage());
        } finally {
            entityManager.close();
        }
    }

    public void eliminar (DraftEntidad draft) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction ();
        try {
            transaction.begin();
            DraftEntidad d = entityManager.find(DraftEntidad.class, draft.getRegistroJugador());
            if (draft != null) {
                entityManager.remove(draft);
            }
            transaction.commit();
        }catch (Exception exception){
            transaction.rollback();
            new PanelDeError(exception.getCause().getCause().getCause().getMessage());
        } finally {
            entityManager.close();
        }
    }

    public void modificar (DraftEntidad draft) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction ();
        try {
            transaction.begin();
            DraftEntidad d = entityManager.find(DraftEntidad.class, draft.getRegistroJugador());
            if (draft != null){
                d.setRegistroJugador(draft.getRegistroJugador());
                d.setPosicion(draft.getPosicion());
                entityManager.persist(d);
            }
            transaction.commit();
        } catch (Exception exception){
            transaction.rollback();
            new PanelDeError(exception.getCause().getCause().getCause().getMessage());
        } finally {
            entityManager.close();
        }
    }

    public List<DraftEntidad> seleccionarTodosLosDrafts() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            String sql = "SELECT d FROM DraftEntidad d JOIN FETCH d.registroJugador.jugador";
            TypedQuery<DraftEntidad> resultado = entityManager.createQuery(sql, DraftEntidad.class);
            return resultado.getResultList();
        } catch (Exception exception) {
            new PanelDeError(exception.getCause().getCause().getCause().getMessage());
        } finally {
            entityManager.close();
        }
        return new ArrayList<>();
    }
}
