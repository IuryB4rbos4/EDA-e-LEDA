package adt.hashtable.open;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import adt.hashtable.hashfunction.HashFunctionClosedAddressMethod;
import adt.hashtable.open.AbstractHashtableOpenAddress;
import adt.hashtable.open.HashtableOpenAddressQuadraticProbingImpl;
import adt.hashtable.open.HashtableElement;

public class StudentTestHashtableOpenAddressQuadraticProbing {
	protected AbstractHashtableOpenAddress<HashtableElement> table1;
	protected AbstractHashtableOpenAddress<HashtableElement> table2;

	protected final int PROPOSED_SIZE = 10;

	@Before
	public void setUp() throws Exception {
		table1 = new HashtableOpenAddressQuadraticProbingImpl<HashtableElement>(
				PROPOSED_SIZE, HashFunctionClosedAddressMethod.DIVISION, 3, 5);
		// o tamanho real utilizado vai ser PROPOSED_SIZE
		table1.insert(new HashtableElement(10)); // coloca no slot indexado com
													// 0
		table1.insert(new HashtableElement(15)); // coloca no slot indexado com
													// 5
		table1.insert(new HashtableElement(2)); // coloca no slot indexado com 2
		table1.insert(new HashtableElement(12)); // coloca no slot indexado com
													// 8, teve 2 colisoes
		table1.insert(new HashtableElement(4)); // coloca no slot indexado com 4
		table1.insert(new HashtableElement(8)); // coloca no slot indexado com
												// 6, teve 1 colisao
		//[10,-,2,-,4,15,8,-,12,-]
		//[0,1,2,3,4,5,6,7,8,9]

		table2 = new HashtableOpenAddressQuadraticProbingImpl<HashtableElement>(
				PROPOSED_SIZE, HashFunctionClosedAddressMethod.DIVISION, 3, 5);
	}

	@Test
	public void testInsert() {
		assertEquals(3, table1.getCOLLISIONS());
		table1.insert(new HashtableElement(11)); // nao tem colisao. coloca no
													// slot indexado com 1
		assertEquals(3, table1.getCOLLISIONS());
		assertEquals(1, table1.indexOf(new HashtableElement(11)));

		table1.insert(new HashtableElement(21)); // tem 1 colisao. coloca no
													// slot indexado com 9
		assertEquals(4, table1.getCOLLISIONS());
		assertEquals(9, table1.indexOf(new HashtableElement(21)));

	}
	
	@Test
	public void testInsertAtDeleted() {
		table1.remove(new HashtableElement(4)); // table[4] é D

		table1.insert(new HashtableElement(14)); // 2->3->D
		assertEquals(4, table1.indexOf(new HashtableElement(14)));
		assertEquals(6, table1.size());

	}
	
	@Test(expected = HashtableOverflowException.class)
	public void testOverflow() throws HashtableOverflowException {
		HashtableOpenAddressQuadraticProbingImpl<HashtableElement> hashTableEx = 
				new HashtableOpenAddressQuadraticProbingImpl<HashtableElement>(
						10,
						HashFunctionClosedAddressMethod.DIVISION,
						3,
						5
				);
		
		for (int i = 0; i < 10; i++) {
			hashTableEx.insert(new HashtableElement(i));
		}
		
		assertEquals(0, hashTableEx.getCOLLISIONS());
		assertEquals(0, hashTableEx.indexOf(new HashtableElement(0)));
		assertEquals(9, hashTableEx.indexOf(new HashtableElement(9)));
		assertTrue(hashTableEx.isFull());
		hashTableEx.insert(new HashtableElement(10));
	}
	
	@Test(expected = HashtableOverflowException.class)
	public void testOverflowWithProbing() throws HashtableOverflowException {
		HashtableOpenAddressQuadraticProbingImpl<HashtableElement> hashTableEx = 
				new HashtableOpenAddressQuadraticProbingImpl<HashtableElement>(
						10,
						HashFunctionClosedAddressMethod.DIVISION,
						3,
						5
				);
		
		for (int i = 0; i < 10; i++) {
			hashTableEx.insert(new HashtableElement(i));
		}
		
		assertEquals(0, hashTableEx.getCOLLISIONS());
		assertEquals(0, hashTableEx.indexOf(new HashtableElement(0)));
		assertEquals(9, hashTableEx.indexOf(new HashtableElement(9)));
		assertTrue(hashTableEx.isFull());
		hashTableEx.insert(new HashtableElement(72)); // index 2
	}

	@Test
	public void testRemove() {
		table1.remove(new HashtableElement(17)); // elemento inexistente
		assertEquals(6, table1.size());

		table1.remove(new HashtableElement(12)); // elemento existente
		assertEquals(5, table1.size());
		assertNull(table1.search(new HashtableElement(12)));
	}
	
