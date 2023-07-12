package problems;

/**
 * Dado um array A ordenado de inteiros de tamanho N e um valor x, possivelmente com mais de uma ocorrencia,
 * usar busca binária para encontrar as posicoes da primeira e ultima ocorrencias de x em  A.
 *
 * Exemplo:
 *   A = [5, 7, 7, 8, 8, 10]
 *   x = 8
 *   Saida:[3, 4] (primeira ocorrencia do 8 esta no indice 3, ultima ocorrencia do 8 esta no indice 4)
 *
 *   A = [5, 17, 100, 111]
     x = 3
 *   Saida: [-1, -1] (nao tem ocorrencia de 3 no array)
 *
 * Restricoes: 
 * - Seu algoritmo NÃO pode usar memória extra (a nao ser variaveis simples locais e nao de colecao/estrutura) 
 * - O tempo de seu algoritmo deve ser O(log n).
 * - Retorna um array com dois elementos [primeiraPosicao,ultimaPosicao], onde primeiraPosicao
 *   tem o valor do indice da primeira ocorrencia de x em A, e ultimaPosicao tem o valor do indice 
 *   da ultima ocorrencia de x em A
 * - Note que se x nao esta em A entao o retorno deve ser [-1,-1]
 * - Voce NAO pode criar nenhuma classe extra
 * - Implemente tudo que necessita na classe StartEndPositionImpl
 * - Voce pode criar metodos auxiliares
 */
public interface StartEndPosition {


	public int[] startEndPosition(Integer[] array, Integer x);

}
