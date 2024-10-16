import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tad.conjuntoDinamico.ConjuntoDinamicoIF;
import tad.conjuntoDinamico.ConjuntoVazioException;
import tad.conjuntoDinamico.MeuConjuntoDinamico;
import tad.listasEncadeadas.ListaVaziaException;

import static org.junit.jupiter.api.Assertions.*;

public class ConjuntoDinamicoTestCase {

	private ConjuntoDinamicoIF<Integer> cd = null;
	
	@BeforeEach
	public void iniciar() {
		cd = new MeuConjuntoDinamico();
	}
	
	@Test
	public void tamanhoTest() {
		assertEquals(0, cd.tamanho());
		cd.inserir(2);
		assertEquals(1, cd.tamanho());
		cd.inserir(2);
		assertEquals(2, cd.tamanho());
		cd.inserir(1);
		assertEquals(3, cd.tamanho());
		cd.inserir(5);
		assertEquals(4, cd.tamanho());
	}
	
	@Test
	public void inserirTeste() {
		assertEquals(0, cd.tamanho());
		cd.inserir(9);
		assertEquals(1, cd.tamanho());
		cd.inserir(4);
		assertEquals(2, cd.tamanho());
		cd.inserir(1);
		assertEquals(3, cd.tamanho());
		cd.inserir(1);
		assertEquals(4, cd.tamanho());
	}
	
	@Test
	public void removerTeste() throws Exception {
		assertEquals(0, cd.tamanho());
		cd.inserir(2);
		cd.inserir(2);
		cd.inserir(1);
		cd.inserir(5);
		assertEquals(Integer.valueOf(2), cd.remover(2));
		assertEquals(3, cd.tamanho());
		
		assertEquals(Integer.valueOf(1), cd.remover(1));
		assertEquals(2, cd.tamanho());
		
		assertEquals(Integer.valueOf(5), cd.remover(5));
		assertEquals(1, cd.tamanho());
		
		assertEquals(Integer.valueOf(2), cd.remover(2));
		assertEquals(0, cd.tamanho());
	}
	
	@Test
	public void removerFailTeste() throws Exception {
		assertThrows(ConjuntoVazioException.class, () -> cd.remover(3));
		cd.inserir(4);
		cd.inserir(5);
		cd.inserir(10);
		cd.inserir(8);
	}
	
	@Test
	public void removerFail2Teste() throws ConjuntoVazioException, ListaVaziaException {
		cd.inserir(4);
		cd.inserir(5);
		cd.inserir(10);
		cd.inserir(8);
		assertNull(cd.remover(3));
	}
	
	@Test
	public void buscarTeste() throws Exception {
		cd.inserir(4);
		cd.inserir(5);
		cd.inserir(10);
		cd.inserir(8);
		assertEquals(Integer.valueOf(10), cd.buscar(10));
	}
	
	@Test
	public void buscarFailTeste() throws Exception {
		cd.inserir(4);
		cd.inserir(5);
		cd.inserir(10);
		cd.inserir(8);
		assertNull(cd.buscar(1));
	}
	
	@Test
	public void minimumTeste() throws Exception {
		try {
			cd.minimum();
			fail("deveria lancar excecao quando chamar minimum num conjunto vazio");
		}catch (Exception e) {}
		
		cd.inserir(4);
		cd.inserir(5);
		cd.inserir(10);
		cd.inserir(8);
		assertEquals(Integer.valueOf(4),cd.minimum());
		
	}
	
	@Test
	public void maximumTest() throws Exception {
		try {
			cd.maximum();
			fail("deveria lancar excecao quando chamar minimum num conjunto vazio");
		}catch (Exception e) {}
		
		cd.inserir(5);
		cd.inserir(4);
		cd.inserir(10);
		cd.inserir(8);
		assertEquals(Integer.valueOf(10),cd.maximum());
	}
	
	@Test
	public void sucessorTeste() throws Exception {
		try {
			cd.sucessor(5);
			fail("deveria lanar exceo quando chamar minimum num conjunto vazio");
		}catch (Exception e) {}
		
		cd.inserir(4);
		cd.inserir(5);
		cd.inserir(10);
		cd.inserir(8);
		assertEquals(criarInteiro(5),cd.sucessor(4));
		assertEquals(criarInteiro(10),cd.sucessor(5));
		assertNull(cd.sucessor(8));
		assertEquals(criarInteiro(8),cd.sucessor(10));
	}

	private static Integer criarInteiro(int valor) {
		return Integer.valueOf(valor);
	}

	@Test
	public void predecessorTeste() throws Exception {
		try {
			cd.predecessor(5);
			fail("deveria lanar exceo quando chamar minimum num conjunto vazio");
		}catch (Exception e) {}
		
		cd.inserir(4);
		cd.inserir(5);
		cd.inserir(10);
		cd.inserir(8);
		assertNull(cd.predecessor(4));
		assertEquals(criarInteiro(5),cd.predecessor(10));
		assertEquals(criarInteiro(4),cd.predecessor(5));
		assertEquals(criarInteiro(10),cd.predecessor(8));
		
		try {
			cd.predecessor(83);
			fail("deveria lanar exceo quando chamar minimum num conjunto vazio");
		}catch (Exception e) {}
	}

}
