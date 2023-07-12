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
public interface BinarySearchRotatedArray {
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
    public int findRotations(int[] array);
}
