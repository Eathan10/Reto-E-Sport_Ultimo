package Controllers;

import DAO.AdministradorDAO;

/**
 * Controlador que se encarga de gestionar el proceso de autenticación de los administradores
 * @author Urko
 * @version 1.0
 * @since 2026-04-19
 */
public class AministradorController {

    private AdministradorDAO adminDao = new AdministradorDAO();

    /**
     * Procesa la solicitud de inicio de sesión de un usuario y las envia al dao para verificarlas
     * @param nombre Nombre de administrador introducido
     * @param password Contraseña introducida
     * @return true si el acceso es bueno, false sino
     */
    public boolean procesarLogin(String nombre, String password) {
        return adminDao.validarAcceso(nombre, password);
    }
}
