package com.boajp.utilidades;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class FechaUtilidades {

    public static String fechaToString(LocalDate fecha) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return fecha.format(formatter);
    }

    public static String fechaToString(LocalDateTime fecha) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return fecha.format(formatter);
    }

    public static String fechaHoraToString(LocalDateTime fechaHora) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return fechaHora.format(formatter);
    }

    public static LocalDate stringToFecha(String fecha) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return LocalDate.parse(fecha, formatter);
    }

    public static LocalDateTime stringToFechaHora(String fecha) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return LocalDateTime.parse(fecha, formatter);
    }

    public static LocalDateTime dateToLocalDateTime(LocalDate fecha, String hora) {
        LocalTime time = LocalTime.parse(hora, DateTimeFormatter.ofPattern("HH:mm"));
        LocalDateTime fechaHora = fecha.atTime(time);
        return fechaHora;
    }

}
