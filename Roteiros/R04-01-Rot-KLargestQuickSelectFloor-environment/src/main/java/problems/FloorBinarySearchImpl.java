package problems;

import util.Util;

public class FloorBinarySearchImpl implements Floor {

	@Override
	public Integer floor(Integer[] array, Integer x) {
		quicksort(array, 0, array.length - 1);

		return floorBinary(array, x, 0, array.length - 1);
	}

	private void quicksort(Integer[] array, int leftIndex, int rightIndex) {
		if (leftIndex < rightIndex) {
			int pivot = partition(array, leftIndex, rightIndex);
			quicksort(array, leftIndex, pivot - 1);
			quicksort(array, pivot + 1, rightIndex);
		}
	}

	private int partition(Integer[] array, int leftIndex, int rightIndex) {
		int pivotIndex = pickMedianOfThreePrivot(array, leftIndex, rightIndex);

		Util.swap(array, leftIndex, pivotIndex);

		Integer pivot = array[leftIndex];

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

	private int pickMedianOfThreePrivot(Integer[] array, int leftIndex, int rightIndex) {
		int meio = (leftIndex + rightIndex) / 2;

		if (array[leftIndex].compareTo(array[meio]) > 0) {
			util.Util.swap(array, leftIndex, meio);
		}

		if (array[leftIndex].compareTo(array[rightIndex]) > 0) {
			util.Util.swap(array, leftIndex, rightIndex);
		}

		if (array[meio].compareTo(array[rightIndex]) > 0) {
			util.Util.swap(array, leftIndex, meio);
		}

		return meio;
	}

	// [1,2,3,4,5,6] x = 2;
	private Integer floorBinary(Integer[] array, Integer x, int leftIndex, int rightIndex) {
		Integer floor = null;

		if (!(leftIndex > rightIndex)) {
			if (x <= array[leftIndex]) {
				floor = array[leftIndex];
			} else {
				int meio = (rightIndex + leftIndex) / 2;

				if (meio > 0 && array[meio] <= x && x < array[meio + 1]) {
					floor = array[meio];
				} else if (x < array[meio]) {
					floor = floorBinary(array, x, leftIndex, meio - 1);
				} else if(x > array[meio]){
					floor = floorBinary(array, x, meio + 1, rightIndex);
				}
			}
		}

		return floor;

	}

	private Integer ceilBinary(Integer[] array, Integer x, int leftIndex, int rightIndex) {
		Integer ceil = null;

		if (!(leftIndex > rightIndex)) {
			if (x >= array[rightIndex]) {
				ceil = array[rightIndex];
			} else {
				int meio = (rightIndex + leftIndex) / 2;

				if (meio < rightIndex && array[meio] < x && x < array[meio + 1]) {
					ceil = array[meio + 1];
				} else if (x < array[meio]) {
					ceil = ceilBinary(array, x, leftIndex, meio - 1);
				} else if (x > array[meio]) {
					ceil = ceilBinary(array, x, meio + 1, rightIndex);
				}
			}

		}
		return ceil;

	}

	public static void main(String[] args) {
		FloorBinarySearchImpl floor = new FloorBinarySearchImpl();
		Integer[] array = { 1, 4, 6, 8, 10 };
		System.out.println(floor.floorBinary(array, 7, 0, array.length - 1));
		System.out.println(floor.ceilBinary(array, 7, 0, array.length - 1));
		System.out.println(floor.floorBinary(array, 0, 0, array.length - 1));
		System.out.println(floor.ceilBinary(array, 12, 0, array.length - 1));

	}

}
