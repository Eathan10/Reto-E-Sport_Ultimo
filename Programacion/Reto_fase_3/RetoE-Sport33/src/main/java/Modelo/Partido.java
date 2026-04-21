package Modelo;


import java.time.LocalTime;


/**
 * Calse para crear partidos con sus atributos
 * @author Unax
 * @version 1.0
 * @since 2026-04-15
 */

public class Partido {
    private int codPartido;
    private LocalTime hora;

    private Equipo equipo1;
    private Equipo equipo2;

    private Jornada jornada;

    public Partido(int codPartido, LocalTime hora, Equipo equipo1, Equipo equipo2, Jornada jornada) {
        this.codPartido = codPartido;
        this.hora = hora;
        this.equipo1 = equipo1;
        this.equipo2 = equipo2;
        this.jornada = jornada;
    }

    public int getCodPartido() {
        return codPartido;
    }

    public void setCodPartido(int codPartido) {
        this.codPartido = codPartido;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public Equipo getEquipo1() {
        return equipo1;
    }

    public void setEquipo1(Equipo equipo1) {
        this.equipo1 = equipo1;
    }

    public Equipo getEquipo2() {
        return equipo2;
    }

    public void setEquipo2(Equipo equipo2) {
        this.equipo2 = equipo2;
    }

    public Jornada getJornada() {
        return jornada;
    }

    public void setJornada(Jornada jornada) {
        this.jornada = jornada;
    }


    @Override
    public String toString() {
        return "Partido{" +
                "codPartido=" + codPartido +
                ", hora=" + hora +
                ", equipo1=" + equipo1 +
                ", equipo2=" + equipo2 +
                ", jornada=" + jornada +
                '}';
    }
}

