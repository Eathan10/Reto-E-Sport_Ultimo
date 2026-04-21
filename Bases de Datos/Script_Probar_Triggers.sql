-- Autor Equipo 1: Unax Gonzales, Urko Lopez, Fatima Din, Eathan Garzon


-- Pruebas para el trigger tr_sueldo_jugador

-- Fallos

insert into jugadores ( nombre, apellido, nacionalidad, fecha_nac, nickname, rol, sueldo, cod_equipo)
values ('juan', 'perez', 'española', to_date('2000-01-01', 'yyyy-mm-dd'), 'jp99', 'centinela', 1000, 1);


/*

Error que empieza en la línea: 8 del comando -
insert into jugadores (cod_jugador, nombre, apellido, nacionalidad, fecha_nac, nickname, rol, sueldo, cod_equipo)
values (99, 'juan', 'perez', 'española', to_date('2000-01-01', 'yyyy-mm-dd'), 'jp99', 'centinela', 1000, 1)
Error en la línea de comandos : 8 Columna : 13
Informe de error -
Error SQL: ORA-20001: error: El sueldo del jugador no puede ser menor al SMI (1.221)
ORA-06512: en "EQDAW01.TR_SUELDO_JUGADOR", línea 3
ORA-04088: error durante la ejecución del disparador 'EQDAW01.TR_SUELDO_JUGADOR'

https://docs.oracle.com/error-help/db/ora-20001/

More Details :
https://docs.oracle.com/error-help/db/ora-20001/
https://docs.oracle.com/error-help/db/ora-06512/
https://docs.oracle.com/error-help/db/ora-04088/
*/
update jugadores 
set sueldo = 1100 
where cod_jugador = 100;

/*

Error que empieza en la línea: 30 del comando -
update jugadores 
set sueldo = 1100 
where cod_jugador = 100
Error en la línea de comandos : 30 Columna : 8
Informe de error -
Error SQL: ORA-20001: error: El sueldo del jugador no puede ser menor al SMI (1.221)
ORA-06512: en "EQDAW01.TR_SUELDO_JUGADOR", línea 3
ORA-04088: error durante la ejecución del disparador 'EQDAW01.TR_SUELDO_JUGADOR'

https://docs.oracle.com/error-help/db/ora-20001/

More Details :
https://docs.oracle.com/error-help/db/ora-20001/
https://docs.oracle.com/error-help/db/ora-06512/
https://docs.oracle.com/error-help/db/ora-04088/

*/

-- Exitos

insert into jugadores (nombre, apellido, nacionalidad, fecha_nac, nickname, rol, sueldo, cod_equipo)
values ('parii', 'garcia', 'Argentina', to_date('1995-05-15', 'yyyy-mm-dd'), 'parii_win', 'centinela', 1500, 1);


update jugadores 
set sueldo = 2000 
where cod_jugador = 100;

-- Pruebas para el trigger tr_numero_jugadores_max

select *
from jugadores;

-- Fallos

insert into jugadores ( nombre, apellido, nacionalidad, fecha_nac, nickname, rol, sueldo, cod_equipo)
values ('juan', 'perez', 'española', to_date('2000-01-01', 'yyyy-mm-dd'), 'jp99', 'centinela', 1500, 1);

update jugadores
set cod_equipo = 1
where cod_jugador = 30;
 /*
 
 
Error que empieza en la línea: 75 del comando -
update jugadores
set cod_equipo = 1
where cod_jugador = 30
Error en la línea de comandos : 75 Columna : 8
Informe de error -
Error SQL: ORA-20001: error: Operación cancelada. Ningún equipo puede tener más de 6 jugadores.
ORA-06512: en "EQDAW01.TR_NUMERO_JUGADORES_MAX", línea 12
ORA-04088: error durante la ejecución del disparador 'EQDAW01.TR_NUMERO_JUGADORES_MAX'

https://docs.oracle.com/error-help/db/ora-20001/

More Details :
https://docs.oracle.com/error-help/db/ora-20001/
https://docs.oracle.com/error-help/db/ora-06512/
https://docs.oracle.com/error-help/db/ora-04088/
*/

-- Exitos
insert into jugadores ( nombre, apellido, nacionalidad, fecha_nac, nickname, rol, sueldo, cod_equipo)
values ( 'alana', 'torres', 'española', to_date('1995-05-15', 'yyyy-mm-dd'), 'alana', 'centinela', 1500, 3);

select * 
from jugadores 
where nickname = 'alana';

update jugadores
set cod_equipo = 3
where cod_jugador = 10;

select cod_jugador, nombre, apellido, cod_equipo 
from jugadores 
where cod_jugador = 10;

-- Pruebas para el trigger tr_numero_jugadores_min

-- Fallos

