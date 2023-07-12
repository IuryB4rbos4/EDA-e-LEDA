package adt.bst;

import adt.bt.BTNode;

/**
 * - Esta eh a unica classe que pode ser modificada 
 * @author adalbertocajueiro
 *
 * @param <T>
 */
public class SimpleBSTManipulationImpl<T extends Comparable<T>> implements SimpleBSTManipulation<T> {

	@Override
	public boolean equals(BST<T> tree1, BST<T> tree2) {
		return this.equalsRecursive(tree1.getRoot(), tree2.getRoot());
	}

	private boolean equalsRecursive(BTNode<T> rootCurrent, BTNode<T> rootCurrent2) {
		boolean result = false;

		if(rootCurrent.isEmpty() && rootCurrent2.isEmpty()){
			result = true;
		}

		if(!rootCurrent.isEmpty() && !rootCurrent2.isEmpty()){
			boolean leftEquals = equalsRecursive(rootCurrent.getLeft(), rootCurrent2.getLeft());
			boolean rightEquals = equalsRecursive(rootCurrent.getRight(), rootCurrent2.getRight());
			result = rootCurrent.equals(rootCurrent2) && leftEquals && rightEquals;
		}

		return result;
	}

	@Override
	public boolean isSimilar(BST<T> tree1, BST<T> tree2) {
		return isSimilarRecursive(tree1.getRoot(), tree2.getRoot());
	}

	private boolean isSimilarRecursive(BTNode<T> rootCurrent, BTNode<T> rootCurrent2) {
		boolean result = false;

		if(!rootCurrent.isEmpty() && !rootCurrent2.isEmpty()){
			boolean leftEquals = this.isSimilarRecursive(rootCurrent.getLeft(), rootCurrent2.getLeft());
			boolean rightEquals = this.isSimilarRecursive(rootCurrent.getRight(), rootCurrent2.getRight());
			result = leftEquals && rightEquals;
		} else {
			boolean condition = (!rootCurrent.isEmpty() || rootCurrent2.isEmpty());
			boolean condition2 = (rootCurrent.isEmpty() || !rootCurrent2.isEmpty());
			result = condition && condition2;
		}

		return result;
	}

	@Override
	public T orderStatistic(BST<T> tree, int k) {
		int treeSize = tree.size();

		T result = null;

		if(k >= 1 && k <= treeSize){
			BTNode<T> treeMinimum = tree.minimum();

			if(k == 1){
				result = treeMinimum.getData();
			} else if (k == treeSize){
				result = tree.maximum().getData();
			} else {
				result = this.orderStatisticRecursive(tree, treeMinimum, k);
			}
		}

		return result;
	}

	private T orderStatisticRecursive(BST<T> tree, BTNode<T> nodeCurrent, int k) {
		return k == 1 ? nodeCurrent.getData() : this.orderStatisticRecursive(tree, tree.sucessor(nodeCurrent.getData()), --k);
	}

}
