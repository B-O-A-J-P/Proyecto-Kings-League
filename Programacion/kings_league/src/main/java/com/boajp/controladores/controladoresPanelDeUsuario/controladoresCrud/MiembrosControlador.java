package com.boajp.controladores.controladoresPanelDeUsuario.controladoresCrud;

import com.boajp.servicios.MiembrosServicio;
import com.boajp.vistas.componentes.PanelDeError;
import com.boajp.vistas.usuario.PanelDeCrud;

public class MiembrosControlador implements CrudControlador {
    private MiembrosServicio miembrosServicio;
    private PanelDeCrud panelDeCrud;

    public MiembrosControlador(PanelDeCrud panelDeCrud) {
        miembrosServicio = new MiembrosServicio();
        try {
            if ( panelDeCrud == null) {
                panelDeCrud = new PanelDeCrud(miembrosServicio.getFilas(), miembrosServicio.getColumnas());
            } else {
                panelDeCrud.actualizarModelo(miembrosServicio.getFilas(), miembrosServicio.getColumnas());
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
