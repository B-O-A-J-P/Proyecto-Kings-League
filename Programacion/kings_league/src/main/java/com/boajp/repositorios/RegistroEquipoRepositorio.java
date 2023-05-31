package com.boajp.repositorios;

import com.boajp.modelo.EquipoEntidad;
import com.boajp.modelo.RegistroEquipoEntidad;
import com.boajp.modelo.TemporadaEntidad;
import com.boajp.vistas.componentes.PanelDeError;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

public class RegistroEquipoRepositorio {

    private final EntityManagerFactory entityManagerFactory;

    public RegistroEquipoRepositorio(){
        entityManagerFactory = AdministradorPersistencia.getEntityManagerFactory();
    }
    public void insertar (RegistroEquipoEntidad registroEquipo) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            TemporadaEntidad temporada = entityManager.find(TemporadaEntidad.class, registroEquipo.getTemporada().getCodTemporada());
            EquipoEntidad equipo = entityManager.find(EquipoEntidad.class, registroEquipo.getEquipo().getCodEquipo());
            transaction.begin();
            registroEquipo.setEquipo(equipo);
            registroEquipo.setTemporada(temporada);
            entityManager.persist(registroEquipo);
            transaction.commit();
        }catch (Exception exception){
            transaction.rollback();
            new PanelDeError(exception.getCause().getCause().getCause().getMessage());
        } finally {
            entityManager.close();
        }
    }
    public void eliminar (RegistroEquipoEntidad registroEquipo) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            RegistroEquipoEntidad r = entityManager.find(RegistroEquipoEntidad.class, registroEquipo.getTemporada());
            entityManager.remove(registroEquipo);
            transaction.commit();
        }catch (Exception exception){
            transaction.rollback();
            new PanelDeError(exception.getCause().getCause().getCause().getMessage());
        } finally {
            entityManager.close();
        }
    }

    public void modificar (RegistroEquipoEntidad registroEquipo) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            RegistroEquipoEntidad r = entityManager.find(RegistroEquipoEntidad.class, registroEquipo.getTemporada());
            r.setEquipo(registroEquipo.getEquipo());
            r.setTemporada(registroEquipo.getTemporada());
            entityManager.persist(r);
        }catch (Exception exception){
            transaction.rollback();
            new PanelDeError(exception.getCause().getCause().getCause().getMessage());
        } finally {
            entityManager.close();
        }
    }

    public List<RegistroEquipoEntidad> buscarTodosRegistrosDeEquipo() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            String sql = "SELECT re FROM RegistroEquipoEntidad re JOIN FETCH re.temporada JOIN FETCH re.equipo";
            TypedQuery<RegistroEquipoEntidad> resultado = entityManager.createQuery(sql, RegistroEquipoEntidad.class);
            return resultado.getResultList();
        } catch (Exception exception) {
            new PanelDeError(exception.getCause().getCause().getCause().getMessage());
        } finally {
            entityManager.close();
        }
        return new ArrayList<>();
    }

    public RegistroEquipoEntidad buscar(int codigoTemporada, int codigoEquipo) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            String sql = "SELECT re FROM RegistroEquipoEntidad re JOIN FETCH re.temporada JOIN FETCH re.equipo WHERE re.equipo.codEquipo = :codigoEquipo AND re.temporada.codTemporada = :codigoTemporada";
            TypedQuery<RegistroEquipoEntidad> resultado = entityManager.createQuery(sql, RegistroEquipoEntidad.class);
            resultado.setParameter("codigoTemporada", codigoTemporada);
            resultado.setParameter("codigoEquipo", codigoEquipo);
            return resultado.getSingleResult();
        } catch (Exception exception) {
            new PanelDeError(exception.getCause().getCause().getCause().getMessage());
        } finally {
            entityManager.close();
        }
        return null;
    }

    public List<RegistroEquipoEntidad> buscarEquiposParticipantesUltimaTemporada() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            String query = "SELECT re FROM RegistroEquipoEntidad re WHERE re.temporada.codTemporada = (SELECT MAX(m.temporada.codTemporada) FROM RegistroEquipoEntidad m)";
            TypedQuery<RegistroEquipoEntidad> resultado = entityManager.createQuery(query, RegistroEquipoEntidad.class);
            return resultado.getResultList();
        } catch (NoResultException e) {
            return null;
        } catch (Exception exception) {
            new PanelDeError(exception.getCause().getCause().getCause().getMessage());
        } finally {
            entityManager.close();
        }
        return new ArrayList<>();
    }
}



