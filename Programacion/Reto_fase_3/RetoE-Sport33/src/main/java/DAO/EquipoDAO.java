package DAO;

import Modelo.Equipo;
import Modelo.Jugador;
import Utilidades.BaseDatos;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase de Acceso a Datos para la entidad Equipo, para realizar los cambion, altas y bajas que quieras
 * @author Urko
 * @version 1.0
 * @since 2026-04-15
 */
public class EquipoDAO {

    /**
     * Metodo para insertar un equipo nuevo en la base de dato, con su nombre, codigo y fecha de fundacion
     * 1. Inserta los datos básicos del equipo en la tabla equipos
     * 2. Recorre la lista de jugadores seleccionados y les asigna el nombre del equipo, para asignar jugadores a equipo
     *
     * @param equipo Objeto de tipo Equipo que contiene los datos y la lista de jugadores
     */
    public static void insertarEquipo(Equipo equipo) {
        //para el equipo
        String sqlEquipo = "INSERT INTO equipos (nombre, cod_equipo, fecha_fundacion) VALUES (?, ?, ?)";
        //para el asignar el equipo al jugaodr
        String sqlJugador = "UPDATE jugadores SET nombre_equipo = ? WHERE nombre_jugador = ?";
        try {
            Connection conn = BaseDatos.getConnection();


            // INSERTAR EQUIPO
            PreparedStatement psE = conn.prepareStatement(sqlEquipo);
            psE.setString(1, equipo.getNombreEquipo());
            psE.setString(2, equipo.getCodigoEquipo());
            psE.setDate(3, java.sql.Date.valueOf(equipo.getFechaFundacion()));
            psE.executeUpdate();
            System.out.println("Equipo insertado con éxito.");


            PreparedStatement psJ = conn.prepareStatement(sqlJugador);
            for (Jugador j : equipo.getJugadores()) {
                psJ.setString(1, equipo.getNombreEquipo()); // Les asignamos este equipo
                psJ.setString(2, j.getNombre());            // Usamos el nombre del jugador para buscarlo
                psJ.executeUpdate();
            }

            System.out.println("Equipo y sus " + equipo.getJugadores().length + " jugadores guardados.");
            BaseDatos.closeConnection();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Metodo para eliminar los equipos existentes de la base de datos, mediante su nombre para identificarlo
     *
     * @param nombreEquipo El nombre del equipo que quieres borrar
     */
    public static void borrarEquipo(String nombreEquipo) {
        String sql = "DELETE FROM equipos WHERE nombre = ?";

        try {
            Connection conn = BaseDatos.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, nombreEquipo);

            ps.executeUpdate();

            System.out.println("Equipo borrado correctamente.");
            BaseDatos.closeConnection();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Metodo para comrpobar si existen los equipos buscado por el nombre
     *
     * @param nombreEquipo El nombre del equipo que quieres comprobar
     * @return true si el equipo ya existe en la base de datos, false sino.
     */
    public static boolean comprobarExistencia(String nombreEquipo) {
        String sql = "SELECT COUNT(*) FROM equipos WHERE nombre = ?";

        try {
            Connection conn = BaseDatos.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, nombreEquipo);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int count = rs.getInt(1);
                return count > 0; // Devuelve true si el equipo existe, false si no
            }
            BaseDatos.closeConnection();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    /**
     * Metodo para actualizar los datos del equipo que quieras que ya exista en la base de datos
     * utilizando el nombre del equipo como clave de búsqueda.
     * * @param equipo Objeto con los datos actualizados.
     */
    public static void actualizarEquipo(Equipo equipo) {
        String sql = "UPDATE equipos SET cod_equipo = ?, fecha_fundacion = ? WHERE nombre = ?";

        try {
            Connection conn = BaseDatos.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, equipo.getCodigoEquipo());
            ps.setDate(2, java.sql.Date.valueOf(equipo.getFechaFundacion()));
            ps.setString(3, equipo.getNombreEquipo());

            ps.executeUpdate();

            System.out.println("Equipo actualizado correctamente.");
            BaseDatos.closeConnection();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    private static List<Equipo> listaEquipos = new ArrayList<>();

    /**
     * Recupera la lista completa de equipos
     * @return una lista que contiene todos los objetos equipos
     */
    public List<Equipo> obtenerListaEquipos() {   // Este método lo usará la ventana Ver Equipos
        return listaEquipos;
    }

    /**
     * Obtiene la lista completa de equipos
     * @return List de objetos Equipo con sus datos
     */
    public static List<Equipo> obtenerTodos() {
        List<Equipo> lista = new ArrayList<>();

        String sql = "SELECT * FROM equipos";

        try {
            Connection conn = BaseDatos.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Equipo e = new Equipo();
                e.setNombreEquipo(rs.getString("nombre"));
                e.setCodigoEquipo(rs.getString("codigo"));

                if (rs.getDate("fecha_fundacion") != null) {
                    e.setFechaFundacion(rs.getDate("fecha_fundacion").toLocalDate());
                }

                lista.add(e);
            }

        } catch (SQLException ex) {
            System.out.println("Error al listar equipos: " + ex.getMessage());
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }

        return lista;

    }

}
