package sorting.linearSorting;

import sorting.AbstractSorting;

/**
 * Classe que implementa do Counting Sort vista em sala. Desta vez este
 * algoritmo deve satisfazer os seguitnes requisitos:
 * - Alocar o tamanho minimo possivel para o array de contadores (C)
 * - Ser capaz de ordenar arrays contendo numeros negativos
 */
public class ExtendedCountingSort extends AbstractSorting<Integer> {

	@Override
	public void sort(Integer[] array, int leftIndex, int rightIndex) {
		if(leftIndex <= rightIndex){
			Integer max = array[leftIndex];
			Integer min = array[leftIndex];
			for(int i = leftIndex; i < rightIndex; i++){
				if(array[i] > max){
					max = array[i];
				}
				if(array[i] < min){
					min = array[i];
				}
			}

			Integer[] cumulative = new Integer[max - min + 1];
			for(int i = leftIndex; i < cumulative.length; i++){
				cumulative[array[i] - min]++;
			}

			for(int i = 1; i < cumulative.length; i++){
				cumulative[i] += cumulative[i - 1];
			}

			Integer[] arrayCopy = array.clone();
			for(int i = rightIndex; i > leftIndex - 1; i--){
				array[cumulative[arrayCopy[i] - min] - 1 + leftIndex] = arrayCopy[i];
				cumulative[arrayCopy[i] - min]--;
			}
		}
	}

}
