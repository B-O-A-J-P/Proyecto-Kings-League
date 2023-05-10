INSERT INTO temporadas (ano, fecha_inicio_inscripcion, fecha_fin_inscripcion) VALUES (2022, TO_DATE('01/01/2022', 'DD/MM/YYYY'), TO_DATE('30/06/2022', 'DD/MM/YYYY'));

INSERT INTO splits (cod_temporada, nombre, fecha_inicio, fecha_fin) VALUES (0, 'Split 1', TO_DATE('01/07/2021', 'DD/MM/YYYY'), TO_DATE('31/07/2021', 'DD/MM/YYYY'));
INSERT INTO splits (cod_temporada, nombre, fecha_inicio, fecha_fin) VALUES (0, 'Split 2', TO_DATE('01/07/2021', 'DD/MM/YYYY'), TO_DATE('31/07/2021', 'DD/MM/YYYY'));

INSERT INTO jornadas (cod_split, numero, fecha, ubicacion) VALUES (0, 1, TO_DATE('01/07/2021', 'DD/MM/YYYY'), 'Estadio A');

INSERT INTO equipos (nombre, logo, presupuesto) VALUES ('Equipo A', EMPTY_BLOB(), 1000000);
INSERT INTO equipos (nombre, logo, presupuesto) VALUES ('Equipo B', EMPTY_BLOB(), 1000000);
INSERT INTO equipos (nombre, logo, presupuesto) VALUES ('Equipo C', EMPTY_BLOB(), 750000);
INSERT INTO equipos (nombre, logo, presupuesto) VALUES ('Equipo D', EMPTY_BLOB(), 1250000);
INSERT INTO equipos (nombre, logo, presupuesto) VALUES ('Equipo E', EMPTY_BLOB(), 800000);
INSERT INTO equipos (nombre, logo, presupuesto) VALUES ('Equipo F', EMPTY_BLOB(), 950000);
INSERT INTO equipos (nombre, logo, presupuesto) VALUES ('Equipo G', EMPTY_BLOB(), 1100000);
INSERT INTO equipos (nombre, logo, presupuesto) VALUES ('Equipo H', EMPTY_BLOB(), 700000);
INSERT INTO equipos (nombre, logo, presupuesto) VALUES ('Equipo I', EMPTY_BLOB(), 900000);
INSERT INTO equipos (nombre, logo, presupuesto) VALUES ('Equipo J', EMPTY_BLOB(), 1200000);
INSERT INTO equipos (nombre, logo, presupuesto) VALUES ('Equipo K', EMPTY_BLOB(), 850000);
INSERT INTO equipos (nombre, logo, presupuesto) VALUES ('Equipo L', EMPTY_BLOB(), 1050000);

INSERT INTO partidos (cod_jornada, cod_equipo1, cod_equipo2, hora, fase) VALUES (0, 0, 1, TO_TIMESTAMP('01/07/2021 10:00:00', 'DD/MM/YYYY HH24:MI:SS'), 'r');

INSERT INTO clasificaciones (cod_split, cod_equipo, posicion) VALUES (0, 0, 1);

INSERT INTO clasificaciones (cod_split, cod_equipo, posicion) VALUES (0, 1, 1);

INSERT INTO informacion_partidos (cod_partido, cod_equipo, numero_goles, resultado) VALUES (1, 0, 2, 'v');

INSERT INTO informacion_partidos (cod_partido, cod_equipo, numero_goles, resultado) VALUES (1, 1, 0, 'd');

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
INSERT INTO jugadores (dni, nombre, apellido, pie, altura, cod_agenda) VALUES ('89012341P', 'Jugador', 'Diez', 'Derecho', 200, 7);
INSERT INTO jugadores (dni, nombre, apellido, pie, altura, cod_agenda) VALUES ('89012351P', 'Jugador', 'Once', 'Derecho', 200, 7);
INSERT INTO jugadores (dni, nombre, apellido, pie, altura, cod_agenda) VALUES ('99012351P', 'Jugador', 'Doce', 'Derecho', 200, 7);



