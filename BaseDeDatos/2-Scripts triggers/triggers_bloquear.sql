create or replace trigger bloquear_splits
before insert or update on splits
for each row
declare
begin
    raise_application_error(-20001, 'No se pueden modifcar splits antes o después de generar el calendario');
end;
/

alter trigger bloquear_splits disable;


create or replace trigger bloquear_jornadas
before insert or update on splits
for each row
declare
begin
    raise_application_error(-20001, 'No se pueden modifcar jornadas antes o después de generar el calendario');
end;

alter trigger bloquear_jornadas disable;

create or replace trigger bloquear_partidos
before insert or update on splits
for each row
declare
begin
    raise_application_error(-20001, 'No se pueden modifcar partidos antes o después de generar el calendario');
end;

alter trigger bloquear_partidos disable;

-- Los triggers están desactivo por defecto. Se activaran cuando se genere el calendario mediante el procedimiento
-- si se desea modificar durante lo jornada, solo se podra desactivar utilizando un procedimiento del paquete calendario
--

create or replace trigger bloquear_equipos
before insert or update on equipos
for each row
    begin
        raise_application_error(-20001, 'No se pueden modifcar los equipos antes o después de generar el calendario');
    end;
    
    
create or replace trigger bloquear_contratos_jugador
before insert or update on contratos_equipo_jugador
for each row
    begin
        raise_application_error(-20001, 'No se pueden modifcar los contratos antes o después de generar el calendario');
end;