package com.boajp.repositorios;

import com.boajp.modelo.ContratoEquipoJugadorEntidad;
import com.boajp.vistas.componentes.PanelDeError;
import jakarta.persistence.*;

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
            transaction.begin();
            entityManager.persist(contratojugador);
            transaction.commit();
        }catch (Exception exception){
            transaction.rollback();
            new PanelDeError(exception.getCause().getCause().getCause().getMessage());
        } finally {
            entityManagerFactory.close();
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

    public void modificar (ContratoEquipoJugadorEntidad contratojugador) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            ContratoEquipoJugadorEntidad c = entityManager.find(ContratoEquipoJugadorEntidad.class, contratojugador.getCodContrato());
            c.setEquipo(contratojugador.getEquipo());
            c.setJugador(contratojugador.getJugador());
            c.setClausula(contratojugador.getClausula());
            c.setSalario(contratojugador.getSalario());
            c.setFechaInicio(contratojugador.getFechaInicio());
            c.setFechaFin(contratojugador.getFechaFin());
            entityManager.persist(c);
        } catch (Exception exception) {
            transaction.rollback();
            new PanelDeError(exception.getCause().getCause().getCause().getMessage());
        } finally {
            entityManager.close();
        }
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

    public List<ContratoEquipoJugadorEntidad> seleccionarSalarioPorEquipo() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            Query qNroCont_equi = entityManager.createNativeQuery("SELECT cod_equipo, SUM(salario) as salario_total_del_equipo\n" +
                    "    FROM contratos_equipo_jugador\n" +
                    "    GROUP BY cod_equipo ");
            List<ContratoEquipoJugadorEntidad> contratosjugador = qNroCont_equi.getResultList();
            return contratosjugador;
        }catch (Exception exception) {
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
