--Comproobar el trigger MAX_JUGADORES_DRAFT

--Primero insertamos a los jugadores en el draft

INSERT INTO DRAFT(COD_TEMPORADA,COD_JUGADOR,POSICION) VALUES (10,20,1);
INSERT INTO DRAFT(COD_TEMPORADA,COD_JUGADOR,POSICION) VALUES (10,21,1);
INSERT INTO DRAFT(COD_TEMPORADA,COD_JUGADOR,POSICION) VALUES (10,22,1);
INSERT INTO DRAFT(COD_TEMPORADA,COD_JUGADOR,POSICION) VALUES (10,23,1);

INSERT INTO DRAFT(COD_TEMPORADA,COD_JUGADOR,POSICION) VALUES (10,24,1);
INSERT INTO DRAFT(COD_TEMPORADA,COD_JUGADOR,POSICION) VALUES (10,25,1);
INSERT INTO DRAFT(COD_TEMPORADA,COD_JUGADOR,POSICION) VALUES (10,26,1);
INSERT INTO DRAFT(COD_TEMPORADA,COD_JUGADOR,POSICION) VALUES (10,27,1);
INSERT INTO DRAFT(COD_TEMPORADA,COD_JUGADOR,POSICION) VALUES (10,28,1);

--Hay nueve jugadores (1 de ellos sobra pero lo usaremos mas tarde para comprobar si funciona el trigger)


INSERT INTO CONTRATOS_EQUIPO_JUGADOR(COD_JUGADOR,COD_EQUIPO,SALARIO,CLAUSULA,FECHA_INICIO,FECHA_FIN)
VALUES('20','0','10000000','1000000',NULL,NULL);

INSERT INTO CONTRATOS_EQUIPO_JUGADOR(COD_JUGADOR,COD_EQUIPO,SALARIO,CLAUSULA,FECHA_INICIO,FECHA_FIN)
VALUES('21','0','10000000','1000000',NULL,NULL);

INSERT INTO CONTRATOS_EQUIPO_JUGADOR(COD_JUGADOR,COD_EQUIPO,SALARIO,CLAUSULA,FECHA_INICIO,FECHA_FIN)
VALUES('22','0','10000000','1000000',NULL,NULL);

INSERT INTO CONTRATOS_EQUIPO_JUGADOR(COD_JUGADOR,COD_EQUIPO,SALARIO,CLAUSULA,FECHA_INICIO,FECHA_FIN)
VALUES('23','0','10000000','1000000',NULL,NULL);

INSERT INTO CONTRATOS_EQUIPO_JUGADOR(COD_JUGADOR,COD_EQUIPO,SALARIO,CLAUSULA,FECHA_INICIO,FECHA_FIN)
VALUES('24','0','10000000','1000000',NULL,NULL);

INSERT INTO CONTRATOS_EQUIPO_JUGADOR(COD_JUGADOR,COD_EQUIPO,SALARIO,CLAUSULA,FECHA_INICIO,FECHA_FIN)
VALUES('25','0','10000000','1000000',NULL,NULL);

INSERT INTO CONTRATOS_EQUIPO_JUGADOR(COD_JUGADOR,COD_EQUIPO,SALARIO,CLAUSULA,FECHA_INICIO,FECHA_FIN)
VALUES('26','0','10000000','1000000',NULL,NULL);

INSERT INTO CONTRATOS_EQUIPO_JUGADOR(COD_JUGADOR,COD_EQUIPO,SALARIO,CLAUSULA,FECHA_INICIO,FECHA_FIN)
VALUES('27','0','10000000','1000000',NULL,NULL);


--Ahora introducimos los 9 jugadores en la tabla contratos_equipos_jugador

INSERT INTO CONTRATOS_EQUIPO_JUGADOR(COD_JUGADOR,COD_EQUIPO,SALARIO,CLAUSULA,FECHA_INICIO,FECHA_FIN)
VALUES('20','0','10000000','1000000',NULL,NULL);

--Comprobar el maximo de jugadores fuera de draft
INSERT INTO CONTRATOS_EQUIPO_JUGADOR(COD_JUGADOR,COD_EQUIPO,SALARIO,CLAUSULA,FECHA_INICIO,FECHA_FIN)
VALUES('21','0','10000000','1000000',NULL,NULL);

INSERT INTO CONTRATOS_EQUIPO_JUGADOR(COD_JUGADOR,COD_EQUIPO,SALARIO,CLAUSULA,FECHA_INICIO,FECHA_FIN)
VALUES('22','0','10000000','1000000',NULL,NULL);

INSERT INTO CONTRATOS_EQUIPO_JUGADOR(COD_JUGADOR,COD_EQUIPO,SALARIO,CLAUSULA,FECHA_INICIO,FECHA_FIN)
VALUES('23','0','10000000','1000000',NULL,NULL);

