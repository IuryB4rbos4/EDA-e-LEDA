package adt.hashtable.open;

import adt.hashtable.hashfunction.HashFunctionClosedAddressMethod;
import adt.hashtable.hashfunction.HashFunctionLinearProbing;

public class HashtableOpenAddressLinearProbingImpl<T extends Storable> extends
		AbstractHashtableOpenAddress<T> {

	public HashtableOpenAddressLinearProbingImpl(int size,
			HashFunctionClosedAddressMethod method) {
		super(size);
		hashFunction = new HashFunctionLinearProbing<T>(size, method);
		this.initiateInternalTable(size);
	}

	@Override
	public void insert(T element) {
		if (element != null && !this.isFull()) {
			int probe = 0;
			boolean inserted = false;
	
			while (probe < this.capacity() && !inserted) {
				int hash = this.hash(element, probe);
				if (this.table[hash] == null || this.table[hash].equals(this.deletedElement)) {
					this.table[hash] = element;
					this.elements++;
					this.COLLISIONS += probe;
					inserted = true;
				} else if (this.table[hash].equals(element)) {
					inserted = true;
				} else {
					probe++;
				}
			}
	
			if (!inserted) {
				throw new HashtableOverflowException();
			}
		} else if (this.isFull()) {
			throw new HashtableOverflowException();
		}
	}

	@Override
	public void remove(T element) {
		if (element != null && !this.isEmpty()) {
			int probe = 0;
			boolean removed = false;
	
			while (probe < this.capacity() && !removed) {
				int hash = this.hash(element, probe);
				if (this.table[hash] != null) {
					if (this.table[hash].equals(element)) {
						this.table[hash] = this.deletedElement;
						this.elements--;
						this.COLLISIONS -= probe;
						removed = true;
					} else {
						probe++;
					}
				} else {
					break;
				}
			}
		}
	}

	@Override
	public T search(T element) {
		return this.indexOf(element) != -1 ? element : null;
	}

	@Override
	public int indexOf(T element) {
		if (element != null && !this.isEmpty()) {
			int probe = 0;
	
			while (probe < this.capacity()) {
				int hash = this.hash(element, probe);
	
				if (this.table[hash] != null && this.table[hash].equals(element)) {
					return hash;
				}
	
				probe++;
			}
		}
	
		return -1;
	}

	private int hash (T element, int probe) {
		return ((HashFunctionLinearProbing<T>) this.hashFunction).hash(element, probe);
	}

}
