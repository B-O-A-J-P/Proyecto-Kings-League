drop table cuentas cascade constraints;
drop table permisos cascade constraints;


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

CREATE TABLE CUENTAS (
    cod_cuenta number(6, 0) GENERATED ALWAYS AS IDENTITY INCREMENT BY 1 START WITH 0 MINVALUE 0 NOCYCLE NOT NULL ENABLE,
    cod_perfil NUMBER(5, 0),
    usuario varchar2(50),
    contrasena varchar2(50),
    email varchar2(60),
    CONSTRAINT cue_cue_pk PRIMARY KEY(cod_cuenta),
    CONSTRAINT cod_perfil FOREIGN KEY(cod_perfil) REFERENCES permisos
);