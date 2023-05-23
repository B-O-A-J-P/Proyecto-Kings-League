package com.boajp.controladores.controladoresPanelDeUsuario.controladoresCrud;

import com.boajp.servicios.PartidoServicio;
import com.boajp.vistas.componentes.PanelDeError;
import com.boajp.vistas.usuario.PanelDeCrud;

public class PartidosControlador implements CrudControlador{
    private PartidoServicio partidoServicio;
    private PanelDeCrud panelDeCrud;

    public PartidosControlador(PanelDeCrud panelDeCrud) {
        partidoServicio = new PartidoServicio();
        try {
            if ( panelDeCrud == null) {
                panelDeCrud = new PanelDeCrud(partidoServicio.getFilas(), partidoServicio.getColumnas());
            } else {
                panelDeCrud.actualizarModelo(partidoServicio.getFilas(), partidoServicio.getColumnas());
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