	@Test
	public void testRemoveACollision() {
		// 3: 3 != 14
		// 4: 4 != 14
		// 5: 5 != 14
		table1.remove(new HashtableElement(14));
		assertEquals(6, table1.size());

	}
	
	
	@Test
	public void testRemoveInexistentInFullTable() {
		// 3: 3 != 14
		// 4: 4 != 14
		// 5: 5 != 14
		// 6: 6 != 14
		// 7: 7 != 14
		// 8: 8 != 14
		// 9: 9 != 14
		table1.insert(new HashtableElement(1));
		table1.insert(new HashtableElement(3));
		table1.insert(new HashtableElement(7));
		table1.insert(new HashtableElement(9));
		assertEquals(10, table1.size());
		table1.remove(new HashtableElement(14));
		assertEquals(10, table1.size());

	}
	
	@Test
	public void testRemoveInexistentLastElementIsDeleted() {
		// 1: 11
		// 4: 4
		// 9: D
		table1.insert(new HashtableElement(11));
		table1.insert(new HashtableElement(9));
		table1.remove(new HashtableElement(9));
		assertEquals(7, table1.size());
		assertNull(table1.search(new HashtableElement(21)));
		assertEquals(7, table1.size());
	}
	
	@Test
	public void testRemoveDespiteDeleted() {
		HashtableElement value = new HashtableElement(20);
		table1.insert(new HashtableElement(3));
		table1.remove(new HashtableElement(12));
		table1.insert(value);
		assertEquals(7, table1.size());
		table1.remove(value); // 10->D->20
		assertEquals(6, table1.size());
	}
	
	@Test
	public void testRemoveWithDeletedAtLast() {
		table1.insert(new HashtableElement(19));
		table1.remove(new HashtableElement(19));
		assertEquals(6, table1.size());
		table1.remove(new HashtableElement(29)); // table[9] == D
		assertEquals(6, table1.size()); // não removeu nada
	}
	
	@Test
	public void testSearch() {
		assertEquals(new HashtableElement(15),
				table1.search(new HashtableElement(15))); // elemento que existe
		assertNull(table1.search(new HashtableElement(17))); // elemento que nao
																// existe
	}
	
	@Test
	public void testSearchWithProbing() {
		// 1: 11
		// 4: 4
		// 9: 21
		table1.insert(new HashtableElement(11));
		table1.insert(new HashtableElement(21));
		assertEquals(new HashtableElement(21), table1.search(new HashtableElement(21)));
	}
	
	@Test
	public void testStopSearchingAtNull() {
		assertNull(table1.search(new HashtableElement(13))); // 2->3->4->5->null
	}
	
	@Test
	public void testSearchDespiteDeleted() {
		HashtableElement value = new HashtableElement(13);
		table1.insert(value);
		table1.remove(new HashtableElement(4));
		assertEquals(value, table1.search(value)); // 2->3->D->5->13
	}
	
	@Test
	public void testSearchWithDeletedAtLast() {
		table1.insert(new HashtableElement(11));
		table1.insert(new HashtableElement(9));
		table1.remove(new HashtableElement(9));
		assertNull(table1.search(new HashtableElement(14))); // 1->4->D
	}
	
	
	@Test
	public void testIndexOf() {
		assertEquals(5,
				table1.indexOf(new HashtableElement(15))); // elemento que existe
		assertEquals(-1, table1.indexOf(new HashtableElement(17))); // elemento que nao
																// existe
	}
	
	@Test
	public void testIndexOfWithProbing() {
		// 1: 21
		// 4: 4
		// 9: 31
		table1.insert(new HashtableElement(21));
		table1.insert(new HashtableElement(31));
		assertEquals(9, table1.indexOf(new HashtableElement(31)));
	}
	
	@Test
	public void testStopSearchingIndexOfAtNull() {
		assertEquals(-1, table1.indexOf(new HashtableElement(13))); // 2->3->4->5->null
	}
	
	@Test
	public void testIndexOfDespiteDeleted() {
		HashtableElement value = new HashtableElement(20);
		table1.insert(new HashtableElement(3));
		table1.remove(new HashtableElement(12));
		table1.insert(value);
		table1.remove(new HashtableElement(3));
		assertEquals(8, table1.indexOf(value)); // 10->D->20
	}
	
	@Test
	public void testIndexOfWithDeletedAtLast() {
		table1.insert(new HashtableElement(11));
		assertEquals(-1, table1.indexOf(new HashtableElement(21))); // 11->4->D
	}

	@Test
	public void testIsEmpty() {
		assertFalse(table1.isEmpty());
		table1.remove(new HashtableElement(10)); // esvazia
		table1.remove(new HashtableElement(2));
		table1.remove(new HashtableElement(4));
		table1.remove(new HashtableElement(15));
		table1.remove(new HashtableElement(8));
		table1.remove(new HashtableElement(12));
		assertTrue(table1.isEmpty());

		assertTrue(table2.isEmpty());
	}

	@Test
	public void testIsFull() {
		assertFalse(table1.isFull());
		table1.insert(new HashtableElement(1)); // enche a tabela
		table1.insert(new HashtableElement(3));
		table1.insert(new HashtableElement(7));
		table1.insert(new HashtableElement(9));
		assertTrue(table1.isFull());

		assertFalse(table2.isFull());
	}

	@Test
	public void testSize() {
		assertEquals(6, table1.size());
	}


}