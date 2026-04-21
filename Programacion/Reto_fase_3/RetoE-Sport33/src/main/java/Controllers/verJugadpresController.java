package Controllers;

import DAO.JugadorDAO;
import Modelo.Jugador;

import java.sql.SQLException;
import java.util.List;

/**
 * Controlador encargado de gestionar la  visualización de los jugadores, intermediario entre la vista y el dao
 * @author Urko
 * @version 1.0
 * @since 2026-04-19
 */
public class verJugadpresController {

    private JugadorDAO jugadorDao = new JugadorDAO();

    /**
     * obtiene la lista completa de jugadores
     * @return Una lista de objetos jugador con toda la su informacion
     */
    public List<Jugador> getListaParaLaTabla() throws SQLException {
        return jugadorDao.obtenerTodos();
    }
}
