package test.java.calculadora;

import main.java.calculadora.Calculadora;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * - Testes de propriedade (comutatividade da soma)
 * - Testes de sinais (multiplicação)
 * - Checagem do truncamento da divisão inteira
 * - Verificação da fórmula do somatório
 * - Testes agrupados (assertAll) para comparar vários casos da função compara
 */
@DisplayName("Calculadora - testes avançados e de propriedade")
public class CalculadoraGPTTest {

    private Calculadora calc;

    @BeforeEach
    void setup() {
        calc = new Calculadora();
    }

    @Test
    @DisplayName("Soma: comutatividade e identidade (0)")
    void somaPropriedades() {
        assertAll("Propriedades da soma",
            () -> assertEquals(7, calc.soma(3, 4), "3+4 deve ser 7"),
            () -> assertEquals(calc.soma(5, 8), calc.soma(8, 5), "a soma deve ser comutativa"),
            () -> assertEquals(42, calc.soma(42, 0), "x + 0 deve ser x")
        );
    }

    @Test
    @DisplayName("Subtração: comportamento básico e não comutatividade")
    void subtracaoBasica() {
        assertAll("Subtração",
            () -> assertEquals(2, calc.subtracao(5, 3)),
            () -> assertNotEquals(calc.subtracao(5, 3), calc.subtracao(3, 5), "subtração não é comutativa")
        );
    }

    @Test
    @DisplayName("Multiplicação: sinal do resultado (regras de sinais)")
    void multiplicacaoSinal() {
        assertAll("Regras de sinais",
            () -> assertEquals(20, calc.multiplicacao(4, 5)),
            () -> assertEquals(-20, calc.multiplicacao(-4, 5)),
            () -> assertEquals(-20, calc.multiplicacao(4, -5)),
            () -> assertEquals(20, calc.multiplicacao(-4, -5)),
            () -> assertEquals(0, calc.multiplicacao(0, 99999), "qualquer coisa multiplicada por zero é zero")
        );
    }

    @Test
    @DisplayName("Divisão inteira: truncamento e divisão por zero lança ArithmeticException")
    void divisaoComportamento() {
        // truncamento inteiro: 7 / 2 == 3
        assertEquals(3, calc.divisao(7, 2), "inteiro: 7/2 deve truncar para 3");

        // divisão negativa
        assertEquals(-3, calc.divisao(-7, 2), " -7 / 2 == -3 (truncamento em Java)");

        // divisão por zero lança
        assertThrows(ArithmeticException.class, () -> calc.divisao(10, 0), "divisão por zero deve lançar ArithmeticException");
    }

    @Test
    @DisplayName("Somatória: confere com fórmula n*(n+1)/2 e comportamento para n <= 0")
    void somatoriaFormulaEcasosLimite() {
        // somatoria(0) == 0
        assertEquals(0, calc.somatoria(0));

        // somatoria(1) == 1
        assertEquals(1, calc.somatoria(1));

        // somatoria(5) == 5*6/2 = 15
        assertEquals(15, calc.somatoria(5));

        // verificação usando a fórmula para alguns valores
        int[] testes = {2, 10, 50};
        for (int n : testes) {
            int esperado = n * (n + 1) / 2;
            assertEquals(esperado, calc.somatoria(n), "somatoria(" + n + ") deve ser " + esperado);
        }

        // comportamento para n negativo: implementação atual devolve 0
        assertEquals(0, calc.somatoria(-10), "somatoria para n negativo deve ser 0 conforme implementação atual");
    }

    @Test
    @DisplayName("ehPositivo: checagem de limites e sinais")
    void ehPositivoLimites() {
        assertAll("ehPositivo",
            () -> assertTrue(calc.ehPositivo(0), "0 é considerado positivo (>= 0)"),
            () -> assertTrue(calc.ehPositivo(1)),
            () -> assertFalse(calc.ehPositivo(-1))
        );
    }

    @Test
    @DisplayName("compara: resultados corretos e consistência (simetria com sinal invertido)")
    void comparaCasosEconsistencia() {
        assertAll("Casos básicos de compara",
            () -> assertEquals(0, calc.compara(7, 7), "iguais -> 0"),
            () -> assertEquals(1, calc.compara(8, 7), "8 > 7 -> 1"),
            () -> assertEquals(-1, calc.compara(3, 9), "3 < 9 -> -1")
        );

        // consistência: compara(a,b) == -compara(b,a) (exceto quando 0)
        int a = 12, b = 5;
        int cab = calc.compara(a, b);
        int cba = calc.compara(b, a);
        assertEquals(-cab, cba, "compara(a,b) deve ser o negativo de compara(b,a)");
    }
}
