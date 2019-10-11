package tp5;

import static org.junit.Assert.*;

import org.junit.Test;

public class HashTableTest {
	
	HashCouple c1 = new HashCouple("bonjour", 7);
	HashCouple c2 = new HashCouple("salut", 6);
	HashCouple c3 = new HashCouple("bonsoir", 2);
	HashCouple c4 = new HashCouple("aloha", 5);

	@Test
	public void test() {
		Chainage test = new Chainage();
		
		test.put(c1.getKey(), c1.getValue());
		test.put(c2.getKey(), c2.getValue());
		
		assertEquals(test.toString(), "[[bonjour,7],[salut,6]]");
		assertEquals(Integer.valueOf(7), test.get(c1.getKey()));
		assertTrue(test.contains(c1.getKey()));
		assertEquals(2, test.size());
		assertTrue(test.remove(c1.getKey()));
		assertEquals(1, test.size());
		assertFalse(test.contains(c1.getKey()));
	}

}
