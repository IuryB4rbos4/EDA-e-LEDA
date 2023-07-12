package adt.avltree;

import adt.bst.BSTNode;
import adt.bst.BSTVerifierImpl;

/**
 * Performs consistency validations within a AVL Tree instance
 * 
 * @author Claudio Campelo
 *
 * @param <T>
 */
public class AVLTreeVerifierImpl<T extends Comparable<T>> extends BSTVerifierImpl<T> implements AVLTreeVerifier<T> {

	private AVLTreeImpl<T> avlTree;

	public AVLTreeVerifierImpl(AVLTree<T> avlTree) {
		super(avlTree);
		this.avlTree = (AVLTreeImpl<T>) avlTree;
	}

	private AVLTreeImpl<T> getAVLTree() {
		return avlTree;
	}

	@Override
	public boolean isAVLTree() {
		return this.isBST() && this.isAVL();
	}

	private boolean isAVL() {
		return this.avlTree.isEmpty() || this.isAVLRecursive(this.avlTree.getRoot());
	}

	private boolean isAVLRecursive(BSTNode<T> nodeCurrent) {
		boolean result = true;

		if(!nodeCurrent.isEmpty()){
			if(Math.abs(this.avlTree.calculateBalance(nodeCurrent)) <= 1) {
				result = this.isAVLRecursive((BSTNode<T>) nodeCurrent.getLeft()) && this.isAVLRecursive((BSTNode<T>) nodeCurrent.getRight());
			} else {
				result = false;
			}
		}

		return result;
	}

}
