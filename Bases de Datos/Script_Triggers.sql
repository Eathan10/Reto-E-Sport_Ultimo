-- Autor Equipo 1: Unax Gonzales, Urko Lopez, Fatima Din, Eathan Garzon

-- Trigger 1 de sueldo

create or replace trigger tr_sueldo_jugador 
before insert or update of sueldo on jugadores
for each row
declare
    e_sueldo exception;

begin
    if :new.sueldo < 1221 then
        raise e_sueldo;
    end if;
exception 
    when e_sueldo then
        raise_application_error(-20001,'error: El sueldo del jugador no puede ser menor al SMI (1.221)');
end tr_sueldo_jugador;
------------------------------------------------------------


-- Trigger 2 maximo jugadores en un equipo

create or replace trigger tr_numero_jugadores_max
for insert or update of cod_equipo on jugadores
compound trigger

    v_cod_equipo jugadores.cod_equipo%type;
    e_equipo_lleno exception;

    before each row is
    begin
        v_cod_equipo := :new.cod_equipo;
    end before each row;

    after statement is
        v_count number;
    begin
        select count(*) into v_count
        from jugadores
        where cod_equipo = v_cod_equipo;
        
        if v_count > 6 then
            raise e_equipo_lleno;
        end if;
        
        exception 
    when e_equipo_lleno then
        raise_application_error(-20001,'Error: el equipo ya tiene 6 jugadores. no se puede agregar mas.');
    end after statement;
    

end tr_numero_jugadores_max;

--------------------------------------------------------


-- Trigger 3 validaciones cuando se cierra una competicion

create or replace trigger tr_validacion_cierre_competicion
before update of estado on competiciones
for each row
declare
    v_conteo_minimo  number;
    v_total_equipos  number;
    e_equip_invalidos exception;
    e_equipos_impares exception;
    e_sin_equipos     exception;
begin

    if :new.estado = 'cerrada' then 

        select count(*) into v_total_equipos
        from equipos;

        if v_total_equipos = 0 then
            raise e_sin_equipos;
        elsif mod(v_total_equipos, 2) != 0 then
            raise e_equipos_impares;
        end if;

        select min(count(*)) into v_conteo_minimo
        from jugadores
        group by cod_equipo;

        if v_conteo_minimo < 2 then
            raise e_equip_invalidos;
        end if;

    end if; 

exception
    when e_sin_equipos then
        raise_application_error(-20021, 'error: no se puede cerrar la competición. no hay ningún equipo registrado.');
    
    when e_equipos_impares then
        raise_application_error(-20020, 'error: no se puede cerrar la competición. el número total de equipos es impar, debe ser par.');
    
    when e_equip_invalidos then
        raise_application_error(-20006, 'error: no se puede cerrar la etapa de inscripciones. hay equipos que tienen menos de 2 jugadores.');
    
    when no_data_found then
        raise_application_error(-20009, 'error: no hay jugadores registrados.');
end tr_validacion_cierre_competicion;

---------------------------------------------------
-- Trigger 4 validaciones que no se 
create or replace trigger tr_validar_estado
before insert or update or delete on competiciones
for each row
declare
    e_competicion_bloqueada exception;
    e_competicion_cerrada_nueva exception;
    e_estado_invalido exception;
    pragma exception_init(e_competicion_bloqueada, -20020);
    pragma exception_init(e_competicion_cerrada_nueva, -20021);
    pragma exception_init(e_estado_invalido, -20022);
begin
    if updating or deleting then
        if :old.estado = 'cerrado' then
            raise e_competicion_bloqueada;
        end if;
    end if;
    
    if inserting then
        if :new.estado = 'cerrado' then
            raise e_competicion_cerrada_nueva;
        end if;
        if :new.estado not in ('abierto', 'cerrado') then
            raise e_estado_invalido;
        end if;
    end if;
exception
    when e_competicion_bloqueada then
        raise_application_error(-20020, 'error: no se puede modificar ni eliminar una competicion cerrada.');
    when e_competicion_cerrada_nueva then
        raise_application_error(-20021, 'error: no se puede crear una competicion con estado cerrado.');
    when e_estado_invalido then
        raise_application_error(-20022, 'error: el estado debe ser abierto o cerrado.');
    when others then
        raise_application_error(-20023, 'error: se ha producido un error inesperado en la competicion.');
end;




























