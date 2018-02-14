import static org.junit.Assert.*;

import org.junit.Test;


public class SplayTreeTest {

	@Test
	public void testSplay() {
		SplayTree<Integer, String> st = new SplayTree<>();
		st.put(0, "zero");
		st.put(1, "one");
		st.put(2, "two");
		st.put(3, "three");
		st.put(4, "four");
		st.put(5, "five");
		st.put(6, "six");
		st.put(7, "seven");
		st.put(8, "eight");
		st.put(9, "nine");
		System.out.println(st);
		System.out.println(st.get(1));
		System.out.println(st);
		
	
	}
}