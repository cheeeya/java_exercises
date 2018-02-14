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

}
