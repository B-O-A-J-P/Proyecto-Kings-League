package com.boajp.controladores.controladoresPanelDeUsuario.controladoresCrud;

import com.boajp.modelo.SplitEntidad;
import com.boajp.modelo.TemporadaEntidad;
import com.boajp.servicios.SplitsServicio;
import com.boajp.servicios.TemporadasServicio;
import com.boajp.utilidades.FechaUtilidades;
import com.boajp.vistas.componentes.PanelDeError;
import com.boajp.vistas.usuario.PanelDeCrud;
import com.boajp.vistas.usuario.crudDialogs.split.AnadirSplitDialog;
import com.boajp.vistas.usuario.crudDialogs.split.ModificarSplitDialog;
import jakarta.persistence.PersistenceException;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class SplitsControlador implements CrudControlador{
    private final PanelDeCrud panelDeCrud;
    private final SplitsServicio splitsServicio;
    private final TemporadasServicio temporadasServicio;

    public SplitsControlador(PanelDeCrud panelDeCrud) {
        splitsServicio = new SplitsServicio();
        temporadasServicio = new TemporadasServicio();
        try {
            if ( panelDeCrud == null) {
                panelDeCrud = new PanelDeCrud(splitsServicio.getFilasSplits(), splitsServicio.getColumnas());
            } else {
                panelDeCrud.actualizarModelo(splitsServicio.getFilasSplits(), splitsServicio.getColumnas());
            }
        } catch (Exception exception) {
            new PanelDeError(exception.getMessage());
        }
        this.panelDeCrud = panelDeCrud;
        anadirListenerAceptar();
        anadirListenerModificar();
        anadirListenerEliminar();
    }
    @Override
    public void anadirListenerAceptar() {
        panelDeCrud.getCrearBoton().addActionListener( e -> {
            try {
                var dialog = new AnadirSplitDialog(temporadasServicio.getCodigos());
                dialog.getButtonOK().addActionListener(x -> {
                    try {
                        TemporadaEntidad temporadaEntidad = temporadasServicio.getTemporada(
                                Integer.parseInt(
                                        dialog.getModel()
                                                .getElementAt(dialog.getComboBox().getSelectedIndex())));
                        SplitEntidad splitEntidad = new SplitEntidad(
                                dialog.getPeriodo(),
                                dialog.getFechaInicio(),
                                dialog.getFechaFin(),
                                temporadaEntidad);
                        splitsServicio.anadirSplit(splitEntidad);
                        panelDeCrud.actualizarModelo(splitsServicio.getFilasSplits(), splitsServicio.getColumnas());
                        dialog.dispose();
                    } catch (PersistenceException exception) {
                        new PanelDeError(exception.getMessage());
                    } catch (Exception exception) {
                        new PanelDeError(exception.getMessage());
                    }
                });
                dialog.setVisible(true);
            } catch (Exception exception) {
                new PanelDeError(exception.getMessage());
            }
        });
    }

    @Override
    public void anadirListenerModificar() {
        panelDeCrud.getModificarBoton().addActionListener( e -> {
            try {
                String[] codigosDeTemporada = temporadasServicio.getCodigos();
                DefaultTableModel modelo = panelDeCrud.getModelo();
                JTable tabla = panelDeCrud.getTabla();
                ModificarSplitDialog dialog = new ModificarSplitDialog(
                        (String) modelo.getValueAt(tabla.getSelectedRow(), 2),
                        (String) modelo.getValueAt(tabla.getSelectedRow(), 3),
                        (String) modelo.getValueAt(tabla.getSelectedRow(), 4),
                        (String) modelo.getValueAt(tabla.getSelectedRow(), 0),
                        codigosDeTemporada
                );

                dialog.getButtonCancel().addActionListener( x -> {
                    if (x.getActionCommand().equalsIgnoreCase("bloqueado")) {
                        dialog.getButtonCancel().setActionCommand("desbloqueado");
                        dialog.getButtonCancel().setText("Cancelar");
                        dialog.habilitarCampos();
                        dialog.getButtonOK().setVisible(true);
                    } else if (x.getActionCommand().equalsIgnoreCase("desbloqueado")) {
                        dialog.getButtonCancel().setActionCommand("bloqueado");
                        dialog.getButtonCancel().setText("Modificar");
                        dialog.deshabilitarCampos();
                        dialog.getButtonOK().setVisible(false);
                    }
                });

                dialog.getButtonOK().addActionListener( x -> {
                    try {
                        SplitEntidad split = splitsServicio.buscarSplit(Integer.parseInt((String) modelo.getValueAt(tabla.getSelectedRow(), 1)));
                        TemporadaEntidad temporada = temporadasServicio.getTemporada(Integer.parseInt((String)modelo.getValueAt(tabla.getSelectedRow(), 0)));
                        split.setTemporada(temporada);
                        split.setFechaInicio(FechaUtilidades.stringToFecha(dialog.getFechaDeInicioTf().getText()));
                        split.setFechaFin(FechaUtilidades.stringToFecha(dialog.getFechaFinTf().getText()));
                        split.setNombre(dialog.getNombreTf().getText());
                        splitsServicio.modificarSplit(split);

                        panelDeCrud.actualizarModelo(splitsServicio.getFilasSplits(), splitsServicio.getColumnas());

                        dialog.getButtonCancel().setActionCommand("bloqueado");
                        dialog.getButtonCancel().setText("Modificar");
                        dialog.deshabilitarCampos();
                        dialog.getButtonOK().setVisible(false);
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                });

                dialog.setVisible(true);
            } catch (Exception ex) {
                throw new RuntimeException(ex.getMessage());
            }
        });
    }

    @Override
    public void anadirListenerEliminar() {
        panelDeCrud.getEliminarBoton().addActionListener( e -> {
            DefaultTableModel modelo = panelDeCrud.getModelo();
            JTable tabla = panelDeCrud.getTabla();
            try {
                splitsServicio.eliminarSplit(splitsServicio.buscarSplit(Integer.parseInt((String)modelo.getValueAt(tabla.getSelectedRow(), 1))));
                panelDeCrud.actualizarModelo(splitsServicio.getFilasSplits(), splitsServicio.getColumnas());
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });
    }
}
