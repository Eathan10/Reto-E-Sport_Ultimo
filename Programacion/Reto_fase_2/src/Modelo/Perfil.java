package Modelo;

public class Perfil {

    //ATRIBUTOS
    private int idPerfil;
    private String nombre;
    private String contrasena;


    //CONSTRUCTOR
    public Perfil(int idPerfil, String nombre, String contrasena) {
        this.idPerfil = idPerfil;
        this.nombre = nombre;
        this.contrasena = contrasena;
    }

    public int getIdPerfil() {
        return idPerfil;
    }

    public void setIdPerfil(int idPerfil) {
        this.idPerfil = idPerfil;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }


    @Override
    public String toString() {
        return "PERFIL" +
                "\n idPerfil=" + idPerfil +
                "\n nombre='" + nombre +
                "\n contrasena='" + contrasena;
    }
}
