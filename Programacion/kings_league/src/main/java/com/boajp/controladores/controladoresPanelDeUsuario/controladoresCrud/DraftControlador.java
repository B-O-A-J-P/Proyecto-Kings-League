package com.boajp.controladores.controladoresPanelDeUsuario.controladoresCrud;

import com.boajp.servicios.DraftServicio;
import com.boajp.vistas.componentes.PanelDeError;
import com.boajp.vistas.usuario.PanelDeCrud;

public class DraftControlador implements CrudControlador{
    private DraftServicio draftServicio;
    private  PanelDeCrud panelDeCrud;
    public DraftControlador(PanelDeCrud panelDeCrud) {
        draftServicio = new DraftServicio();
        try {
            if ( panelDeCrud == null) {
                panelDeCrud = new PanelDeCrud(draftServicio.getFilas(), draftServicio.getColumnas());
            } else {
                panelDeCrud.actualizarModelo(draftServicio.getFilas(), draftServicio.getColumnas());
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
