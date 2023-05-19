package com.boajp.controladores;

import com.boajp.controladores.controladoresCrud.EliminarJugadorControlador;
import com.boajp.modelo.CuentaEntidad;
import com.boajp.vista.Usuarios.JugadoresVistaAdmin;
import com.boajp.vista.Usuarios.PanelAdmin;
import com.boajp.vista.Ventana;

public class VentanaControlador {
    public static Ventana VENTANA = new Ventana();
    private static CuentaEntidad usuario;


    public static void mostrarPanelDeInicio() {
        PanelDeInicioControlador panelDeInicioControlador = new PanelDeInicioControlador();
        VENTANA.setContenidoPrincipal(panelDeInicioControlador.inicializarPanel(VENTANA.getScrollPane()));
        VENTANA.revalidate();
        VENTANA.repaint();
    }

    public static void mostrarPanelDeJugadores() {
        PanelJugadoresControlador panelJugadoresControlador = new PanelJugadoresControlador();
        VENTANA.setContenidoPrincipal(panelJugadoresControlador.inicializarPanel(VENTANA.getScrollPane()));
    }

    public static void mostrarPanelDeFormulario() {
        PanelFormularioControlador panelFormularioControlador = new PanelFormularioControlador();
        VENTANA.setContenidoPrincipal(panelFormularioControlador.inicializarFormulario());
    }

    public static void mostrarPanelDeAjustes(CuentaEntidad usuario) {
        PanelDeAjusteControlador panelDeAjusteControlador = new PanelDeAjusteControlador();
        VENTANA.setContenidoPrincipal(panelDeAjusteControlador.inicializarPanelDeAjuste(usuario));
    }

    public static void mostrarPanelDeEquipos() {
        PanelDeEquiposControlador panelDeEquiposControlador = new PanelDeEquiposControlador();
        VENTANA.setContenidoPrincipal(panelDeEquiposControlador.inicializarPanelEquipos());
    }

    public static void mostrarPanelCalendario() {
        PanelCalendarioControlador panelCalendario = new PanelCalendarioControlador();
        VENTANA.setContenidoPrincipal(panelCalendario.inicializarCalendario());
    }

    public static void mostrarPanelClasificacion() {
        PanelClasificacionControlador panelClasificacionControlador = new PanelClasificacionControlador();
        VENTANA.setContenidoPrincipal(panelClasificacionControlador.inicializarClasificacion());
    }

    public static void mostrarOpciones(String op){
        PanelAdmin panelAdmin = new PanelAdmin(op);
        VENTANA.setContenidoPrincipal(panelAdmin.getpPrincipal());
    }

    public static void mostrarInsertarJugador(){
        JugadoresVistaAdmin jugadoresVistaAdmin = new JugadoresVistaAdmin();
        VENTANA.setContenidoPrincipal(PanelClasificacionControlador.inicializarInsertarJugador());
    }

    public static void mostrarEliminarJugador(){
        EliminarJugadorControlador eliminarJugadorControlador = new EliminarJugadorControlador();
        VENTANA.setContenidoPrincipal(eliminarJugadorControlador.inicializarVistaEliminarJugador());
    }


    /*
    public static void mostrarInsertarVentana(){
        PanelAdminControlador panelAdminControlador = new PanelAdminControlador();
    }
    public static void mostrarActualizarVentana(){
        PanelAdminControlador panelAdminControlador = new PanelAdminControlador();

    }*/

    /*public static void mostrarPanelAdmin(){
       PanelDeAjusteControlador panelDeAjusteControlador = new PanelDeAjusteControlador();
       VENTANA.setContenidoPrincipal(panelDeAjusteControlador.inicializarOpciones());
    }
    */

    public static void setUsuario(CuentaEntidad cuentaEntidad) {
        usuario = cuentaEntidad;
    }

    public static CuentaEntidad getUsuario() {
        return usuario;
    }

    public Ventana getVENTANA() {
        return VENTANA;
    }
}

