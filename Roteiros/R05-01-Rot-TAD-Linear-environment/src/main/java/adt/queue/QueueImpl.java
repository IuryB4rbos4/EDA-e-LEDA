package adt.queue;

public class QueueImpl<T> implements Queue<T> {

	private T[] array;
	private int tail;

	@SuppressWarnings("unchecked")
	public QueueImpl(int size) {
		array = (T[]) new Object[size];
		tail = -1;
	}

	@Override
	public T head() {
		if(isEmpty()){ return null;}

		return this.array[0];
	}

	@Override
	public boolean isEmpty() {
		return this.tail == -1;

	}

	@Override
	public boolean isFull() {
		return this.tail == this.array.length - 1;
	}

	private void shiftLeft() {
		for(int i = 0; i < this.array.length - 1; i ++){
			this.array[i] = this.array[i + 1];
		}
	}

	@Override
	public void enqueue(T element) throws QueueOverflowException {
		if(this.isFull()){
			throw new QueueOverflowException();
		} else if(element != null){
			this.array[++this.tail] = element;
		}

	}

	@Override
	public T dequeue() throws QueueUnderflowException {
		if(this.isEmpty()){
			throw new QueueUnderflowException();
		}
		T dequeue = this.array[0];
		shiftLeft();
		this.tail--;

		return dequeue;
	}

}
