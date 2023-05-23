package com.boajp.controladores.controladoresPanelDeUsuario.controladoresCrud;

import com.boajp.servicios.EquiposServicio;
import com.boajp.vistas.componentes.PanelDeError;
import com.boajp.vistas.usuario.PanelDeCrud;

public class EquiposControlador implements CrudControlador{
    private EquiposServicio equiposServicio;
    private PanelDeCrud panelDeCrud;

    public EquiposControlador(PanelDeCrud panelDeCrud) {
        equiposServicio = new EquiposServicio();
        try {
            if ( panelDeCrud == null) {
                panelDeCrud = new PanelDeCrud(equiposServicio.getFilas(), equiposServicio.getColumnas());
            } else {
                panelDeCrud.actualizarModelo(equiposServicio.getFilas(), equiposServicio.getColumnas());
            }
        } catch (Exception exception) {
            new PanelDeError(exception.getMessage());
        }
        this.panelDeCrud = panelDeCrud;
    }

    @Override
    public void anadirListenerAceptar() {

    }

    @Override
    public void anadirListenerModificar() {

    }

    @Override
    public void anadirListenerEliminar() {

    }
}
