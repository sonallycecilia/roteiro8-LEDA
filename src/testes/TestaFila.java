import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tad.conjuntoDinamico.ConjuntoVazioException;
import tad.fila.FilaCheiaException;
import tad.fila.FilaIF;
import tad.fila.FilaVaziaException;
import tad.fila.MinhaFila;

import static org.junit.jupiter.api.Assertions.*;

public class TestaFila {

	private FilaIF<Integer> fila = null;

	@BeforeEach
	public void iniciar() {
		fila = new MinhaFila();
	}

	@Test
	public void enfileirarTest() {
		try {
			fila.enfileirar(3);
			assertEquals(Integer.valueOf(3), fila.verificarCabeca());
			fila.enfileirar(5);
			assertEquals(Integer.valueOf(3), fila.verificarCabeca());
			fila.enfileirar(7);
			assertEquals(Integer.valueOf(3), fila.verificarCabeca());
			fila.enfileirar(4);
			assertEquals(Integer.valueOf(3), fila.verificarCabeca());
			fila.enfileirar(2);
			assertEquals(Integer.valueOf(3), fila.verificarCabeca());
		} catch(FilaCheiaException | FilaVaziaException fce) {
			fail("fila cheia exception lançado indevidamente");
		}
	}

	@Test
	public void verificarCabecaTest() throws FilaVaziaException {
		assertNull(fila.verificarCabeca());
		try {
			fila.enfileirar(3);
			assertEquals(Integer.valueOf(3), fila.verificarCabeca());
			fila.enfileirar(5);
			assertEquals(Integer.valueOf(3), fila.verificarCabeca());
			fila.enfileirar(7);
			assertEquals(Integer.valueOf(3), fila.verificarCabeca());
			fila.enfileirar(4);
			assertEquals(Integer.valueOf(3), fila.verificarCabeca());
			fila.enfileirar(2);
			assertEquals(Integer.valueOf(3), fila.verificarCabeca());
		}catch (FilaCheiaException | FilaVaziaException fce) {
			fail("fila cheia exception lançado indevidamente");
		}

	}

	// configurar o tamanho da fila para 5
	@Test
	public void desenfileirarTest() {
		fila = new MinhaFila(5);
		try {
			fila.desenfileirar();
			fail("deveria lanar uma exceo quando chamar o desenfileirar de uma fila vazia");
		} catch (Exception e) {}
		try {
			fila.enfileirar(3);
			assertEquals(Integer.valueOf(3), fila.verificarCabeca());
			fila.enfileirar(5);
			assertEquals(Integer.valueOf(3), fila.verificarCabeca());
			fila.enfileirar(7);
			assertEquals(Integer.valueOf(3), fila.verificarCabeca());
			fila.enfileirar(4);
			assertEquals(Integer.valueOf(3), fila.verificarCabeca());
			fila.enfileirar(2);
			assertEquals(Integer.valueOf(3), fila.verificarCabeca());
			assertEquals(Integer.valueOf(3), fila.desenfileirar());
			assertEquals(Integer.valueOf(5), fila.verificarCabeca());

			fila.enfileirar(15);

			assertEquals(Integer.valueOf(5), fila.desenfileirar());
			assertEquals(Integer.valueOf(7), fila.verificarCabeca());

			fila.enfileirar(20);

			assertEquals(Integer.valueOf(7), fila.desenfileirar());
			assertEquals(Integer.valueOf(4), fila.verificarCabeca());

			assertEquals(Integer.valueOf(4), fila.desenfileirar());
			assertEquals(Integer.valueOf(2), fila.verificarCabeca());

			assertEquals(Integer.valueOf(2), fila.desenfileirar());
		} catch(FilaCheiaException fce) {
			fail("fila cheia exception lançado indevidamente");
		} catch(FilaVaziaException fve) {
			fail("fila vazia exception lançado indevidamente");
		}
	}

	@Test
	public void isEmptyTest() {
		try {
			assertTrue(fila.isEmpty());
			fila.enfileirar(3);
			assertEquals(Integer.valueOf(3), fila.verificarCabeca());
			assertFalse(fila.isEmpty());
			fila.desenfileirar();
			assertTrue(fila.isEmpty());
		} catch(FilaCheiaException fce) {
			fail("fila cheia exception lançado indevidamente");
		} catch(FilaVaziaException fve) {
			fail("fila vazia exception lançado indevidamente");
		}
	}

	@Test
	public void filaVaziaTest() {
		assertThrows(FilaVaziaException.class, () -> {
			fila.enfileirar(3);
			fila.enfileirar(2);
			fila.enfileirar(10);
			fila.desenfileirar();
			fila.desenfileirar();
			fila.desenfileirar();
			fila.desenfileirar();
		});
	}

	@Test
	public void filaCheiaTest() {
		assertThrows(FilaCheiaException.class, () -> {
			fila.enfileirar(3);
			fila.enfileirar(2);
			fila.enfileirar(10);
			fila.enfileirar(3);
			fila.enfileirar(2);
			fila.enfileirar(10);
			fila.enfileirar(5);
			fila.enfileirar(7);
			fila.enfileirar(10);
			fila.enfileirar(10);
			fila.enfileirar(11);
		});
	}

	// Neste teste a fila tem que estourar o tamanho depois de 999
	@Test
	public void enfileirarEstouroTeste() throws Exception {
		fila = new MinhaFila(1000);
		for (int i = 0; i < 1000; i++) {
			fila.enfileirar(i);
		}
		assertThrows(FilaCheiaException.class, () -> fila.enfileirar(1000));
	}
}