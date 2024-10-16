package tad.pilha;

public class MinhaPilha implements PilhaIF<Integer> {

	private int tamanho = 10;
	private int cabeca = 0;
	private Integer[] meusDados = null;

	public MinhaPilha(int tamanho) {
		this.tamanho = tamanho;
		meusDados = new Integer[tamanho];
	}

	public MinhaPilha() {
		meusDados = new Integer[tamanho];
	}

	@Override
	public void empilhar(Integer item) throws PilhaCheiaException {
		if (cabeca==tamanho-1){
			throw new PilhaCheiaException();
		} else {
			this.meusDados[this.cabeca] =item;
			if (this.cabeca<this.tamanho){
				this.cabeca++;
			}
		}
	}

	@Override
	public Integer desempilhar() throws PilhaVaziaException {
		if (isEmpty()){
			throw new PilhaVaziaException();
		}
		Integer desempilhado = this.topo();
		this.meusDados[cabeca-1] = null;
		this.cabeca--;
		return desempilhado;
	}

	@Override
	public Integer topo() {
		Integer retorno = null;
		if (!isEmpty()){
			retorno = this.meusDados[cabeca-1];
		}
		return retorno;
	}

	@Override
	public PilhaIF<Integer> multitop(int k) throws PilhaCheiaException, PilhaVaziaException {
		PilhaIF<Integer> retorno = null;
		if (k > this.tamanho-1) {
			throw new PilhaVaziaException();
		}
		if (k == cabeca+1){
			retorno = new MinhaPilha();
			retorno.empilhar(this.meusDados[cabeca-1]);
		} else{
			retorno = new MinhaPilha();
			for (int i = 1; i <= k  ; i++) {
				retorno.empilhar(this.meusDados[cabeca-i]);
			}
		}

		return retorno;
	}

	@Override
	public boolean isEmpty() {
		return cabeca==0;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}
		MinhaPilha outraPilha = (MinhaPilha) obj;

		if (this.cabeca != outraPilha.cabeca) {
			return false;
		}
		for (int i = 0; i < cabeca; i++) {
			if (!this.meusDados[i].equals(outraPilha.meusDados[i])) {
				return false;
			}
		}

		return true;
	}
}