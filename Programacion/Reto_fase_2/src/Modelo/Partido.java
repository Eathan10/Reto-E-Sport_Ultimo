package Modelo;

import java.time.LocalTime;
import java.util.Date;

public class Partido {

    //ATRIBUTOS
    private int id;
    private LocalTime hora;
    private int resultadoLocal;
    private int resultadoVisitante;

    //RELACIONES
    private Jornada jornadas;
    private Equipo equipoLocal;
    private Equipo equipoVisitante;

    //CONSTRUCTORES
    public Partido(int id, LocalTime hora, int resultadoLocal, int resultadoVisitante, Jornada jornadas, Equipo equipoLocal, Equipo equipoVisitante) {
        this.id = id;
        this.hora = hora;
        this.resultadoVisitante = resultadoVisitante;
        this.resultadoLocal = resultadoLocal;
        this.jornadas = jornadas;
        this.equipoLocal = equipoLocal;
        this.equipoVisitante = equipoVisitante;
    }

    public Partido(int id, LocalTime hora, int resultadoLocal, int resultadoVisitante) {
        this.id = id;
        this.hora = hora;
        this.resultadoLocal = resultadoLocal;
        this.resultadoVisitante = resultadoVisitante;
    }

    //GETTER AND SETTER
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public int getResultadoVisitante() {
        return resultadoVisitante;
    }

    public void setResultadoVisitante(int resultadoVisitante) {
        this.resultadoVisitante = resultadoVisitante;
    }

    public int getResultadoLocal() {
        return resultadoLocal;
    }

    public void setResultadoLocal(int resultadoLocal) {
        this.resultadoLocal = resultadoLocal;
    }

    public Jornada getJornadas() {
        return jornadas;
    }

    public void setJornadas(Jornada jornadas) {
        this.jornadas = jornadas;
    }

    public Equipo getEquipoLocal() {
        return equipoLocal;
    }

    public void setEquipoLocal(Equipo equipoLocal) {
        this.equipoLocal = equipoLocal;
    }

    public Equipo getEquipoVisitante() {
        return equipoVisitante;}

    public void setEquipoVisitante(Equipo equipoVisitante) {
        this.equipoVisitante= equipoVisitante   ;
    }



    @Override
    public String toString() {
        return "PARTIDO" +
                "\n id:" + id +
                "\n hora:" + hora +
                "\n resultado equipo local:" + resultadoLocal +
                "\n resultado equipo visitante:" + resultadoVisitante +
                "\n jornadas:" + jornadas +
                "\n equipo Local:" + equipoLocal
                +"\n equipo Visitante:" + equipoVisitante;
    }
}
