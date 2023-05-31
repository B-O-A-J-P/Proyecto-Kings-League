package com.boajp.repositorios;

import com.boajp.modelo.ContratoEquipoJugadorEntidad;
import com.boajp.modelo.EquipoEntidad;
import com.boajp.modelo.JugadorEntidad;
import com.boajp.vistas.componentes.PanelDeError;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ContratoEquipoJugadorRepositorio {

    private final EntityManagerFactory entityManagerFactory;


    public ContratoEquipoJugadorRepositorio(){
        entityManagerFactory = AdministradorPersistencia.getEntityManagerFactory();
    }

    public void insertar (ContratoEquipoJugadorEntidad contratojugador) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction ();
        try {
            EquipoEntidad equipo = entityManager.find(EquipoEntidad.class, contratojugador.getEquipo().getCodEquipo());
            JugadorEntidad jugador = entityManager.find(JugadorEntidad.class, contratojugador.getJugador().getCodJugador());
            transaction.begin();
            contratojugador.setEquipo(equipo);
            contratojugador.setJugador(jugador);
            entityManager.persist(contratojugador);
            transaction.commit();
        }catch (Exception exception){
            transaction.rollback();
            new PanelDeError(exception.getCause().getCause().getCause().getMessage());
        } finally {
            entityManager.close();
        }
    }

    public void eliminar (ContratoEquipoJugadorEntidad contratojugador) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction ();
        try {
            transaction.begin();
            ContratoEquipoJugadorEntidad c = entityManager.find(ContratoEquipoJugadorEntidad.class, contratojugador.getCodContrato());
            entityManager.remove(contratojugador);
            transaction.commit();
        }catch (Exception exception){
            transaction.rollback();
            new PanelDeError(exception.getCause().getCause().getCause().getMessage());
        } finally {
            entityManager.close();
        }
    }

    public void eliminar (int codigoEquipo, int codigoJugador, LocalDate fecha) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction ();
        try {
            transaction.begin();
            String sql = "SELECT c FROM ContratoEquipoJugadorEntidad c WHERE c.equipo.codEquipo = :codigoEquipo AND c.jugador.codJugador = :codigoJugador AND c.fechaInicio = :fecha";
            TypedQuery<ContratoEquipoJugadorEntidad> resultado = entityManager.createQuery(sql, ContratoEquipoJugadorEntidad.class);
            resultado.setParameter("codigoEquipo", codigoEquipo);
            resultado.setParameter("codigoJugador", codigoJugador);
            resultado.setParameter("fecha", fecha);
            entityManager.remove(resultado.getSingleResult());
            transaction.commit();
        }catch (Exception exception){
            transaction.rollback();
            new PanelDeError(exception.getCause().getCause().getCause().getMessage());
        } finally {
            entityManager.close();
        }
    }

    public void modificar (ContratoEquipoJugadorEntidad contratojugador) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            EquipoEntidad equipo = entityManager.find(EquipoEntidad.class, contratojugador.getEquipo().getCodEquipo());
            JugadorEntidad jugador = entityManager.find(JugadorEntidad.class, contratojugador.getJugador().getCodJugador());
            transaction.begin();
            ContratoEquipoJugadorEntidad c = entityManager.find(ContratoEquipoJugadorEntidad.class, contratojugador.getCodContrato());
            c.setEquipo(equipo);
            c.setJugador(jugador);
            c.setClausula(contratojugador.getClausula());
            c.setSalario(contratojugador.getSalario());
            c.setFechaInicio(contratojugador.getFechaInicio());
            c.setFechaFin(contratojugador.getFechaFin());
            entityManager.persist(c);
            transaction.commit();
        } catch (Exception exception) {
            transaction.rollback();
            new PanelDeError(exception.getCause().getCause().getCause().getMessage());
        } finally {
            entityManager.close();
        }
    }

    public ContratoEquipoJugadorEntidad buscar(EquipoEntidad equipo, JugadorEntidad jugador, LocalDate fecha) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        int codJug = jugador.getCodJugador();
        int codEqu = equipo.getCodEquipo();
        try {
            String sql = "SELECT c FROM ContratoEquipoJugadorEntidad c WHERE c.jugador.codJugador = :codJug AND c.equipo.codEquipo = :codEqu AND c.fechaInicio = :fecha";
            TypedQuery<ContratoEquipoJugadorEntidad> resultado = entityManager.createQuery(sql, ContratoEquipoJugadorEntidad.class);
            resultado.setParameter("codJug", codJug);
            resultado.setParameter("codEqu", codEqu);
            resultado.setParameter("fecha", fecha);
            return resultado.getSingleResult();
        } catch (Exception exception) {
            new PanelDeError(exception.getCause().getCause().getCause().getMessage());
        } finally {
            entityManager.close();
        }
        return null;
    }


    public List<ContratoEquipoJugadorEntidad> seleccionarTodosLosContratosDeJugador() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            String sql = "SELECT cej FROM ContratoEquipoJugadorEntidad cej JOIN FETCH cej.equipo JOIN FETCH cej.jugador";
            TypedQuery<ContratoEquipoJugadorEntidad> resultado = entityManager.createQuery(sql, ContratoEquipoJugadorEntidad.class);
            return resultado.getResultList();
        } catch (Exception exception) {
            new PanelDeError(exception.getCause().getCause().getCause().getMessage());
        } finally {
            entityManager.close();
        }
        return new ArrayList<>();
    }

    public List<ContratoEquipoJugadorEntidad> buscarContratosVigentes() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            String sql = "SELECT ce FROM ContratoEquipoJugadorEntidad ce " +
                    "WHERE (ce.fechaFin > current_date OR ce.fechaFin IS NULL) " +
                    "AND ce.equipo.codEquipo IN " +
                    "(SELECT e.codEquipo FROM EquipoEntidad e WHERE e.codEquipo IN " +
                    "(SELECT re.equipo.codEquipo FROM RegistroEquipoEntidad re WHERE re.temporada.codTemporada = " +
                    "(SELECT MAX(t.codTemporada) FROM TemporadaEntidad t)))";
            TypedQuery<ContratoEquipoJugadorEntidad> query = entityManager.createQuery(sql, ContratoEquipoJugadorEntidad.class);
            return query.getResultList();
        } catch (Exception exception) {
            new PanelDeError(exception.getCause().getCause().getCause().getMessage());
        } finally {
            entityManager.close();
        }
        return new ArrayList<>();
    }


}
