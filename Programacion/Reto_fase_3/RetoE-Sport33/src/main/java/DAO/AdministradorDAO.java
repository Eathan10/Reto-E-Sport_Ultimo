package DAO;


import java.util.Arrays;
import java.util.List;

/**
 * Clase de Acceso a Datos para la entidad Administrador. Gestionar la autenticación y permisos de para acceso del administradpr..
 * @author Urko
 * @version 1.0
 * @since 2026-04-19
 */
public class AdministradorDAO {

        /** Lista de nombres de usuario que tienen permiso de acceso al sistema como administradores */
        private List<String> autorizados = Arrays.asList("fatima", "urko", "eathan", "unax");
        /** Contraseña única para la validación de administradores, para todos los administradores */
        private final String PASS = "1234";

    /**
     * Metodo para validar si los datos proporcionadados pertenecen a un administrador del sistema
     * @param nombre El nombre del administrador que intenta acceder
     * @param password La contraseña del administrador que intenta acceder
     * @return true si el administrador está en la lista y ademas la contraseña es correcta, false sino
     */
        public boolean validarAcceso(String nombre, String password) {
            return autorizados.contains(nombre.toLowerCase()) && PASS.equals(password);
        }

}
