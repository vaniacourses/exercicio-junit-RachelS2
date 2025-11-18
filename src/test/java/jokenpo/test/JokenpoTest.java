package test.java.jokenpo.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import main.java.jokenpo.Jokenpo;

@DisplayName("Classe para teste da  - jokenpo")
public class JokenpoTest {

    private Jokenpo jokenpo;
    
    @BeforeEach
	public void inicializa() {
		jokenpo = new Jokenpo();
	}
	
	@DisplayName("Testa para a mesma jogada (EMPATE)")
	@Test
	public void testJogarIgualmente() {	
		Assertions.assertEquals(0, jokenpo.jogar(1,1));		
	}

	// ------------------------------- TESTES JOGADOR UM VENCENDO ------------------------------- 

    @DisplayName("Testa para jogador 1 vencedor (papel X pedra)")
	@Test
	public void testJogadorUmVencedorPapel() {	
		Assertions.assertEquals(1, jokenpo.jogar(1,2));		
	}

    @DisplayName("Testa para jogador 1 vencedor (tesoura X papel)")
	@Test
	public void testJogadorUmVencedorTesoura() {	
		Assertions.assertEquals(1, jokenpo.jogar(3,1));		
	}

	@DisplayName("Testa para jogador 2 vencedor (pedra X tesoura)")
	@Test
	public void testJogadorUmVencedorPedra() {	
		Assertions.assertEquals(1, jokenpo.jogar(2,3));		
	}
	// ------------------------------- TESTES JOGADOR DOIS VENCENDO ------------------------------- 
    
    @DisplayName("Testa para jogador 2 vencedor (pedra X papel)")
	@Test
	public void testJogadorDoisVencedorPapel() {	
		Assertions.assertEquals(2, jokenpo.jogar(2,1));		
	}

    @DisplayName("Testa para jogador 2 vencedor (papel X tesoura)")
	@Test
	public void testJogadorDoisVencedorTesoura() {	
		Assertions.assertEquals(2, jokenpo.jogar(1,3));		
	}

	@DisplayName("Testa para jogador 2 vencedor (tesoura X pedra)")
	@Test
	public void testJogadorDoisVencedorPedra() {	
		Assertions.assertEquals(2, jokenpo.jogar(3,2));		
	}

	// ------------------------------- TESTES ENTRADAS MAIORES QUE 3------------------------------- 

	@DisplayName("Teste para jogador 1 jogando um numero maior que 3")
	@Test
	public void testJogador1MaiorQue3() {	
		Assertions.assertEquals(-1, jokenpo.jogar(4,2));		
	}

	@DisplayName("Teste para jogador 2 jogando um numero maior que 3")
	@Test
	public void testJogador2MaiorQue3() {	
		Assertions.assertEquals(-1, jokenpo.jogar(1,8));		
	}

	@DisplayName("Teste para jogador 1 e 2 jogando um numero maior que 3")
	@Test
	public void testJogador1e2MaiorQue3() {	
		Assertions.assertEquals(-1, jokenpo.jogar(879,8));		
	}

	// ------------------------------- TESTES ENTRADAS MENORES QUE 1------------------------------- 

	@DisplayName("Teste para jogador 1 jogando um numero menor que 1")
	@Test
	public void testJogador1MenorQue1() {	
		Assertions.assertEquals(-1, jokenpo.jogar(-2,-3));		
	}

	@DisplayName("Teste para jogador 2 jogando um numero menor que 1")
	@Test
	public void testJogador2MenorQue1() {	
		Assertions.assertEquals(-1, jokenpo.jogar(2,-1));		
	}

	@DisplayName("Teste para jogador 1 e 2 jogando um numero menor que 1")
	@Test
	public void testJogador1e2MenorQue1() {	
		Assertions.assertEquals(-1, jokenpo.jogar(0,0));		
	}

}
