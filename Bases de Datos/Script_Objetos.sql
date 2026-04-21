-- Autor Equipo 1: Unax Gonzales, Urko Lopez, Fatima Din, Eathan Garzon

drop table jugadores cascade constraints;
drop table equipos cascade constraints;
drop table competiciones cascade constraints;
drop table jornadas cascade constraints;
drop table partidos cascade constraints;
drop table resultados cascade constraints;
drop table perfiles cascade constraints;

create table equipos(
    cod_equipo number(3) generated always as identity, 
    nombre varchar2(55),
    fecha_fundacion date,
    constraint equi_cod_pk primary key (cod_equipo)
);

create table jugadores (
    cod_jugador   number(3) generated always as identity,
    nombre        varchar2(55),
    apellido      varchar2(55),
    nacionalidad  varchar2(55),
    fecha_nac     date,
    nickname      varchar2(55),
    rol           varchar2(20),
    sueldo        number,
    cod_equipo    number,
    constraint jug_cod_pk primary key (cod_jugador),
    constraint jug_rol_ck 
        check (rol in ('duelista', 'iniciador', 'controlador', 'centinela')),
    constraint jug_cod_equip_fk foreign key (cod_equipo) 
        references equipos(cod_equipo),
    constraint jug_nickname_uq unique (nickname)
);

create table competiciones(
    cod_comp number(3) generated always as identity,
    nombre varchar2(55),
    estado varchar2(7),
    premio number,
    constraint comp_cod_pk primary key (cod_comp),
    constraint comp_estado_ck check(estado in ('abierto','cerrado'))
);


create table jornadas (
    num_jornada number(3) generated always as identity,
    fecha_inicio date,
    cod_comp number,
    constraint jor_num_pk primary key (num_jornada),
    constraint jor_cod_comp_fk foreign key (cod_comp)
    references competiciones(cod_comp)
);

create table partidos(
    cod_partido number(3) generated always as identity,
    num_jornada number(3),
    hora TIMESTAMP,
    constraint part_cod_pk primary key (cod_partido),
    constraint part_num_jorn_fk foreign key (num_jornada)
    references jornadas(num_jornada)
);

create table resultados(
    cod_partido number(3),
    cod_equipo number(3),
    resultado number(2),
    constraint resul_cod_partido_cod_equipo_pk primary key 
    (cod_partido,cod_equipo),
    constraint resul_cod_partido_fk foreign key (cod_partido)
    references partidos(cod_partido),
    constraint resul_cod_equipo_fk foreign key (cod_equipo)
    references equipos(cod_equipo)
);

create table perfiles(
    cod_perfil number(3) generated always as identity,
    nombre varchar2(55),
    password varchar2(55),
    tipo varchar2(55),
    constraint per_cod_pk primary key (cod_perfil),
    constraint per_nombre_uq unique (nombre),
    constraint per_tipo_ck check(tipo in ('usuario','administrador'))
);
/*
creamos la vista para utilizarla en el procedimiento informe_jugadores
y que la select dentro del procedimiento este mas simplificada
*/
create or replace view datos_jugadores as
select j.nombre, j.apellido, j.rol, j.sueldo, e.nombre as nombre_equipo
from jugadores j join equipos e 
on j.cod_equipo = e.cod_equipo;

/*
creamos la vista para utilizarla en el procedimiento pr_informe_equipos
y que la select dentro del procedimiento este mas simplificada
*/

create or replace view vs_sueldos_numeros_equipos as
select e.nombre Equipo, e.fecha_fundacion Fundacion, 
        count(j.cod_jugador) Numero_jugadores,max(j.sueldo) Max_sueldo, 
        min(j.sueldo) Min_sueldo, round(avg(j.sueldo),2) Media_sueldos   
from equipos e join jugadores j
on j.cod_equipo = e.cod_equipo
group by e.nombre, e.fecha_fundacion 
;


--creamos la vista para utilizarla en el procedimiento informe_jugadores
--y que la select dentro del procedimiento esté mas simplificada

create or replace view datos_jugadores as
select j.nombre, j.apellido, j.rol, j.sueldo, e.nombre as nombre_equipo
from jugadores j join equipos e 
on j.cod_equipo = e.cod_equipo;


-- Creamo las vistas para almacenar el numero de victoria y derrotas

create or replace view victorias as
    select e.nombre as nombre_equipo, count(*) as victorias
    from equipos e
    join resultados r1 on e.cod_equipo = r1.cod_equipo
    join resultados r2 on r1.cod_partido = r2.cod_partido 
        and r1.cod_equipo != r2.cod_equipo
    where r1.resultado > r2.resultado
    group by e.nombre;

create or replace view derrotas as
    select e.nombre as nombre_equipo, count(*) as derrotas
    from equipos e
    join resultados r1 on e.cod_equipo = r1.cod_equipo
    join resultados r2 on r1.cod_partido = r2.cod_partido 
        and r1.cod_equipo != r2.cod_equipo
    where r1.resultado < r2.resultado
    group by e.nombre;

create or replace view datos_victorias_derrotas as
    select 
        e.nombre as nombre_equipo, 
        nvl(v.victorias, 0) as victorias, 
        nvl(d.derrotas, 0) as derrotas
    from equipos e
    left join victorias v on e.nombre = v.nombre_equipo
    left join derrotas d on e.nombre = d.nombre_equipo
    order by victorias desc;

commit;