INSERT INTO registros_jugadores (cod_temporada, cod_jugador) VALUES (0, 0);
INSERT INTO registros_jugadores (cod_temporada, cod_jugador) VALUES (0, 1);
INSERT INTO registros_jugadores (cod_temporada, cod_jugador) VALUES (0, 2);
INSERT INTO registros_jugadores (cod_temporada, cod_jugador) VALUES (0, 3);
INSERT INTO registros_jugadores (cod_temporada, cod_jugador) VALUES (0, 4);
INSERT INTO registros_jugadores (cod_temporada, cod_jugador) VALUES (0, 5);
INSERT INTO registros_jugadores (cod_temporada, cod_jugador) VALUES (0, 6);
INSERT INTO registros_jugadores (cod_temporada, cod_jugador) VALUES (0, 7);
INSERT INTO registros_jugadores (cod_temporada, cod_jugador) VALUES (0, 8);
INSERT INTO registros_jugadores (cod_temporada, cod_jugador) VALUES (0, 9);
INSERT INTO registros_jugadores (cod_temporada, cod_jugador) VALUES (0, 10);
INSERT INTO registros_jugadores (cod_temporada, cod_jugador) VALUES (0, 11);





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
INSERT INTO contratos_equipo_jugador (cod_equipo, cod_jugador, salario, clausula, fecha_inicio, fecha_fin) VALUES (0, 9, 10500000, 10000000, TO_DATE('01/01/2022', 'DD/MM/YYYY'), TO_DATE('31/12/2023', 'DD/MM/YYYY'));
INSERT INTO contratos_equipo_jugador (cod_equipo, cod_jugador, salario, clausula, fecha_inicio, fecha_fin) VALUES (0, 10, 10500000, 10000000, TO_DATE('01/01/2022', 'DD/MM/YYYY'), TO_DATE('31/12/2023', 'DD/MM/YYYY'));
INSERT INTO contratos_equipo_jugador (cod_equipo, cod_jugador, salario, clausula, fecha_inicio, fecha_fin) VALUES (0, 11, 10500000, 10000000, TO_DATE('01/01/2022', 'DD/MM/YYYY'), TO_DATE('31/12/2023', 'DD/MM/YYYY'));



INSERT INTO miembros (dni, nombre, apellido, cod_agenda)
VALUES ('12345678A', 'Juan', 'Pérez', 1);

INSERT INTO miembros (dni, nombre, apellido, cod_agenda)
VALUES ('23456789B', 'María', 'González', 0);

INSERT INTO contratos_equipo_miembro (cod_equipo, cod_miembro, funcion, fecha_entrada)
VALUES (0, 0, 'p', TO_DATE('01/01/2022', 'DD/MM/YYYY'));

INSERT INTO contratos_equipo_miembro (cod_equipo, cod_miembro, funcion, fecha_entrada, fecha_salida)
VALUES (1, 1, 'p', TO_DATE('01/01/2022', 'DD/MM/YYYY'), TO_DATE('31/12/2022', 'DD/MM/YYYY'));


INSERT INTO registros_equipos (cod_temporada, cod_equipo) VALUES (0, 0);
INSERT INTO registros_equipos (cod_temporada, cod_equipo) VALUES (0, 1);
INSERT INTO registros_equipos (cod_temporada, cod_equipo) VALUES (0, 2);
INSERT INTO registros_equipos (cod_temporada, cod_equipo) VALUES (0, 3);
INSERT INTO registros_equipos (cod_temporada, cod_equipo) VALUES (0, 4);
INSERT INTO registros_equipos (cod_temporada, cod_equipo) VALUES (0, 5);
INSERT INTO registros_equipos (cod_temporada, cod_equipo) VALUES (0, 6);
INSERT INTO registros_equipos (cod_temporada, cod_equipo) VALUES (0, 7);
INSERT INTO registros_equipos (cod_temporada, cod_equipo) VALUES (0, 8);
INSERT INTO registros_equipos (cod_temporada, cod_equipo) VALUES (0, 9);
INSERT INTO registros_equipos (cod_temporada, cod_equipo) VALUES (0, 10);
INSERT INTO registros_equipos (cod_temporada, cod_equipo) VALUES (0, 11);


