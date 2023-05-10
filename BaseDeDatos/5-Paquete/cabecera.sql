CREATE OR REPLACE PACKAGE utilidades_calendario AS
 	PROCEDURE generar_calendario
    (p_fecha IN date,
    p_ubicacion IN varchar2,
    p_cod_temporada IN temporadas.cod_temporada%TYPE,
     p_cod_split IN splits.cod_split%TYPE);

	PROCEDURE habilitar_desabilitar_trigger
	(p_op in varchar2);
    
END utilidades_calendario;
