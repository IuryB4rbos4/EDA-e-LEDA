package adt.stack;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import adt.queue.QueueUnderflowException;

public class StudentStackTest {

	public Stack<Integer> stack1;
	public Stack<Integer> stack2;
	public Stack<Integer> stack3;

	@Before
	public void setUp() throws StackOverflowException {

		getImplementations();

		// Pilha com 3 elementos não cheia.
		stack1.push(1);
		stack1.push(2);
		stack1.push(3);

		// Pilha com 2 elementos de tamanho 2, pilha cheia.
		stack2.push(1);
		stack2.push(2);

	}

	private void getImplementations() {
		// TODO O aluno deve ajustar aqui para instanciar sua implementação
		stack1 = new StackImpl<Integer>(4);
		stack2 = new StackImpl<Integer>(2);
		stack3 = new StackImpl<Integer>(0);
	}

	// MÉTODOS DE TESTE
	@Test
	public void testTop() {
		assertEquals(new Integer(3), stack1.top());
	}
	
	@Test
	public void testEmptyStackTop() {
		try {
			stack2.pop();
			stack2.pop();			
			assertNull(stack2.top());
		} catch (StackUnderflowException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testEmptyStack3Top() {
		assertNull(stack3.top());
	}

	@Test
	public void testIsEmpty() {
		assertFalse(stack1.isEmpty());
	}
	
	@Test
	public void testIsEmptyStack3() {
		assertTrue(stack3.isEmpty());
	}

	@Test
	public void testIsEmptyStack2() {
		assertFalse(stack2.isEmpty());
	}
	
	@Test
	public void testIsFull() {
		assertFalse(stack1.isFull()); // vai depender do tamanho que a pilha foi
		// iniciada!!!!
	}

	@Test
	public void testIsFullStack2() {
		assertTrue(stack2.isFull());
	}
	
	@Test
	public void testIsFullStack3() {
		assertTrue(stack3.isFull());
	}
	
	@Test
	public void testOneElementIsntEmpty() {
		try {
			stack2.pop();
			assertFalse(stack2.isEmpty());
		} catch (StackUnderflowException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testPush() {
		try {
			Integer expected = 5;
			
			stack1.push(new Integer(5));
			
			Integer result = stack1.top();
			assertEquals(expected, result);
			assertEquals(expected, stack1.top());
		} catch (StackOverflowException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testPushNullElement() {
		try {
			Integer expected = 3;
			
			stack1.push(null);
			
			Integer result = stack1.top();
			assertEquals(expected, result);
		} catch (StackOverflowException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test(expected = StackOverflowException.class)
	public void testPushComErro() throws StackOverflowException {
		stack1.push(new Integer(4));
		stack1.push(new Integer(5)); // levanta excecao apenas se o tamanhonao
										// permitir outra insercao
	}

	@Test
	public void testPop() {
		try {
			Integer expected = 2;
			
			assertEquals(new Integer(3), stack1.pop());
			assertEquals(expected, stack1.top());
		} catch (StackUnderflowException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test(expected = StackUnderflowException.class)
	public void testPopComErro() throws StackUnderflowException {
		stack1.pop();
		stack1.pop();
		stack1.pop();
		stack1.pop(); // levanta excecao apenas se
					  // stack1 for vazia
	}
	
	@Test(expected = StackOverflowException.class)
	public void testPushComErroStack3() throws StackOverflowException {
		stack3.push(new Integer(4)); // levanta excecao apenas se o tamanhonao
									// permitir outra insercao
	}
	
	@Test(expected = StackUnderflowException.class)
	public void testPopComErroStack3() throws StackUnderflowException {
		stack3.pop(); // levanta excecao apenas se
													// stack1 for vazia
	}
}