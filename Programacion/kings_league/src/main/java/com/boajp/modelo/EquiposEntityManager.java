package com.boajp.modelo;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

public class EquiposEntityManager {
    private static EntityManagerFactory emf;
    private static EntityManager em;
    private static EntityTransaction transaction;

    public EquiposEntityManager() {
        emf = Persistence.createEntityManagerFactory("default");
        em = emf.createEntityManager();
        transaction = em.getTransaction();


    }

    public ArrayList<EquipoEntidad> consultarNombreLogoEquipo(){
        transaction.begin();

        TypedQuery<EquipoEntidad> qEquipos = em.createNamedQuery("equipos.nombreLogo", EquipoEntidad.class);
        List<EquipoEntidad> lista = qEquipos.getResultList();
        transaction.commit();
        // Conversión a ArrayList
        return new ArrayList<>(lista);
    }
}
