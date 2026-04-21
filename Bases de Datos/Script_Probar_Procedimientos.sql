-- Autor Equipo 1: Unax Gonzales, Urko Lopez, Fatima Din, Eathan Garzon

-- Procedimiento anonimo para porbar el procedimieno almacenado pr_obtener_informe_equipos
declare
    p_cursor sys_refcursor;
    
    v_equipo          varchar2(100);
    v_fundacion       date;
    v_num_jugadores   number;
    v_max_sueldo      number;
    v_min_sueldo      number;
    v_media_sueldos   number;
begin

    obtener_informe_equipos(p_cursor);

    loop
       
        fetch p_cursor into v_equipo, v_fundacion, v_num_jugadores, v_max_sueldo, 
                            v_min_sueldo, v_media_sueldos;
        exit when p_cursor%notfound;
        
        dbms_output.put_line('equipo: ' || v_equipo || 
                             ' | fundacion: ' || to_char(v_fundacion, 'dd/mm/yyyy') || 
                             ' | nº jugadores: ' || v_num_jugadores || 
                             ' | max sueldo: ' || v_max_sueldo || 
                             ' | min sueldo: ' || v_min_sueldo || 
                             ' | media: ' || v_media_sueldos);
    end loop;
    
end;


/*
equipo: leones | fundacion: 01/01/2014 | nº jugadores: 5 | max sueldo: 2590 | min sueldo: 2000 | media: 2454
equipo: fantasmas | fundacion: 14/03/2004 | nº jugadores: 2 | max sueldo: 3000 | min sueldo: 1500 | media: 2250
equipo: leviatan | fundacion: 19/03/2020 | nº jugadores: 3 | max sueldo: 2900 | min sueldo: 1500 | media: 2366,67
equipo: equipo de prueba | fundacion: 16/04/2026 | nº jugadores: 1 | max sueldo: 1500 | min sueldo: 1500 | media: 1500

Procedimiento PL/SQL terminado correctamente.

*/



--BLOQUE ANONIMO PARA PROBAR PROCEDIMIENTO INFORME VICTORIAS

--BLOQUE ANONIMO PARA PROBAR PROCEDIMIENTO INFORME VICTORIAS

DECLARE
    v_cursor SYS_REFCURSOR;
    v_nombre EQUIPOS.nombre%TYPE;
    v_victorias   NUMBER;
    v_derrotas   NUMBER;
BEGIN

    pr_informe_victorias_derrotas(v_cursor);
    
    DBMS_OUTPUT.PUT_LINE('CLASIFICACION DE LA LIGA:');

    

    LOOP
        FETCH v_cursor INTO v_nombre, v_victorias, v_derrotas;
        EXIT WHEN v_cursor%NOTFOUND;
        
        DBMS_OUTPUT.PUT_LINE('Equipo: ' || v_nombre|| ' | Victorias: ' || v_victorias || ' | Derrotas: ' || v_derrotas);

    END LOOP;
    

EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Error: ' || SQLERRM);

END;

/*
CLASIFICACION DE LA LIGA:
Equipo: leones | Victorias: 1 | Derrotas: 0
Equipo: fantasmas | Victorias: 0 | Derrotas: 1

Procedimiento PL/SQL terminado correctamente.

*/


--BLOQUE ANONIMO PARA PROBAR EL PROCEDIMIENTO pr_informe_jugadores

--BLOQUE ANONIMO PARA PROBAR EL PROCEDIMIENTO pr_informe_jugadores


DECLARE
    v_cursor SYS_REFCURSOR;
    v_nombre VARCHAR2(50);
    v_apellido VARCHAR2(50);
    v_rol VARCHAR2(50);
    v_sueldo NUMBER;
BEGIN 

    pr_informe_jugadores('fantasmas', v_cursor);
    
    LOOP
        FETCH v_cursor INTO v_nombre, v_apellido,v_rol, v_sueldo;
        EXIT WHEN v_cursor%NOTFOUND;
        
        DBMS_OUTPUT.PUT_LINE(
            v_nombre || ' ' || v_apellido || ' | rol: ' || v_rol || ' | sueldo: ' || v_sueldo);
            
    END LOOP;
    
    CLOSE v_cursor;
    
exception 
when others then
    dbms_output.put_line('Error: ' || sqlerrm);

END;

/*
ana martinez centinela 3000
Carlos López iniciador 1500

Procedimiento PL/SQL terminado correctamente.

*/

