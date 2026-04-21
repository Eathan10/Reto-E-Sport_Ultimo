package Modelo;

import java.time.LocalDate;

/**
 * Calse que sirve para crear jugadores con sus atributos
 * @author Fatima
 * @version 1.0
 * @since 2026-04-15
 */
public class Jugador {
    private int codJugador;
    private String nombre;
    private String apellido;
    private String nacionalidad;
    private LocalDate fechaNac;
    private String nickname;
    private String rol;
    private double sueldo;

    private Equipo equipo;

    public Jugador(int codJugador, String nombre, String apellido, String nacionalidad, LocalDate fechaNac, String nickname, String rol, double sueldo, Equipo equipo) {
        this.codJugador = codJugador;
        this.nombre = nombre;
        this.apellido = apellido;
        this.nacionalidad = nacionalidad;
        this.fechaNac = fechaNac;
        this.nickname = nickname;
        this.rol = rol;
        this.sueldo = sueldo;
        this.equipo = equipo;
    }

    public Jugador() {

    }

    public int getCodJugador() {
        return codJugador;
    }

    public void setCodJugador(int codJugador) {
        this.codJugador = codJugador;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public LocalDate getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(LocalDate fechaNac) {
        this.fechaNac = fechaNac;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public double getSueldo() {
        return sueldo;
    }

    public void setSueldo(double sueldo) {
        this.sueldo = sueldo;
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }

    @Override
    public String toString() {
        return "Jugador{" +
                "codJugador=" + codJugador +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", nacionalidad='" + nacionalidad + '\'' +
                ", fechaNac=" + fechaNac +
                ", nickname='" + nickname + '\'' +
                ", rol='" + rol + '\'' +
                ", sueldo=" + sueldo +
                ", equipo=" + equipo +
                '}';
    }
}