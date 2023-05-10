DECLARE
    out_ref_cursor SYS_REFCURSOR;
    v_cod_temporada temporadas.cod_temporada%type;
    v_codJugador jugadores.cod_jugador%type;
    v_nombreJugador jugadores.nombre%type;
    v_nombreApellido jugadores.apellido%type;
    v_codContrato contratos_equipo_jugador.cod_contrato%type;
    v_clausula contratos_equipo_jugador.clausula%type;
    v_salario contratos_equipo_jugador.salario%type;
    v_codEquipo contratos_equipo_jugador.cod_equipo%type;
    v_nombreEquipo equipos.nombre%type;
BEGIN
    informe_jugador_ultima_temporada(0, out_cursor => out_ref_cursor);

    LOOP
        FETCH out_ref_cursor INTO v_codJugador, v_nombreJugador, v_nombreApellido, v_codContrato, v_clausula, v_salario, v_codEquipo, v_nombreEquipo;
        EXIT WHEN out_ref_cursor%NOTFOUND;
        DBMS_OUTPUT.PUT_LINE(
            'Código de jugador: ' || v_codJugador || ' || ' ||
            'Nombre de jugador: ' || v_nombreJugador || ' ' || v_nombreApellido || ' || ' ||
            'Código contrato: ' || v_codContrato || ' || ' ||
            'Cláusula: ' || v_clausula || ' || ' ||
            'Salario: ' || v_salario || ' || ' ||
            'Código equipo: ' || v_codEquipo || ' || ' ||
            'Nombre equipo: ' || v_nombreEquipo 
        );
    END LOOP;
    CLOSE out_ref_cursor;
END;
/*
Código de jugador: 0 || Nombre de jugador: Jugador Uno || Código contrato: 0 || Cláusula: 10000000 || Salario: 10500000 || Código equipo: 0 || Nombre equipo: Equipo A
Código de jugador: 1 || Nombre de jugador: Jugador Dos || Código contrato: 1 || Cláusula: 10000000 || Salario: 10500000 || Código equipo: 0 || Nombre equipo: Equipo A
Código de jugador: 2 || Nombre de jugador: Jugador Tres || Código contrato: 2 || Cláusula: 10000000 || Salario: 10500000 || Código equipo: 0 || Nombre equipo: Equipo A
Código de jugador: 3 || Nombre de jugador: Jugador Cuatro || Código contrato: 3 || Cláusula: 10000000 || Salario: 10500000 || Código equipo: 0 || Nombre equipo: Equipo A
Código de jugador: 4 || Nombre de jugador: Jugador Cinco || Código contrato: 4 || Cláusula: 10000000 || Salario: 10500000 || Código equipo: 0 || Nombre equipo: Equipo A
Código de jugador: 5 || Nombre de jugador: Jugador Seis || Código contrato: 5 || Cláusula: 10000000 || Salario: 10500000 || Código equipo: 0 || Nombre equipo: Equipo A
Código de jugador: 6 || Nombre de jugador: Jugador Siete || Código contrato: 6 || Cláusula: 10000000 || Salario: 10500000 || Código equipo: 0 || Nombre equipo: Equipo A
Código de jugador: 7 || Nombre de jugador: Jugador Ocho || Código contrato: 7 || Cláusula: 10000000 || Salario: 10500000 || Código equipo: 0 || Nombre equipo: Equipo A
Código de jugador: 8 || Nombre de jugador: Jugador Nueve || Código contrato: 8 || Cláusula: 10000000 || Salario: 10500000 || Código equipo: 0 || Nombre equipo: Equipo A


PL/SQL procedure successfully completed.

*/