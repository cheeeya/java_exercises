import static org.junit.Assert.*;
import java.util.*;

import org.junit.Test;


public class SequenceTest {

	@Test
	public void testString() {
		Sequence<String> s1 = new Sequence<String>(10);
		s1.add("one");
		s1.add("two");
		s1.add("four");
		s1.insert("three", 2);
		assertEquals("one two three four", s1.toString());
		s1.remove(0);
		System.out.println(s1.toString());
		System.out.println(s1.elementAt(0));
	}
	
	@Test
	public void testIntegers(){
		Sequence<Integer> s2 = new Sequence<Integer>(5);
		s2.add(1);
		s2.add(2);
		assertEquals("1 2", s2.toString());
		s2.insert(0, 0);
		System.out.println(s2.toString());
		s2.remove(1);
		System.out.println(s2.toString());
		System.out.println(s2.elementAt(0));

	}
	
	@Test
	public void testIterator(){
		Sequence<Integer> s3 = new Sequence<Integer>(5);
		s3.add(0);
		s3.add(1);
		s3.add(2);
		Iterator<Integer> sequenceIterator = s3.iterator();
		assertTrue(sequenceIterator.hasNext());
		assertEquals(0, (int) sequenceIterator.next());
		assertTrue(sequenceIterator.hasNext());
		assertEquals(1, (int) sequenceIterator.next());
		assertTrue(sequenceIterator.hasNext());
		assertEquals(2, (int) sequenceIterator.next());
		assertFalse(sequenceIterator.hasNext());
		
	}

}
