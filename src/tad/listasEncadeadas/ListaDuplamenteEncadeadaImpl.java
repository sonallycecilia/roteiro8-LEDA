package tad.listasEncadeadas;

public class ListaDuplamenteEncadeadaImpl<T extends Comparable<T>> implements ListaDuplamenteEncadeadaIF<T> {

	//TODO: implementar o nó cabeça
	//TODO: implementar o nó cauda
	//TODO: implementar as sentinelas

	public NodoListaDuplamenteEncadeada<T> cabeca = null; // Estratégia usando marcação sentinela
	NodoListaDuplamenteEncadeada<T> cauda = null;// Estratégia usando marcação sentinela

	public ListaDuplamenteEncadeadaImpl() {// Estratégia usando marcação sentinela
		cabeca = new NodoListaDuplamenteEncadeada<T>();
		cauda = new NodoListaDuplamenteEncadeada<T>();
		cabeca.setProximo(cauda);

		// lista circular
		cabeca.setAnterior(cauda);
		cauda.setAnterior(cabeca);

	}

	@Override
	public boolean isEmpty() {
		return (cabeca==null || cabeca.isNull()) && (cauda ==null || cauda.isNull());
	}

	@Override
	public int size() {
		int tamanho = 0;
		if(!isEmpty()){
			NodoListaDuplamenteEncadeada<T> atual = cabeca;
			do {
				tamanho++;
				atual = atual.getProximo();
			} while (atual != cabeca);
		}
		return tamanho;
	}

	@Override
	public NodoListaDuplamenteEncadeada<T> search(T chave) {
		NodoListaDuplamenteEncadeada<T> atual = null;
		if (!isEmpty()){
			atual = cabeca;
			if (!atual.getChave().equals(chave)){
				do {
					if (atual.getChave().equals(chave)) {
						break;
					}atual = atual.getProximo();
				} while (atual != cabeca);
				if (atual.getChave() != chave){
					atual = null;
				}
			}
		}
		return atual;
	}

	@Override
	public void insert(T chave) {
		NodoListaDuplamenteEncadeada<T> novoNo = new NodoListaDuplamenteEncadeada<T>(chave);
		if (isEmpty()) {
			cabeca = novoNo;
			cauda = novoNo;
			cabeca.setProximo(cabeca);
			cabeca.setAnterior(cabeca);
		} else if (cauda == null) {
			novoNo.setProximo(cabeca);
			novoNo.setAnterior(cabeca);
			cauda = novoNo;
		} else {
			NodoListaDuplamenteEncadeada<T> ultimo = cauda;

			ultimo.setProximo(novoNo);
			novoNo.setAnterior(ultimo);
			novoNo.setProximo(cabeca);
			cabeca.setAnterior(novoNo);

			cauda = novoNo;
		}
	}

	@Override
	public NodoListaDuplamenteEncadeada<T> remove(T chave) throws ListaVaziaException {
		if (isEmpty()){
			throw new ListaVaziaException();
		}
		NodoListaDuplamenteEncadeada<T> removido = search(chave);
		if (removido != null) {
			if (removido == cabeca && removido == cauda) {
				cabeca = null;
				cauda = null;
			} else {
				if (removido == cabeca) {
					cabeca = removido.getProximo();
				}

				if (removido == cauda) {
					cauda = removido.getAnterior();
				}

				removido.getAnterior().setProximo(removido.getProximo());
				removido.getProximo().setAnterior(removido.getAnterior());
			}
		}
		return removido;
	}

	@Override
	public String imprimeEmOrdem() {
		Integer[] elementos;
		String saida = "";
		if (!isEmpty()){
			elementos = new Integer[this.size()];
			NodoListaDuplamenteEncadeada<T> atual = cabeca;
			for (int index = 0; index<elementos.length; index++){
				elementos[index] = (Integer) atual.getChave();
				atual = atual.getProximo();
			}
			StringBuilder sb = new StringBuilder();
			sb.append(elementos[0]);

			for (int index = 1; index < elementos.length; index++) {
				sb.append(", ").append(elementos[index]);
			}

			saida = sb.toString();
		}

		return saida;
	}

	@Override
	public String imprimeInverso() {
		Integer[] elementos;
		String saida = "";
		if (!isEmpty()){
			elementos = new Integer[this.size()];
			NodoListaDuplamenteEncadeada<T> atual = cabeca;
			for (int index = this.size()-1; index>=0; index--){
				elementos[index] = (Integer) atual.getChave();
				atual = atual.getProximo();
			}
			StringBuilder sb = new StringBuilder();
			sb.append(elementos[0]);

			for (int index = 1; index < elementos.length; index++) {
				sb.append(", ").append(elementos[index]);
			}

			saida = sb.toString();
		}

		return saida;
	}

	@Override
	public NodoListaDuplamenteEncadeada<T> sucessor(T chave) {
		NodoListaDuplamenteEncadeada<T> atual = search(chave);
		if (atual == cauda){
			atual = null;
		} else{
			atual = atual.getProximo();
		}
		return atual;
	}

	@Override
	public NodoListaDuplamenteEncadeada<T> predecessor(T chave) {
		NodoListaDuplamenteEncadeada<T> atual = search(chave);
		if (atual == cabeca){
			atual = null;
		} else{
			atual = atual.getAnterior();
		}
		return atual;
	}

	@Override
	public T[] toArray(Class<T> clazz) {
		Integer[] elementos = null;
		if (!isEmpty()){
			elementos = new Integer[this.size()];
			NodoListaDuplamenteEncadeada<T> atual = cabeca;
			for (int index = 0; index<this.size(); index++){
				elementos[index] = ((Integer) atual.getChave());
				atual = atual.getProximo();
			}
		}

		return (T[]) elementos;
	}

	@Override
	public void inserePrimeiro(T elemento) {
		NodoListaDuplamenteEncadeada<T> novoNo = new NodoListaDuplamenteEncadeada<T>(elemento);
		novoNo.setProximo(cabeca);
		novoNo.setAnterior(cauda);
		cabeca.setAnterior(null);
		cauda.setProximo(novoNo);
		cabeca = novoNo;

	}

	@Override
	public NodoListaDuplamenteEncadeada<T> removeUltimo() {
		NodoListaDuplamenteEncadeada<T> ultimo = cauda;

		if (cabeca == cauda) {
			cabeca = null;
			cauda = null;
		} else {
			NodoListaDuplamenteEncadeada<T> penultimo = ultimo.getAnterior();
			penultimo.setProximo(cabeca);
			cabeca.setAnterior(penultimo);
			cauda = penultimo;
		}

		return ultimo;
	}

	@Override
	public NodoListaDuplamenteEncadeada<T> removePrimeiro() {
		NodoListaDuplamenteEncadeada<T> primeiro = cabeca;

		if (cabeca == cauda) {
			cabeca = null;
			cauda = null;
		} else {
			NodoListaDuplamenteEncadeada<T> segundo = primeiro.getProximo();
			cabeca = segundo;
			cabeca.setAnterior(null);

			cauda.setProximo(cabeca);
		}

		return primeiro;
	}

	@Override
	public void insert(T chave, int index) {
		throw new UnsupportedOperationException("Precisa implementar!");
	}

}