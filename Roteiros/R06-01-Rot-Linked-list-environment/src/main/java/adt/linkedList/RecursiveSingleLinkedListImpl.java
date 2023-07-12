package adt.linkedList;

public class RecursiveSingleLinkedListImpl<T> implements LinkedList<T> {

	protected T data;
	protected RecursiveSingleLinkedListImpl<T> next;

	public RecursiveSingleLinkedListImpl() {

	}


	@Override
	public boolean isEmpty() {
		return this.data == null;
	}

	@Override
	public int size() {
		if(this.isEmpty()){
			return 0;
		} else {
			return 1 + this.next.size();
		}
	}

	@Override
	public T search(T element) {
		if(this.isEmpty()){
			return null;
		} else {
			if(this.data == element){
				return this.data;
			} else {
				return this.next.search(element);
			}
		}
	}

	@Override
	public void insert(T element) {
		if(element != null){
			if(this.isEmpty()){
				this.data = element;
				this.next = new RecursiveSingleLinkedListImpl<>();
			} else {
				this.next.insert(element);
			}
		}
	}

	@Override
	public void remove(T element) {
		if(element != null){
			if(this.data == element){
				this.data = this.next.data;
				this.next = this.next.next;
			} else {
				if(this.next != null){
					this.next.remove(element);
				}
			}
		}
	}

	@Override
	public T[] toArray() {
		return toArrayRecursivo(this, size());
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public RecursiveSingleLinkedListImpl<T> getNext() {
		return next;
	}

	public void setNext(RecursiveSingleLinkedListImpl<T> next) {
		this.next = next;
	}

	private T[] toArrayRecursivo(RecursiveSingleLinkedListImpl<T> node, int size){
		if(node.isEmpty()){
			return (T[]) new Object[size];
		} else {
			T[] array = toArrayRecursivo(node.getNext(), size - 1);
			array[size - 1] = node.getData();
			return array;
		}
	}

}
