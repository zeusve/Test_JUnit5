package org.marvi.junit5app.ejemplos.models;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.marvi.junit5app.ejemplos.exceptions.DinerosuficienteException;

import java.math.BigDecimal;


class CuentaTest {

    @Test
    void testNombreCuenta() {
        Cuenta cuenta = new Cuenta("Natalia", new BigDecimal("542543.43543"));
        String esperado = "Natalia";
        String real = cuenta.getPersona();
        assertNotNull(real);
        assertEquals(esperado, real);
    }

    @Test
    void testSaldoCuenta(){
        Cuenta cuenta = new Cuenta("Natalia",new BigDecimal("3532.2435243"));
        assertNotNull(cuenta.getSaldo());
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

    @Test
    void testDebitoCuenta() {
        Cuenta cuenta = new Cuenta("Natalia", new BigDecimal("1000.12345"));
        cuenta.debito((new BigDecimal(100)));
        assertNotNull(cuenta.getSaldo());
        assertEquals(900,cuenta.getSaldo().intValue());
        assertEquals("900.12345", cuenta.getSaldo().toPlainString());
    }

    @Test
    void testCreditoCuenta() {
        Cuenta cuenta = new Cuenta("Natalia", new BigDecimal("1000.12345"));
        cuenta.credito((new BigDecimal(100)));
        assertNotNull(cuenta.getSaldo());
        assertEquals(1100,cuenta.getSaldo().intValue());
        assertEquals("1100.12345", cuenta.getSaldo().toPlainString());
    }

    @Test
    void testDineroInsuficienteExceptionCuenta() {
        Cuenta cuenta = new Cuenta("Natalia", new BigDecimal("1000.12345"));
        Exception exception = assertThrows(DinerosuficienteException.class,
                () -> cuenta.debito((new BigDecimal(1500))));
        String actual = exception.getMessage();
        String esperado = "Dinero Insuficiente";
        assertEquals(esperado,actual);
    }
}