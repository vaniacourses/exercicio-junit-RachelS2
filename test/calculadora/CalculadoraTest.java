package calculadora;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testes unitários para a classe Calculadora usando JUnit 5
 */
public class CalculadoraTest {

    private Calculadora calculadora;

    @BeforeEach
    public void setUp() {
        calculadora = new Calculadora();
    }

    // ---- Testes do método soma ----
    @Test
    public void testSomaComNumerosPositivos() {
        assertEquals(5, calculadora.soma(2, 3));
    }

    @Test
    public void testSomaComNumerosNegativos() {
        assertEquals(-8, calculadora.soma(-3, -5));
    }

    @Test
    public void testSomaComZero() {
        assertEquals(7, calculadora.soma(0, 7));
    }

    // ---- Testes do método subtração ----
    @Test
    public void testSubtracaoComNumerosPositivos() {
        assertEquals(1, calculadora.subtracao(5, 4));
    }

    @Test
    public void testSubtracaoComResultadoNegativo() {
        assertEquals(-3, calculadora.subtracao(2, 5));
    }

    @Test
    public void testSubtracaoComZero() {
        assertEquals(4, calculadora.subtracao(4, 0));
    }

    // ---- Testes do método multiplicação ----
    @Test
    public void testMultiplicacaoComNumerosPositivos() {
        assertEquals(12, calculadora.multiplicacao(3, 4));
    }

    @Test
    public void testMultiplicacaoComZero() {
        assertEquals(0, calculadora.multiplicacao(0, 100));
    }

    @Test
    public void testMultiplicacaoComNumeroNegativo() {
        assertEquals(-15, calculadora.multiplicacao(-3, 5));
    }

    // ---- Testes do método divisão ----
    @Test
    public void testDivisaoComNumerosInteiros() {
        assertEquals(2, calculadora.divisao(10, 5));
    }

    @Test
    public void testDivisaoComResultadoZero() {
        assertEquals(0, calculadora.divisao(3, 10));
    }

    @Test
    public void testDivisaoPorZeroLancaExcecao() {
        assertThrows(ArithmeticException.class, () -> calculadora.divisao(10, 0));
    }

    // ---- Testes do método somatoria ----
    @Test
    public void testSomatoriaComNumeroPositivo() {
        assertEquals(15, calculadora.somatoria(5)); // 5 + 4 + 3 + 2 + 1 + 0
    }

    @Test
    public void testSomatoriaComZero() {
        assertEquals(0, calculadora.somatoria(0));
    }

    @Test
    public void testSomatoriaComNumeroNegativo() {
        assertEquals(0, calculadora.somatoria(-5)); // Deve retornar 0
    }

    // ---- Testes do método ehPositivo ----
    @Test
    public void testEhPositivoComNumeroPositivo() {
        assertTrue(calculadora.ehPositivo(10));
    }

    @Test
    public void testEhPositivoComZero() {
        assertTrue(calculadora.ehPositivo(0));
    }

    @Test
    public void testEhPositivoComNumeroNegativo() {
        assertFalse(calculadora.ehPositivo(-1));
    }

    // ---- Testes do método compara ----
    @Test
    public void testComparaQuandoIguais() {
        assertEquals(0, calculadora.compara(5, 5));
    }

    @Test
    public void testComparaQuandoMaior() {
        assertEquals(1, calculadora.compara(10, 3));
    }

    @Test
    public void testComparaQuandoMenor() {
        assertEquals(-1, calculadora.compara(2, 7));
    }
}
