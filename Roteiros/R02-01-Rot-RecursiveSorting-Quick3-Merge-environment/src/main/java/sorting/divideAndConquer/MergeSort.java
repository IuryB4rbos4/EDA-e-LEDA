package sorting.divideAndConquer;

import sorting.AbstractSorting;

/**
 * Merge sort is based on the divide-and-conquer paradigm. The algorithm
 * consists of recursively dividing the unsorted list in the middle, sorting
 * each sublist, and then merging them into one single sorted list. Notice that
 * if the list has length == 1, it is already sorted.
 */
public class MergeSort<T extends Comparable<T>> extends AbstractSorting<T> {

	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {

		if (leftIndex < rightIndex) {
			int i_meio = (leftIndex + rightIndex) / 2;
			sort(array, leftIndex, i_meio);
			sort(array, i_meio + 1, rightIndex);
			merge(array, leftIndex, rightIndex);
		}

	}

	private void merge(T[] array, int leftIndex, int rightIndex) {

		T[] newArray = (T[]) new Comparable[array.length];

		for(int i = leftIndex; i <= rightIndex; i++){
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
