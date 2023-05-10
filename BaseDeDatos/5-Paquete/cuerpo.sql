CREATE OR REPLACE PACKAGE BODY utilidades_calendario AS
    
    --procedimiento crear calendario:
PROCEDURE generar_calendario
    (p_fecha IN date,
    p_ubicacion IN varchar2,
    p_cod_temporada IN temporadas.cod_temporada%TYPE,
     p_cod_split IN splits.cod_split%TYPE)
AS
    --conserguir los códigos de los equipos 
    CURSOR cursor_lista_cod_equipo IS 
        SELECT cod_equipo FROM registros_equipos 
        WHERE cod_temporada = p_cod_temporada;
        
    TYPE record_lista_cod_equipo IS RECORD(
        cod_equipo equipos.cod_equipo%TYPE
    );
    
    TYPE varray_lista_cod_equipo IS VARRAY(12)
        OF record_lista_cod_equipo;
    
    array_lista_cod_equipo varray_lista_cod_equipo := varray_lista_cod_equipo();
    
    --crear los partidos
    TYPE record_partidos IS RECORD (
        cod_equipo1 equipos.cod_equipo%TYPE,
        cod_equipo2 equipos.cod_equipo%TYPE
    );
    
    TYPE table_partidos IS TABLE OF record_partidos INDEX BY PLS_INTEGER;
    
    array_partidos table_partidos;
    
    --jornadas
    TYPE table_jornadas IS TABLE OF table_partidos INDEX BY PLS_INTEGER;
    
    array_jornadas table_jornadas;
    
    j number;
    
    temp record_partidos;

BEGIN

    FOR cod_equipo IN cursor_lista_cod_equipo LOOP
        array_lista_cod_equipo.EXTEND;
        array_lista_cod_equipo(array_lista_cod_equipo.LAST).cod_equipo := cod_equipo.cod_equipo;
    END LOOP;
    
    
    DECLARE
        numero_partido NUMBER := 1;
    BEGIN
        FOR l_index1 IN array_lista_cod_equipo.FIRST..array_lista_cod_equipo.LAST
        LOOP
            FOR l_index2 IN array_lista_cod_equipo.FIRST..array_lista_cod_equipo.LAST
            LOOP
                IF array_lista_cod_equipo(l_index2).cod_equipo > array_lista_cod_equipo(l_index1).cod_equipo
                THEN
                    array_partidos(numero_partido).cod_equipo1 := array_lista_cod_equipo(l_index1).cod_equipo;
                    array_partidos(numero_partido).cod_equipo2 := array_lista_cod_equipo(l_index2).cod_equipo;
                    numero_partido := numero_partido + 1;
                END IF;
            END LOOP;
        END LOOP;
        
        
        FOR i IN REVERSE array_partidos.FIRST+1..array_partidos.LAST LOOP
            j := TRUNC(DBMS_RANDOM.VALUE(array_partidos.FIRST, i));
            temp := array_partidos(i);
            array_partidos(i) := array_partidos(j);
            array_partidos(j) := temp;
        END LOOP;
        
    END;  
    
    declare
        partidoInsertado boolean :=false;
        codJornada jornadas.cod_jornada%type;
        index_min number := 1;
        index_max number := array_partidos.count;
        intervaloJornada number := 7;
        hora number := 0;
        fecha date:= p_fecha;
        c number;
        v_hora timestamp := TO_TIMESTAMP(TO_CHAR(fecha, 'YYYY-MM-DD') || '16:00', 'YYYY-MM-DD HH24:MI');
    begin
        for i in 1..11
        loop
            insert into jornadas values(p_cod_split, default, i, fecha, p_ubicacion);
            for x in 1..6 
            loop

                while not partidoInsertado and index_min <= index_max
                loop

                    if array_partidos.exists(index_min)
                    then

                        select cod_jornada into codJornada from jornadas where cod_split = p_cod_split and numero = i;
                        select count(*) into c from partidos
                        where cod_jornada = codJornada
                        and (array_partidos(index_min).cod_equipo1 in (cod_equipo1, cod_equipo2)
                        or array_partidos(index_min).cod_equipo2 in (cod_equipo1, cod_equipo2));
                        if c = 0
                        then

                            insert into partidos values(codJornada, default, array_partidos(index_min).cod_equipo1, array_partidos(index_min).cod_equipo2, v_hora, default);
                            partidoInsertado := true;
                            array_partidos.delete(index_min);
                        end if;
                     end if;
                     index_min := index_min + 1;
                end loop;
                index_min := 1;
                partidoInsertado := false;
                v_hora := v_hora + INTERVAL '1' hour;
            end loop;
        fecha := fecha + intervaloJornada;
        v_hora := TO_TIMESTAMP(TO_CHAR(fecha, 'YYYY-MM-DD') || '16:00', 'YYYY-MM-DD HH24:MI');

        end loop;
        

    end;
EXCEPTION
    when others
    then    
        DBMS_OUTPUT.PUT_LINE('Ha occurido un error: ' || SQLERRM);
END generar_calendario;

procedure habilitar_desabilitar_trigger
(p_op in varchar2) as
begin

    if p_op = 'habilitar' then
    
        EXECUTE IMMEDIATE 'ALTER TRIGGER bloquear_splits  enable';
        EXECUTE IMMEDIATE 'ALTER TRIGGER bloquear_jornadas enable';
        EXECUTE IMMEDIATE 'ALTER TRIGGER bloquear_partidos enable';
    
    elsif p_op = 'deshabilitar' then
    
        EXECUTE IMMEDIATE 'ALTER TRIGGER bloquear_splits disable';
        EXECUTE IMMEDIATE 'ALTER TRIGGER  bloquear_jornadas  disable';
        EXECUTE IMMEDIATE 'ALTER TRIGGER  bloquear_partidos  disable';
        end if;
        
    end habilitar_desabilitar_trigger;

    
    
END utilidades_calendario;

