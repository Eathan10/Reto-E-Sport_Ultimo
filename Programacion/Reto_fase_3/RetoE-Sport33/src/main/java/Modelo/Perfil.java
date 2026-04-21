package Modelo;

/**
 * Clase que sirve para crear los perfiles con sus atributos
 * @author Unax
 * @version 1.0
 * @since 2026-04-16
 */
public class Perfil {

    private int codPerfil;
    private String nombre;
    private String password;
    private String tipo;


    public Perfil(int codPerfil, String nombre, String password, String tipo) {
        this.codPerfil = codPerfil;
        this.nombre = nombre;
        this.password = password;
        this.tipo = tipo;
    }


    public int getCodPerfil() {
        return codPerfil;
    }

    public void setCodPerfil(int codPerfil) {
        this.codPerfil = codPerfil;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }


    @Override
    public String toString() {
        return "Perfil{" +
                "codPerfil=" + codPerfil +
                ", nombre='" + nombre + '\'' +
                ", password='" + password + '\'' +
                ", tipo='" + tipo + '\'' +
                '}';
    }

}
