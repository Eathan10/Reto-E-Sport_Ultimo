package Test;

import Modelo.Competicion;

import static org.junit.jupiter.api.Assertions.*;

class CompeticionTest {
    @Test
    void testCrearCompeticion() {
        Competicion c = new Competicion(1, "Premier", "Abierta", "100000€");

        assertEquals(1, c.getCodComp());
        assertEquals("Premier", c.getNombre());
        assertEquals("Abierta", c.getEstado());
        assertEquals("100000€", c.getPremio());
    }

    @Test
    void testModificarCompeticion() {
        Competicion c = new Competicion(0, "", "", "");

        c.setNombre("Liga");
        c.setEstado("Cerrada");
        c.setPremio("Trofeo");

        assertEquals("Liga", c.getNombre());
        assertEquals("Cerrada", c.getEstado());
        assertEquals("Trofeo", c.getPremio());
    }

}