INSERT INTO CONTRATOS_EQUIPO_JUGADOR(COD_JUGADOR,COD_EQUIPO,SALARIO,CLAUSULA,FECHA_INICIO,FECHA_FIN)
VALUES('24','0','10000000','1000000',NULL,NULL);

INSERT INTO CONTRATOS_EQUIPO_JUGADOR(COD_JUGADOR,COD_EQUIPO,SALARIO,CLAUSULA,FECHA_INICIO,FECHA_FIN)
VALUES('25','0','10000000','1000000',NULL,NULL);

INSERT INTO CONTRATOS_EQUIPO_JUGADOR(COD_JUGADOR,COD_EQUIPO,SALARIO,CLAUSULA,FECHA_INICIO,FECHA_FIN)
VALUES('26','0','10000000','1000000',NULL,NULL);

INSERT INTO CONTRATOS_EQUIPO_JUGADOR(COD_JUGADOR,COD_EQUIPO,SALARIO,CLAUSULA,FECHA_INICIO,FECHA_FIN)
VALUES('27','0','10000000','1000000',NULL,NULL);

INSERT INTO CONTRATOS_EQUIPO_JUGADOR(COD_JUGADOR,COD_EQUIPO,SALARIO,CLAUSULA,FECHA_INICIO,FECHA_FIN)
VALUES('28','0','10000000','1000000',NULL,NULL);

--Vemos que a pesar de estar en el draft al ser mas de 8 el resultado de la insert sobrante es:

/*Error que empieza en la línea: 46 del comando :
INSERT INTO CONTRATOS_EQUIPO_JUGADOR(COD_JUGADOR,COD_EQUIPO,SALARIO,CLAUSULA,FECHA_INICIO,FECHA_FIN)
VALUES('28','0','10000000','1000000',NULL,NULL)
Informe de error -
ORA-20001: El equipo ya tiene 8 jugadores
ORA-06512: en "EQDAW05.MAX_JUGADORES_DRAFT", línea 17
ORA-04088: error durante la ejecución del disparador 'EQDAW05.MAX_JUGADORES_DRAFT'
*/


--Comprobar el trigger MAX_JUGADORES_WILD_CARD
INSERT INTO CONTRATOS_EQUIPO_JUGADOR(COD_JUGADOR,COD_EQUIPO,SALARIO,CLAUSULA,FECHA_INICIO,FECHA_FIN)
VALUES('32','0','10000000','1000000',NULL,NULL);

INSERT INTO CONTRATOS_EQUIPO_JUGADOR(COD_JUGADOR,COD_EQUIPO,SALARIO,CLAUSULA,FECHA_INICIO,FECHA_FIN)
VALUES('33','0','10000000','1000000',NULL,NULL);


INSERT INTO CONTRATOS_EQUIPO_JUGADOR(COD_JUGADOR,COD_EQUIPO,SALARIO,CLAUSULA,FECHA_INICIO,FECHA_FIN)
VALUES('34','0','10000000','1000000',NULL,NULL);

--Insertamos 3 jugadores, los dos primeros son insertados como wild card sin embargo con el tercero el resultado es

/*
Error que empieza en la línea: 57 del comando :
INSERT INTO CONTRATOS_EQUIPO_JUGADOR(COD_JUGADOR,COD_EQUIPO,SALARIO,CLAUSULA,FECHA_INICIO,FECHA_FIN)
VALUES('34','0','10000000','1000000',NULL,NULL)
Informe de error -
ORA-20001: El equipo ya tiene 2 jugadores fuera del draft
ORA-06512: en "EQDAW05.MAX_JUGADORES_WILD_CARD", línea 13
ORA-04088: error durante la ejecución del disparador 'EQDAW05.MAX_JUGADORES_WILD_CARD'
*/

--Comprobar el trigger MIN_EQUIPOS

TRUNCATE TABLE EQUIPOS_PARTICIPANTES;

--Una vez tenemos la tabla vacia insertamos un split

insert into splits (cod_temporada, nombre,fecha_inicio,fecha_fin) values (0,'invierno',null,null);

--Resultado

/*
Error que empieza en la línea: 83 del comando :
insert into splits (cod_temporada, nombre,fecha_inicio,fecha_fin) values (0,'invierno',null,null)
Informe de error -
ORA-20001: Tiene que haber un mínimo de 12 equipos para generar el calendario
ORA-06512: en "EQDAW05.MIN_EQUIPOS", línea 9
ORA-04088: error durante la ejecución del disparador 'EQDAW05.MIN_EQUIPOS'

*/

--Comprobar el trigger MIN_JUGADORES

TRUNCATE TABLE CONTRATOS_EQUIPO_JUGADOR;

--Una vez vaciamos la tabla para que no haya jugadores comprobamos si podriamos hacer participar a un equipo sin jugadores


