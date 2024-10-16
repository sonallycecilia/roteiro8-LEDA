package tad.listasEncadeadas;

import tad.fila.FilaCheiaException;
import tad.fila.FilaIF;
import tad.fila.FilaVaziaException;

public class FilaListaEncadeada<T extends Comparable<T>> implements FilaIF<T> {

	private NodoListaEncadeada<T> inicio;  // Cabeça da fila
	private NodoListaEncadeada<T> fim;     // Cauda da fila
	private int tamanho;                   // Tamanho atual da fila

	public FilaListaEncadeada() {
		this.inicio = null;
		this.fim = null;
		this.tamanho = 0;
	}

	@Override
	public void enfileirar(T item) throws FilaCheiaException {
		NodoListaEncadeada<T> novoNodo = new NodoListaEncadeada<>(item);

		if (isEmpty()) {
			inicio = fim = novoNodo;  // Primeiro elemento na fila
		} else {
			fim.setProximo(novoNodo); // Adiciona no final
			fim = novoNodo;           // Atualiza a referência do fim
		}
		tamanho++;
	}

	@Override
	public T desenfileirar() throws FilaVaziaException {
		if (isEmpty()) {
			throw new FilaVaziaException("A fila está vazia.");
		}

		T chave = inicio.getChave();          // Obtém o valor do início
		inicio = inicio.getProximo();         // Move o ponteiro para o próximo nó

		if (inicio == null) {  // Se o início ficou nulo, a fila está vazia
			fim = null;
		}

		tamanho--;
		return chave;
	}

	@Override
	public T verificarCauda() {
		if (isEmpty()) return null;
		return fim.getChave();  // Retorna o valor no fim da fila
	}

	@Override
	public T verificarCabeca() {
		if (isEmpty()) return null;
		return inicio.getChave();  // Retorna o valor no início da fila
	}

	@Override
	public boolean isEmpty() {
		return inicio == null;
	}

	@Override
	public boolean isFull() {
		return false;  // A fila encadeada não tem limite fixo
	}

	public int tamanho() {
		return tamanho;  // Retorna o tamanho atual da fila
	}
}
