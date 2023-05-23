package com.boajp.repositorios;

import com.boajp.modelo.EquipoEntidad;
import com.boajp.modelo.TemporadaEntidad;
import jakarta.persistence.*;

import java.util.List;

public class EquipoRepositorio {

    private final EntityManagerFactory emf;
    private final EntityManager em;

    public EquipoRepositorio() {
        emf = Persistence.createEntityManagerFactory("default"); //Cambiar "default" por el nombre de su unidad de persistencia
        em = emf.createEntityManager();
    }

    public void insertar(EquipoEntidad equipo) throws Exception {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.persist(equipo);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw new Exception("Error al intentar insertar el equipo");
        }
    }

    public void eliminar(int codigo) throws Exception {
        EntityTransaction transaction = em.getTransaction();
        EquipoEntidad e = em.find(EquipoEntidad.class, codigo);
        try {
            transaction.begin();
            if (e != null) {
                em.remove(e);
            }
            transaction.commit();
        } catch (Exception ex) {
            transaction.rollback();
            throw new Exception("Error al intentar eliminar el equipo");
        }
    }

    public void modificar(EquipoEntidad equipo) throws Exception {
        EntityTransaction transaction = em.getTransaction();
        EquipoEntidad e = em.find(EquipoEntidad.class, equipo.getCodEquipo());
        try {
            transaction.begin();
            if (e != null) {
                e.setNombre(equipo.getNombre());
                e.setLogo(equipo.getLogo());
                e.setPresupuesto(equipo.getPresupuesto());
                em.persist(e);
                transaction.commit();
            }
        } catch (Exception ex) {
            transaction.rollback();
            throw new Exception("Error al intentar modificar el equipo");
        }
    }

    public List<EquipoEntidad> seleccionarTodosLosEquipos() {
        return em.createQuery("SELECT e FROM EquipoEntidad e", EquipoEntidad.class)
                .getResultList();
    }

    public EquipoEntidad seleccionarEquipoPorId(int id) {
        return em.find(EquipoEntidad.class, id);
    }

    public List<EquipoEntidad> buscarEquipoParticipantes() throws Exception{
        try {
            String sql = "SELECT e FROM EquipoEntidad e WHERE e.codEquipo IN (SELECT re.equipo.codEquipo FROM RegistroEquipoEntidad re WHERE re.temporada.codTemporada = (SELECT MAX(t.codTemporada) FROM TemporadaEntidad t))";
            TypedQuery<EquipoEntidad> resultado = em.createQuery(sql, EquipoEntidad.class);
            return resultado.getResultList();
        }catch (Exception exception) {
            throw new Exception("Error al intentar extraer equipos");
        }
    }

    public EquipoEntidad buscarEquipo(int codigo)throws Exception{
        try {
            String jpql = "SELECT e FROM EquipoEntidad e WHERE e.codEquipo = :codigo";
            TypedQuery<EquipoEntidad> query = em.createQuery(jpql, EquipoEntidad.class);
            query.setParameter("codigo", codigo);
            System.out.println(query.getSingleResult().getCodEquipo() + " " + query.getSingleResult().getLogo());
            return query.getSingleResult();
        }catch (Exception exception){
            throw new Exception("Error al intentar extraer equipo", exception);
        }
    }

}