package Modelo;

import java.time.LocalDate;

public class Jugador {

    //ATRIBUTOS
    private int cod_jugador;
    private String nombre;
    private String apellido;
    private String nacionalidad;
    private LocalDate fecha_nac;
    private String nickname;
    private String rol;
    private double sueldo;


    //RELACION
    private Equipo equipo;// 1 jugador ---> 1 equipo

    //CONSTRUCTORES
    public Jugador(int cod_jugador, String nombre, String apellido, String nacionalidad, LocalDate fecha_nac, String nickname, String rol, double sueldo,  Equipo equipo) {
        this.cod_jugador = cod_jugador;
        this.nombre = nombre;
        this.apellido = apellido;
        this.nacionalidad = nacionalidad;
        this.fecha_nac = fecha_nac;
        this.nickname = nickname;
        this.rol = rol;
        this.sueldo = sueldo;
        this.equipo = equipo;
    }

    public Jugador(int cod_jugador, String nombre, String apellido, String nacionalidad, LocalDate fecha_nac, String nickname, String rol, double sueldo) {
        this.cod_jugador = cod_jugador;
        this.nombre = nombre;
        this.apellido = apellido;
        this.nacionalidad = nacionalidad;
        this.fecha_nac = fecha_nac;
        this.nickname = nickname;
        this.rol = rol;
        this.sueldo = sueldo;
    }

    public Jugador() {

    }

    //GETTER AND SETTER
    public int getCod_jugador() {
        return cod_jugador;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public LocalDate getFecha_nac() {
        return fecha_nac;
    }

    public String getNickname() {
        return nickname;
    }

    public String getRol() {
        return rol;
    }

    public double getSueldo() {
        return sueldo;
    }

    public void setCod_jugador(int cod_jugador) {
        this.cod_jugador = cod_jugador;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public void setFecha_nac(LocalDate fecha_nac) {
        this.fecha_nac = fecha_nac;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setRol(String rol) {
        this.rol = rol;
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
        return "Jugador" +
                "\n cod_jugador=" + cod_jugador +
                "\n nombre=" + nombre +
                "\n apellido=" + apellido +
                "\n nacionalidad=" + nacionalidad +
                "\n fechaNacimiento=" + fecha_nac +
                "\n nickname=" + nickname +
                "\n rol=" + rol +
                "\n sueldo=" + sueldo;
    }
}
