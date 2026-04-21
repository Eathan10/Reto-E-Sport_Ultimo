-- Autor Equipo 1: Unax Gonzales, Urko Lopez, Fatima Din, Eathan Garzon

--procedimiento almacenado para generar un informe de los jugadores

create or replace procedure pr_informe_jugadores(
p_nombre_equipo IN varchar2,
p_cursor OUT SYS_REFCURSOR)

IS


begin

open p_cursor for
select nombre, apellido, rol, sueldo
from datos_jugadores
where nombre_equipo = p_nombre_equipo;

exception 
when others then
    dbms_output.put_line('Error: ' || sqlerrm);

end;

--PROCEDIMIENTO ALMACENADO PARA GENERAR UN INFORME DE LOS EQUIPOS

create or replace procedure pr_obtener_informe_equipos (
    p_cursor out sys_refcursor
) 
as
begin
    open p_cursor for 
    select equipo, fundacion, numero_jugadores, max_sueldo, 
            min_sueldo, Media_sueldos
    from vs_sueldos_numeros_equipos;
    

    

exception 
when others then
    dbms_output.put_line('Error: ' || sqlerrm);

end pr_obtener_informe_equipos;
/

desc vs_sueldos_numeros_equipos

--PROCEDIMIENTO ALMACENADO CREADO POR NOSOTROS PARA MOSTRAR DESPUï¿½S EL NUMERO DE
--VICTORIAS Y DERROTAS QUE LLEVA CADA EQUIPO


CREATE OR REPLACE PROCEDURE pr_informe_victorias_derrotas(
p_cursor OUT SYS_REFCURSOR)

IS

BEGIN     
    
    OPEN p_cursor FOR
    SELECT nombre_equipo, victorias, derrotas
    FROM datos_victorias_derrotas;
    
    
exception 
when others then
    dbms_output.put_line('Error: ' || sqlerrm);

end;





