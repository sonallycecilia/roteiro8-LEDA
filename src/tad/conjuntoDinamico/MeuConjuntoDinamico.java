package tad.conjuntoDinamico;

public class MeuConjuntoDinamico implements ConjuntoDinamicoIF<Integer> {

	private Integer[] meusDados; //array com os dados
	private int posInsercao;

	public MeuConjuntoDinamico(){
		meusDados = new Integer[10];
		posInsercao = 0;
	}
	@Override
	public void inserir(Integer item) {
		if (arrayEstaCheio()) {
			meusDados = aumentarArray();
		}
		meusDados[posInsercao] = item;
		posInsercao++;
	}

	private boolean arrayEstaCheio() {
		return posInsercao == meusDados.length;
    }

	private Integer[] aumentarArray() {
		Integer[] arrayMaior = new Integer[posInsercao * 2]; // criar um array maior (arrayMaior)
		for (int i = 0; i < meusDados.length; i++){ // copiar os dados de meusDados (array cheio)
			arrayMaior[i] = meusDados[i];
		}
		return arrayMaior;
	}

	@Override
	public Integer remover(Integer item) throws ConjuntoVazioException{
		if (posInsercao == 0) {
			throw new ConjuntoVazioException("O conjunto está vazio.");
		}
		Integer temp = null;
		boolean existe = false;

		for (int i = 0; i < posInsercao; i++){
			if (meusDados[i].equals(item)){
				temp = meusDados[i];
				meusDados[i] = null;
				existe = true;
				for (int j = i; j < posInsercao; j++){
					meusDados[j] = meusDados[j+1]; // movendo os dados

				}
				meusDados[posInsercao - 1] = null;
            	posInsercao--;
			}
		}
		if (!existe){
			System.out.println("Falha ao remover o item " + item + " pois ele não existe nos dados.");
		}
		return temp;
	}

	@Override
	public Integer predecessor(Integer item) throws ConjuntoVazioException {
		if (posInsercao == 0){
			throw new ConjuntoVazioException("O conjunto esta vazio.");
		}
		if(buscar(item) != null){
			for (int i = 1; i < posInsercao; i++){
				if (meusDados[i].equals(item)){
					return meusDados[i-1];
				}
			}
		} else {
			throw new ConjuntoVazioException("O item não existe");
		}
		return null;
	}

	@Override
	public Integer sucessor(Integer item) throws ConjuntoVazioException {
		if (posInsercao == 0){
			throw new ConjuntoVazioException("O conjunto esta vazio.");
		}
		if (buscar(item) != null){
			for (int i = 0; i < posInsercao - 1; i++){
				if (meusDados[i].equals(item)){
					return meusDados[i+1];
				}
			}
		}
		return null;
	}

	@Override
	public int tamanho() {
		return posInsercao;
	}

	@Override
	public Integer buscar(Integer item) {
		Integer temp = null;
		if (posInsercao > 0){
			for (int i = 0; i < posInsercao; i++){
				if (meusDados[i].equals(item)){
					temp = meusDados[i];
					break;
				}
			}
		}
		return temp;
	}

	@Override
	public Integer minimum() throws ConjuntoVazioException{
		Integer min;
		if (posInsercao == 0){
			throw new ConjuntoVazioException("O conjunto está vazio.");
		} else{
			min = meusDados[0];
			for(int i = 1; i < posInsercao; i++){
				if(min > meusDados[i]){
					min = meusDados[i];
				}
			}
		}
		return min;
	}

	@Override
	public Integer maximum() throws ConjuntoVazioException{
		Integer max;
		if (posInsercao == 0){
			throw new ConjuntoVazioException("O conjunto está vazio");
		} else{
			max = meusDados[0];
			for(int i = 1; i < posInsercao; i++){
				if(max < meusDados[i]){
					max = meusDados[i];
				}
			}
		}
		return max;
	}
}
