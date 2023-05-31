package com.boajp.servicios;

import com.boajp.modelo.JugadorEntidad;
import com.boajp.modelo.RegistroJugadorEntidad;
import com.boajp.repositorios.JugadorRepositorio;
import com.boajp.repositorios.RegistroJugadorRepositorio;
import com.boajp.vistas.carta.CartaAbstracta;
import com.boajp.vistas.carta.JugadorCarta;
import com.boajp.vistas.componentes.PanelDeError;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class JugadoresServicio {
    private RegistroJugadorRepositorio registroJugadorRepositorio;
    private JugadorRepositorio jugadorRepositorio;
    public JugadoresServicio() {
        registroJugadorRepositorio = new RegistroJugadorRepositorio();
        jugadorRepositorio = new JugadorRepositorio();
    }

    public void anadirJugador(String nombre, String apellido, String dni, String pie, Integer altura) {
        JugadorEntidad jugadorEntidad = new JugadorEntidad(nombre, apellido, dni, pie, altura);
        jugadorRepositorio.insertar(jugadorEntidad);
    }

    public JugadorEntidad buscar(int codigoJugador) {
        return jugadorRepositorio.buscar(codigoJugador);
    }

    public void modificar(JugadorEntidad jugador) {
        jugadorRepositorio.modificar(jugador);
    }

    public void eliminar(int codigoJugador) {
        jugadorRepositorio.eliminar(codigoJugador);
    }

    public String[] getColumnas() {
        return new JugadorEntidad().getAtributos();
    }

    public String[][] getFilas() {
        List<JugadorEntidad> jugadorEntidadList = jugadorRepositorio.seleccionarTodosLosJugadores();
        String[][] filas = new String[jugadorEntidadList.size()][jugadorEntidadList.get(0).getAtributos().length];
        for ( int x = 0; x < filas.length; x++ ) {
            filas[x] = jugadorEntidadList.get(x).toArray();
        }
        return filas;
    }

    public ArrayList<CartaAbstracta> crearCartasJugadoresUltimaTemporada() {
        List<RegistroJugadorEntidad> listaDeRegistrosDeJugadores = new ArrayList<>();

        try {
            listaDeRegistrosDeJugadores = registroJugadorRepositorio.buscarJugadoresRegistradosUltimaTemporada();
        }catch (Exception exception) {
            new PanelDeError(exception.getMessage());
        }

        List<CartaAbstracta> cartasDeJugadores = new ArrayList<>();
        for ( RegistroJugadorEntidad registroJugador : listaDeRegistrosDeJugadores ) {
            String nombreCompleto = registroJugador.getJugador().getNombre() + " " + registroJugador.getJugador().getApellido();
            HashMap<String, String> caracteristicas = new HashMap<>();
            caracteristicas.put("Altura", String.valueOf(registroJugador.getJugador().getAltura()));
            caracteristicas.put("Pie", registroJugador.getJugador().getPie());
            cartasDeJugadores.add(new JugadorCarta(nombreCompleto, caracteristicas));
        }

        return (ArrayList<CartaAbstracta>) cartasDeJugadores;
    }

    public String[] getCodigos() {
        List<JugadorEntidad> lista = jugadorRepositorio.seleccionarTodosLosJugadores();
        String[] codigos = new String[lista.size()];
        for (int x = 0; x < codigos.length; x++) {
            codigos[x] = String.valueOf(lista.get(x).getCodJugador());
        }
        return codigos;
    }
}
