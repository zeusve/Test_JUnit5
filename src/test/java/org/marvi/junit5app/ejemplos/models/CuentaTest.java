package org.marvi.junit5app.ejemplos.models;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;


class CuentaTest {

    @Test
    void testNombreCuenta() {
        Cuenta cuenta = new Cuenta("Natalia", new BigDecimal("542543.43543"));
        String esperado = "Natalia";
        String real = cuenta.getPersona();
        assertEquals(esperado, real);
    }

    @Test
    void testSaldoCuenta(){
        Cuenta cuenta = new Cuenta("Natalia",new BigDecimal("3532.2435243"));
        assertEquals(3532.2435243, cuenta.getSaldo().doubleValue());
        assertFalse(cuenta.getSaldo().compareTo(BigDecimal.ZERO) < 0);
        assertTrue(cuenta.getSaldo().compareTo(BigDecimal.ZERO) > 0);
    }

    @Test
    void testReferenciaCuenta() {
        Cuenta cuenta = new Cuenta("Natalia", new BigDecimal("4534543.34523"));
        Cuenta cuenta2 = new Cuenta("Natalia", new BigDecimal("4534543.34523"));

        //assertNotEquals(cuenta2,cuenta);
        assertEquals(cuenta, cuenta2);
    }
}