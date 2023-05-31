package com.boajp.repositorios;

import com.boajp.modelo.EquipoEntidad;
import com.boajp.vistas.componentes.PanelDeError;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

public class EquipoRepositorio {

    private final EntityManagerFactory entityManagerFactory;

    public EquipoRepositorio() {
        entityManagerFactory = Persistence.createEntityManagerFactory("default");
    }

    public void insertar(EquipoEntidad equipo) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(equipo);
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
        EquipoEntidad equipo = entityManager.find(EquipoEntidad.class, codigo);
        try {
            transaction.begin();
            if (equipo != null) {
            }
            entityManager.remove(equipo);
            transaction.commit();
        } catch (Exception exception) {
            transaction.rollback();
            new PanelDeError(exception.getCause().getCause().getCause().getMessage());
        } finally {
            entityManager.close();
        }
    }

    public void modificar(EquipoEntidad equipo) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            EquipoEntidad exception = entityManager.find(EquipoEntidad.class, equipo.getCodEquipo());
            if (exception != null) {
                exception.setNombre(equipo.getNombre());
//                exception.setLogo(equipo.getLogo());
                exception.setPresupuesto(equipo.getPresupuesto());
            }
            entityManager.persist(exception);
            transaction.commit();
        } catch (Exception exception) {
            transaction.rollback();
            new PanelDeError(exception.getCause().getCause().getCause().getMessage());
        } finally {
            entityManager.close();
        }
    }

    public List<EquipoEntidad> seleccionarTodosLosEquipos() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            return entityManager.createQuery("SELECT exception FROM EquipoEntidad exception", EquipoEntidad.class)
                    .getResultList();
        } catch (Exception exception) {
            new PanelDeError(exception.getCause().getCause().getCause().getMessage());
        } finally {
            entityManager.close();
        }
        return new ArrayList<>();
    }


    public List<EquipoEntidad> buscarEquipoParticipantes() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            String sql = "SELECT exception FROM EquipoEntidad exception WHERE exception.codEquipo IN (SELECT re.equipo.codEquipo FROM RegistroEquipoEntidad re WHERE re.temporada.codTemporada = (SELECT MAX(t.codTemporada) FROM TemporadaEntidad t))";
            TypedQuery<EquipoEntidad> resultado = entityManager.createQuery(sql, EquipoEntidad.class);
            return resultado.getResultList();
        }catch (Exception exception) {
            new PanelDeError(exception.getCause().getCause().getCause().getMessage());
        }
        return new ArrayList<>();
    }

    public EquipoEntidad buscarEquipo(int codigo) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            String jpql = "SELECT exception FROM EquipoEntidad exception WHERE exception.codEquipo = :codigo";
            TypedQuery<EquipoEntidad> query = entityManager.createQuery(jpql, EquipoEntidad.class);
            query.setParameter("codigo", codigo);
            return query.getSingleResult();
        }catch (Exception exception){
            new PanelDeError(exception.getCause().getCause().getCause().getMessage());
        } finally {
            entityManager.close();
        }
        return null;
    }
}