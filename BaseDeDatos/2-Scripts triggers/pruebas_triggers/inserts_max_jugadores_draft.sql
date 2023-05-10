INSERT INTO temporadas (ano, fecha_inicio_inscripcion, fecha_fin_inscripcion) VALUES (2021, TO_DATE('01/01/2021', 'DD/MM/YYYY'), TO_DATE('30/06/2021', 'DD/MM/YYYY'));

INSERT INTO equipos (nombre, logo, presupuesto) VALUES ('Equipo A', EMPTY_BLOB(), 1000000);

INSERT INTO agendas (email, telefono) VALUES ('example@example.com', '123456789');
INSERT INTO agendas (email, telefono) VALUES ('john@example.com', '987654321');
INSERT INTO agendas (email, telefono) VALUES ('jane@example.com', '123123123');
INSERT INTO agendas (email, telefono) VALUES ('mark@example.com', '456789123');
INSERT INTO agendas (email, telefono) VALUES ('sara@example.com', '789456123');
INSERT INTO agendas (email, telefono) VALUES ('peter@example.com', '159357246');
INSERT INTO agendas (email, telefono) VALUES ('lisa@example.com', '654987321');
INSERT INTO agendas (email, telefono) VALUES ('mike@example.com', '321654987');

INSERT INTO jugadores (dni, nombre, apellido, pie, altura, cod_agenda) VALUES ('12345678A', 'Jugador', 'Uno', 'Izquierdo', 180, 0);
INSERT INTO jugadores (dni, nombre, apellido, pie, altura, cod_agenda) VALUES ('23456789B', 'Jugador', 'Dos', 'Derecho', 185, 1);
INSERT INTO jugadores (dni, nombre, apellido, pie, altura, cod_agenda) VALUES ('34567890C', 'Jugador', 'Tres', 'Izquierdo', 170, 2);
INSERT INTO jugadores (dni, nombre, apellido, pie, altura, cod_agenda) VALUES ('45678901D', 'Jugador', 'Cuatro', 'Derecho', 190, 3);
INSERT INTO jugadores (dni, nombre, apellido, pie, altura, cod_agenda) VALUES ('56789012E', 'Jugador', 'Cinco', 'Izquierdo', 175, 4);
INSERT INTO jugadores (dni, nombre, apellido, pie, altura, cod_agenda) VALUES ('67890123F', 'Jugador', 'Seis', 'Derecho', 195, 5);
INSERT INTO jugadores (dni, nombre, apellido, pie, altura, cod_agenda) VALUES ('78901234G', 'Jugador', 'Siete', 'Izquierdo', 180, 6);
INSERT INTO jugadores (dni, nombre, apellido, pie, altura, cod_agenda) VALUES ('89012345H', 'Jugador', 'Ocho', 'Derecho', 200, 7);
INSERT INTO jugadores (dni, nombre, apellido, pie, altura, cod_agenda) VALUES ('89012343M', 'Jugador', 'Nueve', 'Derecho', 200, 7);

INSERT INTO registros_jugadores (cod_temporada, cod_jugador) VALUES (0, 0);
INSERT INTO registros_jugadores (cod_temporada, cod_jugador) VALUES (0, 1);
INSERT INTO registros_jugadores (cod_temporada, cod_jugador) VALUES (0, 2);
INSERT INTO registros_jugadores (cod_temporada, cod_jugador) VALUES (0, 3);
INSERT INTO registros_jugadores (cod_temporada, cod_jugador) VALUES (0, 4);
INSERT INTO registros_jugadores (cod_temporada, cod_jugador) VALUES (0, 5);
INSERT INTO registros_jugadores (cod_temporada, cod_jugador) VALUES (0, 6);
INSERT INTO registros_jugadores (cod_temporada, cod_jugador) VALUES (0, 7);
INSERT INTO registros_jugadores (cod_temporada, cod_jugador) VALUES (0, 8);

