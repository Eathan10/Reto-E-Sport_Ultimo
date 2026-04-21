
import Modelo.*;
import Nacionalidades.Pais;

import javax.swing.JOptionPane;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Date;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    private static ArrayList<Equipo> listaEquipos = new ArrayList<>();
    private static ArrayList<Jugador> listaJugadores = new ArrayList<>();


    private static Competicion competicioActual;

    public static void main(String[] args) {

        if (competicioActual != null) {
            generarCompeticion();
        }
        //MENU GENERAL DONDE SE TENDRA Q ELEGIR O ADMIN O USUARIO, EL ADMIN LUEGO ES EL Q MODIFICARA JUGADOR O QUIOPO, LO Q EL ESCOJA
        String entrada = "";
        do {
            try {

                 entrada = validarSolicitarDatos(
                        "Opcion del menu","-------------- CONSULTORIA E-SPORT ----------------\n"
                                 +"1 - ADMINISTRADOR\n"
                                + "2 - USUARIO\n"
                                + "3 - SALIR\n"
                                + "Introduce una opción: ","[1-3]");

                switch (entrada) {
                    case "1":
                        administrador();
                        break;
                    case "2":
                        usuario();
                        break;
                    case "3":
                        JOptionPane.showMessageDialog(null, "saliendo del programa");
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "La opcion elegida no es valida ");
                        break;
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "ERROR. La opcion no es valida ");
            }

        } while (!entrada.equalsIgnoreCase("3"));
    }

    private static boolean inscripcionesAbiertas() {
        if (competicioActual == null) return true; // O false, según prefieras
        return competicioActual.getEstado().equalsIgnoreCase("Abierto");
    }

    // MENU DEL ADMIN DONDE PODRA ELEGIR Q QUIERE M,ODIFICAR SI JUGADOR O EQUIPO
    private static void administrador() {
        String entrada = "";
        do {
            try{

                entrada = validarSolicitarDatos(
                        "opcion del menu Administrador", "---------MENU ADMINISTRADOR---------"
                                + "1- JUGADOR\n"
                                + "2- EQUIPO\n"
                                + "3- CERRAR INSCRIPCIONES (Generar Partidos)\n"
                                + "4- REGISTRAR RESULTADOS\n" // <--- Nueva opción
                                + "5- SALIR\n"
                                + "Introduce una opción: ", "[1-5]");


                switch (entrada) {
                    case "1": // JUGADOR
                        if (inscripcionesAbiertas()) {
                            jugador();
                        } else {
                            JOptionPane.showMessageDialog(null, "BLOQUEADO: Las inscripciones están cerradas. No se pueden modificar jugadores.");
                        }
                        break;
                    case "2": // EQUIPO
                        if (inscripcionesAbiertas()) {
                            equipo();
                        } else {
                            JOptionPane.showMessageDialog(null, "BLOQUEADO: Las inscripciones están cerradas. No se pueden modificar equipos.");
                        }
                        break;
                    case "3":
                        cerrarInscripcionesYempezarEnfrentamiendtos();
                        break;
                    case "4":
                        registrarResultados();
                        break;
                    case "5":
                        JOptionPane.showMessageDialog(null, "Saliendo...");
                        break;
                }


            }catch(Exception e){
                JOptionPane.showMessageDialog(null, "ERROR");
            }
        }while(!entrada.equalsIgnoreCase("4"));

    }

    //MENU DE JUGADOR EN CASO DE EN LA ANTERIOR OOCION HABER ELEGIDO JUGADOR. DENTRO DEL ADMINISTRADOR EN SU MENU ELEGIR JUGADOR
    private static void jugador() {
        String opcion = "";
        do {
            try{
                opcion = validarSolicitarDatos(
                        "Menu de jugadores", "-------JUGADORES-------"
                                + "\n 1 - ALTA"
                                + "\n 2 - BAJA"
                                + "\n 3 - MODIFICAR"
                                + "\n 4 - Ver Jugadores"
                                + "\n 5 - SALIR"
                                + "\n Introduce una opción: ", "[1-4]");
                switch (opcion) {
                    case "1":
                        altaJugador();
                        break;
                    case "2":
                        bajaJugador();
                        break;
                    case "3":
                        modificarJugador();
                        break;
                    case "4":
                        verJugadores();
                        break;
                    case "5":
                        JOptionPane.showMessageDialog(null, "saliendo");
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "La opcion no es valida ");
                        break;
                }
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, "Error");
            }
        }while(!opcion.equalsIgnoreCase("5"));
    }


    private static void altaEquipo() {
        String respuesta = "";
        do {
            try {
                String nombreEquipo = validarSolicitarDatos("Nombre del equipo", "Ingrese el nombre del EQUIPO", "");
                String codEquipo = validarSolicitarDatos("Codigo del equipo", "Ingrese el codigo del EQUIPO", "");
                LocalDate fechaFundacion = solicitarValidarFechas("Fecha de fundación", "Ingrese la fecha de fundación");

                int numJugadores = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la cantidad de jugadores del equipo"));




                for (int i = 0; i < numJugadores; i++) {

                    altaJugador();
                    JOptionPane.showMessageDialog(null, "Se ha completado la plantilla");

                }

                Equipo equipo = new Equipo();
                equipo.setNombreEquipo(nombreEquipo);
                equipo.setCodEquipo(codEquipo);
                equipo.setFechaFundacion(fechaFundacion);


                listaEquipos.add(equipo);

                JOptionPane.showMessageDialog(null,
                        "------------EQUIPO CREADO-------------"
                                + "\n Nombre: " + nombreEquipo
                                + "\n Codigo de equipo: " + codEquipo
                                + "\n Fecha de fundación: " + fechaFundacion
                                + "\n Plantilla:\n " + listaJugadores);

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "ERROR");
            }

            boolean respuestaValida = false;
            do {
                respuesta = JOptionPane.showInputDialog("Desea guardar los datos? Ingrese SI o NO");

                if (!respuesta.equalsIgnoreCase("SI") || !respuesta.equalsIgnoreCase("NO")) {
                    JOptionPane.showMessageDialog(null, "Valor introducido erroneo");
                    respuestaValida = false;
                } else {
                    respuestaValida = true;
                }
            }while(!respuestaValida);

        }while(!respuesta.equalsIgnoreCase("SI"));
    }


    private static void registrarResultados() {

        if (competicioActual != null && !competicioActual.getJornadas().isEmpty()) {


            Jornada jornadaActual = competicioActual.getJornadas().get(0);


            for (Partido p : jornadaActual.getPartidos()) {
                try {

                    String info = "Partido ID: " + p.getId() + "\n" +
                            p.getEquipoLocal().getNombreEquipo() + " vs " + p.getEquipoVisitante().getNombreEquipo();


                    int resLocal = Integer.parseInt(JOptionPane.showInputDialog(null,
                            info + "\n\nIntroduce goles de " + p.getEquipoLocal().getNombreEquipo() + ":"));


                    int resVisitante = Integer.parseInt(JOptionPane.showInputDialog(null,
                            info + "\n\nIntroduce goles de " + p.getEquipoVisitante().getNombreEquipo() + ":"));


                    p.setResultadoLocal(resLocal);
                    p.setResultadoVisitante(resVisitante);

                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Error: Introduce un número entero válido.");

                }
            }
            JOptionPane.showMessageDialog(null, "Todos los resultados han sido registrados.");

        } else {
            JOptionPane.showMessageDialog(null, "No hay partidos generados. Primero cierra las inscripciones.");
        }
    }


    private static void altaJugador() {
        String respuesta = "";
        do {
            try {
                String nombreJugador = validarSolicitarDatos("Nombre del jugados", "Ingrese el nombre del JUGADOR", "^[A-Z]([a-z]+)$");
                String apellidoJugador = validarSolicitarDatos("Apellido del jugador", "Ingrese el Apellido del JUGADOR", "^[A-Z]([a-z]+)( [A-Za-z][a-z]+)*$");
                String nickname = validarSolicitarDatos("Nickname", "Ingrese el nickname JUGADOR", "^[A-Za-z0-9áéíóúÁÉÍÓÚ@_!$_><.*?¿/·#,; -]+$");
                String rolJugador = validarSolicitarDatos("Rol del jugador", "Ingrese el rol del jugador: Centinela / Controlador / Iniciador / Duelista)", "^(Centinela|Controlador|Iniciador|Duelista)$");
                LocalDate fechaNacimiento = solicitarValidarFechas("Fecha de nacimiento", "Ingrese la fecha de nacimiento en fotrmato dd/MM/yyyy");


                LocalDate fecha = LocalDate.now();
                Period periodo = Period.between(fechaNacimiento, fecha);
                int edad = periodo.getYears();
                JOptionPane.showMessageDialog(null, "La Edad del JUGADOR es de: " + edad);

                String nacionalidadjugador = validarNacionalidad("Nacionalidad del jugador", "Ingrese la nacionalidad del JUGADOR", "^[A-Za-záéíóúÁÉÍÓÚñÑ ]+$");

                double sueldo = validarSueldo("Sueldo", "Ingrese el sueldo del JUGADOR");


                Jugador jugador = new Jugador();
                jugador.setNombre(nombreJugador);
                jugador.setApellido(apellidoJugador);
                jugador.setNickname(nickname);
                jugador.setNacionalidad(nacionalidadjugador);
                jugador.setSueldo(sueldo);
                jugador.setRol(rolJugador);
                jugador.setFecha_nac(fechaNacimiento);

                listaJugadores.add(jugador);

                JOptionPane.showMessageDialog(null,
                        "------------JUGADOR CREADO-------------"
                                + "\n Nickname: " + nickname
                                + "\n Nombre: " + nombreJugador
                                + "\n Apellido:  " + apellidoJugador
                                + "\n Rol: " + rolJugador
                                + "\n Fecha de nacimiento: " + fechaNacimiento
                                + "\n Edad: " + edad
                                + "\n Nacionalidad: " + nacionalidadjugador
                                + "\n Sueldo: " + sueldo);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "ERROR");
            }

            boolean respuestaValida = false;
            do {
                respuesta = JOptionPane.showInputDialog("Desea guardar los datos? Ingrese SI o NO");

                if (!respuesta.equalsIgnoreCase("SI") || !respuesta.equalsIgnoreCase("NO")) {
                    JOptionPane.showMessageDialog(null, "Valor introducido erroneo");
                    respuestaValida = false;
                } else {
                    respuestaValida = true;
                }
            }while(!respuestaValida);
            JOptionPane.showMessageDialog(null, listaJugadores);

        }while(respuesta.equalsIgnoreCase("SI"));
    }

    private static void bajaJugador() {
        if (listaJugadores.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay Jugadores registrados pasra darles de baja");
        }else {
            boolean jugadorEncontrado = false;
            Jugador jugadorEliminado = null;

            do {
                String nombreJugador = JOptionPane.showInputDialog("Introduce el nombre del jugador para dar de baja");

                for (Jugador jugador : listaJugadores) {
                    if (jugador.getNombre().equalsIgnoreCase(nombreJugador)) {
                        jugadorEncontrado = true;
                        jugadorEliminado = jugador;
                    }
                }

                if (jugadorEncontrado) {
                    listaJugadores.remove(jugadorEliminado);
                } else {
                    JOptionPane.showMessageDialog(null, "Ese jugador no existe dentro de la lista de jugadores. Vuelve a intentarlo");
                }
            } while (!jugadorEncontrado);
        }

        JOptionPane.showMessageDialog(null, listaJugadores);
    }

    private static void modificarJugador() {
        if (listaJugadores.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay jugadores registrados para modificar.");
        }else{
            boolean jugadorEncontrado = false;
            do {
                Jugador jugadorModificar = null;
                String nombreJugador = validarSolicitarDatos("Nombre del jugador","Introduce el nombre del jugador a modificar","^[a-zA-Z]+$");
                String apellidoJugador = validarSolicitarDatos("Apellido del jugador","Introduce el apellido de ese jugador que quieres modificar: ","^[a-zA-Z]+$");

                if (nombreJugador == null) {
                    jugadorEncontrado = true;

                } else {
                    for (Jugador jugador : listaJugadores) {
                        if (jugador.getNombre().equalsIgnoreCase(nombreJugador) && jugador.getApellido().equalsIgnoreCase(apellidoJugador)) {
                            jugadorEncontrado = true;
                            jugadorModificar = jugador;
                        }
                    }


                    if (jugadorEncontrado) {
                        String respuesta = "";
                        do {
                            try {
                                String modificar = JOptionPane.showInputDialog("Introduce lo que le quieres modificar: ");

                                if (modificar.equalsIgnoreCase("Nombre")) {
                                    String nuevoJugador = validarSolicitarDatos("Nombre del jugados", "Ingrese el nombre del JUGADOR", "^[A-Z]([a-z]+)$");
                                    jugadorModificar.setNombre(nuevoJugador);
                                } else if (modificar.equalsIgnoreCase("Apellido")) {
                                    String nuevoApellido = validarSolicitarDatos("Apellido del jugador", "Ingrese el Apellido del JUGADOR", "^[A-Z]([a-z]+)( [A-Za-z][a-z]+)*$");
                                    jugadorModificar.setApellido(nuevoApellido);


                                } else if (modificar.equalsIgnoreCase("Sueldo")) {
                                    double nuevoSueldo = validarSueldo("Sueldo", "Ingrese el sueldo del JUGADOR");
                                    jugadorModificar.setSueldo(nuevoSueldo);

                                } else if (modificar.equalsIgnoreCase("Nacionalidad")) {
                                    String nuevaNacionalidadjugador = validarNacionalidad("Nacionalidad del jugador", "Ingrese la nacionalidad del JUGADOR", "^[A-Za-záéíóúÁÉÍÓÚñÑ ]+$");
                                    jugadorModificar.setNacionalidad(nuevaNacionalidadjugador);

                                } else if (modificar.equalsIgnoreCase("fecha_nac")) {
                                    LocalDate nuevaFechaNacimiento = solicitarValidarFechas("Fecha de nacimiento", "Ingrese la fecha de nacimiento en fotrmato dd/MM/yyyy");

                                    LocalDate fecha = LocalDate.now();
                                    Period periodo = Period.between(nuevaFechaNacimiento, fecha);
                                    int nuevaEdad = periodo.getYears();
                                    JOptionPane.showMessageDialog(null, "La Edad del JUGADOR es de: " + nuevaEdad);

                                    jugadorModificar.setFecha_nac(nuevaFechaNacimiento);

                                } else if (modificar.equalsIgnoreCase("nickname")) {
                                    String nuevoNickname = validarSolicitarDatos("Nickname", "Ingrese el nickname JUGADOR", "^[A-Za-z0-9áéíóúÁÉÍÓÚ@_!$_><.*?¿/·#,; -]+$");
                                    jugadorModificar.setNickname(nuevoNickname);

                                }else if(modificar.equalsIgnoreCase("rol")) {
                                    String rolJugador = validarSolicitarDatos("Rol del jugador", "Ingrese el rol del jugador: Centinela / Controlador / Iniciador / Duelista)", "^(Centinela|Controlador|Iniciador|Duelista)$");
                                    jugadorModificar.setRol(rolJugador);
                                }

                                boolean respuestaValida = false;
                                do {
                                    respuesta = JOptionPane.showInputDialog("Desea modificar algo más: Si/No ?");

                                    if (respuesta.equalsIgnoreCase("Si") || respuesta.equalsIgnoreCase("No")) {
                                        respuestaValida = true;
                                    } else {
                                        JOptionPane.showMessageDialog(null, "Valor introducido erróneo. Vuelve a intentarlo.");
                                    }
                                }while(!respuestaValida);

                            } catch (Exception e) {
                                JOptionPane.showMessageDialog(null, "ERROR");
                            }
                        }while(respuesta.equalsIgnoreCase("SI"));

                    } else {
                        JOptionPane.showMessageDialog(null, "El jugador" + nombreJugador + "no existe");
                    }
                }
            }while(!jugadorEncontrado);

            verJugadores();
        }
    }

    //MENU DE EQUIPO EN CASO DE HABER ELEIGO EN LA ANTERIOR OPCION EQUIPO. DENTRO DEL ADMINISTRADOR EN SU MENU ELEGIR EQUIPO
    private static void equipo() {
        String opcion = "";
        do {
            try{
                opcion = validarSolicitarDatos(
                        "Menu de jugadores", "-------EQUIPOS-------"
                                + "\n 1 - ALTA"
                                + "\n 2 - BAJA"
                                + "\n 3 - MODIFICAR"
                                + "\n 4 - VER EQUIPOS"
                                + "\n 5 - SALIR"
                                + "\n Seleccione una opción: ", "[1-4]");
                switch (opcion) {
                    case "1":
                        altaEquipo();
                        break;
                    case "2":
                        bajaEquipo();
                        break;
                    case "3":
                        modificarEquipo();
                        break;
                    case "4":
                        verEquipos();
                        break;
                    case "5":
                        JOptionPane.showMessageDialog(null, "saliendo");
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "La opcion introducida no es valida ");
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error");
            }
        }while (!opcion.equalsIgnoreCase("5"));
    }



    private static void bajaEquipo() {
        if (listaEquipos.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay equipos registrados para darlos de baja");
        } else {
            Equipo equipoEliminado = null;
            do {
                String nombreEquipo = JOptionPane.showInputDialog("Introduce el nombre del equipo que quieras dar de baja");

                for (Equipo equipo : listaEquipos) {

                    if (equipo.getNombreEquipo().equalsIgnoreCase(nombreEquipo)) {
                        equipoEliminado = equipo;
                        break;
                    }
                }

                if (equipoEliminado != null) {
                    listaEquipos.remove(equipoEliminado);
                    JOptionPane.showMessageDialog(null, "Equipo eliminado .");
                } else {
                    JOptionPane.showMessageDialog(null, "Ese equipo no existe. Vuelve a intentarlo");
                }
            } while (equipoEliminado == null);
        }
    }

    private static void modificarEquipo() {


        try{


        if (listaEquipos.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay ningun equipo registrado.");
            return;
        }

        String nombreBuscar = JOptionPane.showInputDialog("Ingrese el nombre del equipo que quiere modificar.");
        Equipo equipoEncontrado = null;

        for (Equipo equipo : listaEquipos) {
            if (equipo.getNombreEquipo().equalsIgnoreCase(nombreBuscar)) {
                equipoEncontrado = equipo;
                break;
            }
        }

        if (equipoEncontrado == null) {
            JOptionPane.showMessageDialog(null, "Equipo no encontrado");


        }else {String opcion = JOptionPane.showInputDialog("Que quieres cambiar del equipo?\"" +
                "A)Nombre del equipo \n" +
                "B)Fecha de fundación del equipo\n" +
                "C)Cancelar");

            if (opcion == null) return;

            switch (opcion){

                case "A":
                    String nombreNuevo = validarSolicitarDatos("Nuevo nombre", "Ingrese el nuevo nombre del equipo:" , "");
                    equipoEncontrado.setNombreEquipo(nombreNuevo);
                    JOptionPane.showMessageDialog(null, "Se ha actualizado el noombre del equipo");
                    break;

                case "B":
                    LocalDate fechaNueva = solicitarValidarFechas("Nueva fecha", "Ingrese la nueva fecha de fundación:");
                    equipoEncontrado.setFechaFundacion(fechaNueva);
                    JOptionPane.showMessageDialog(null, "Se ha actualizado la fecha de fundación del equipo");
                    break;

                case "C":
                    break;

            }

        }
        }catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error");
        }


    }


    public static void cerrarInscripcionesYempezarEnfrentamiendtos() {

        if (competicioActual != null) {


            if (listaEquipos.size() % 2 == 0 && !listaEquipos.isEmpty()) {


                Jornada jornada = new Jornada();
                jornada.setFecha_inicio(LocalDate.now());
                jornada.setCompeticion(competicioActual);
                competicioActual.getJornadas().add(jornada);

                List<Partido> partidosGenerados = new ArrayList<>();
                int contadorId = 1;

                for (int i = 0; i < listaEquipos.size(); i += 2) {
                    Equipo local = listaEquipos.get(i);
                    Equipo visitante = listaEquipos.get(i + 1);

                    String horaInput = JOptionPane.showInputDialog(null,
                            "Partido: " + local.getNombreEquipo() + " vs " + visitante.getNombreEquipo() +
                                    "\nIntroduce la hora (HH:mm):");

                    LocalTime hora = LocalTime.parse(horaInput);
                    partidosGenerados.add(new Partido(contadorId++, hora, 0, 0, jornada, local, visitante));
                }

                jornada.setPartidos(partidosGenerados);


                competicioActual.setEstado("Cerrado");
                JOptionPane.showMessageDialog(null, "Inscripciones cerradas con éxito.");

            } else {
                JOptionPane.showMessageDialog(null, "Error: Debe haber un número par de equipos registrados.");
            }

        } else {
            JOptionPane.showMessageDialog(null, "No existe ninguna competición activa que cerrar.");
        }
    }


    private static void generarCompeticion(){

        competicioActual = new Competicion();

        int idCompeticion = Integer.parseInt(validarSolicitarDatos("id","Ingrese el ID de la competicion","^[0-9]+$"));
        String estado = "Abierto";
        String nombre = validarSolicitarDatos("Nombre de la competicio","Introduce el nombre de la competicio","^[a-zA-Z0-9]+$");
        double premio = Double.parseDouble(validarSolicitarDatos("Premio","Introduce la cantidad del premio","^[0-9]+$"));

        competicioActual.setID(idCompeticion);
        competicioActual.setNombre(nombre);
        competicioActual.setPremio(premio);
        competicioActual.setEstado(estado);

        JOptionPane.showMessageDialog(null, "Competicion registrada correctamente");
    }





    // MENU DEL USUARIO DONDE PODRA ELEGIR ENTRE VER LOS EQUIPOS Y JUGADORES Q HAY , NO PODRA MADIFICARLOS
    private static void usuario() {
        String opcion = "";
        do {
            try {
                opcion = validarSolicitarDatos(
                        "Menu de usuario", "-------USUARIO-------\n" +
                                "\n 1 - VER EQUIPOS" +
                                "\n 2 - VER JUGADORES" +
                                "\n 3 - SALIR" +
                                "\n Seleccione una opción: ", "[1-3]");
                switch (opcion) {
                    case "1":
                        verEquipos();
                        break;
                    case "2":
                        verJugadores();
                        break;
                    case "3":
                        JOptionPane.showMessageDialog(null, "saliendo");
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "La opcion introducida no es valida ");
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error");
            }
        }while(!opcion.equalsIgnoreCase("4"));
    }

    private static void verEquipos() {
        if (listaEquipos.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay equipos registrados.");
        }else{
            JOptionPane.showMessageDialog(null, " ---------Lista EQUIPOS---------");
            String listaDeEquipos = "";
            for(Equipo equipo : listaEquipos){
                listaDeEquipos = listaDeEquipos + "\n ***Nombre***: " + equipo.getNombreEquipo();
                listaDeEquipos = listaDeEquipos + "\n Apellido: " + equipo.getCodEquipo();
                listaDeEquipos = listaDeEquipos + "\n Nacionalidad: " + equipo.getFechaFundacion();
            }

            JOptionPane.showMessageDialog(null, listaDeEquipos);

        }
    }

    private static void verJugadores() {
        if (listaJugadores.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay jugadores registrados en el sistema.");
        }else{
            JOptionPane.showMessageDialog(null,"---------Lista JUGADORES---------");
            String lista = "";
            for(Jugador jugador : listaJugadores){
                lista = lista + "\n ***Nombre***: " + jugador.getNombre();
                lista = lista + "\n Apellido: " + jugador.getApellido();
                lista = lista + "\n Nacionalidad: " + jugador.getNacionalidad();
                lista = lista + "\n Rol: " + jugador.getRol();
                lista = lista + "\n Sueldo: " + jugador.getSueldo();
                lista = lista + "\n Nickname: " + jugador.getNickname();
                lista = lista + "\n Codigo del jugador: " + jugador.getCod_jugador();
                lista = lista + "\n Fecha de nacimineto: " + jugador.getFecha_nac();
            }
            JOptionPane.showMessageDialog(null, lista);
        }
    }


    public static LocalDate solicitarValidarFechas(String dato, String mensaje) {
        String var = "";
        boolean error;
        DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate fecha = null;


        do {
            error = false;
            try {
                var = JOptionPane.showInputDialog(mensaje);


                if (var == null) {
                    throw new DatoNoValido("Tienes que introducir un valor para " + dato);
                }


                if (var.trim().isEmpty()) {
                    throw new DatoNoValido("La " + dato + " no puede estar vacia");
                }


                fecha = LocalDate.parse(var, formatoFecha);


            } catch (DateTimeParseException e) {
                error = true;
                JOptionPane.showMessageDialog(null, "La fecha no tiene un formato valido. Use DD/MM/YYYY", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (DatoNoValido e) {
                error = true;
                JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } while (error);


        return fecha;
    }

    public static String validarSolicitarDatos(String dato, String mensaje, String expresionRegular) {
        boolean error;
        String var = "";

        do {
            error = false;
            try {
                var = JOptionPane.showInputDialog(mensaje);

                if (var == null) {
                    throw new DatoNoValido("Tienes que introducir un valor para " + dato);
                }

                if (var.trim().isEmpty()) {
                    throw new DatoNoValido(dato + " no puede estar vacio");
                }

                Pattern patron = Pattern.compile(expresionRegular);
                Matcher mat = patron.matcher(var);

                if (!mat.matches()) {
                    throw new DatoNoValido(dato + " no tiene un formato valido");
                }

            } catch (Exception e) {
                error = true;
                JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } while (error);

        return var;
    }

    public static double validarSueldo(String dato, String mensaje) {
        double sueldoMinimoInterpersonal = 1184;
        String var = "";
        boolean error;
        double sueldo = 0;


        do {
            error = false;
            try {
                var = JOptionPane.showInputDialog(mensaje);


                if (var == null) {
                    throw new DatoNoValido("Tienes que introducir un valor para " + dato);
                }


                if (var.trim().isEmpty()) {
                    throw new DatoNoValido(dato + " no puede estar vacio");
                }


                sueldo = Double.parseDouble(var.replace(",", "."));


                if (sueldo < sueldoMinimoInterpersonal) {
                    throw new DatoNoValido(dato + " no puede ser menor que el Salario Mínimo Interprofesional (1184€)");
                }


            } catch (NumberFormatException e) {
                error = true;
                JOptionPane.showMessageDialog(null, dato + " debe ser un número válido", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (DatoNoValido e) {
                error = true;
                JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            } catch (Exception e) {
                error = true;
                JOptionPane.showMessageDialog(null, "Error inesperado: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } while (error);


        return sueldo;
    }

    public static String validarNacionalidad(String dato, String mensaje, String expresionRegular) {
        boolean error;
        String var = "";

        do {
            error = false;
            try {
                var = JOptionPane.showInputDialog(mensaje);

                if (var == null) {
                    throw new DatoNoValido("Tienes que introducir un valor para " + dato);
                }

                if (var.trim().isEmpty()) {
                    throw new DatoNoValido(dato + " no puede estar vacio");
                }

                Pattern patron = Pattern.compile(expresionRegular);
                Matcher mat = patron.matcher(var);

                if (!mat.matches()) {
                    throw new DatoNoValido(dato + " no tiene un formato valido");
                }

                if (Pais.esValido(var)){
                    throw new DatoNoValido("Nacionalidad NO encontrada");
                }

            } catch (Exception e) {
                error = true;
                JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } while (error);

        return var;
    }



}
