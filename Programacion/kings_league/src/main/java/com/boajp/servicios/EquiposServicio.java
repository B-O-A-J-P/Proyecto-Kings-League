package com.boajp.servicios;

import com.boajp.modelo.*;
import com.boajp.repositorios.ContratoEquipoJugadorRepositorio;
import com.boajp.repositorios.ContratoEquipoMiembroRepositorio;
import com.boajp.repositorios.EquipoRepositorio;
import com.boajp.vistas.carta.CartaAbstracta;
import com.boajp.vistas.carta.EquipoCarta;
import com.boajp.vistas.carta.JugadorCarta;
import com.boajp.vistas.carta.MiembroCarta;
import com.boajp.vistas.componentes.PanelDeError;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class EquiposServicio {
    private final EquipoRepositorio equipoRepositorio;
    private final ContratoEquipoJugadorRepositorio contratoEquipoJugadorRepositorio;
    private final ContratoEquipoMiembroRepositorio contratoEquipoMiembroRepositorio;
    public EquiposServicio() {
        equipoRepositorio = new EquipoRepositorio();
        contratoEquipoJugadorRepositorio = new ContratoEquipoJugadorRepositorio();
        contratoEquipoMiembroRepositorio = new ContratoEquipoMiembroRepositorio();
    }

    public ArrayList<CartaAbstracta> crearCartasDeEquipos() {
        List<EquipoEntidad> equipoEntidadList = new ArrayList<>();
        try {
            equipoEntidadList = equipoRepositorio.buscarEquipoParticipantes();
        } catch (Exception exception) {
            new PanelDeError(exception.getMessage());
        }

        ArrayList<CartaAbstracta> cartasEquipos = new ArrayList<>();
        for (EquipoEntidad equipo : equipoEntidadList) {
            CartaAbstracta carta = new EquipoCarta(equipo.getNombre());
            carta.setCodigoDeCarta(equipo.getCodEquipo());
            cartasEquipos.add(carta);
        }
        return cartasEquipos;
    }

    public ArrayList<CartaAbstracta> crearCartasDeMiembros() {
        ArrayList<CartaAbstracta> cartasMiembros = new ArrayList<>();
        List<ContratoEquipoJugadorEntidad> contratoEquipoJugadorEntidadList = contratoEquipoJugadorRepositorio.buscarContratosVigentes();
        List<ContratoEquipoMiembroEntidad> contratoEquipoMiembroEntidadList = contratoEquipoMiembroRepositorio.buscarContratosVigentes();

        for (ContratoEquipoJugadorEntidad contrato : contratoEquipoJugadorEntidadList) {
            String nombreCompleto = contrato.getJugador().getNombre() + " " + contrato.getJugador().getApellido();
            HashMap<String, String> caracteristicas = new HashMap<>();
            caracteristicas.put("Altura", String.valueOf(contrato.getJugador().getAltura()));
            caracteristicas.put("Pie", contrato.getJugador().getPie());

            CartaAbstracta cartaJugador = new JugadorCarta(nombreCompleto, caracteristicas);
            cartaJugador.setCodigoDeCarta(contrato.getEquipo().getCodEquipo());
            cartasMiembros.add(cartaJugador);
        }

        for (ContratoEquipoMiembroEntidad contrato : contratoEquipoMiembroEntidadList) {
            CartaAbstracta cartaMiembro = new MiembroCarta(contrato.getMiembro());
            cartaMiembro.setCodigoDeCarta(contrato.getEquipo().getCodEquipo());
            cartasMiembros.add(cartaMiembro);
        }
        return cartasMiembros;
    }

    public String[][] getFilas() {
        List<EquipoEntidad> equipoEntidadList = equipoRepositorio.seleccionarTodosLosEquipos();
        String[][] filas = new String[equipoEntidadList.size()][equipoEntidadList.get(0).getAtributos().length];
        for ( int x = 0; x< equipoEntidadList.size();x++){
            filas[x] = equipoEntidadList.get(x).toArray();
        }
        return filas;
    }

    public String[] getColumnas(){
        return new EquipoEntidad().getAtributos();
    }

    public EquipoEntidad getEquipo(int codigo) {
        return equipoRepositorio.buscarEquipo(codigo);
    }

    public void crearEquipo(String nombre, int presupuesto) {
        EquipoEntidad e = new EquipoEntidad();
        e.setNombre(nombre);
        e.setPresupuesto(presupuesto);
        equipoRepositorio.insertar(e);
    }

    public void modificarEquipo(EquipoEntidad equipo) {
        equipoRepositorio.modificar(equipo);
    }

    public void eliminarEquipo(int cod) {
        equipoRepositorio.eliminar(cod);
    }

    public String[] getCodigos() {
        List<EquipoEntidad> lista = equipoRepositorio.seleccionarTodosLosEquipos();
        String[] codigos = new String[lista.size()];
        for ( int x = 0; x < codigos.length; x++) {
            codigos[x] = String.valueOf(lista.get(x).getCodEquipo());
        }
        return codigos;
    }

}