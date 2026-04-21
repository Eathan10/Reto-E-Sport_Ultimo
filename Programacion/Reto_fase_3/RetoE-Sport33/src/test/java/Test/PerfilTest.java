package Test;

import Modelo.Perfil;

import static org.junit.jupiter.api.Assertions.*;

class PerfilTest {

    @Test
    void testCrearPerfil() {

        Perfil p = new Perfil(1, "Urko", "1234", "ADMIN");


        assertEquals(1, p.getCodPerfil());
        assertEquals("Urko", p.getNombre());
        assertEquals("1234", p.getPassword());
        assertEquals("ADMIN", p.getTipo());
    }
}