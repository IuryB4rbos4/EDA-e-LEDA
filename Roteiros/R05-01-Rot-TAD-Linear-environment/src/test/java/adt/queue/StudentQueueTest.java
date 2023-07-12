package adt.queue;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import adt.stack.StackUnderflowException;

public class StudentQueueTest {

	public Queue<Integer> queue1;
	public Queue<Integer> queue2;
	public Queue<Integer> queue3;
	public Queue<Integer> queue4;
	public Queue<Integer> queue5;
	public Queue<Integer> queue6;
	public Queue<Integer> queue7;
	public Queue<Integer> queue8;
	public Queue<Integer> queue9;

	@Before
	public void setUp() throws QueueOverflowException {

		getImplementations();

		// Fila com 3 elementos não cheia.
		queue1.enqueue(1);
		queue1.enqueue(2);
		queue1.enqueue(3);

		// Fila com 2 elementos de tamanho 2. Fila cheia.
		queue2.enqueue(1);
		queue2.enqueue(2);
		
		
		// Fila circular com 3 elementos não cheia.
		queue3.enqueue(1);
		queue3.enqueue(2);
		queue3.enqueue(3);

		// Fila circular com 2 elementos de tamanho 2. Fila cheia.
		queue4.enqueue(1);
		queue4.enqueue(2);
		
		// Fila de pilhas com 3 elementos não cheia.
		queue5.enqueue(1);
		queue5.enqueue(2);
		queue5.enqueue(3);

		// Fila de pilhas com 2 elementos de tamanho 2. Fila cheia.
		queue6.enqueue(1);
		queue6.enqueue(2);

	}

	private void getImplementations() {
		// TODO O aluno deve ajustar aqui para instanciar sua implementação
		queue1 = new QueueImpl<Integer>(4);
		queue2 = new QueueImpl<Integer>(2);
		queue3 = new CircularQueue<Integer>(4);
		queue4 = new CircularQueue<Integer>(2);
		queue5 = new QueueUsingStack<Integer>(4);
		queue6 = new QueueUsingStack<Integer>(2);
		queue7 = new QueueImpl<Integer>(0);
		queue8 = new CircularQueue<Integer>(0);
		queue9 = new QueueUsingStack<Integer>(0);
		
	}

	// MÉTODOS DE TESTE
	
	// TESTES DA QUEUE COM SHIFTLEFT
	
	@Test
	public void testQueueImplHead() {
		Integer expected = 1;
		Integer result = queue1.head();
		assertEquals(expected, result);
	}
	
	
	@Test
	public void testQueueImplThirdHead() throws QueueUnderflowException {
		queue1.dequeue();
		queue1.dequeue();
		Integer expected = 3;
		Integer result = queue1.head();
		assertEquals(expected, result);
	}
	
