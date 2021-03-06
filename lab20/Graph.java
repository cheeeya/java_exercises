import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.Stack;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Graph implements Iterable<Integer> {

	private LinkedList<Edge>[] myAdjLists;
	private int myVertexCount;
	private int myStartVertex;

	// Initialize a graph with the given number of vertices and no edges.
	public Graph(int numVertices) {
		myAdjLists = new LinkedList[numVertices];
		myStartVertex = 0;
		for (int k = 0; k < numVertices; k++) {
			myAdjLists[k] = new LinkedList<Edge>();
		}
		myVertexCount = numVertices;
	}

	// Change the vertex the iterator will start DFS from
	public void setStartVertex(int v) {
		if (v < myVertexCount && v >= 0) {
			myStartVertex = v;
		} else {
			throw new IllegalArgumentException(
					"Cannot set iteration start vertex to " + v + ".");
		}
	}

	// Add to the graph a directed edge from vertex v1 to vertex v2.
	public void addEdge(int v1, int v2) {
		addEdge(v1, v2, null);
	}

	// Add to the graph an undirected edge from vertex v1 to vertex v2.
	public void addUndirectedEdge(int v1, int v2) {
		addUndirectedEdge(v1, v2, null);
	}

	// Add to the graph a directed edge from vertex v1 to vertex v2,
	// with the given edge information.
	public void addEdge(int v1, int v2, Object edgeInfo) {
		myAdjLists[v1].add(new Edge(v1, v2, edgeInfo));
	}

	// Add to the graph an undirected edge from vertex v1 to vertex v2,
	// with the given edge information.
	public void addUndirectedEdge(int v1, int v2, Object edgeInfo) {
		myAdjLists[v1].add(new Edge(v1, v2, edgeInfo));
		myAdjLists[v2].add(new Edge(v2, v1, edgeInfo));
	}

	// Return true if there is an edge from vertex "from" to vertex "to";
	// return false otherwise.
	public boolean isAdjacent(int from, int to) {
		ListIterator<Edge> list = myAdjLists[from].listIterator();
		while (list.hasNext()) {
			if (list.next().to() == to) {
				return true;
			}
		}
		return false;
	}

	// Returns a list of all the neighboring vertices 'u'
	// such that the edge (VERTEX, 'u') exists in this graph.
	public List neighbors(int vertex) {
		return myAdjLists[vertex];
	}

	// Return the number of incoming vertices for the given vertex,
	// i.e. the number of vertices v such that (v, vertex) is an edge.
	public int inDegree(int vertex) {
		int count = 0;
		for (LinkedList<Edge> list : myAdjLists) {
			for (Edge e : list) {
				if (e.to() == vertex) {
					count += 1;
				}
			}
		}
		return count;
	}

	public Iterator<Integer> iterator() {
		return new TopologicalIterator();
	}

	private int howManyVertex() {
		return myVertexCount;
	}

	// A class that iterates through the vertices of this graph, starting with a
	// given vertex.
	// Does not necessarily iterate through all vertices in the graph: if the
	// iteration starts
	// at a vertex v, and there is no path from v to a vertex w, then the
	// iteration will not
	// include w
	private class DFSIterator implements Iterator<Integer> {

		private Stack<Integer> fringe;
		private HashSet<Integer> visited;

		public DFSIterator(Integer start) {
			fringe = new Stack<Integer>();
			visited = new HashSet<Integer>();
			fringe.push(start);
		}

		public boolean hasNext() {
			return !fringe.isEmpty();
		}

		public Integer next() {
			int next = fringe.pop();
			visited.add(next);
			List<Edge> l = neighbors(next);
			for (Edge e : l) {
				if (!visited.contains(e.to())) {
					fringe.push(e.to());
				}
			}
			return next;
		}

		// ignore this method
		public void remove() {
			throw new UnsupportedOperationException(
					"vertex removal not implemented");
		}

	}

	// Return the collected result of iterating through this graph's
	// vertices as an ArrayList.
	public ArrayList<Integer> visitAll(int startVertex) {
		ArrayList<Integer> result = new ArrayList<Integer>();
		Iterator<Integer> iter = new DFSIterator(startVertex);
		while (iter.hasNext()) {
			result.add(iter.next());
		}
		return result;
	}

	// Returns true iff there exists a path from STARVETEX to
	// STOPVERTEX. Assumes both STARTVERTEX and STOPVERTEX are
	// in this graph. If STARVERTEX == STOPVERTEX, returns true.
	public boolean pathExists(int startVertex, int stopVertex) {
		if (startVertex == stopVertex) {
			return true;
		}
		ArrayList<Integer> vertices = visitAll(startVertex);
		if (vertices.contains(stopVertex)) {
			return true;
		}
		return false;
	}

	// Returns the path from startVertex to stopVertex.
	// If no path exists, returns an empty arrayList.
	// If startVertex == stopVertex, returns a one element arrayList.
	public ArrayList<Integer> path(int startVertex, int stopVertex) {
		ArrayList<Integer> path = new ArrayList<Integer>();
		ArrayList<Integer> visited = new ArrayList<Integer>();
		LinkedList<Integer> linkedPath = new LinkedList<Integer>();
		boolean hasPath = false;
		if (startVertex == stopVertex) {
			path.add(startVertex);
			return path;
		}
		Iterator<Integer> iter = new DFSIterator(startVertex);
		while (iter.hasNext() && !hasPath) {
			int checkVertex = iter.next();
			visited.add(checkVertex);
			if (checkVertex == stopVertex) {
				hasPath = true;
			}
		}
		if (hasPath) {
			int check = visited.get(visited.size() - 1);
			linkedPath.addFirst(check);
			while (check != startVertex) {
				for (Integer i : visited) {
					if (isAdjacent(i, check)) {
						linkedPath.addFirst(i);
						check = i;
						break;
					}
				}
			}
		}
		if (linkedPath.size() > 0) {
			for (Integer vertices : linkedPath) {
				path.add(vertices);
			}
		}
		return path;
	}

	public ArrayList<Integer> shortestPath(int startVertex, int endVertex) {
		int[] myWeightArray = new int[howManyVertex()];
		VertexComparator comp = new VertexComparator(myWeightArray);
		PriorityQueue<Integer> fringe = new PriorityQueue<Integer>(
				howManyVertex(), comp);
		HashMap<Integer, Integer> myParentMap = new HashMap<Integer, Integer>();
		Integer max = Integer.MAX_VALUE;
		for (int i = 0; i < howManyVertex(); i++) {
			if (i == startVertex) {
				myWeightArray[i] = 0;
			} else {
				myWeightArray[i] = max;
			}
			fringe.add(i);
		}
		while (!fringe.isEmpty()) {
			Integer currentVertex = fringe.poll();
			if (currentVertex == endVertex) {
				break;
			}
			for (Edge e : (List<Edge>) neighbors(currentVertex)) {
				if (fringe.contains(e.to())) {
					Integer newWeight = (Integer) e.info()
							+ myWeightArray[currentVertex];
					if (newWeight < myWeightArray[e.to()]) {
						myWeightArray[e.to()] = newWeight;
						myParentMap.put(e.to(), currentVertex);
						fringe.remove(e.to());
						fringe.add(e.to());
					}
				}
			}
		}
		return reconstructPath(myParentMap, startVertex, endVertex);
	}

	private ArrayList<Integer> reconstructPath(
			HashMap<Integer, Integer> parents, int startVertex, int endVertex) {
		LinkedList<Integer> linkedPath = new LinkedList<Integer>();
		int prev = endVertex;
		while (prev != startVertex) {
			linkedPath.addFirst(prev);
			prev = parents.get(prev);
		}
		ArrayList<Integer> result = new ArrayList<Integer>();
		result.add(startVertex);
		for (Integer i : linkedPath) {
			result.add(i);
		}
		return result;
	}

	public class VertexComparator implements Comparator<Integer> {

		int[] weights;

		public VertexComparator(int[] weightArray) {
			weights = weightArray;
		}

		@Override
		public int compare(Integer v1, Integer v2) {
			if (weights[v1] < weights[v2]) {
				return -1;
			} else if (weights[v1] == weights[v2]) {
				return 0;
			} else {
				return 1;
			}
		}
	}

	public ArrayList<Integer> topologicalSort() {
		ArrayList<Integer> result = new ArrayList<Integer>();
		Iterator<Integer> iter = new TopologicalIterator();
		while (iter.hasNext()) {
			result.add(iter.next());
		}
		return result;
	}

	private class TopologicalIterator implements Iterator<Integer> {

		private Stack<Integer> fringe;
		private int[] currentInDegree;

		// more instance variables go here

		public TopologicalIterator() {
			fringe = new Stack<Integer>();
			currentInDegree = new int[howManyVertex()];
			for (int k = 0; k < howManyVertex(); k++) {
				currentInDegree[k] = inDegree(k);
				if (inDegree(k) == 0) {
					fringe.push(k);
				}
			}
		}

		public boolean hasNext() {
			return !fringe.isEmpty();
		}

		public Integer next() {
			int next = fringe.pop();
			List<Edge> adjV = neighbors(next);
			for (Edge e : adjV) {
				currentInDegree[e.to()] -= 1;
				if (currentInDegree[e.to()] == 0) {
					fringe.push(e.to());
				}
			}
			return next;
		}

		public void remove() {
			throw new UnsupportedOperationException(
					"vertex removal not implemented");
		}

	}

	private class Edge {

		private Integer myFrom;
		private Integer myTo;
		private Object myEdgeInfo;

		public Edge(int from, int to, Object info) {
			myFrom = new Integer(from);
			myTo = new Integer(to);
			myEdgeInfo = info;
		}

		public Integer to() {
			return myTo;
		}

		public Object info() {
			return myEdgeInfo;
		}

		public String toString() {
			return "(" + myFrom + "," + myTo + ",dist=" + myEdgeInfo + ")";
		}

	}

	public static void main(String[] args) {
		ArrayList<Integer> result;

		Graph g1 = new Graph(5);
		g1.addEdge(0, 1);
		g1.addEdge(0, 2);
		g1.addEdge(0, 4);
		g1.addEdge(1, 2);
		g1.addEdge(2, 0);
		g1.addEdge(2, 3);
		g1.addEdge(4, 3);
		System.out.println("Traversal starting at 0");
		result = g1.visitAll(0);
		Iterator<Integer> iter;
		iter = result.iterator();
		while (iter.hasNext()) {
			System.out.println(iter.next() + " ");
		}
		System.out.println();
		System.out.println();
		System.out.println("Traversal starting at 2");
		result = g1.visitAll(2);
		iter = result.iterator();
		while (iter.hasNext()) {
			System.out.println(iter.next() + " ");
		}
		System.out.println();
		System.out.println();
		System.out.println("Traversal starting at 3");
		result = g1.visitAll(3);
		iter = result.iterator();
		while (iter.hasNext()) {
			System.out.println(iter.next() + " ");
		}
		System.out.println();
		System.out.println();
		System.out.println("Traversal starting at 4");
		result = g1.visitAll(4);
		iter = result.iterator();
		while (iter.hasNext()) {
			System.out.println(iter.next() + " ");
		}
		System.out.println();
		System.out.println();
		System.out.println("Path from 0 to 3");
		result = g1.path(0, 3);
		iter = result.iterator();
		while (iter.hasNext()) {
			System.out.println(iter.next() + " ");
		}
		System.out.println();
		System.out.println();
		System.out.println("Path from 0 to 4");
		result = g1.path(0, 4);
		iter = result.iterator();
		while (iter.hasNext()) {
			System.out.println(iter.next() + " ");
		}
		System.out.println();
		System.out.println();
		System.out.println("Path from 1 to 3");
		result = g1.path(1, 3);
		iter = result.iterator();
		while (iter.hasNext()) {
			System.out.println(iter.next() + " ");
		}
		System.out.println();
		System.out.println();
		System.out.println("Path from 1 to 4");
		result = g1.path(1, 4);
		iter = result.iterator();
		while (iter.hasNext()) {
			System.out.println(iter.next() + " ");
		}
		System.out.println();
		System.out.println();
		System.out.println("Path from 4 to 0");
		result = g1.path(4, 0);
		if (result.size() != 0) {
			System.out.println("*** should be no path!");
		}

		Graph g2 = new Graph(5);
		g2.addEdge(0, 1);
		g2.addEdge(0, 2);
		g2.addEdge(0, 4);
		g2.addEdge(1, 2);
		g2.addEdge(2, 3);
		g2.addEdge(4, 3);
		System.out.println();
		System.out.println();
		System.out.println("Topological sort");
		result = g2.topologicalSort();
		iter = result.iterator();
		while (iter.hasNext()) {
			System.out.println(iter.next() + " ");
		}
	}

}
