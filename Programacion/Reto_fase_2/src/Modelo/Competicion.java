package Modelo;

import java.util.List;

public class Competicion {

    //ATRIBUTOS
    private int ID;
    private String estado;
    private String nombre;
    private double premio;

    private List<Jornada> jornadas;//RELACION  --> 1 competicion ---- N jornadas



    //CONSTRUCTOR


    public Competicion() {
    }

    public Competicion(int ID, String estado, String nombre, double premio, List<Jornada> jornadas) {
        this.ID = ID;
        this.estado = estado;
        this.nombre = nombre;
        this.premio = premio;
        this.jornadas = jornadas;
    }




    //GETTER AND SETTER
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPremio() {
        return premio;
    }

    public void setPremio(double premio) {
        this.premio = premio;
    }

    public List<Jornada> getJornadas() {
        return jornadas;
    }

    public void setJornadas(List<Jornada> jornadas) {
        this.jornadas = jornadas;
    }





    @Override
    public String toString() {
        return "COMPETICION" +
                "\n ID=" + ID +
                "\n estado='" + estado
                + "\n nombre='" + nombre
                + "\n premio=" + premio;
    }
}
