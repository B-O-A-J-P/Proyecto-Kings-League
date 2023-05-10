INSERT INTO temporadas (ano, fecha_inicio_inscripcion, fecha_fin_inscripcion) VALUES (2021, TO_DATE('01/01/2021', 'DD/MM/YYYY'), TO_DATE('30/06/2021', 'DD/MM/YYYY'));

INSERT INTO equipos (nombre, logo, presupuesto) VALUES ('Equipo A', EMPTY_BLOB(), 1000000);

INSERT INTO agendas (email, telefono) VALUES ('example@example.com', '123456789');
INSERT INTO agendas (email, telefono) VALUES ('john@example.com', '987654321');
INSERT INTO agendas (email, telefono) VALUES ('jane@example.com', '123123123');

INSERT INTO jugadores (dni, nombre, apellido, pie, altura, cod_agenda) VALUES ('12345678A', 'Jugador', 'Uno', 'Izquierdo', 180, 0);
INSERT INTO jugadores (dni, nombre, apellido, pie, altura, cod_agenda) VALUES ('23456789B', 'Jugador', 'Dos', 'Derecho', 185, 1);
INSERT INTO jugadores (dni, nombre, apellido, pie, altura, cod_agenda) VALUES ('34567890C', 'Jugador', 'Tres', 'Izquierdo', 170, 2);

INSERT INTO registros_jugadores (cod_temporada, cod_jugador) VALUES (0, 0);
INSERT INTO registros_jugadores (cod_temporada, cod_jugador) VALUES (0, 1);
INSERT INTO registros_jugadores (cod_temporada, cod_jugador) VALUES (0, 2);

INSERT INTO contratos_equipo_jugador (cod_equipo, cod_jugador, salario, clausula, fecha_inicio, fecha_fin) VALUES (0, 0, 10500000, 10000000, TO_DATE('01/01/2022', 'DD/MM/YYYY'), TO_DATE('31/12/2023', 'DD/MM/YYYY'));
INSERT INTO contratos_equipo_jugador (cod_equipo, cod_jugador, salario, clausula, fecha_inicio, fecha_fin) VALUES (0, 1, 10500000, 10000000, TO_DATE('01/01/2022', 'DD/MM/YYYY'), TO_DATE('31/12/2023', 'DD/MM/YYYY'));
INSERT INTO contratos_equipo_jugador (cod_equipo, cod_jugador, salario, clausula, fecha_inicio, fecha_fin) VALUES (0, 2, 10500000, 10000000, TO_DATE('01/01/2022', 'DD/MM/YYYY'), TO_DATE('31/12/2023', 'DD/MM/YYYY'));

/*
Error starting at line : 19 in command -
INSERT INTO contratos_equipo_jugador (cod_equipo, cod_jugador, salario, clausula, fecha_inicio, fecha_fin) VALUES (0, 2, 10500000, 10000000, TO_DATE('01/01/2022', 'DD/MM/YYYY'), TO_DATE('31/12/2023', 'DD/MM/YYYY'))
Error at Command Line : 19 Column : 13
Error report -
SQL Error: ORA-20001: El equipo ya tiene 2 jugadores wild card.
ORA-06512: at "HR.MAX_JUGADORES_WILD_CARD", line 13
ORA-04088: error during execution of trigger 'HR.MAX_JUGADORES_WILD_CARD'
*/