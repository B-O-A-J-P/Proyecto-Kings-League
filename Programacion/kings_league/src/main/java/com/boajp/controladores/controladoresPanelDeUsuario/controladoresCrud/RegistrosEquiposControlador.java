package com.boajp.controladores.controladoresPanelDeUsuario.controladoresCrud;

import com.boajp.servicios.RegistrosEquipoServicio;
import com.boajp.vistas.componentes.PanelDeError;
import com.boajp.vistas.usuario.PanelDeCrud;

public class RegistrosEquiposControlador implements CrudControlador{
    private RegistrosEquipoServicio registrosEquipoServicio;
    private PanelDeCrud panelDeCrud;

    public RegistrosEquiposControlador(PanelDeCrud panelDeCrud) {
        registrosEquipoServicio = new RegistrosEquipoServicio();
        try {
            if ( panelDeCrud == null) {
                panelDeCrud = new PanelDeCrud(registrosEquipoServicio.getFilas(), registrosEquipoServicio.getColumnas());
            } else {
                panelDeCrud.actualizarModelo(registrosEquipoServicio.getFilas(), registrosEquipoServicio.getColumnas());
            }
        } catch (Exception exception) {
            new PanelDeError(exception.getMessage());
        }
        this.panelDeCrud = panelDeCrud;
    }

    @Override
    public void anadirListenerInsertar() {

    }

    @Override
    public void anadirListenerModificar() {

    }

    @Override
    public void anadirListenerEliminar() {

    }
}
