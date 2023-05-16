package com.boajp.repositorios;

import com.boajp.modelo.JornadaEntidad;
import com.boajp.modelo.PartidoEntidad;
import jakarta.persistence.*;

import java.util.List;

public class PartidoRepositorio {
    private final EntityManagerFactory emf;
    private final EntityManager em;


    public PartidoRepositorio(){
        emf = Persistence.createEntityManagerFactory("default");
        em = emf. createEntityManager();

    }
    public void insertar (PartidoEntidad partido) throws Exception {

        EntityTransaction transaction = em.getTransaction ();
        try {
            transaction.begin();
            em.persist(partido);
            transaction.commit();
        }catch (Exception exception){
            transaction.rollback();
            throw new Exception("Error al intentar insertar un partido");
        }
    }
    public void eliminar (PartidoEntidad partido) throws Exception {
        EntityTransaction transaction = em.getTransaction ();
        PartidoEntidad p = em.find(PartidoEntidad.class, partido.getCodPartido());
        try {
            transaction.begin();
            if (partido != null) {
                em.remove(partido);
                transaction.commit();
            }
        }catch (Exception exception){
            transaction.rollback();
            throw new Exception("Error al intentar eliminar el partido");
        }
    }

    public void modificar (PartidoEntidad partido) throws Exception {
        EntityTransaction transaction = em.getTransaction ();
        PartidoEntidad p = em.find(PartidoEntidad.class, partido.getCodPartido());
        try {
            transaction.begin();
            if (partido != null){
                p.setJornada(partido.getJornada());
                p.setEquipoUno(partido.getEquipoUno());
                p.setEquipoDos(partido.getEquipoDos());
                p.setHora(partido.getHora());
                p.setFase(partido.getFase());
                em.persist(p);
            }
        }catch (Exception exception){
            transaction.rollback();
            throw new Exception("Error al intentar modificar el partido");
        }
    }

    public List<PartidoEntidad> seleccionarPartidosPorJornada(JornadaEntidad jornada) {
        Query qPartidoJornada = em.createQuery("SELECT p FROM PartidoEntidad p WHERE p.jornada = :jornada ORDER BY p.hora ASC");
        qPartidoJornada.setParameter("jornada", jornada);
        List<PartidoEntidad> partidos = qPartidoJornada.getResultList();
        return partidos;
    }



}
