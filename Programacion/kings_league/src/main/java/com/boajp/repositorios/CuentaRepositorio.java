package com.boajp.repositorios;

import com.boajp.excepciones.UsuarioNoEncontradoExcepcion;
import com.boajp.modelo.CuentaEntidad;
import com.boajp.vistas.componentes.PanelDeError;
import jakarta.persistence.*;

public class CuentaRepositorio {
    private final EntityManagerFactory entityManagerFactory;

    public CuentaRepositorio() {
        entityManagerFactory = AdministradorPersistencia.getEntityManagerFactory();
    }

    public void insertar(CuentaEntidad cuenta) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(cuenta);
            transaction.commit();
        } catch (Exception exception) {
            transaction.rollback();
            new PanelDeError(exception.getCause().getCause().getCause().getMessage());
        } finally {
            entityManager.close();
        }
    }

    public void modificar(CuentaEntidad cuentaEntidad) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            CuentaEntidad oldCuenta = entityManager.find(CuentaEntidad.class, cuentaEntidad.getCodCuenta());
            oldCuenta.setNombreDeUsuario(cuentaEntidad.getNombreDeUsuario());
            oldCuenta.setEmail(cuentaEntidad.getEmail());
            oldCuenta.setContrasena(cuentaEntidad.getContrasena());
            entityManager.persist(oldCuenta);
            transaction.commit();
        }catch (Exception exception) {
            transaction.rollback();
            new PanelDeError(exception.getCause().getCause().getCause().getMessage());
        } finally {
            entityManager.close();
        }
    }

    public CuentaEntidad buscarCuenta(String usuario) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            String sql = "SELECT c FROM CuentaEntidad c WHERE c.nombreDeUsuario = :usuario";
            TypedQuery<CuentaEntidad> resultado = entityManager.createQuery(sql, CuentaEntidad.class);
            resultado.setParameter("usuario", usuario);
            return resultado.getSingleResult();
        } catch (Exception exception) {
            throw new UsuarioNoEncontradoExcepcion();
        } finally {
            entityManager.close();
        }
    }
}
