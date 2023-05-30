package com.boajp.repositorios;

import com.boajp.modelo.AgendaEntidad;
import com.boajp.modelo.MiembroEntidad;
import com.boajp.vistas.componentes.PanelDeError;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.util.ArrayList;
import java.util.List;

public class MiembroRepositorio {

    private final EntityManagerFactory entityManagerFactory;

    public MiembroRepositorio() {
        entityManagerFactory = AdministradorPersistencia.getEntityManagerFactory();
    }

    public void insertar(MiembroEntidad miembro) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(miembro);
            transaction.commit();
        } catch (Exception exception) {
            transaction.rollback();
            new PanelDeError(exception.getCause().getCause().getCause().getMessage());
        } finally {
            entityManager.close();
        }
    }

    public void eliminar(MiembroEntidad miembro) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            MiembroEntidad m = entityManager.find(MiembroEntidad.class, miembro.getCodMiembro());
            if (m != null) {
                entityManager.remove(m);
            }
            transaction.commit();
        } catch (Exception exception) {
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
            MiembroEntidad m = entityManager.find(MiembroEntidad.class, codigo);
            if (m != null) {
                entityManager.remove(m);
            }
            transaction.commit();
        } catch (Exception exception) {
            transaction.rollback();
            new PanelDeError(exception.getCause().getCause().getCause().getMessage());
        } finally {
            entityManager.close();
        }
    }

    public void modificar(MiembroEntidad miembro) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            AgendaEntidad agenda = null;

            MiembroEntidad m = entityManager.find(MiembroEntidad.class, miembro.getCodMiembro());
            m.setNombre(miembro.getNombre());
            m.setApellido(miembro.getApellido());
            m.setDni(miembro.getDni());
            if (miembro.getAgenda() != null) {
                agenda = entityManager.find(AgendaEntidad.class, miembro.getAgenda().getCodAgenda());
                m.setAgenda(agenda);
            }
            transaction.begin();
            entityManager.merge(m);
            transaction.commit();
        } catch (Exception exception) {
            transaction.rollback();
            new PanelDeError(exception.getCause().getCause().getCause().getMessage());
        } finally {
            entityManager.close();
        }
    }

    public List<MiembroEntidad> seleccionarTodosLosMiembros() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            return entityManager.createQuery("SELECT m FROM MiembroEntidad m", MiembroEntidad.class).getResultList();
        } catch (Exception exception) {
            new PanelDeError(exception.getCause().getCause().getCause().getMessage());
        } finally {
            entityManager.close();
        }
        return new ArrayList<>();
    }

    public MiembroEntidad buscar(int codigo) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            return entityManager.find(MiembroEntidad.class, codigo);
        } catch (Exception exception) {
            new PanelDeError(exception.getCause().getCause().getCause().getMessage());
        } finally {
            entityManager.close();
        }
        return null;
    }
}