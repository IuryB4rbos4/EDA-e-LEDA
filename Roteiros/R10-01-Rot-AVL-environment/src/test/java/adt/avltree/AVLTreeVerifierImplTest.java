package adt.avltree;

import adt.bst.*;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Não realiza balanceamento nem rotação
 */
class AVLTest <T extends Comparable<T>> extends AVLTreeImpl<T> {
    @Override
	public void insert(T element) {
		if (element != null) {
			this.insertRecursive(this.root, element);
		}
	}

	private void insertRecursive(BSTNode<T> nodeCurrent, T element) {
		if (nodeCurrent.isEmpty()) {
			nodeCurrent.setData(element);
			nodeCurrent.setRight(new BSTNode.Builder<T>().parent(nodeCurrent).build());
			nodeCurrent.setLeft(new BSTNode.Builder<T>().parent(nodeCurrent).build());
		} else {
			if (element.compareTo(nodeCurrent.getData()) > 0)
				this.insertRecursive((BSTNode<T>) nodeCurrent.getRight(), element);
			else
				this.insertRecursive((BSTNode<T>) nodeCurrent.getLeft(), element);
		}
	}

    @Override
	public void remove(T element) {
		if(element != null){
			BSTNode<T> nodeCurrent = this.search(element);

			if(!nodeCurrent.isEmpty()){
				if(nodeCurrent.isLeaf()){
					removeLeaf(nodeCurrent);
				} else if(nodeCurrent.getLeft().isEmpty() || nodeCurrent.getRight().isEmpty()){
					removeOneLeaf(nodeCurrent);
				} else {
					removeTwoLeaf(nodeCurrent);
				}
			}
		}
	}

	private void removeTwoLeaf(BSTNode<T> nodeCurrent) {
		T sucessor = this.sucessor(nodeCurrent.getData()).getData();
		this.remove(sucessor);
		nodeCurrent.setData(sucessor);
	}

	private void removeOneLeaf(BSTNode<T> nodeCurrent) {
		BSTNode<T> childNode = nodeCurrent.getRight().isEmpty() ? (BSTNode<T>) nodeCurrent.getLeft() : (BSTNode<T>) nodeCurrent.getRight();
		if(getRoot().equals(nodeCurrent)){
			this.root = childNode;
			this.root.setParent(null);
		} else {
			childNode.setParent(nodeCurrent.getParent());
			if(nodeCurrent.getParent().getRight().equals(nodeCurrent)){
				nodeCurrent.getParent().setRight(childNode);
			} else {
				nodeCurrent.getParent().setLeft(childNode);
			}
		}
	}

	private void removeLeaf(BSTNode<T> nodeCurrent) {
		nodeCurrent.setData(null);
		nodeCurrent.setLeft(null);
		nodeCurrent.setRight(null);
	}
}

public class AVLTreeVerifierImplTest {

    private AVLTree<Integer> avl, avlTest;
    private AVLTreeVerifier<Integer> avlVerifier, avlTestVerifier;

    private void defineAVLS (Integer[] array) {
        avl = new AVLTreeImpl<>();
        avlTest = new AVLTest<>();

        for (Integer value : array) {
            avl.insert(value);
            avlTest.insert(value);
        }

        this.avlVerifier = new AVLTreeVerifierImpl<>(avl);
        this.avlTestVerifier = new AVLTreeVerifierImpl<>(avlTest);
    }

    @Test
    public void testIsAVL01() {
        defineAVLS(new Integer[]{10,15,18,12});
        assertTrue(avlVerifier.isAVLTree());
        assertFalse(avlTestVerifier.isAVLTree());
    }

    @Test
    public void testIsAVL02() {
        defineAVLS(new Integer[]{18,15,10});
        assertTrue(avlVerifier.isAVLTree());
        assertFalse(avlTestVerifier.isAVLTree());
    }

    @Test
    public void testIsAVL03() {
        defineAVLS(new Integer[]{18,15,10,17});
        assertTrue(avlVerifier.isAVLTree());
        assertFalse(avlTestVerifier.isAVLTree());
    }

    @Test
    public void testIsAVL04() {
        defineAVLS(new Integer[]{10,15,12});
        assertTrue(avlVerifier.isAVLTree());
        assertFalse(avlTestVerifier.isAVLTree());
    }

    @Test
    public void testIsAVL05() {
        defineAVLS(new Integer[]{15,10,12});
        assertTrue(avlVerifier.isAVLTree());
        assertFalse(avlTestVerifier.isAVLTree());
    }

    @Test
    public void testIsBstIsAvl() {
        defineAVLS(new Integer[]{10,15,9});
        assertTrue(avlVerifier.isAVLTree());
        assertTrue(avlTestVerifier.isAVLTree());
    }

    @Test
    public void testNotIsBstIsAvl() {
        defineAVLS(new Integer[]{10,15,12});
        avl.search(10).setData(18);
        avlTest.search(10).setData(18);
        assertFalse(avlVerifier.isAVLTree());
        assertFalse(avlTestVerifier.isAVLTree());
    }

    @Test
    public void testIsBstNotIsAvl() {
        defineAVLS(new Integer[]{10,15,18});
        assertTrue(avlVerifier.isAVLTree());
        assertFalse(avlTestVerifier.isAVLTree());
    }

}