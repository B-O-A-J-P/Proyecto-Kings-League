package com.boajp.vistas.usuario;

import javax.swing.*;
import java.awt.*;

public class BarraDeNavegacion {

    private JPanel panel;
    private JButton jugadoresBoton;
    private JButton jornadasBoton;
    private JButton partidosButton;
    private JButton miembrosButton;
    private JButton contratosDeEquipoJugadoresButton;
    private JButton contratosDeEquipoMiembrosButton;
    private JButton registrosDeEquiposButton;
    private JButton registrosDeJugadoresButton;
    private JButton draftButton;
    private JButton agendasButton;
    private JButton clasificacionesButton;
    private JButton perfilBoton;
    private JButton temporadasBoton;
    private JButton splitsBoton;
    private JButton equiposBoton;
    private JButton[] botones = {jugadoresBoton, jornadasBoton, partidosButton, miembrosButton, contratosDeEquipoJugadoresButton, contratosDeEquipoMiembrosButton, registrosDeEquiposButton, registrosDeJugadoresButton, draftButton, agendasButton, clasificacionesButton, temporadasBoton, splitsBoton, equiposBoton};

    private JScrollPane scrollPane;
    private JPanel panelBotones;

    public BarraDeNavegacion(int codigoCuenta) {
        scrollPane.getVerticalScrollBar().setUnitIncrement(40);
        scrollPane.getVerticalScrollBar().setBlockIncrement(40);

        if (codigoCuenta > 0) {
            for (JButton boton: botones) {
                panelBotones.remove(boton);
            }
        }

    }



    public JButton getClasificacionesButton() {
        return clasificacionesButton;
    }

    public void setClasificacionesButton(JButton clasificacionesButton) {
        this.clasificacionesButton = clasificacionesButton;
    }

    public JButton getAgendasButton() {
        return agendasButton;
    }

    public void setAgendasButton(JButton agendasButton) {
        this.agendasButton = agendasButton;
    }

    public JButton getDraftButton() {
        return draftButton;
    }

    public void setDraftButton(JButton draftButton) {
        this.draftButton = draftButton;
    }

    public JButton getRegistrosDeJugadoresButton() {
        return registrosDeJugadoresButton;
    }

    public void setRegistrosDeJugadoresButton(JButton registrosDeJugadoresButton) {
        this.registrosDeJugadoresButton = registrosDeJugadoresButton;
    }

    public JButton getRegistrosDeEquiposButton() {
        return registrosDeEquiposButton;
    }

    public void setRegistrosDeEquiposButton(JButton registrosDeEquiposButton) {
        this.registrosDeEquiposButton = registrosDeEquiposButton;
    }

    public JButton getContratosDeEquipoMiembrosButton() {
        return contratosDeEquipoMiembrosButton;
    }

    public void setContratosDeEquipoMiembrosButton(JButton contratosDeEquipoMiembrosButton) {
        this.contratosDeEquipoMiembrosButton = contratosDeEquipoMiembrosButton;
    }

    public JButton getContratosDeEquipoJugadoresButton() {
        return contratosDeEquipoJugadoresButton;
    }

    public void setContratosDeEquipoJugadoresButton(JButton contratosDeEquipoJugadoresButton) {
        this.contratosDeEquipoJugadoresButton = contratosDeEquipoJugadoresButton;
    }

    public JButton getMiembrosButton() {
        return miembrosButton;
    }

    public void setMiembrosButton(JButton miembrosButton) {
        this.miembrosButton = miembrosButton;
    }

    public JButton getPartidosButton() {
        return partidosButton;
    }

    public void setPartidosButton(JButton partidosButton) {
        this.partidosButton = partidosButton;
    }

    public JButton getJugadoresBoton() {
        return jugadoresBoton;
    }

    public void setJugadoresBoton(JButton jugadoresBoton) {
        this.jugadoresBoton = jugadoresBoton;
    }

    public JButton getJornadasBoton() {
        return jornadasBoton;
    }

    public void setJornadasBoton(JButton jornadasBoton) {
        this.jornadasBoton = jornadasBoton;
    }



    public JButton getJugadorBoton() {
        return jugadoresBoton;
    }

    public void setJugadorBoton(JButton jugadorBoton) {
        this.jugadoresBoton = jugadorBoton;
    }

    public JButton getPerfilBoton() {
        return perfilBoton;
    }

    public void setPerfilBoton(JButton perfilBoton) {
        this.perfilBoton = perfilBoton;
    }

    public JButton getTemporadasBoton() {
        return temporadasBoton;
    }

    public void setTemporadasBoton(JButton temporadasBoton) {
        this.temporadasBoton = temporadasBoton;
    }

    public JButton getSplitsBoton() {
        return splitsBoton;
    }

    public void setSplitsBoton(JButton splitsBoton) {
        this.splitsBoton = splitsBoton;
    }

    public JButton getEquiposBoton() {
        return equiposBoton;
    }

    public void setEquiposBoton(JButton equiposBoton) {
        this.equiposBoton = equiposBoton;
    }

    public JPanel getPanel() {
        return panel;
    }

    public void setPanel(JPanel panel) {
        this.panel = panel;
    }
}
