drop trigger min_equipos;
drop trigger min_jugadores;
drop trigger max_jugadores_draft;
drop trigger max_jugadores_wild_card;
drop trigger max_presupuesto_equipo;
drop trigger check_contrato_jugador;
drop trigger control_miembros;
drop trigger equipo_duplicado;
drop trigger trigger_temporadas_ano;
drop trigger triger_splits_fec_ini;
drop trigger triger_jornadas_fec;

--------------------------------------------------------------------------------

create or replace trigger min_equipos
before insert on splits
declare
    v_numero_equipos number;
begin
    select count(*) into v_numero_equipos from registros_equipos
    where cod_temporada = (select max(cod_temporada) from temporadas);
    
    if v_numero_equipos < 12
    then 
        raise_application_error(-20001, 'Tiene que haber un minimo de 12 equipos para poder iniciar el split.');
    end if;
end;

--------------------------------------------------------------------------------

create or replace trigger min_jugadores
before insert on registros_equipos
for each row
declare
    v_numero_jugadores number;
begin
    
    select count(*) into v_numero_jugadores
    from contratos_equipo_jugador
    where cod_equipo = :new.cod_equipo
    and (fecha_fin > sysdate or fecha_fin is null);
    
    if (v_numero_jugadores < 8)
    then
        raise_application_error(-20001, 'El equipo tiene que tener minimo 8 jugadores.');
    end if;
    
end;

--------------------------------------------------------------------------------

create or replace trigger max_jugadores_draft
before insert on contratos_equipo_jugador
for each row
declare
    v_numero_jugadores number;
    v_pertenece_draft number;
begin
    select count(*) into v_numero_jugadores
    from contratos_equipo_jugador cj, draft d
    where cj.cod_equipo = :new.cod_equipo 
    and cj.cod_jugador = d.cod_jugador --join
    and d.cod_temporada = (select max(cod_temporada) from temporadas)
    and (fecha_fin > sysdate or fecha_fin is null);
    
    select count(*) into v_pertenece_draft
    from draft 
    where cod_jugador = :new.cod_jugador
    and cod_temporada = (select max(cod_temporada) from temporadas);
    
    if v_numero_jugadores >= 8 and v_pertenece_draft > 0
    then 
        raise_application_error(-20001, 'El equipo ya tiene 8 jugadores pertenecientes al draft.');
    end if;
    
end max_jugadores;

--------------------------------------------------------------------------------

create or replace trigger max_jugadores_wild_card
before insert on contratos_equipo_jugador
for each row
declare
    v_numero_jugadores number;
begin
    select count(*) into v_numero_jugadores
    from contratos_equipo_jugador cj, registros_jugadores rj
    where cj.cod_equipo = :new.cod_equipo 
    and cj.cod_jugador = rj.cod_jugador
    and rj.cod_jugador not in (select cod_jugador from draft where cod_temporada = (select max(cod_temporada) from temporadas))
    and (fecha_fin > sysdate or fecha_fin is null);
    
    if v_numero_jugadores >= 2
    then 
        raise_application_error(-20001, 'El equipo ya tiene 2 jugadores wild card.');
    end if;
    
end max_jugadores;

--------------------------------------------------------------------------------

CREATE OR REPLACE TRIGGER max_presupuesto_equipo
before INSERT OR UPDATE on contratos_equipo_jugador
for each row
declare
    v_clausula number;
    v_salario number;
    v_presupuesto_maximo number;
  BEGIN
    SELECT presupuesto INTO v_presupuesto_maximo
    FROM equipos
    WHERE cod_equipo = :new.cod_equipo;
            
    SELECT SUM(salario), SUM(clausula) INTO v_salario, v_clausula
    FROM contratos_equipo_jugador
    WHERE cod_equipo = :new.cod_equipo
    AND (fecha_fin > sysdate OR fecha_fin IS NULL);
    
    if (v_salario + v_clausula) > v_presupuesto_maximo
    then
       raise_application_error(-20001, 'No se puede sobrepasar el presupuesto del equipo.');
    end if;

END max_presupuesto_equipo;

--------------------------------------------------------------------------------

CREATE OR REPLACE TRIGGER check_contrato_jugador
BEFORE INSERT OR UPDATE ON contratos_equipo_jugador
FOR EACH ROW
DECLARE
    v_count INTEGER;
BEGIN
    SELECT COUNT(*) INTO v_count
    FROM contratos_equipo_jugador
    WHERE cod_jugador = :NEW.cod_jugador
    AND (fecha_fin > sysdate or fecha_fin is null);
    
    IF v_count > 0 THEN
        RAISE_APPLICATION_ERROR(-20001, 'Este jugador ya tiene un contrato activo');
    END IF;
END;

--------------------------------------------------------------------------------

create or replace trigger control_miembros
before insert or update on contratos_equipo_miembro
for each row
declare
    v_numero_de_miembros number;
begin
    select count(*) into v_numero_de_miembros
    from contratos_equipo_miembro
    where cod_equipo = :new.cod_equipo
    and funcion = :new.funcion
    and (fecha_salida > sysdate or fecha_salida is null);
    
    if v_numero_de_miembros >= 1
    then
        raise_application_error(-20001, 'No puede haber mas de un miembro con la misma funciÃ³n');
    end if;
    
end control_miembros;

--------------------------------------------------------------------------------

CREATE OR REPLACE TRIGGER equipo_duplicado
BEFORE INSERT or update ON equipos
FOR EACH ROW
    DECLARE v_num_equi INT;
BEGIN

    SELECT COUNT(*) into v_num_equi 
    FROM equipos
     WHERE lower(nombre) = lower(:NEW.nombre);
    IF v_num_equi > 0 THEN
        RAISE_APPLICATION_ERROR(-20001, 'ERROR, YA EXISTE UN EQUIPO CON ESE NOMBRE');
    END IF;
END equipo_duplicado_tr;

--------------------------------------------------------------------------------

CREATE OR REPLACE TRIGGER trigger_temporadas_ano
BEFORE INSERT OR UPDATE ON temporadas
FOR EACH ROW
BEGIN
  IF :NEW.ano < EXTRACT(YEAR FROM SYSDATE) THEN
    RAISE_APPLICATION_ERROR(-20001, 'El año tiene que ser igual o superior al año actual.');
  END IF;
END;

--------------------------------------------------------------------------------

CREATE OR REPLACE TRIGGER triger_splits_fec_ini
BEFORE INSERT OR UPDATE ON splits
FOR EACH ROW
BEGIN
  IF :NEW.fecha_inicio < (SYSDATE) THEN
    RAISE_APPLICATION_ERROR(-20001, 'La fecha de inicio tiene que ser igual o superior a la fecha actual');
  END IF;
END;

--------------------------------------------------------------------------------

CREATE OR REPLACE TRIGGER triger_jornadas_fec
BEFORE INSERT OR UPDATE ON jornadas
FOR EACH ROW
BEGIN
  IF :NEW.fecha <= (SYSDATE) THEN
    RAISE_APPLICATION_ERROR(-20001, 'La fecha tiene que ser superior a la fecha actual.');
  END IF;
END;