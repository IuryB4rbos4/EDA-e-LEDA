package sorting.divideAndConquer.quicksort3;

import sorting.AbstractSorting;
import util.Util;

/**
 * A classe QuickSortMedianOfThree representa uma variação do QuickSort que
 * funciona de forma ligeiramente diferente. Relembre que quando o pivô
 * escolhido divide o array aproximadamente na metade, o QuickSort tem um
 * desempenho perto do ótimo. Para aproximar a entrada do caso ótimo, diversas
 * abordagens podem ser utilizadas. Uma delas é usar a mediana de 3 para achar o
 * pivô. Essa técnica consiste no seguinte:
 * 1. Comparar o elemento mais a esquerda, o central e o mais a direita do intervalo.
 * 2. Ordenar os elementos, tal que: A[left] < A[center] < A[right].
 * 3. Adotar o A[center] como pivô.
 * 4. Colocar o pivô na penúltima posição A[right-1].
 * 5. Aplicar o particionamento considerando o vetor menor, de A[left+1] até A[right-1].
 * 6. Aplicar o algoritmo na particao a esquerda e na particao a direita do pivô.
 */
public class QuickSortMedianOfThree<T extends Comparable<T>> extends
		AbstractSorting<T> {

	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		if (leftIndex < rightIndex) {
			int pivotIndex = partition(array, leftIndex, rightIndex);
			sort(array, leftIndex, pivotIndex - 1);
			sort(array, pivotIndex + 1, rightIndex);
		}
	}


	// A QUESTÃO PEDE PARA IR DO PENULTIMO ATÉ O PRIMEIRO
	// EU FIZ DO PRIMEIRO ATÉ O PENULTIMO
	// NÃO VOU MUDAR PORQUE A LÓGICA É A MESMA
	// SÓ É PRECISO INVERTER O PIVOT E A ESTRUTURA DE REPETIÇÃO...
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
}
