import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

import java.util.ArrayList;


public class BinaryTreeTest {

	@Test
	public void testArrayListBT() {
		ArrayList<String> preorder = new ArrayList<>(Arrays.asList("A", "B", "C", "D", "E", "F"));
		ArrayList<String> inorder = new ArrayList<>(Arrays.asList("B", "A", "E", "D", "F", "C"));
		BinaryTree bt = new BinaryTree(preorder, inorder);
		BinaryTree.print(bt, "ArrayList BT");
	}

}
