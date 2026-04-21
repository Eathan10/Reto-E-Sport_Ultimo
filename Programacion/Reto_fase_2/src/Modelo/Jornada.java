package Modelo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Jornada {

    private static int contadorGneral = 1;

    //ATRIBUTOS
    private int numJornada;
    private LocalDate fecha_inicio;



    //RELACIONES
    private List<Partido> partidos;// 1 jornada ---- N partidos
    private Competicion competicion;// 1 jornada ---- 1 competicion


    //CONSTRUCTORES


    public Jornada() {
    }

    public Jornada(int numJornada, LocalDate fecha_inicio, Competicion competicion, List<Partido> partidos) {
        this.numJornada = contadorGneral++;
        this.fecha_inicio = fecha_inicio;
        this.partidos = partidos;
        this.competicion = competicion;
    }

    public Jornada(int numJornada, LocalDate fecha_inicio, Competicion competicion) {
        this.numJornada = contadorGneral++;
        this.fecha_inicio = fecha_inicio;
        this.competicion = competicion;
    }



    //GETTER AND SETTER
    public int getNumJornada() {
        return numJornada;
    }

    public void setNumJornada(int numJornada) {
        this.numJornada = numJornada;
    }

    public LocalDate getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(LocalDate fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public List<Partido> getPartidos() {
        return partidos;
    }

    public void setPartidos(List<Partido> partidos) {
        this.partidos = partidos;
    }

    public Competicion getCompeticion() {
        return competicion;
    }

    public void setCompeticion(Competicion competicion) {
        this.competicion = competicion;
    }





    @Override
    public String toString() {
        return "JORNADA" +
                "\n numJornada=" + numJornada +
                "\n fechaInicio=" + fecha_inicio;
    }
}
