#!/bin/bash

file='./dummy-inserts.sql';

echo "INSERT INTO temporadas (ano, fecha_inicio_inscripcion, fecha_fin_inscripcion) VALUES (2023, TO_DATE('01/01/2023', 'DD/MM/YYYY'), TO_DATE('30/06/2023', 'DD/MM/YYYY'));" > $file;


# Crear los equipos
for i in {1..12}
do
    echo "INSERT INTO equipos (nombre, logo, presupuesto) VALUES ('Equipo $i', EMPTY_BLOB(), DEFAULT);" >> $file;
done

# Crear los jugadores
for i in {0..95}
do
    echo "INSERT INTO agendas (email, telefono) VALUES ('example$i@example.com', '123456789');" >> $file
    echo "INSERT INTO jugadores (dni, nombre, apellido, pie, altura, cod_agenda) VALUES ('1234567$i', 'Jugador$i', '$i', 'Izquierdo', 180, $i);
" >> $file
done

# Crear los registros de los jugadores
for i in {0..95}
do
    echo "INSERT INTO registros_jugadores (cod_temporada, cod_jugador) VALUES (0, $i);" >> $file
    echo "INSERT INTO draft (cod_temporada, cod_jugador, posicion) VALUES (0, $i, $(($i + 1)));" >> $file
done

codJugador=0;

# Crear contratos entre equipo y jugador 
for i in {0..11}
do
    for x in {1..8}
    do
	echo "INSERT INTO contratos_equipo_jugador (cod_equipo, cod_jugador, salario, clausula, fecha_inicio, fecha_fin) VALUES ($i, $codJugador, 10500000, 10000000, TO_DATE('01/01/2024', 'DD/MM/YYYY'), TO_DATE('31/12/2024', 'DD/MM/YYYY'));" >> $file;
	codJugador=$(($codJugador + 1));
    done
done


# Registrar los equipos en la temporada
for i in {0..11}
do
    echo "INSERT INTO registros_equipos (cod_temporada, cod_equipo) VALUES (0, $i);" >> $file;
done

# Registrar los miembros del equipo
for i in {0..11}
do
    echo "INSERT INTO miembros (dni, nombre, apellido, cod_agenda) VALUES ('$i', 'Miembro $i', 'Apellido', 0);" >> $file;
    echo "INSERT INTO miembros (dni, nombre, apellido, cod_agenda) VALUES ('$(($i + 12))', 'Miembro $(($i + 12))', 'Apellido', 0);" >> $file;
    echo "INSERT INTO miembros (dni, nombre, apellido, cod_agenda) VALUES ('$(($i + 24))', 'Miembro $(($i + 24))', 'Apellido', 0);" >> $file;

done

for i in {0..11}
do
    echo "INSERT INTO contratos_equipo_miembro (cod_equipo, cod_miembro, funcion, fecha_entrada) VALUES ($i, $i, 'p', TO_DATE('01/01/2022', 'DD/MM/YYYY'));" >> $file;
    echo "INSERT INTO contratos_equipo_miembro (cod_equipo, cod_miembro, funcion, fecha_entrada) VALUES ($i, $(($i + 12)), 's', TO_DATE('01/01/2022', 'DD/MM/YYYY'));" >> $file;
    echo "INSERT INTO contratos_equipo_miembro (cod_equipo, cod_miembro, funcion, fecha_entrada) VALUES ($i, $(($i + 24)), 'e', TO_DATE('01/01/2022', 'DD/MM/YYYY'));" >> $file;    
done

echo "INSERT INTO splits (cod_temporada, nombre, fecha_inicio, fecha_fin) VALUES (0, 'Split 1', TO_DATE('01/07/2024', 'DD/MM/YYYY'), TO_DATE('31/07/2024', 'DD/MM/YYYY'));" >> $file;
echo "INSERT INTO splits (cod_temporada, nombre, fecha_inicio, fecha_fin) VALUES (0, 'Split 2', TO_DATE('01/07/2024', 'DD/MM/YYYY'), TO_DATE('31/07/2024', 'DD/MM/YYYY'));" >> $file;
echo "insert into permisos values(default, 't', 't', 't', 't', 't', 't');" >> $file;
echo "insert into permisos values(default, 'f', 'f', 'f', 'f', 'f', 'f');" >> $file;
echo "insert into cuentas values(default, 0, 'admin', 'asd123', 'admin@email.com');" >> $file;
echo "COMMIT;" >> $file;


