package com.boajp.controladores.controladoresPanelDeUsuario;

import com.boajp.controladores.Controlador;
import com.boajp.vistas.Ventana;
import com.boajp.vistas.usuario.BarraDeNavegacion;

public class PanelUsuarioBarraDeNavegacionControlador {
    private BarraDeNavegacion barraDeNavegacion;
    private PanelUsuarioControlador panelUsuarioControlador;
    public PanelUsuarioBarraDeNavegacionControlador(PanelUsuarioControlador panelDeUsuarioControlador) {
        barraDeNavegacion = new BarraDeNavegacion(Controlador.getUsuario().getCodDePermisos());
        this.panelUsuarioControlador = panelDeUsuarioControlador;
        anadirlistenerPerfil();
        anadirListenerTemporadas();
        anadirListenerSplits();
        anadirListenerJugadores();
        anadirListenerJornadas();
        anadirListenerPartidos();
        anadirListenerEquipos();
        anadirListenerMiembros();
        anadirListenerContratosEquipoJugador();
        anadirListenerContratosEquipoMiembro();
        anadirListenerRegistrosDeEquipos();
        anadirListenerRegistrosDeJugadores();
        anadirListenerDraft();
        anadirListenerAgendas();
        anadirListenerClasificaciones();
    }
    public void anadirlistenerPerfil() {
        barraDeNavegacion.getPerfilBoton().addActionListener( e -> {
            panelUsuarioControlador.mostrarAjustesDePerfil();
        });
    }

    public void anadirListenerTemporadas() {
        barraDeNavegacion.getTemporadasBoton().addActionListener( e -> {
            panelUsuarioControlador.mostrarPanelDeCrudTemporada();
        });
    }

    public void anadirListenerSplits() {
        barraDeNavegacion.getSplitsBoton().addActionListener( e -> {
            panelUsuarioControlador.mostrarPanelDeCrudSplit();
        });
    }

    public void anadirListenerJugadores() {
        barraDeNavegacion.getJugadorBoton().addActionListener( e -> {
            panelUsuarioControlador.mostrarPanelDeCrudJugadores();
        });
    }

    public void anadirListenerJornadas() {
        barraDeNavegacion.getJornadasBoton().addActionListener( e -> {
            panelUsuarioControlador.mostrarPanelDeCrudJornadas();
        });
    }

    public void anadirListenerPartidos() {
        barraDeNavegacion.getPartidosButton().addActionListener( e -> {
            panelUsuarioControlador.mostrarPanelDeCrudPartidos();
        });
    }

    public void anadirListenerEquipos() {
        barraDeNavegacion.getEquiposBoton().addActionListener( e -> {
            panelUsuarioControlador.mostrarPanelDeCrudEquipos();
        });
    }

    public void anadirListenerMiembros() {
        barraDeNavegacion.getMiembrosButton().addActionListener( e -> {
            panelUsuarioControlador.mostrarPanelDeCrudMiembros();
        });
    }

    public void anadirListenerContratosEquipoJugador() {
        barraDeNavegacion.getContratosDeEquipoJugadoresButton().addActionListener( e -> {
            panelUsuarioControlador.mostrarPanelDeCrudContratosEquipoJugador();
        });
    }

    public void anadirListenerContratosEquipoMiembro() {
        barraDeNavegacion.getContratosDeEquipoMiembrosButton().addActionListener( e -> {
            panelUsuarioControlador.mostrarPanelDeCrudContratosEquipoMiembros();
        });
    }

    public void anadirListenerRegistrosDeEquipos() {
        barraDeNavegacion.getRegistrosDeEquiposButton().addActionListener( e -> {
            panelUsuarioControlador.mostrarPanelDeCrudRegistrosEquipos();
        });
    }

    public void anadirListenerRegistrosDeJugadores() {
        barraDeNavegacion.getRegistrosDeJugadoresButton().addActionListener( e -> {
            panelUsuarioControlador.mostrarPanelDeCrudRegistrosJugadores();
        });
    }

    public void anadirListenerDraft() {
        barraDeNavegacion.getDraftButton().addActionListener( e -> {
            panelUsuarioControlador.mostrarPanelDeCrudDraft();
        });
    }

    public void anadirListenerAgendas() {
        barraDeNavegacion.getAgendasButton().addActionListener( e -> {
            panelUsuarioControlador.mostrarPanelDeCrudAgendas();
        });
    }

    public void anadirListenerClasificaciones() {
        barraDeNavegacion.getClasificacionesButton().addActionListener( e -> {
            panelUsuarioControlador.mostrarPanelDeCrudClasificaciones();
        });
    }

    public BarraDeNavegacion getBarraDeNavegacion() {
        return barraDeNavegacion;
    }

    public void setBarraDeNavegacion(BarraDeNavegacion barraDeNavegacion) {
        this.barraDeNavegacion = barraDeNavegacion;
    }

}
