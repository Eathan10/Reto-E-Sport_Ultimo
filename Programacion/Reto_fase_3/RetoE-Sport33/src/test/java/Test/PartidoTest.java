package Test;

import static org.junit.jupiter.api.Assertions.*;

import Modelo.Equipo;
import Modelo.Jornada;
import Modelo.Jugador;
import Modelo.Partido;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class PartidoTest {

    private Equipo e1;
    private Equipo e2;
    private Jornada j;

    @BeforeEach
    void creacion() {
        ArrayList<Jugador> listaMinima = new ArrayList<>();

        listaMinima.add(new Jugador(1, "Mikel", "Hernandez", "española", LocalDate.now(), "Mikel321", "controlador", 1000, null));
        listaMinima.add(new Jugador(2, "Eneko", "apellido2", "española", LocalDate.now(), "Enekd08", "duelista", 1000, null));
        listaMinima.add(new Jugador(3, "Maria", "apellido3", "española", LocalDate.now(), "Ma21", "centinela", 1000, null));

        e1 = new Equipo("G2 Esports", "E01", LocalDate.parse("2013-02-24"), listaMinima);
        e2 = new Equipo("KOI", "E02", LocalDate.parse("2021-12-15"), listaMinima);

        j = new Jornada(1, LocalDate.parse("2026-05-01"));
    }


    @Test
    void testCrearPartidoConObjetos() {
        LocalTime hora = LocalTime.of(20, 0);

        Partido partido = new Partido(10, hora, e1, e2, j);

        assertNotNull(partido);
        assertEquals(10, partido.getCodPartido());
        assertEquals(hora, partido.getHora());

        assertEquals("G2 Esports", partido.getEquipo1().getNombreEquipo());
        assertEquals("KOI", partido.getEquipo2().getNombreEquipo());

        assertEquals(1, partido.getJornada().getNumJornada());
    }
}