package adt.queue;

import adt.stack.Stack;
import adt.stack.StackImpl;

public class QueueUsingStack<T> implements Queue<T> {

	private Stack<T> stack1;
	private Stack<T> stack2;

	public QueueUsingStack(int size) {
		stack1 = new StackImpl<T>(size);
		stack2 = new StackImpl<T>(size);
	}

	@Override
	public void enqueue(T element) throws QueueOverflowException {
		if (this.isFull()) {
			throw new QueueOverflowException();
		} else if (element != null) {
			try {
				this.stack1.push(element);
			} catch (Exception enqueueException) {
				enqueueException.printStackTrace();
			}
		}
	}

	@Override
	public T dequeue() throws QueueUnderflowException {
		if (this.isEmpty()) {
			throw new QueueUnderflowException();
		}
		try {
			while (!stack1.isEmpty()) {
				stack2.push(stack1.top());
				stack1.pop();
			}
		} catch (Exception dequeuException) {
			dequeuException.printStackTrace();
		}

		T dequeue = stack2.top();

		try {
			stack2.pop();
			while (!stack2.isEmpty()) {
				stack1.push(stack2.top());
				stack2.pop();
			}
		} catch (Exception dequeuException) {
			dequeuException.printStackTrace();
		}

		return dequeue;
	}

	@Override
	public T head() {
		T head = null;

		if (!this.isEmpty()) {
			while (!stack1.isEmpty()) {
				try {
					stack2.push(stack1.top());
					stack1.pop();
				} catch (Exception headException) {
					headException.printStackTrace();
				}
			}

			head = stack2.top();

			while (!stack2.isEmpty()) {
				try {
					stack1.push(stack2.top());
					stack2.pop();
				} catch (Exception headException) {
					headException.printStackTrace();
				}
			}
		} 
		
		return head;
	}

	@Override
	public boolean isEmpty() {
		return this.stack1.isEmpty();
	}

	@Override
	public boolean isFull() {
		return this.stack1.isFull();
	}

}
