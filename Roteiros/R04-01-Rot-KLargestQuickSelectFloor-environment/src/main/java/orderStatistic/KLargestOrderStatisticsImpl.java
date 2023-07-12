package orderStatistic;

import java.util.Arrays;

/**
 * Uma implementacao da interface KLargest que usa estatisticas de ordem para 
 * retornar um array com os k maiores elementos de um conjunto de dados/array.
 * 
 * A k-esima estatistica de ordem de um conjunto de dados eh o k-esimo menor
 * elemento desse conjunto. Por exemplo, considere o array [4,8,6,9,12,1]. 
 * A 3a estatistica de ordem eh 6, a 6a estatistica de ordem eh 12.
 * 
 * Note que, para selecionar os k maiores elementos, pode-se pegar todas as 
 * estatisticas de ordem maiores que k. 
 * 
 * Requisitos do algoritmo:
 * - DEVE ser in-place. Porem, voce pode modificar o array original.
 *   Voce pode criar ainda um array de tamanho k que vai armazenar apenas
 *   os elementos a serem retornados.
 * - Voce DEVE usar alguma ideia de algoritmo de ordenacao visto em sala 
 *   para calcular estatisticas de ordem.
 * - Se a entrada for invalida, deve-se retornar um array vazio (por exemplo,
 *   ao solicitar os 5 maiores elementos em um array que soh contem 3 elementos).
 *   Este metodo NUNCA deve retornar null.
 * 
 * @author campelo and adalberto
 *
 * @param <T>
 */
public class KLargestOrderStatisticsImpl<T extends Comparable<T>> implements KLargest<T>{

	@Override
	public T[] getKLargest(T[] array, int k) {
		T[] result = null;

		if (array == null || array.length < k || k <= 0) {
			result = (T[]) new Comparable[0];
		} else {
			orderStatistics(array, k);
			result = Arrays.copyOfRange(array, array.length - k, array.length);
		}

		return result;
	}

	/**
	 * Metodo que retorna a k-esima estatistica de ordem de um array, usando
	 * a ideia de algum algoritmo de ordenacao visto em sala. Caso nao exista 
	 * a k-esima estatistica de ordem, entao retorna null.
	 * 
	 * Obs: o valor de k deve ser entre 1 e N.
	 * 
	 * @param array
	 * @param k
	 * @return
	 */
	public T orderStatistics(T[] array, int k){
		return orderStatistics(array, 0, array.length - 1, k);	
	}

	private T orderStatistics(T[] array, int leftIndex, int rightIndex, int k){
		T result = null;

		if(!(leftIndex >= rightIndex)){
			int pivotI = partition(array, leftIndex, rightIndex);
			int pivotO = array.length - pivotI;
			if(k == pivotO){
				result = array[pivotO];
			} else if (k < pivotO){
				result = orderStatistics(array, pivotI + 1, rightIndex, k);
			} else {
				result = orderStatistics(array, leftIndex, pivotI - 1, k);
			}
		}
		return result;	
	}


	private int partition(T[] array, int leftIndex, int rightIndex) {

		int pivot = pickMedianOfThreePrivot(array, leftIndex, rightIndex);

		util.Util.swap(array, pivot, rightIndex);

		int i = leftIndex;

		for (int j = i; j < rightIndex; j++){
			if(array[j].compareTo(array[pivot]) < 0){
				util.Util.swap(array, i, j);
				i += 1;
			}
		}

		util.Util.swap(array, i, rightIndex);

		return i;
	}


	private int pickMedianOfThreePrivot(T[] array, int leftIndex, int rightIndex){
		int meio = (leftIndex + rightIndex) / 2;

		if (array[leftIndex].compareTo(array[meio]) > 0) {
			util.Util.swap(array, leftIndex, meio);
		}

		if (array[leftIndex].compareTo(array[rightIndex]) > 0) {
			util.Util.swap(array, leftIndex, rightIndex);
		}

		if (array[meio].compareTo(array[rightIndex]) > 0 ) {
			util.Util.swap(array, leftIndex, meio);
		}

		return meio;
	}
}
