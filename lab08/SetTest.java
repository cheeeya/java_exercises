import static org.junit.Assert.*;

import org.junit.Test;


public class SetTest {

	@Test
	public void testIterator() {
		Set s1 = new Set(6);
		s1.insert(1);
		s1.insert(3);
		s1.insert(5);
		s1.initIterator();
		assertTrue(s1.hasNext());
		assertEquals(1, s1.next());
		assertTrue(s1.hasNext());
		assertEquals(3, s1.next());
		assertTrue(s1.hasNext());
		assertEquals(5, s1.next());
		assertFalse(s1.hasNext());
	}

}
