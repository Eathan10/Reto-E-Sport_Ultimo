package Test;

import Modelo.Administrador;

import static org.junit.jupiter.api.Assertions.*;

class AdministradorTest {
    @Test
    void testCrearAdmin() {
        Administrador admin = new Administrador("Fatima", "1234");

        assertEquals("Fatima", admin.getNombre());
        assertEquals("1234", admin.getPassword());
    }

    @Test
    void testDatosVacios() {
        Administrador adminVacio = new Administrador("", "");

        assertNotNull(adminVacio.getNombre());
        assertEquals("", adminVacio.getNombre());
    }
}

