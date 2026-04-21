package Controllers;

import DAO.JornadaDAO;
import Modelo.Jornada;

/**
 * Controlador para la entidad jornada, intermediario entre la vista y el DAO, para gesyionar las jornadas
 * @author Urko
 * @version 1.0
 * @since 2026-04-16
 */
public class JornadaController {

    /**
     * Metodo para para crear una jornada llamando al DAO, con sus datos
     * @param jornadaNueva Objeto que contiene los datos de la jornada
     */
    public static void crearJornada(Jornada jornadaNueva) {
        JornadaDAO jornadaDAO = new JornadaDAO();
        jornadaDAO.crearJornada(jornadaNueva);
    }

    public static Jornada obtenerUltimaJornada() {
        JornadaDAO jornadaDAO = new JornadaDAO();
        return jornadaDAO.obtenerUltima();
    }
}
