package test.java.calculadora;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import main.java.calculadora.Calculadora;

public class CalculadoraTest {

    private Calculadora calculadora;

    @BeforeEach
    public void setUp() {
        calculadora = new Calculadora();
    }

    @Test
    public void testSomaComNumerosPositivos() {
        assertEquals(5, calculadora.soma(2, 3));
    }

    @Test
    public void testSomaComNumerosNegativos() {
        assertEquals(-8, calculadora.soma(-3, -5));
    }

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

    @Test
    public void testSomatoriaComZero() {
        assertEquals(0, calculadora.somatoria(0));
    }

    @Test
    public void testSomatoriaComNumeroNegativo() {
        assertEquals(0, calculadora.somatoria(-5)); 
    }

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

    @Test
    public void testComparaQuandoMaior() {
        assertEquals(1, calculadora.compara(10, 3));
    }

}
