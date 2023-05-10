package com.boajp;


import com.boajp.modelo.TemporadaEntidad;
import com.boajp.repositorios.TemporadaRepositorio;
import com.boajp.vista.vLoginRegistro;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Aplicacion {

    static public void main(String... args) {
        List<TemporadaEntidad> lista = new ArrayList<>();
        TemporadaRepositorio temporadaRepositorio = new TemporadaRepositorio();
        try {
            lista = temporadaRepositorio.buscarTodasTemporadas();
        } catch (Exception exception) {

        }

        System.out.println(lista.get(0));

    }

}
