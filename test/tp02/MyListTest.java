package tp02;

/**
 * SDD Seance TP 2 : Exercice 2
 * Implémentation d'une liste doublement chain
 * 
 * @author <a href="mailto:Frederic.Guyomarch@univ-lille1.fr">Frédéric Guyomarch</a>, IUT-A, Universite de Lille
 */

import static org.junit.Assert.*;

import org.junit.Test;


public class MyListTest {

	@Test
	public void testIsEmpty() {
		MyList<Integer> list = new MyList<Integer>();
		assertTrue(list.isEmpty());
		list.add(20);
		assertFalse(list.isEmpty());
	}

	@Test
	public void testClear() {
		MyList<Integer> list = new MyList<Integer>();
		assertNull(list.next);
		assertNull(list.previous);
		list.add(33);
		list.add(45);
		assertNotNull(list.next);
		assertNull(list.previous);
		list.clear();
		assertNull(list.next);
		assertNull(list.previous);
	}

	@Test
	public void testToString() {
		MyList<Integer> list = new MyList<Integer>();
		assertEquals(list.toString(), "");
		list.add(33);
		list.add(45);
		list.add(7);
		assertEquals(list.toString(), "33 45 7");
	}


	
/******************************************************/



	
	@Test
	public void testAddE() {
		MyList<Integer> list = new MyList<Integer>();
		assertTrue(list.add(33));
		list.add(45);	
		list.add(7);
		assertEquals(Integer.valueOf(33), list.element);
		assertEquals(Integer.valueOf(45), list.next.element);
		assertEquals(Integer.valueOf(7), list.next.next.element);
	}

	@Test
	public void testSize() {
		MyList<Integer> list = new MyList<Integer>();
		assertEquals(0, list.size());
		list.add(33);
		list.add(45);
		list.add(7);
		assertEquals(3, list.size());
		list.remove(0);
		assertEquals(2, list.size());
		list.remove(1);
		assertEquals(1, list.size());
		list.remove(0);
		assertEquals(0, list.size());
		list.add(7);
		assertEquals(1, list.size());
	}
/*
	@Test
	public void testGet() {
		MyList<Integer> list = new MyList<Integer>();
		list.add(33);
		list.add(45);
		list.add(7);
		assertEquals(Integer.valueOf(45), list.get(1));
		assertNotEquals(Integer.valueOf(33), list.get(2));
	}

	@Test(expected=IndexOutOfBoundsException.class)
	public void testGetWrongIndex1() {
		MyList<Integer> list = new MyList<Integer>();
		list.add(33);
		list.add(45);
		list.add(7);
		list.get(-1);;		
	}

	@Test(expected=IndexOutOfBoundsException.class)
	public void testGetWrongIndex2() {
		MyList<Integer> list = new MyList<Integer>();
		list.add(33);
		list.add(45);
		list.add(7);
		list.get(3);;		
	}
	
	@Test
	public void testIndexOf() {
		MyList<Integer> list = new MyList<Integer>();
		list.add(33);
		list.add(45);
		list.add(7);
		list.add(33);

		assertEquals(1, list.indexOf(Integer.valueOf(45)));
		assertEquals(0, list.indexOf(Integer.valueOf(33)));
		assertEquals(2, list.indexOf(Integer.valueOf(7)));
		assertEquals(-1, list.indexOf(Integer.valueOf(25)));
	}

	@Test
	public void testLastIndexOf() {
		MyList<Integer> list = new MyList<Integer>();
		list.add(33);
		list.add(45);
		list.add(7);
		list.add(33);

		assertEquals(3, list.lastIndexOf(Integer.valueOf(33)));
		assertEquals(2, list.lastIndexOf(Integer.valueOf(7)));
		assertEquals(-1, list.lastIndexOf(Integer.valueOf(25)));
	}

	@Test
	public void testContains() {
		MyList<Integer> list = new MyList<Integer>();
		list.add(33);
		list.add(45);
		list.add(7);
		assertTrue(list.contains(Integer.valueOf(33)));
		assertFalse(list.contains(Integer.valueOf(8)));
	}

	@Test
	public void testAddIntE() {
		MyList<Integer> list = new MyList<Integer>();
		list.add(5);
		list.add(4);
		list.add(33);
		assertEquals(Integer.valueOf(33), list.next.next.element);
		list.add(0,96);
		assertEquals(Integer.valueOf(96), list.element);
		assertEquals(Integer.valueOf(33), list.next.next.next.element);
	}

	@Test
	public void testRemoveObject() {
		MyList<Integer> list = new MyList<Integer>();
		list.add(5);
		list.add(4);
		list.add(33);
		assertFalse(list.remove(Integer.valueOf(20)));
		assertEquals(Integer.valueOf(33), list.next.next.element);
		assertTrue(list.remove(Integer.valueOf(33)));
		list.add(Integer.valueOf(35));
		assertNotEquals(Integer.valueOf(33), list.next.next.element);
	}

	@Test
	public void testRemoveInt() {
		MyList<Integer> list = new MyList<Integer>();
		list.add(5);
		list.add(4);
		list.add(33);
		assertEquals(Integer.valueOf(33), list.remove(2));
		assertEquals(Integer.valueOf(4), list.next.element);
		assertEquals(Integer.valueOf(5), list.remove(0));
		assertEquals(Integer.valueOf(4), list.element);
		assertEquals(Integer.valueOf(4), list.remove(0));
		assertTrue(list.isEmpty());
	}
	
	@Test(expected=IndexOutOfBoundsException.class)
	public void testRemoveFromEmpty() {
		MyList<Integer> list = new MyList<Integer>();
		list.remove(0);
	}
	
	@Test(expected=IndexOutOfBoundsException.class)
	public void testRemoveWrongIndex1() {
		MyList<Integer> list = new MyList<Integer>();
		list.add(2); 
		list.add(5);
		list.add(21);
		list.remove(-1);
	}
	
	@Test(expected=IndexOutOfBoundsException.class)
	public void testRemoveWrongIndex2() {
		MyList<Integer> list = new MyList<Integer>();
		list.add(2); 
		list.add(5);
		list.add(21);
		list.remove(3);
	}
	
*/
}
