INSERT INTO temporadas (ano, fecha_inicio_inscripcion, fecha_fin_inscripcion) VALUES (2021, TO_DATE('01/01/2021', 'DD/MM/YYYY'), TO_DATE('30/06/2021', 'DD/MM/YYYY'));

INSERT INTO equipos (nombre, logo, presupuesto) VALUES ('Equipo A', EMPTY_BLOB(), 1000000);

INSERT INTO registros_equipos (cod_temporada, cod_equipo) VALUES (0, 0);

/*
Error starting at line : 5 in command -
INSERT INTO registros_equipos (cod_temporada, cod_equipo) VALUES (0, 0)
Error at Command Line : 5 Column : 13
Error report -
SQL Error: ORA-20001: El equipo tiene que tener mínimo 8 jugadores.
ORA-06512: at "HR.MIN_JUGADORES", line 12
ORA-04088: error during execution of trigger 'HR.MIN_JUGADORES'
*/