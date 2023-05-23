package com.boajp.controladores.controladoresPanelDeUsuario.controladoresCrud;

import com.boajp.servicios.ContratosEquiposJugadoresServicio;
import com.boajp.vistas.componentes.PanelDeError;
import com.boajp.vistas.usuario.PanelDeCrud;

public class ContratosEquiposJugadoresControlador implements CrudControlador{
    private ContratosEquiposJugadoresServicio contratosEquiposJugadoresServicio;
    private  PanelDeCrud panelDeCrud;
    public ContratosEquiposJugadoresControlador(PanelDeCrud panelDeCrud) {
        contratosEquiposJugadoresServicio = new ContratosEquiposJugadoresServicio();
        try {
            if ( panelDeCrud == null) {
                panelDeCrud = new PanelDeCrud(contratosEquiposJugadoresServicio.getFilas(), contratosEquiposJugadoresServicio.getColumnas());
            } else {
                panelDeCrud.actualizarModelo(contratosEquiposJugadoresServicio.getFilas(), contratosEquiposJugadoresServicio.getColumnas());
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
