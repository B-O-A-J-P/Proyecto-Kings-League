package com.boajp.controladores.controladoresPanelDeUsuario.controladoresCrud;

import com.boajp.servicios.AgendaServicio;
import com.boajp.vistas.componentes.PanelDeError;
import com.boajp.vistas.usuario.PanelDeCrud;

public class AgendaCrontrolador implements CrudControlador{
    private AgendaServicio agendaServicio;
    private PanelDeCrud panelDeCrud;
    public AgendaCrontrolador(PanelDeCrud panelDeCrud) {
        agendaServicio = new AgendaServicio();
        try {
            if ( panelDeCrud == null) {
                panelDeCrud = new PanelDeCrud(agendaServicio.getFilas(), agendaServicio.getColumnas());
            } else {
                panelDeCrud.actualizarModelo(agendaServicio.getFilas(), agendaServicio.getColumnas());
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
