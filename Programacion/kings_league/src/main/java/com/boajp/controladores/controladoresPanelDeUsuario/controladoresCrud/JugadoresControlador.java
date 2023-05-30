package com.boajp.controladores.controladoresPanelDeUsuario.controladoresCrud;

import com.boajp.modelo.JugadorEntidad;
import com.boajp.servicios.JugadoresServicio;
import com.boajp.vistas.componentes.PanelDeError;
import com.boajp.vistas.usuario.PanelDeCrud;
import com.boajp.vistas.usuario.crudDialogs.jugador.AnadirJugador;
import com.boajp.vistas.usuario.crudDialogs.jugador.ModificarJugador;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

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
                        panelDeCrud.actualizarModelo(jugadoresServicio.getFilas(), jugadoresServicio.getColumnas());
                    }catch (Exception exception) {
                        new PanelDeError(exception.getMessage());
                    }
                    dialog.dispose();
                });
            dialog.setVisible(true);
        });
    }

    @Override
    public void anadirListenerModificar() {
        panelDeCrud.getModificarBoton().addActionListener( e -> {
            DefaultTableModel modelo = panelDeCrud.getModelo();
            JTable tabla = panelDeCrud.getTabla();
            ModificarJugador dialog = null;
            JugadorEntidad jugador = null;
            try {
                jugador = jugadoresServicio.buscar(Integer.parseInt((String) modelo.getValueAt(tabla.getSelectedRow(), 0)));
                dialog = new ModificarJugador(
                        jugador.getNombre(),
                        jugador.getApellido(),
                        jugador.getDni(),
                        jugador.getPie(),
                        jugador.getAltura()
                );
            } catch (Exception exception) {
                new PanelDeError(exception.getMessage());
            }
            ModificarJugador finalDialog = dialog;
            dialog.getButtonCancel().addActionListener(l -> {
                if (l.getActionCommand().equalsIgnoreCase("bloqueado")) {
                    finalDialog.getButtonCancel().setActionCommand("desbloqueado");
                    finalDialog.habilitarCampos();
                    finalDialog.getButtonCancel().setText("Cancelar");
                    finalDialog.getButtonOK().setVisible(true);
                } else if (l.getActionCommand().equalsIgnoreCase("desbloqueado")) {
                    finalDialog.getButtonCancel().setActionCommand("bloqueado");
                    finalDialog.deshabilitarCampos();
                    finalDialog.getButtonCancel().setText("Modificar");
                    finalDialog.getButtonOK().setVisible(false);
                    finalDialog.restablecerValoresPorDefecto();
                }
            });

            JugadorEntidad finalJugador = jugador;
            dialog.getButtonOK().addActionListener(l -> {
                finalDialog.establecerValoresPorDefecto();
                finalJugador.setNombre(finalDialog.getCopiaNombre());
                finalJugador.setApellido(finalDialog.getCopiaApellido());
                finalJugador.setDni(finalDialog.getCopiaDni());
                finalJugador.setPie(finalDialog.getCopiaPie());
                finalJugador.setAltura(finalDialog.getCopiaAltura());
                try {
                    jugadoresServicio.modificar(finalJugador);
                    panelDeCrud.actualizarModelo(jugadoresServicio.getFilas(), jugadoresServicio.getColumnas());
                } catch (Exception exception) {
                    new PanelDeError(exception.getMessage());
                }
                finalDialog.getButtonCancel().setActionCommand("bloqueado");
                finalDialog.deshabilitarCampos();
                finalDialog.getButtonCancel().setText("Modificar");
                finalDialog.getButtonOK().setVisible(false);
            });
            dialog.setVisible(true);
        });
    }

    @Override
    public void anadirListenerEliminar() {
        panelDeCrud.getEliminarBoton().addActionListener( e -> {
            int fila = panelDeCrud.getTabla().getSelectedRow();
            try {
                jugadoresServicio.eliminar(Integer.parseInt((String)panelDeCrud.getModelo().getValueAt(fila, 0)));
                panelDeCrud.actualizarModelo(jugadoresServicio.getFilas(), jugadoresServicio.getColumnas());
            } catch (Exception exception) {
                new PanelDeError(exception.getMessage());
            }
        });
    }
}
