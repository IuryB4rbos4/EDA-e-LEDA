package adt.bst.extended;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;

/**
 * Note que esta classe estende sua implementacao de BST (BSTImpl).
 * Dependendo do design que voce use, sua BSTImpl precisa ter apenas funcionando
 * corretamente o metodo insert para que voce consiga testar esta classe.
 */
public class FloorCeilBSTImpl extends BSTImpl<Integer> implements FloorCeilBST {

	@Override
	public Integer floor(Integer[] array, double numero) {
		for(Integer elemento : array){
			this.insert(elemento);
		}

		return floorRecursive(this.root, numero, null);
	}

	private Integer floorRecursive(BSTNode<Integer> nodeCurrent, double numero, Integer floorResutl) {
		if(!nodeCurrent.isEmpty()) {
			if(numero > nodeCurrent.getData()){
				floorResutl = floorRecursive((BSTNode<Integer>) nodeCurrent.getRight(), numero, nodeCurrent.getData());
			} else if(numero < nodeCurrent.getData()){
				floorResutl = floorRecursive((BSTNode<Integer>) nodeCurrent.getLeft(), numero, floorResutl);
			} else {
				floorResutl = nodeCurrent.getData();
			}
		}
		return floorResutl;
	}

	@Override
	public Integer ceil(Integer[] array, double numero) {
		for(Integer elemento : array){
			this.insert(elemento);
		}

		return ceilRecursive(this.root, numero, null);
	}

	private Integer ceilRecursive(BSTNode<Integer> nodeCurrent, double numero, Integer ceilResult) {
		if(!nodeCurrent.isEmpty()){
			if(numero > nodeCurrent.getData()){
				ceilResult = ceilRecursive((BSTNode<Integer>) nodeCurrent.getRight(), numero, ceilResult);
			} else if(numero < nodeCurrent.getData()){
				ceilResult = ceilRecursive((BSTNode<Integer>) nodeCurrent.getLeft(), numero, nodeCurrent.getData());
			} else {
				ceilResult = nodeCurrent.getData();
			}
		}
		return ceilResult;
	}

}
