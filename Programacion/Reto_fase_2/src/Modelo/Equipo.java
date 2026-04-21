package Modelo;

import java.time.LocalDate;
import java.util.ArrayList;

public class Equipo {

    //ATRIBUTOS
    private String codEquipo;
    private String nombreEquipo;
    private LocalDate fechaFundacion;

    //RELACION
    private ArrayList<Jugador> listaJugadores; //1 equipo -- N jugadores 

    //CONSTRUCTOR

    public Equipo() {
        this.listaJugadores = new ArrayList<>(); // Es buena práctica inicializar la lista aquí
    }

    public Equipo(String codEquipo, String nombreEquipo, LocalDate fechaFundacion, ArrayList<Jugador> listaJugadores) {
        this.codEquipo = codEquipo;
        this.nombreEquipo = nombreEquipo;
        this.fechaFundacion = fechaFundacion;
        this.listaJugadores = listaJugadores;
    }


    //GETTER AND SETTER
    public String getCodEquipo() {
        return codEquipo;
    }

    public void setCodEquipo(String codEquipo) {
        this.codEquipo = codEquipo;
    }

    public String getNombreEquipo() {
        return nombreEquipo;
    }

    public void setNombreEquipo(String nombreEquipo) {
        this.nombreEquipo = nombreEquipo;
    }

    public LocalDate getFechaFundacion() {
        return fechaFundacion;
    }

    public void setFechaFundacion(LocalDate fecha_fundacion) {
        this.fechaFundacion = fechaFundacion;
    }

    public ArrayList<Jugador> getListaJugadores() {
        return listaJugadores;
    }

    public void setListaJugadores(ArrayList<Jugador> listaJugadores) {
        this.listaJugadores = listaJugadores;
    }
}
