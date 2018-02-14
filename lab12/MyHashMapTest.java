import java.util.Iterator;

import junit.framework.TestCase;


public class MyHashMapTest extends TestCase {
	
	public void testDefault(){
		MyHashMap<Integer, Integer> hmap = new MyHashMap<Integer, Integer>(20);
		assertEquals(0, hmap.size());
		assertEquals(20, hmap.capacity());
	}
	
	public void testContains(){
		MyHashMap<Integer, Integer> hmap = new MyHashMap<Integer, Integer>(20);
		assertEquals(20, hmap.capacity());
		hmap.put(1,1);
		assertTrue(hmap.containsKey(1));
		assertTrue(hmap.containsValue(1));
		hmap.put(1, 5);
		assertTrue(hmap.containsKey(1));
		assertTrue(hmap.containsValue(5));
		assertFalse(hmap.containsValue(1));
		int x = hmap.remove(1);
		assertEquals(5, x);
		assertFalse(hmap.containsKey(1));
		assertFalse(hmap.containsValue(5));
		
	}
	
	public void testGet(){
		MyHashMap<Integer, Integer> hmap = new MyHashMap<Integer, Integer>(20);
		hmap.put(1, 4);
		hmap.put(2, 5);
		hmap.put(3, 6);
		hmap.put(4, 7);
		int check = hmap.get(1);
		assertEquals(4, check);
		check = hmap.get(2);
		assertEquals(5, check);
		check = hmap.get(3);
		assertEquals(6, check);
		check = hmap.get(4);
		assertEquals(7, check);
		assertNull(hmap.get(5));
	}
	
	public void testExpand(){
		MyHashMap<Integer, Integer> hmap = new MyHashMap<Integer, Integer>(3);
		hmap.put(1, 5);
		hmap.put(2, 3);
		hmap.put(3, 6);
		assertEquals(6, hmap.capacity());
	}
	
	public void testIterator(){
		MyHashMap<Integer, Integer> hmap = new MyHashMap<Integer, Integer>(20);
		hmap.put(1, 10);
		hmap.put(8, 9);
		hmap.put(15, 7);
		hmap.put(14, 3);
		hmap.put(5, 20);
		hmap.put(19, 8);
		Iterator i = hmap.iterator();
		assertTrue(i.hasNext());
		int next = (int)i.next();
		assertEquals(1, next);
		next = (int)i.next();
		assertEquals(5,next);
		next = (int) i.next();
		assertEquals(8, next);
		next = (int) i.next();
		assertEquals(14, next);
		next = (int) i.next();
		assertEquals(15, next);
		next = (int) i.next();
		assertEquals(19, next);
		assertFalse(i.hasNext());
	}
}
