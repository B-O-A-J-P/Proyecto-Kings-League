DROP TABLE registros_equipos;
DROP TABLE registros_jugadores CASCADE CONSTRAINTS;
DROP TABLE contratos_equipo_miembro;
DROP TABLE miembros CASCADE CONSTRAINTS;
DROP TABLE contratos_equipo_jugador;
DROP TABLE draft;
DROP TABLE agendas CASCADE CONSTRAINTS;
DROP TABLE jugadores CASCADE CONSTRAINTS;
DROP TABLE informacion_partidos CASCADE CONSTRAINTS;
DROP TABLE clasificaciones;
DROP TABLE equipos CASCADE CONSTRAINTS;
DROP TABLE partidos CASCADE CONSTRAINTS;
DROP TABLE jornadas CASCADE CONSTRAINTS;
DROP TABLE splits CASCADE CONSTRAINTS;
DROP TABLE temporadas CASCADE CONSTRAINTS;

CREATE TABLE temporadas(
    cod_temporada NUMBER(5, 0) GENERATED ALWAYS AS IDENTITY INCREMENT BY 1 START WITH 0 MINVALUE 0 NOCYCLE NOT NULL ENABLE,
    ano NUMBER(4) NOT NULL,
    fecha_inicio_inscripcion DATE NOT NULL,
    fecha_fin_inscripcion DATE NOT NULL,
    CONSTRAINT tem_cod_pk PRIMARY KEY (cod_temporada),
    CONSTRAINT tem_fec_fin CHECK (fecha_fin_inscripcion > fecha_inicio_inscripcion)
);

CREATE TABLE splits(
    cod_temporada NUMBER(5, 0) NOT NULL,    
    cod_split NUMBER(6, 0) GENERATED ALWAYS AS IDENTITY INCREMENT BY 1 START WITH 0 MINVALUE 0 NOCYCLE NOT NULL ENABLE,
    nombre VARCHAR2(20),
    fecha_inicio DATE NOT NULL,
    fecha_fin DATE NOT NULL,
    CONSTRAINT spl_cod_tem_fk FOREIGN KEY (cod_temporada) REFERENCES temporadas,
    CONSTRAINT spl_cod_spl_pk PRIMARY KEY (cod_split),
    CONSTRAINT spl_fec_fin_ck CHECK (fecha_fin > fecha_inicio)
);

CREATE TABLE jornadas(
    cod_split NUMBER(6, 0) NOT NULL,
    cod_jornada NUMBER(7, 0) GENERATED ALWAYS AS IDENTITY INCREMENT BY 1 START WITH 0 MINVALUE 0 NOCYCLE NOT NULL ENABLE,
    numero NUMBER(2) NOT NULL,
    fecha DATE NOT NULL,
    ubicacion VARCHAR2(50) NOT NULL,
    CONSTRAINT jor_cod_jor_pk PRIMARY KEY (cod_jornada),
    CONSTRAINT jor_cod_spl_fk FOREIGN KEY (cod_split) REFERENCES splits
);

CREATE TABLE equipos(
    cod_equipo NUMBER(6, 0) GENERATED ALWAYS AS IDENTITY INCREMENT BY 1 START WITH 0 MINVALUE 0 NOCYCLE NOT NULL ENABLE,
    nombre VARCHAR2(50) NOT NULL,
    logo BLOB NOT NULL, 
    presupuesto NUMBER(11, 2) NOT NULL,
    CONSTRAINT equ_cod_pk PRIMARY KEY (cod_equipo)
);

CREATE TABLE partidos(
  cod_jornada NUMBER(7, 0) NOT NULL,
  cod_partido NUMBER(8, 0) GENERATED ALWAYS AS IDENTITY INCREMENT BY 1 START WITH 0 MINVALUE 0 NOCYCLE NOT NULL ENABLE,
  cod_equipo1 NUMBER(6, 0) NOT NULL,
  cod_equipo2 NUMBER(6, 0) NOT NULL,
  hora timestamp NOT NULL,
  fase VARCHAR2(1) DEFAULT 'r' NOT NULL,
  CONSTRAINT par_cod_equ1_fk FOREIGN KEY (cod_equipo1) REFERENCES equipos,
  CONSTRAINT par_cod_equ2_fk FOREIGN KEY (cod_equipo2) REFERENCES equipos,
  CONSTRAINT par_cod_jor_pk PRIMARY KEY (cod_partido),
  CONSTRAINT par_cod_par_fk FOREIGN KEY (cod_jornada) REFERENCES jornadas,
  CONSTRAINT par_fas_ck CHECK (fase in ('r', 's', 'f'))
  -- r = regular | s = semifinal | f = final
);


CREATE TABLE clasificaciones (
    cod_split NUMBER(6, 0),
    cod_equipo NUMBER(6, 0),
    posicion NUMBER(3) NOT NULL,
    CONSTRAINT cla_spl_fk FOREIGN KEY (cod_split) REFERENCES splits,
    CONSTRAINT cla_equ_fk FOREIGN KEY (cod_equipo) REFERENCES equipos,
    CONSTRAINT cla_cod_pk PRIMARY KEY (cod_split, cod_equipo)
);

CREATE TABLE informacion_partidos (
    cod_partido NUMBER(8, 0),
    cod_equipo NUMBER(6, 0),
    numero_goles NUMBER(2) NOT NULL,
    resultado VARCHAR2(1) NOT NULL,
    CONSTRAINT inf_cod_par_fk FOREIGN KEY (cod_partido) REFERENCES partidos,
    CONSTRAINT inf_cod_equ_fk FOREIGN KEY (cod_equipo) REFERENCES equipos,
    CONSTRAINT info_cod_pk PRIMARY KEY (cod_partido, cod_equipo),
    CONSTRAINT inf_res_ck CHECK (resultado in ('v', 'd'))
    -- v = victoria | d = derrota
);

