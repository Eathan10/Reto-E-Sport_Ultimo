package Test;

import Modelo.Jornada;
import Modelo.Partido;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JornadaTest {
    @Test
    void testCrearJornada() {
        LocalDate fecha = LocalDate.of(2026, 5, 10);

        Jornada j = new Jornada(1, fecha);

        assertEquals(fecha, j.getFecha_inicio());
        assertTrue(j.getNumJornada() > 0);
    }

    @Test
    void testJornadaConPartidos() {
        List<Partido> listaPartidos = new ArrayList<>();
        LocalDate fecha = LocalDate.now();

        Jornada j = new Jornada(2, fecha, listaPartidos);

        assertNotNull(j.getPartidos());
        assertEquals(fecha, j.getFecha_inicio());
    }

}