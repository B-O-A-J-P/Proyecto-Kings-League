package com.boajp.controladores.controladoresPanelDeUsuario.controladoresCrud;

import com.boajp.modelo.RegistroEquipoEntidad;
import com.boajp.servicios.EquiposServicio;
import com.boajp.servicios.RegistrosEquipoServicio;
import com.boajp.servicios.TemporadasServicio;
import com.boajp.vistas.componentes.PanelDeError;
import com.boajp.vistas.usuario.PanelDeCrud;
import com.boajp.vistas.usuario.crudDialogs.registroEquipo.AnadirRegistroEquipo;

public class RegistrosEquiposControlador implements CrudControlador{
    private RegistrosEquipoServicio registrosEquipoServicio;
    private EquiposServicio equiposServicio;
    private TemporadasServicio temporadasServicio;
    private PanelDeCrud panelDeCrud;

    public RegistrosEquiposControlador(PanelDeCrud panelDeCrud) {
        registrosEquipoServicio = new RegistrosEquipoServicio();
        equiposServicio = new EquiposServicio();
        temporadasServicio = new TemporadasServicio();
        try {
            if ( panelDeCrud == null) {
                panelDeCrud = new PanelDeCrud(registrosEquipoServicio.getFilas(), registrosEquipoServicio.getColumnas());
            } else {
                panelDeCrud.actualizarModelo(registrosEquipoServicio.getFilas(), registrosEquipoServicio.getColumnas());
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
            String[] codigosTemporada = temporadasServicio.getCodigos();
            String[] codigoEquipos = equiposServicio.getCodigos();
            AnadirRegistroEquipo dialog = new AnadirRegistroEquipo(codigosTemporada, codigoEquipos);
            dialog.getButtonOK().addActionListener(l -> {
                RegistroEquipoEntidad registro = new RegistroEquipoEntidad();
                registro.setEquipo(equiposServicio.getEquipo(dialog.getCodigoEquipoCb()));
                registro.setTemporada(temporadasServicio.getTemporada(dialog.getCodigoTemporadaCb()));
                registrosEquipoServicio.insertar(registro);
                panelDeCrud.actualizarModelo(registrosEquipoServicio.getFilas(), registrosEquipoServicio.getColumnas());
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
                registrosEquipoServicio.eliminar(registrosEquipoServicio.buscar(
                        Integer.parseInt((String) panelDeCrud.getModelo().getValueAt(fila, 0)),
                        Integer.parseInt((String) panelDeCrud.getModelo().getValueAt(fila, 1)))
                );
                panelDeCrud.actualizarModelo(registrosEquipoServicio.getFilas(), registrosEquipoServicio.getColumnas());
            } catch (Exception exception) {
                new PanelDeError(exception.getMessage());
            }
        });
    }
}
