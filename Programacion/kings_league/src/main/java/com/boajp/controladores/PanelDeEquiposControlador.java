package com.boajp.controladores;

import com.boajp.servicios.EquiposServicio;
import com.boajp.vistas.*;
import com.boajp.vistas.componentes.PanelDeError;


import javax.swing.*;

public class PanelDeEquiposControlador {
    private PanelEquipos panelEquipos;
    private EquiposServicio equiposServicio;

    public JPanel inicializarPanelEquipos(){
        try {
            equiposServicio = new EquiposServicio();
            panelEquipos = new PanelEquipos(
                    equiposServicio.crearCartasDeEquipos(),
                    equiposServicio.crearCartasDeMiembros(),
                    Controlador.VENTANA.getScrollPane());

        } catch (Exception exception) {
            new PanelDeError(exception.getMessage());
        }
        return panelEquipos;
    }

}
