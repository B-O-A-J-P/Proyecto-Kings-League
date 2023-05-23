package com.boajp.controladores.controladoresPanelDeUsuario.controladoresCrud;

import com.boajp.modelo.JornadaEntidad;
import com.boajp.modelo.SplitEntidad;
import com.boajp.servicios.JornadasServicio;
import com.boajp.servicios.SplitsServicio;
import com.boajp.utilidades.FechaUtilidades;
import com.boajp.vistas.componentes.PanelDeError;
import com.boajp.vistas.usuario.PanelDeCrud;
import com.boajp.vistas.usuario.crudDialogs.jornada.AnadirJornadaDialog;
import com.boajp.vistas.usuario.crudDialogs.jornada.ModificarJornadaDialog;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class JornadasControlador implements CrudControlador{

    private JornadasServicio jornadasServicio;
    private SplitsServicio splitsServicio;
    private PanelDeCrud panelDeCrud;

    public JornadasControlador(PanelDeCrud panelDeCrud) {
        jornadasServicio = new JornadasServicio();
        splitsServicio = new SplitsServicio();
        try {
            if ( panelDeCrud == null) {
                panelDeCrud = new PanelDeCrud(jornadasServicio.getFilas(), jornadasServicio.getColumnas());
            } else {
                panelDeCrud.actualizarModelo(jornadasServicio.getFilas(), jornadasServicio.getColumnas());
            }
        } catch (Exception exception) {
            new PanelDeError(exception.getMessage());
        }
        this.panelDeCrud = panelDeCrud;
        anadirListenerAceptar();
        anadirListenerEliminar();
        anadirListenerModificar();
    }

    @Override
    public void anadirListenerAceptar() {
        panelDeCrud.getCrearBoton().addActionListener( e -> {
            AnadirJornadaDialog dialog = new AnadirJornadaDialog();
            try {
                DefaultComboBoxModel<String> codigos = new DefaultComboBoxModel<>(splitsServicio.getCodigos());
                JComboBox<String> combo = dialog.getComboBox();
                combo.setModel(codigos);
                dialog.getButtonOK().addActionListener( x -> {
                    try {
                        JornadaEntidad jornadaEntidad = new JornadaEntidad();
                        SplitEntidad splitEntidad = splitsServicio.buscarSplit(Integer.parseInt((String) codigos.getSelectedItem()));
                        jornadaEntidad.setSplit(splitEntidad);
                        jornadaEntidad.setNumero(Byte.parseByte(dialog.getNumeroTf().getText()));
                        jornadaEntidad.setFecha(FechaUtilidades.stringToFecha(dialog.getFechaTf().getText()));
                        jornadaEntidad.setUbicacion(dialog.getUbicacionTf().getText());
                        jornadasServicio.insertarJornada(jornadaEntidad);
                        panelDeCrud.actualizarModelo(jornadasServicio.getFilas(), jornadasServicio.getColumnas());
                        dialog.dispose();
                    } catch (Exception exception) {
                        new PanelDeError(exception.getMessage());
                    }
                });

            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
            dialog.setVisible(true);
        });
    }

    @Override
    public void anadirListenerModificar() {
        panelDeCrud.getModificarBoton().addActionListener( e -> {
            JTable tabla = panelDeCrud.getTabla();
            DefaultTableModel modelo = panelDeCrud.getModelo();
            try {
                String[] codigos = splitsServicio.getCodigos();
                ModificarJornadaDialog dialog = new ModificarJornadaDialog(
                        (String) modelo.getValueAt(tabla.getSelectedRow(), 4),
                        (String) modelo.getValueAt(tabla.getSelectedRow(), 1),
                        (String) modelo.getValueAt(tabla.getSelectedRow(), 2),
                        (String) modelo.getValueAt(tabla.getSelectedRow(), 3),
                        codigos
                );

                dialog.getButtonCancel().addActionListener( x -> {
                    if (x.getActionCommand().equalsIgnoreCase("bloqueado")) {
                        dialog.getButtonCancel().setActionCommand("desbloqueado");
                        dialog.habilitarCampos();
                        dialog.getButtonOK().setVisible(true);
                        dialog.getButtonCancel().setText("Cancelar");
                    } else if (x.getActionCommand().equalsIgnoreCase("desbloqueado")) {
                        dialog.getButtonCancel().setActionCommand("bloqueado");
                        dialog.desabilitarCampos();
                        dialog.restablecerValoresPorDefecto();
                        dialog.getButtonOK().setVisible(false);
                        dialog.getButtonCancel().setText("Modificar");
                    }
                });

                dialog.getButtonOK().addActionListener( x -> {
                    try {
                        JornadaEntidad jornada = new JornadaEntidad();
                        SplitEntidad split = splitsServicio.buscarSplit(Integer.parseInt((String)dialog.getComboBox().getSelectedItem()));
                        jornada.setSplit(split);
                        jornada.setNumero(Byte.parseByte(dialog.getNumeroTf().getText()));
                        jornada.setFecha(FechaUtilidades.stringToFecha(dialog.getFechaTf().getText()));
                        jornada.setUbicacion(dialog.getUbicacionTf().getText());
                        jornadasServicio.modificarJornada(jornada);

                        panelDeCrud.actualizarModelo(jornadasServicio.getFilas(), jornadasServicio.getColumnas());

                        dialog.getButtonCancel().setActionCommand("bloqueado");
                        dialog.desabilitarCampos();
                        dialog.restablecerValoresPorDefecto();
                        dialog.getButtonOK().setVisible(false);
                        dialog.getButtonCancel().setText("Modificar");
                    } catch (Exception ex) {
                        new PanelDeError(ex.getMessage());
                    }
                });
                dialog.setVisible(true);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }

        });

    }

    @Override
    public void anadirListenerEliminar() {
        panelDeCrud.getEliminarBoton().addActionListener( e -> {
            DefaultTableModel modelo = panelDeCrud.getModelo();
            JTable tabla = panelDeCrud.getTabla();
            try {
                jornadasServicio.eliminarJornada(jornadasServicio.buscarJornada(Integer.parseInt((String)modelo.getValueAt(tabla.getSelectedRow(), 0))));
                panelDeCrud.actualizarModelo(jornadasServicio.getFilas(), jornadasServicio.getColumnas());
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });
    }
}
