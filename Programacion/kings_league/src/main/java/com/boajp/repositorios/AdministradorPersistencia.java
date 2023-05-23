package com.boajp.repositorios;
import com.boajp.vistas.componentes.PanelDeError;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class AdministradorPersistencia {
    private static EntityManagerFactory entityManagerFactory;

    public static EntityManagerFactory getEntityManagerFactory() {
        try {
            if (entityManagerFactory == null) {
                entityManagerFactory = Persistence.createEntityManagerFactory("default");
            }
            return entityManagerFactory;
        } catch (Exception exception) {
            new PanelDeError("No se ha podido conectar a la base de datos.");
            System.exit(1);
        }
        return null;
    }

    public static void cerrarEntityManagerFactory() {
        if (entityManagerFactory != null && entityManagerFactory.isOpen()) {
            entityManagerFactory.close();
        }
    }
}
