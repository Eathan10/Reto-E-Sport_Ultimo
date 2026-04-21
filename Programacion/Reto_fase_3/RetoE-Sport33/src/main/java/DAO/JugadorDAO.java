package DAO;



import Modelo.Equipo;

import Modelo.Jugador;

import Utilidades.BaseDatos;



import java.sql.Connection;

import java.sql.PreparedStatement;

import java.sql.ResultSet;

import java.sql.SQLException;

import java.util.ArrayList;

import java.util.List;



/**

 * Clase de Acceso a Datos para la entidad Jugador, para realizar los cambion, altas, bajas que quieras, bucarlos y obtenerlos

 * @author Fatima

 * @version 1.0

 * @since 2026-04-16

 */

public class JugadorDAO {



    private final Connection conn;



    public JugadorDAO(Connection conn) {

        this.conn = conn;

    }

    public JugadorDAO() {
        this.conn = BaseDatos.getConnection();
    }

    /**

     * Metodo para inserta un nuevo jugador en la base de datos

     * @param jugador Objeto que contiene la informacion de jugador

     */



    public void insertarJugador(Jugador jugador) throws SQLException {

        String sql = "INSERT INTO jugadores (nombre, apellido, nacionalidad," +

                " fecha_nac, nickname, rol, sueldo, cod_equipo) VALUES (?, ?, ?, ?, ?, ?, ?, ? )";

        try (

                PreparedStatement ps = conn.prepareStatement(sql)) {



            ps.setString(1, jugador.getNombre());

            ps.setString(2, jugador.getApellido());

            ps.setString(3, jugador.getNacionalidad());

            ps.setDate(4, java.sql.Date.valueOf(jugador.getFechaNac()));

            ps.setString(5, jugador.getNickname());

            ps.setString(6, jugador.getRol());

            ps.setDouble(7, jugador.getSueldo());

            ps.setInt(8, Integer.parseInt(jugador.getEquipo().getCodigoEquipo()));

            ps.executeUpdate();



        }

    }



    /**

     * Metodo para eliminar un jugador mediante su nickname como identificador

     * @param nickname El apodo único del jugador que quieres eliminar

     * @throws Exception Si ocurre un error durante la conexión o la ejecución SQL

     */

    public void eliminarJugador(String nickname ) throws Exception {

        String sql = "DELETE FROM jugadores WHERE nickname = ?";

        try (

                PreparedStatement ps = conn.prepareStatement(sql)) {



            ps.setString(1, nickname);

            int filas = ps.executeUpdate();

            if (filas == 0) {

                throw new Exception("No se encontró el jugador con el nickname: " + nickname);

            }

        }

    }



    /**

     * Metodo para actualizar los datos de un jugador que ya existe

     * @param jugador Objeto con los datos actualizados.

     */

    public void actualizarJugador(Jugador jugador) throws SQLException {

        String sql = "UPDATE jugadores SET nombre = ?, apellido = ?, nacionalidad = ?, " +

                "fecha_nac = ?, rol = ?, sueldo = ?, cod_equipo = ? WHERE nickname = ?";

        try (

                PreparedStatement ps = conn.prepareStatement(sql)) {



            ps.setString(1, jugador.getNombre());

            ps.setString(2, jugador.getApellido());

            ps.setString(3, jugador.getNacionalidad());

            ps.setDate(4, java.sql.Date.valueOf(jugador.getFechaNac()));

            ps.setString(5, jugador.getRol());

            ps.setDouble(6, jugador.getSueldo());

            ps.setInt(7, Integer.parseInt(jugador.getEquipo().getCodigoEquipo()));

            ps.setString(8, jugador.getNickname());



            ps.executeUpdate();

        }

    }



    /**

     * Metodo para buscar un jugador que ya existe utilizando su nickname

     * @param nickname El apodo unico del jugador que quieres buscar

     * @return el jugador que has buscado si es que existe

     * @throws Exception si ocurre un error en la consulta SQL

     */

    public Jugador buscarJugadorPorNickname(String nickname) throws SQLException{

        String sql = "SELECT * FROM jugadores WHERE nickname = ?";

        try (

                PreparedStatement ps = conn.prepareStatement(sql)) {



            ps.setString(1, nickname);

            try (

                    ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {

                    return mapJugador(rs);

                }

            }

        }

        return null;

    }



    /**

     * Metodo para obtener la lista completa de todos los jugadores registrados

     * @return la lista con todos los jugadores . En caso de no tener jugadores la devolvera vacia

     */

    public List<Jugador> obtenerTodos() throws SQLException {

        String sql = "SELECT * FROM jugadores";

        List<Jugador> listaJugadores = new ArrayList<>();

        try (

                PreparedStatement ps = conn.prepareStatement(sql);

                ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                listaJugadores.add(mapJugador(rs));

            }

        }

        return listaJugadores;

    }



    public List<Equipo> obtenerEquiposDisp() throws SQLException {

        String sql = "SELECT cod_equipo, nombre FROM EQUIPOS";
        List<Equipo> listaEquipos = new ArrayList<>();

        if (conn == null) return listaEquipos;

        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Equipo equipo = new Equipo();
                equipo.setCodigoEquipo(String.valueOf(rs.getInt("cod_equipo")));
                equipo.setNombreEquipo(rs.getString("nombre"));
                listaEquipos.add(equipo);
            }
        }
        return listaEquipos;

    }



    private Jugador mapJugador(ResultSet rs) throws SQLException {
        Equipo equipo = new Equipo();
        equipo.setCodigoEquipo(String.valueOf(rs.getInt("cod_equipo")));


        return new Jugador(

                rs.getInt("cod_jugador"),

                rs.getString("nombre"),

                rs.getString("apellido"),

                rs.getString("nacionalidad"),

                rs.getDate("fecha_nac").toLocalDate(),

                rs.getString("nickname"),

                rs.getString("rol"),

                rs.getDouble("sueldo"),

                equipo

        );

    }

}