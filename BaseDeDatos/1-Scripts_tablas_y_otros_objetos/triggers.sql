
/*
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
drop trigger bloquear_splits;
drop trigger bloquear_jornadas;
drop trigger bloquear_partidos;
drop trigger bloquear_equipos;
drop trigger bloquear_contratos_jugador;
drop trigger control_splits;
*/

create or replace trigger control_splits
before insert or update on splits
for each row
declare
    v_cod_temporada temporadas.cod_temporada%type;
begin
    select max(cod_temporada) into v_cod_temporada from temporadas;
    
    if (:NEW.cod_temporada < v_cod_temporada) 
    then
        raise_application_error(-20001, 'No se puede insertars splits en una temporada finalizada.');
    end if;
end;
/
--------------------------------------------------------------------------------

create or replace trigger min_equipos
before insert or update on splits
for each row
declare
    v_numero_equipos number;
begin
 
    select count(*) into v_numero_equipos from registros_equipos
    where cod_temporada = :NEW.cod_temporada;
    
    if v_numero_equipos < 12
    then 
        raise_application_error(-20001, 'Tiene que haber un mínimo de 12 equipos
        para poder iniciar el split.');
    end if;
end;
/

--------------------------------------------------------------------------------

create or replace trigger min_jugadores
before insert or update on registros_equipos
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
        raise_application_error(-20001, 'El equipo tiene que tener un mínimo 8 jugadores.');
    end if;
    
end;
/
--------------------------------------------------------------------------------


CREATE OR REPLACE TRIGGER max_jugadores_draft
FOR INSERT OR UPDATE OF cod_equipo, cod_jugador ON contratos_equipo_jugador
COMPOUND TRIGGER

    v_cod_equipo equipos.cod_equipo%TYPE;
    v_cod_jugador jugadores.cod_jugador%TYPE;
    
  BEFORE EACH ROW IS
  BEGIN
    v_cod_equipo := :NEW.cod_equipo;
    v_cod_jugador := :NEW.cod_jugador;
  END BEFORE EACH ROW;

  AFTER EACH ROW IS
  BEGIN
    null;
  END AFTER EACH ROW;

  AFTER STATEMENT IS
    v_numero_jugadores number;
    v_pertenece_draft number;
  BEGIN
    v_numero_jugadores := get_numero_jugadores_draft(v_cod_equipo);
    
    select count(*) into v_pertenece_draft
    from draft 
    where cod_jugador = v_cod_jugador
    and cod_temporada = (select max(cod_temporada) from temporadas);
    
    if v_numero_jugadores > 8 and v_pertenece_draft > 0
    then 
        raise_application_error(-20001, 'El equipo ' || v_cod_equipo || ' ya tiene 8 jugadores pertenecientes al draft.');
    end if;

    
  END AFTER STATEMENT;

END max_jugadores_draft;
/

--------------------------------------------------------------------------------

CREATE OR REPLACE TRIGGER max_jugadores_wild_card
FOR INSERT OR UPDATE OF cod_equipo, cod_jugador ON contratos_equipo_jugador
COMPOUND TRIGGER

    v_cod_equipo equipos.cod_equipo%TYPE;
    v_cod_jugador jugadores.cod_jugador%TYPE;
    
  BEFORE EACH ROW IS
  BEGIN
    v_cod_equipo := :NEW.cod_equipo;
    v_cod_jugador := :NEW.cod_jugador;
  END BEFORE EACH ROW;

  AFTER EACH ROW IS
  BEGIN
    null;
  END AFTER EACH ROW;

  AFTER STATEMENT IS
    v_numero_jugadores number;
  BEGIN
    
    select count(*) into v_numero_jugadores
    from contratos_equipo_jugador cj, registros_jugadores rj
    where cj.cod_equipo = v_cod_equipo 
    and cj.cod_jugador = rj.cod_jugador
    and rj.cod_jugador not in (select cod_jugador from draft 
        where cod_temporada = (select max(cod_temporada) from temporadas))
    and (fecha_fin > sysdate or fecha_fin is null);
    
    if v_numero_jugadores > 2
    then 
        raise_application_error(-20001, 'El equipo ' || v_cod_equipo ||' ya tiene 2 jugadores wild card.');
    end if;
    
  END AFTER STATEMENT;

END max_jugadores_wild_card;
/

--------------------------------------------------------------------------------

CREATE OR REPLACE TRIGGER max_presupuesto_equipo
FOR INSERT OR UPDATE ON contratos_equipo_jugador
COMPOUND TRIGGER

    v_cod_equipo equipos.cod_equipo%TYPE;
    v_cod_jugador jugadores.cod_jugador%TYPE;
    v_salario contratos_equipo_jugador.salario%TYPE;
    v_clausula contratos_equipo_jugador.clausula%TYPE;
    
  BEFORE EACH ROW IS
  BEGIN
    v_cod_equipo := :NEW.cod_equipo;
    v_cod_jugador := :NEW.cod_jugador;
    v_salario := :NEW.salario;
    v_clausula := :NEW.clausula;
  END BEFORE EACH ROW;

  AFTER EACH ROW IS
  BEGIN
    null;
  END AFTER EACH ROW;

  AFTER STATEMENT IS
    v_dinero_total number;
    v_presupuesto_maximo number;
  BEGIN
    
    SELECT presupuesto INTO v_presupuesto_maximo
    FROM equipos
    WHERE cod_equipo = v_cod_equipo;
            
    SELECT SUM(salario + clausula) INTO v_dinero_total
    FROM contratos_equipo_jugador
    WHERE cod_equipo = v_cod_equipo
    AND (fecha_fin > sysdate OR fecha_fin IS NULL);

    if v_dinero_total > v_presupuesto_maximo
    then
       raise_application_error(-20001, 'No se puede sobrepasar el presupuesto del equipo.');
    end if;
    
  END AFTER STATEMENT;

END max_presupuesto_equipo;

/
--------------------------------------------------------------------------------

CREATE OR REPLACE TRIGGER check_contrato_jugador
FOR INSERT OR UPDATE OF cod_equipo, cod_jugador ON contratos_equipo_jugador
COMPOUND TRIGGER

    v_cod_equipo equipos.cod_equipo%TYPE;
    v_cod_jugador jugadores.cod_jugador%TYPE;
    
  BEFORE EACH ROW IS
  BEGIN
    v_cod_equipo := :NEW.cod_equipo;
    v_cod_jugador := :NEW.cod_jugador;
  END BEFORE EACH ROW;

  AFTER EACH ROW IS
  BEGIN
    null;
  END AFTER EACH ROW;

  AFTER STATEMENT IS
    v_count NUMBER;
  BEGIN
  
    SELECT COUNT(*) INTO v_count
    FROM contratos_equipo_jugador
    WHERE cod_jugador = v_cod_jugador
    AND (fecha_fin > sysdate or fecha_fin is null);
    
    IF v_count > 1 THEN
        RAISE_APPLICATION_ERROR(-20001, 'Este jugador ya tiene un contrato activo.');
    END IF;
    
  END AFTER STATEMENT;

END check_contrato_jugador;
/

--------------------------------------------------------------------------------

CREATE OR REPLACE TRIGGER control_miembros
FOR INSERT OR UPDATE OF cod_equipo, cod_miembro ON contratos_equipo_miembro
COMPOUND TRIGGER

    v_cod_equipo equipos.cod_equipo%TYPE;
    v_cod_miembro contratos_equipo_miembro.cod_miembro%TYPE;
    v_funcion contratos_equipo_miembro.funcion%TYPE;
    
  BEFORE EACH ROW IS
  BEGIN
    v_cod_equipo := :NEW.cod_equipo;
    v_cod_miembro := :NEW.cod_miembro;
    v_funcion := :NEW.funcion;
  END BEFORE EACH ROW;

  AFTER EACH ROW IS
  BEGIN
    null;
  END AFTER EACH ROW;

  AFTER STATEMENT IS
    v_numero_de_miembros number;
  BEGIN
  
    select count(*) into v_numero_de_miembros
    from contratos_equipo_miembro
    where cod_equipo = v_cod_equipo
    and funcion = v_funcion
    and (fecha_salida > sysdate or fecha_salida is null);
    
    if v_numero_de_miembros > 1
    then
        raise_application_error(-20001, 'No puede haber más de un miembro con la
        misma función');
    end if;
    
  END AFTER STATEMENT;

END control_miembros;
/
--------------------------------------------------------------------------------

CREATE OR REPLACE TRIGGER check_registro_equipo_fecha_inscripcion
BEFORE INSERT OR UPDATE ON registros_equipos
FOR EACH ROW
DECLARE
    v_fecha_fin DATE;
BEGIN
  
  SELECT fecha_fin_inscripcion INTO v_fecha_fin
  FROM temporadas where cod_temporada = :NEW.cod_temporada;
  
  IF v_fecha_fin < SYSDATE
  THEN
    RAISE_APPLICATION_ERROR(-20001, 'No se puede registrar un equipo pasada la fecha de inscripción.');
  END IF;
  
END triger_splits_fec_ini;
/
--------------------------------------------------------------------------------
CREATE OR REPLACE TRIGGER check_registro_jugador_fecha_inscripcion
BEFORE INSERT OR UPDATE ON registros_jugadores
FOR EACH ROW
DECLARE
    v_fecha_inicio DATE;
    v_fecha_fin DATE;
BEGIN
  
  SELECT fecha_inicio_inscripcion, fecha_fin_inscripcion INTO v_fecha_inicio, v_fecha_fin
  FROM temporadas where cod_temporada = :NEW.cod_temporada;
  
  IF SYSDATE < v_fecha_inicio OR SYSDATE > v_fecha_fin
  THEN
    RAISE_APPLICATION_ERROR(-20001, 'No se puede registrar el jugador fuera de la fecha de inscripción.');
  END IF;
  
END check_registro_jugador_fecha_inscripcion;
/
--------------------------------------------------------------------------------


CREATE OR REPLACE TRIGGER equipo_duplicado
FOR INSERT OR UPDATE ON equipos
COMPOUND TRIGGER

    v_nombre_equipo equipos.nombre%TYPE;
    
  BEFORE EACH ROW IS
  BEGIN
    v_nombre_equipo := :NEW.nombre;
  END BEFORE EACH ROW;

  AFTER STATEMENT IS
  v_num_equi NUMBER;
  BEGIN
  
    SELECT COUNT(*) into v_num_equi 
    FROM equipos
    WHERE lower(nombre) = lower(v_nombre_equipo);
     
    IF v_num_equi > 1 THEN
        RAISE_APPLICATION_ERROR(-20001, 'Ya existe un equipo con ese nombre.');
    END IF;
    
  END AFTER STATEMENT;

END equipo_duplicado;
/
--------------------------------------------------------------------------------

CREATE OR REPLACE TRIGGER trigger_temporadas_ano
BEFORE INSERT OR UPDATE ON temporadas
FOR EACH ROW
BEGIN
  IF :NEW.ano < EXTRACT(YEAR FROM SYSDATE) THEN
    RAISE_APPLICATION_ERROR(-20001, 'El año tiene que ser igual o superior al año actual.');
  END IF;
END trigger_temporadas_ano;
/
--------------------------------------------------------------------------------

CREATE OR REPLACE TRIGGER triger_splits_fec_ini
BEFORE INSERT OR UPDATE ON splits
FOR EACH ROW
BEGIN
  IF :NEW.fecha_inicio < (SYSDATE) THEN
    RAISE_APPLICATION_ERROR(-20001, 'La fecha de inicio tiene que ser igual o superior a la fecha actual');
  END IF;
END triger_splits_fec_ini;
/

--------------------------------------------------------------------------------

CREATE OR REPLACE TRIGGER triger_jornadas_fec
BEFORE INSERT OR UPDATE ON jornadas
FOR EACH ROW
BEGIN
  IF :NEW.fecha <= (SYSDATE) THEN
    RAISE_APPLICATION_ERROR(-20001, 'La fecha tiene que ser superior a la fecha actual.');
  END IF;
END triger_jornadas_fec;
/
--------------------------------------------------------------------------------
create or replace trigger bloquear_splits
before insert or update on splits
for each row
declare
begin
    raise_application_error(-20001, 'No se pueden modifcar splits antes o despues de generar el calendario');

end bloquear_splits;

/

alter trigger bloquear_splits disable;


create or replace trigger bloquear_jornadas
before insert or update on splits
for each row
declare
begin
    raise_application_error(-20001, 'No se pueden modifcar jornadas antes o despues de generar el calendario');

end bloquear_jornadas;
/

alter trigger bloquear_jornadas disable;

create or replace trigger bloquear_partidos
before insert or update on splits
for each row
declare
begin
    raise_application_error(-20001, 'No se pueden modifcar partidos antes o despues de generar el calendario');

end bloquear_partidos;
/

alter trigger bloquear_partidos disable;

create or replace trigger bloquear_equipos
before insert or update on equipos
for each row
    begin
        raise_application_error(-20001, 'No se pueden modifcar los equipos antes o despues de generar el calendario');

    end bloquear_equipos;
    /
alter trigger bloquear_equipos disable;
    
create or replace trigger bloquear_contratos_jugador
before insert or update on contratos_equipo_jugador
for each row
    begin
        raise_application_error(-20001, 'No se pueden modifcar los contratos antes o despues de generar el calendario');

end bloquear_contratos_jugador;
/
alter trigger bloquear_contratos_jugador disable;
