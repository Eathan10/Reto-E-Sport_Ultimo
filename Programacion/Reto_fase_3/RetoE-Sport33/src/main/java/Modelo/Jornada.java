package Modelo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
/**
 * Clase que sirve para crear las jornadas con sus atributos
 * @author Urko
 * @version 1.0
 * @since 2026-04-15
 */
public class Jornada {

    private static int contadorGneral = 1;

    //ATRIBUTOS
    private int numJornada;
    private LocalDate fecha_inicio;


    //RELACIONES
    private List<Partido> partidos;// 1 jornada ---- N partidos


    //CONSTRUCTORES


    public Jornada() {
    }

    public Jornada(int numJornada, LocalDate fecha_inicio, List<Partido> partidos) {
        this.numJornada = contadorGneral++;
        this.fecha_inicio = fecha_inicio;
        this.partidos = partidos;

    }

    public Jornada(int numJornada, LocalDate fecha_inicio) {
        this.numJornada = contadorGneral++;
        this.fecha_inicio = fecha_inicio;
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


}
