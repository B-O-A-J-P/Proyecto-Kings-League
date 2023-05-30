
--CREAR TABLA:
CREATE TABLE playoffs(result CLOB);

DROP TYPE Equipos_xml FORCE;
drop type equipolist_tipo force;
DROP TYPE Partidos_xml FORCE;
DROP TYPE partidolist_tipo FORCE;
DROP TYPE Splits_xml FORCE;
DROP TYPE splitlist_tipo FORCE;
DROP TYPE temporada_tipo FORCE;


CREATE OR REPLACE TYPE Equipos_xml AS OBJECT (
  "@ID" NUMBER(6),
  nombre VARCHAR2(50),
  logo BLOB,
  numero_goles NUMBER(2),
  resultado VARCHAR2(1)
);
/
CREATE OR REPLACE TYPE equipolist_tipo AS TABLE OF Equipos_xml;  
/
CREATE OR REPLACE TYPE Partidos_xml AS OBJECT (
  "@ID" NUMBER(5),
  hora VARCHAR2(20),
  ubicacion varchar2(50),
  fase varchar2(1),
  equipo_local Equipos_xml,
  equipo_visitante Equipos_xml
);
/
CREATE OR REPLACE TYPE partidolist_tipo AS TABLE OF Partidos_xml;
/

CREATE OR REPLACE TYPE Splits_xml AS OBJECT (
  "@ID" NUMBER(5),
partido partidolist_tipo
);
/
CREATE OR REPLACE TYPE splitlist_tipo AS TABLE OF Splits_xml;
/
CREATE OR REPLACE TYPE temporada_tipo AS OBJECT (
  "@ID" NUMBER(5),
  split splitlist_tipo
);
/

DECLARE
  resultado CLOB;
  qryCtx DBMS_XMLGEN.ctxHandle;
BEGIN
qryCtx := DBMS_XMLGEN.newContext('
  SELECT temporada_tipo(
    t.cod_temporada,
    CAST(
      MULTISET(
        SELECT
          s.cod_split,
                CAST(
                  MULTISET(
                    SELECT 
                      p.cod_partido,
                      TO_CHAR(p.hora, ''YYYY-MM-DD"T"HH24:MI:SS''),
                      j.ubicacion,
                      p.fase,
                      Equipos_xml(
                        e1.cod_equipo,
                        e1.nombre,
                        e1.logo,
                         (SELECT SUM(numero_goles) FROM informacion_partidos WHERE cod_partido = p.cod_partido AND cod_equipo = e1.cod_equipo),
                        (SELECT resultado FROM informacion_partidos WHERE cod_partido = p.cod_partido AND cod_equipo = e1.cod_equipo)
                      ),
                      Equipos_xml(
                        e2.cod_equipo,
                        e2.nombre,
                        e2.logo,
                        (SELECT SUM(numero_goles) FROM informacion_partidos WHERE cod_partido = p.cod_partido AND cod_equipo = e2.cod_equipo),
                        (SELECT resultado FROM informacion_partidos WHERE cod_partido = p.cod_partido AND cod_equipo = e2.cod_equipo)
                      )
                    
                    FROM partidos p
                    JOIN equipos e1 ON p.cod_equipo1 = e1.cod_equipo
                    JOIN equipos e2 ON p.cod_equipo2 = e2.cod_equipo
                    JOIN jornadas j ON p.cod_jornada = j.cod_jornada
                    LEFT JOIN informacion_partidos ip1 ON p.cod_partido = ip1.cod_partido AND p.cod_equipo1 = ip1.cod_equipo
                    LEFT JOIN informacion_partidos ip2 ON p.cod_partido = ip2.cod_partido AND p.cod_equipo2 = ip2.cod_equipo
                    where j.cod_jornada = (select max(cod_jornada) from jornadas)
                    ORDER BY p.cod_partido ASC
                  ) AS partidolist_tipo
                )
          
        FROM splits s where
        s.cod_temporada = t.cod_temporada
        ORDER BY s.cod_split ASC
      ) AS splitlist_tipo
    )) as "temporada"
  FROM temporadas t
    where 
    
  t.cod_temporada = 0');
  
   dbms_xmlgen.setRowTag( qryCtx, NULL );
   
   dbms_xmlgen.setRowSetTag( qryCtx, NULL );
   
    DBMS_XMLGEN.setNullHandling(qryCtx, 2);


  resultado := DBMS_XMLGEN.getXML(qryCtx);


  INSERT INTO playoffs VALUES (resultado);
   DBMS_XMLGEN.closeContext(qryCtx);
END;

