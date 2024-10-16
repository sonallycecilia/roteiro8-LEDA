import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tad.pilha.MinhaPilha;
import tad.pilha.PilhaCheiaException;
import tad.pilha.PilhaIF;
import tad.pilha.PilhaVaziaException;

import static org.junit.jupiter.api.Assertions.*;

public class TestaPilha {

	protected PilhaIF<Integer> pilha = null;

	@BeforeEach
	public void iniciar() {
		pilha = new MinhaPilha();
	}

	@Test
	public void empilharTest() {
		try {
			pilha.empilhar(3);

			assertEquals(Integer.valueOf(3), pilha.topo());
			pilha.empilhar(5);
			assertEquals(Integer.valueOf(5), pilha.topo());
			pilha.empilhar(7);
			assertEquals(Integer.valueOf(7), pilha.topo());
			pilha.empilhar(4);
			assertEquals(Integer.valueOf(4), pilha.topo());
			pilha.empilhar(2);
			assertEquals(Integer.valueOf(2), pilha.topo());
		} catch (PilhaCheiaException e) {
			fail("empilharTest está estourando a pilha erroneamente");
		}
	}

	@Test
	public void topoTest() {
		assertNull(pilha.topo());
		try {
			pilha.empilhar(3);
			assertEquals(Integer.valueOf(3), pilha.topo());
			pilha.empilhar(5);
			assertEquals(Integer.valueOf(5), pilha.topo());
			pilha.empilhar(7);
			assertEquals(Integer.valueOf(7), pilha.topo());
			pilha.empilhar(4);
			assertEquals(Integer.valueOf(4), pilha.topo());
			pilha.empilhar(2);
			assertEquals(Integer.valueOf(2), pilha.topo());
		} catch (PilhaCheiaException e) {
			fail(" está estourando a pilha erroneamente");
		}

	}

	@Test
	public void desempilharTest() {
		try {
			pilha.desempilhar();
			fail("deveria lançar uma exceção quando chamar o desempilhar de uma pilha vazia");
		} catch (Exception e) {}
		try {
			pilha.empilhar(3);
			assertEquals(Integer.valueOf(3), pilha.topo());
			pilha.empilhar(5);
			assertEquals(Integer.valueOf(5), pilha.topo());
			pilha.empilhar(7);
			assertEquals(Integer.valueOf(7), pilha.topo());
			pilha.empilhar(4);
			assertEquals(Integer.valueOf(4), pilha.topo());
			pilha.empilhar(2);
		} catch (PilhaCheiaException e) {
			fail(" está estourando a pilha erroneamente");
		}
		try {
			assertEquals(Integer.valueOf(2), pilha.topo());
			assertEquals(Integer.valueOf(2), pilha.desempilhar());
			assertEquals(Integer.valueOf(4), pilha.topo());

			assertEquals(Integer.valueOf(4), pilha.desempilhar());
			assertEquals(Integer.valueOf(7), pilha.topo());

			assertEquals(Integer.valueOf(7), pilha.desempilhar());
			assertEquals(Integer.valueOf(5), pilha.topo());

			assertEquals(Integer.valueOf(5), pilha.desempilhar());
			assertEquals(Integer.valueOf(3), pilha.topo());

			assertEquals(Integer.valueOf(3), pilha.topo());
		} catch (PilhaVaziaException e) {
			fail(" está esvaziando a pilha erroneamente");
		}
	}

	@Test
	public void isEmptyTest() {
		assertTrue(pilha.isEmpty());
		try {
			pilha.empilhar(3);
			assertEquals(Integer.valueOf(3), pilha.topo());
			assertFalse(pilha.isEmpty());
			pilha.desempilhar();
			assertTrue(pilha.isEmpty());
			pilha.empilhar(4);
			pilha.empilhar(6);
			assertFalse(pilha.isEmpty());
		} catch (PilhaCheiaException e) {
			fail(" está estourando a pilha erroneamente");
		} catch (PilhaVaziaException e) {
			fail(" está esvaziando a pilha erroneamente");
		}
	}

	@Test
	public void pilhaVaziaTest() {
		assertThrows(PilhaVaziaException.class, () -> {
			pilha.empilhar(3);
			pilha.empilhar(2);
			pilha.empilhar(10);
			pilha.desempilhar();
			pilha.desempilhar();
			pilha.desempilhar();
			pilha.desempilhar();
		});
	}

	@Test
	public void pilhaCheiaTest() {
		pilha = new MinhaPilha(5);
		/*Se não redefinir o tamanho da pilha não tem sentido o teste.
		 *Pois, com a quantidade de inserções não será estourado o tamanho da pilha*/
		assertThrows(PilhaCheiaException.class, () -> {
			pilha.empilhar(3);
			pilha.empilhar(2);
			pilha.empilhar(10);
			pilha.empilhar(3);
			pilha.empilhar(2);
			pilha.empilhar(10);
		});
	}

	@Test
	public void multitopTest() throws PilhaCheiaException, PilhaVaziaException {
		PilhaIF<Integer> saida = new MinhaPilha();
		PilhaIF<Integer> saida2 = new MinhaPilha();
		try {
			pilha.empilhar(3);
			pilha.empilhar(2);
			pilha.empilhar(10);
			pilha.empilhar(3);
			saida.empilhar(3);
			saida.empilhar(10);
			saida.empilhar(2);
			saida2.empilhar(3);
		} catch (PilhaCheiaException e) {
			fail("Estouro inexperado da pilha");
		}
		assertEquals(saida, pilha.multitop(3)); // 3, 10, 2
		assertEquals(saida2,pilha.multitop(5)); //

		assertThrows(PilhaVaziaException.class, () -> {
			pilha.multitop(10);
		});
	}
}