package test.java.jokenpo;

import main.java.jokenpo.Jokenpo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testes alternativos para a classe Jokenpo.
 * Diferentes dos testes originais: contém testes exaustivos, propriedades (simetria),
 * fuzz determinístico e cenários nomeados.
 */
@DisplayName("Jokenpo - testes alternativos e propriedades")
public class JokenpoGPTTest {

    private Jokenpo j;

    @BeforeEach
    void setup() {
        j = new Jokenpo();
    }

    /**
     * Helper para calcular o resultado esperado (mesma lógica do domínio):
     * retorna 0 empate, 1 jogador1 vence, 2 jogador2 vence, -1 inválido.
     */
    private int esperado(int a, int b) {
        if (a < 1 || a > 3 || b < 1 || b > 3) return -1;
        if (a == b) return 0;
        // regras: 1=papel, 2=pedra, 3=tesoura
        // jogador1 vence quando (a - b == -1) ou (a - b == 2)
        int diff = a - b;
        if (diff == -1 || diff == 2) return 1;
        return 2;
    }

    @Test
    @DisplayName("Matriz exaustiva: todas as combinações válidas 1..3")
    void matrizExaustiva() {
        for (int a = 1; a <= 3; a++) {
            for (int b = 1; b <= 3; b++) {
                int esperadoValor = esperado(a, b);
                int obtido = j.jogar(a, b);

                assertEquals(
                    esperadoValor,
                    obtido,
                    String.format(
                        "jogar(%d,%d) deve ser %d, mas foi %d",
                        a, b, esperadoValor, obtido
                    )
                );
            }
        }
    }


    @Test
    @DisplayName("Entradas inválidas: abaixo de 1 e acima de 3")
    void entradasInvalidas() {
        int[] invalidos = { Integer.MIN_VALUE, -5, -1, 0, 4, 10, 9999 };
        for (int inval : invalidos) {
            // testar combinando com uma escolha válida do outro jogador
            assertEquals(-1, j.jogar(inval, 1), () -> "jogar(" + inval + ",1) deve retornar -1");
            assertEquals(-1, j.jogar(1, inval), () -> "jogar(1," + inval + ") deve retornar -1");
        }
    }

    @Test
    @DisplayName("Propriedade de simetria: resultado invertido quando jogadores trocados")
    void propriedadeSimetria() {
        for (int a = 1; a <= 3; a++) {
            for (int b = 1; b <= 3; b++) {

                int r1 = j.jogar(a, b);
                int r2 = j.jogar(b, a);

                if (r1 == -1 || r2 == -1) {
                    assertEquals(
                        r1,
                        r2,
                        "inconsistência em inválidos para (" + a + "," + b + ")"
                    );
                } else if (r1 == 0) {
                    assertEquals(
                        0,
                        r2,
                        "empate deve ser simétrico para (" + a + "," + b + ")"
                    );
                } else {
                    assertEquals(
                        3 - r1,
                        r2,
                        String.format(
                            "jogar(%d,%d)=%d então jogar(%d,%d) deve ser %d (obtido %d)",
                            a, b, r1, b, a, (3 - r1), r2
                        )
                    );
                }
            }
        }
    }


    @Test
    @DisplayName("Casos documentados: exemplos nomeados (papel, pedra, tesoura)")
    void casosNomeados() {
        // papel(1) vence pedra(2)
        assertEquals(1, j.jogar(1, 2), "papel(1) deve vencer pedra(2)");

        // tesoura(3) vence papel(1)
        assertEquals(1, j.jogar(3, 1), "tesoura(3) deve vencer papel(1)");

        // pedra(2) vence tesoura(3)
        assertEquals(1, j.jogar(2, 3), "pedra(2) deve vencer tesoura(3)");

        // empates
        assertEquals(0, j.jogar(1, 1));
        assertEquals(0, j.jogar(2, 2));
        assertEquals(0, j.jogar(3, 3));
    }

    @Test
    @DisplayName("Fuzz determinístico: muitas jogadas aleatórias (mesma semente)")
    void fuzzDeterministico() {
        Random rnd = new Random(123456); // semente fixa para reprodução
        for (int i = 0; i < 200; i++) {
            int a = 1 + rnd.nextInt(3);
            int b = 1 + rnd.nextInt(3);
            int esperado = esperado(a, b);
            int obtido = j.jogar(a, b);
            assertEquals(esperado, obtido, () -> "Fuzz falhou para jogar(" + a + "," + b + ")");
        }
    }

    @RepeatedTest(3)
    @DisplayName("Sanity: repetido para garantir estabilidade")
    void sanityRepetido() {
        // uma checagem curta repetida para detectar efeitos colaterais (não deveriam existir)
        assertEquals(0, j.jogar(2, 2));
        assertEquals(1, j.jogar(1, 2));
        assertEquals(1, j.jogar(3, 1));
    }
}
