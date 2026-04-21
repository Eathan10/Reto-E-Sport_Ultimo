package Test;

import Modelo.Equipo;
import Modelo.Jugador;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class EquipoTest {
    @Test
    void testCrearEquipoCorrecto() {
        ArrayList<Jugador> jugadores = new ArrayList<>();

        jugadores.add(new Jugador(1, "Aitor", "Lopez", "Española", LocalDate.now(), "AitLo12", "centinela", 1000, null));
        jugadores.add(new Jugador(2, "Lucia", "Rodriguez", "Española", LocalDate.now(), "LuR7", "duelista", 1000, null));
        jugadores.add(new Jugador(3, "Ander", "Gracia", "Española", LocalDate.now(), "N3", "iniciador", 1000, null));

        LocalDate fecha = LocalDate.of(2020, 1, 1);
        Equipo e = new Equipo("G2 Esports", "G2", fecha, jugadores);

        assertEquals("G2 Esports", e.getNombreEquipo());
        assertEquals("G2", e.getCodigoEquipo());
        assertEquals(3, e.getListaJugadores().size());
    }

    @Test
    void testEquipoInvalido() {
        ArrayList<Jugador> pocosJugadores = new ArrayList<>();
        pocosJugadores.add(new Jugador(1, "Aitor", "Lopez", "Española", LocalDate.now(), "AitLo12", "centinela", 1000, null));

        assertThrows(IllegalArgumentException.class, () -> {
            new Equipo("Fallo", "F1", LocalDate.now(), pocosJugadores);
        });
    }

}