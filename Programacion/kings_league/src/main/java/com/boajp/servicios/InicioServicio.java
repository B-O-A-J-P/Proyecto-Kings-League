package com.boajp.servicios;

import com.boajp.modelo.*;
import com.boajp.repositorios.ClasificacionRepositorio;
import com.boajp.repositorios.JornadaRepositorio;
import com.boajp.repositorios.PartidosRepositorio;
import com.boajp.repositorios.RegistroEquipoRepositorio;
import com.boajp.vistas.carta.CartaAbstracta;
import com.boajp.vistas.carta.ClasificacionCarta;
import com.boajp.vistas.carta.EquipoCarta;
import com.boajp.vistas.carta.JornadaCarta;
import com.boajp.vistas.componentes.PanelDeError;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class InicioServicio {
    private JornadaRepositorio jornadaRepositorio;
    private ClasificacionRepositorio clasificacionRepositorio;
    private RegistroEquipoRepositorio registroEquipoRepositorio;
    private PartidosRepositorio partidosRepositorio;


    public InicioServicio() {
        jornadaRepositorio = new JornadaRepositorio();
        clasificacionRepositorio = new ClasificacionRepositorio();
        registroEquipoRepositorio = new RegistroEquipoRepositorio();
        partidosRepositorio = new PartidosRepositorio();
    }

    public CartaAbstracta crearCartaUltimaJornada() {
        JornadaEntidad jornadaEntidad = null;

        try {
            jornadaEntidad = jornadaRepositorio.buscarUltimaJornada();
        } catch (Exception exception) {
            new PanelDeError(exception.getMessage());
        }
        List<PartidoEntidad> listaDePartidos = (List<PartidoEntidad>) partidosRepositorio.buscarPartidosDeJornada(jornadaEntidad);
        String[][] datosDePartido = new String[listaDePartidos.size()][3];
        for ( int x = 0; x < datosDePartido.length; x++ ) {
            datosDePartido[x][0] = listaDePartidos.get(x).getEquipoUno().getNombre();
            datosDePartido[x][1] = "vs";
            datosDePartido[x][2] = listaDePartidos.get(x).getEquipoDos().getNombre();
        }

        LocalDate fecha = jornadaEntidad.getFecha();
        String formato = "dd 'de' MMMM 'de' yyyy";
        DateTimeFormatter formatoLocal = DateTimeFormatter.ofPattern(formato, new Locale("es"));

        JornadaCarta jornadaCarta  = new JornadaCarta(jornadaEntidad.getNumero(), fecha.format(formatoLocal), datosDePartido);
        jornadaCarta.crearCuerpo();
        return jornadaCarta;
    }

    public CartaAbstracta crearCartaUltimaClasificacion() {
        List<ClasificacionEntidad> tablaDeClasificacion = null;

        try {
            tablaDeClasificacion = clasificacionRepositorio.buscarUltimaClasificacion();
        } catch (Exception exception) {
            new PanelDeError(exception.getMessage());
        }

        String[] columnas = new String[]{"Posición", "Equipo"};
        String[][] filas = new String[tablaDeClasificacion.size()][columnas.length];

        for ( int x = 0; x < filas.length; x++ ) {
            filas[x][0] = String.valueOf(tablaDeClasificacion.get(x).getPosicion());
            filas[x][1] = tablaDeClasificacion.get(x).getEquipo().getNombre();
        }


        ClasificacionCarta clasificacionCarta = new ClasificacionCarta(columnas, filas);
        return clasificacionCarta;
    }

    public List<CartaAbstracta> crearCartasDeEquiposParticipantes() {
        List<RegistroEquipoEntidad> listaDeRegistrosEquipos = new ArrayList<>();

        try {
            listaDeRegistrosEquipos = registroEquipoRepositorio.buscarEquiposParticipantesUltimaTemporada();
        } catch (Exception exception){
            new PanelDeError(exception.getMessage());
        }

        List<EquipoEntidad> listaDeEquipos = new ArrayList<>();
        for (RegistroEquipoEntidad registro : listaDeRegistrosEquipos) {
            listaDeEquipos.add(registro.getEquipo());
        }

        List<CartaAbstracta> listaDeCartasDeEquipos = new ArrayList<>();
        for (EquipoEntidad equipo : listaDeEquipos) {
            EquipoCarta equipoCarta = new EquipoCarta(equipo.getNombre());
            equipoCarta.setCodigoDeCarta(equipo.getCodEquipo());
            listaDeCartasDeEquipos.add(new EquipoCarta(equipo.getNombre()));
        }

        return listaDeCartasDeEquipos;
    }

}
