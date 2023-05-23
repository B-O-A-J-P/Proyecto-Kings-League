package com.boajp.controladores.controladoresPanelDeUsuario;

import com.boajp.controladores.controladoresPanelDeUsuario.controladoresCrud.*;
import com.boajp.vistas.usuario.PanelDeCrud;

public class PanelDeCrudControlador {
    private PanelDeCrud panelDeCrud;

    public PanelDeCrudControlador() {
        panelDeCrud = new PanelDeCrud();
    }

    public PanelDeCrud getPanelDeCrudTemporadas() {
        new TemporadasControlador(panelDeCrud);
        return panelDeCrud;
    }

    public PanelDeCrud getPanelDeCrudSplit() {
        new SplitsControlador(panelDeCrud);
        return panelDeCrud;
    }

    public PanelDeCrud getPanelDeCrudJugadores() {
        new JugadoresControlador(panelDeCrud);
        return panelDeCrud;
    }

    public PanelDeCrud getPanelDeCrudJornadas() {
        new JornadasControlador(panelDeCrud);
        return panelDeCrud;
    }

    public PanelDeCrud getPanelDeCrudPartidos() {
        new PartidosControlador(panelDeCrud);
        return panelDeCrud;
    }

    public PanelDeCrud getPanelDeCrudEquipos() {
        new EquiposControlador(panelDeCrud);
        return panelDeCrud;
    }

    public PanelDeCrud getPanelDeCrudMiembros() {
        new MiembrosControlador(panelDeCrud);
        return panelDeCrud;
    }

    public PanelDeCrud getPanelDeCrudContratosEquipoJugadores() {
        new ContratosEquiposJugadoresControlador(panelDeCrud);
        return panelDeCrud;
    }

    public PanelDeCrud getPanelDeCrudContratosEquipoMiembros() {
        new ContratosEquiposMiembrosControlador(panelDeCrud);
        return panelDeCrud;
    }

    public PanelDeCrud getPanelDeCrudRegistrosEquipos() {
        new RegistrosEquiposControlador(panelDeCrud);
        return panelDeCrud;
    }

    public PanelDeCrud getPanelDeCrudRegistrosJugadores() {
        new RegistroJugadorControlador(panelDeCrud);
        return panelDeCrud;
    }

    public PanelDeCrud getPanelDeCrudDraft() {
        new DraftControlador(panelDeCrud);
        return panelDeCrud;
    }

    public PanelDeCrud getPanelDeCrudAgendas() {
        new AgendaCrontrolador(panelDeCrud);
        return panelDeCrud;
    }

    public PanelDeCrud getPanelDeCrudClasificaciones() {
        new ClasificacionesControlador(panelDeCrud);
        return panelDeCrud;
    }

    public PanelDeCrud getPanelDeCrud() {
        return panelDeCrud;
    }

    public void setPanelDeCrud(PanelDeCrud panelDeCrud) {
        this.panelDeCrud = panelDeCrud;
    }
}
