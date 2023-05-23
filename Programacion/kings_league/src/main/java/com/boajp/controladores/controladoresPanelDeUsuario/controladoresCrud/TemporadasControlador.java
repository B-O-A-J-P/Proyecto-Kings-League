package com.boajp.controladores.controladoresPanelDeUsuario.controladoresCrud;

import com.boajp.modelo.TemporadaEntidad;
import com.boajp.servicios.TemporadasServicio;
import com.boajp.vistas.componentes.PanelDeError;
import com.boajp.vistas.usuario.PanelDeCrud;
import com.boajp.vistas.usuario.crudDialogs.temporada.AnadirTemporadaDialog;
import com.boajp.vistas.usuario.crudDialogs.temporada.ModificarTemporadaDialog;

import javax.swing.*;

public class TemporadasControlador implements CrudControlador{
    private final PanelDeCrud panelDeCrud;
    private final TemporadasServicio temporadasServicio;
    public TemporadasControlador(PanelDeCrud panelDeCrud) {
        temporadasServicio = new TemporadasServicio();
        try {
            if (panelDeCrud == null) {
                panelDeCrud = new PanelDeCrud(temporadasServicio.getFilas(), temporadasServicio.getColumnas());
            } else {
                panelDeCrud.actualizarModelo(temporadasServicio.getFilas(), temporadasServicio.getColumnas());
            }
        } catch (Exception exception) {
            new PanelDeError(exception.getMessage());
        }
        this.panelDeCrud = panelDeCrud;
        anadirListenerInsertar();
        anadirListenerModificar();
        anadirListenerEliminar();
    }

    public void anadirListenerInsertar()  {
        panelDeCrud.getCrearBoton().addActionListener( e -> {
            AnadirTemporadaDialog dialog = new AnadirTemporadaDialog();
            dialog.getButtonOK().addActionListener( x -> {
                try {
                    temporadasServicio.anadirTemporada(
                            dialog.getAnoTf(),
                            dialog.getFechaInicioInscripcionTf(),
                            dialog.getFechaFinInscripcion()
                    );
                    JOptionPane.showMessageDialog(null, "Se ha registado la temporada.");
                    panelDeCrud.actualizarModelo(temporadasServicio.getFilas(), temporadasServicio.getColumnas());
                    dialog.dispose();
                }catch (Exception exception) {
                    new PanelDeError(exception.getMessage());
                }
            });
            dialog.setVisible(true);
        });
    }

    public void anadirListenerModificar() {
        panelDeCrud.getModificarBoton().addActionListener( e -> {
            var modelo = panelDeCrud.getModelo();
            try {
                int codigo = Integer.parseInt((String) modelo.getValueAt(panelDeCrud.getTabla().getSelectedRow(), 0));
                TemporadaEntidad temporada = null;
                try {
                    temporada = temporadasServicio.getTemporada(codigo);
                } catch (Exception exception) {
                    new PanelDeError(exception.getMessage());
                }
                var dialog = new ModificarTemporadaDialog(
                        String.valueOf(temporada.getAno()),
                        temporada.getFechaInicioInscripcionString(),
                        temporada.getFechaFinInscripcionString()
                );
                dialog.getModificarBoton().addActionListener( x -> {
                    if (x.getActionCommand().equalsIgnoreCase("bloqueado")) {
                        dialog.getModificarBoton().setActionCommand("desbloqueado");
                        dialog.habilitarCampos();
                        dialog.getAceptarBoton().setVisible(true);
                        dialog.getModificarBoton().setText("Cancelar");
                    } else if (x.getActionCommand().equalsIgnoreCase("desbloqueado")) {
                        dialog.getModificarBoton().setActionCommand("bloqueado");
                        dialog.desabilitarCampos();
                        dialog.restablecerValoresPorDefecto();
                        dialog.getAceptarBoton().setVisible(false);
                        dialog.getModificarBoton().setText("Modificar");
                    }
                });

                TemporadaEntidad finalTemporada = temporada;
                dialog.getAceptarBoton().addActionListener(x -> {
                    finalTemporada.setAno(Short.parseShort(dialog.getAnoTf().getText()));
                    finalTemporada.setFechaInicioInscripcion(dialog.getFechaInicioInscripcion());
                    finalTemporada.setFechaFinInscripcion(dialog.getFechaFinInscripcion());
                    try {
                        temporadasServicio.modificarTemporada(finalTemporada);
                        panelDeCrud.actualizarModelo(
                                temporadasServicio.getFilas(),
                                temporadasServicio.getColumnas());
                    } catch (Exception exception) {
                        new PanelDeError(exception.getMessage());
                    }
                    dialog.getModificarBoton().setActionCommand("bloqueado");
                    dialog.desabilitarCampos();
                    dialog.establecerValoresPorDefecto();
                    dialog.getAceptarBoton().setVisible(false);
                    dialog.getModificarBoton().setText("Modificar");
                });
                dialog.setVisible(true);
            } catch (ArrayIndexOutOfBoundsException exception) {
                new PanelDeError("Es necesario elegir una fila.");
            }
        });
    }

    public void anadirListenerEliminar() {
        panelDeCrud.getEliminarBoton().addActionListener( e -> {
            int fila = panelDeCrud.getTabla().getSelectedRow();
            try {
                temporadasServicio.eliminarTemporada(Integer.parseInt((String) panelDeCrud.getModelo().getValueAt(fila, 0)));
                panelDeCrud.actualizarModelo(temporadasServicio.getFilas(), temporadasServicio.getColumnas());
            } catch (Exception exception) {
                new PanelDeError(exception.getMessage());
            }
        });
    }
}
