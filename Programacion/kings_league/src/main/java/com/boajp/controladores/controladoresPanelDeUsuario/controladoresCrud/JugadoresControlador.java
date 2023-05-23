package com.boajp.controladores.controladoresPanelDeUsuario.controladoresCrud;

import com.boajp.servicios.JugadoresServicio;
import com.boajp.vistas.componentes.PanelDeError;
import com.boajp.vistas.usuario.PanelDeCrud;
import com.boajp.vistas.usuario.crudDialogs.jugador.AnadirJugador;

public class JugadoresControlador implements CrudControlador{

    private JugadoresServicio jugadoresServicio;
    private PanelDeCrud panelDeCrud;

    public JugadoresControlador(PanelDeCrud panelDeCrud) {
        jugadoresServicio = new JugadoresServicio();
        try {
            if ( panelDeCrud == null) {
                panelDeCrud = new PanelDeCrud(jugadoresServicio.getFilas(), jugadoresServicio.getColumnas());
            } else {
                panelDeCrud.actualizarModelo(jugadoresServicio.getFilas(), jugadoresServicio.getColumnas());
            }
        } catch (Exception exception) {
            new PanelDeError(exception.getMessage());
        }
        this.panelDeCrud = panelDeCrud;
        anadirListenerInsertar();
        anadirListenerModificar();
        anadirListenerEliminar();
    }

    @Override
    public void anadirListenerInsertar() {
        panelDeCrud.getCrearBoton().addActionListener( e -> {
            AnadirJugador dialog = new AnadirJugador();
                dialog.getButtonOK().addActionListener(x -> {
                    try {
                        jugadoresServicio.anadirJugador(
                                dialog.getNombreTf().getText(),
                                dialog.getApellidoTf().getText(),
                                dialog.getDniTf().getText(),
                                dialog.getPieCb().getSelectedItem().toString(),
                                (Integer) dialog.getAlturaSp().getValue()
                        );
                    }catch (Exception exception) {
                        new PanelDeError(exception.getMessage());
                    }
                });
            dialog.setVisible(true);
        });
    }

    @Override
    public void anadirListenerModificar() {

    }

    @Override
    public void anadirListenerEliminar() {

    }
}
