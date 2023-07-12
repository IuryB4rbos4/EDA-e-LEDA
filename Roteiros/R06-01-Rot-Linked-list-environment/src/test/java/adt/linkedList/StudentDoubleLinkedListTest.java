package adt.linkedList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class StudentDoubleLinkedListTest extends StudentLinkedListTest {

	@Before
	public void setUp() throws Exception {

		getImplementations();

		// Lista iterativa double com 3 elementos.
		lista1.insert(3);
		lista1.insert(2);
		lista1.insert(1);
		
		// Lista recursiva double com 3 elementos
		lista3.insert(3);
		lista3.insert(2);
		lista3.insert(1);
	}

	private void getImplementations() {
		lista1 = new DoubleLinkedListImpl<Integer>();
		lista2 = new DoubleLinkedListImpl<Integer>();
		lista3 = new RecursiveDoubleLinkedListImpl<Integer>();
		lista4 = new RecursiveDoubleLinkedListImpl<Integer>();
	}

	// Métodos de DoubleLinkedList

	@Test
	public void testInsertFirst() {
		((DoubleLinkedList<Integer>) lista1).insertFirst(4);
		Assert.assertArrayEquals(new Integer[] { 4, 3, 2, 1 }, lista1.toArray());
		((DoubleLinkedList<Integer>) lista1).insertFirst(7);
		Assert.assertArrayEquals(new Integer[] { 7, 4, 3, 2, 1 }, lista1.toArray());
	}
	
	
	@Test
	public void testInsertFirstInEmptyList() {
		((DoubleLinkedList<Integer>) lista2).insertFirst(4);
		Assert.assertArrayEquals(new Integer[] { 4 }, lista2.toArray());
		((DoubleLinkedList<Integer>) lista2).insertFirst(7);
		Assert.assertArrayEquals(new Integer[] { 7, 4 }, lista2.toArray());
	}
	
	@Test
	public void testInsertNullElement() {
		((DoubleLinkedList<Integer>) lista1).insertFirst(null);
		Assert.assertArrayEquals(new Integer[] { 3, 2, 1 }, lista1.toArray());
		((DoubleLinkedList<Integer>) lista1).insertFirst(null);
		Assert.assertArrayEquals(new Integer[] { 3, 2, 1 }, lista1.toArray());
		((DoubleLinkedList<Integer>) lista1).insertFirst(4);
		Assert.assertArrayEquals(new Integer[] { 4, 3, 2, 1 }, lista1.toArray());
	}
	
	@Test
	public void testInsertNullElementInEmptyList() {
		((DoubleLinkedList<Integer>) lista2).insertFirst(null);
		Assert.assertArrayEquals(new Integer[] {}, lista2.toArray());
		((DoubleLinkedList<Integer>) lista2).insertFirst(null);
		Assert.assertArrayEquals(new Integer[] {}, lista2.toArray());
		((DoubleLinkedList<Integer>) lista2).insertFirst(4);
		Assert.assertArrayEquals(new Integer[] { 4 }, lista2.toArray());
	}
	
	@Test
	public void testRemoveFirst() {
		((DoubleLinkedList<Integer>) lista1).removeFirst();
		Assert.assertArrayEquals(new Integer[] { 2, 1 }, lista1.toArray());
		((DoubleLinkedList<Integer>) lista1).removeFirst();
		Assert.assertArrayEquals(new Integer[] { 1 }, lista1.toArray());
	}
	
	@Test
	public void testRemoveFirstInEmptyList() {
		((DoubleLinkedList<Integer>) lista2).removeFirst();
		Assert.assertArrayEquals(new Integer[] {}, lista2.toArray());
		((DoubleLinkedList<Integer>) lista2).removeFirst();
		Assert.assertArrayEquals(new Integer[] {}, lista2.toArray());
	}
	
	@Test
	public void testRemoveFirstInSingleElementList() {
		((DoubleLinkedList<Integer>) lista2).insertFirst(4);
		Assert.assertArrayEquals(new Integer[] { 4 }, lista2.toArray());
		((DoubleLinkedList<Integer>) lista2).removeFirst();
		Assert.assertArrayEquals(new Integer[] {}, lista2.toArray());
	}

	@Test
	public void testRemoveLast() {
		((DoubleLinkedList<Integer>) lista1).removeLast();
		Assert.assertArrayEquals(new Integer[] { 3, 2 }, lista1.toArray());
		((DoubleLinkedList<Integer>) lista1).removeLast();
		Assert.assertArrayEquals(new Integer[] { 3 }, lista1.toArray());
	}
	
	@Test
	public void testRemoveLastInEmptyList() {
		((DoubleLinkedList<Integer>) lista2).removeLast();
		Assert.assertArrayEquals(new Integer[] {}, lista2.toArray());
		((DoubleLinkedList<Integer>) lista2).removeLast();
		Assert.assertArrayEquals(new Integer[] {}, lista2.toArray());
	}
	
	@Test
	public void testRemoveLastInSingleElementList() {
		((DoubleLinkedList<Integer>) lista2).insert(4);
		Assert.assertArrayEquals(new Integer[] { 4 }, lista2.toArray());
		((DoubleLinkedList<Integer>) lista2).removeLast();
		Assert.assertArrayEquals(new Integer[] {}, lista2.toArray());
	}
	
	// Métodos de RecursiveDoubleLinkedList

		@Test
		public void testRecursiveInsertFirst() {
			((DoubleLinkedList<Integer>) lista3).insertFirst(4);
			Assert.assertArrayEquals(new Integer[] { 4, 3, 2, 1 }, lista3.toArray());
			((DoubleLinkedList<Integer>) lista3).insertFirst(7);
			Assert.assertArrayEquals(new Integer[] { 7, 4, 3, 2, 1 }, lista3.toArray());
		}
		
		
		@Test
		public void testRecursiveInsertFirstInEmptyList() {
			((DoubleLinkedList<Integer>) lista4).insertFirst(4);
			Assert.assertArrayEquals(new Integer[] { 4 }, lista4.toArray());
			((DoubleLinkedList<Integer>) lista4).insertFirst(7);
			Assert.assertArrayEquals(new Integer[] { 7, 4 }, lista4.toArray());
		}
		
		@Test
		public void testRecursiveInsertNullElement() {
			((DoubleLinkedList<Integer>) lista3).insertFirst(null);
			Assert.assertArrayEquals(new Integer[] { 3, 2, 1 }, lista3.toArray());
			((DoubleLinkedList<Integer>) lista3).insertFirst(null);
			Assert.assertArrayEquals(new Integer[] { 3, 2, 1 }, lista3.toArray());
			((DoubleLinkedList<Integer>) lista3).insertFirst(4);
			Assert.assertArrayEquals(new Integer[] { 4, 3, 2, 1 }, lista3.toArray());
		}
		
		@Test
		public void testRecursiveInsertFirstNullElementInEmptyList() {
			((DoubleLinkedList<Integer>) lista4).insertFirst(null);
			Assert.assertArrayEquals(new Integer[] {}, lista4.toArray());
			((DoubleLinkedList<Integer>) lista4).insertFirst(null);
			Assert.assertArrayEquals(new Integer[] {}, lista4.toArray());
			((DoubleLinkedList<Integer>) lista4).insertFirst(4);
			Assert.assertArrayEquals(new Integer[] { 4 }, lista4.toArray());
		}
		
		@Test
		public void testRecursiveRemoveFirst() {
			((DoubleLinkedList<Integer>) lista3).removeFirst();
			Assert.assertArrayEquals(new Integer[] { 2, 1 }, lista3.toArray());
			((DoubleLinkedList<Integer>) lista3).removeFirst();
			Assert.assertArrayEquals(new Integer[] { 1 }, lista3.toArray());
		}
		
		@Test
		public void testRecursiveRemoveFirstInEmptyList() {
			((DoubleLinkedList<Integer>) lista4).removeFirst();
			Assert.assertArrayEquals(new Integer[] {}, lista4.toArray());
			((DoubleLinkedList<Integer>) lista4).removeFirst();
			Assert.assertArrayEquals(new Integer[] {}, lista4.toArray());
		}
		
		@Test
		public void testRecursiveRemoveFirstInSingleElementList() {
			((DoubleLinkedList<Integer>) lista4).insertFirst(4);
			Assert.assertArrayEquals(new Integer[] { 4 }, lista4.toArray());
			((DoubleLinkedList<Integer>) lista4).removeFirst();
			Assert.assertArrayEquals(new Integer[] {}, lista4.toArray());
		}

		@Test
		public void testRecursiveRemoveLast() {
			((DoubleLinkedList<Integer>) lista3).removeLast();
			Assert.assertArrayEquals(new Integer[] { 3, 2 }, lista3.toArray());
			((DoubleLinkedList<Integer>) lista3).removeLast();
			Assert.assertArrayEquals(new Integer[] { 3 }, lista3.toArray());
		}
		
		@Test
		public void testRecursiveRemoveLastInEmptyList() {
			((DoubleLinkedList<Integer>) lista4).removeLast();
			Assert.assertArrayEquals(new Integer[] {}, lista4.toArray());
			((DoubleLinkedList<Integer>) lista4).removeLast();
			Assert.assertArrayEquals(new Integer[] {}, lista4.toArray());
		}
		
		@Test
		public void testRecursiveRemoveLastInSingleElementList() {
			((DoubleLinkedList<Integer>) lista4).insert(4);
			Assert.assertArrayEquals(new Integer[] { 4 }, lista4.toArray());
			((DoubleLinkedList<Integer>) lista4).removeLast();
			Assert.assertArrayEquals(new Integer[] {}, lista4.toArray());
		}
}