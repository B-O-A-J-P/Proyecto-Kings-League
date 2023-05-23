package com.boajp;

import com.boajp.modelo.TemporadaEntidad;
import com.boajp.servicios.IniciarUsuarioServicio;
import com.boajp.servicios.RegistrarUsuarioServicio;
import com.boajp.servicios.TemporadasServicio;
import com.boajp.vistas.formulario.FormularioIniciarSesion;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.time.LocalDate;
import java.util.jar.JarOutputStream;

import static org.junit.jupiter.api.Assertions.*;

class AplicacionTest {

    @Test
    void getVentanaControlador() {
    }

    @Test
    public void insertarTemporada() throws Exception {
        TemporadasServicio temporadasServicio = new TemporadasServicio();
        temporadasServicio.anadirTemporada((short) 2023, LocalDate.of(2023,06,12),LocalDate.of(2023,06,23));
    }

    @Test
    public void modificarTemporada()throws Exception{
        TemporadasServicio temporadasServicio = new TemporadasServicio();
        TemporadaEntidad temporadaEntidad = new TemporadaEntidad((short) 2023, LocalDate.of(2023,06,12),LocalDate.of(2023,06,23));
        temporadasServicio.modificarTemporada(temporadaEntidad);
    }

    @Test
    public void modificarTemporada2()throws Exception{
        TemporadasServicio temporadasServicio = new TemporadasServicio();
        TemporadaEntidad temporadaEntidad = new TemporadaEntidad((short) 2023, LocalDate.of(2023,06,12),LocalDate.of(2023,06,10));
        temporadasServicio.modificarTemporada(temporadaEntidad);
    }

    @Test
    public void pruebaIniciarSesion()throws Exception{

            IniciarUsuarioServicio iniciarUsuarioServicio = new IniciarUsuarioServicio();
            iniciarUsuarioServicio.encontrarUsuario("bea");
            iniciarUsuarioServicio.iniciarUsuario("bea12345".toCharArray());
            FormularioIniciarSesion formularioIniciarSesion = new FormularioIniciarSesion();

    }

    @Test
    public void pruebaIniciarSesionError()throws Exception {

        IniciarUsuarioServicio iniciarUsuarioServicio = new IniciarUsuarioServicio();
        iniciarUsuarioServicio.encontrarUsuario("noexiste");
        iniciarUsuarioServicio.iniciarUsuario("kl".toCharArray());
    }

    @Test
    public void registro()throws Exception{
        RegistrarUsuarioServicio registrarUsuarioServicio = new RegistrarUsuarioServicio();
        registrarUsuarioServicio.registrarUsuario("pepe", "pepe@gmail.com", "pepe12345".toCharArray());
    }
}