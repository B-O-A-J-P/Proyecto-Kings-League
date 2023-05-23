package com.boajp.controladores.controladoresPanelDeUsuario.controladoresCrud;

import com.boajp.servicios.ClasificacionesServicio;
import com.boajp.vistas.componentes.PanelDeError;
import com.boajp.vistas.usuario.PanelDeCrud;

public class ClasificacionesControlador implements CrudControlador{
    private ClasificacionesServicio clasificacionesServicio;
    private PanelDeCrud panelDeCrud;

    public ClasificacionesControlador(PanelDeCrud panelDeCrud) {
        clasificacionesServicio = new ClasificacionesServicio();
        try {
            if ( panelDeCrud == null) {
                panelDeCrud = new PanelDeCrud(clasificacionesServicio.getFilas(), clasificacionesServicio.getColumnas());
            } else {
                panelDeCrud.actualizarModelo(clasificacionesServicio.getFilas(), clasificacionesServicio.getColumnas());
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
