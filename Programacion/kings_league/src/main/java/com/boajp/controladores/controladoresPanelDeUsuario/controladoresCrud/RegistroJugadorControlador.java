package com.boajp.controladores.controladoresPanelDeUsuario.controladoresCrud;

import com.boajp.servicios.RegistroJugadorServicio;
import com.boajp.vistas.componentes.PanelDeError;
import com.boajp.vistas.usuario.PanelDeCrud;

public class RegistroJugadorControlador implements CrudControlador{
    private RegistroJugadorServicio registroJugadorServicio;
    private PanelDeCrud panelDeCrud;

    public RegistroJugadorControlador(PanelDeCrud panelDeCrud) {
        registroJugadorServicio = new RegistroJugadorServicio();
        try {
            if ( panelDeCrud == null) {
                panelDeCrud = new PanelDeCrud(registroJugadorServicio.getFilas(), registroJugadorServicio.getColumnas());
            } else {
                panelDeCrud.actualizarModelo( registroJugadorServicio.getFilas(), registroJugadorServicio.getColumnas());
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
