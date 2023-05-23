package com.boajp;

import com.boajp.modelo.TemporadaEntidad;
import com.boajp.servicios.TemporadasServicio;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

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
}