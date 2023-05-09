
drop trigger max_jugadores;
drop trigger min_jugadores;
drop trigger min_equipos;
drop trigger max_presupuesto_equipo;

select * from jornadas;

create or replace trigger min_equipos
before insert on splits
declare
    numeroEquipos number;
begin
    select count(*) into numeroEquipos from equipos_participantes
    where cod_temporada = (select max(cod_temporada) from temporadas);
    
    if numeroEquipos < 12
    then 
        raise_application_error(-20001, 'Tiene que haber un minimo de 12 equipos para generar el calendario');
    end if;
    
end;
insert into splits (cod_temporada, fecha_inicio, fecha_fin)
values (12, '2023-05-01', '2023-05-31');
--Prueba del trigger
--Insertamos un contrato donde la clausula es tan grande que con la suma de los
--jugadores supere los 200M

/***********************************************************************/

create or replace trigger min_jugadores
before insert on equipos_participantes
for each row
declare
    codEquipo equipos.cod_equipo%type := :new.cod_equipo;
    numeroJugadores number;
begin
    
    select count(*) into numeroJugadores
    from contratos_equipo_jugador
    where cod_equipo = codEquipo
    and (fecha_fin > sysdate or fecha_fin is null);
    
    if (numeroJugadores < 8)
    then
        raise_application_error(-20001, 'El equipo tiene que tener un minimo de 8 jugadores');
    end if;
    
end;

--Prueba del trigger
--Insertamos mas jugadores de los que el equipo permite
select count(*) from equipos;


/************************************************************************/
create or replace trigger max_jugadores
before insert on contratos_equipo_jugador
for each row
declare
    codEquipo contratos_equipo_jugador.cod_equipo%type := :new.cod_equipo;
    numeroContratos number;
begin
    select count(*) into numeroContratos
    from contratos_equipo_jugador
    where cod_equipo = codEquipo 
    and (fecha_fin > sysdate or fecha_fin is null);
    
    if numeroContratos > 8
    then 
        raise_application_error(-20001, 'El equipo ya tiene 8 jugadores');
    end if;
    
end max_jugadores;

CREATE OR REPLACE TRIGGER max_presupuesto_equipo
before INSERT OR UPDATE on contratos_equipo_jugador
for each row
declare
    v_clausula number;
    v_salario number;
    v_pres_max number;
  BEGIN
    SELECT presupuesto INTO v_pres_max
    FROM equipos
    WHERE cod_equipo = :new.cod_equipo;
            
    SELECT SUM(salario), SUM(clausula) INTO v_salario, v_clausula
    FROM contratos_equipo_jugador
    WHERE cod_equipo = :new.cod_equipo
    AND (fecha_fin > sysdate OR fecha_fin IS NULL);
    
    if (v_salario + v_clausula) > 200000000
    then
       raise_application_error(-20001, 'no puede sobrepasarse el presupuesto
       establecido');
    end if;

END max_presupuesto_equipo;

CREATE OR REPLACE TRIGGER check_contrato_jugador_split
BEFORE INSERT OR UPDATE OF cod_jugador, fecha_inicio, fecha_fin
ON contratos_equipo_jugador
FOR EACH ROW
DECLARE
    v_count INTEGER;
BEGIN
    SELECT COUNT(*) INTO v_count
    FROM contratos_equipo_jugador
    WHERE cod_jugador = :NEW.cod_jugador
    AND (fecha_fin > sysdate

    or fecha_fin is null);
    
    IF v_count > 0 THEN
        RAISE_APPLICATION_ERROR(-20001, 'Este jugador ya tiene un contrato activo');
    END IF;
END;


