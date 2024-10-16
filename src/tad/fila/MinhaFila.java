package tad.fila;

/**
 * Fila deve ser implementada com array fixo e estratégia circular
 * de gerenciamento de apontadores de cauda e cabeça.
 * @author fabioleite
 *
 */
public class MinhaFila implements FilaIF<Integer> {

	private int tamanho = 10;

	private int cauda = 0;
	private int cabeca = 0;

	private Integer[] meusDados = null;

	public MinhaFila(int tamanhoInicial) {
		tamanho = tamanhoInicial;
		meusDados = new Integer[tamanho];
	}

	public MinhaFila() {
		meusDados = new Integer[tamanho];
	}

	@Override
	public void enfileirar(Integer item) throws FilaCheiaException {
		if (isFull()) {
			throw new FilaCheiaException();
		}
		this.meusDados[this.cauda] = item;
		if (this.cauda < this.meusDados.length) {
			this.cauda++;
		}
	}

	@Override
	public Integer desenfileirar() throws FilaVaziaException {
		if (isEmpty()) {
			throw new FilaVaziaException();
		}
		int desenfileirado = this.meusDados[cabeca];
		for(int index = this.cabeca; index<this.cauda-1;index++){
			this.meusDados[index] = this.meusDados[index+1];
		}
		this.meusDados[cauda-1] = null;
		cauda--;
		return desenfileirado;
	}

	@Override
	public Integer verificarCauda() throws FilaVaziaException {
		Integer retorno = null;
		if (!isEmpty()) {
			retorno = meusDados[cauda];
		}
		return retorno;
	}

	@Override
	public Integer verificarCabeca() throws FilaVaziaException {
		Integer retorno = null;
		if (!isEmpty()) {
			retorno = meusDados[cabeca];
		}
		return retorno;
	}

	@Override
	public boolean isEmpty() {
		return this.cabeca == this.cauda;
	}

	@Override
	public boolean isFull() {
		return this.cauda == this.tamanho;
	}
}