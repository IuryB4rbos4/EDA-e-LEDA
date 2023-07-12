package sorting.divideAndConquer;

import java.util.Arrays;

import sorting.AbstractSorting;
import util.Util;

/**
 * A mediana de uma colecao de valores é o valor que divide a coleção na metade.
 * A forma mais custosa de encontrar a mediana seria ordenar o array completo e
 * escolher o elemento da metade. Essa abordagem limita o tempo de execucao
 * ao tempo do algoritmo de ordenacao utilizado. Entretanto, existem outras
 * formas
 * de se encontrar a mediana usando-se estrategias dos algoritmos de ordenação
 * vistos
 * (excetuando-se mergesort e quicksort). Sabe-se que a mediana é um excelente
 * pivot
 * para garantir um bom tempo de execução do quicksort.
 * 
 * Voce deve implementar o algoritmo do quicksort que seleciona a mediana dos
 * dados
 * a serem ordenados como pivot. Considere o comentário acima para escolher a
 * mediana.
 * 
 * Obs: VOCE NAO PODE ORDENAR OS DADOS E ESCOLHER O ELEMENTO DO MEIO COMO
 * MEDIANA!!!
 * Qualquer metodo auxiliar deve ser implementado nesta classe!
 * 
 */
public class QuickSortComMediana<T extends Comparable<T>> extends AbstractSorting<T> {

	@Override
	public void sort(T[] array) {
		sort(array, 0, array.length - 1);
	}

	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		if (leftIndex < rightIndex) {
			int pivotIndex = partition(array, leftIndex, rightIndex);
			sort(array, leftIndex, pivotIndex - 1);
			sort(array, pivotIndex + 1, rightIndex);
		}
	}

	private int partition(T[] array, int leftIndex, int rightIndex) {
		int pivotIndex = medianaThree(array, leftIndex, rightIndex);
		
		Util.swap(array, leftIndex, pivotIndex);

		T pivot = array[leftIndex];

		int index = leftIndex;

		for (int i = leftIndex + 1; i <= rightIndex; i++) {
			if (array[i].compareTo(pivot) <= 0) {
				index++;
				Util.swap(array, index, i);
			}
		}

		Util.swap(array, leftIndex, index);

		return index;
	}

	private int medianaThree(T[] array, int leftIndex, int rightIndex) {
		int mid = (leftIndex + rightIndex) / 2;

		if (array[leftIndex].compareTo(array[mid]) > 0) {
			Util.swap(array, leftIndex, mid);
		}

		if (array[leftIndex].compareTo(array[rightIndex]) > 0) {
			Util.swap(array, leftIndex, rightIndex);

		}

		if (array[mid].compareTo(array[rightIndex]) > 0) {
			Util.swap(array, mid, rightIndex);
		}

		return mid;
	}

	public static void main(String[] args) {
		QuickSortComMediana quick = new QuickSortComMediana();
		Integer[] array2 = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
		Integer[] array3 = { 10, 9, 8, 7, 6, 5, 4, 3, 2, 1 };
		Integer[] array4 = { 5, 2, 8, 2, 9, 1, 5, 3, 8 };
		Integer[] array5 = { -6, -3, -8, -1, -5, -2, -4 };
		Integer[] array = { 9, 4, 6, 2, 7, 1, 5 };
		quick.sort(array);
		System.out.println(Arrays.toString(array));
		quick.sort(array2);
		System.out.println(Arrays.toString(array2));
		quick.sort(array3);
		System.out.println(Arrays.toString(array3));
		quick.sort(array4);
		System.out.println(Arrays.toString(array4));
		quick.sort(array5);
		System.out.println(Arrays.toString(array5));

	}

}
