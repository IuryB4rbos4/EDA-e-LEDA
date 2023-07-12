package adt.linkedList;

public class SingleLinkedListImpl<T> implements LinkedList<T> {

	protected SingleLinkedListNode<T> head;

	public SingleLinkedListImpl() {
		this.head = new SingleLinkedListNode<T>();
	}

	@Override
	public boolean isEmpty() {
		return this.head.isNIL();
	}

	@Override
	public int size() {
		SingleLinkedListNode<T> node = this.head;
		int size = 0;
		while (!node.isNIL()) {
			node = node.getNext();
			size++;
		}

		return size;
	}

	@Override
	// this.head
	public T search(T element) {
		SingleLinkedListNode<T> node = this.getHead();
		while (!node.isNIL() && node.getData() != element) {
			node = node.getNext();
		}
		return node.getData();
	}

	@Override
	//
	public void insert(T element) {
		if (element != null) {
			SingleLinkedListNode<T> newNode = new SingleLinkedListNode<T>(element, this.head);
			SingleLinkedListNode<T> auxHead = this.head;

			if (this.head.isNIL()) {
				this.head = newNode;
			} else {

				while (!auxHead.getNext().isNIL()) {
					auxHead = auxHead.getNext();
				}

				auxHead.setNext(newNode);
			}
		}
	}

	@Override
	//
	public void remove(T element) {
		if(element != null){
			if(this.head.getData() == element){
				this.head = this.head.getNext();
			} else {
				SingleLinkedListNode<T> node = this.head;
				while(!node.isNIL() && node.getData() != element){
					node = node.getNext();
				}

				node.data = node.next.getData();
				node.next = node.next.getNext();
			}
		}
	}

	@Override
	public T[] toArray() {
		T[] result = (T[]) new Object[this.size()];
		SingleLinkedListNode<T> node = this.head;
		for (int i = 0; i < this.size(); i++) {
			result[i] = node.getData();
			node = node.getNext();
		}
		return result;
	}

	public SingleLinkedListNode<T> getHead() {
		return head;
	}

	public void setHead(SingleLinkedListNode<T> head) {
		this.head = head;
	}

}
