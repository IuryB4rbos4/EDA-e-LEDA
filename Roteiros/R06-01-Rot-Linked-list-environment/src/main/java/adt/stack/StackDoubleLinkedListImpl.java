package adt.stack;

import adt.linkedList.DoubleLinkedList;
import adt.linkedList.DoubleLinkedListImpl;

public class StackDoubleLinkedListImpl<T> implements Stack<T> {

	protected DoubleLinkedList<T> top;
	protected int size;

	public StackDoubleLinkedListImpl(int size) {
		this.size = size;
		this.top = new DoubleLinkedListImpl<T>();
	}

	@Override
	//
	public void push(T element) throws StackOverflowException {
		if(this.isFull()){
			throw new StackOverflowException();
		} else if (element != null){
			this.top.insertFirst(element);
		}		
	}

	@Override
	//
	public T pop() throws StackUnderflowException {
		if(isFull()){
			throw new StackUnderflowException();
		}

		T removeTop = this.top();

		this.top.removeFirst();

		return removeTop;
	}

	@Override
	public T top() {
		T top = null;

		if(!this.isEmpty()){
			top = this.top.toArray()[0];
		}

		return top;
	}

	@Override
	public boolean isEmpty() {
		return this.top.isEmpty();
	}

	@Override
	public boolean isFull() {
		return this.size == this.top.toArray().length;
	}

}
