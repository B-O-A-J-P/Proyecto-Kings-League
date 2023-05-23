package com.boajp.controladores.controladoresPanelDeUsuario.controladoresCrud;

import com.boajp.servicios.ContratosEquiposMiembrosServicio;
import com.boajp.vistas.componentes.PanelDeError;
import com.boajp.vistas.usuario.PanelDeCrud;

public class ContratosEquiposMiembrosControlador implements CrudControlador{
    private ContratosEquiposMiembrosServicio contratosEquiposMiembrosServicio;
    private PanelDeCrud panelDeCrud;

    public ContratosEquiposMiembrosControlador(PanelDeCrud panelDeCrud) {
        contratosEquiposMiembrosServicio = new ContratosEquiposMiembrosServicio();
        try {
            if ( panelDeCrud == null) {
                panelDeCrud = new PanelDeCrud(contratosEquiposMiembrosServicio.getFilas(), contratosEquiposMiembrosServicio.getColumnas());
            } else {
                panelDeCrud.actualizarModelo(contratosEquiposMiembrosServicio.getFilas(), contratosEquiposMiembrosServicio.getColumnas());
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
