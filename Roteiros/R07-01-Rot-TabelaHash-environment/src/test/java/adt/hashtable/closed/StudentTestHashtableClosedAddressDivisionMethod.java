package adt.hashtable.closed;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import adt.hashtable.hashfunction.HashFunctionClosedAddressMethod;
import adt.hashtable.open.HashtableOverflowException;

public class StudentTestHashtableClosedAddressDivisionMethod {

	protected AbstractHashtableClosedAddress<Integer> table1;
	protected AbstractHashtableClosedAddress<Integer> table2;

	protected final int PROPOSED_SIZE = 100;

	@Before
	public void setUp() throws Exception {
		table1 = new HashtableClosedAddressImpl<Integer>(PROPOSED_SIZE,
				HashFunctionClosedAddressMethod.DIVISION);

		Integer initialValue = 200;
		int increment = 5;
		while (initialValue < 600) {
			table1.insert(initialValue);
			initialValue = initialValue + increment;
		}

		table2 = new HashtableClosedAddressImpl<Integer>(PROPOSED_SIZE,
				HashFunctionClosedAddressMethod.DIVISION);
	}

	@Test
	public void testInsert() {
		assertEquals(0, table1.getCOLLISIONS());
		table1.insert(105); // nao produz colisao
		assertEquals(0, table1.getCOLLISIONS());
		assertEquals(4, table1.indexOf(105));
		table1.insert(110); // nao produz colisao
		assertEquals(0, table1.getCOLLISIONS());
		assertEquals(9, table1.indexOf(110));
		table1.insert(101); // produz colisao no 0
		assertEquals(1, table1.getCOLLISIONS());
		assertEquals(0, table1.indexOf(101));

		table2.insert(103); // nao produz colisao inserindo 1 elemento na table
							// vazia
		assertEquals(0, table2.getCOLLISIONS());
		assertEquals(2, table2.indexOf(103));
	}

	@Test
	public void testRemove() {
		int currentSize = table1.size();
		table1.remove(200); // elemento existente
		assertEquals(currentSize - 1, table1.size());
		assertEquals(-1, table1.indexOf(200));
	}
	
	@Test
	public void testRemoveInexisstentElement() {
		int currentSize = table1.size();
		table1.remove(9); // elemento existente
		assertEquals(currentSize, table1.size());
		assertEquals(-1, table1.indexOf(2));
	}

	@Test
	public void testSearch() {

		// busca um elemento existente. compara a posicao
		assertEquals(new Integer(305), table1.search(305));

	}
	
	@Test
	public void testSearchInAList() {

		// busca um elemento existente. compara a posicao
		table1.insert(9);
		table1.insert(110);
		table1.insert(211);
		table1.insert(312);
		assertEquals(new Integer(211), table1.search(211));

	}

	@Test
	public void testSearchInexistentElement() {
		// busca um elemento inexistente. compara a posicao
		assertNull(table1.search(100));
	}
	
	@Test
	public void testSearchNullElement() {
		assertNull(table1.search(null));
	}
	
	
	@Test
	public void testIndexOf() {

		// busca um elemento existente. compara a posicao
		assertEquals(2, table1.indexOf(305));

	}
	
	@Test
	public void testIndexOfInAList() {

		// busca um elemento existente. compara a posicao
		table1.insert(9);
		table1.insert(110);
		table1.insert(211);
		table1.insert(312);
		assertEquals(9, table1.indexOf(211));

	}

	@Test
	public void testIndefOfInexistentElement() {
		// busca um elemento inexistente. compara a posicao
		assertEquals(-1, table1.indexOf(100));
	}
	
	@Test
	public void testIndexOfNullElement() {
		assertEquals(-1, table1.indexOf(null));
	}

	@Test
	public void testIsEmpty() {
		assertFalse(table1.isEmpty());
	}

	@Test
	public void testIsFull() {
		assertFalse(table1.isFull());
	}

	@Test
	public void testSize() {
		assertEquals(80, table1.size());
	}
}
