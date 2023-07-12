package adt.queue;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class StudentQueueTest {

	public Queue<Integer> queue1;
	public Queue<Integer> queue2;
	public Queue<Integer> queue3;

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

	}

	private void getImplementations() {
		// TODO O aluno deve ajustar aqui para instanciar sua implementação
		queue1 = new QueueDoubleLinkedListImpl<Integer>(4);
		queue2 = new QueueDoubleLinkedListImpl<Integer>(2);
		queue3 = new QueueDoubleLinkedListImpl<Integer>(0);
	}

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
			Assert.assertNull(queue2.head());
		} catch (QueueUnderflowException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testEmptyQueue3Head() {
		Assert.assertNull(queue3.head());
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
	public void testIsEmptyQueue3() {
		assertTrue(queue3.isEmpty());
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
	public void testIsFullQueue3() {
		assertTrue(queue3.isFull());
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
			e.printStackTrace();
		}
	}
	
	@Test
	public void testResetQueue() {
		try {
			queue1.dequeue();
			queue1.dequeue(); 
			queue1.dequeue();
			queue1.enqueue(new Integer(1));
			queue1.enqueue(new Integer(2));
			queue1.enqueue(new Integer(3));
			queue1.enqueue(new Integer(4));
			assertTrue(queue1.isFull());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testEnqueueNullElement() {
		try {
			queue1.enqueue(null);
			assertFalse(queue1.isFull());
		} catch (QueueOverflowException e) {
			e.printStackTrace();
		}
	}

	@Test(expected = QueueOverflowException.class)
	public void testEnqueueComErro() throws QueueOverflowException {
		queue2.enqueue(new Integer(5)); // vai depender do tamanho que a fila
										// foi iniciada!!!
	}
	
	@Test(expected = QueueOverflowException.class)
	public void testEnqueueComErroQueue1() throws QueueOverflowException {
		queue1.enqueue(new Integer(5)); // vai depender do tamanho que a fila
		queue1.enqueue(new Integer(7));		// foi iniciada!!!
	}
	
	@Test(expected = QueueOverflowException.class)
	public void testEnqueueComErroQueue3() throws QueueOverflowException {
		queue3.enqueue(new Integer(5)); // vai depender do tamanho que a fila
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
		queue3.dequeue(); // vai depender do
						  // tamanho que a fial
						  // foi iniciada!!!
	}
}