package Modelo;

/**
 * Clase para crear administradores con su nombre y contraseñas
 * @author Urko
 * @version 1.0
 * @since 2026-04-19
 */
public class Administrador {
    private String nombre;
    private String password;

    public Administrador(String nombre, String password) {
        this.nombre = nombre;
        this.password = password;
    }

    public String getNombre() { return nombre; }
    public String getPassword() { return password; }
}