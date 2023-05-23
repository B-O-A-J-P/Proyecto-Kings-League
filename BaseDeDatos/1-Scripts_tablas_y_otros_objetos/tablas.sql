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
drop table cuentas;
drop table permisos cascade constraints;

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
    CONSTRAINT spl_cod_tem_fk FOREIGN KEY (cod_temporada) REFERENCES temporadas ON DELETE CASCADE,
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
    CONSTRAINT jor_cod_spl_fk FOREIGN KEY (cod_split) REFERENCES splits ON DELETE CASCADE
);

CREATE TABLE equipos(
    cod_equipo NUMBER(6, 0) GENERATED ALWAYS AS IDENTITY INCREMENT BY 1 START WITH 0 MINVALUE 0 NOCYCLE NOT NULL ENABLE,
    nombre VARCHAR2(50) NOT NULL,
    logo BLOB, 
    presupuesto NUMBER(12, 2) default 200000000,
    CONSTRAINT equ_cod_pk PRIMARY KEY (cod_equipo)
);

CREATE TABLE partidos(
  cod_jornada NUMBER(7, 0) NOT NULL,
  cod_partido NUMBER(8, 0) GENERATED ALWAYS AS IDENTITY INCREMENT BY 1 START WITH 0 MINVALUE 0 NOCYCLE NOT NULL ENABLE,
  cod_equipo1 NUMBER(6, 0) NOT NULL,
  cod_equipo2 NUMBER(6, 0) NOT NULL,
  hora timestamp NOT NULL,
  fase VARCHAR2(1) DEFAULT 'r' NOT NULL,
  CONSTRAINT par_cod_equ1_fk FOREIGN KEY (cod_equipo1) REFERENCES equipos ON DELETE CASCADE,
  CONSTRAINT par_cod_equ2_fk FOREIGN KEY (cod_equipo2) REFERENCES equipos ON DELETE CASCADE,
  CONSTRAINT par_cod_jor_pk PRIMARY KEY (cod_partido),
  CONSTRAINT par_cod_par_fk FOREIGN KEY (cod_jornada) REFERENCES jornadas ON DELETE CASCADE,
  CONSTRAINT par_fas_ck CHECK (fase in ('r', 's', 'f'))
  -- r = regular | s = semifinal | f = final
);


CREATE TABLE clasificaciones (
    cod_split NUMBER(6, 0),
    cod_equipo NUMBER(6, 0),
    posicion NUMBER(4) NOT NULL,
    CONSTRAINT cla_spl_fk FOREIGN KEY (cod_split) REFERENCES splits ON DELETE CASCADE,
    CONSTRAINT cla_equ_fk FOREIGN KEY (cod_equipo) REFERENCES equipos ON DELETE CASCADE,
    CONSTRAINT cla_cod_pk PRIMARY KEY (cod_split, cod_equipo)
);

CREATE TABLE informacion_partidos (
    cod_partido NUMBER(8, 0),
    cod_equipo NUMBER(6, 0),
    numero_goles NUMBER(2) NOT NULL,
    resultado VARCHAR2(1) NOT NULL,
    CONSTRAINT inf_cod_par_fk FOREIGN KEY (cod_partido) REFERENCES partidos ON DELETE CASCADE,
    CONSTRAINT inf_cod_equ_fk FOREIGN KEY (cod_equipo) REFERENCES equipos ON DELETE CASCADE,
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
    cod_agenda NUMBER(8),
    CONSTRAINT jug_cod_jug_pk PRIMARY KEY (cod_jugador),
    CONSTRAINT jug_co_age_fk FOREIGN KEY (cod_agenda) REFERENCES agendas ON DELETE CASCADE
);

CREATE TABLE registros_jugadores (
    cod_temporada NUMBER(5, 0),
    cod_jugador NUMBER(6, 0),
    CONSTRAINT reg_cod_tem_fk FOREIGN KEY (cod_temporada) REFERENCES temporadas ON DELETE CASCADE,
    CONSTRAINT reg_cod_jug_fk FOREIGN KEY (cod_jugador) REFERENCES jugadores ON DELETE CASCADE,
    CONSTRAINT reg_tem_jug_pk PRIMARY KEY (cod_temporada, cod_jugador)
);

CREATE TABLE draft (
    cod_temporada NUMBER(5, 0),
    cod_jugador NUMBER(6, 0),
    posicion NUMBER(3) NOT NULL,
    CONSTRAINT dra_cod_tem_fk FOREIGN KEY (cod_temporada, cod_jugador) REFERENCES registros_jugadores(cod_temporada, cod_jugador) ON DELETE CASCADE,

    CONSTRAINT dra_cod_pk PRIMARY KEY (cod_temporada, cod_jugador)
);

