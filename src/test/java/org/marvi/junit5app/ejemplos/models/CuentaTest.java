package org.marvi.junit5app.ejemplos.models;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.*;
import org.marvi.junit5app.ejemplos.exceptions.DinerosuficienteException;
import java.math.BigDecimal;
import java.util.Properties;


class CuentaTest {

    Cuenta cuenta;
    @BeforeEach
    void initMetodoTest(){
        this.cuenta = new Cuenta("Natalia", new BigDecimal("1000.12345"));
        System.out.println("iniciando el metodo");
    }

    @AfterEach
    void tearDown() {
        System.out.println("finalizando el metodo de prueba");
    }

    @BeforeAll
    static void beforeAll() {
        System.out.println("Inicilizando el test");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("Finalizando el test");
    }

    @Test
    @DisplayName("Probando nombre de la cuenta corriente!")
    void testNombreCuenta() {
        String esperado = "Natalia";
        String real = cuenta.getPersona();
        assertNotNull(real, ()-> "la cuenta no puede ser nula");
        assertEquals(esperado, real, ()-> "el nombre de la cuenta no es el que se esperaba");
    }

    @Test
    void testSaldoCuenta() {
        assertNotNull(cuenta.getSaldo());
        assertEquals(1000.12345, cuenta.getSaldo().doubleValue());
        assertFalse(cuenta.getSaldo().compareTo(BigDecimal.ZERO) < 0);
        assertTrue(cuenta.getSaldo().compareTo(BigDecimal.ZERO) > 0);
    }

    @Test
    void testReferenciaCuenta() {
        Cuenta cuenta2 = new Cuenta("Natalia", new BigDecimal("1000.12345"));
        //assertNotEquals(cuenta2,cuenta);
        assertEquals(cuenta.getPersona(), cuenta2.getPersona());
        assertEquals(cuenta.getSaldo(), cuenta2.getSaldo());
    }

    @Test
    void testDebitoCuenta() {
        cuenta.debito((new BigDecimal(100)));
        assertNotNull(cuenta.getSaldo());
        assertEquals(900, cuenta.getSaldo().intValue());
        assertEquals("900.12345", cuenta.getSaldo().toPlainString());
    }

    @Test
    void testCreditoCuenta() {
        cuenta.credito((new BigDecimal(100)));
        assertNotNull(cuenta.getSaldo());
        assertEquals(1100, cuenta.getSaldo().intValue());
        assertEquals("1100.12345", cuenta.getSaldo().toPlainString());
    }

    @Test
    void testDineroInsuficienteExceptionCuenta() {
        Exception exception = assertThrows(DinerosuficienteException.class,
                () -> cuenta.debito((new BigDecimal(1500))));
        String actual = exception.getMessage();
        String esperado = "Dinero Insuficiente";
        assertEquals(esperado, actual);
    }

    @Test
    void testTransferirDineroCuentas() {
        Cuenta cuenta2 = new Cuenta("Zeus", new BigDecimal("1000.12345"));

        Banco banco = new Banco();
        banco.setNombre("Banco del Estado");
        banco.transferir(cuenta2, cuenta, new BigDecimal(500));
        assertEquals("500.12345", cuenta2.getSaldo().toPlainString());
        assertEquals("1500.12345", cuenta.getSaldo().toPlainString());
    }

    @Test
    void testRelacionBancoCuentas() {
        Cuenta cuenta2 = new Cuenta("Zeus", new BigDecimal("1000.12345"));

        Banco banco = new Banco();
        banco.addCuenta(cuenta);
        banco.addCuenta(cuenta2);

        banco.setNombre("Banco del Estado");
        banco.transferir(cuenta2, cuenta, new BigDecimal(500));
        assertAll(() -> assertEquals("500.12345", cuenta2.getSaldo().toPlainString()),
                () -> assertEquals("1500.12345", cuenta.getSaldo().toPlainString()),
                () -> assertEquals(2, banco.getCuentas().size()),
                () -> assertEquals("Banco del Estado", cuenta.getBanco().getNombre()),
                () -> assertEquals("Natalia", banco.getCuentas().stream()
                            .filter(c -> c.getPersona().equals("Natalia"))
                            .findFirst()
                            .get().getPersona()),
                () -> assertTrue(banco.getCuentas().stream()
                            .anyMatch(cuenta -> cuenta.getPersona().equals("Natalia")))
        );}
    @Test
    @Disabled
    @DisplayName("Saltar test")
    void name() {
        fail();
    }

    @Test
    @EnabledOnOs(OS.WINDOWS)
    void testSoloWindows() {
    }

    @Test
    @EnabledOnOs({OS.LINUX, OS.MAC})
    void testSoloLinuxMac() {
    }

    @Test
    @DisabledOnOs(OS.WINDOWS)
    void testNoWindows() {
    }

    @Test
    @EnabledOnJre(JRE.JAVA_8)
    void soloJdk8(){
    }

    @Test
    @DisabledOnJre(JRE.JAVA_8)
    void soloNoJdk8(){
    }

    @Test
    void imprimirSystemProperties() {
        Properties properties = System.getProperties();
        properties.forEach((k, v) -> System.out.println(k + " : " + v));
    }

    @Test
    @EnabledIfSystemProperty(named = "java.version", matches = "17.0.2")
    void testJavaVersion(){}

    @Test
    @DisabledIfSystemProperty(named = "os.arch", matches = ".*32.*")
    void testSolo64() {
    }

    @Test
    @EnabledIfSystemProperty(named = "os.arch", matches = ".*32.*")
    void testNo64() {
    }
}