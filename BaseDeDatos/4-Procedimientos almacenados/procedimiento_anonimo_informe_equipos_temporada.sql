SET SERVEROUTPUT ON;
DECLARE
    mi_cursor SYS_REFCURSOR;
    codEquipo equipos.cod_equipo%type;
    nombre equipos.nombre%type;
    presupuesto equipos.presupuesto%type;
    numeroJugadores NUMBER;
    codPresidente miembros.cod_miembro%type;    
    nombrePresidente VARCHAR2(100);
    fechaInicioPresidencia DATE;
    codEntrenador miembros.cod_miembro%type;    
    nombreEntrenador VARCHAR2(100);
    codStaff miembros.cod_miembro%type;    
    nombreStaff VARCHAR2(100);
BEGIN
    informe_equipos_temporada(0, out_cursor => mi_cursor);
    LOOP
        FETCH mi_cursor INTO codEquipo, nombre, presupuesto, numeroJugadores, codPresidente, 
        nombrePresidente, fechaInicioPresidencia, codEntrenador, nombreEntrenador, codStaff, nombreStaff;
        EXIT WHEN mi_cursor%NOTFOUND;
        DBMS_OUTPUT.PUT_LINE(
        'Código de equipo: ' || codEquipo || ' || ' || 
        'Nombre de equipo: ' || nombre || ' || ' || 
        'Presupuesto de equipo: ' || presupuesto || ' || ' ||
        'Código de presidente: ' || codPresidente || ' || ' ||
        'Presidente: ' || INITCAP(nombrePresidente) || ' (desde ' || fechaInicioPresidencia || ')' || ' || ' ||
        'Código de entrenador: ' || codEntrenador || ' || ' ||
        'Entrenador: ' || INITCAP(nombreEntrenador) || ' || ' ||
        'Código de staff: ' || codStaff || ' || ' ||
        'Staff: ' || INITCAP(nombreStaff) || ' || ' || 
        'Número de jugadores: ' || numeroJugadores);
    END LOOP;
    CLOSE mi_cursor;
END;

/*
Código de equipo: 6 || Nombre de equipo: Equipo G || Presupuesto de equipo: 1100000 || Código de presidente:  || Presidente:   (desde ) || Código de entrenador:  || Entrenador:   || Código de staff:  || Staff:   || Número de jugadores: 0
Código de equipo: 1 || Nombre de equipo: Equipo B || Presupuesto de equipo: 1000000 || Código de presidente: 1 || Presidente: María González (desde 01-JAN-22) || Código de entrenador:  || Entrenador:   || Código de staff:  || Staff:   || Número de jugadores: 0
Código de equipo: 7 || Nombre de equipo: Equipo H || Presupuesto de equipo: 700000 || Código de presidente:  || Presidente:   (desde ) || Código de entrenador:  || Entrenador:   || Código de staff:  || Staff:   || Número de jugadores: 0
Código de equipo: 11 || Nombre de equipo: Equipo L || Presupuesto de equipo: 1050000 || Código de presidente:  || Presidente:   (desde ) || Código de entrenador:  || Entrenador:   || Código de staff:  || Staff:   || Número de jugadores: 0
Código de equipo: 2 || Nombre de equipo: Equipo C || Presupuesto de equipo: 750000 || Código de presidente:  || Presidente:   (desde ) || Código de entrenador:  || Entrenador:   || Código de staff:  || Staff:   || Número de jugadores: 0
Código de equipo: 8 || Nombre de equipo: Equipo I || Presupuesto de equipo: 900000 || Código de presidente:  || Presidente:   (desde ) || Código de entrenador:  || Entrenador:   || Código de staff:  || Staff:   || Número de jugadores: 0
Código de equipo: 4 || Nombre de equipo: Equipo E || Presupuesto de equipo: 800000 || Código de presidente:  || Presidente:   (desde ) || Código de entrenador:  || Entrenador:   || Código de staff:  || Staff:   || Número de jugadores: 0
Código de equipo: 5 || Nombre de equipo: Equipo F || Presupuesto de equipo: 950000 || Código de presidente:  || Presidente:   (desde ) || Código de entrenador:  || Entrenador:   || Código de staff:  || Staff:   || Número de jugadores: 0
Código de equipo: 10 || Nombre de equipo: Equipo K || Presupuesto de equipo: 850000 || Código de presidente:  || Presidente:   (desde ) || Código de entrenador:  || Entrenador:   || Código de staff:  || Staff:   || Número de jugadores: 0
Código de equipo: 3 || Nombre de equipo: Equipo D || Presupuesto de equipo: 1250000 || Código de presidente:  || Presidente:   (desde ) || Código de entrenador:  || Entrenador:   || Código de staff:  || Staff:   || Número de jugadores: 0
Código de equipo: 9 || Nombre de equipo: Equipo J || Presupuesto de equipo: 1200000 || Código de presidente:  || Presidente:   (desde ) || Código de entrenador:  || Entrenador:   || Código de staff:  || Staff:   || Número de jugadores: 0
Código de equipo: 0 || Nombre de equipo: Equipo A || Presupuesto de equipo: 1000000 || Código de presidente: 0 || Presidente: Juan Pérez (desde 01-JAN-22) || Código de entrenador:  || Entrenador:   || Código de staff:  || Staff:   || Número de jugadores: 9


PL/SQL procedure successfully completed.
*/
