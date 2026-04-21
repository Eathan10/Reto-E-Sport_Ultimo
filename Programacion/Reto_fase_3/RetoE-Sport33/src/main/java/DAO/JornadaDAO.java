package DAO;

import Modelo.Jornada;
import Utilidades.BaseDatos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Clase de acceso a datos  para la entidad jornada, para realizar la creacion de la jornada, con su numero de jornada y fecha de inicio
 * @author Urko
 * @version 1.0
 * @since 2026-04-16
 */
public class JornadaDAO {

    /**
     * Metodo para insertar una nuvea jornada con su numero de jornada y fecha de inicio
     * @param jornadaNueva Objeto jornada con los datos que quiero guardar
     * @throws Exception por si ocurre un error en la base de datos
     */
    public void crearJornada(Jornada jornadaNueva) {
        String sql = "INSERT INTO jornadas (num_jornada, fecha) VALUES (?, ?)";
        try {
            Connection conn = BaseDatos.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, jornadaNueva.getNumJornada());
            ps.setDate(2, java.sql.Date.valueOf(jornadaNueva.getFecha_inicio()));

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally{
                BaseDatos.closeConnection();
        }
    }

    public Jornada obtenerUltima() {
        Jornada jornada = null;
        String sql = "SELECT * FROM (SELECT * FROM jornadas ORDER BY num_jornada DESC) WHERE ROWNUM <= 1";

        try {
            Connection conn = BaseDatos.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();



            if (rs.next()) {
                jornada = new Jornada();
                jornada.setNumJornada(rs.getInt("num_jornada"));
                jornada.setFecha_inicio(rs.getDate("fecha").toLocalDate());
            }
        } catch (SQLException e) {
            System.out.println("Error al recuperar la última jornada: " + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return jornada;
    }
}
