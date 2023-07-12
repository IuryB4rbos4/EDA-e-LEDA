package adt.avltree;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;
import adt.bt.Util;

/**
 * 
 * Implementacao de uma arvore AVL
 * A CLASSE AVLTree herda de BSTImpl. VOCE PRECISA SOBRESCREVER A IMPLEMENTACAO
 * DE BSTIMPL RECEBIDA COM SUA IMPLEMENTACAO "OU ENTAO" IMPLEMENTAR OS SEGUITNES
 * METODOS QUE SERAO TESTADOS NA CLASSE AVLTREE:
 *  - insert
 *  - preOrder
 *  - postOrder
 *  - remove
 *  - height
 *  - size
 *
 * @author Claudio Campelo
 *
 * @param <T>
 */
public class AVLTreeImpl<T extends Comparable<T>> extends BSTImpl<T> implements
		AVLTree<T> {

	// TODO Do not forget: you must override the methods insert and remove
	// conveniently.

	// AUXILIARY
	protected int calculateBalance(BSTNode<T> node) {
		int result = 0;

		if (!node.isEmpty())
			result = this.heightRecursive((BSTNode<T>) node.getLeft()) - this.heightRecursive((BSTNode<T>) node.getRight());
		return result;
	}

	// AUXILIARY
	protected void rebalance(BSTNode<T> node) {
		BSTNode<T> newRoot = null;
		int balance = this.calculateBalance(node);

		if (Math.abs(balance) > 1)
			if (balance > 1)
				if (this.calculateBalance((BSTNode<T>) node.getLeft()) >= 0)
					newRoot = Util.rightRotation(node);
				else
					newRoot = Util.doubleRightRotation(node);
			else
				if (this.calculateBalance((BSTNode<T>) node.getRight()) <= 0)
					newRoot = Util.leftRotation(node);
				else
					newRoot = Util.doubleLeftRotation(node);

		if (this.getRoot().equals(node) && newRoot != null)
			this.root = newRoot;
	}

	// AUXILIARY
	protected void rebalanceUp(BSTNode<T> node) {
		if (node.getParent() != null) {
			this.rebalance((BSTNode<T>) node.getParent());
			this.rebalanceUp((BSTNode<T>) node.getParent());
		}
	}

	@Override
	public void insert (T element) {
		if (element != null){
			this.insertRecursive(this.root, element);
		}
	}

	private void insertRecursive (BSTNode<T> nodeCurrent, T element) {
		if (nodeCurrent.isEmpty()) {
			nodeCurrent.setData(element);
			nodeCurrent.setRight(new BSTNode.Builder<T>().parent(nodeCurrent).build());
			nodeCurrent.setLeft(new BSTNode.Builder<T>().parent(nodeCurrent).build());
		} else
		if (element.compareTo(nodeCurrent.getData()) > 0){
			this.insertRecursive((BSTNode<T>) nodeCurrent.getRight(), element);
		} else {
			this.insertRecursive((BSTNode<T>) nodeCurrent.getLeft(), element);
		}
		rebalance(nodeCurrent);
	}

	@Override
	public void remove(T element) {
		if(element != null){
			BSTNode<T> nodeCurrent = this.search(element);

			if(!nodeCurrent.isEmpty()){
				if(nodeCurrent.isLeaf()){
					removeLeaf(nodeCurrent);
					rebalanceUp(nodeCurrent);
				} else if(nodeCurrent.getLeft().isEmpty() || nodeCurrent.getRight().isEmpty()){
					removeOneLeaf(nodeCurrent);
					rebalanceUp(nodeCurrent);
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
