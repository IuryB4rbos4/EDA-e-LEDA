package problems;

import adt.bst.BST;

/**
 * Interface contendo método para somar todas as folhas de uma BST
 **/
public interface BSTSumLeaves {
    /**
     * Metodo para somar todas as folhsa de uma BST de inteiros. 
     * 
     * Restricoes e informacoes:
     * - A classe BSTInteger é fornecida para que voces nao implementem
     *   nenhuma BST. Voces podem usa-la para testar a implementacao de seu metodo
     * - Voce DEVE implementar o metodo USANDO RECURSAO. Voce pode usar metodo auxiliar
     *   desde que implementado na classe BSTSumLeavesImpl. 
     * - Voce NÃO pode editar nenhuma outra classe
     * - Voce NAO pode implementar nenhuma outra classe nova
     * - Para implementar o metodo sumLeaves voce DEVE, da classe BSTInteger,
     *   USAR APENAS o metodo getRoot
     * - Voce pode usar qualquer métod da classe BSTNode mas NÃO pode modifica-la
     * - Voce DEVE implementar seu algoritmo com complexidade Theta(N)
     * - Sua implementacao DEVE ter relacao de recorrencia T(N) = 2T(N) + O(1)
     * - Voce NÃO PODE usar nenhuma outra estrutura auxiliar nem converter a BST 
     *   em outra estrutura, string, etc.
     **/
    public int sumLeaves(BST<Integer> bst);
}

