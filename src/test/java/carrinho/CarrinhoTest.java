package test.java.carrinho;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.java.carrinho.Carrinho;
import main.java.produto.Produto;

import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

import main.java.produto.ProdutoNaoEncontradoException;

public class CarrinhoTest {

    private Carrinho carrinho;

    @BeforeEach
    public void setUp() {
        carrinho = new Carrinho();
    }

    @Test
    public void testCarrinhoInicialVazio() {
        assertEquals(0, carrinho.getQtdeItems());
        assertEquals(0.0, carrinho.getValorTotal(), 0.0001);
    }

    @Test
    public void testAdicionarItem() {
        Produto p1 = new Produto("Caneta", 2.50);
        carrinho.addItem(p1);
        assertEquals(1, carrinho.getQtdeItems());
    }

    @Test
    public void testRemoverItemExistente() throws ProdutoNaoEncontradoException {
        Produto p1 = new Produto("Caneta", 2.50);
        carrinho.addItem(p1);
        carrinho.removeItem(p1);
        assertEquals(0, carrinho.getQtdeItems());
    }

    @Test
    public void testRemoverItemNaoExistenteLancaExcecao() {
        Produto p1 = new Produto("Caneta", 2.50);
        Produto p2 = new Produto("Lápis", 1.00);
        carrinho.addItem(p1);

        assertThrows(ProdutoNaoEncontradoException.class, () -> {
            carrinho.removeItem(p2);
        });

        assertEquals(1, carrinho.getQtdeItems());
    }

    @Test
    public void testEsvaziarCarrinho() {
        carrinho.addItem(new Produto("Caneta", 2.50));
        carrinho.addItem(new Produto("Lápis", 1.00));
        carrinho.esvazia();
        assertEquals(0, carrinho.getQtdeItems());
    }

}