CREATE TABLE contratos_equipo_jugador (
    cod_equipo NUMBER(6, 0) NOT NULL,
    cod_jugador NUMBER(4, 0) NOT NULL,
    cod_contrato NUMBER(7, 0) GENERATED ALWAYS AS IDENTITY INCREMENT BY 1 START WITH 0 MINVALUE 0 NOCYCLE NOT NULL ENABLE,
    salario NUMBER(9) NOT NULL,
    clausula NUMBER(9) NOT NULL,
    fecha_inicio DATE NOT NULL,
    fecha_fin DATE,
    CONSTRAINT con_jug_equ_fk FOREIGN KEY (cod_equipo) REFERENCES equipos ON DELETE CASCADE,
    CONSTRAINT con_jug_mie_fk FOREIGN KEY (cod_jugador) REFERENCES jugadores ON DELETE CASCADE,
    CONSTRAINT con_jug_cod_pk PRIMARY KEY (cod_contrato),
    CONSTRAINT con_sal_ck CHECK (salario in (10000000, 10500000, 15000000, 22500000)),
    CONSTRAINT con_cla_ck CHECK (clausula >= 1000000)
);

CREATE TABLE miembros (
    cod_miembro NUMBER(4, 0) GENERATED ALWAYS AS IDENTITY INCREMENT BY 1 START WITH 0 MINVALUE 0 NOCYCLE NOT NULL ENABLE,
    dni VARCHAR(9) NOT NULL UNIQUE,
    nombre VARCHAR2(50) NOT NULL,
    apellido VARCHAR2(50) NOT NULL,
    cod_agenda NUMBER(8, 0) NOT NULL,
    CONSTRAINT mie_cod_pk PRIMARY KEY (cod_miembro),
    CONSTRAINT mie_age_fk FOREIGN KEY (cod_agenda) REFERENCES agendas ON DELETE CASCADE
);

CREATE TABLE contratos_equipo_miembro (
    cod_equipo NUMBER(6, 0) NOT NULL,
    cod_miembro NUMBER(4, 0) NOT NULL,
    cod_contrato NUMBER (6, 0) GENERATED ALWAYS AS IDENTITY INCREMENT BY 1 START WITH 0 MINVALUE 0 NOCYCLE NOT NULL ENABLE,
    funcion VARCHAR2(1) NOT NULL,
    fecha_entrada DATE NOT NULL, 
    fecha_salida DATE,
    CONSTRAINT con_mie_equ_fk FOREIGN KEY (cod_equipo) REFERENCES equipos ON DELETE CASCADE,
    CONSTRAINT con_mie_mie_fk FOREIGN KEY (cod_miembro) REFERENCES miembros ON DELETE CASCADE,
    CONSTRAINT con_mie_cod_pk PRIMARY KEY (cod_contrato),
    CONSTRAINT con_fun_ck CHECK (funcion in ('p', 'e', 's')) 
    -- p = propietario | e = entrenador | s = staff
);

CREATE TABLE registros_equipos (
    cod_temporada NUMBER(5, 0),
    cod_equipo NUMBER(6, 0),
    CONSTRAINT equ_par_tem_fk FOREIGN KEY (cod_temporada) REFERENCES temporadas ON DELETE CASCADE,
    CONSTRAINT equ_par_equ_fk FOREIGN KEY (cod_equipo) REFERENCES equipos ON DELETE CASCADE,
    CONSTRAINT equ_tem_equ_pk PRIMARY KEY (cod_temporada, cod_equipo)
);

--------------------------------------------------------------------------------
--Usuarios
CREATE TABLE permisos(
    cod_perfil NUMBER(5, 0) GENERATED ALWAYS AS IDENTITY INCREMENT BY 1 START WITH 0 MINVALUE 0 NOCYCLE NOT NULL ENABLE,
    insertar varchar2(1),
    eliminar varchar2(1),
    actualizar varchar2(1),
    ver_vista varchar2(1),
    ver_global varchar2(1),
    lanzar_procedimientos varchar2(1),
    CONSTRAINTS per_ins_ck CHECK(insertar in ('t', 'f')),
    CONSTRAINTS per_act_ck CHECK(actualizar in ('t', 'f')),
    CONSTRAINTS per_vis_ck CHECK(ver_vista in ('t', 'f')),
    CONSTRAINTS per_glo_ck CHECK(ver_global in ('t', 'f')),
    CONSTRAINTS per_pro_ck CHECK(lanzar_procedimientos in ('t', 'f')),
    CONSTRAINT per_cod_pk PRIMARY KEY (cod_perfil)
);

CREATE TABLE Cuentas (
    cod_cuenta number(6, 0) GENERATED ALWAYS AS IDENTITY INCREMENT BY 1 START WITH 0 MINVALUE 0 NOCYCLE NOT NULL ENABLE,
    cod_perfil NUMBER(5, 0) NOT NULL,
    usuario varchar2(50) NOT NULL,
    contrasena varchar2(50) NOT NULL,
    email varchar2(60) NOT NULL,
    CONSTRAINT cue_cue_pk PRIMARY KEY(cod_cuenta),
    CONSTRAINT cod_perfil FOREIGN KEY(cod_perfil) REFERENCES permisos ON DELETE CASCADE,
    CONSTRAINT cue_usu_uq UNIQUE (usuario),
    CONSTRAINT cue_con_uq UNIQUE (contrasena),
    CONSTRAINT cue_ema_uq UNIQUE (email)
);
