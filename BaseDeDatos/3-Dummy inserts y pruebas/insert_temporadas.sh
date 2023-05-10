#!/bin/bash

rm ./insert_temporadas.sql 2>/dev/null

for y in {1..12}
do
    echo "INSERT INTO EQUIPOS(NOMBRE, PRESUPUESTO) VALUES('PORCINOS',200000000);" >> insert_temporadas.sql
done

for i in {0..10}
do
    echo "INSERT INTO temporadas(ano, fecha_inicio_inscripcion, fecha_fin_inscripcion) values(2023, SYSDATE + $i, SYSDATE + $i + 1);" >> insert_temporadas.sql
    for e in 1 2
    do
	echo "INSERT INTO splits(cod_temporada, nombre, fecha_inicio,fecha_fin) VALUES($i,'verano',SYSDATE, SYSDATE+1);" >> insert_temporadas.sql
    done

    for x in {0..12}
    do
	echo "INSERT INTO JORNADAS(COD_SPLIT,NUMERO,FECHA,UBICACION) VALUES($i,$x,SYSDATE,'CUPRA ARENA');" >> insert_temporadas.sql
	echo "INSERT INTO JORNADAS(COD_SPLIT,NUMERO,FECHA,UBICACION) VALUES($i+1,$x,SYSDATE,'CUPRA ARENA');" >> insert_temporadas.sql
    done


done

for p in {0..285}
do
    for m in {1..6}
    do
	echo "INSERT INTO PARTIDOS(COD_JORNADA,HORA,EQUIPO_GANADOR,FASE) VALUES($p,SYSTIMESTAMP, null,'r');" >> insert_temporadas.sql
    done
done

for q in {0..43}
do
    for s in {0..11}
    do
	echo "INSERT INTO TABLA_CLASIFICACIONES(COD_SPLIT,COD_EQUIPO,POSICION) VALUES($q,$s,$s);" >> insert_temporadas.sql
    done
done

for h in {0..95..4}
do
    echo "INSERT INTO JUGADORES(DNI,NOMBRE,APELLIDO,PIE,ALTURA,POSICION) VALUES('a$h','nombre$h','apellido$h','DERECHO',174,'DEFENSA');" >> insert_temporadas.sql
    echo "INSERT INTO JUGADORES(DNI,NOMBRE,APELLIDO,PIE,ALTURA,POSICION) VALUES('a$h','nombre$h','apellido$h','DERECHO',174,'MEDIO');" >> insert_temporadas.sql
    echo "INSERT INTO JUGADORES(DNI,NOMBRE,APELLIDO,PIE,ALTURA,POSICION) VALUES('a$h','nombre$h','apellido$h','DERECHO',174,'DELANTERO');" >> insert_temporadas.sql
    echo "INSERT INTO JUGADORES(DNI,NOMBRE,APELLIDO,PIE,ALTURA,POSICION) VALUES('a$h','nombre$h','apellido$h','DERECHO',174,'PORTERO');" >> insert_temporadas.sql
done
