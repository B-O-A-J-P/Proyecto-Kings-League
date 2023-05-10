INSERT INTO temporadas (ano, fecha_inicio_inscripcion, fecha_fin_inscripcion) VALUES (2021, TO_DATE('01/01/2021', 'DD/MM/YYYY'), TO_DATE('30/06/2021', 'DD/MM/YYYY'));

INSERT INTO splits (cod_temporada, nombre, fecha_inicio, fecha_fin) VALUES (0, 'Split 1', TO_DATE('01/07/2021', 'DD/MM/YYYY'), TO_DATE('31/07/2021', 'DD/MM/YYYY'));

/*
Error starting at line : 3 in command -
INSERT INTO splits (cod_temporada, nombre, fecha_inicio, fecha_fin) VALUES (0, 'Split 1', TO_DATE('01/07/2021', 'DD/MM/YYYY'), TO_DATE('31/07/2021', 'DD/MM/YYYY'))
Error at Command Line : 3 Column : 13
Error report -
SQL Error: ORA-20001: Tiene que haber un mínimo de 12 equipos para poder iniciar el split.
ORA-06512: at "HR.MIN_EQUIPOS", line 9
ORA-04088: error during execution of trigger 'HR.MIN_EQUIPOS'

*/