CREATE TABLE agendas (
    cod_agenda NUMBER(8, 0) GENERATED ALWAYS AS IDENTITY INCREMENT BY 1 START WITH 0 MINVALUE 0 NOCYCLE NOT NULL ENABLE,
    email VARCHAR2(50),
    telefono VARCHAR2(9),
    CONSTRAINT age_cod_pk PRIMARY KEY (cod_agenda)
);

CREATE TABLE jugadores (
    cod_jugador NUMBER(6, 0) GENERATED ALWAYS AS IDENTITY INCREMENT BY 1 START WITH 0 MINVALUE 0 NOCYCLE NOT NULL ENABLE,
    dni VARCHAR(9) NOT NULL UNIQUE,
    nombre VARCHAR2(50) NOT NULL,
    apellido VARCHAR2(50) NOT NULL,
    pie VARCHAR(20),
    altura NUMBER(3), --en centímetros
    cod_agenda NUMBER(8) NOT NULL,
    CONSTRAINT jug_cod_jug_pk PRIMARY KEY (cod_jugador),
    CONSTRAINT jug_co_age_fk FOREIGN KEY (cod_agenda) REFERENCES agendas
);

CREATE TABLE registros_jugadores (
    cod_temporada NUMBER(5, 0),
    cod_jugador NUMBER(6, 0),
    CONSTRAINT reg_cod_tem_fk FOREIGN KEY (cod_temporada) REFERENCES temporadas,
    CONSTRAINT reg_cod_jug_fk FOREIGN KEY (cod_jugador) REFERENCES jugadores,
    CONSTRAINT reg_tem_jug_pk PRIMARY KEY (cod_temporada, cod_jugador)
);

CREATE TABLE draft (
    cod_temporada NUMBER(5, 0),
    cod_jugador NUMBER(6, 0),
    posicion NUMBER(3) NOT NULL,
    CONSTRAINT dra_cod_tem_fk FOREIGN KEY (cod_temporada, cod_jugador) REFERENCES registros_jugadores(cod_temporada, cod_jugador),

    CONSTRAINT dra_cod_pk PRIMARY KEY (cod_temporada, cod_jugador)
);

CREATE TABLE contratos_equipo_jugador (
    cod_equipo NUMBER(6, 0) NOT NULL,
    cod_jugador NUMBER(4, 0) NOT NULL,
    cod_contrato NUMBER(7, 0) GENERATED ALWAYS AS IDENTITY INCREMENT BY 1 START WITH 0 MINVALUE 0 NOCYCLE NOT NULL ENABLE,
    salario NUMBER(8) NOT NULL,
    clausula NUMBER(8) NOT NULL,
    fecha_inicio DATE NOT NULL,
    fecha_fin DATE,
    CONSTRAINT con_jug_equ_fk FOREIGN KEY (cod_equipo) REFERENCES equipos,
    CONSTRAINT con_jug_mie_fk FOREIGN KEY (cod_jugador) REFERENCES jugadores,
    CONSTRAINT con_jug_cod_pk PRIMARY KEY (cod_contrato),
    CONSTRAINT con_sal_ck CHECK (salario in (10000000, 10500000, 15000000, 22500000))
);

CREATE TABLE miembros (
    cod_miembro NUMBER(4, 0) GENERATED ALWAYS AS IDENTITY INCREMENT BY 1 START WITH 0 MINVALUE 0 NOCYCLE NOT NULL ENABLE,
    dni VARCHAR(9) NOT NULL UNIQUE,
    nombre VARCHAR2(50) NOT NULL,
    apellido VARCHAR2(50) NOT NULL,
    cod_agenda NUMBER(8, 0) NOT NULL,
    CONSTRAINT mie_cod_pk PRIMARY KEY (cod_miembro),
    CONSTRAINT mie_age_fk FOREIGN KEY (cod_agenda) REFERENCES agendas
);

CREATE TABLE contratos_equipo_miembro (
    cod_equipo NUMBER(6, 0) NOT NULL,
    cod_miembro NUMBER(4, 0) NOT NULL,
    cod_contrato NUMBER (6, 0) GENERATED ALWAYS AS IDENTITY INCREMENT BY 1 START WITH 0 MINVALUE 0 NOCYCLE NOT NULL ENABLE,
    funcion VARCHAR2(1) NOT NULL,
    fecha_entrada DATE NOT NULL, 
    fecha_salida DATE,
    CONSTRAINT con_mie_equ_fk FOREIGN KEY (cod_equipo) REFERENCES equipos,
    CONSTRAINT con_mie_mie_fk FOREIGN KEY (cod_miembro) REFERENCES miembros,
    CONSTRAINT con_mie_cod_pk PRIMARY KEY (cod_contrato),
    CONSTRAINT con_fun_ck CHECK (funcion in ('p', 'e', 's')) 
    -- p = propietario | e = entrenador | s = staff
);

CREATE TABLE registros_equipos (
    cod_temporada NUMBER(5, 0),
    cod_equipo NUMBER(6, 0),
    CONSTRAINT equ_par_tem_fk FOREIGN KEY (cod_temporada) REFERENCES temporadas,
    CONSTRAINT equ_par_equ_fk FOREIGN KEY (cod_equipo) REFERENCES equipos,
    CONSTRAINT equ_tem_equ_pk PRIMARY KEY (cod_temporada, cod_equipo)
);