	@Test
	public void testEmptyQueueHead() {
		try {
			queue2.dequeue();
			queue2.dequeue();			
			assertNull(queue2.head());
		} catch (QueueUnderflowException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testEmptyQueue7Head() {
		assertNull(queue7.head());
	}

	@Test
	public void testIsEmptyQueue1() {
		assertFalse(queue1.isEmpty());
	}
	
	@Test
	public void testIsEmptyQueue2s() {
		assertFalse(queue2.isEmpty());
	}
	
	@Test
	public void testIsEmptyQueue7() {
		assertTrue(queue7.isEmpty());
	}

	@Test
	public void testIsFullQueue1() {
		assertFalse(queue1.isFull());
	}
	
	@Test
	public void testIsFullQueue2() {
		assertTrue(queue2.isFull());
	}
	
	@Test
	public void testIsFullQueue7() {
		assertTrue(queue7.isFull());
	}
	
	@Test
	public void testOneElementIsntEmpty() {
		try {
			queue2.dequeue();
			assertFalse(queue2.isEmpty());
		} catch (QueueUnderflowException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testEnqueue() {
		try {
			queue1.enqueue(new Integer(5));
			assertTrue(queue1.isFull());
		} catch (QueueOverflowException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testEnqueueNullElement() {
		try {
			queue1.enqueue(null);
			assertFalse(queue1.isFull());
		} catch (QueueOverflowException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test(expected = QueueOverflowException.class)
	public void testEnqueueComErro() throws QueueOverflowException {
		queue2.enqueue(new Integer(5)); // vai depender do tamanho que a fila
										// foi iniciada!!!
	}
	
	@Test(expected = QueueOverflowException.class)
	public void testEnqueueComErroQueue7() throws QueueOverflowException {
		queue7.enqueue(new Integer(5)); // vai depender do tamanho que a fila
										// foi iniciada!!!
	}

	@Test
	public void testDequeue() {
		try {
			assertEquals(new Integer(1), queue1.dequeue());
			assertEquals(new Integer(2), queue1.head());
		} catch (QueueUnderflowException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test(expected = QueueUnderflowException.class)
	public void testDequeueComErro() throws QueueUnderflowException {
		queue1.dequeue(); // vai depender do
		queue1.dequeue(); // tamanho que a fial
		queue1.dequeue();
		queue1.dequeue(); // foi iniciada!!!
	}
	
	@Test(expected = QueueUnderflowException.class)
	public void testDequeueComErroQueue7() throws QueueUnderflowException {
		queue7.dequeue(); // vai depender do
						  // tamanho que a fial
						  // foi iniciada!!!
	}
	
	
	// TESTES DA CIRCULAR QUEUE
	
	
	@Test
	public void testCircularQueueHead() {
		Integer expected = 1;
		Integer result = queue3.head();
		assertEquals(expected, result);
	}
	
	@Test
	public void testCircularQueueThirdHead() throws QueueUnderflowException {
		queue3.dequeue();
		queue3.dequeue();
		Integer expected = 3;
		Integer result = queue3.head();
		assertEquals(expected, result);
	}
	
	@Test
	public void testEmptyCircularQueueHead() {
		try {
			queue4.dequeue();
			queue4.dequeue();			
			assertNull(queue4.head());
		} catch (QueueUnderflowException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testEmptyQueue8Head() {
		assertNull(queue8.head());
	}

	@Test
	public void testIsEmptyQueue3() {
		assertFalse(queue3.isEmpty());
	}
	
	@Test
	public void testIsEmptyQueue4() {
		assertFalse(queue4.isEmpty());
	}
	
	@Test
	public void testIsEmptyQueue8() {
		assertTrue(queue8.isEmpty());
	}

	@Test
	public void testIsFullQueue3() {
		assertFalse(queue3.isFull());
	}
	
	@Test
	public void testIsFullQueue4() {
		assertTrue(queue4.isFull());
	}
	
	@Test
	public void testIsFullQueue8() {
		assertTrue(queue8.isFull());
	}
	
	@Test
	public void testOneElementIsntEmptyCircularQueue() {
		try {
			queue4.dequeue();
			assertFalse(queue4.isEmpty());
		} catch (QueueUnderflowException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testCircularEnqueue() {
		try {
			queue3.enqueue(new Integer(5));
			assertTrue(queue3.isFull());
		} catch (QueueOverflowException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testCircularEnqueueNullElement() {
		try {
			queue3.enqueue(null);
			assertFalse(queue3.isFull());
		} catch (QueueOverflowException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test(expected = QueueOverflowException.class)
	public void testCircularEnqueueComErro() throws QueueOverflowException {
		queue4.enqueue(new Integer(5)); // vai depender do tamanho que a fila
										// foi iniciada!!!
	}
	
	@Test(expected = QueueOverflowException.class)
	public void testCircularEnqueueComErroQueue8() throws QueueOverflowException {
		queue8.enqueue(new Integer(5)); // vai depender do tamanho que a fila
										// foi iniciada!!!
	}
	
	@Test(expected = QueueOverflowException.class)
	public void testCircularEnqueueFullQueueAtMiddle() throws QueueOverflowException, 
	QueueUnderflowException {
		// Após 2 dequeues meu head vai para a posição 2
		// A estratégia é dar enqueues para dar a volta no array
		// e tentar dar enqueues por trás do head
		// dando overflow quando o tail atingir o head
		
		queue3.dequeue();
		queue3.dequeue();
		queue3.enqueue(new Integer(4));
		queue3.enqueue(new Integer(1));
		queue3.enqueue(new Integer(2));
		
		// enqueue na posição 2 que estourará overflow
		queue3.enqueue(new Integer(3));
	}

	@Test
	public void testCircularDequeue() {
		try {
			assertEquals(new Integer(1), queue3.dequeue());
			assertEquals(new Integer(2), queue3.head());
		} catch (QueueUnderflowException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test(expected = QueueUnderflowException.class)
	public void testCircularDequeueComErro() throws QueueUnderflowException {
		queue3.dequeue(); // vai depender do
		queue3.dequeue(); // tamanho que a fial
		queue3.dequeue();
		queue3.dequeue(); // foi iniciada!!!
	}
	
	@Test(expected = QueueUnderflowException.class)
	public void testCircularDequeueComErroQueue8() throws QueueUnderflowException {
		queue8.dequeue(); // vai depender do
						  // tamanho que a fial
						  // foi iniciada!!!
	}
	
	// TESTES DA QUEUE COM DUAS STACKS
	
	@Test
	public void testStackQueueHead() {
		Integer expected = 1;
		Integer result = queue5.head();
		assertEquals(expected, result);
	}
	
	@Test
	public void testStacksQueueThirdHead() throws QueueUnderflowException {
		queue5.dequeue();
		queue5.dequeue();
		Integer expected = 3;
		Integer result = queue5.head();
		assertEquals(expected, result);
	}
	
	@Test
	public void testEmptyStackQueueHead() {
		try {
			queue6.dequeue();
			queue6.dequeue();			
			assertNull(queue6.head());
		} catch (QueueUnderflowException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testEmptyStackQueue9Head() {
		assertNull(queue9.head());
	}

	@Test
	public void testIsEmptyStackQueue5() {
		assertFalse(queue5.isEmpty());
	}
	
	@Test
	public void testIsEmptyStackQueue6() {
		assertFalse(queue6.isEmpty());
	}
	
	@Test
	public void testIsEmptyStackQueue9() {
		assertTrue(queue9.isEmpty());
	}

	@Test
	public void testIsFullStackQueue5() {
		assertFalse(queue5.isFull());
	}
	
	@Test
	public void testIsFullStackQueue6() {
		assertTrue(queue6.isFull());
	}
	
	@Test
	public void testIsFullStackQueue9() {
		assertTrue(queue9.isFull());
	}
	
	@Test
	public void testOneElementIsntEmptyStackQueue() {
		try {
			queue6.dequeue();
			assertFalse(queue6.isEmpty());
		} catch (QueueUnderflowException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testStackEnqueue() {
		try {
			queue5.enqueue(new Integer(5));
			assertTrue(queue5.isFull());
		} catch (QueueOverflowException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testStackEnqueueNullElement() {
		try {
			queue5.enqueue(null);
			assertFalse(queue5.isFull());
		} catch (QueueOverflowException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test(expected = QueueOverflowException.class)
	public void testStackEnqueueComErro() throws QueueOverflowException {
		queue6.enqueue(new Integer(5)); // vai depender do tamanho que a fila
										// foi iniciada!!!
	}
	
	@Test(expected = QueueOverflowException.class)
	public void testStackEnqueueComErroQueue9() throws QueueOverflowException {
		queue9.enqueue(new Integer(5)); // vai depender do tamanho que a fila
										// foi iniciada!!!
	}

	@Test
	public void testStackDequeue() {
		try {
			assertEquals(new Integer(1), queue5.dequeue());
			assertEquals(new Integer(2), queue5.head());
		} catch (QueueUnderflowException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test(expected = QueueUnderflowException.class)
	public void testStackDequeueComErro() throws QueueUnderflowException {
		queue5.dequeue(); // vai depender do
		queue5.dequeue(); // tamanho que a fial
		queue5.dequeue();
		queue5.dequeue(); // foi iniciada!!!
	}
	
	@Test(expected = QueueUnderflowException.class)
	public void testStackDequeueComErroQueue9() throws QueueUnderflowException {
		queue9.dequeue(); // vai depender do
						  // tamanho que a fial
						  // foi iniciada!!!
	}
}