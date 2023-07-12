package problems;

import adt.linkedList.SingleLinkedListNode;

public class LinkedListRemoveDuplicatesImpl<T> implements LinkedListRemoveDuplicates<T> {
    /**
     * Interface definindo um metodo para remover as duplicadas de uma lista
     * possivelmente não ordenada, a partir de um determinado nó.
     */
    /**
     * Remove as duplicadas de uma lista a partir de determinado nó, passado como
     * parâmetro.
     * Restrições:
     * - OBSERVE AS RESTRICOES EXTRAS NA CLASSE QUE IMPLEMENTA ESSA INTERFACE!
     * - Você NÃO pode usar nenhuma estrutura auxiliar como memória extra. Ou seja,
     * sua solução deve usar espaco extra O(1)
     * - voce NÃO pode alterar nada na classe SingleLinkedListNode
     * - Você NÃO pode ordenar a lista
     * - Você NÃO pode criar uma lista nova e retornar
     * - Sua solução DEVE ir varrendo a lista da esquerda pra direita e ir
     * resolvendo
     * o problema durante essa varredura.
     * - Seu algoritmo deve ser da ordem exata (Theta) de n^2.
     */
    /**
     * Restricoes extras:
     * - Você NÃO pode usar recursão
     * - Você pode criar métodos auxiliares se achar necessário, desde que sejam
     * criados
     * nesta classe
     */
    public void removeDuplicates(SingleLinkedListNode<T> node) {
        while (!node.isNIL()) {
            SingleLinkedListNode<T> nodeCurrent = node.getNext();
            while (!nodeCurrent.isNIL()) {
                if (node.equals(nodeCurrent)) {
                    node.setNext(nodeCurrent.getNext());
                    nodeCurrent = node.getNext();
                } else {
                    nodeCurrent = nodeCurrent.getNext();
                }
            }
            node = node.getNext();
        }
    }

    public static void main(String[] args) {
        SingleLinkedListNode<Integer> n1 = new SingleLinkedListNode<Integer>();
        SingleLinkedListNode<Integer> n2 = new SingleLinkedListNode<Integer>();
        SingleLinkedListNode<Integer> n3 = new SingleLinkedListNode<Integer>();
        SingleLinkedListNode<Integer> n4 = new SingleLinkedListNode<Integer>();
        SingleLinkedListNode<Integer> n5 = new SingleLinkedListNode<Integer>();

        n1.setData(1);
        n2.setData(2);
        n3.setData(1);
        n4.setData(2);
        n5.setData(1);
        n1.setNext(n2);
        n2.setNext(n3);
        n3.setNext(n4);
        n4.setNext(n5);
        n5.setNext(new SingleLinkedListNode<>());

        LinkedListRemoveDuplicatesImpl<Integer> chama = new LinkedListRemoveDuplicatesImpl<Integer>();
        chama.removeDuplicates(n1);
        while (!n1.isNIL()) {
            System.out.print(n1.getData());
            n1 = n1.getNext();
        }
    }
}