INSERT INTO draft (cod_temporada, cod_jugador, posicion) VALUES (0, 0, 1);
INSERT INTO draft (cod_temporada, cod_jugador, posicion) VALUES (0, 1, 2);
INSERT INTO draft (cod_temporada, cod_jugador, posicion) VALUES (0, 2, 3);
INSERT INTO draft (cod_temporada, cod_jugador, posicion) VALUES (0, 3, 4);
INSERT INTO draft (cod_temporada, cod_jugador, posicion) VALUES (0, 4, 5);
INSERT INTO draft (cod_temporada, cod_jugador, posicion) VALUES (0, 5, 6);
INSERT INTO draft (cod_temporada, cod_jugador, posicion) VALUES (0, 6, 7);
INSERT INTO draft (cod_temporada, cod_jugador, posicion) VALUES (0, 7, 8);
INSERT INTO draft (cod_temporada, cod_jugador, posicion) VALUES (0, 8, 9);

INSERT INTO contratos_equipo_jugador (cod_equipo, cod_jugador, salario, clausula, fecha_inicio, fecha_fin) VALUES (0, 0, 10500000, 10000000, TO_DATE('01/01/2022', 'DD/MM/YYYY'), TO_DATE('31/12/2023', 'DD/MM/YYYY'));
INSERT INTO contratos_equipo_jugador (cod_equipo, cod_jugador, salario, clausula, fecha_inicio, fecha_fin) VALUES (0, 1, 10500000, 10000000, TO_DATE('01/01/2022', 'DD/MM/YYYY'), TO_DATE('31/12/2023', 'DD/MM/YYYY'));
INSERT INTO contratos_equipo_jugador (cod_equipo, cod_jugador, salario, clausula, fecha_inicio, fecha_fin) VALUES (0, 2, 10500000, 10000000, TO_DATE('01/01/2022', 'DD/MM/YYYY'), TO_DATE('31/12/2023', 'DD/MM/YYYY'));
INSERT INTO contratos_equipo_jugador (cod_equipo, cod_jugador, salario, clausula, fecha_inicio, fecha_fin) VALUES (0, 3, 10500000, 10000000, TO_DATE('01/01/2022', 'DD/MM/YYYY'), TO_DATE('31/12/2023', 'DD/MM/YYYY'));
INSERT INTO contratos_equipo_jugador (cod_equipo, cod_jugador, salario, clausula, fecha_inicio, fecha_fin) VALUES (0, 4, 10500000, 10000000, TO_DATE('01/01/2022', 'DD/MM/YYYY'), TO_DATE('31/12/2023', 'DD/MM/YYYY'));
INSERT INTO contratos_equipo_jugador (cod_equipo, cod_jugador, salario, clausula, fecha_inicio, fecha_fin) VALUES (0, 5, 10500000, 10000000, TO_DATE('01/01/2022', 'DD/MM/YYYY'), TO_DATE('31/12/2023', 'DD/MM/YYYY'));
INSERT INTO contratos_equipo_jugador (cod_equipo, cod_jugador, salario, clausula, fecha_inicio, fecha_fin) VALUES (0, 6, 10500000, 10000000, TO_DATE('01/01/2022', 'DD/MM/YYYY'), TO_DATE('31/12/2023', 'DD/MM/YYYY'));
INSERT INTO contratos_equipo_jugador (cod_equipo, cod_jugador, salario, clausula, fecha_inicio, fecha_fin) VALUES (0, 7, 10500000, 10000000, TO_DATE('01/01/2022', 'DD/MM/YYYY'), TO_DATE('31/12/2023', 'DD/MM/YYYY'));
INSERT INTO contratos_equipo_jugador (cod_equipo, cod_jugador, salario, clausula, fecha_inicio, fecha_fin) VALUES (0, 8, 10500000, 10000000, TO_DATE('01/01/2022', 'DD/MM/YYYY'), TO_DATE('31/12/2023', 'DD/MM/YYYY'));

/*
Error starting at line : 52 in command -
INSERT INTO contratos_equipo_jugador (cod_equipo, cod_jugador, salario, clausula, fecha_inicio, fecha_fin) VALUES (0, 8, 10500000, 10000000, TO_DATE('01/01/2022', 'DD/MM/YYYY'), TO_DATE('31/12/2023', 'DD/MM/YYYY'))
Error at Command Line : 52 Column : 13
Error report -
SQL Error: ORA-20001: El equipo ya tiene 8 jugadores pertenecientes al draft.
ORA-06512: at "HR.MAX_JUGADORES_DRAFT", line 19
ORA-04088: error during execution of trigger 'HR.MAX_JUGADORES_DRAFT'
*/