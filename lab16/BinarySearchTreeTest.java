import static org.junit.Assert.*;

import org.junit.Test;


public class BinarySearchTreeTest {

	@Test
	public void testAdd() {
		BinarySearchTree<String> bst = new BinarySearchTree<String>();
		bst.add("C");
		bst.add("A");
		bst.add("E");
		bst.add("D");
		bst.add("B");
		BinaryTree.print(bst, "ABCDE");
		assertEquals("C", bst.myRoot.myItem);
		assertEquals("A", bst.myRoot.myLeft.myItem);
		assertEquals("B", bst.myRoot.myLeft.myRight.myItem);
		assertEquals("E", bst.myRoot.myRight.myItem);
		assertEquals("D", bst.myRoot.myRight.myLeft.myItem);
	}

}
