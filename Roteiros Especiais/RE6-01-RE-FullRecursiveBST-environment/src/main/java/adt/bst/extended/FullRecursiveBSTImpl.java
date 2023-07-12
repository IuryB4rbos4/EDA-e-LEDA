package adt.bst.extended;

import java.util.ArrayList;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;

public class FullRecursiveBSTImpl<T extends Comparable<T>> extends BSTImpl<T> implements FullRecursiveBST<T> {

	/**
	 * Sobrescreva este metodo usando recursao.
	 */
	@Override
	public BSTNode<T> maximum() {
		BSTNode<T> max = null;
		if (!this.isEmpty()) {
			max = maximumRecursivo(this.root);
		}
		return max;
	}

	private BSTNode<T> maximumRecursivo(BSTNode<T> nodeCurrent) {
		if (!nodeCurrent.getRight().isEmpty()) {
			nodeCurrent = maximumRecursivo((BSTNode<T>) nodeCurrent.getRight());
		}

		return nodeCurrent;
	}

	/**
	 * Sobrescreva este metodo usando recursao.
	 */
	@Override
	public BSTNode<T> minimum() {
		BSTNode<T> min = null;
		if (!this.isEmpty()) {
			min = minimumRecursivo(this.root);
		}
		return min;
	}

	private BSTNode<T> minimumRecursivo(BSTNode<T> nodeCurrent) {
		if (!nodeCurrent.getLeft().isEmpty()) {
			nodeCurrent = maximumRecursivo((BSTNode<T>) nodeCurrent.getLeft());
		}

		return nodeCurrent;
	}

	/**
	 * Sobrescreva este metodo usando recursao. Quando um no tem filho a direita
	 * entao o sucessor sera o minimum do filho a direita. Caso contrario
	 * o sucessor sera o primeiro ascendente a ter um valor maior.
	 */
	@Override
	public BSTNode<T> sucessor(T element) {
		BSTNode<T> nodeCurrent = search(element);

		if (!nodeCurrent.isEmpty()) {
			if (!nodeCurrent.getRight().isEmpty()) {
				nodeCurrent = minimumRecursivo((BSTNode<T>) nodeCurrent.getRight());
			} else {
				nodeCurrent = sucessorRecursive(nodeCurrent, element);
			}
		}

		return (nodeCurrent == null || nodeCurrent.isEmpty()) ? null : nodeCurrent;
	}

	private BSTNode<T> sucessorRecursive(BSTNode<T> nodeCurrent, T element) {
		if (nodeCurrent != null && nodeCurrent.getData().compareTo(element) <= 0) {
			nodeCurrent = sucessorRecursive((BSTNode<T>) nodeCurrent.getParent(), element);
		}
		return nodeCurrent;
	}

	/**
	 * Sobrescreva este metodo usando recursao. Quando um no tem filho a esquerda
	 * entao o predecessor sera o maximum do filho a esquerda. Caso contrario
	 * o predecessor sera o primeiro ascendente a ter um valor menor.
	 */
	@Override
	public BSTNode<T> predecessor(T element) {
		BSTNode<T> nodeCurrent = search(element);

		if (!nodeCurrent.isEmpty()) {
			if (!nodeCurrent.getRight().isEmpty()) {
				nodeCurrent = maximumRecursivo((BSTNode<T>) nodeCurrent.getLeft());
			} else {
				nodeCurrent = predecessorRecursive(nodeCurrent, element);
			}
		}

		return (nodeCurrent == null || nodeCurrent.isEmpty()) ? null : nodeCurrent;
	}

	private BSTNode<T> predecessorRecursive(BSTNode<T> nodeCurrent, T element) {
		if (nodeCurrent != null && nodeCurrent.getData().compareTo(element) >= 0) {
			nodeCurrent = predecessorRecursive((BSTNode<T>) nodeCurrent.getParent(), element);
		}
		return nodeCurrent;
	}

	@Override
	public T[] elementsAtDistance(int k) {
		ArrayList<T> result = new ArrayList<T>();
		dfs(this.root, k, result);
		return (T[]) result.toArray(new Comparable[0]);
	}

	private void dfs(BSTNode<T> nodeCurrent, int k, ArrayList<T> result) {
		if (nodeCurrent != null) {
			if (k == 0 && !nodeCurrent.isEmpty()) {
				result.add(nodeCurrent.getData());
			}

			if (k > 0) {
				dfs((BSTNode<T>) nodeCurrent.getLeft(), k - 1, result);
				dfs((BSTNode<T>) nodeCurrent.getRight(), k - 1, result);
			}
		}

	}

	public static void main(String[] args) {

		FullRecursiveBSTImpl<Integer> chama = new FullRecursiveBSTImpl<Integer>();

		chama.insert(63);
		chama.insert(7);
		chama.insert(2);
		chama.insert(59);
		chama.insert(52);
		chama.insert(60);
		chama.insert(69);
		chama.insert(65);
		chama.insert(90);
		chama.insert(83);

		chama.elementsAtDistance(0);
		chama.elementsAtDistance(1);
		chama.elementsAtDistance(2);
		chama.elementsAtDistance(3);

	}
}
