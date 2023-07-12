package problems;

/**
 * Interface contendo método para calcular o número de rotações 
 * acontecidas em um array circularmente ordenado rotacionado.
 * Um array está circularmente ordenado se existe algum indice interno
 * em que seus elementos podem ser percorridos em ordem até que todos 
 * os elementos sejam visitados. O aspecto circular significa que 
 * quando o último elemento é atingido o percurso continua a partir 
 * do início do array. 
 * Exemplo: A = [4,5,6,7,8,9]
 * A é um array circularmente ordenado porque podemos percorrer seus elementos
 * a partir do indice 0 até o índice 5 e eles estão em ordem. Neste caso, como o menor 
 * elemento está na posição 0, o array não sofreu nenhuma rotação.
 * 
 * Exemplo: B = [9,4,5,6,7,8]
 * B é um array circularmente ordenado porque podemos percorrer seus elementos
 * a partir do indice 1 até o o índice 5 e depois retornando para o índice 0 
 * novamente e eles estão em ordem. Neste caso, o array sofreu 1 rotação.
 * 
 * É fácil imaginar que uma rotação nada mais é do que uma operação de 
 * "shift" onde os elementos do array são "empurrados" para frente e os que ultrapassam
 * o tamanho do array são trazidos de volta para o início, preservando a ordenação
 * de forma circular.
 **/

/**
 * Método para calcular a quantidade de rotações acontecidas em um array
 * circularmente ordenado.
 *
 * Restricoes:
 * - O array está ordenado
 * - O array NÃO possui elementos repetidos
 * - Voce DEVE implementar seu algoritmo usando a estratégia de busca binária
 * - Voce NÃO pode usar estrutura auxiliar
 * - Voce NÃO pode criar um novo atributo na classe
 * - Você DEVE usar recursão em sua solução
 * - Seu algoritmo DEVE ter complexidade O(log N)
 **/
public class BinarySearchRotatedArrayImpl implements BinarySearchRotatedArray {

    public int findRotations(int[] array) {
        int result = 0;
        // Não é necessário fazer essa verificação
        if (array != null && array.length > 0) {
            result = findRotationsBinary(array, 0, array.length - 1);
        }

        return result;
    }

    private int findRotationsBinary(int[] array, int leftIndex, int rightIndex) {
        int result = 0;
        if (!(leftIndex > rightIndex)) {
            int mid = (leftIndex + rightIndex) / 2;

            if (mid < array.length - 1 && array[mid] > array[mid + 1]) {
                result = mid + 1;
            } else {
                if (array[leftIndex] > array[rightIndex] && array[leftIndex] > array[mid]) {
                    result = findRotationsBinary(array, leftIndex, mid - 1);
                } else {
                    result = findRotationsBinary(array, mid + 1, rightIndex);
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int[] numbers1 = { 5, 1, 2, 3, 4 }; // 1
        int[] numbers2 = { 4, 5, 1, 2, 3 }; // 2
        int[] numbers3 = { 3, 4, 5, 1, 2 }; // 3
        int[] numbers4 = { 2, 3, 4, 5, 1 }; // 4
        int[] numbers5 = { 1, 2, 3, 4, 5 }; // 0
        BinarySearchRotatedArrayImpl arrocha = new BinarySearchRotatedArrayImpl();
        System.out.println(arrocha.findRotations(numbers1));
        System.out.println(arrocha.findRotations(numbers2));
        System.out.println(arrocha.findRotations(numbers3));
        System.out.println(arrocha.findRotations(numbers4));
        System.out.println(arrocha.findRotations(numbers5));
    }
}
