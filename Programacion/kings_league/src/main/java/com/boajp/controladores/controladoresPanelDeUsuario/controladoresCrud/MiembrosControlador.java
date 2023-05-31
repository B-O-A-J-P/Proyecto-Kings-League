package com.boajp.controladores.controladoresPanelDeUsuario.controladoresCrud;

import com.boajp.modelo.MiembroEntidad;
import com.boajp.servicios.MiembrosServicio;
import com.boajp.vistas.componentes.PanelDeError;
import com.boajp.vistas.usuario.PanelDeCrud;
import com.boajp.vistas.usuario.crudDialogs.miembro.AnadirMiembro;
import com.boajp.vistas.usuario.crudDialogs.miembro.ModificarMiembro;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

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
        anadirListenerInsertar();
        anadirListenerModificar();
        anadirListenerEliminar();
    }

    @Override
    public void anadirListenerInsertar() {
        panelDeCrud.getCrearBoton().addActionListener(e -> {
            AnadirMiembro dialog = new AnadirMiembro();
            dialog.getButtonOK().addActionListener(l -> {
                MiembroEntidad miembro = new MiembroEntidad();
                miembro.setNombre(dialog.getNombreTf());
                miembro.setApellido(dialog.getApellidoTf());
                miembro.setDni(dialog.getDniTf());
                miembrosServicio.insertar(miembro);
                panelDeCrud.actualizarModelo(miembrosServicio.getFilas(), miembrosServicio.getColumnas());
                dialog.dispose();
            });
            dialog.setVisible(true);
        });
    }

    @Override
    public void anadirListenerModificar() {
        panelDeCrud.getModificarBoton().addActionListener(e -> {
            DefaultTableModel modelo = panelDeCrud.getModelo();
            JTable tabla = panelDeCrud.getTabla();
            MiembroEntidad miembro = miembrosServicio.buscar(Integer.parseInt((String)modelo.getValueAt(tabla.getSelectedRow(), 0)));
            ModificarMiembro dialog = new ModificarMiembro(
                    miembro.getNombre(),
                    miembro.getApellido(),
                    miembro.getDni()
            );
            dialog.getButtonCancel().addActionListener( l -> {
                if (l.getActionCommand().equalsIgnoreCase("bloqueado")) {
                    dialog.getButtonCancel().setActionCommand("desbloqueado");
                    dialog.getButtonCancel().setText("Cancelar");
                    dialog.habilitarCampos();
                } else if (l.getActionCommand().equalsIgnoreCase("desbloqueado")) {
                    dialog.getButtonCancel().setActionCommand("bloqueado");
                    dialog.getButtonCancel().setText("Modificar");
                    dialog.reestablecerValoresPorDefecto();
                    dialog.deshabilitarCampos();
                }
            });
            dialog.getButtonOK().addActionListener( l -> {
                miembro.setNombre(dialog.getNombreTf());
                miembro.setApellido(dialog.getApellidoTf());
                miembro.setDni(dialog.getDniTf());
                miembrosServicio.modificar(miembro);
                panelDeCrud.actualizarModelo(miembrosServicio.getFilas(), miembrosServicio.getColumnas());

                dialog.establecerValoresPorDefecto();
                dialog.getButtonCancel().setActionCommand("bloqueado");
                dialog.getButtonCancel().setText("Modificar");
                dialog.deshabilitarCampos();
            });
            dialog.setVisible(true);
        });
    }

    @Override
    public void anadirListenerEliminar() {
        panelDeCrud.getEliminarBoton().addActionListener( e -> {
            int fila = panelDeCrud.getTabla().getSelectedRow();
            try {
                miembrosServicio.eliminar(Integer.parseInt((String)panelDeCrud.getModelo().getValueAt(fila, 0)));
                panelDeCrud.actualizarModelo(miembrosServicio.getFilas(), miembrosServicio.getColumnas());
            } catch (Exception exception) {
                new PanelDeError(exception.getMessage());
            }
        });
    }
}
