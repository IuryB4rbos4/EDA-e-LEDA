package problems;

import adt.bst.BST;
import adt.bst.BSTNode;

public class BSTSumLeavesImpl implements BSTSumLeaves {
    public int sumLeaves(BST<Integer> bst) {
        return sumLeavesRecursive((BSTNode<Integer>) bst.getRoot());
    }

    private int sumLeavesRecursive(BSTNode<Integer> nodeCurrent) {
        int value = 0;

        if(!(nodeCurrent.isEmpty())){
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
