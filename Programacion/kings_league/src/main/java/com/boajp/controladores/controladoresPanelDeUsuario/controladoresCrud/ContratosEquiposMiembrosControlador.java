package com.boajp.controladores.controladoresPanelDeUsuario.controladoresCrud;

import com.boajp.modelo.ContratoEquipoMiembroEntidad;
import com.boajp.servicios.ContratosEquiposMiembrosServicio;
import com.boajp.servicios.EquiposServicio;
import com.boajp.servicios.MiembrosServicio;
import com.boajp.utilidades.FechaUtilidades;
import com.boajp.vistas.componentes.PanelDeError;
import com.boajp.vistas.usuario.PanelDeCrud;
import com.boajp.vistas.usuario.crudDialogs.contratoMiembro.AnadirContratoMiembro;

public class ContratosEquiposMiembrosControlador implements CrudControlador{
    private ContratosEquiposMiembrosServicio contratosEquiposMiembrosServicio;
    private EquiposServicio equiposServicio;
    private MiembrosServicio miembrosServicio;
    private PanelDeCrud panelDeCrud;

    public ContratosEquiposMiembrosControlador(PanelDeCrud panelDeCrud) {
        contratosEquiposMiembrosServicio = new ContratosEquiposMiembrosServicio();
        equiposServicio = new EquiposServicio();
        miembrosServicio = new MiembrosServicio();
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
        anadirListenerInsertar();
        anadirListenerModificar();
        anadirListenerEliminar();
    }

    @Override
    public void anadirListenerInsertar() {
        panelDeCrud.getCrearBoton().addActionListener(e -> {
            String[] codigosMiembros = miembrosServicio.getCodigos();
            String[] codigosEquipo = equiposServicio.getCodigos();
            AnadirContratoMiembro dialog = new AnadirContratoMiembro(codigosEquipo, codigosMiembros);
            dialog.getButtonOK().addActionListener( l -> {
                var contrato = new ContratoEquipoMiembroEntidad();
                contrato.setEquipo(equiposServicio.getEquipo(dialog.getCodigoEquipoCb()));
                contrato.setMiembro(miembrosServicio.buscar(dialog.getCodigoMiembro()));
                contrato.setFuncion(dialog.getFuncionCb());
                contrato.setFechaEntrada(dialog.getFechaEntradaTf());
                contrato.setFechaSalida(dialog.getFechaSalidaTf());
                contratosEquiposMiembrosServicio.insertar(contrato);
                panelDeCrud.actualizarModelo(contratosEquiposMiembrosServicio.getFilas(), contratosEquiposMiembrosServicio.getColumnas());
                dialog.dispose();
            });
            dialog.setVisible(true);
        });
    }

    @Override
    public void anadirListenerModificar() {

    }

    @Override
    public void anadirListenerEliminar() {
        panelDeCrud.getEliminarBoton().addActionListener( e -> {
            int fila = panelDeCrud.getTabla().getSelectedRow();
            try {
                contratosEquiposMiembrosServicio.eliminar(
                        Integer.parseInt((String)panelDeCrud.getModelo().getValueAt(fila, 0)),
                        Integer.parseInt((String)panelDeCrud.getModelo().getValueAt(fila, 1)),
                        FechaUtilidades.stringToFecha((String)panelDeCrud.getModelo().getValueAt(fila, 3))
                );
                panelDeCrud.actualizarModelo(contratosEquiposMiembrosServicio.getFilas(), contratosEquiposMiembrosServicio.getColumnas());
            } catch (Exception exception) {
                new PanelDeError(exception.getMessage());
            }
        });
    }
}
