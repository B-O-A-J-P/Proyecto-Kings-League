package com.boajp.controlador;


import com.boajp.modelo.*;
import com.boajp.repositorios.*;
import com.boajp.vista.Ventana;
import com.boajp.vista.carta.*;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;


public class VentanaControlador {
    private Ventana ventana;

    public VentanaControlador() {
        ventana= new Ventana();

    }
    public void crearCarta(){
        JornadaRepositorio jornada = new JornadaRepositorio();
        PartidoRepositorio partido = new PartidoRepositorio();
        RegistroEquipoRespositorio registroRepositorio = new RegistroEquipoRespositorio();
        ArrayList<JornadaEntidad> lista= new ArrayList<>();
        try {

            lista = (ArrayList<JornadaEntidad>) jornada.buscarTodasJornadas();

        }catch (Exception e) {
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
        Cabecera cabecera = new Cabecera(lista.get(lista.size()-1).getFecha().toString());
        List<PartidoEntidad> partidosLista = partido.seleccionarPartidosPorJornada(lista.get(lista.size()-1));

        JLabel jLabels[][] = new JLabel[6][3];
        for (int x = 0; x < 6; x++) {
            jLabels[x][0] = new JLabel(partidosLista.get(x).getEquipoUno().getNombre());
            jLabels[x][1] = new JLabel("vs");
            jLabels[x][2] = new JLabel(partidosLista.get(x).getEquipoDos().getNombre());
        }

        CalendarioTabla calendarioTabla = new CalendarioTabla(jLabels);
        ArrayList<CartaAbstracta> cartaAbstractas = new ArrayList<>();
        cartaAbstractas.add(new Carta(cabecera, calendarioTabla));



        List<RegistroEquipoEntidad> registroEquipos = registroRepositorio.seleccionarTodosLosEquiposParticipantes();
        List<EquipoEntidad> listaDeEquipos = new ArrayList<>();
        for (RegistroEquipoEntidad r : registroEquipos) {
            listaDeEquipos.add(r.getEquipo());
        }

        ArrayList<CartaAbstracta> equipoCartas = new ArrayList<>();
        for (EquipoEntidad e : listaDeEquipos) {
            equipoCartas.add(new EquipoCarta(e.getNombre()));
        }
        ventana.mostrarPanelDeInicio(cartaAbstractas, equipoCartas);
    }
}
