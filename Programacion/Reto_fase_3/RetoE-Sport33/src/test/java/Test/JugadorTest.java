package Test;

import Modelo.Equipo;
import Modelo.Jugador;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class JugadorTest {
    @Test
    void testDatosJugador() {

        Equipo eq = new Equipo();
        eq.setNombreEquipo("G2 Esports");
        eq.setCodigoEquipo("G2");


        LocalDate fecha = LocalDate.of(2000, 10, 10);
        Jugador j = new Jugador(1, "Mikel", "hernandez", "Española", fecha, "mikel234", "controlador", 2500.0, eq);

        assertEquals(1, j.getCodJugador());
        assertEquals("Mikel", j.getNombre());
        assertEquals("mikel234", j.getNickname());
        assertEquals(2500.0, j.getSueldo());

        assertEquals("G2 Esports", j.getEquipo().getNombreEquipo());
    }

    @Test
    void testCambioDatos() {
        Jugador j = new Jugador(10, "Test", "Test", "Test", LocalDate.now(), "Nick", "Rol", 1000.0, null);

        j.setNombre("NuevoNombre");
        j.setSueldo(3000.50);

        assertEquals("NuevoNombre", j.getNombre());
        assertEquals(3000.50, j.getSueldo());
    }

}