package sorting.variationsOfBubblesort;

import java.util.Arrays;

import sorting.AbstractSorting;

public class RecursiveBubbleSort<T extends Comparable<T>> extends
		AbstractSorting<T> {

	/**
	 * Implementação recursiva do bubble sort. Você deve implementar apenas esse
	 * método sem usar nenhum outro método auxiliar (exceto
	 * Util.swap(array,int,int)). Para isso, tente definir o caso base do
	 * algoritmo e depois o caso indutivo, que reduz o problema para uma entrada
	 * menor em uma chamada recursiva. Seu algoritmo deve ter complexidade
	 * quadrática O(n^2).
	 */
	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {

		if (leftIndex <= rightIndex) {
			boolean swapped = false;

			for (int i = leftIndex; i < rightIndex; i++) {
				if (array[i].compareTo(array[i + 1]) > 0) {
					util.Util.swap(array, i, i + 1);
					swapped = true;
				}
			}

			if (swapped && rightIndex - 1 > leftIndex) {
				sort(array, leftIndex, rightIndex - 1);
			}
		}
	}

	// public static void main(String[] args) {
	// 	RecursiveBubbleSort<Integer> buble = new RecursiveBubbleSort<>();
	// 	Integer[] array = { 9, 13, 16, 21, 32, 12 };

	// 	buble.sort(array);
	// 	System.out.println(Arrays.toString(array));
	// }

}
