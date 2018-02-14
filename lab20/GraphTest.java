import static org.junit.Assert.*;

import org.junit.Test;


public class GraphTest {

//	@Test
	public void testPathExists() {
		Graph g = new Graph(7);
		g.addUndirectedEdge(0,2); 
		g.addUndirectedEdge(0,3); 
		g.addUndirectedEdge(1,4); 
		g.addUndirectedEdge(1,5); 
		g.addUndirectedEdge(2,3); 
		g.addUndirectedEdge(2,6); 
		g.addUndirectedEdge(4,5);
		assertTrue(g.pathExists(0, 6));
		assertFalse(g.pathExists(0, 4));
		System.out.println(g.path(1, 5));
	}
	
//	@Test
	public void testPathExists1() {
		Graph g = new Graph(5);
		g.addEdge(0,1); 
		g.addEdge(1,2); 
		g.addEdge(2,0); 
		g.addEdge(2,3); 
		g.addEdge(4,2);
		assertTrue(g.pathExists(0, 3));
		assertFalse(g.pathExists(0, 4));
	}
	
	//@Test
	public void testPath(){
		Graph g = new Graph(5);
		g.addEdge(0,1); 
		g.addEdge(1,2); 
		g.addEdge(2,0); 
		g.addEdge(2,3); 
		g.addEdge(4,2);
		System.out.println(g.path(0, 3));
	}
	
	@Test
	public void testDijkstra(){
		Graph g = new Graph(5);
		g.addEdge(0, 1, 14);
		g.addEdge(0, 2, 13);
		g.addEdge(0, 3, 5);
		g.addEdge(1, 2, 12);
		g.addEdge(2, 4, 1);
		g.addEdge(3, 2, 7);
		System.out.println(g.shortestPath(0, 4));
	}

}
