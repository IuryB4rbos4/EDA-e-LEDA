package sorting.linearSorting;

import sorting.AbstractSorting;

/**
 * Classe que implementa a estratégia de Counting Sort vista em sala.
 *
 * Procure evitar desperdício de memória: AO INVÉS de alocar o array de
 * contadores
 * com um tamanho arbitrariamente grande (por exemplo, com o maior valor de
 * entrada possível),
 * aloque este array com o tamanho sendo o máximo inteiro presente no array a
 * ser ordenado.
 *
 * Seu algoritmo deve assumir que o array de entrada nao possui numeros
 * negativos,
 * ou seja, possui apenas numeros inteiros positivos e o zero.
 *
 */
public class CountingSort extends AbstractSorting<Integer> {

	@Override
	public void sort(Integer[] array, int leftIndex, int rightIndex) {
		if(leftIndex <= rightIndex){
			Integer max = array[leftIndex];
			for(int i = leftIndex; i < rightIndex; i++){
				if(array[i] > max){
					max = array[i];
				}
			}

			Integer[] cumulative = new Integer[max + 1];
			for(int i = leftIndex; i < rightIndex + 1; i++){
				cumulative[array[i]]++;
			}

			for(int i = 1; i < cumulative.length; i++){
				cumulative[i] += cumulative[i - 1];
			}

			Integer[] arrayCopy = array.clone();
			for(int i = rightIndex; i > leftIndex - 1; i--){
				array[cumulative[arrayCopy[i]] - 1 + leftIndex] = arrayCopy[i];
				cumulative[arrayCopy[i]]--;
			}
		}
	}
}
