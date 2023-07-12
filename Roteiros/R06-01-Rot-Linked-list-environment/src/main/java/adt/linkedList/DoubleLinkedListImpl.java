package adt.linkedList;

public class DoubleLinkedListImpl<T> extends SingleLinkedListImpl<T> implements
		DoubleLinkedList<T> {

	protected DoubleLinkedListNode<T> last;

	public DoubleLinkedListImpl() {
		super();
		this.head = new DoubleLinkedListNode<T>();
		this.last = (DoubleLinkedListNode<T>) this.head;
	}

	@Override
	public void insertFirst(T element) {

		if (element != null) {
			DoubleLinkedListNode<T> newNode = new DoubleLinkedListNode<T>();
			DoubleLinkedListNode<T> lastHead = (DoubleLinkedListNode<T>) this.head;
			lastHead.previous = newNode;

			if (this.head.isNIL()) {
				this.last = new DoubleLinkedListNode<T>(element, newNode, lastHead);
			}
			
			this.head = new DoubleLinkedListNode<T>(element, newNode, lastHead);
		}

	}

	@Override
	//
	public void insert(T element) {

		if (element != null) {
			DoubleLinkedListNode<T> newNode = new DoubleLinkedListNode<T>();
			this.last.next = newNode;

			if (this.head.isNIL() && this.last.isNIL()) {
				this.head = new DoubleLinkedListNode<T>(element, newNode, this.last);
				this.last = new DoubleLinkedListNode<T>(element, newNode, this.last);
			} 
			
			this.last = new DoubleLinkedListNode<T>(element, newNode, this.last);
		}

	}

	@Override
	public void removeFirst() {
		if(!this.head.isNIL()){
			this.head = (DoubleLinkedListNode<T>) this.head.next;
			if(this.head.isNIL()){
				this.last = (DoubleLinkedListNode<T>) this.head;
			} else {
				DoubleLinkedListNode<T> node = (DoubleLinkedListNode<T>) this.head;
				node.previous = new DoubleLinkedListNode<T>();
			}
		}
	}

	@Override
	public void removeLast() {
		if(!this.last.isNIL()){
			this.last = this.last.previous;

			if(this.last.isNIL()){
				this.head = this.last;
			} else {
				this.last.next = new DoubleLinkedListNode<T>();
			}
		}
	}

	@Override
	//
	public void remove(T element) {
		if(element != null){
			if(this.head.getData() == element){
				this.head = (DoubleLinkedListNode<T>) this.head.getNext();
			} else {
				DoubleLinkedListNode<T> node = (DoubleLinkedListNode<T>) this.head;
				while(!node.isNIL() && node.getData() != element){
					node = (DoubleLinkedListNode<T>) node.getNext();
				}

				node.previous.next = (DoubleLinkedListNode<T>) node.next;
				node = (DoubleLinkedListNode<T>) node.next;
				node.previous = (DoubleLinkedListNode<T>) node.previous;
			}
		}
	}

	public DoubleLinkedListNode<T> getLast() {
		return last;
	}

	public void setLast(DoubleLinkedListNode<T> last) {
		this.last = last;
	}

	@Override
	public T[] toArray() {
		T[] result = (T[]) new Object[this.size()];
		DoubleLinkedListNode<T> node = (DoubleLinkedListNode<T>) this.head;
		for (int i = 0; i < this.size(); i++) {
			result[i] = node.getData();
			node = (DoubleLinkedListNode<T>) node.getNext();
		}
		return result;
	}

	@Override
	public int size() {
		DoubleLinkedListNode<T> node = (DoubleLinkedListNode<T>) this.head;
		int size = 0;
		while (!node.isNIL()) {
			node = (DoubleLinkedListNode<T>) node.getNext();
			size++;
		}

		return size;
	}

	@Override
	//
	public T search(T element) {
		DoubleLinkedListNode<T> node = (DoubleLinkedListNode<T>) this.getHead();
		while (!node.isNIL() && node.getData() != element) {
			node = (DoubleLinkedListNode<T>) node.getNext();
		}
		return node.getData();
	}

}
