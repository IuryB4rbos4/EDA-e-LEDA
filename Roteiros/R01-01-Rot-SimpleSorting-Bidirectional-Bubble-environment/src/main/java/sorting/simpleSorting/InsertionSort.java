package sorting.simpleSorting;

import java.util.Arrays;

import sorting.AbstractSorting;

/**
 * As the insertion sort algorithm iterates over the array, it makes the
 * assumption that the visited positions are already sorted in ascending order,
 * which means it only needs to find the right position for the current element
 * and insert it there.
 */
public class InsertionSort<T extends Comparable<T>> extends AbstractSorting<T> {

	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {

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

	// public static void main(String[] args) {
	// 	InsertionSort<Integer> insertionSort = new InsertionSort<>();
	// 	Integer[] array = { 9, 13, 16, 21, 32, 12 };

	// 	insertionSort.sort(array);
	// 	System.out.println(Arrays.toString(array));
	// }
}
