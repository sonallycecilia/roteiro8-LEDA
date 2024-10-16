package tad.conjuntoDinamico;

import tad.listasEncadeadas.ListaVaziaException;

public interface ConjuntoDinamicoIF<E> {
	
	public void inserir(E item);
	
	public E remover(E item) throws ListaVaziaException, ConjuntoVazioException;
	
	public E predecessor(E item) throws ConjuntoVazioException;
	
	public E sucessor(E item) throws ConjuntoVazioException;
	
	public int tamanho();
	
	public E buscar(E item);
	
	public E minimum() throws ConjuntoVazioException;
	
	public E maximum() throws ConjuntoVazioException;

}
