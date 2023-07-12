package adt.bst;

import adt.bt.BTNode;

/**
 * 
 * Performs consistency validations within a BST instance
 * 
 * @author Claudio Campelo
 *
 * @param <T>
 */
public class BSTVerifierImpl<T extends Comparable<T>> implements BSTVerifier<T> {

	private BSTImpl<T> bst;

	public BSTVerifierImpl(BST<T> bst) {
		this.bst = (BSTImpl<T>) bst;
	}

	private BSTImpl<T> getBSt() {
		return bst;
	}

	@Override
	public boolean isBST() {
		return this.bst.isEmpty() || this.isBSTRecursive(this.bst.getRoot());
	}

	private boolean isBSTRecursive(BTNode<T> nodeCurrent) {
		boolean result = true;

		if (!nodeCurrent.isEmpty())
			if (this.isValidLeft(nodeCurrent) && this.isValidRight(nodeCurrent))
				result = this.isBSTRecursive(nodeCurrent.getLeft()) && this.isBSTRecursive(nodeCurrent.getRight());
			else
				result = false;

		return result;
	}

	private boolean isValidLeft(BTNode<T> node) {
		return this.isValidLeft(node.getLeft(), node);
	}

	private boolean isValidLeft(BTNode<T> nodeCurrent, BTNode<T> root) {
		boolean result = true;

		if (!nodeCurrent.isEmpty())
			if (nodeCurrent.getData().compareTo(root.getData()) < 0)
				result = this.isValidLeft(nodeCurrent.getLeft(), root) && this.isValidLeft(nodeCurrent.getRight(), root);
			else
				result = false;

		return result;
	}

	private boolean isValidRight(BTNode<T> node) {
		return this.isValidRight(node.getRight(), node);
	}

	private boolean isValidRight(BTNode<T> nodeCurrent, BTNode<T> root) {
		boolean result = true;

		if (!nodeCurrent.isEmpty())
			if (nodeCurrent.getData().compareTo(root.getData()) > 0)
				result = this.isValidRight(nodeCurrent.getLeft(), root)
						&&
						this.isValidRight(nodeCurrent.getRight(), root);
			else
				result = false;

		return result;
	}

}
