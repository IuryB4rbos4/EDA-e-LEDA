package problems;

import java.util.Arrays;

/**
 * Dado um array A ordenado de inteiros de tamanho N e um valor x, possivelmente
 * com mais de uma ocorrencia,
 * usar busca binária para encontrar as posicoes da primeira e ultima
 * ocorrencias de x em A.
 * 
 * Return an array of size 2, such that first element = starting position of B
 * in A and second element = ending position of B in A, if B is not found in A
 * return [-1, -1].
 * 
 * Restricoes:
 * - Seu algoritmo NÃO pode usar memória extra (a nao ser variaveis simples
 * locais e nao de colecao/estrutura)
 * - O tempo de seu algoritmo deve ser O(log n).
 * - Retornar um array com dois elementos [primeiraPosicao,ultimaPosicao], onde
 * primeiraPosicao
 * tem o valor do indice da primeira ocorrencia de x em A, e ultimaPosicao tem o
 * valor do indice
 * da ultima ocorrencia de x em A
 * - Note que se x nao esta em A entao o retorno deve ser [-1,-1]
 *
 */
public class StartEndPositionImpl implements StartEndPosition {

	@Override
	public int[] startEndPosition(Integer[] array, Integer x) {
		int[] result = { -1, -1 };

		if (array != null && array.length > 0 && array[0] != null && x != null) {
			// PRIMEIRA E ULTIMA POSSIÇÃO
			result[0] = startRecursive(array, 0, array.length - 1, x);

			if (result[0] > -1) {
				result[1] = endRecursive(array, result[0], array.length - 1, x);
			}
		}

		return result;
	}

	// Encontrar a última ocorrencia de um valor igual a x
	private int endRecursive(Integer[] array, int leftIndex, int rightIndex, Integer x) {
		int result = -1;
		if (!(leftIndex > rightIndex)) {
			int mid = (leftIndex + rightIndex) / 2;

			if (array[mid].compareTo(x) == 0) {
				// tamannho do array
				// [1,1,1,1,1,1]
				if (mid + 1 < array.length && array[mid + 1].compareTo(x) == 0) {
					result = endRecursive(array, mid + 1, rightIndex, x);
				} else {
					result = mid;
				}
				// array[mid] > x ; parte do array é maior que o valor; significa que
				// precisamos de valores menores
			} else if (array[mid] > x) {
				result = endRecursive(array, leftIndex, mid - 1, x);
			} else {
				// array[mid] < x ; parte do array é maior que o valor; significa que
				// precisamos de valores menores
				result = endRecursive(array, mid + 1, rightIndex, x);
			}
		}
		return result;
	}
	// Encontrar a primeira ocorrencia de um valor igual a x
	private int startRecursive(Integer[] array, int leftIndex, int rightIndex, Integer x) {
		int result = -1;
		if (!(leftIndex > rightIndex)) {
			int mid = (leftIndex + rightIndex) / 2;

			if (array[mid].compareTo(x) == 0) {
				// tamanho inicial do array
				if (mid - 1 > -1 && array[mid - 1].compareTo(x) == 0) {
					result = startRecursive(array, leftIndex, mid - 1, x);
				} else {
					result = mid;
				}
			} else if (array[mid] > x) {
				result = startRecursive(array, leftIndex, mid - 1, x);
			} else {
				// array[mid] < x ; parte do array é maior que o valor; significa que
				// precisamos de valores menores
				result = startRecursive(array, mid + 1, rightIndex, x);
			}
		}
		return result;
	}

	public static void main(String[] args) {

		Integer[] arrayNull = null;
		Integer[] arrayVazio = {};
		Integer[] arrayValorRepetidoDoComeçoAoFim = { 1, 1, 1, 1, 1, 1, 1 };
		Integer[] arrayNormal = { 1, 2, 3, 4, 5, 6, 7 };
		Integer[] array4 = { 1, 1, 2, 2, 3, 3, 4, 4 };
		Integer[] array5 = { 100, 200, 300, 400, 500, 999, 999, 999 };

		StartEndPositionImpl startEndPosition = new StartEndPositionImpl();

		System.out.println("esperado: " + -1 + ", " + -1);
		System.out.println(Arrays.toString(startEndPosition.startEndPosition(arrayNull, 100)));
		System.out.println();

		System.out.println("esperado: " + -1 + ", " + -1);
		System.out.println(Arrays.toString(startEndPosition.startEndPosition(arrayVazio, 1)));
		System.out.println();

		System.out.println("esperado: " + 0 + ", " + (arrayValorRepetidoDoComeçoAoFim.length - 1));
		System.out.println(Arrays.toString(startEndPosition.startEndPosition(arrayValorRepetidoDoComeçoAoFim, 1)));
		System.out.println();

		System.out.println("esperado: " + 3 + ", " + 3);
		System.out.println(Arrays.toString(startEndPosition.startEndPosition(arrayNormal, 4)));
		System.out.println();

		// COM O X = NULL;
		System.out.println("valor de x == null");
		System.out.println("esperado: " + -1 + ", " + -1);
		System.out.println(Arrays.toString(startEndPosition.startEndPosition(arrayNormal, null)));
		System.out.println();

		System.out.println("esperado: " + 2 + ", " + 3);
		System.out.println(Arrays.toString(startEndPosition.startEndPosition(array4, 2)));
		System.out.println();

		System.out.println("esperado: " + 6 + ", " + 7);
		System.out.println(Arrays.toString(startEndPosition.startEndPosition(array4, 4)));
		System.out.println();

		System.out.println("esperado: " + 5 + ", " + 7);
		System.out.println(Arrays.toString(startEndPosition.startEndPosition(array5, 999)));
		System.out.println();
	}
}
