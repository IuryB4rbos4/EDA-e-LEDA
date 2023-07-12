package sorting.divideAndConquer.hybridMergesort;

import sorting.AbstractSorting;

/**
 * A classe HybridMergeSort representa a implementação de uma variação do
 * MergeSort que pode fazer uso do InsertionSort (um algoritmo híbrido) da
 * seguinte forma: o MergeSort é aplicado a entradas maiores a um determinado
 * limite. Caso a entrada tenha tamanho menor ou igual ao limite o algoritmo usa
 * o InsertionSort.
 * 
 * A implementação híbrida deve considerar os seguintes detalhes:
 * - Ter contadores das quantidades de MergeSorts e InsertionSorts aplicados, de
 * forma
 * que essa informação possa ser capturada pelo teste.
 * - A cada chamado do método de sort(T[] array) esses contadores são resetados.
 * E a cada chamada
 * interna de um merge ou insertion, os contadores MERGESORT_APPLICATIONS e
 * INSERTIONSORT_APPLICATIONS são incrementados.
 * - O InsertionSort utilizado no algoritmo híbrido deve ser in-place.
 */
public class HybridMergeSort<T extends Comparable<T>> extends
		AbstractSorting<T> {

	/**
	 * For inputs with size less or equal to this value, the insertionsort
	 * algorithm will be used instead of the mergesort.
	 */
	public static final int SIZE_LIMIT = 4;

	protected static int MERGESORT_APPLICATIONS = 0;
	protected static int INSERTIONSORT_APPLICATIONS = 0;

	public void sort(T[] array, int leftIndex, int rightIndex) {
		if (rightIndex - leftIndex <= SIZE_LIMIT) {
			insertion(array, leftIndex, rightIndex);
			this.INSERTIONSORT_APPLICATIONS++;
		} else {
			int i_meio = (leftIndex + rightIndex) / 2;
			sort(array, leftIndex, i_meio);
			sort(array, i_meio + 1, rightIndex);
			merge(array, leftIndex, rightIndex);
			this.MERGESORT_APPLICATIONS++;
		}
	}

	public void sort(T[] array) {
		this.MERGESORT_APPLICATIONS = 0;
		this.INSERTIONSORT_APPLICATIONS = 0;
		sort(array, 0, array.length - 1);
	}

	public void insertion(T[] array, int leftIndex, int rightIndex) {

		if (leftIndex <= rightIndex) {
			for (int i = leftIndex; i <= rightIndex; i++) {
				int j = i;
				while (j > leftIndex && array[j].compareTo(array[j - 1]) < 0) {
					util.Util.swap(array, j, j - 1);
					j--;
				}
			}
		}

	}

	private void merge(T[] array, int leftIndex, int rightIndex) {

		T[] newArray = (T[]) new Comparable[array.length];

		for (int i = leftIndex; i <= rightIndex; i++) {
			newArray[i] = array[i];
		}

		int i = leftIndex;
		int i_meio = (leftIndex + rightIndex) / 2;
		int j = i_meio + 1;
		int k = leftIndex;

		while (i <= i_meio && j <= rightIndex) {
			if (newArray[i].compareTo(newArray[j]) <= 0) {
				array[k++] = newArray[i++];
			} else {
				array[k++] = newArray[j++];
			}
		}

		while (i <= i_meio) {
			array[k++] = newArray[i++];
		}

		while (j <= rightIndex) {
			array[k++] = newArray[j++];
		}
	}
}
