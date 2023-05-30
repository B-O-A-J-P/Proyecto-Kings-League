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

    }

    @Override
    public void anadirListenerEliminar() {

    }
}
