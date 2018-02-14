import static org.junit.Assert.*;

import org.junit.Test;


public class ListNodeTest {

	@Test
	public void testGet() {
		ListNode l1 = new ListNode("A", new ListNode("B", new ListNode("C", null)));
		
		assertEquals("C", l1.get(2));
	}

}
