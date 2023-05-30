package com.boajp.controladores.controladoresPanelDeUsuario.controladoresCrud;

import com.boajp.modelo.JornadaEntidad;
import com.boajp.modelo.PartidoEntidad;
import com.boajp.servicios.EquiposServicio;
import com.boajp.servicios.JornadasServicio;
import com.boajp.servicios.PartidoServicio;
import com.boajp.utilidades.FechaUtilidades;
import com.boajp.vistas.componentes.PanelDeError;
import com.boajp.vistas.usuario.PanelDeCrud;
import com.boajp.vistas.usuario.crudDialogs.partido.AnadirPartido;
import com.boajp.vistas.usuario.crudDialogs.partido.ModificarPartido;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class PartidosControlador implements CrudControlador{
    private PartidoServicio partidoServicio;
    private PanelDeCrud panelDeCrud;
    private EquiposServicio equiposServicio;
    private JornadasServicio jornadasServicio;

    public PartidosControlador(PanelDeCrud panelDeCrud) {
        partidoServicio = new PartidoServicio();
        equiposServicio = new EquiposServicio();
        jornadasServicio = new JornadasServicio();
        try {
            if ( panelDeCrud == null) {
                panelDeCrud = new PanelDeCrud(partidoServicio.getFilas(), partidoServicio.getColumnas());
            } else {
                panelDeCrud.actualizarModelo(partidoServicio.getFilas(), partidoServicio.getColumnas());
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
            AnadirPartido dialog = new AnadirPartido(jornadasServicio.getCodigos(), equiposServicio.getCodigos());
            dialog.getButtonOK().addActionListener( l -> {
                JornadaEntidad jornada = jornadasServicio.buscarJornada(dialog.getCodigoJornadaCb());
                PartidoEntidad partido = new PartidoEntidad();
                partido.setJornada(jornada);
                partido.setFase(dialog.getFaseCb());
                partido.setHora(FechaUtilidades.dateToLocalDateTime(jornada.getFecha(), dialog.getHoraTf()));
                partido.setEquipoUno(equiposServicio.getEquipo(dialog.getEquipoUnoCb()));
                partido.setEquipoDos(equiposServicio.getEquipo(dialog.getEquipoDosCb()));
                partidoServicio.insertar(partido);
                panelDeCrud.actualizarModelo(partidoServicio.getFilas(), partidoServicio.getColumnas());
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
            String[] codigoJornadas = jornadasServicio.getCodigos();
            String[] codigoEquipos = equiposServicio.getCodigos();
            PartidoEntidad partido = partidoServicio.buscar(Integer.parseInt((String)modelo.getValueAt(tabla.getSelectedRow(), 0)));
            ModificarPartido dialog = new ModificarPartido(
                    String.valueOf(partido.getJornada().getCodJornada()),
                    partido.getFase(),
                    String.valueOf(partido.getEquipoUno().getCodEquipo()),
                    String.valueOf(partido.getEquipoDos().getCodEquipo()),
                    FechaUtilidades.fechaToString(partido.getHora()),
                    codigoJornadas,
                    codigoEquipos
            );
            dialog.getButtonCancel().addActionListener( l -> {
                if (l.getActionCommand().equalsIgnoreCase("bloqueado")) {
                    dialog.getButtonCancel().setActionCommand("desbloqueado");
                    dialog.getButtonCancel().setText("Cancelar");
                    dialog.habilitarCampos();
                }
                else if (l.getActionCommand().equalsIgnoreCase("desbloqueado")) {
                    dialog.getButtonCancel().setActionCommand("bloqueado");
                    dialog.getButtonCancel().setText("Modificar");
                    dialog.deshabilitar();
                }
            });

            dialog.getButtonOK().addActionListener(l -> {
                partido.setEquipoUno(equiposServicio.getEquipo(dialog.getEquipoUnoCb()));
                partido.setEquipoDos(equiposServicio.getEquipo(dialog.getEquipoDosCb()));
                partido.setFase(dialog.getFaseCb());
                partido.setHora(FechaUtilidades.stringToFechaHora(dialog.getHoraTf()));
                partido.setJornada(jornadasServicio.buscarJornada(dialog.getCodigoJornadaCb()));
                partidoServicio.modificar(partido);

                dialog.getButtonCancel().setActionCommand("bloqueado");
                dialog.getButtonCancel().setText("Modificar");
                dialog.deshabilitar();
                panelDeCrud.actualizarModelo(partidoServicio.getFilas(), partidoServicio.getColumnas());
            });
            dialog.setVisible(true);
        });
    }

    @Override
    public void anadirListenerEliminar() {
        panelDeCrud.getEliminarBoton().addActionListener(e -> {
            int fila = panelDeCrud.getTabla().getSelectedRow();
            try {
                partidoServicio.eliminar(Integer.parseInt((String)panelDeCrud.getModelo().getValueAt(fila, 0)));
                panelDeCrud.actualizarModelo(partidoServicio.getFilas(), partidoServicio.getColumnas());
            } catch (Exception exception) {
                new PanelDeError(exception.getMessage());
            }
        });

    }
}
