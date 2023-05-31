package com.boajp.controladores.controladoresPanelDeUsuario.controladoresCrud;

import com.boajp.modelo.ContratoEquipoJugadorEntidad;
import com.boajp.modelo.EquipoEntidad;
import com.boajp.modelo.JugadorEntidad;
import com.boajp.servicios.ContratosEquiposJugadoresServicio;
import com.boajp.servicios.EquiposServicio;
import com.boajp.servicios.JugadoresServicio;
import com.boajp.utilidades.FechaUtilidades;
import com.boajp.vistas.componentes.PanelDeError;
import com.boajp.vistas.usuario.PanelDeCrud;
import com.boajp.vistas.usuario.crudDialogs.contratoJugador.AnadirContratoJugador;
import com.boajp.vistas.usuario.crudDialogs.contratoJugador.ModificarContratoJugador;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ContratosEquiposJugadoresControlador implements CrudControlador{
    private ContratosEquiposJugadoresServicio contratosEquiposJugadoresServicio;
    private EquiposServicio equiposServicio;
    private JugadoresServicio jugadoresServicio;
    private  PanelDeCrud panelDeCrud;
    public ContratosEquiposJugadoresControlador(PanelDeCrud panelDeCrud) {
        contratosEquiposJugadoresServicio = new ContratosEquiposJugadoresServicio();
        equiposServicio = new EquiposServicio();
        jugadoresServicio = new JugadoresServicio();
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
        anadirListenerInsertar();
        anadirListenerModificar();
        anadirListenerEliminar();
    }

    @Override
    public void anadirListenerInsertar() {
        panelDeCrud.getCrearBoton().addActionListener(e -> {
            String[] codigoEquipos = equiposServicio.getCodigos();
            String[] codigoJugadores = jugadoresServicio.getCodigos();
            AnadirContratoJugador dialog = new AnadirContratoJugador(codigoEquipos, codigoJugadores);
            dialog.getButtonOK().addActionListener( l -> {
                var contrato = new ContratoEquipoJugadorEntidad();
                contrato.setEquipo(equiposServicio.getEquipo(dialog.getCodigoEquipoCb()));
                contrato.setJugador(jugadoresServicio.buscar(dialog.getCodigoJugadorCb()));
                contrato.setSalario(dialog.getSalarioTf());
                contrato.setClausula(dialog.getClausulaTf());
                contrato.setFechaInicio(dialog.getFechaInicioTf());
                contrato.setFechaFin(dialog.getFechaFinTf());
                contratosEquiposJugadoresServicio.insertar(contrato);
                panelDeCrud.actualizarModelo(contratosEquiposJugadoresServicio.getFilas(), contratosEquiposJugadoresServicio.getColumnas());
                dialog.dispose();
            });
            dialog.setVisible(true);
        });
    }

    @Override
    public void anadirListenerModificar() {
        panelDeCrud.getModificarBoton().addActionListener(e -> {
            String[] codigoEquipos = equiposServicio.getCodigos();
            String[] codigoJugadores = jugadoresServicio.getCodigos();
            DefaultTableModel modelo = panelDeCrud.getModelo();
            JTable tabla = panelDeCrud.getTabla();
            EquipoEntidad equipo = equiposServicio.getEquipo(Integer.parseInt((String)modelo.getValueAt(tabla.getSelectedRow(), 0)));
            JugadorEntidad jugador = jugadoresServicio.buscar(Integer.parseInt((String)modelo.getValueAt(tabla.getSelectedRow(), 1)));
            ContratoEquipoJugadorEntidad contrato = contratosEquiposJugadoresServicio.buscar(
                    equipo,
                    jugador,
                    FechaUtilidades.stringToFecha((String)modelo.getValueAt(tabla.getSelectedRow(), 4))
                    );
            ModificarContratoJugador dialog = new ModificarContratoJugador(
                    String.valueOf(contrato.getEquipo()),
                    String.valueOf(contrato.getJugador().getCodJugador()),
                    String.valueOf(contrato.getSalario()),
                    String.valueOf(contrato.getClausula()),
                    FechaUtilidades.fechaToString(contrato.getFechaInicio()),
                    FechaUtilidades.fechaToString(contrato.getFechaFin()),
                    codigoEquipos,
                    codigoJugadores
            );

            dialog.getButtonCancel().addActionListener(l -> {
                if (l.getActionCommand().equalsIgnoreCase("bloqueado")) {
                    dialog.getButtonCancel().setActionCommand("desbloqueado");
                    dialog.getButtonCancel().setText("Cancelar");
                    dialog.habilitarCampos();
                } else if(l.getActionCommand().equalsIgnoreCase("desbloqueado")) {
                    dialog.getButtonCancel().setActionCommand("bloqueado");
                    dialog.getButtonCancel().setText("Modificar");
                    dialog.deshabilitarCampos();
                }
            });

            dialog.getButtonOK().addActionListener(l -> {
                dialog.establecerValoresPorDefecto();
                contrato.setJugador(jugadoresServicio.buscar(dialog.getCodigoJugadorCb()));
                contrato.setEquipo(equiposServicio.getEquipo(dialog.getCodigoEquipoCb()));
                contrato.setSalario(dialog.getSalarioTf());
                contrato.setClausula(dialog.getClausulaTf());
                contrato.setFechaInicio(dialog.getFechaInicioTf());
                contrato.setFechaFin(dialog.getFechaFinTf());
                contratosEquiposJugadoresServicio.modificar(contrato);
                panelDeCrud.actualizarModelo(contratosEquiposJugadoresServicio.getFilas(), contratosEquiposJugadoresServicio.getColumnas());

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
                contratosEquiposJugadoresServicio.eliminar(
                        Integer.parseInt((String)panelDeCrud.getModelo().getValueAt(fila, 0)),
                        Integer.parseInt((String)panelDeCrud.getModelo().getValueAt(fila, 1)),
                        FechaUtilidades.stringToFecha((String)panelDeCrud.getModelo().getValueAt(fila, 4))
                        );
                panelDeCrud.actualizarModelo(contratosEquiposJugadoresServicio.getFilas(), contratosEquiposJugadoresServicio.getColumnas());
            } catch (Exception exception) {
                new PanelDeError(exception.getMessage());
            }
        });
    }
}