delete from jugadores
where cod_jugador = 30;

-- Esto tiene que dar el error de "No hay jugadores en ningún equipo."
update competiciones 
set estado = 'cerrado' 
where cod_comp = 1;

/*

Error que empieza en la línea: 115 del comando -
update competiciones 
set estado = 'cerrado' 
where cod_comp = 1
Error en la línea de comandos : 115 Columna : 8
Informe de error -
Error SQL: ORA-20006: No se puede cerrar la etapa de inscripciones. Hay equipos que tienen menos de 2 jugadores.
ORA-06512: en "EQDAW01.TR_NUMERO_JUGADORES_MIN", línea 12
ORA-04088: error durante la ejecución del disparador 'EQDAW01.TR_NUMERO_JUGADORES_MIN'

https://docs.oracle.com/error-help/db/ora-20006/

More Details :
https://docs.oracle.com/error-help/db/ora-20006/
https://docs.oracle.com/error-help/db/ora-06512/
https://docs.oracle.com/error-help/db/ora-04088/


*/

-- Exitos

insert into jugadores ( nombre, apellido, nacionalidad, fecha_nac, nickname, rol, sueldo, cod_equipo) 
values ('Carlos', 'López', 'España', sysdate, 'Carlitos', 'iniciador', 1500, 2);

select * 
from jugadores 
where nickname = 'carlitos';

update competiciones 
set estado = 'cerrado' 
where cod_comp = 1;

select cod_comp, nombre, estado 
from competiciones 
where cod_comp = 1;


-- Pruebas para los triggers tr_bloqueo_jugadores y tr_bloqueo_equipos

-- Fallos

select *
from partidos;

insert into jornadas (fecha_inicio, cod_comp) 
values (sysdate, 1);

insert into partidos (num_jornada, hora) 
values ( 1, current_timestamp);

insert into equipos ( nombre, fecha_fundacion) 
values ( 'equipo bloqueado', sysdate);

delete from equipos 
where cod_equipo = 80;


/*

Error que empieza en la línea: 164 del comando -
insert into equipos (cod_equipo, nombre, fecha_fundacion) 
values (90, 'equipo bloqueado', sysdate)
Error en la línea de comandos : 164 Columna : 13
Informe de error -
Error SQL: ORA-20011: error: el calendario ya está generado. no se pueden añadir ni eliminar equipos.
ORA-06512: en "EQDAW01.TR_BLOQUEO_EQUIPOS", línea 8
ORA-04088: error durante la ejecución del disparador 'EQDAW01.TR_BLOQUEO_EQUIPOS'

https://docs.oracle.com/error-help/db/ora-20011/

More Details :
https://docs.oracle.com/error-help/db/ora-20011/
https://docs.oracle.com/error-help/db/ora-06512/
https://docs.oracle.com/error-help/db/ora-04088/

*/

-- Exitos


insert into equipos ( nombre, fecha_fundacion) 
values ('equipo de prueba', sysdate);

select * 
from equipos 
where nombre = 'equipo de prueba';

insert into jugadores ( nombre, apellido, nacionalidad, fecha_nac, nickname, rol, sueldo, cod_equipo) 
values ('prueba', 'exito', 'españa', sysdate, 'exito_player', 'centinela', 1500, 80);

select * 
from jugadores 
where nickname = 'exito_player';

update jugadores 
set cod_equipo = 2
where cod_jugador = 20;

select * from jugadores where cod_jugador = 20;

delete from jugadores 
where cod_jugador = 20;

select * from jugadores where cod_jugador = 20;


-- Pruebas para el trigger tr_equipos_pares

-- Fallos


update competiciones 
set estado = 'Cerrado' 
where cod_comp = 1;

/*

Error que empieza en la línea: 215 del comando :
update competiciones 
set estado = 'Cerrado' 
where cod_comp = 1
Informe de error -
ORA-20020: Error: no se puede cerrar la competición. el número total de equipos (5) es impar, debe ser par.
ORA-06512: en "EQDAW01.TR_EQUIPOS_PARES", línea 9
ORA-04088: error durante la ejecución del disparador 'EQDAW01.TR_EQUIPOS_PARES'

https://docs.oracle.com/error-help/db/ora-20020/

More Details :
https://docs.oracle.com/error-help/db/ora-20020/
https://docs.oracle.com/error-help/db/ora-06512/
https://docs.oracle.com/error-help/db/ora-04088/

*/


-- Exitos

insert into equipos ( nombre, fecha_fundacion) 
values ('equipo 2', sysdate);

select * from equipos where nombre = 'equipo 2';

update competiciones 
set estado = 'Cerrado' 
where cod_comp = 1;

select cod_comp, nombre, estado 
from competiciones 
where cod_comp = 1;








