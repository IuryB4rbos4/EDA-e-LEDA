package adt.heap.extended;

import java.util.Comparator;

import adt.heap.HeapImpl;

public class FloorCeilHeapImpl extends HeapImpl<Integer> implements FloorCeilHeap {

	public FloorCeilHeapImpl(Comparator<Integer> comparator) {
		super(comparator);
	}

	@Override
	public Integer floor(Integer[] array, double numero) {
		for(Integer value : array){
			this.insert(value);
		}


		return this.floorRecursive(numero, null);
	}

	private Integer floorRecursive(double numero, Integer floor) {
		Integer root = this.extractRootElement();

		if(root != null){
			if(numero >= root && (floor == null || root >= floor)){
				floor = this.floorRecursive(numero, root);
			} else {
				floor = floorRecursive(numero, floor);
			}
		}

		return floor;
	}

	@Override
	public Integer ceil(Integer[] array, double numero) {
		for(Integer value : array){
			this.insert(value);
		}

		return this.ceilRecursive(numero, null);
	}

	private Integer ceilRecursive(double numero, Integer ceil) {
		Integer root = this.extractRootElement();

		if(root != null){
			if(numero <= root && (ceil == null || root <= ceil)){
				ceil = this.ceilRecursive(numero, root);
			} else {
				ceil = ceilRecursive(numero, ceil);
			}
		}

		return ceil;
	}

}
