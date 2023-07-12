package sorting.variationsOfSelectionsort;

import java.util.Arrays;

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

		if (leftIndex <= rightIndex) {
			int i_menor = leftIndex;
			for (int i = leftIndex + 1; i <= rightIndex; i++) {
				if (array[i].compareTo(array[i_menor]) < 0) {
					i_menor = i;
				}
			}

			util.Util.swap(array, i_menor, leftIndex);

			sort(array, ++leftIndex, rightIndex);
		}
	}

	// public static void main(String[] args) {
	// 	RecursiveSelectionSort<Integer> selection = new RecursiveSelectionSort<>();
	// 	Integer[] array = { 9, 13, 16, 21, 32, 12 };

	// 	selection.sort(array);
	// 	System.out.println(Arrays.toString(array));
	// }
}
