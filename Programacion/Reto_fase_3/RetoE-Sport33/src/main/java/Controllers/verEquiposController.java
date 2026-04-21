package Controllers;

import DAO.EquipoDAO;
import Modelo.Equipo;

import java.util.List;

/**
 * Controlador encargado de gestionar la  visualización de equipos. Inrermediario entre la vista y el dao
 * @author Urko
 * @version 1.0
 * @since 2026-04-19
 */
public class verEquiposController {
    private EquipoDAO dao = new EquipoDAO();

    /**
     * obtiene la lista completa de equipos registrados en el sistema
     * @return Una lista de objetos equipos con toda su informacion
     */
    public List<Equipo> getEquiposParaMostrar() {
        return dao.obtenerListaEquipos();
    }
}
