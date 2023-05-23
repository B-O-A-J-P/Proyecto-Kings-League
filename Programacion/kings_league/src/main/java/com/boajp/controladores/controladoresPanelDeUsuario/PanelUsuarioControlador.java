package com.boajp.controladores.controladoresPanelDeUsuario;


import com.boajp.vistas.usuario.PanelDeUsuario;

import javax.swing.*;


public class PanelUsuarioControlador {
    private PanelUsuarioBarraDeNavegacionControlador panelUsuarioBarraDeNavegacionControlador;
    private PanelDeUsuario panelDeUsuario;

    public PanelUsuarioControlador() {
        panelDeUsuario = new PanelDeUsuario();
        panelUsuarioBarraDeNavegacionControlador = new PanelUsuarioBarraDeNavegacionControlador(this);
        panelDeUsuario.setBarraDeNavegacion(panelUsuarioBarraDeNavegacionControlador.getBarraDeNavegacion().getPanel());
    }
    public JPanel getPanelDeUsuario() {
        return panelDeUsuario.getPanel();
    }

    public void mostrarAjustesDePerfil() {
        var panelAjustesDePerfilControlador = new PanelAjustesDePerfilControlador();
        cambiarPanel(panelAjustesDePerfilControlador.getPanelAjustesDePerfil().getPanel());
    }

    public void mostrarPanelDeCrudTemporada() {
        var controlador = new PanelDeCrudControlador();
        cambiarPanel(controlador.getPanelDeCrudTemporadas().getPanel());
    }

    public void mostrarPanelDeCrudSplit() {
        var controlador = new PanelDeCrudControlador();
        cambiarPanel(controlador.getPanelDeCrudSplit().getPanel());
    }

    public void mostrarPanelDeCrudJugadores() {
        var controlador = new PanelDeCrudControlador();
        cambiarPanel(controlador.getPanelDeCrudJugadores().getPanel());
    }

    public void mostrarPanelDeCrudJornadas() {
        var controlador = new PanelDeCrudControlador();
        cambiarPanel(controlador.getPanelDeCrudJornadas().getPanel());
    }

    public void mostrarPanelDeCrudPartidos() {
        var controlador = new PanelDeCrudControlador();
        cambiarPanel(controlador.getPanelDeCrudPartidos().getPanel());
    }

    public void mostrarPanelDeCrudEquipos() {
        var controlador = new PanelDeCrudControlador();
        cambiarPanel(controlador.getPanelDeCrudEquipos().getPanel());
    }

    public void mostrarPanelDeCrudMiembros() {
        var controlador = new PanelDeCrudControlador();
        cambiarPanel(controlador.getPanelDeCrudMiembros().getPanel());
    }

    public void mostrarPanelDeCrudContratosEquipoJugador() {
        var controlador = new PanelDeCrudControlador();
        cambiarPanel(controlador.getPanelDeCrudContratosEquipoJugadores().getPanel());
    }

    public void mostrarPanelDeCrudContratosEquipoMiembros() {
        var controlador = new PanelDeCrudControlador();
        cambiarPanel(controlador.getPanelDeCrudContratosEquipoMiembros().getPanel());
    }

    public void mostrarPanelDeCrudRegistrosEquipos() {
        var controlador = new PanelDeCrudControlador();
        cambiarPanel(controlador.getPanelDeCrudRegistrosEquipos().getPanel());
    }

    public void mostrarPanelDeCrudRegistrosJugadores() {
        var controlador = new PanelDeCrudControlador();
        cambiarPanel(controlador.getPanelDeCrudRegistrosJugadores().getPanel());
    }

    public void mostrarPanelDeCrudDraft() {
        var controlador = new PanelDeCrudControlador();
        cambiarPanel(controlador.getPanelDeCrudDraft().getPanel());
    }

    public void mostrarPanelDeCrudAgendas() {
        var controlador = new PanelDeCrudControlador();
        cambiarPanel(controlador.getPanelDeAgendas().getPanel());
    }

    public void cambiarPanel(JPanel panel) {
        panelDeUsuario.getPanelContenido().removeAll();
        panelDeUsuario.getPanelContenido().add(panel);
        panelDeUsuario.getPanelContenido().revalidate();
        panelDeUsuario.getPanelContenido().repaint();
    }


}
