package com.boajp.repositorios;

import com.boajp.modelo.RegistroEquipoEntidad;
import com.boajp.modelo.SplitEntidad;
import jakarta.persistence.*;
import jakarta.persistence.*;

import java.util.List;

public class RegistroEquipoRespositorio {

    private final EntityManagerFactory emf;
    private final EntityManager em;


    public RegistroEquipoRespositorio(){
        emf = Persistence.createEntityManagerFactory("default");
        em = emf. createEntityManager();

    }
    public void insertar (RegistroEquipoEntidad registroEquipo) throws Exception {

        EntityTransaction transaction = em.getTransaction ();
        try {
            transaction.begin();
            em.persist(registroEquipo);
            transaction.commit();
        }catch (Exception exception){
            transaction.rollback();
            throw new Exception("Error al intentar insertar un equipo participante");
        }
    }
    public void eliminar (RegistroEquipoEntidad registroEquipo) throws Exception {
        EntityTransaction transaction = em.getTransaction ();
        RegistroEquipoEntidad r = em.find(RegistroEquipoEntidad.class, registroEquipo.getTemporada());
        try {
            transaction.begin();
            if (registroEquipo != null) {
                em.remove(registroEquipo);
                transaction.commit();
            }
        }catch (Exception exception){
            transaction.rollback();
            throw new Exception("Error al intentar eliminar el equipo participante");
        }
    }

    public void modificar (RegistroEquipoEntidad registroEquipo) throws Exception {
        EntityTransaction transaction = em.getTransaction ();
        RegistroEquipoEntidad r = em.find(RegistroEquipoEntidad.class, registroEquipo.getTemporada());
        try {
            transaction.begin();
            if (registroEquipo != null){
                r.setEquipo(registroEquipo.getEquipo());
                r.setTemporada(registroEquipo.getTemporada());
                em.persist(r);
            }
        }catch (Exception exception){
            transaction.rollback();
            throw new Exception("Error al intentar modificar el equipo participante");
        }
    }

    public List<RegistroEquipoEntidad> seleccionarTodosLosEquiposParticipantes() {
        Query qEquiposParticipantes = em.createQuery("SELECT re FROM RegistroEquipoEntidad re WHERE re.temporada.codTemporada = (SELECT MAX(re2.temporada.codTemporada) FROM RegistroEquipoEntidad re2)");
        List<RegistroEquipoEntidad> equiposParticipantes = qEquiposParticipantes.getResultList();
        return equiposParticipantes;
    }


    public List<RegistroEquipoEntidad> seleccionarCantidadEquiposParticipantes(){
        Query qNroEquiposParticipantes = em.createNativeQuery ("SELECT COUNT(DISTINCT cod_equipo) FROM equipos_participantes ");
        List<RegistroEquipoEntidad> equipos_participantes = qNroEquiposParticipantes.getResultList();
        return equipos_participantes;

    }
    }



