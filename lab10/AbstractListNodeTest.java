import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import static org.junit.Assert.*;


@RunWith(JUnit4.class)
public class AbstractListNodeTest {

	@Rule
	public ExpectedException exception = ExpectedException.none();
	
	@Test
	public void testSize() {
		NonemptyListNode neln = new NonemptyListNode("A", new NonemptyListNode("B", new NonemptyListNode("C", null)));
		assertEquals(3, neln.size());
		EmptyListNode eln = new EmptyListNode();
		assertEquals(0, eln.size());
	}
	
	@Test
	public void testGet() {
		NonemptyListNode neln = new NonemptyListNode("A", new NonemptyListNode("B", new NonemptyListNode("C", null)));
		assertEquals("C", neln.get(2));
		EmptyListNode eln = new EmptyListNode();
		exception.expect(IllegalArgumentException.class);
		eln.get(0);
	}
	
	@Test
	public void testToString(){
		NonemptyListNode neln = new NonemptyListNode("A", new NonemptyListNode("B", new NonemptyListNode("C", null)));
		assertEquals("( A B C )", neln.toString());
		EmptyListNode eln = new EmptyListNode();
		assertEquals("( )", eln.toString());
	}
	
	@Test
	public void testEquals(){
		NonemptyListNode neln = new NonemptyListNode("A", new NonemptyListNode("B", new NonemptyListNode("C", null)));
		NonemptyListNode neln2 = new NonemptyListNode("A", new NonemptyListNode("B", new NonemptyListNode("C", null)));
		assertTrue(neln.equals(neln2)); //this also checks if empty lists return true when compared to empty lists
	}
	
	@Test
	public void testAdd() {
	    AbstractListNode l1 = new EmptyListNode();
	    AbstractListNode l2 = l1.add("a");
	    assertEquals("( a )", l2.toString());
	    AbstractListNode l3 = l2.add("b");
	    assertEquals("( a b )", l3.toString());
	    assertEquals("( a )", l2.toString());
	}
	
	@Test
	public void testAppend(){
		AbstractListNode l1 = new EmptyListNode();
		AbstractListNode l2 = new NonemptyListNode("a");
		AbstractListNode l3 = l1.append(l2);
		AbstractListNode l4 = l2.append(l1);
		AbstractListNode l5 = new NonemptyListNode("b", new NonemptyListNode("c"));
		AbstractListNode l6 = l2.append(l5);
		assertEquals("( a )", l3.toString());
		assertEquals("( a )", l4.toString());
		assertEquals("( a )", l2.toString());
		assertEquals("( a b c )", l6.toString());
	}
	
	@Test
	public void testReverse(){
		AbstractListNode l1 = new NonemptyListNode("a", new NonemptyListNode("b", new NonemptyListNode("c")));
		AbstractListNode l2 = l1.reverse();
		assertEquals("( c b a )", l2.toString());
	}
	
	@Test
	public void testStraightforward() {

	    AbstractListNode empty1 = new EmptyListNode();
	    AbstractListNode empty2 = new EmptyListNode();

	    empty1 = empty1.appendInPlace (empty2);

	    assertEquals ("( )", empty1.toString());
	    assertEquals ("( )", empty2.toString());

	    AbstractListNode a = new NonemptyListNode("a");

	    a = a.appendInPlace(empty1);

	    assertEquals ("( a )", a.toString());
	    assertEquals ("( )", empty1.toString());

	    AbstractListNode b = new NonemptyListNode("b");
	    AbstractListNode c = new NonemptyListNode("c");

	    b = b.appendInPlace(c);

	    assertEquals ("( b c )", b.toString());
	    assertEquals ("( c )", c.toString());
	    
	    AbstractListNode test = new NonemptyListNode("a", new NonemptyListNode("b", new NonemptyListNode("c")));
	    AbstractListNode d = new NonemptyListNode("d");
	    test = test.appendInPlace(d);
	    assertEquals ("( a b c d )", test.toString());
	}
	
	@Test
	public void testTrickyAppendInPlace() {

	    AbstractListNode list1 = new EmptyListNode();
	    AbstractListNode list2 = new NonemptyListNode ("a");

	    list1 = list1.appendInPlace(list2);

	    assertEquals ("( a )", list1.toString());
	    assertEquals ("( a )", list2.toString());

	    list2 = list2.appendInPlace(list1);

	    assertEquals ("( a )", list1.toString());
	    assertEquals ("( a a )", list2.toString());
	}
	
	@Test
	public void testMerge(){
		AbstractListNode l1 = new NonemptyListNode(1, new NonemptyListNode(2, new NonemptyListNode (3)));
		AbstractListNode l2 = new NonemptyListNode(1, new NonemptyListNode(2, new NonemptyListNode (3)));
		l1 = AbstractListNode.merge(l1, l2);
		assertEquals("( 1 1 2 2 3 3)", l1.toString());
	}

}
