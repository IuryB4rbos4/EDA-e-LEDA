package sorting.variationsOfSelectionsort;

import sorting.AbstractSorting;

public class RecursiveSelectionSort<T extends Comparable<T>> extends
		AbstractSorting<T> {

	/**
	 * Implementação recursiva do selection sort. Você deve implementar apenas
	 * esse método sem usar nenhum outro método auxiliar (exceto
	 * Util.swap(array,int,int)). Para isso, tente definir o caso base do
	 * algoritmo e depois o caso indutivo, que reduz o problema para uma entrada
	 * menor em uma chamada recursiva. Seu algoritmo deve ter complexidade
	 * quadrática O(n^2).
	 */
	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		if (rightIndex > leftIndex) {

			int i_menor = leftIndex;
			for (int i = i_menor; i <= rightIndex; i++) {
				if (array[i].compareTo(array[i_menor]) < 0) {
					i_menor = i;
				}
			}

			swap(array, i_menor, leftIndex);

			if (leftIndex++ < rightIndex) {
				sort(array, leftIndex, rightIndex);
			}
		}

	}

	public static void swap(Object[] array, int i, int j) {
		if (array == null)
			throw new IllegalArgumentException();

		Object temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}

}
