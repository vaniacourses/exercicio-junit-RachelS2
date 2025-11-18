package test.java.carrinho;

import main.java.carrinho.Carrinho;
import main.java.produto.Produto;
import main.java.produto.ProdutoNaoEncontradoException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 */
@DisplayName("Carrinho - testes alternativos")
public class CarrinhoGPTTest {

    private Carrinho carrinho;

    private Produto p(String nome, double preco) {
        return new Produto(nome, preco);
    }

    @BeforeEach
    void setup() {
        carrinho = new Carrinho();
    }

    @Test
    @DisplayName("Adicionar e remover múltiplas instâncias com mesmo conteúdo (objetos distintos)")
    void adicionarRemoverInstanciasDistintasMesmoConteudo() throws ProdutoNaoEncontradoException {
        // Criamos duas instâncias distintas com mesmo nome/preço
        Produto a1 = p("Caneta", 2.5);
        Produto a2 = p("Caneta", 2.5);

        carrinho.addItem(a1);
        carrinho.addItem(a2);

        // devemos ter 2 itens e valor total somado
        assertAll("estado após adicionar duas instâncias iguais em conteúdo",
            () -> assertEquals(2, carrinho.getQtdeItems()),
            () -> assertEquals(5.0, carrinho.getValorTotal(), 1e-6)
        );

        // Remover apenas uma instância (a1) deve deixar 1 item e valor total reduzido
        carrinho.removeItem(a1);
        assertAll("estado após remover uma das instâncias",
            () -> assertEquals(1, carrinho.getQtdeItems()),
            () -> assertEquals(2.5, carrinho.getValorTotal(), 1e-6)
        );

        // Remover a2 também deve esvaziar
        carrinho.removeItem(a2);
        assertAll("estado final",
            () -> assertEquals(0, carrinho.getQtdeItems()),
            () -> assertEquals(0.0, carrinho.getValorTotal(), 1e-6)
        );
    }

    @Test
    @DisplayName("Esvaziar é idempotente (pode ser chamado múltiplas vezes sem erro)")
    void esvaziaIdempotente() {
        carrinho.addItem(p("Borracha", 0.5));
        carrinho.addItem(p("Caderno", 10.0));
        assertEquals(2, carrinho.getQtdeItems());

        // primeira limpeza
        carrinho.esvazia();
        assertEquals(0, carrinho.getQtdeItems());

        // chamar de novo não deve lançar e o estado permanece vazio
        assertDoesNotThrow(() -> carrinho.esvazia());
        assertEquals(0, carrinho.getQtdeItems());
        assertEquals(0.0, carrinho.getValorTotal(), 1e-6);
    }

    @Test
    @DisplayName("Remover de carrinho vazio lança ProdutoNaoEncontradoException")
    void removerEmCarrinhoVazioLanca() {
        Produto prod = p("Item", 1.0);
        assertThrows(ProdutoNaoEncontradoException.class, () -> carrinho.removeItem(prod));
    }

    @Test
    @DisplayName("getValorTotal calculado via iteração manual (teste do loop interno)")
    void valorTotalIterandoComIteratorManual() {
        // Vamos simular manualmente o algoritmo interno para checar consistência
        Produto p1 = p("A", 1.1);
        Produto p2 = p("B", 2.2);
        Produto p3 = p("C", 3.3);

        carrinho.addItem(p1);
        carrinho.addItem(p2);
        carrinho.addItem(p3);

        // calcula soma manualmente (mesma lógica do loop interno)
        double somaManual = 0.0;
        List<Produto> copia = new ArrayList<>();
        copia.add(p1);
        copia.add(p2);
        copia.add(p3);
        for (Produto x : copia) {
            somaManual += x.getPreco();
        }

        double somaCarrinho = carrinho.getValorTotal();
        assertEquals(somaManual, somaCarrinho, 1e-6, "getValorTotal deve corresponder à soma via iteração");
    }

    @Test
    @DisplayName("Adicionar muitos itens e verificar contagem e valor (stress leve)")
    void adicionarMuitosItens() {
        int n = 50;
        double preco = 1.5;
        for (int i = 0; i < n; i++) {
            carrinho.addItem(p("Item" + i, preco));
        }
        assertAll("contagem e soma com muitos itens",
            () -> assertEquals(n, carrinho.getQtdeItems()),
            () -> assertEquals(n * preco, carrinho.getValorTotal(), 1e-6)
        );
    }

    @Test
    @DisplayName("Remover apenas um entre múltiplos iguais (mesma instância várias vezes)")
    void removerUmaEntreVariasMesmaInstancia() throws ProdutoNaoEncontradoException {
        Produto p = p("Bloco", 2.0);
        // adiciona mesma instância 3x
        carrinho.addItem(p);
        carrinho.addItem(p);
        carrinho.addItem(p);
        assertEquals(3, carrinho.getQtdeItems());
        assertEquals(6.0, carrinho.getValorTotal(), 1e-6);

        // remover uma vez decrementa adequadamente
        carrinho.removeItem(p);
        assertEquals(2, carrinho.getQtdeItems());
        assertEquals(4.0, carrinho.getValorTotal(), 1e-6);
    }
}
