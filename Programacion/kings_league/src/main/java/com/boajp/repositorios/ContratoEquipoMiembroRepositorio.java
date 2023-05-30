package com.boajp.repositorios;

import com.boajp.modelo.ContratoEquipoMiembroEntidad;
import com.boajp.vistas.componentes.PanelDeError;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

public class ContratoEquipoMiembroRepositorio {

    private final EntityManagerFactory entityManagerFactory;

    public ContratoEquipoMiembroRepositorio() {
        entityManagerFactory = AdministradorPersistencia.getEntityManagerFactory();
    }

    public void insertar(ContratoEquipoMiembroEntidad contrato) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(contrato);
            transaction.commit();
        } catch (Exception exception) {
            transaction.rollback();
            new PanelDeError(exception.getCause().getCause().getCause().getMessage());
        } finally {
            entityManager.close();
        }
    }

    public void eliminar(ContratoEquipoMiembroEntidad contrato) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        ContratoEquipoMiembroEntidad c = entityManager.find(ContratoEquipoMiembroEntidad.class, contrato.getCodContrato());
        try {
            transaction.begin();
            if (c != null) {
                entityManager.remove(c);
            }
            transaction.commit();
        } catch (Exception exception) {
            transaction.rollback();
            new PanelDeError(exception.getCause().getCause().getCause().getMessage());
        } finally {
            entityManager.close();
        }
    }

    public void modificar(ContratoEquipoMiembroEntidad contrato) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        ContratoEquipoMiembroEntidad c = entityManager.find(ContratoEquipoMiembroEntidad.class, contrato.getCodContrato());
        try {
            transaction.begin();
            if (c != null) {
                c.setFuncion(contrato.getFuncion());
                c.setFechaEntrada(contrato.getFechaEntrada());
                c.setFechaSalida(contrato.getFechaSalida());
                c.setEquipo(contrato.getEquipo());
                c.setMiembro(contrato.getMiembro());
                entityManager.persist(c);
            }
            transaction.commit();
        } catch (Exception exception) {
            transaction.rollback();
            new PanelDeError(exception.getCause().getCause().getCause().getMessage());
        } finally {
            entityManager.close();
        }
    }

    public List<ContratoEquipoMiembroEntidad> seleccionarTodosLosContratos() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            return entityManager.createQuery("SELECT c FROM ContratoEquipoMiembroEntidad c JOIN FETCH c.equipo JOIN FETCH c.miembro", ContratoEquipoMiembroEntidad.class)
                    .getResultList();
        } catch (Exception exception) {
            new PanelDeError(exception.getCause().getCause().getCause().getMessage());
        } finally {
            entityManager.close();
        }
        return new ArrayList<>();
    }

    public ContratoEquipoMiembroEntidad seleccionarContratoPorId(int id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            return entityManager.find(ContratoEquipoMiembroEntidad.class, id);
        } catch (Exception exception) {
            new PanelDeError(exception.getCause().getCause().getCause().getMessage());
        } finally {
            entityManager.close();
        }
        return null;
    }

    public List<ContratoEquipoMiembroEntidad> buscarContratosVigentes() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            String sql = "SELECT cm FROM ContratoEquipoMiembroEntidad cm WHERE (cm.fechaSalida > current_date OR cm.fechaSalida IS NULL)";
            TypedQuery<ContratoEquipoMiembroEntidad> resultado = entityManager.createQuery(sql, ContratoEquipoMiembroEntidad.class);
            return resultado.getResultList();
        } catch (Exception exception) {
            new PanelDeError(exception.getCause().getCause().getCause().getMessage());
        } finally {
            entityManager.close();
        }
        return new ArrayList<>();
    }
}

