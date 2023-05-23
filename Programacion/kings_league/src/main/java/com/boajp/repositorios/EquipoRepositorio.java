package com.boajp.repositorios;

import com.boajp.modelo.EquipoEntidad;
import jakarta.persistence.*;

import java.util.List;

public class EquipoRepositorio {

    private final EntityManagerFactory entityManagerFactory;
    public EquipoRepositorio() {
        entityManagerFactory = AdministradorPersistencia.getEntityManagerFactory();
    }

    public void insertar(EquipoEntidad equipo) throws Exception {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(equipo);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw new Exception("Error al intentar insertar el equipo");
        } finally {
            entityManager.close();
        }
    }

    public void eliminar(EquipoEntidad equipo) throws Exception {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        EquipoEntidad e = entityManager.find(EquipoEntidad.class, equipo.getCodEquipo());
        try {
            transaction.begin();
            if (e != null) {
                entityManager.remove(e);
            }
            transaction.commit();
        } catch (Exception ex) {
            transaction.rollback();
            throw new Exception("Error al intentar eliminar el equipo");
        } finally {
            entityManager.close();
        }
    }

    public void modificar(EquipoEntidad equipo) throws Exception {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            EquipoEntidad e = entityManager.find(EquipoEntidad.class, equipo.getCodEquipo());
            if (e != null) {
                e.setNombre(equipo.getNombre());
                e.setLogo(equipo.getLogo());
                e.setPresupuesto(equipo.getPresupuesto());
                entityManager.persist(e);
            }
            transaction.commit();
        } catch (Exception ex) {
            transaction.rollback();
            throw new Exception("Error al intentar modificar el equipo");
        } finally {
            entityManager.close();
        }
    }

    public List<EquipoEntidad> seleccionarTodosLosEquipos() throws Exception{
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            return entityManager.createQuery("SELECT e FROM EquipoEntidad e", EquipoEntidad.class)
                    .getResultList();
        } catch (Exception exception) {
            throw new Exception("Error al intentar extraer equipos");
        } finally {
            entityManager.close();
        }
    }

    public EquipoEntidad seleccionarEquipoPorId(int id) throws Exception{
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            return entityManager.find(EquipoEntidad.class, id);
        } catch (Exception exception) {
            throw new Exception("Error al intentar extraer equipo");
        } finally {
            entityManager.close();
        }
    }

    public List<EquipoEntidad> buscarEquipoParticipantes() throws Exception{
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            String sql = "SELECT e FROM EquipoEntidad e WHERE e.codEquipo IN (SELECT re.equipo.codEquipo FROM RegistroEquipoEntidad re WHERE re.temporada.codTemporada = (SELECT MAX(t.codTemporada) FROM TemporadaEntidad t))";
            TypedQuery<EquipoEntidad> resultado = entityManager.createQuery(sql, EquipoEntidad.class);
            return resultado.getResultList();
        }catch (Exception exception) {
            throw new Exception("Error al intentar extraer equipos");
        } finally {
            entityManager.close();
        }
    }

}
