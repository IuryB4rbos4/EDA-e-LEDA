package problems;

import adt.bst.BST;
import adt.bst.BSTNode;

/**
 * Metodo para somar todas as folhsa de uma BST de inteiros.
 * 
 * Restricoes e informacoes:
 * - A classe BSTInteger é fornecida para que voces nao implementem
 * nenhuma BST. Voces podem usa-la para testar a implementacao de seu metodo
 * - Voce DEVE implementar o metodo USANDO RECURSAO. Voce pode usar metodo
 * auxiliar
 * desde que implementado na classe BSTSumLeavesImpl.
 * - Voce NÃO pode editar nenhuma outra classe
 * - Voce NAO pode implementar nenhuma outra classe nova
 * - Para implementar o metodo sumLeaves voce DEVE, da classe BSTInteger,
 * USAR APENAS o metodo getRoot
 * - Voce pode usar qualquer métod da classe BSTNode mas NÃO pode modifica-la
 * - Voce DEVE implementar seu algoritmo com complexidade Theta(N)
 * - Sua implementacao DEVE ter relacao de recorrencia T(N) = 2T(N) + O(1)
 * - Voce NÃO PODE usar nenhuma outra estrutura auxiliar nem converter a BST
 * em outra estrutura, string, etc.
 **/

public class BSTSumLeavesImpl implements BSTSumLeaves {
    public int sumLeaves(BST<Integer> bst) {
        return sumLeavesRecursive((BSTNode<Integer>) bst.getRoot());
    }

    private int sumLeavesRecursive(BSTNode<Integer> nodeCurrent) {
        int value = 0;

        if (!(nodeCurrent.isEmpty())) {
            if (nodeCurrent.getLeft().isEmpty() && nodeCurrent.getRight().isEmpty()) {
                value = nodeCurrent.getData();
            }

            value += sumLeavesRecursive((BSTNode<Integer>) nodeCurrent.getLeft());
            value += sumLeavesRecursive((BSTNode<Integer>) nodeCurrent.getRight());
        }

        return value;
    }

    public static void main(String[] args) {
        BSTInteger rootBST = new BSTInteger();
        rootBST.insert(5);
        rootBST.insert(6);

        rootBST.insert(4);
        rootBST.insert(3);
        rootBST.insert(2);
        rootBST.insert(1);

        BSTSumLeavesImpl bstRoot = new BSTSumLeavesImpl();
        System.out.println(bstRoot.sumLeaves(rootBST)); // 7
    }
}