INSERT INTO EQUIPOS_PARTICIPANTES (COD_TEMPORADA,COD_EQUIPO) VALUES (0,0);

--Resultado

/*Error que empieza en la línea: 92 del comando :
INSERT INTO EQUIPOS_PARTICIPANTES (COD_TEMPORADA,COD_EQUIPO) VALUES (0,0)
Informe de error -
ORA-20001: El equipo tiene que tener un mínimo de 10 jugadores
ORA-06512: en "EQDAW05.MIN_JUGADORES", línea 13
ORA-04088: error durante la ejecución del disparador 'EQDAW05.MIN_JUGADORES'
*/


--Comprobar el trigger MIN_CLAUSULA_INICIAL

--Insertamos un contrato con una clausula inferior a 1.000.000

INSERT INTO CONTRATOS_EQUIPO_JUGADOR(COD_JUGADOR,COD_EQUIPO,SALARIO,CLAUSULA,FECHA_INICIO,FECHA_FIN)
VALUES('34','0','10000000','500',NULL,NULL);

--Resultado

/*
Error que empieza en la línea: 98 del comando :
INSERT INTO CONTRATOS_EQUIPO_JUGADOR(COD_JUGADOR,COD_EQUIPO,SALARIO,CLAUSULA,FECHA_INICIO,FECHA_FIN)
VALUES('34','0','10000000','500',NULL,NULL)
Informe de error -
ORA-20004: La clausula minima inicial es de 1.000.000
ORA-06512: en "EQDAW05.MIN_CLAUSULA_INICIAL", línea 3
ORA-04088: error durante la ejecución del disparador 'EQDAW05.MIN_CLAUSULA_INICIAL'

*/



--Prueba del trigger max_presupuesto_equipo
--Insertamos un contrato donde la clausula es tan grande que con la suma de los
--jugadores supere los 200M
insert into contratos_equipo_jugador
values(0,4,default,10000000,99999999,sysdate,sysdate+2);
/*RESULTADO OBTENIDO
Error que empieza en la línea: 28 del comando :
insert into contratos_equipo_jugador
values(0,4,default,10000000,99999999,sysdate,sysdate+2)
Informe de error -
ORA-20001: no puede sobrepasarse el presupuesto establecido
ORA-06512: en "HR.MAX_PRESUPUESTO_EQUIPO", línea 17
ORA-04088: error durante la ejecución del disparador 'HR.MAX_PRESUPUESTO_EQUIPO'
*/


--Prueba trigger check_contrato_jugador_split
--para comprobar este trigger, insertamos un contrato donde el jugador ya tenga uno
insert into contratos_equipo_jugador values (0,0,default,10000000,99000000,'04/05/23','06/05/23');
/*Resultado obtenido
Error que empieza en la línea: 19 del comando :
insert into contratos_equipo_jugador values (0,0,default,10000000,99000000,'04/05/23','06/05/23')
Informe de error -
ORA-20001: Este jugador ya tiene un contrato activo
ORA-06512: en "HR.CHECK_CONTRATO_JUGADOR_SPLIT", línea 12
ORA-04088: error durante la ejecución del disparador 'HR.CHECK_CONTRATO_JUGADOR_SPLIT'
*/




--PRUEBA TRIGGER CONTROL DE MIEMBROS:

--PARA PROBARLO INSERTAMOS DOS MIEMBROS CON LA MISMA FUNCION:
insert into contratos_equipo_miembro (cod_equipo,funcion, fecha_entrada, fecha_salida)
values (11,'e',null, null);

insert into contratos_equipo_miembro (cod_equipo,funcion, fecha_entrada, fecha_salida)
values (11,'e',null, null);


/*REUSLTADO OBTENIDO:

Error que empieza en la línea: 21 del comando :
insert into contratos_equipo_miembro (cod_equipo,funcion, fecha_entrada, fecha_salida)
values (11,'e',null, null)
Informe de error -
ORA-20001: No puede haber más de un miembro con la misma función
ORA-06512: en "EQDAW05.CONTROL_MIEMBROS", línea 12
ORA-04088: error durante la ejecución del disparador 'EQDAW05.CONTROL_MIEMBROS'

*/

--Comprobar el trigger equipo_duplicado_tr

INSERT INTO EQUIPOS(NOMBRE, PRESUPUESTO) VALUES('PORCINOS',200000000);

/*Resultado

Error que empieza en la línea: 6 del comando :
INSERT INTO EQUIPOS(NOMBRE, PRESUPUESTO) VALUES('PORCINOS',200000000)
Informe de error -
ORA-20001: ERROR, EQUIPO YA EXISTENTE
ORA-06512: en "EQDAW05.EQUIPO_DUPLICADO_TR", línea 8
ORA-04088: error durante la ejecución del disparador 'EQDAW05.EQUIPO_DUPLICADO_TR'

*/


