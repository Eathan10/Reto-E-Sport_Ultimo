-- Autor Equipo 1: Unax Gonzales, Urko Lopez, Fatima Din, Eathan Garzon



--Inserts tabla equipos

insert into equipos (nombre, fecha_fundacion) 
values ('leones', to_date('01-01-2014', 'dd-mm-yyyy'));

insert into equipos ( nombre, fecha_fundacion) 
values ('fantasmas', to_date('14-03-2004', 'dd-mm-yyyy'));

insert into equipos ( nombre, fecha_fundacion) 
values ('leviatan', to_date('19-03-2020', 'dd-mm-yyyy'));

insert into equipos ( nombre, fecha_fundacion) 
values ('Los Invencibles', sysdate);

--Inserts tabla jugadores

insert into jugadores ( nombre, apellido, nacionalidad, fecha_nac, nickname, rol, sueldo, cod_equipo)
values ( 'juan', 'perez', 'mexicano', to_date('15-03-1998', 'dd-mm-yyyy'), 'jp-pro', 'duelista', 2500, 1);

insert into jugadores ( nombre, apellido, nacionalidad, fecha_nac, nickname, rol, sueldo, cod_equipo)
values ( 'carlos', 'rodriguez', 'español', to_date('22-07-2000', 'dd-mm-yyyy'), 'charly-r', 'iniciador', 2800, 1);

insert into jugadores ( nombre, apellido, nacionalidad, fecha_nac, nickname, rol, sueldo, cod_equipo)
values ( 'luis', 'garcia', 'argentino', to_date('10-01-1995', 'dd-mm-yyyy'), 'lucho-smoke', 'controlador', 3200, 2);

insert into jugadores ( nombre, apellido, nacionalidad, fecha_nac, nickname, rol, sueldo, cod_equipo)
values ( 'ana', 'martinez', 'colombiana', to_date('05-06-1999', 'dd-mm-yyyy'), 'anam-trap', 'centinela', 3000, 2);

insert into jugadores ( nombre, apellido, nacionalidad, fecha_nac, nickname, rol, sueldo, cod_equipo)
values ( 'diego', 'lopez', 'chileno', to_date('18-11-2002', 'dd-mm-yyyy'), 'diegox', 'duelista', 2700, 3);

insert into jugadores ( nombre, apellido, nacionalidad, fecha_nac, nickname, rol, sueldo, cod_equipo)
values ( 'maria', 'sanchez', 'peruana', to_date('30-09-1997', 'dd-mm-yyyy'), 'mary-spot', 'iniciador', 2900, 3);

insert into jugadores ( nombre, apellido, nacionalidad, fecha_nac, nickname, rol, sueldo, cod_equipo)
values ( 'jhonda', 'ozpina', 'mexicano', to_date('15-03-2008', 'dd-mm-yyyy'), 'jondas', 'duelista', 2590, 1);

insert into jugadores (nombre, apellido, nacionalidad, fecha_nac, nickname, rol, sueldo, cod_equipo)
values ( 'xemelas', 'torres', 'estadounidense', to_date('11-09-2001', 'dd-mm-yyyy'), 'lol', 'iniciador', 2590, 1);

insert into jugadores ( nombre, apellido, nacionalidad, fecha_nac, nickname, rol, sueldo, cod_equipo)
values ( 'lola', 'garcia', 'estadounidense', to_date('01-09-2021', 'dd-mm-yyyy'), 'elmejor', 'iniciador', 2590, 1);



-- Inserts para competicion

insert into competiciones ( nombre, estado) 
values ( 'liga de leyendas 2024', 'abierto');

-- Inserts para jornada

insert into jornadas ( fecha_inicio, cod_comp)
values (to_date('20-04-2024', 'dd-mm-yyyy'), 2);

-- Inserts para partido

insert into partidos (num_jornada, hora)
values (2, to_date('20-04-2024', 'dd-mm-yyyy'));


-- Inserts para resultados


insert into resultados (cod_partido, cod_equipo, resultado)
values (2, 1, 13);


insert into resultados (cod_partido, cod_equipo, resultado)
values (2, 2, 8);



-- Inserts para Perfiles


insert into perfiles (nombre,password,tipo) values ('admin1','12345','administrador');

insert into perfiles (nombre,password,tipo) values ('usuario1','12345','usuario');


















